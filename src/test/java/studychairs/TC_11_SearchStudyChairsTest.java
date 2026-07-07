package studychairs;

import base.BaseTest;
import org.furniture.pages.StudyChairsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.ExtentReportManager;
import utils.LoggerManager;

public class TC_11_SearchStudyChairsTest extends BaseTest {

    @Test
    public void verifyStudyChairsSearch() {
        LoggerManager.info("Starting TC_11");
        ExtentReportManager.getTest().info("Launching Study Chairs search test");
        StudyChairsPage page = new StudyChairsPage(driver);
        page.searchStudyChairs();
        boolean result = page.isStudyChairsPageDisplayed();
        Assert.assertTrue(result, "Study Chairs page not displayed");
        ExtentReportManager.getTest().pass("Study Chairs page displayed successfully ✅");
    }
}