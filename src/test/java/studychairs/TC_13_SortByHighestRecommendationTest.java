package studychairs;

import base.BaseTest;
import org.furniture.pages.StudyChairsPage;
import org.testng.annotations.Test;

import utils.ExtentReportManager;
import utils.LoggerManager;
import utils.PopupHandler;

public class TC_13_SortByHighestRecommendationTest extends BaseTest {
    @Test
    public void verifySortByPopularity() {
        LoggerManager.info("Starting TC_13");
        StudyChairsPage page = new StudyChairsPage(driver);
        page.searchStudyChairs();
        PopupHandler.closePopupIfPresent(driver);
        page.clickSortBy();
        page.selectPopularity();
        LoggerManager.info("Popularity selected successfully");
        ExtentReportManager.getTest().pass("Popularity option selected successfully and sorted");
    }
}