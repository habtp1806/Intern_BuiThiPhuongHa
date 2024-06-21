package pages;

import base.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.SeleniumHelper;

import static base.DriverManager.waitForElementToBeVisible;

public class RegisterPage extends BasePage {
    private final By emailtxtXPath = By.id("email");
    private final By passwordtxtXPath = By.id("password");
    private final By confirmPassXPath = By.id("confirmPassword");
    private final By pidXPath = By.id("pid");
    private final By registerBtnXPath = By.xpath("//input[@title='Register']");
    private final By messErrorXPath = By.xpath(" //p[@class='message error']");
    private final By passwordMessXPath = By.xpath("//label[normalize-space()='Invalid password length']");
    private final By pidMessXPath = By.xpath("//label[normalize-space()='Invalid ID length']");
    private final By confirmMessXPath = By.xpath("//h1[normalize-space()='Thank you for registering your account']");
    private final By confirmRegisterXPath = By.xpath("//p[contains(text(),'Registration Confirmed! You can now log in to the ')]");

    public void register(String email, String password, String confirmPass, String pidNumber) {
        enterEmail(email);
        enterPassword(password);
        enterconfirmPassword(confirmPass);
        enterPid(pidNumber);
        clickRegister();
    }

    public void enterEmail(String email) {
        SeleniumHelper.enter(emailtxtXPath, email);
    }

    public void enterPassword(String password) {
        SeleniumHelper.enter(passwordtxtXPath, password);
    }

    public void enterconfirmPassword(String confirmPassword) {
        SeleniumHelper.enter(confirmPassXPath, confirmPassword);
    }

    public void enterPid(String pidNumber) {
        SeleniumHelper.enter(pidXPath, pidNumber);
    }

    public void clickRegister() {
        SeleniumHelper.scrollToElement(registerBtnXPath);
        SeleniumHelper.clickElement(registerBtnXPath);
    }

    public String verifyRegisterFailure(String expectedErrorMessage) {
        waitForElementToBeVisible(messErrorXPath, 5);
        return SeleniumHelper.getElementText(messErrorXPath);
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

