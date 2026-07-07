package studychairs;

import base.BaseTest;
import org.furniture.pages.StudyChairsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.LoggerManager;
import utils.PopupHandler;

import java.util.List;

public class TC_14_GetTopThreeStudyChairsTest extends BaseTest {

    @Test
    public void verifyTopThreeProducts() {
        LoggerManager.info("Starting TC_14");
        StudyChairsPage page = new StudyChairsPage(driver);
        page.searchStudyChairs();
        PopupHandler.closePopupIfPresent(driver);
        page.clickSortBy();
        page.selectPopularity();
        List<String> topProducts = page.getTopThreeProducts();
        ExcelUtils.writeTopThreeStudyChairs(topProducts);
        LoggerManager.info("Top 3 Products:");
        for (String product : topProducts) {
            LoggerManager.info(product);
            ExtentReportManager.getTest().info(product);
        }
        Assert.assertEquals(topProducts.size(), 3, "Top 3 products are not displayed");
        ExtentReportManager.getTest().pass("Top 3 products retrieved successfully");
    }
}