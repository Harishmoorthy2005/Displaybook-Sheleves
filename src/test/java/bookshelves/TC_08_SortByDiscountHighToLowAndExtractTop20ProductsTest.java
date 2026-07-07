package bookshelves;

import base.BaseTest;
import org.furniture.pages.BookshelvesPage;
import org.testng.annotations.Test;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.LoggerManager;

import java.util.List;

public class TC_08_SortByDiscountHighToLowAndExtractTop20ProductsTest
        extends BaseTest {

    @Test
    public void sortByDiscountAndExtractTop20Products() {

        LoggerManager.info("Starting TC_08 - Discount High To Low Extraction");
        BookshelvesPage page = new BookshelvesPage(driver);
        ExtentReportManager.getTest().info("Searching Bookshelves");
        page.searchBookshelves();
        ExtentReportManager.getTest().info("Sorting by Discount High To Low");
        page.sortByDiscountHighToLow();
        page.loadTwentyProducts();
        List<String[]> productData = page.getTopTwentyProductsWithDiscounts();
        LoggerManager.info("Total products extracted : " + productData.size());
        ExcelUtils.writeDiscountData(productData);
        ExtentReportManager.getTest().pass("Top 20 products and discounts exported to Excel");
    }
}