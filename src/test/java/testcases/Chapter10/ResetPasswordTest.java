package testcases.Chapter10;

import base.DriverManager;
import enums.RailwayTab;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import testcases.base.BaseTest;

import static base.DriverManager.*;

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
        String railwayWindow = getWindowHandle();
        basePage.clickTab(RailwayTab.LOGIN.getValue());
        basePage.clickLink("Forgot Password page");
        forgotPassPage.sendForgotPass(email);
        DriverManager.driver.switchTo().newWindow(WindowType.TAB);
        openMailPage();
        String mailWindow = getWindowHandle();
        mailPage.setMail("dqzvyoml", "guerrillamail.com");
        mailPage.clickResetLink();
        String token = mailPage.getResetPasswordToken();
        basePage.switchToRemainingTab(mailWindow, railwayWindow);
        Assert.assertTrue(resetPasswordPage.isResetPasswordFormDisplayed(), "Change password form does not displayed");
        Assert.assertEquals(resetPasswordPage.getResetTokenInTextBox(), token, "Reset token does not match");
        resetPasswordPage.resetPassword(newPass, newPass);
        resetPasswordPage.clickReset();
        Assert.assertEquals(resetPasswordPage.getMessageAbove(), "The new password cannot be the same with the current password");
    }

    @Test(description = "Reset password shows error if the new password and confirm password doesn't match")
    public void verifyPassWordMatch() {
        openHomePage();
        String railwayWindow = getWindowHandle();
        basePage.clickTab(RailwayTab.LOGIN.getValue());
        basePage.clickLink("Forgot Password page");
        forgotPassPage.sendForgotPass(email);
        DriverManager.driver.switchTo().newWindow(WindowType.TAB);
        openMailPage();
        String mailWindow = getWindowHandle();
        mailPage.setMail("dqzvyoml", "guerrillamail.com");
        mailPage.clickResetLink();
        String token = mailPage.getResetPasswordToken();
        basePage.switchToRemainingTab(mailWindow, railwayWindow);
        Assert.assertTrue(resetPasswordPage.isResetPasswordFormDisplayed(), "Change password form does not displayed");
        Assert.assertEquals(resetPasswordPage.getResetTokenInTextBox(), token, "Reset token does not match");
        resetPasswordPage.resetPassword(newPass, "1234567899");
        resetPasswordPage.clickReset();
        Assert.assertEquals(resetPasswordPage.getMessageNextToTextBox(), "The password confirmation did not match the new password.");
        Assert.assertEquals(resetPasswordPage.getMessageAbove(), "Could not reset password. Please correct the errors and try again.");

    }
}


