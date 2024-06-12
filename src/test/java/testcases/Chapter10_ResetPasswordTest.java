package testcases;

import base.WebDriverConfig;
import org.openqa.selenium.WindowType;
import org.testng.annotations.Test;
import pages.*;

public class Chapter10_ResetPasswordTest extends BaseTest {
    private RegisterPage registerPage = new RegisterPage();
    private MailPage mailPage = new MailPage();
    private BasePage basePage = new BasePage();
    private LoginPage loginPage = new LoginPage();
    private ForgotPassPage forgotPassPage = new ForgotPassPage();
    private ResetPasswordPage resetPasswordPage = new ResetPasswordPage();

    @Test
    public void TC10() throws Exception {
        System.out.println("Reset password shows error if the new password is same as current");
        basePage.openHomePage();
        basePage.clickTab("Login");
        basePage.clickLink("Forgot Password page");
        forgotPassPage.sendForgotPass(email);
        WebDriverConfig.driver.switchTo().newWindow(WindowType.TAB);
        mailPage.openMailPage();
        mailPage.setMail("dqzvyoml", "guerrillamail.com");
        mailPage.clickResetLink();
        resetPasswordPage.inputDifferentPasswords("1234567890", "1234567899");
        resetPasswordPage.clickRest();

    }

    @Test
    public void TC11() throws Exception {
        System.out.println("Reset password shows error if the new password is same as current");
        basePage.openHomePage();
        basePage.clickTab("Login");
        basePage.clickLink("Forgot Password page");
        forgotPassPage.sendForgotPass(email);
        WebDriverConfig.driver.switchTo().newWindow(WindowType.TAB);
        mailPage.openMailPage();
        mailPage.setMail("dqzvyoml", "guerrillamail.com");
        mailPage.clickResetLink();
        resetPasswordPage.inputDifferentPasswords("1234567890", "1234567899");
        resetPasswordPage.clickRest();
    }

}
