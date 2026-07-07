package org.furniture.pages;

import utils.PopupHandler;
import utils.LoggerManager;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StudyChairsPage {

    WebDriver driver;
    WebDriverWait wait;

    public StudyChairsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    // Search Box
    @FindBy(id = "searchInput")
    WebElement searchBox;
    // Study Chairs Page Title
    @FindBy(xpath = "//h1[contains(text(),'Study Chairs')]")
    WebElement title;
    // Sort By Dropdown
    @FindBy(xpath = "//span[contains(text(),'Sort By')]")
    WebElement sortByDropdown;
    // Product Names
    @FindBy(xpath = "//h2[contains(@class,'XxwSy')]")
    List<WebElement> productNames;
    @FindBy(xpath = "//div[contains(text(),'₹')]")
    List<WebElement> productPrices;
    @FindBy(xpath="//span[contains(text(),'OFF')]")
    List<WebElement> discountPercentages;
    // Search Study Chairs
    public void searchStudyChairs() {
        LoggerManager.info("Waiting for search box");
        PopupHandler.closePopupIfPresent(driver);
        wait.until(ExpectedConditions.visibilityOf(searchBox));
        LoggerManager.info("Clicking search box");
        searchBox.click();
        LoggerManager.info("Typing Study Chairs");
        searchBox.clear();
        searchBox.sendKeys("Study Chairs");
        LoggerManager.info("Pressing Enter");
        searchBox.sendKeys(Keys.ENTER);
        LoggerManager.info("Waiting for Study Chairs page");
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h1[contains(text(),'Study Chairs')]")),
                ExpectedConditions.titleContains("Study Chairs")
        ));

    }
    // Verify Study Chairs Page Displayed
    public boolean isStudyChairsPageDisplayed() {
        return !driver.findElements(
                        By.xpath("//h1[contains(text(),'Study Chairs')]"))
                .isEmpty();
    }
    // Get Product Count
    public int getStudyChairsCount() {
        PopupHandler.closePopupIfPresent(driver);
        WebElement countElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("span.PpQnM")
                )
        );
        wait.until(driver -> {
            String text = countElement.getText()
                    .replaceAll("[^0-9]", "");
            if(text.isEmpty()) {
                return false;
            }
            return Integer.parseInt(text) > 0;
        });
        String countText = countElement.getText();
        LoggerManager.info(
                "Study Chairs Count : " + countText);
        return Integer.parseInt(
                countText.replaceAll("[^0-9]", "")
        );
    }
    // Click Sort By
    public void clickSortBy() {
        PopupHandler.closePopupIfPresent(driver);
        LoggerManager.info("Clicking Sort By");
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        sortByDropdown)
        ).click();
    }
    // Select Popularity
    public void selectPopularity() {

        PopupHandler.closePopupIfPresent(driver);
        LoggerManager.info("Selecting Popularity");
        WebElement popularity = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[contains(text(),'Popularity')]")
                )
        );
        popularity.click();
        LoggerManager.info(
                "Popularity selected successfully");
    }
    // Top 3 Products
    public List<String> getTopThreeProducts() {
        PopupHandler.closePopupIfPresent(driver);
        wait.until(ExpectedConditions.visibilityOfAllElements(productNames));
        return productNames.stream()
                .filter(WebElement::isDisplayed)
                .limit(3)
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
    public void selectDiscountHighToLow() {
        LoggerManager.info("Selecting Discount High To Low");
        WebElement discount = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Discount High to Low')]")));
        discount.click();
        LoggerManager.info("Discount High To Low selected successfully");
    }
    public void printTopFiveDiscountProducts() {
        PopupHandler.closePopupIfPresent(driver);
        wait.until(ExpectedConditions.visibilityOfAllElements(productNames));
        int count = Math.min(5, productNames.size());
        for(int i = 0; i < count; i++) {
            String name = productNames.get(i).getText();
            String price = productPrices.get(i).getText();
            String discount = discountPercentages.get(i).getText();
            LoggerManager.info(
                    "Product " + (i + 1)
                            + " | Name : " + name
                            + " | Price : " + price
                            + " | Discount : " + discount
            );
        }
    }
    public int getTopFiveProductsCount() {
        return Math.min(5, productNames.size());
    }
}