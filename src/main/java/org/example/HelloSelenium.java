package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HelloSelenium {
    public static void main(String[] args) {
        try {
            WebDriver chromeDriver = new ChromeDriver();
            verifyTitle(chromeDriver, "Chrome");
        } catch (WebDriverException e) {
            System.out.println("Chrome WebDriver initialization failed: " + e.getMessage());
        }

        // Test with Firefox
        try {
            WebDriver firefoxDriver = new FirefoxDriver();
            verifyTitle(firefoxDriver, "Firefox");
        } catch (WebDriverException e) {
            System.out.println("Firefox WebDriver initialization failed: " + e.getMessage());
        }
}
    private static void verifyTitle(WebDriver driver, String browserName) {
        try {
            driver.get("https://www.selenium.dev/documentation/");
            String expectedTitle = "The Selenium Browser Automation Project | Selenium";
            String actualTitle = driver.getTitle();

            if (expectedTitle.equals(actualTitle)) {
                System.out.println(browserName + ": Test case passed!");
            } else {
                System.out.println(browserName + ": Test case failed. Actual title: " + actualTitle);
            }
        } finally {
            driver.quit();
        }
    }
}