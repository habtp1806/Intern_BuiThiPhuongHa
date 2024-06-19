package testcases.Chapter10;

import enums.RailwayStation;
import enums.RailwayTab;
import enums.SeatType;
import model.BookTicket;
import model.User;
import org.testng.annotations.Test;
import pages.*;
import testcases.base.BaseTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static base.DriverManager.openHomePage;

public class CancelBookingTest extends BaseTest {
    private BasePage basePage = new BasePage();
    private LoginPage loginPage = new LoginPage();
    private BookTicketPage bookTicketPage = new BookTicketPage();
    private MyTicketPage myTicketPage = new MyTicketPage();

    private String bookingDate = LocalDate.now().plusDays(7).format(DateTimeFormatter.ofPattern("M/d/yyyy"));


    @Test(description = "User can cancel a ticket")
    public void verifyCancelTicket() {
        BookTicket ticket = new BookTicket(bookingDate, RailwayStation.NHA_TRANG, RailwayStation.SAI_GON, SeatType.SOFT_SEAT_AIR_CONDITIONER, "5");
        User user = new User(email, password);
        openHomePage();
        basePage.clickTab(RailwayTab.LOGIN.getValue());
        loginPage.login(user);
        basePage.clickTab(RailwayTab.BOOK_TICKET.getValue());
        bookTicketPage.bookTicket(ticket);
        basePage.clickTab(RailwayTab.MY_TICKET.getValue());
        myTicketPage.cancelTicket(ticket);
        myTicketPage.confirmCancel();
        myTicketPage.checkTicketDisappear(ticket);


    }
}
