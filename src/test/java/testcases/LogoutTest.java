package testcases;

import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;
import pages.MailPage;
import pages.RegisterPage;

public class LogoutTest extends BaseTest {
    private RegisterPage registerPage = new RegisterPage();
    private MailPage mailPage = new MailPage();
    private BasePage basePage = new BasePage();
    private LoginPage loginPage = new LoginPage();
    private String email = "dqzvyoml@guerrillamail.com";
    private String password = "123456789";

    @Test
    public void Logout() throws Exception {
        System.out.println("User is redirected to Home page after logging out");
        basePage.openHomePage();
        basePage.clickTab("Login");
        loginPage.login(email, password);
        basePage.clickTab("FAQ");
        basePage.clickTab("Log out");
    }
}
