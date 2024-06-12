package testcases;

import base.WebDriverConfig;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;
import pages.MailPage;
import pages.RegisterPage;

public class Chapter10_LogoutTest extends BaseTest {
    private BasePage basePage = new BasePage();
    private LoginPage loginPage = new LoginPage();
    private String email = "dqzvyoml@guerrillamail.com";
    private String password = "123456789";

    @Test
    public void TC6() throws Exception {
        System.out.println("User is redirected to Home page after logging out");
        basePage.openHomePage();
        basePage.clickTab("Login");
        loginPage.login(email, password);
        basePage.clickTab("FAQ");
        basePage.clickTab("Log out");
        Assert.assertFalse(basePage.isTabPresent("Log out"), "Test Failed: 'Log out' tab is still present.");
        
    }

}
