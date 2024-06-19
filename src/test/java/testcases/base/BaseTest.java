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
        DriverManager.initDriver();
    }

    @AfterTest
    public void quitBrowser() {
        DriverManager.driver.quit();
    }
}
