package testcases.Chapter10;

import enums.RailwayTab;
import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import testcases.base.BaseTest;

import static base.DriverManager.openHomePage;

public class LoginTest extends BaseTest {

    private BasePage basePage = new BasePage();
    private LoginPage loginPage = new LoginPage();
    private HomePage homePage = new HomePage();
    private String invalidemail = "abc";
    private String invalidpassword = "1234";

    @Test(description = "User can log into Railway with valid username and password")
    public void verifyValidInforLogin() {
        User user = new User(email, password);
        openHomePage();
        basePage.clickTab(RailwayTab.LOGIN.getValue());
        loginPage.login(user);
        boolean isWelcomeDisplayed = homePage.isWelcomeUserDisplayed();
        Assert.assertTrue(isWelcomeDisplayed, "Welcome user message does not display");
    }

    @Test(description = "User cannot login with blank \"Username\" textbox")
    public void verifyBlankUserNameLogin() {
        User user = new User("", password);
        openHomePage();
        basePage.clickTab(RailwayTab.LOGIN.getValue());
        loginPage.login(user);
        loginPage.verifyLoginFailure("There was a problem with your login and/or errors exist in your form.");
    }

    @Test(description = "User cannot log into Railway with invalid password")
    public void verifyInvalidPasswordLogin() {
        User user = new User(email, invalidpassword);
        openHomePage();
        basePage.clickTab(RailwayTab.LOGIN.getValue());
        loginPage.verifyLoginFailure("There was a problem with your login and/or errors exist in your form.");
    }

    @Test(description = "System shows message when user enters wrong password many times")
    public void verifyPasswordAttemptsMessage() {
        User user = new User(email, invalidpassword);
        openHomePage();
        basePage.clickTab(RailwayTab.LOGIN.getValue());
        handleLoginAttempts(user);

    }

    @Test(description = "User can't login with an account hasn't been activated")
    public void verifyInactiveAccountLogin() {
        User user = new User(invalidemail, invalidpassword);
        openHomePage();
        basePage.clickTab(RailwayTab.LOGIN.getValue());
        loginPage.login(user);
        loginPage.verifyLoginFailure("Invalid username or password. Please try again.");
    }

    private void handleLoginAttempts(User user) {
        for (int i = 1; i <= 4; i++) {
            loginPage.login(user);
            String expectedErrorMessage;
            if (i < 4) {
                expectedErrorMessage = "Invalid username or password. Please try again";
            } else {
                expectedErrorMessage = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
            }
            loginPage.verifyLoginFailure(expectedErrorMessage);
        }
    }
}
