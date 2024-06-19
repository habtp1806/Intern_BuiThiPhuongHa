package pages;

import base.DriverManager;
import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.SeleniumHelper;

public class LoginPage extends BasePage {

    private By loginBtnXPath = By.xpath("//input[@title='Login']");
    private By messFailXPath = By.xpath("//p[@class='message error LoginForm']");
    private static String railway;

    private By getXPathByName(String name) {
        return By.xpath(String.format("//input[@id='%s']", name));
    }

    public void login(User user) {
        enterEmail(user.getEmail());
        enterPassword(user.getPassword());
        clickLogin();
    }

    public void enterEmail(String email) {
        SeleniumHelper.enter(getXPathByName("username"), email);
    }

    public void enterPassword(String password) {
        SeleniumHelper.enter(getXPathByName("password"), password);
    }

    public void clickLogin() {
        SeleniumHelper.scrollToElement(loginBtnXPath);
        SeleniumHelper.clickElement(loginBtnXPath);
    }

    public void verifyLoginFailure(String expectedErrorMessage) {
        SeleniumHelper.verifyTextEquals(messFailXPath, expectedErrorMessage);
    }

}

