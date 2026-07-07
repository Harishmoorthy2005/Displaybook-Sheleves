package collections;

import base.BaseTest;
import org.furniture.pages.TerraBedroomPage;
import org.furniture.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.ExtentReportManager;
import utils.LoggerManager;

public class TC_21_GetNoOfBedroomCollection extends BaseTest {
    @Test
    public void verifyFiltersProductValidation() {
        LoggerManager.info("Starting TC - Filters Product Validation");
        ExtentReportManager.getTest().info("Test started");
        HomePage homePage = new HomePage(driver);
        homePage.hoverOnNewArrivals();
        Assert.assertTrue(homePage.isNewArrivalsDropdownDisplayed(), "New Arrivals dropdown not displayed");
        homePage.clickTerraBedroom();
        Assert.assertTrue(homePage.isTerraBedroomPageDisplayed(), "Terra Bedroom page not displayed");
        TerraBedroomPage page = new TerraBedroomPage(driver);
        page.applyFiltersUsingAllFilters();
        int productCount = page.getProductListSize();
        LoggerManager.info("Product list size: " + productCount);
        ExtentReportManager.getTest().info("Product count from DOM: " + productCount);
        String textCount = page.getProductCountText();
        int uiCount = Integer.parseInt(textCount.replaceAll("[^0-9]", ""));
        LoggerManager.info("Product count from UI: " + uiCount);
        ExtentReportManager.getTest().info("Product count from UI: " + uiCount);
        Assert.assertEquals(productCount, uiCount, "Mismatch between UI product count and actual product list");
        ExtentReportManager.getTest().pass("Validation successful. Product count: " + productCount);
    }
}