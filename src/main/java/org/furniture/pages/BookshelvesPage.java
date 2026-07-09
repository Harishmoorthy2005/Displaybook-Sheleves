package org.furniture.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LoggerManager;
import utils.PopupHandler;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class BookshelvesPage {
    WebDriver driver;
    WebDriverWait wait;

    public BookshelvesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    // Search Box
    @FindBy(id = "searchInput")
    WebElement searchBox;

    // Result Title
    @FindBy(xpath = "//h1[contains(text(),'Bookshelves')]")
    WebElement title;

    // All Filters Button
    @FindBy(className = "qJoGr")
    WebElement allFiltersBtn;

    // Price Section
    @FindBy(xpath = "//div[@aria-label='Price']")
    WebElement priceSection;

    // Maximum Price Input
    @FindBy(xpath = "//input[@type='text' and contains(@aria-label,'Maximum')]")
    WebElement maxPriceInput;

    // Apply Filter Button
    @FindBy(xpath = "//button[.//text()='Apply' or contains(.,'Apply')]")
    WebElement applyFilterBtn;

    // Bookshelf names
    @FindBy(xpath = "//h2[contains(text(),'Bookshelf')]")
    List<WebElement> bookshelfNames;

    // Bookshelf prices
    @FindBy(xpath ="//div[@role='link']//div[contains(text(),'₹')]")
    List<WebElement> bookshelfPrices;

    @FindBy(css = "span.PpQnM")
    WebElement productsCount;

    public void searchBookshelves() {
        PopupHandler.closePopupIfPresent(driver);
        LoggerManager.info("Waiting for search box");
        PopupHandler.closePopupIfPresent(driver);
        wait.until(ExpectedConditions.visibilityOf(searchBox));
        try {
            searchBox.click();
        }
        catch(ElementClickInterceptedException e) {
            PopupHandler.closePopupIfPresent(driver);
            searchBox.click();
        }
        LoggerManager.info("Clicking search box");
        LoggerManager.info("Typing Bookshelves");
        searchBox.sendKeys("Bookshelves");
        LoggerManager.info("Pressing Enter");
        searchBox.sendKeys(Keys.ENTER);
    }

    public boolean isBookshelvesPageDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(title));
        return title.isDisplayed();
    }

    public void openFilters() {
        PopupHandler.closePopupIfPresent(driver);
        LoggerManager.info("Clicking All Filters button");
        wait.until(ExpectedConditions.elementToBeClickable(allFiltersBtn));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", allFiltersBtn);
        LoggerManager.info("Filters panel opened successfully");
    }

    public void applyPriceFilter() {
        PopupHandler.closePopupIfPresent(driver);
        LoggerManager.info("Clicking All Filters");
        wait.until(ExpectedConditions.elementToBeClickable(allFiltersBtn));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", allFiltersBtn);
        LoggerManager.info("All Filters opened");
        LoggerManager.info("Scrolling to Price");
        wait.until(ExpectedConditions.visibilityOf(priceSection));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", priceSection);
        priceSection.click();
        LoggerManager.info("Setting max price");
        wait.until(ExpectedConditions.elementToBeClickable(maxPriceInput));
        maxPriceInput.clear();
        maxPriceInput.sendKeys("15000");
        maxPriceInput.sendKeys(Keys.TAB);
        LoggerManager.info("Waiting for UI to update after entering price");
        wait.until(ExpectedConditions.attributeContains(
                maxPriceInput,
                "value",
                "15"
        ));
        LoggerManager.info("Clicking Apply button");
        wait.until(ExpectedConditions.elementToBeClickable(applyFilterBtn));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", applyFilterBtn);
        LoggerManager.info("Price filter applied successfully");
    }

    public void selectOpenStorage() {
        LoggerManager.info("Scrolling inside All Filters panel");
        ((JavascriptExecutor) driver).executeScript(
                "document.querySelector(\"div[role='dialog']\").scrollTop=300");
        LoggerManager.info("Clicking Storage Type");
        WebElement storageType = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("(//span[text()='Storage Type'])[2]")));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", storageType);
        LoggerManager.info("Storage Type expanded");
        LoggerManager.info("Selecting Open Storage");
        WebElement openStorage = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[contains(text(),'Open Storage')]")));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", openStorage);
        LoggerManager.info("Open Storage selected successfully");
    }

    public void applyFilters() {
        LoggerManager.info("Clicking Apply Filter button");
        WebElement applyBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(.,'Apply Filter')]")));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", applyBtn);
        LoggerManager.info("Filters applied successfully");
        wait.until(ExpectedConditions.invisibilityOf(applyBtn));
    }

    public int getFilteredProductsCount() {
        PopupHandler.closePopupIfPresent(driver);
        WebElement countElement = wait.until(
                ExpectedConditions.visibilityOf(productsCount));
        wait.until(driver -> {
            String text = countElement.getText()
                    .replaceAll("[^0-9]", "");
            if (text.isEmpty()) {
                return false;
            }
            return Integer.parseInt(text) < 607;
        });
        String countText = countElement.getText();
        LoggerManager.info("Count text found: " + countText);
        return Integer.parseInt(
                countText.replaceAll("[^0-9]", "")
        );
    }

    public List<Double> getFirstTwentyBookshelfPrices() {
        PopupHandler.closePopupIfPresent(driver);
        wait.until(ExpectedConditions.visibilityOfAllElements(bookshelfPrices));
        return bookshelfPrices.stream()
                .filter(WebElement::isDisplayed)
                .limit(20)
                .map(WebElement::getText)
                .map(price -> price.replace("₹", "")
                        .replace(",", "")
                        .trim())
                .map(Double::parseDouble)
                .collect(toList());
    }

    public List<String> getTopThreeBookshelfNames() {
        PopupHandler.closePopupIfPresent(driver);
        wait.until(ExpectedConditions.visibilityOfAllElements(bookshelfNames));
        return bookshelfNames.stream()
                .limit(3)
                .map(WebElement::getText)
                .collect(toList());
    }

    public List<String> getTopThreeBookshelfPrices() {
        PopupHandler.closePopupIfPresent(driver);
        return bookshelfPrices.stream()
                .filter(WebElement::isDisplayed)   // KEY FIX
                .limit(3)
                .map(WebElement::getText)
                .collect(toList());
    }



}