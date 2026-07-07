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

    @FindBy(xpath = "//div[@aria-label='Sort By filter']")
    WebElement sortBy;

    @FindBy(xpath = "//div[contains(text(),'Discount High to Low')]")
    WebElement discountHighToLow;

    @FindBy(xpath = "//h2[contains(@class,'XxwSy')]")
    List<WebElement> productNames;

    @FindBy(xpath = "//div[@role='link']")
    List<WebElement> productCards;

    @FindBy(xpath = "//*[contains(text(),'Bookshelves')]")
    WebElement bookshelvesCategory;

    @FindBy(xpath = "(//div[@role='link']//img)[1]")
    WebElement firstProductImage;

    @FindBy(xpath = "(//h2[contains(@class,'XxwSy')])[1]")
    WebElement firstProduct;

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

    public String clickFirstProduct() {
        PopupHandler.closePopupIfPresent(driver);
        wait.until(ExpectedConditions.visibilityOf(firstProduct));
        String productName = firstProduct.getText();
        LoggerManager.info("Selected Product : " + productName);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstProduct);
        return productName;
    }

    public void switchToProductTab() {
        String parent = driver.getWindowHandle();
        wait.until(driver ->
                driver.getWindowHandles().size() > 1);
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(parent)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        LoggerManager.info("Switched to Product tab");
    }

    public void selectPrimaryMaterialEngineeredWood() {
        LoggerManager.info("Expanding Primary Material");
        WebElement primaryMaterial = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@role='button' and @aria-label='Primary Material']")
                ));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", primaryMaterial);
        LoggerManager.info("Selecting Engineered Wood under Primary Material");
        WebElement engineeredWood = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("(//div[text()='Engineered Wood'])[1]")
                ));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", engineeredWood);
    }

    public void selectTableTopMaterialEngineeredWood() {
        PopupHandler.closePopupIfPresent(driver);
        LoggerManager.info("Scrolling to Table Top Material");
        WebElement tableTopMaterial = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@role='button' and @aria-label='Table Top Material']")
                ));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);",
                        tableTopMaterial);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();",
                        tableTopMaterial);
        LoggerManager.info("Selecting Engineered Wood under Table Top Material");
        WebElement engineeredWood = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("(//div[text()='Engineered Wood'])[2]")
                ));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();",
                        engineeredWood);
    }

    public int getProductsCountAfterMaterialFilters() {
        PopupHandler.closePopupIfPresent(driver);
        WebElement countElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h1[contains(text(),'Bookshelves')]/following-sibling::span")
                )
        );
        wait.until(driver -> {
            String countText = countElement.getText()
                    .replaceAll("[^0-9]", "");
            return !countText.isEmpty()
                    && Integer.parseInt(countText) < 639;
        });
        String countText = countElement.getText();
        LoggerManager.info(
                "Filtered Products Count : " + countText);
        return Integer.parseInt(
                countText.replaceAll("[^0-9]", ""));
    }

    public void sortByDiscountHighToLow() {
        PopupHandler.closePopupIfPresent(driver);
        LoggerManager.info("Clicking Sort By");
        wait.until(ExpectedConditions.elementToBeClickable(sortBy)).click();
        LoggerManager.info("Selecting Discount High To Low");
        wait.until(ExpectedConditions.elementToBeClickable(discountHighToLow)).click();
        LoggerManager.info("Discount sorting applied");
        wait.until(ExpectedConditions.visibilityOfAllElements(productCards));
    }

    public void loadTwentyProducts() {
        PopupHandler.closePopupIfPresent(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        while (productNames.size() < 20) {
            int currentCount = productNames.size();
            js.executeScript("window.scrollBy(0,1000)");
            wait.until(driver -> productNames.size() > currentCount || productNames.size() >= 20);
        }
        LoggerManager.info("Minimum 20 products loaded");
    }

    public List<String[]> getTopTwentyProductsWithDiscounts() {
        PopupHandler.closePopupIfPresent(driver);
        wait.until(ExpectedConditions.visibilityOfAllElements(productCards));
        List<String[]> productData = new ArrayList<>();
        int count = Math.min(20, productCards.size());
        for (int i = 0; i < count; i++) {
            WebElement card = productCards.get(i);
            try {
                String name = card.findElement(
                                By.xpath(".//h2[contains(@class,'XxwSy')]")).getText();
                String discount = "N/A";
                List<WebElement> discountElements = card.findElements(
                                By.xpath(".//span[contains(text(),'OFF')]"));
                if (!discountElements.isEmpty()) {
                    discount = discountElements.get(0).getText();
                }
                productData.add(new String[]{name, discount});
            } catch (Exception e) {
                LoggerManager.error(
                        "Unable to extract product details");
            }
        }
        LoggerManager.info("Extracted " + productData.size() + " products with discounts");
        return productData;
    }

    public void clickBookshelvesCategory() {
        PopupHandler.closePopupIfPresent(driver);
        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        bookshelvesCategory);
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        bookshelvesCategory));
        bookshelvesCategory.click();
        LoggerManager.info(
                "Clicked Bookshelves category");
    }

    public String getFirstProductImageUrl() {
        wait.until(ExpectedConditions.visibilityOf(firstProductImage));
        return firstProductImage.getAttribute("src");
    }

}