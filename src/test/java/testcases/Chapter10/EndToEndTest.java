package testcases.Chapter10;

import base.Config;
import enums.RailwayStation;
import enums.RailwayTab;
import enums.SeatType;
import model.BookTicket;
import model.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;
import testcases.base.BaseTest;
import utils.DateUtils;
import utils.listeners.ReportListener;

import static base.DriverManager.*;
import static org.testng.AssertJUnit.assertTrue;

@Listeners(ReportListener.class)
public class EndToEndTest extends BaseTest {
    private final RegisterPage registerPage = new RegisterPage();
    private final MailPage mailPage = new MailPage();
    private final BasePage basePage = new BasePage();
    private final HomePage homePage = new HomePage();
    private final LoginPage loginPage = new LoginPage();
    private final BookTicketPage bookTicketPage = new BookTicketPage();
    private final TimeTablePage timeTablePage = new TimeTablePage();
    private final TicketPricePage ticketPricePage = new TicketPricePage();
    private final MyTicketPage myTicketPage = new MyTicketPage();
    String nextDepartDate = DateUtils.calculateNextDepartDate(10);

    BookTicket ticket = new BookTicket(nextDepartDate, RailwayStation.QUANG_NGAI, RailwayStation.HUE, SeatType.SOFT_SEAT_AIR_CONDITIONER, "1");

    @Test(description = "End-to-end test scenario covering registration, login, booking, and logout")
    public void completeRailwayAppFlow() {
        // Register a new account
        navigateToRailWay();
        BasePage.clickLink("create an account");
        String registerWindow = getWindowHandle();
        BasePage.openNewTab(Config.getProperty("tempmail.url"));
        String email = mailPage.getMail();
        User user = new User(email, password);
        String mailWindow = getWindowHandle();
        switchToWindow(registerWindow);
        BasePage.clickTab(RailwayTab.REGISTER);
        registerPage.register(email, password, confirmPassword, pid);
        assertTrue("Confirm message is not present after register successfully.", registerPage.isConfirmationSuccessful());
        switchToWindow(mailWindow);
        refreshPage();
        mailPage.getConFirmLinkMail();
        BasePage.switchToRemainingTab(registerWindow, mailWindow);
        assertTrue("Registration Confirmed! You can now log in to the site", registerPage.isConfirmationRegister());
        // Login with registered account
        navigateToRailWay();
        BasePage.clickTab(RailwayTab.LOGIN);
        loginPage.login(user);
        // Book a ticket
        BasePage.clickTab(RailwayTab.TIMETABLE);
        timeTablePage.clickBookTicket(ticket.getDepartStation().getValue(), ticket.getArrivalStation().getValue());
        bookTicketPage.bookTicket(ticket);
        boolean bookingResult = bookTicketPage.verifySelectedBooking(ticket.getDepartStation().getValue(), ticket.getArrivalStation().getValue(), SeatType.SOFT_SEAT_AIR_CONDITIONER.getValue(), null, null, "1");
        Assert.assertTrue(bookingResult, "The booking should be verified correctly");
        BasePage.clickTab(RailwayTab.MY_TICKET);
        //Cancel ticket and verify
        myTicketPage.cancelTicket(ticket);
        myTicketPage.confirmCancel();
        myTicketPage.doesTicketDisappear(ticket);
        // Logout
        BasePage.clickTab(RailwayTab.LOG_OUT);
        Assert.assertFalse(BasePage.isTabDisplayed(RailwayTab.LOG_OUT.getValue()), "Logout was not successful");
    }

}
