package studychairs;

import base.BaseTest;
import org.furniture.pages.StudyChairsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.ExtentReportManager;
import utils.LoggerManager;

public class TC_12_GetNoOfStudyChairs extends BaseTest {
    @Test
    public void verifyStudyChairsCount() {
        LoggerManager.info("Starting TC_12");
        StudyChairsPage page = new StudyChairsPage(driver);
        page.searchStudyChairs();
        int count = page.getStudyChairsCount();
        LoggerManager.info("Total Study Chairs Found : " + count);
        Assert.assertTrue(count > 0, "No Study Chairs found");
        ExtentReportManager.getTest().pass("Study Chairs count fetched successfully ✅");
    }
}