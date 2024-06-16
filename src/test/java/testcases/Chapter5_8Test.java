package testcases;

import base.DriverManager;
import enums.RailwayStation;
import enums.SeatType;
import model.BookTicket;
import model.User;
import org.openqa.selenium.WindowType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.*;
import testcases.base.BaseTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.testng.AssertJUnit.assertTrue;

public class Chapter5_8Test extends BaseTest {

    private RegisterPage registerPage = new RegisterPage();
    private MailPage mailPage = new MailPage();
    private BasePage basePage = new BasePage();
    private LoginPage loginPage = new LoginPage();
    private TimeTablePage timeTablePage = new TimeTablePage();
    private TicketPricePage ticketPricePage = new TicketPricePage();
    private BookTicketPage bookTicketPage = new BookTicketPage();
    private String email;
    private String departStation = "Sài Gòn";
    private String arriveStation = "Đà Nẵng";
    private String seatType = "Soft seat";
    private String bookingDate = LocalDate.now().plusDays(7).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
    private String expiredDate = LocalDate.now().plusDays(10).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
    private String amount = "2";

    @BeforeMethod
    public void Register() throws Exception {

        mailPage.openMailPage();
        email = mailPage.getMail();
        DriverManager.driver.switchTo().newWindow(WindowType.TAB);
        basePage.openHomePage();
        basePage.clickTab("Register");
        registerPage.register(email, password, password, pid);
        mailPage.switchToEmail();
        basePage.refreshPage();
        mailPage.verifyMail();
    }

    @Test
    public void bookTicket() throws Exception {
        User user = new User(email, password);
        BookTicket ticket = new BookTicket(bookingDate, null, null, null, "5");
        DriverManager.driver.switchTo().newWindow(WindowType.TAB);
        basePage.openHomePage();
        basePage.clickTab("Login");
        loginPage.login(user);
        basePage.clickTab("Timetable");
        timeTablePage.clickCheckPrice(departStation, arriveStation);
        ticketPricePage.chooseTypeSeat(seatType);
        bookTicketPage.bookTicket(ticket);
        boolean result = bookTicketPage.verifySelectedBooking(departStation, arriveStation, seatType, bookingDate, expiredDate, amount);
        assertTrue("The booking should be verified correctly", result);
    }
}
