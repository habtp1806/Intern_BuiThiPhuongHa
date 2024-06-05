package chapter5.base.pages;

import chapter5.base.BaseSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage extends BaseSetup {
    private By emailtxt = By.xpath("//input[@id='username']");
    private By passwordtxt = By.xpath("//input[@id='password']");
    private By loginBtn = By.xpath("//input[@title='Login']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String email, String password) throws Exception {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
        Thread.sleep(1000);
    }

    public void enterEmail(String email) {
        WebElement emailTxtBox = driver.findElement(emailtxt);
        if (emailTxtBox.isDisplayed())
            emailTxtBox.sendKeys(email);

    }

    public void enterPassword(String password) {
        WebElement passwordTxtBox = driver.findElement(passwordtxt);
        if (passwordTxtBox.isDisplayed())
            passwordTxtBox.sendKeys(password);
    }

    public void clickLogin() {
        WebElement loginButton = driver.findElement(loginBtn);
        if (loginButton.isDisplayed()) {
            // Scroll to the login button
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginButton);

            // Click on the login button
            loginButton.click();
        }
    }

    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
                        .equals("complete");
            }
        };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }
}

