package base;

import config.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ConfigReader;
import utils.ExtentReportManager;
import utils.LoggerManager;
import utils.PopupHandler;
import utils.ScreenshotUtils;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseTest {
    protected WebDriver driver;

    @BeforeSuite
    public void setupReport() {
        ExtentReportManager.getReportInstance();
    }

    @BeforeMethod
    public void setUp(Method method) {
        LoggerManager.info("===== Test Started =====");
        ExtentReportManager.createTest(method.getName());
        driver = DriverFactory.initDriver();
        String url = ConfigReader.getProperty("url");
        driver.get(url);
        LoggerManager.info("Navigated to: " + url);
        PopupHandler.closePopupIfPresent(driver);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            ExtentReportManager.getTest().pass("Test Passed");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            try {
                ScreenshotUtils.capturePageScreenshot(driver, result.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
            ExtentReportManager.getTest().fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            ExtentReportManager.getTest().skip("Test Skipped");
        }
        DriverFactory.quitDriver();
        LoggerManager.info("===== Test Finished =====");
    }

    @AfterSuite
    public void flushReport() {
        ExtentReportManager.getReportInstance().flush();
    }
}