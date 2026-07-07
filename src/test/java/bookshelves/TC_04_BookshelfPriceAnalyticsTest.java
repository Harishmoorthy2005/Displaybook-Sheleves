package bookshelves;

import base.BaseTest;
import org.furniture.pages.BookshelvesPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ExtentReportManager;
import utils.LoggerManager;

import java.util.DoubleSummaryStatistics;
import java.util.List;

public class TC_04_BookshelfPriceAnalyticsTest extends BaseTest {

    @Test
    public void verifyBookshelfPriceAnalytics() {

        LoggerManager.info("Starting TC_04 - Bookshelf Price Analytics");
        BookshelvesPage page = new BookshelvesPage(driver);
        page.searchBookshelves();
        page.applyPriceFilter();
        int totalProducts = page.getFilteredProductsCount();
        List<Double> prices = page.getFirstTwentyBookshelfPrices();
        Assert.assertFalse(prices.isEmpty(), "No bookshelf prices found");
        DoubleSummaryStatistics stats = prices.stream().mapToDouble(Double::doubleValue).summaryStatistics();
        LoggerManager.info("Total Products After Filter : " + totalProducts);
        LoggerManager.info("First 20 Products Analysis");
        LoggerManager.info("Lowest Price : ₹" + stats.getMin());
        LoggerManager.info("Highest Price : ₹" + stats.getMax());
        LoggerManager.info("Average Price : ₹" + String.format("%.2f", stats.getAverage()));
        ExtentReportManager.getTest().info("Total Products After Filter : " + totalProducts);
        ExtentReportManager.getTest().info("Products Analysed : " + prices.size());
        ExtentReportManager.getTest().info("Lowest Price : ₹" + stats.getMin());
        ExtentReportManager.getTest().info("Highest Price : ₹" + stats.getMax());
        ExtentReportManager.getTest().info("Average Price : ₹" + String.format("%.2f", stats.getAverage()));
        //ExtentReportManager.getTest().pass("Price analytics generated successfully for first 20 bookshelves");
    }
}