package giftcards;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import org.furniture.pages.GiftCardsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.LoggerManager;

public class TC_24_CaptureInvalidEmailValidationTest extends BaseTest {

    @Test
    public void validateInvalidSenderEmail() throws Exception {
        ExtentReportManager.createTest("TC_24 - Validate Invalid Sender Email");
        LoggerManager.info("Starting TC_24");
        try {
            GiftCardsPage page = new GiftCardsPage(driver);
            page.clickGiftCards();
            page.switchToGiftCardWindow();
            page.selectAnniversaryCard();
            page.waitForGiftCardFormToLoad();
            String filePath =
                    ConfigReader.getProperty("excel.input.path");
            String sheetName =
                    ConfigReader.getProperty("giftcard.sheet");
            ExcelUtils.setExcelFile(filePath, sheetName);
            int row = 2;
            String amount = ExcelUtils.getCellData(row, 0);
            String quantity = ExcelUtils.getCellData(row, 1);
            String senderFName = ExcelUtils.getCellData(row, 2);
            String senderLName = ExcelUtils.getCellData(row, 3);
            String senderEmail = ExcelUtils.getCellData(row, 4);
            String senderMobile = ExcelUtils.getCellData(row, 5);
            String receiverFName = ExcelUtils.getCellData(row, 6);
            String receiverLName = ExcelUtils.getCellData(row, 7);
            String receiverEmail = ExcelUtils.getCellData(row, 8);
            String message = ExcelUtils.getCellData(row, 9);

            LoggerManager.info("Excel data fetched successfully");
            page.fillGiftCardForm(
                    amount,
                    quantity,
                    senderFName,
                    senderLName,
                    senderEmail,
                    senderMobile,
                    receiverFName,
                    receiverLName,
                    receiverEmail,
                    message
            );
            page.triggerEmailValidation();
            String actualError = page.getSenderEmailErrorMessage();
            LoggerManager.info("Actual Validation Message : " + actualError);
            page.clickSenderFirstName();
            Assert.assertEquals(actualError,"Enter valid Email ID.",
                    "Validation message mismatch");
            LoggerManager.info("Invalid Email Validation Verified Successfully");
            ExtentReportManager.getTest().log(Status.PASS,"Validation message verified successfully : "+ actualError);

        } catch (Exception e) {
            LoggerManager.error("Test failed: " + e.getMessage());
            ExtentReportManager.getTest().log(
                    Status.FAIL,
                    e.getMessage()
            );
            throw e;
        }
    }
}