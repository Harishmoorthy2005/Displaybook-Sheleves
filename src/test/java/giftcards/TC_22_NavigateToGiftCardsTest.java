package giftcards;

import base.BaseTest;
import org.furniture.pages.GiftCardsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import utils.LoggerManager;
import utils.ExtentReportManager;

public class TC_22_NavigateToGiftCardsTest extends BaseTest {

    @Test
    public void navigateToGiftCards() {
        ExtentReportManager.createTest("TC_21 - Navigate to Gift Cards");
        LoggerManager.info("Starting TC_21");
        ExtentReportManager.getTest().log(Status.INFO, "Test started");
        GiftCardsPage page = new GiftCardsPage(driver);
        page.clickGiftCards();
        ExtentReportManager.getTest().log(Status.INFO, "Clicked Gift Cards");
        page.switchToGiftCardWindow();
        ExtentReportManager.getTest().log(Status.INFO, "Switched to Gift Card page");
        boolean result = page.isGiftCardPageOpened();
        Assert.assertTrue(result, "Gift card page not opened");
        ExtentReportManager.getTest().log(Status.PASS, "Navigated to Gift Card page successfully");
    }
}