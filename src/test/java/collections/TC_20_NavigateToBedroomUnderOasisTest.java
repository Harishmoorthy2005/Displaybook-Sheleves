package collections;


import base.BaseTest;
import org.furniture.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.ExtentReportManager;
import utils.LoggerManager;

public class TC_20_NavigateToBedroomUnderOasisTest extends BaseTest{
    @Test
    public void verifyTerraBedroomNavigation() {

        LoggerManager.info("Starting TC - Terra Bedroom Navigation");
        ExtentReportManager.getTest().info("Test started");
        HomePage homePage = new HomePage(driver);
        homePage.hoverOnNewArrivals();
        boolean dropdown = homePage.isNewArrivalsDropdownDisplayed();
        Assert.assertTrue(dropdown, "Dropdown not displayed");
        homePage.clickTerraBedroom();
        boolean result = homePage.isTerraBedroomPageDisplayed();
        Assert.assertTrue(result, "Navigation failed");
        ExtentReportManager.getTest().pass("Navigation successful ");

    }
}