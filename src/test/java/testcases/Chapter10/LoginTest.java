package testcases.Chapter10;

import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import testcases.base.BaseTest;

public class LoginTest extends BaseTest {

    private BasePage basePage = new BasePage();
    private LoginPage loginPage = new LoginPage();
    private HomePage homePage = new HomePage();
    private String invalidemail = "abc";
    private String invalidpassword = "1234";

    @Test(description = "User can log into Railway with valid username and password")
    public void TC1() {
        User user = new User(email, password);
        basePage.openHomePage();
        basePage.clickTab("Login");
        loginPage.login(user);
        //BasePage.zoomIn(1.5);
        boolean isWelcomeDisplayed = homePage.isWelcomeUserDisplayed();
        Assert.assertEquals(isWelcomeDisplayed, true, "Welcome user message does not display");
    }

    @Test(description = "User cannot login with blank \"Username\" textbox")
    public void TC2() {
        User user = new User("", password);
        basePage.openHomePage();
        basePage.clickTab("Login");
        loginPage.login(user);
        loginPage.verifyLoginFailure("There was a problem with your login and/or errors exist in your form.");
    }

    @Test(description = "User cannot log into Railway with invalid password")
    public void TC3() {
        User user = new User(email, invalidpassword);
        basePage.openHomePage();
        basePage.clickTab("Login");
        loginPage.verifyLoginFailure("There was a problem with your login and/or errors exist in your form.");
    }

    @Test(description = "System shows message when user enters wrong password many times")
    public void TC4() {
        User user = new User(email, invalidpassword);
        basePage.openHomePage();
        basePage.clickTab("Login");
        handleLoginAttempts(user);

    }

    @Test(description = "User can't login with an account hasn't been activated")
    public void TC5() {
        User user = new User(invalidemail, invalidpassword);
        basePage.openHomePage();
        basePage.clickTab("Login");
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
