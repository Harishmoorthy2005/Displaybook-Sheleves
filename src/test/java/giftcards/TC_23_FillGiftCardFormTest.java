package giftcards;
import org.testng.Assert;
import base.BaseTest;
import org.furniture.pages.GiftCardsPage;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import utils.ExcelUtils;
import utils.LoggerManager;
import utils.ExtentReportManager;

public class TC_23_FillGiftCardFormTest extends BaseTest {

    @Test
    public void fillGiftCardFormTest() {

        ExtentReportManager.createTest("TC_23 - Fill Gift Card Form");
        LoggerManager.info("Starting TC_23");
        ExtentReportManager.getTest().log(Status.INFO, "Test started");
        try {
            GiftCardsPage page = new GiftCardsPage(driver);
            page.clickGiftCards();
            page.switchToGiftCardWindow();
            page.selectAnniversaryCard();
            page.waitForGiftCardFormToLoad();
            String filePath = System.getProperty("user.dir") + "/src/test/resources/testdata/testdata.xlsx";
            String sheetName = "giftcardinput";
            ExcelUtils.setExcelFile(filePath, sheetName);
            LoggerManager.info("Excel file loaded");
            String amount = ExcelUtils.getCellData(1, 0);
            String quantity = ExcelUtils.getCellData(1, 1);
            String senderFName = ExcelUtils.getCellData(1, 2);
            String senderLName = ExcelUtils.getCellData(1, 3);
            String senderEmail = ExcelUtils.getCellData(1, 4);
            String senderMobile = ExcelUtils.getCellData(1, 5);
            String receiverFName = ExcelUtils.getCellData(1, 6);
            String receiverLName = ExcelUtils.getCellData(1, 7);
            String receiverEmail = ExcelUtils.getCellData(1, 8);
            String message = ExcelUtils.getCellData(1, 9);
            LoggerManager.info("Excel data fetched successfully");
            page.fillGiftCardForm(amount, quantity, senderFName, senderLName, senderEmail, senderMobile, receiverFName, receiverLName, receiverEmail, message);
            Assert.assertEquals(page.getAmountValue(), amount, "Amount mismatch");
            Assert.assertEquals(page.getQuantityValue(), quantity, "Quantity mismatch");
            Assert.assertEquals(page.getSenderFirstNameValue(), senderFName, "Sender First Name mismatch");
            Assert.assertEquals(page.getSenderLastNameValue(), senderLName, "Sender Last Name mismatch");
            Assert.assertEquals(page.getSenderEmailValue(), senderEmail, "Sender Email mismatch");
            Assert.assertEquals(page.getSenderMobileValue(), senderMobile, "Sender Mobile mismatch");
            Assert.assertEquals(page.getReceiverFirstNameValue(), receiverFName, "Receiver First Name mismatch");
            Assert.assertEquals(page.getReceiverLastNameValue(), receiverLName, "Receiver Last Name mismatch");
            Assert.assertEquals(page.getReceiverEmailValue(), receiverEmail, "Receiver Email mismatch");
            ExtentReportManager.getTest().log(Status.PASS, "Gift card form filled successfully");
        } catch (Exception e) {
            LoggerManager.error("Test failed: " + e.getMessage());
            ExtentReportManager.getTest().log(Status.FAIL, e.getMessage());
            throw e;
        }
    }
}