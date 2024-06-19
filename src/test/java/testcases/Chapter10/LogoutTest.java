package testcases.Chapter10;

import enums.RailwayTab;
import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;
import testcases.base.BaseTest;

import static base.DriverManager.openHomePage;
import static org.testng.AssertJUnit.assertFalse;

public class LogoutTest extends BaseTest {
    private BasePage basePage = new BasePage();
    private LoginPage loginPage = new LoginPage();

    @Test(description = "User is redirected to Home page after logging out")
    public void verifyLogoutRedirect() {
        User user = new User(email, password);
        openHomePage();
        basePage.clickTab(RailwayTab.LOGIN.getValue());
        loginPage.login(user);
        basePage.clickTab(RailwayTab.FAQ.getValue());
        basePage.clickTab(RailwayTab.LOG_OUT.getValue());
        assertFalse(BasePage.isTabDisplayed("Log out"));
    }

}
