package testcases.Chapter10;

import enums.RailwayStation;
import enums.RailwayTab;
import enums.SeatType;
import model.BookTicket;
import model.User;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;
import testcases.base.BaseTest;
import utils.listeners.ReportListener;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static base.DriverManager.navigateToRailWay;

@Listeners(ReportListener.class)
public class CancelBookingTest extends BaseTest {
    private final BasePage basePage = new BasePage();
    private final LoginPage loginPage = new LoginPage();
    private final BookTicketPage bookTicketPage = new BookTicketPage();
    private final MyTicketPage myTicketPage = new MyTicketPage();

    private final String bookingDate = LocalDate.now().plusDays(7).format(DateTimeFormatter.ofPattern("M/d/yyyy"));


    @Test(description = "User can cancel a ticket")
    public void verifyCancelTicket() {
        BookTicket ticket = new BookTicket(bookingDate, RailwayStation.NHA_TRANG, RailwayStation.SAI_GON, SeatType.SOFT_SEAT_AIR_CONDITIONER, "1");
        User user = new User(email, password);
        navigateToRailWay();
        BasePage.clickTab(RailwayTab.LOGIN);
        loginPage.login(user);
        BasePage.clickTab(RailwayTab.BOOK_TICKET);
        bookTicketPage.bookTicket(ticket);
        BasePage.clickTab(RailwayTab.MY_TICKET);
        myTicketPage.cancelTicket(ticket);
        myTicketPage.confirmCancel();
        myTicketPage.doesTicketDisappear(ticket);

    }
}
