package testcases.Chapter10;

import base.Config;
import base.DriverManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;
import pages.MailPage;
import pages.RegisterPage;
import testcases.base.BaseTest;

import java.util.Set;

import static base.DriverManager.*;
import static org.testng.AssertJUnit.assertTrue;

public class RegisterTest extends BaseTest {
    private final RegisterPage registerPage = new RegisterPage();
    private final MailPage mailPage = new MailPage();
    private final BasePage basePage = new BasePage();
    private final HomePage homePage = new HomePage();

    @Test(description = "User can't create account with an already in-use email")
    public void verifyUniqueEmailRegistration() {
        navigateToRailWay();
        BasePage.clickTab("Register");
        registerPage.register(email, password, "123456789", "123456789");
        String actualErrorMessage = registerPage.verifyRegisterFailure("This email address is already in use.");
        String expectedErrorMessage = "This email address is already in use.";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message does not match.");

    }

    @Test(description = "User can't create account while password and PID fields are empty")
    public void verifyEmptyFieldsRegistration() {
        navigateToRailWay();
        BasePage.clickTab("Register");
        registerPage.register(email, "", "", "");
        registerPage.verifyRegisterFailure("There're errors in the form. Please correct the errors and try again.");
        String pwdError = registerPage.getPasswordErrorMessage();
        Assert.assertEquals(pwdError, "Invalid password length.");
        String pidError = registerPage.getPIDErrorMessage();
        Assert.assertEquals(pidError, "Invalid ID length.");
    }

    @Test(description = "User create and activate account")
    public void verifyAccountCreationActivation() {
        navigateToRailWay();
        assertTrue("Guide link is not present on the home page.", homePage.isGuideLinkPresent());
        BasePage.clickLink("create an account");
        String registerWindow = getWindowHandle();
        BasePage.openNewTab(Config.getProperty("tempmail.url"));
        email = mailPage.getMail();
        String mailWindow = getWindowHandle();
        switchToWindow(registerWindow);
        BasePage.clickTab("Register");
        registerPage.register(email, password, password, pid);
        assertTrue("Confirm message is not present after register successfully.", registerPage.isConfirmationSuccessful());
        switchToWindow(mailWindow);
        refreshPage();
        mailPage.getConFirmLinkMail();
        BasePage.switchToRemainingTab(registerWindow, mailWindow);
        assertTrue("Registration Confirmed! You can now log in to the site", registerPage.isConfirmationRegister());

    }


}
