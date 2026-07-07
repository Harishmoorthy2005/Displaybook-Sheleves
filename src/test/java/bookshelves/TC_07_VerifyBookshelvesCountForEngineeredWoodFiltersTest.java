package bookshelves;

import base.BaseTest;
import org.furniture.pages.BookshelvesPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ExtentReportManager;
import utils.LoggerManager;

public class TC_07_VerifyBookshelvesCountForEngineeredWoodFiltersTest extends BaseTest {

    @Test
    public void filterByEngineeredWoodMaterialAndRetrieveProductCount() {
        LoggerManager.info("Starting TC_07 - Filter by Engineered Wood Materials");
        BookshelvesPage page = new BookshelvesPage(driver);
        ExtentReportManager.getTest().info("Searching Bookshelves");
        page.searchBookshelves();
        LoggerManager.info("Bookshelves searched successfully");
        ExtentReportManager.getTest().info("Opening All Filters");
        page.openFilters();
        LoggerManager.info("All Filters opened");
        ExtentReportManager.getTest().info("Selecting Primary Material as Engineered Wood");
        page.selectPrimaryMaterialEngineeredWood();
        LoggerManager.info("Primary Material selected as Engineered Wood");
        ExtentReportManager.getTest().info("Selecting Table Top Material as Engineered Wood");
        page.selectTableTopMaterialEngineeredWood();
        LoggerManager.info("Table Top Material selected as Engineered Wood");
        ExtentReportManager.getTest().info("Applying selected filters");
        page.applyFilters();
        LoggerManager.info("Filters applied successfully");
        int productCount = page.getProductsCountAfterMaterialFilters();
        LoggerManager.info("Number of products displayed after applying filters: " + productCount);
        ExtentReportManager.getTest().pass("Product count retrieved successfully: " + productCount);
        Assert.assertTrue(productCount > 0, "No products found after applying filters");
        ExtentReportManager.getTest().pass("Verified product count is greater than zero");
    }

}