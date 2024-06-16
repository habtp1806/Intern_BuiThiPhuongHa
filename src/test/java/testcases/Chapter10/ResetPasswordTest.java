package testcases.Chapter10;

import base.DriverManager;
import org.openqa.selenium.WindowType;
import org.testng.annotations.Test;
import pages.*;
import testcases.base.BaseTest;

public class ResetPasswordTest extends BaseTest {
    private MailPage mailPage = new MailPage();
    private BasePage basePage = new BasePage();
    private LoginPage loginPage = new LoginPage();
    private ForgotPassPage forgotPassPage = new ForgotPassPage();
    private ResetPasswordPage resetPasswordPage = new ResetPasswordPage();
    private String newPass = "1234567890";
    private String confirmDifferent = "1234567899";

    @Test(description = "Reset password shows error if the new password is same as current")
    public void TC10() {
        basePage.openHomePage();
        basePage.clickTab("Login");
        basePage.clickLink("Forgot Password page");
        forgotPassPage.sendForgotPass(email);
        DriverManager.driver.switchTo().newWindow(WindowType.TAB);
        mailPage.openMailPage();
        mailPage.setMail("dqzvyoml", "guerrillamail.com");
        mailPage.clickResetLink();
        resetPasswordPage.inputDifferentPasswords("1234567890", "1234567899");
        resetPasswordPage.clickRest();

    }

    @Test(description = "User can check price of ticket from Timetable")
    public void TC11() {
        System.out.println("Reset password shows error if the new password is same as current");
        basePage.openHomePage();
        basePage.clickTab("Login");
        basePage.clickLink("Forgot Password page");
        forgotPassPage.sendForgotPass(email);
        DriverManager.driver.switchTo().newWindow(WindowType.TAB);
        mailPage.openMailPage();
        mailPage.setMail("dqzvyoml", "guerrillamail.com");
        mailPage.clickResetLink();
        resetPasswordPage.inputDifferentPasswords(newPass, confirmDifferent);
        resetPasswordPage.clickRest();
    }

}
