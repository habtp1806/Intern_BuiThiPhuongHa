package pages;

import base.WebDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage extends BasePage {
    private By emailtxt = By.id("email");
    private By passwordtxt = By.id("password");
    private By confirmPass = By.id("confirmPassword");
    private By pid = By.id("pid");
    private By registerBtn = By.xpath("//input[@title='Register']");


    public void register(String email, String password, String confirmPass, String pidNumber) throws Exception {
        enterEmail(email);
        enterPassword(password);
        enterconfirmPassword(confirmPass);
        enterPid(pidNumber);
        clickRegister();
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

    public void enterconfirmPassword(String confirmPassword) {
        WebElement confirmPasswordTxtBox = WebDriverConfig.driver.findElement(confirmPass);
        if (confirmPasswordTxtBox.isDisplayed())
            confirmPasswordTxtBox.sendKeys(confirmPassword);
    }

    public void enterPid(String pidNumber) {
        WebElement pidTxtBox = WebDriverConfig.driver.findElement(pid);
        if (pidTxtBox.isDisplayed())
            pidTxtBox.sendKeys(pidNumber);
    }

    public void clickRegister() {
        WebElement registerButton = WebDriverConfig.driver.findElement(registerBtn);
        if (registerButton.isDisplayed()) {
            ((JavascriptExecutor) WebDriverConfig.driver).executeScript("arguments[0].scrollIntoView(true);", registerButton);
            registerButton.click();
        }
    }

}
