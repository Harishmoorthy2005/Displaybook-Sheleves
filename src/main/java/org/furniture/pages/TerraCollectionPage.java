package org.furniture.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ExtentReportManager;
import utils.LoggerManager;
import utils.PopupHandler;
import java.time.Duration;

public class TerraCollectionPage {

    WebDriver driver;
    WebDriverWait wait;
    public TerraCollectionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @FindBy(xpath = "//a[contains(@href,'/collection/terra-collection')]")
    WebElement discoverAllTerraProducts;
    @FindBy(xpath = "//div[text()='ALL FILTERS']")
    WebElement allFiltersBtn;
    @FindBy(xpath = "//span[text()='Sort']")
    WebElement sortSection;
    @FindBy(xpath = "//div[text()='Price High to Low']")
    WebElement priceHighToLowOption;
    @FindBy(xpath = "//div[@role='button' and @aria-label='Close filter drawer']")
    WebElement closeSidebarBtn;

    // Scroll to Discover all Terra products
    public void scrollToDiscoverAllTerraProducts() {
        PopupHandler.closePopupIfPresent(driver);
        LoggerManager.info("Scrolling to Discover all Terra products link");
        ExtentReportManager.getTest().info("Scrolling to Discover all Terra products link");
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                discoverAllTerraProducts);
    }

    // Click Discover all Terra products
    public void clickDiscoverAllTerraProducts() {
        PopupHandler.closePopupIfPresent(driver);
        LoggerManager.info("Clicking Discover all Terra products");
        ExtentReportManager.getTest().info("Clicking Discover all Terra products");
        wait.until(ExpectedConditions.elementToBeClickable(discoverAllTerraProducts));
        discoverAllTerraProducts.click();
    }

    // Switch to newly opened tab
    public void switchToNewTab() {
        PopupHandler.closePopupIfPresent(driver);
        String currentWindow = driver.getWindowHandle();
        for (String window : driver.getWindowHandles()) {
            if (!window.equals(currentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    // Validate Terra Products page
    public boolean isTerraProductsPageDisplayed() {
        PopupHandler.closePopupIfPresent(driver);
        LoggerManager.info("Validating Terra Collection products page");
        wait.until(ExpectedConditions.urlContains("terra-collection"));
        String currentUrl = driver.getCurrentUrl();
        LoggerManager.info("Current URL: " + currentUrl);
        return currentUrl.contains("/collection/terra-collection");
    }

    // Open All Filters
    public void openAllFilters() {
        PopupHandler.closePopupIfPresent(driver);
        LoggerManager.info("Opening All Filters");
        wait.until(
                        ExpectedConditions.elementToBeClickable(allFiltersBtn))
                .click();
    }

    // Expand Sort section
    public void expandSort() {
        PopupHandler.closePopupIfPresent(driver);
        LoggerManager.info("Expanding Sort section");
        wait.until(
                        ExpectedConditions.elementToBeClickable(sortSection))
                .click();
    }

    // Select Price High to Low
    public void selectPriceHighToLow() {
        PopupHandler.closePopupIfPresent(driver);
        LoggerManager.info("Selecting Price High to Low");
        wait.until(
                        ExpectedConditions.elementToBeClickable(priceHighToLowOption))
                .click();
    }

    // Close Filter Sidebar
    public void closeFilterPanel() {
        PopupHandler.closePopupIfPresent(driver);
        LoggerManager.info("Closing Filter and Sort sidebar");
        wait.until(ExpectedConditions.elementToBeClickable(closeSidebarBtn))
                .click();
    }
}
