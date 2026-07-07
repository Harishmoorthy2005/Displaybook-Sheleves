package giftcards;

import base.BaseTest;
import org.furniture.pages.GiftCardsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import utils.LoggerManager;
import utils.ExtentReportManager;
import utils.ScreenshotUtils;

import java.io.IOException;

public class TC_25_SelectAnniversaryGiftCardTest extends BaseTest {

    @Test
    public void selectAnniversaryCard() throws IOException {

        ExtentReportManager.createTest("TC_22 - Select Anniversary Gift Card");
        LoggerManager.info("Starting TC_22");
        ExtentReportManager.getTest().log(Status.INFO, "Test started");
        GiftCardsPage page = new GiftCardsPage(driver);
        page.clickGiftCards();
        page.switchToGiftCardWindow();
        ExtentReportManager.getTest().log(Status.INFO, "Navigated to Gift Card page");
        page.selectAnniversaryCard();
        ScreenshotUtils.capturePageScreenshot(driver, "TC_25_SelectAnniversaryGiftCardTest");

        ExtentReportManager.getTest().log(Status.INFO, "Clicked Anniversary card");
        boolean result = page.isAnniversaryCardSelected();
        Assert.assertTrue(result, "Anniversary card not selected");
        ExtentReportManager.getTest().log(Status.PASS, "Clicked on Happy Anniversary card");
    }
}