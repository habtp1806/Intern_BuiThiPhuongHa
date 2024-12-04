package testcases.Chapter10;

import base.DriverManager;
import enums.RailwayTab;
import model.User;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;
import testcases.base.BaseTest;
import utils.commons.DataUtils;

import static org.testng.Assert.assertFalse;

public class ParallelTest extends BaseTest {
    private final BasePage basePage = new BasePage();
    private final LoginPage loginPage = new LoginPage();

    @Test(description = "User can book 1 ticket at a time", dataProvider = "browserData", dataProviderClass = DataUtils.class)
    public void verifyLogoutRedirect(String browser) {
        User user = new User(email, password);
        DriverManager.navigateToRailWay();
        BasePage.clickTab(RailwayTab.LOGIN);
        loginPage.login(user);
        BasePage.clickTab(RailwayTab.FAQ);
        BasePage.clickTab(RailwayTab.LOG_OUT);
        assertFalse(BasePage.isTabDisplayed("Log out"));
    }
}
