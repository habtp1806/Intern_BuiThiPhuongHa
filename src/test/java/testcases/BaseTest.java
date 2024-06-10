package testcases;


import base.Config;
import base.WebDriverConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.MailPage;


public class BaseTest {

    @BeforeTest
    public void setUp() {
        String browserType = Config.getProperty("browser").toLowerCase();
        if (browserType.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            WebDriverConfig.driver = new ChromeDriver();
        } else if (browserType.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            WebDriverConfig.driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }

        WebDriverConfig.driver.manage().window().maximize();

    }

    @AfterTest
    protected void quitBrowser() {
        WebDriverConfig.driver.quit();
    }
}
