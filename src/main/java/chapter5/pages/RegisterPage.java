package chapter5.pages;

import chapter5.base.BaseSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class RegisterPage extends BaseSetup {

    private By emailtxt = By.id("email");
    private By passwordtxt = By.id("password");
    private By confirmPass = By.id("confirmPassword");
    private By pid = By.id("pid");
    private By registerBtn = By.xpath("//input[@title='Register']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void register(String email, String password, String confirmPass, String pidNumber) throws Exception {
        enterEmail(email);
        enterPassword(password);
        enterconfirmPassword(confirmPass);
        enterPid(pidNumber);
        clickRegister();
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

    public void enterconfirmPassword(String confirmPassword) {
        WebElement confirmPasswordTxtBox = driver.findElement(confirmPass);
        if (confirmPasswordTxtBox.isDisplayed())
            confirmPasswordTxtBox.sendKeys(confirmPassword);
    }

    public void enterPid(String pidNumber) {
        WebElement pidTxtBox = driver.findElement(pid);
        if (pidTxtBox.isDisplayed())
            pidTxtBox.sendKeys(pidNumber);
    }

    public void clickRegister() {
        WebElement registerButton = driver.findElement(registerBtn);
        if (registerButton.isDisplayed()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerButton);
            registerButton.click();
        }
    }

}
