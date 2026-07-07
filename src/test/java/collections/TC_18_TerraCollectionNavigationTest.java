package collections;

import base.BaseTest;
import org.furniture.pages.HomePage;
import org.furniture.pages.TerraCollectionPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.ExtentReportManager;
import utils.LoggerManager;

public class TC_18_TerraCollectionNavigationTest extends BaseTest{
    @Test
    public void verifyTerraCollectionNavigation() {
        LoggerManager.info("Starting TC_18 - Terra Collection Navigation");
        ExtentReportManager.getTest().info("Launching Terra Collection navigation test");
        HomePage homePage = new HomePage(driver);
        homePage.hoverOnNewArrivals();
        homePage.clickTerraCollection();
        Assert.assertTrue(homePage.isTerraCollectionPageDisplayed(),
                "Terra Collection landing page is not displayed");
        TerraCollectionPage terraCollectionPage = new TerraCollectionPage(driver);
        terraCollectionPage.scrollToDiscoverAllTerraProducts();
        terraCollectionPage.clickDiscoverAllTerraProducts();
        terraCollectionPage.switchToNewTab();
        Assert.assertTrue(
                terraCollectionPage.isTerraProductsPageDisplayed(),
                "Terra Collection products page is not displayed");
        ExtentReportManager.getTest().pass(
                "Terra Collection navigation verified successfully");
    }
}
