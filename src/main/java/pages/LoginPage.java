package pages;

import base.WebDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage extends BasePage {

    private By emailtxt = By.xpath("//input[@id='username']");
    private By passwordtxt = By.xpath("//input[@id='password']");
    private By loginBtn = By.xpath("//input[@title='Login']");
    private By messFail = By.xpath("p[@class='message error LoginForm']");
    // private String welcomeUser = String.format("//strong[normalize-space()='Welcome %s']");


    public void login(String email, String password) throws Exception {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
        Thread.sleep(1000);
    }

    public void enterEmail(String email) {
        WebElement emailTxtBox = WebDriverConfig.driver.findElement(emailtxt);
        if (emailTxtBox.isDisplayed())
            emailTxtBox.sendKeys(email);

    }

    public void enterPassword(String password) {
        WebElement passwordTxtBox = WebDriverConfig.driver.findElement(passwordtxt);
        if (passwordTxtBox.isDisplayed())
            passwordTxtBox.sendKeys(password);
    }

    public void clickLogin() {
        WebElement loginButton = WebDriverConfig.driver.findElement(loginBtn);
        if (loginButton.isDisplayed()) {
            // Scroll to the login button
            ((JavascriptExecutor) WebDriverConfig.driver).executeScript("arguments[0].scrollIntoView(true);", loginButton);

            loginButton.click();
        }
    }

    public void verifyLoginFailure(String expectedErrorMessage) {
        waitForElementToBeVisible(messFail, 5);
        WebElement errorMessage = WebDriverConfig.driver.findElement(messFail);
        String actualErrorMessage = errorMessage.getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message does not match.");
    }
//    public void verifyWelcomeUser(String user) {
//        WebElement welcomeMessage = WebDriverConfig.driver.findElement();
//        Assert.assertTrue(welcomeMessage.isDisplayed(), "Welcome user message is not displayed.");
//    }
}

