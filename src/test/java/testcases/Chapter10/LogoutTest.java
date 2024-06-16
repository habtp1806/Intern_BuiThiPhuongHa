package testcases.Chapter10;

import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;
import testcases.base.BaseTest;

public class LogoutTest extends BaseTest {
    private BasePage basePage = new BasePage();
    private LoginPage loginPage = new LoginPage();

    @Test(description = "User is redirected to Home page after logging out")
    public void TC6() {
        User user = new User(email, password);
        basePage.openHomePage();
        basePage.clickTab("Login");
        loginPage.login(user);
        basePage.clickTab("FAQ");
        basePage.clickTab("Log out");
        Assert.assertFalse(basePage.isTabPresent("Log out"), "Test Failed: 'Log out' tab is still present.");

    }

}
