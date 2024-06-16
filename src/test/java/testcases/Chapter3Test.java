package testcases;

import base.DriverManager;
import enums.RailwayStation;
import enums.SeatType;
import model.BookTicket;
import model.User;
import org.openqa.selenium.*;
import org.testng.annotations.Test;
import pages.*;
import testcases.base.BaseTest;

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
    public void chap3() {
        User user = new User(email, password);
        BookTicket ticket = new BookTicket(null, null, RailwayStation.PHAN_THIET.getValue(), SeatType.SOFT_SEAT.getValue(), "2");
        mailPage.openMailPage();
        String originalWindowHandle = DriverManager.driver.getWindowHandle();
        email = mailPage.getMail();
        DriverManager.driver.switchTo().newWindow(WindowType.TAB);
        basePage.openHomePage();
        basePage.clickTab("Register");
        registerPage.register(email, password, password, password);
        DriverManager.driver.switchTo().window(originalWindowHandle);
        DriverManager.driver.navigate().refresh();
        mailPage.verifyMail();
        DriverManager.driver.switchTo().newWindow(WindowType.TAB);
        basePage.openHomePage();
        basePage.clickTab("Login");
        loginPage.login(user);
        basePage.clickTab("Book ticket");
        bookTicketPage.bookTicket(ticket);
    }

}
