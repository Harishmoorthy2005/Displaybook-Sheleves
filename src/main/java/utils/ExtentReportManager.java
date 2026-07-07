package utils;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static ExtentReports getReportInstance() {
        if (extent == null) {
            String path = System.getProperty("user.dir") + "/reports/ExtentReport.html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(path);
            reporter.config().setReportName("Urban Ladder Automation Report");
            reporter.config().setDocumentTitle("Test Execution Report");
            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester", "Your Name");
            extent.setSystemInfo("Project", "Urban Ladder");
        }
        return extent;
    }

    public static void createTest(String testName) {
        test.set(getReportInstance().createTest(testName));
    }

    public static ExtentTest getTest() {
        return test.get();
    }
}
