package testcases.Chapter;

import base.DriverManager;
import enums.RailwayStation;
import enums.RailwayTab;
import enums.SeatType;
import model.BookTicket;
import model.User;
import org.openqa.selenium.*;
import org.testng.annotations.Test;
import pages.*;
import testcases.base.BaseTest;

import static base.DriverManager.*;

public class Chapter3Test extends BaseTest {
    private final RegisterPage registerPage = new RegisterPage();
    private final MailPage mailPage = new MailPage();
    private final BasePage basePage = new BasePage();
    private final LoginPage loginPage = new LoginPage();
    private final TicketPricePage ticketPricePage = new TicketPricePage();
    private final BookTicketPage bookTicketPage = new BookTicketPage();
    private String email;
    private final String password = "123456789";
    private final String amount = "2";
    private final String arriveAt = "Phan Thiết";

    @Test
    public void chap3() {
        User user = new User(email, password);
        BookTicket ticket = new BookTicket(null, null, RailwayStation.PHAN_THIET, SeatType.SOFT_SEAT, "2");
        navigateToRailWay();
        String originalWindowHandle = DriverManager.driver.getWindowHandle();
        email = mailPage.getMail();
        DriverManager.driver.switchTo().newWindow(WindowType.TAB);
        navigateToRailWay();
        BasePage.clickTab(RailwayTab.REGISTER);
        registerPage.register(email, password, password, password);
        DriverManager.driver.switchTo().window(originalWindowHandle);
        DriverManager.driver.navigate().refresh();
        mailPage.getConFirmLinkMail();
        DriverManager.driver.switchTo().newWindow(WindowType.TAB);
        navigateToRailWay();
        BasePage.clickTab(RailwayTab.LOGIN);
        loginPage.login(user);
        BasePage.clickTab(RailwayTab.BOOK_TICKET);
        bookTicketPage.bookTicket(ticket);
    }

}
