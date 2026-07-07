package bookshelves;

import base.BaseTest;
import org.furniture.pages.BookshelvesPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ExtentReportManager;
import utils.LoggerManager;
import utils.PopupHandler;

public class TC_01_BookshelvesSearchTest extends BaseTest {

    @Test
    public void verifyBookshelvesSearch() {

        LoggerManager.info("Starting TC_01");
        ExtentReportManager.getTest().info("Launching Bookshelves search test");
        BookshelvesPage page = new BookshelvesPage(driver);
        PopupHandler.closePopupIfPresent(driver);
        page.searchBookshelves();
        ExtentReportManager.getTest().info("Search action performed");
        boolean result = page.isBookshelvesPageDisplayed();
        ExtentReportManager.getTest().info("Validation result: " + result);
        Assert.assertTrue(result, "Bookshelves page not displayed");
        ExtentReportManager.getTest().pass("Bookshelves page displayed successfully ✅");
    }
}