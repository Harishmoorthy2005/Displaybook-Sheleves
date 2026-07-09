package org.furniture.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.LoggerManager;
import utils.ExtentReportManager;
import utils.PopupHandler;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    @FindBy(xpath = "//span[normalize-space()='New Arrivals']")
    WebElement newArrivalsMenu;

    @FindBy(id = "category-menu-0")
    WebElement newArrivalsDropdown;

    @FindBy(xpath = "//a[text()='Terra Collection']")
    WebElement terraCollection;

    @FindBy(xpath = "//a[text()='Terra Collection']//ancestor::li")
    WebElement terraSection;

    @FindBy(xpath = "//a[text()='Terra Collection']//ancestor::li//ul//a")
    List<WebElement> terraItems;



    // Hover on New Arrivals
    public void hoverOnNewArrivals() {
        PopupHandler.closePopupIfPresent(driver);
        LoggerManager.info("Waiting for New Arrivals menu");
        wait.until(ExpectedConditions.visibilityOf(newArrivalsMenu));
        LoggerManager.info("Hovering on New Arrivals menu");
        ExtentReportManager.getTest().info("Hovering on New Arrivals");
        Actions actions = new Actions(driver);
        actions.moveToElement(newArrivalsMenu)
                .pause(Duration.ofSeconds(2))
                .perform();
        LoggerManager.info("Hover action completed");
        ExtentReportManager.getTest().info("Hover completed successfully");
    }

    // Check dropdown is displayed
    public boolean isNewArrivalsDropdownDisplayed() {
        PopupHandler.closePopupIfPresent(driver);
        LoggerManager.info("Validating New Arrivals dropdown visibility");
        ExtentReportManager.getTest().info("Checking dropdown visibility");
        wait.until(ExpectedConditions.visibilityOf(newArrivalsDropdown));
        LoggerManager.info("Dropdown container is visible");
        wait.until(ExpectedConditions.visibilityOf(terraCollection));
        LoggerManager.info("Terra Collection is visible inside dropdown");
        boolean result =
                newArrivalsDropdown.isDisplayed() &&
                        terraCollection.isDisplayed();
        LoggerManager.info("Final dropdown validation result: " + result);
        ExtentReportManager.getTest().info("Dropdown displayed: " + result);
        return result;
    }

    // Get all sub-menu of Terra Collection
    public List<String> getTerraCollectionItems() {
        PopupHandler.closePopupIfPresent(driver);
        List<String> itemsText = new ArrayList<>();
        LoggerManager.info("Capturing Terra Collection submenu items");
        ExtentReportManager.getTest().info("Fetching Terra Collection items");
        wait.until(ExpectedConditions.visibilityOf(terraSection));
        for (WebElement item : terraItems) {
            String text = item.getText().trim();
            LoggerManager.info("Found item: " + text);
            itemsText.add(text);
        }
        return itemsText;
    }

    public void clickTerraCollection() {
        PopupHandler.closePopupIfPresent(driver);
        LoggerManager.info("Waiting for Terra Collection option");
        wait.until(ExpectedConditions.visibilityOf(terraCollection));
        LoggerManager.info("Clicking Terra Collection");
        ExtentReportManager.getTest().info("Clicking Terra Collection");
        terraCollection.click();
    }

    public boolean isTerraCollectionPageDisplayed() {
        PopupHandler.closePopupIfPresent(driver);
        LoggerManager.info("Validating Terra Collection page");
        wait.until(ExpectedConditions.urlContains("new-terra-collection"));
        String currentUrl = driver.getCurrentUrl();
        LoggerManager.info("Current URL: " + currentUrl);
        return currentUrl.contains("new-terra-collection");
    }
}