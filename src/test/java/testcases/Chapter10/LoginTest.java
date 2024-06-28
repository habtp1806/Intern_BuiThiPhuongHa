package testcases.Chapter10;

import enums.RailwayTab;
import model.User;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;
import testcases.base.BaseTest;
import utils.DataUtils;
import utils.listeners.ReportListener;

import static base.DriverManager.navigateToRailWay;

@Listeners(ReportListener.class)
public class LoginTest extends BaseTest {

    private final LoginPage loginPage = new LoginPage();
    private final HomePage homePage = new HomePage();

    @Test(description = "User can log into Railway with valid username and password")
    public void verifyValidInforLogin() {
        User user = DataUtils.getUserFromJson("validCredentials");
        navigateToRailWay();
        BasePage.clickTab(RailwayTab.LOGIN);
        loginPage.login(user);
        boolean isWelcomeDisplayed = homePage.isWelcomeUserDisplayed();
        Assert.assertTrue(isWelcomeDisplayed, "Welcome user message does not display");
    }

    @Test(description = "User cannot login with blank \"Username\" textbox")
    public void verifyBlankUserNameLogin() {
        User user = DataUtils.getUserFromJson("invalidBlankUsername");
        navigateToRailWay();
        BasePage.clickTab(RailwayTab.LOGIN);
        loginPage.login(user);
        loginPage.verifyLoginFailure("There was a problem with your login and/or errors exist in your form.");
    }

    @Test(description = "User cannot log into Railway with invalid password")
    public void verifyInvalidPasswordLogin() {
        User user = DataUtils.getUserFromJson("invalidPassword");
        navigateToRailWay();
        BasePage.clickTab(RailwayTab.LOGIN);
        loginPage.login(user);
        loginPage.verifyLoginFailure("There was a problem with your login and/or errors exist in your form.");
    }

    @Test(description = "System shows message when user enters wrong password many times")
    public void verifyPasswordAttemptsMessage() {
        User user = DataUtils.getUserFromJson("passwordAttempts");
        navigateToRailWay();
        BasePage.clickTab(RailwayTab.LOGIN);
        loginPage.login(user);
        handleLoginAttempts(user);

    }

    @Test(description = "User can't login with an account hasn't been activated")
    public void verifyInactiveAccountLogin() {
        User user = DataUtils.getUserFromJson("inactiveAccount");
        navigateToRailWay();
        BasePage.clickTab(RailwayTab.LOGIN);
        loginPage.login(user);
        loginPage.verifyLoginFailure("Invalid username or password. Please try again.");
    }

    private void handleLoginAttempts(User user) {
        for (int i = 1; i <= 5; i++) {
            loginPage.login(user);
            String expectedErrorMessage;
            if (i < 4) {
                expectedErrorMessage = "Invalid username or password. Please try again";
            } else {
                expectedErrorMessage = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
            }
            loginPage.verifyLoginFailure(expectedErrorMessage);
            BasePage.clickTab(RailwayTab.LOGIN);
        }
    }
}
