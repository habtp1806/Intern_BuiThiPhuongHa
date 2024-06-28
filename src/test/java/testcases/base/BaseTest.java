package testcases.base;


import base.Config;
import base.DriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;
import org.testng.annotations.*;

import java.net.MalformedURLException;


public class BaseTest {
    protected String email = "nsctyhmy@guerrillamail.com";
    protected String password = "123456789";
    protected String confirmPassword = "123456789";
    protected String pid = "123456789";


    @BeforeMethod
    @Parameters({"browser", "type"})
    public void setUp(@Optional("chrome") String browser, @Optional("local") String type) throws MalformedURLException {
        if (type.equals("grid"))
            DriverManager.initRemoteDriver(browser);
        else {
            DriverManager.initDriver(browser);
        }
    }

    @AfterMethod
    public void tearDown() {
        if (DriverManager.driver != null) {
            DriverManager.driver.quit();
        }
    }
}
