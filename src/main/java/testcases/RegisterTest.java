package testcases;

import base.BaseSetup;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.MailPage;
import pages.RegisterPage;

public class RegisterTest extends BaseSetup {
    private WebDriver driver;
    public RegisterPage registerPage;
    public MailPage mailPage;

    @BeforeClass
    public void setUp() {
        driver = getDriver();
        registerPage = new RegisterPage(driver);
        mailPage = new MailPage(driver);
    }

    @Test
    public void TC01() throws Exception {
        driver.navigate().to("https://www.guerrillamail.com/inbox");
        String email = mailPage.getMail();
        System.out.printf(email);
        
        registerPage.register("hahah@1234kk", "123456789", "123456789", "123456789");
    }


}
