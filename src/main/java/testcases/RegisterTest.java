package testcases;

import base.BaseSetup;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.MailPage;
import pages.RegisterPage;

public class RegisterTest extends BaseSetup {
    private WebDriver driver;
    private RegisterPage registerPage;
    private MailPage mailPage;
    private HomePage homePage;

    @BeforeClass
    public void setUp() {
        driver = getDriver();
        registerPage = new RegisterPage(driver);
        mailPage = new MailPage(driver);
        homePage = new HomePage(driver); // Thêm dòng này để tạo đối tượng HomePage
    }

    @Test
    public void TC01() throws Exception {
        mailPage.openMailPage();
        // Lấy email từ trang email
        String email = mailPage.getMail();
        homePage.openhomePage();
        // Quay lại trang railway và đăng ký với email đó
        homePage.clickTab("Register");
        registerPage.register(email, "123456789", "123456789", "123456789");
        mailPage.openMailPage();
    }
}
