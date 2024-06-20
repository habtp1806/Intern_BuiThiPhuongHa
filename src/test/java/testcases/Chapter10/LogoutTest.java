package testcases.Chapter10;

import enums.RailwayTab;
import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;
import testcases.base.BaseTest;

import static base.DriverManager.navigateToRailWay;
import static org.testng.AssertJUnit.assertFalse;

public class LogoutTest extends BaseTest {
    private final BasePage basePage = new BasePage();
    private final LoginPage loginPage = new LoginPage();

    @Test(description = "User is redirected to Home page after logging out")
    public void verifyLogoutRedirect() {
        User user = new User(email, password);
        navigateToRailWay();
        BasePage.clickTab(RailwayTab.LOGIN.getValue());
        loginPage.login(user);
        BasePage.clickTab(RailwayTab.FAQ.getValue());
        BasePage.clickTab(RailwayTab.LOG_OUT.getValue());
        assertFalse(BasePage.isTabDisplayed("Log out"));
    }

}
