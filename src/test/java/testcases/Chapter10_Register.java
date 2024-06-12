package testcases;

import base.Config;
import base.WebDriverConfig;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;
import pages.MailPage;
import pages.RegisterPage;

import java.util.Set;

import static org.testng.AssertJUnit.assertTrue;

public class Chapter10_Register extends BaseTest {
    private RegisterPage registerPage = new RegisterPage();
    private MailPage mailPage = new MailPage();
    private BasePage basePage = new BasePage();
    private HomePage homePage = new HomePage();

    @Test
    public void TC7() throws Exception {
        System.out.println("User can't create account with an already in-use email");
        basePage.openHomePage();
        basePage.clickTab("Register");
        registerPage.register(email, password, "123456789", "123456789");
        registerPage.verifyRegisterFailure("This email address is already in use.");

    }

    @Test
    public void TC8() throws Exception {
        System.out.println("User can't create account while password and PID fields are empty");
        basePage.openHomePage();
        basePage.clickTab("Register");
        registerPage.register(email, "", "", "");
        registerPage.verifyRegisterFailure("There're errors in the form. Please correct the errors and try again.");
        String pwdError = registerPage.getPasswordErrorMessage();
        Assert.assertEquals(pwdError, "Invalid password length.");
        String pidError = registerPage.getPIDErrorMessage();
        Assert.assertEquals(pidError, "Invalid ID length.");
    }

    @Test
    public void TC9() throws Exception {
        System.out.println("User create and activate account");
        basePage.openHomePage();
        assertTrue("Guide link is not present on the home page.", homePage.isGuideLinkPresent());
        basePage.clickLink("create an account");
        String registerWindow = basePage.getWindowHandle();
        basePage.openNewTab(Config.getProperty("tempmail.url"));
        email = mailPage.getMail();
        String mailWindow = basePage.getWindowHandle();
        basePage.switchToWindow(registerWindow);
        basePage.clickTab("Register");
        registerPage.register(email, password, password, pid);
        assertTrue("Confirm message is not present after register successfully.", registerPage.isConfirmationSuccessful());
        basePage.switchToWindow(mailWindow);
        basePage.refreshPage();
        mailPage.verifyMail();
        Set<String> allTabs = WebDriverConfig.driver.getWindowHandles();
        for (String tab : allTabs) {
            if (!tab.equals(mailWindow) && !tab.equals(registerWindow)) {
                WebDriverConfig.driver.switchTo().window(tab);
                break;
            }
        }
        assertTrue("Registration Confirmed! You can now log in to the site", registerPage.isConfirmationRegister());

    }

}
