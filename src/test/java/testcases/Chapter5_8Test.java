package testcases;

import base.WebDriverConfig;
import org.openqa.selenium.WindowType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.*;

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

    @BeforeTest
    public void Register() throws Exception {

        mailPage.openMailPage();
        email = mailPage.getMail();
        WebDriverConfig.driver.switchTo().newWindow(WindowType.TAB);
        basePage.openHomePage();
        basePage.clickTab("Register");
        registerPage.register(email, password, password, pid);
        mailPage.switchToEmail();
        basePage.refreshPage();
        mailPage.verifyMail();
    }

    @Test
    public void bookTicket() throws Exception {

        WebDriverConfig.driver.switchTo().newWindow(WindowType.TAB);
        basePage.openHomePage();
        basePage.clickTab("Login");
        loginPage.login(email, password);
        basePage.clickTab("Timetable");
        timeTablePage.clickCheckPrice(departStation, arriveStation);
        ticketPricePage.chooseTypeSeat(seatType);
        bookTicketPage.bookTicket(bookingDate, "", "", "", amount);
        boolean result = bookTicketPage.verifySelectedBooking(departStation, arriveStation, seatType, bookingDate, expiredDate, amount);
        assertTrue("The booking should be verified correctly", result);
    }
}
