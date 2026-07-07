package collections;

import base.BaseTest;
import org.furniture.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.ExtentReportManager;
import utils.LoggerManager;

public class TC_16_MouseHoverOnNewArrivalsTest extends BaseTest {

    @Test
    public void verifyNewArrivalsHover() {
        LoggerManager.info("Starting TC - New Arrivals Hover");
        ExtentReportManager.getTest().info("Test started");
        HomePage homePage = new HomePage(driver);
        homePage.hoverOnNewArrivals();
        boolean result = homePage.isNewArrivalsDropdownDisplayed();
        ExtentReportManager.getTest().info("Validation result: " + result);
        Assert.assertTrue(result, "New Arrivals dropdown not displayed");
        ExtentReportManager.getTest().pass("Dropdown displayed successfully");
    }

}