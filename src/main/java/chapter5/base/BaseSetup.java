package chapter5.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class BaseSetup {
    public WebDriver driver;
    public String raiwayUrl = "http://saferailway.somee.com/";
    public String tempmailUrl = "https://www.guerrillamail.com/inbox";


}
