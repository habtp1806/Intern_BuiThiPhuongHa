package testcases;

import base.WebDriverConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.*;

import java.time.Duration;

public class Chapter3Test extends BaseTest {
    private RegisterPage registerPage = new RegisterPage();
    private MailPage mailPage = new MailPage();
    private BasePage basePage = new BasePage();
    private LoginPage loginPage = new LoginPage();
    private TicketPricePage ticketPricePage = new TicketPricePage();
    private BookTicketPage bookTicketPage = new BookTicketPage();
    private String email;
    private String password = "123456789";
    private String amount = "2";
    private String arriveAt = "Phan Thiết";

    @Test
    public void chap3() throws Exception {
        mailPage.openMailPage();
        String originalWindowHandle = WebDriverConfig.driver.getWindowHandle();
        email = mailPage.getMail();
        WebDriverConfig.driver.switchTo().newWindow(WindowType.TAB);
        basePage.openHomePage();
        basePage.clickTab("Register");
        registerPage.register(email, password, password, password);
        WebDriverConfig.driver.switchTo().window(originalWindowHandle);
        WebDriverConfig.driver.navigate().refresh();
        mailPage.verifyMail();
        WebDriverConfig.driver.switchTo().newWindow(WindowType.TAB);
        basePage.openHomePage();
        basePage.clickTab("Login");
        loginPage.login(email, password);
        basePage.clickTab("Book ticket");
        bookTicketPage.bookTicket(null, "", arriveAt, "Soft seat", "2");
    }

}
