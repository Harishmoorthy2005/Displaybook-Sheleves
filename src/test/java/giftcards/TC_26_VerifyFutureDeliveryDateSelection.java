package giftcards;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import org.furniture.pages.GiftCardsPage;
import org.testng.annotations.Test;
import utils.ConfigReader;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import org.testng.Assert;
import utils.ScreenshotUtils;

import java.io.IOException;


public class TC_26_VerifyFutureDeliveryDateSelection extends BaseTest {
    @Test
    public void verifyFutureDeliveryDateSelection() throws IOException {
        ExtentReportManager.createTest("TC_26 - Verify Future Delivery Date Selection");
        GiftCardsPage page = new GiftCardsPage(driver);
        page.clickGiftCards();
        page.switchToGiftCardWindow();
        page.selectAnniversaryCard();
        page.waitForGiftCardFormToLoad();
        String filePath = System.getProperty("user.dir")
                + "/src/test/resources/testdata/testdata.xlsx";
        ExcelUtils.setExcelFile(filePath, "giftcardinput");
        int row = 1;
        page.fillGiftCardForm(
                ExcelUtils.getCellData(row, 0),
                ExcelUtils.getCellData(row, 1),
                ExcelUtils.getCellData(row, 2),
                ExcelUtils.getCellData(row, 3),
                ExcelUtils.getCellData(row, 4),
                ExcelUtils.getCellData(row, 5),
                ExcelUtils.getCellData(row, 6),
                ExcelUtils.getCellData(row, 7),
                ExcelUtils.getCellData(row, 8),
                ExcelUtils.getCellData(row, 9)
        );

        String deliveryDate =ConfigReader.getProperty("deliveryDate");
        page.selectDeliveryDate(deliveryDate);
        ScreenshotUtils.capturePageScreenshot(driver, "TC_26_VerifyFutureDeliveryDateSelection");
        String expectedDate = "20/07/2026";
        Assert.assertEquals(page.getSelectedDeliveryDate(),expectedDate,"Selected delivery date does not match");
        ExtentReportManager.getTest().log(Status.PASS,"Future delivery date selected successfully");
    }
}
