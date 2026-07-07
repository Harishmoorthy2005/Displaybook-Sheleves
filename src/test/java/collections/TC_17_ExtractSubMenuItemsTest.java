package collections;

import base.BaseTest;
import org.furniture.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.ExtentReportManager;
import utils.LoggerManager;
import utils.ExcelUtils;

public class TC_17_ExtractSubMenuItemsTest extends BaseTest{
    @Test
    public void captureTerraCollectionData() {

        LoggerManager.info("Starting TC - Capture Terra Collection Data");
        ExtentReportManager.getTest().info("Test started");
        HomePage homePage = new HomePage(driver);
        homePage.hoverOnNewArrivals();
        boolean dropdown = homePage.isNewArrivalsDropdownDisplayed();
        Assert.assertTrue(dropdown, "Dropdown not displayed");
        java.util.List<String> items = homePage.getTerraCollectionItems();
        LoggerManager.info("Total items captured: " + items.size());
        ExcelUtils.writeTerraData(items);
        ExtentReportManager.getTest().pass("Terra Collection data captured & written to Excel ✅");
    }
}