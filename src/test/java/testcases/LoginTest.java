package testcases;

import base.WebDriverConfig;
import org.openqa.selenium.WindowType;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;
import pages.MailPage;
import pages.RegisterPage;

public class LoginTest extends BaseTest {
    private RegisterPage registerPage = new RegisterPage();
    private MailPage mailPage = new MailPage();
    private BasePage basePage = new BasePage();
    private LoginPage loginPage = new LoginPage();
    private String email = "dqzvyoml@guerrillamail.com";
    private String invalidemail = "abc";
    private String password = "123456789";
    private String invalidpassword = "1234";

    @Test
    public void Login_validinfor() throws Exception {
        System.out.println("User can log into Railway with valid username and password");
        basePage.openHomePage();
        basePage.clickTab("Login");
        loginPage.login(email, password);
    }

    @Test
    public void Login_blankusername() throws Exception {
        System.out.println("User cannot login with blank \"Username\" textbox");
        basePage.openHomePage();
        basePage.clickTab("Login");
        loginPage.login("", password);
        loginPage.verifyLoginFailure("There was a problem with your login and/or errors exist in your form.");
    }

    @Test
    public void Login_invalidpass() throws Exception {
        System.out.println("User cannot log into Railway with invalid password");
        basePage.openHomePage();
        basePage.clickTab("Login");
        loginPage.login(email, invalidpassword);
        loginPage.verifyLoginFailure("There was a problem with your login and/or errors exist in your form.");
    }

    @Test
    public void Login_invalidpassmanytime() throws Exception {
        System.out.println("System shows message when user enters wrong password many times");
        basePage.openHomePage();
        basePage.clickTab("Login");
        loginPage.login(email, invalidpassword);
        loginPage.verifyLoginFailure("Invalid username or password. Please try again");
    }

    @Test
    public void Login_inactiveacount() throws Exception {
        System.out.println("User can't login with an account hasn't been activated");
        basePage.openHomePage();
        basePage.clickTab("Login");
        loginPage.login(invalidemail, invalidpassword);
        loginPage.verifyLoginFailure("Invalid username or password. Please try again");
    }

}
