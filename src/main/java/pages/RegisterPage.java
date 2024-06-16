package pages;

import base.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.SeleniumHelper;

public class RegisterPage extends BasePage {
    private By emailtxtXPath = By.id("email");
    private By passwordtxtXPath = By.id("password");
    private By confirmPassXPath = By.id("confirmPassword");
    private By pidXPath = By.id("pid");
    private By registerBtnXPath = By.xpath("//input[@title='Register']");
    private By messErrorXPath = By.xpath(" //p[@class='message error']");
    private By passwordMessXPath = By.xpath("//label[normalize-space()='Invalid password length']");
    private By pidMessXPath = By.xpath("//label[normalize-space()='Invalid ID length']");
    private By confirmMessXPath = By.xpath("//h1[normalize-space()='Thank you for registering your account']");
    private By confirmRegisterXPath = By.xpath("//p[contains(text(),'Registration Confirmed! You can now log in to the ')]");

    public void register(String email, String password, String confirmPass, String pidNumber) {
        enterEmail(email);
        enterPassword(password);
        enterconfirmPassword(confirmPass);
        enterPid(pidNumber);
        clickRegister();
    }

    public void enterEmail(String email) {
        SeleniumHelper.enterText(emailtxtXPath, email);
    }

    public void enterPassword(String password) {
        SeleniumHelper.enterText(passwordtxtXPath, password);
    }

    public void enterconfirmPassword(String confirmPassword) {
        SeleniumHelper.enterText(confirmPassXPath, confirmPassword);
    }

    public void enterPid(String pidNumber) {
        SeleniumHelper.enterText(pidXPath, pidNumber);
    }

    public void clickRegister() {
        SeleniumHelper.scrollToElement(registerBtnXPath);
        SeleniumHelper.clickElement(registerBtnXPath);
    }

    public void verifyRegisterFailure(String expectedErrorMessage) {
        waitForElementToBeVisible(messErrorXPath, 5);
        String actualErrorMessage = SeleniumHelper.getElementText(messErrorXPath);
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message does not match.");
    }


    public String getPasswordErrorMessage() {
        return SeleniumHelper.getElementText(passwordMessXPath);
    }

    public String getPIDErrorMessage() {
        return SeleniumHelper.getElementText(pidMessXPath);
    }

    public boolean isConfirmationSuccessful() {
        return SeleniumHelper.isElementDisplayed(confirmMessXPath);
    }

    public boolean isConfirmationRegister() {
        return SeleniumHelper.isElementDisplayed(confirmRegisterXPath);
    }
}

