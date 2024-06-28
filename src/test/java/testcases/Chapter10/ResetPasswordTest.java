package testcases.Chapter10;

import base.Config;
import base.DriverManager;
import enums.RailwayTab;
import org.json.JSONObject;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import testcases.base.BaseTest;

import static base.DriverManager.*;

public class ResetPasswordTest extends BaseTest {
    private final MailPage mailPage = new MailPage();
    private final BasePage basePage = new BasePage();
    private final LoginPage loginPage = new LoginPage();
    private final ForgotPassPage forgotPassPage = new ForgotPassPage();
    private final ResetPasswordPage resetPasswordPage = new ResetPasswordPage();

    @Test(description = "Reset password shows error if the new password is same as current")
    public void verifyPasswordResetError() {
        JSONObject testData = Config.getJsonObject();
        JSONObject userData = testData.getJSONObject("resetPasswordSameAsCurrent");
        String newPass = userData.getString("newPass");
        String confirmPassword = userData.getString("confirmPassword");
        navigateToRailWay();
        String railwayWindow = getWindowHandle();
        BasePage.clickTab(RailwayTab.LOGIN);
        BasePage.clickLink("Forgot Password page");
        forgotPassPage.sendForgotPass(email);
        DriverManager.driver.switchTo().newWindow(WindowType.TAB);
        navigateToMailPage();
        String mailWindow = getWindowHandle();
        mailPage.setMail("dqzvyoml", "guerrillamail.com");
        mailPage.clickResetLink();
        String token = mailPage.getResetPasswordToken();
        BasePage.switchToRemainingTab(mailWindow, railwayWindow);
        Assert.assertTrue(resetPasswordPage.isResetPasswordFormDisplayed(), "Change password form does not displayed");
        Assert.assertEquals(resetPasswordPage.getResetTokenInTextBox(), token, "Reset token does not match");
        resetPasswordPage.resetPassword(newPass, confirmPassword);
        resetPasswordPage.clickReset();
        Assert.assertEquals(resetPasswordPage.getMessageAboveForm(), "The new password cannot be the same with the current password");
    }

    @Test(description = "Reset password shows error if the new password and confirm password doesn't match")
    public void verifyPassWordMatch() {
        JSONObject testData = Config.getJsonObject();
        JSONObject userData = testData.getJSONObject("resetPasswordMismatch");
        String newPass = userData.getString("newPass");
        String confirmPassword = userData.getString("confirmPassword");
        navigateToRailWay();
        String railwayWindow = getWindowHandle();
        BasePage.clickTab(RailwayTab.LOGIN);
        BasePage.clickLink("Forgot Password page");
        forgotPassPage.sendForgotPass(email);
        DriverManager.driver.switchTo().newWindow(WindowType.TAB);
        navigateToMailPage();
        String mailWindow = getWindowHandle();
        mailPage.setMail("dqzvyoml", "guerrillamail.com");
        mailPage.clickResetLink();
        String token = mailPage.getResetPasswordToken();
        BasePage.switchToRemainingTab(mailWindow, railwayWindow);
        Assert.assertTrue(resetPasswordPage.isResetPasswordFormDisplayed(), "Change password form does not displayed");
        Assert.assertEquals(resetPasswordPage.getResetTokenInTextBox(), token, "Reset token does not match");
        resetPasswordPage.resetPassword(newPass, confirmPassword);
        resetPasswordPage.clickReset();
        Assert.assertEquals(resetPasswordPage.getMessageNextToTextBox(), "The password confirmation did not match the new password.");
        Assert.assertEquals(resetPasswordPage.getMessageAboveForm(), "Could not reset password. Please correct the errors and try again.");

    }
}


