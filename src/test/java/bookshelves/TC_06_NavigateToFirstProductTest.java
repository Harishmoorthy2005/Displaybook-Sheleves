package bookshelves;

import base.BaseTest;
import org.furniture.pages.BookshelvesPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ExtentReportManager;
import utils.LoggerManager;

public class TC_06_NavigateToFirstProductTest extends BaseTest {

    @Test
    public void verifyNavigationToFirstProduct() {

        LoggerManager.info("Starting TC_06");
        BookshelvesPage page = new BookshelvesPage(driver);
        page.searchBookshelves();
        page.clickFirstProduct();
        page.switchToProductTab();
        Assert.assertEquals(driver.getWindowHandles().size(), 2, "Product page did not open in new tab");
        ExtentReportManager.getTest().pass("Successfully navigated to first product");
        LoggerManager.info("Successfully navigated to first product");
    }
}