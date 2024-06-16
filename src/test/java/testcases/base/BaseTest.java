package testcases.base;


import base.Config;
import base.DriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


public class BaseTest {
    protected String email = "dqzvyoml@guerrillamail.com";
    protected String password = "123456789";
    protected String pid = "123456789";

    @BeforeTest
    public void setUp() {
        String browserType = Config.getProperty("browser").toLowerCase();
        if (browserType.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            DriverManager.driver = new ChromeDriver();
        } else if (browserType.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            DriverManager.driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }

        DriverManager.driver.manage().window().maximize();

    }

    @AfterTest
    protected void quitBrowser() {
        DriverManager.driver.quit();
    }
}
