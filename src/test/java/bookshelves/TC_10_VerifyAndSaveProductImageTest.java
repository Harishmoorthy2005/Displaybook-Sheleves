package bookshelves;

import base.BaseTest;
import org.furniture.pages.BookshelvesPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ExtentReportManager;
import utils.LoggerManager;
import utils.ExcelUtils;

public class TC_10_VerifyAndSaveProductImageTest extends BaseTest {

    @Test
    public void verifyAndSaveProductImage() {
        LoggerManager.info("Starting TC_10");
        BookshelvesPage page = new BookshelvesPage(driver);
        page.searchBookshelves();
        String imageUrl = page.getFirstProductImageUrl();
        ExcelUtils.writeProductImageUrl(imageUrl);
        Assert.assertFalse(imageUrl.isEmpty(), "Product image URL is empty");
        LoggerManager.info("Product Image URL : " + imageUrl);
        ExtentReportManager.getTest().info("Product Image URL : " + imageUrl);
        ExtentReportManager.getTest().pass("Product image verified successfully");
    }
}