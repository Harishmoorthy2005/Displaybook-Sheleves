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
import java.util.List;

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
    @FindBy(xpath="//h2[@class=\"XxwSy\"]")
    List<WebElement> products;

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



    //check the product display
    public int productCount(){
        PopupHandler.closePopupIfPresent(driver);
        return products.size();
    }
}
