package testcases;

import chapter5.base.BaseSetup;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import chapter5.base.pages.HomePage;
import chapter5.base.pages.LoginPage;
import chapter5.base.pages.MailPage;
import chapter5.base.pages.RegisterPage;

public class RegisterTest extends BaseSetup {

    private RegisterPage registerPage;
    private MailPage mailPage;
    private HomePage homePage;
    private LoginPage loginPage;
    private String email;
    private String password = "123456789";

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        registerPage = new RegisterPage(driver);
        mailPage = new MailPage(driver);
        homePage = new HomePage(driver); // Thêm dòng này để tạo đối tượng HomePage
        loginPage = new LoginPage(driver);

    }

    @Test
    public void TC01() throws Exception {
        mailPage.openMailPage();
        String originalWindowHandle = driver.getWindowHandle();
        email = mailPage.getMail();
        driver.switchTo().newWindow(WindowType.TAB);
        homePage.openhomePage();
        homePage.clickTab("Register");
        registerPage.register(email, password, "123456789", "123456789");
        driver.switchTo().window(originalWindowHandle);
        driver.navigate().refresh();
        mailPage.verifyMail();

    }

    @Test
    public void TC02() throws Exception {
        TC01();
        driver.switchTo().newWindow(WindowType.TAB);
        homePage.openhomePage();
        homePage.clickTab("Login");
        loginPage.login(email, password);
        homePage.clickTab("Timetable");
        

    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
