package testcases.Chapter;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Chapter2Test {
    @Test
    public static void ChromeTest() {
        WebDriverManager.chromedriver().setup();
        WebDriver chromeDriver = new ChromeDriver();
        chromeDriver.get("https://www.selenium.dev/documentation/");
        String actualTitle = chromeDriver.getTitle();
        String expectedTitle = "The Selenium Browser Automation Project | Selenium";

        Assert.assertEquals(actualTitle, expectedTitle);
        chromeDriver.close();
    }

    @Test
    public static void FirefoxTest() {
        WebDriverManager.firefoxdriver().setup();
        WebDriver firefoxDriver = new FirefoxDriver();
        firefoxDriver.get("https://www.selenium.dev/documentation/");
        String actualTitle = firefoxDriver.getTitle();
        String expectedTitle = "The Selenium Browser Automation Project | Selenium";

        Assert.assertEquals(actualTitle, expectedTitle);
        firefoxDriver.close();
    }
}