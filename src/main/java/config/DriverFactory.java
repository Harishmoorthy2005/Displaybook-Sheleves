package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import utils.ConfigReader;
import utils.LoggerManager;
import java.time.Duration;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver initDriver() {
        String browser = ConfigReader.getProperty("browser");
        boolean maximize = Boolean.parseBoolean(ConfigReader.getProperty("maximize"));
        LoggerManager.info("Initializing browser : " + browser);

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--disable-infobars");
                if (Boolean.parseBoolean(ConfigReader.getProperty("headless"))) {
                    chromeOptions.addArguments("--headless=new");
                }
                driver = new ChromeDriver(chromeOptions);
                LoggerManager.info("Chrome browser launched");
                break;

            case "edge":
                driver = new EdgeDriver();
                LoggerManager.info("Edge browser launched");
                break;

            default:
                throw new RuntimeException("Unsupported Browser : " + browser);
        }

        if (maximize) {
            driver.manage().window().maximize();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("implicitWait"))));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("pageLoadTimeout"))));
        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            LoggerManager.info("Browser closed successfully");
        }
    }
}