package testcases.Chapter10;

import base.DriverManager;
import enums.RailwayTab;
import org.openqa.selenium.WindowType;
import org.testng.annotations.Test;
import pages.*;
import testcases.base.BaseTest;

import static base.DriverManager.openHomePage;
import static base.DriverManager.openMailPage;

public class ResetPasswordTest extends BaseTest {
    private MailPage mailPage = new MailPage();
    private BasePage basePage = new BasePage();
    private LoginPage loginPage = new LoginPage();
    private ForgotPassPage forgotPassPage = new ForgotPassPage();
    private ResetPasswordPage resetPasswordPage = new ResetPasswordPage();
    private String newPass = "1234567890";
    private String confirmDifferent = "1234567899";

    @Test(description = "Reset password shows error if the new password is same as current")
    public void verifyPasswordResetError() {
        openHomePage();
        basePage.clickTab(RailwayTab.LOGIN.getValue());
        basePage.clickLink("Forgot Password page");
        forgotPassPage.sendForgotPass(email);
        DriverManager.driver.switchTo().newWindow(WindowType.TAB);
        openMailPage();
        mailPage.setMail("dqzvyoml", "guerrillamail.com");
        mailPage.clickResetLink();
        resetPasswordPage.resetPassword("1234567890", "1234567899");
        resetPasswordPage.clickReset();

    }

    @Test(description = "Reset password shows error if the new password and confirm password doesn't match")
    public void verifyPassWordMatch() {
        openHomePage();
        basePage.clickTab(RailwayTab.LOGIN.getValue());
        basePage.clickLink("Forgot Password page");
        forgotPassPage.sendForgotPass(email);
        DriverManager.driver.switchTo().newWindow(WindowType.TAB);
        openMailPage();
        mailPage.setMail("dqzvyoml", "guerrillamail.com");
        mailPage.clickResetLink();
        resetPasswordPage.resetPassword(newPass, confirmDifferent);
        resetPasswordPage.clickReset();
    }

}
