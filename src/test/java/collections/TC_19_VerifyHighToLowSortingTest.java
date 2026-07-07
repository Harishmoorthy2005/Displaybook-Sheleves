package collections;

import base.BaseTest;
import org.furniture.pages.HomePage;
import org.furniture.pages.TerraCollectionPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.ExtentReportManager;
import utils.LoggerManager;
import utils.ScreenshotUtils;

import java.io.IOException;

public class TC_19_VerifyHighToLowSortingTest extends BaseTest {
    @Test
    public void verifyTerraCollectionPriceHighToLowSorting() throws IOException {
        LoggerManager.info("Starting TC_19 - Verify Terra Collection Price High To Low Sorting");
        ExtentReportManager.getTest().info("Test started");
        HomePage homePage = new HomePage(driver);
        homePage.hoverOnNewArrivals();
        homePage.clickTerraCollection();
        Assert.assertTrue(homePage.isTerraCollectionPageDisplayed(),
                "Terra Collection landing page is not displayed");
        TerraCollectionPage terraPage = new TerraCollectionPage(driver);
        terraPage.scrollToDiscoverAllTerraProducts();
        terraPage.clickDiscoverAllTerraProducts();
        terraPage.switchToNewTab();
        Assert.assertTrue(terraPage.isTerraProductsPageDisplayed(),
                "Terra Collection products page is not displayed");
        terraPage.openAllFilters();
        terraPage.expandSort();
        terraPage.selectPriceHighToLow();
        terraPage.closeFilterPanel();
        Assert.assertTrue(driver.getCurrentUrl().contains("sortOn=price_dsc"),
                "URL does not indicate Price High To Low sorting");
        ScreenshotUtils.capturePageScreenshot(driver, "TC_19_VerifySorting");
        ExtentReportManager.getTest().pass("Price High To Low sorting verified successfully");
    }
}
