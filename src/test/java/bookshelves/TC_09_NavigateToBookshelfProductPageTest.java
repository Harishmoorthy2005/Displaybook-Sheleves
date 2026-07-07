package bookshelves;

import base.BaseTest;
import org.furniture.pages.BookshelvesPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ExtentReportManager;
import utils.LoggerManager;

public class TC_09_NavigateToBookshelfProductPageTest extends BaseTest {

    @Test
    public void navigateToBookshelfProductPage() {
        LoggerManager.info("Starting TC_09");
        BookshelvesPage page = new BookshelvesPage(driver);
        page.clickBookshelvesCategory();
        Assert.assertTrue(page.isBookshelvesPageDisplayed(), "Failed to navigate to Bookshelves page");
        ExtentReportManager.getTest().pass("Successfully navigated to Bookshelves page");
        LoggerManager.info("Successfully navigated to Bookshelves page");
    }
}