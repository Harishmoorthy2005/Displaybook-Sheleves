package org.furniture.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import utils.LoggerManager;
import utils.ExtentReportManager;
import java.time.Duration;
import java.util.List;

public class TerraBedroomPage {

    WebDriver driver;
    WebDriverWait wait;
    public TerraBedroomPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @FindBy(xpath = "//div[text()='ALL FILTERS']")
    WebElement allFiltersBtn;
    @FindBy(xpath = "//div[@aria-label='Primary Material']")
    WebElement primaryMaterialSection;
    @FindBy(xpath = "//div[@aria-label='Bed Size']")
    WebElement bedSizeSection;
    @FindBy(xpath = "//div[@aria-label='Storage Type']")
    WebElement storageTypeSection;
    @FindBy(xpath = "//div[text()='Solid Wood']")
    WebElement solidWoodCheckbox;
    @FindBy(xpath = "//div[text()='King']")
    WebElement kingCheckbox;
    @FindBy(xpath = "//div[text()='Non Storage']")
    WebElement nonStorageCheckbox;
    @FindBy(xpath = "//button[@data-testid='plp-filter-apply-button']")
    WebElement applyFilterBtn;
    @FindBy(xpath = "//span[contains(text(),'Products')]")
    WebElement productCountText;
    @FindBy(xpath = "//div[contains(@data-testid,'product')]")
    List<WebElement> productList;

    // Apply filters
    public void applyFiltersUsingAllFilters() {
        LoggerManager.info("Clicking All Filters");
        wait.until(ExpectedConditions.elementToBeClickable(allFiltersBtn)).click();
        LoggerManager.info("Expanding Primary Material");
        wait.until(ExpectedConditions.elementToBeClickable(primaryMaterialSection)).click();
        LoggerManager.info("Selecting Solid Wood");
        wait.until(ExpectedConditions.visibilityOf(solidWoodCheckbox)).click();
        LoggerManager.info("Expanding Bed Size");
        wait.until(ExpectedConditions.elementToBeClickable(bedSizeSection)).click();
        LoggerManager.info("Selecting King");
        wait.until(ExpectedConditions.visibilityOf(kingCheckbox)).click();
        LoggerManager.info("Expanding Storage Type");
        wait.until(ExpectedConditions.elementToBeClickable(storageTypeSection)).click();
        LoggerManager.info("Selecting Non Storage");
        wait.until(ExpectedConditions.visibilityOf(nonStorageCheckbox)).click();
        LoggerManager.info("Applying filters");
        ExtentReportManager.getTest().info("Applying filters");
        wait.until(ExpectedConditions.elementToBeClickable(applyFilterBtn)).click();
    }

    // Get product count from UI text
    public String getProductCountText() {
        LoggerManager.info("Waiting for product count");
        wait.until(ExpectedConditions.visibilityOf(productCountText));
        String text = productCountText.getText();
        LoggerManager.info("Product count text: " + text);
        System.out.println(text);
        return text;
    }

    // Get product list size (DOM validation)
    public int getProductListSize() {
        LoggerManager.info("Waiting for product list");
        wait.until(ExpectedConditions.visibilityOfAllElements(productList));
        int size = productList.size();
        LoggerManager.info("Product list size: " + size);
        return size;
    }
}