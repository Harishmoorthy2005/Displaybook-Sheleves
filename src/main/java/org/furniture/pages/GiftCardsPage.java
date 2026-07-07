package org.furniture.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import utils.LoggerManager;
import utils.PopupHandler;
import java.time.Duration;

public class GiftCardsPage {
    WebDriver driver;
    WebDriverWait wait;
    public GiftCardsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }
    // Navigation
    @FindBy(xpath = "//a[text()='Gift Cards']")
    WebElement giftCardsLink;

    // Card
    @FindBy(xpath = "(//*[@id='design-theme']//img)[3]")
    WebElement anniversaryCard;

    // Form
    @FindBy(id = "denomination")
    WebElement amountField;

    @FindBy(id = "quantity")
    WebElement quantityField;

    // Sender
    @FindBy(xpath = "//div[@id='sender-details']//input[@id='firstname']")
    public WebElement senderFirstName;

    @FindBy(xpath = "//div[@id='sender-details']//input[@id='lastname']")
    WebElement senderLastName;

    @FindBy(xpath = "//div[@id='sender-details']//input[@id='email']")
    WebElement senderEmail;
    @FindBy(xpath = "//div[@id='sender-details']//input[@id='telephone']")
    WebElement senderMobile;
    // Receiver
    @FindBy(xpath = "//div[@id='receiver-details']//input[@id='firstname']")
    WebElement receiverFirstName;
    @FindBy(xpath = "//div[@id='receiver-details']//input[@id='lastname']")
    WebElement receiverLastName;
    @FindBy(xpath = "//div[@id='receiver-details']//input[@id='email']")
    WebElement receiverEmail;
    // Message
    @FindBy(id = "giftMessage")
    WebElement messageBox;
    // Validation
    By senderEmailError =
            By.xpath("//div[contains(@class,'invalid-address')]");
    // Preview
    @FindBy(xpath = "//button[contains(@class,'preview-button')]")
    WebElement previewButton;
    @FindBy(xpath = "//div[contains(@class,'modal-content')]")
    WebElement voucherPopup;
    // Delivery Date
    @FindBy(xpath = "//button[contains(.,'Send later')]")
    WebElement sendLaterRadio;
    @FindBy(css = "span.gift-date")
    WebElement selectedDeliveryDateText;
    // Navigation
    public void clickGiftCards() {
        PopupHandler.closePopupIfPresent(driver);
        LoggerManager.info("Clicking Gift Cards link");
        wait.until(ExpectedConditions.elementToBeClickable(giftCardsLink)).click();
    }

    public void switchToGiftCardWindow() {
        PopupHandler.closePopupIfPresent(driver);
        String parentWindow = driver.getWindowHandle();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String window : driver.getWindowHandles()) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
        wait.until(ExpectedConditions.urlContains("woohoo"));
        LoggerManager.info("Switched to Gift Card window");
    }

    public boolean isGiftCardPageOpened() {
        return driver.getCurrentUrl().contains("woohoo");
    }

    // Card
    public void selectAnniversaryCard() {
        wait.until(ExpectedConditions.visibilityOf(anniversaryCard));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                anniversaryCard
        );
        wait.until(ExpectedConditions.elementToBeClickable(anniversaryCard));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                anniversaryCard
        );
        LoggerManager.info("Anniversary card selected");
    }

    public boolean isAnniversaryCardSelected() {
        try {
            wait.until(ExpectedConditions.visibilityOf(anniversaryCard));
            return anniversaryCard.getAttribute("class")
                    .contains("border-secondary");
        } catch (Exception e) {
            return false;
        }
    }
    // Form
    public void waitForGiftCardFormToLoad() {
        wait.until(ExpectedConditions.visibilityOf(amountField));
        LoggerManager.info("Gift card form loaded");
    }

    public void fillGiftCardForm(String amount,
                                 String quantity,
                                 String senderFName,
                                 String senderLName,
                                 String senderEmailVal,
                                 String senderMobileVal,
                                 String receiverFName,
                                 String receiverLName,
                                 String receiverEmailVal,
                                 String message) {
        PopupHandler.closePopupIfPresent(driver);
        LoggerManager.info("Filling Gift Card form");
        amountField.clear();
        amountField.sendKeys(amount);
        quantityField.clear();
        quantityField.sendKeys(quantity);
        senderFirstName.clear();
        senderFirstName.sendKeys(senderFName);
        senderLastName.clear();
        senderLastName.sendKeys(senderLName);
        senderEmail.clear();
        senderEmail.sendKeys(senderEmailVal);
        senderMobile.clear();
        senderMobile.sendKeys(senderMobileVal);
        receiverFirstName.clear();
        receiverFirstName.sendKeys(receiverFName);
        receiverLastName.clear();
        receiverLastName.sendKeys(receiverLName);
        receiverEmail.clear();
        receiverEmail.sendKeys(receiverEmailVal);
        messageBox.clear();
        messageBox.sendKeys(message);
        wait.until(ExpectedConditions.attributeToBe(
                senderFirstName, "value", senderFName));
        wait.until(ExpectedConditions.attributeToBe(
                senderEmail, "value", senderEmailVal));
        LoggerManager.info("Gift Card form filled successfully");
    }
    // Validation
    public void triggerEmailValidation() {
        wait.until(ExpectedConditions.visibilityOf(senderEmail));
        senderEmail.sendKeys(Keys.TAB);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(senderEmailError)
        );
        LoggerManager.info("Email validation triggered");
    }

    public String getSenderEmailErrorMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(senderEmailError)
        ).getText().trim();
    }

    public void clickSenderFirstName() {
        wait.until(ExpectedConditions.visibilityOf(senderFirstName));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", senderFirstName);
        LoggerManager.info("Clicked Sender First Name field");
    }
    // Getters
    public String getAmountValue() {
        return amountField.getAttribute("value");
    }

    public String getQuantityValue() {
        return quantityField.getAttribute("value");
    }

    public String getSenderFirstNameValue() {
        return senderFirstName.getAttribute("value");
    }

    public String getSenderLastNameValue() {
        return senderLastName.getAttribute("value");
    }

    public String getSenderEmailValue() {
        return senderEmail.getAttribute("value");
    }

    public String getSenderMobileValue() {
        return senderMobile.getAttribute("value");
    }

    public String getReceiverFirstNameValue() {
        return receiverFirstName.getAttribute("value");
    }

    public String getReceiverLastNameValue() {
        return receiverLastName.getAttribute("value");
    }

    public String getReceiverEmailValue() {
        return receiverEmail.getAttribute("value");
    }

    // Delivery Date
    public void selectDeliveryDate(String deliveryDate) {
        wait.until(
                ExpectedConditions.elementToBeClickable(sendLaterRadio)
        ).click();
        LoggerManager.info("Send Later selected");
        By dateLocator = By.xpath("//span[@aria-label='" + deliveryDate + "']");
        wait.until(
                ExpectedConditions.elementToBeClickable(dateLocator)
        ).click();
        wait.until(
                ExpectedConditions.visibilityOf(selectedDeliveryDateText)
        );
        LoggerManager.info("Delivery Date Selected : " + deliveryDate);
    }

    public String getSelectedDeliveryDate() {
        return wait.until(
                ExpectedConditions.visibilityOf(selectedDeliveryDateText)
        ).getText().trim();
    }

    public boolean isDeliveryDateSelected(String deliveryDate) {
        By selectedDate = By.xpath(
                "//span[contains(@class,'selected') and @aria-label='"+ deliveryDate + "']");
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(selectedDate)
        ).isDisplayed();
    }
}