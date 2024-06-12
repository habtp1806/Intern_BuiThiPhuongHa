package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class Chapter10_LoginTest extends BaseTest {
    
    private BasePage basePage = new BasePage();
    private LoginPage loginPage = new LoginPage();
    private HomePage homePage = new HomePage();

    private String invalidemail = "abc";
    private String invalidpassword = "1234";

    @Test
    public void TC1() throws Exception {
        System.out.println("User can log into Railway with valid username and password");
        basePage.openHomePage();
        basePage.clickTab("Login");
        loginPage.login(email, password);
        BasePage.zoomIn(1.5);
        Assert.assertEquals(homePage.isWelcomUserDisplayed(), Boolean.TRUE, "Welcome user message does not display");
    }

    @Test
    public void TC2() throws Exception {
        System.out.println("User cannot login with blank \"Username\" textbox");
        basePage.openHomePage();
        basePage.clickTab("Login");
        loginPage.login("", password);
        loginPage.verifyLoginFailure("There was a problem with your login and/or errors exist in your form.");
    }

    @Test
    public void TC3() throws Exception {
        System.out.println("User cannot log into Railway with invalid password");
        basePage.openHomePage();
        basePage.clickTab("Login");
        loginPage.login(email, invalidpassword);
        loginPage.verifyLoginFailure("There was a problem with your login and/or errors exist in your form.");
    }

    @Test
    public void TC4() throws Exception {
        System.out.println("System shows message when user enters wrong password many times");
        basePage.openHomePage();
        basePage.clickTab("Login");
        for (int i = 1; i <= 4; i++) {
            loginPage.login(email, invalidpassword);
            String expectedErrorMessage;
            if (i < 4) {
                expectedErrorMessage = "Invalid username or password. Please try again";
            } else {
                expectedErrorMessage = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
            }
            loginPage.verifyLoginFailure(expectedErrorMessage);
        }
    }

    @Test
    public void TC5() throws Exception {
        System.out.println("User can't login with an account hasn't been activated");
        basePage.openHomePage();
        basePage.clickTab("Login");
        loginPage.login(invalidemail, invalidpassword);
        loginPage.verifyLoginFailure("Invalid username or password. Please try again.");
    }

}
