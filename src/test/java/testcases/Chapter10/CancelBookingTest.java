package testcases.Chapter10;

import enums.RailwayStation;
import enums.SeatType;
import model.BookTicket;
import model.User;
import org.testng.annotations.Test;
import pages.*;
import testcases.base.BaseTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CancelBookingTest extends BaseTest {
    private BasePage basePage = new BasePage();
    private LoginPage loginPage = new LoginPage();
    private BookTicketPage bookTicketPage = new BookTicketPage();

    private String bookingDate = LocalDate.now().plusDays(7).format(DateTimeFormatter.ofPattern("M/d/yyyy"));


    @Test(description = "User can cancel a ticket")
    public void TC16() {
        BookTicket ticket = new BookTicket(bookingDate, RailwayStation.NHA_TRANG.getValue(), RailwayStation.SAI_GON.getValue(), SeatType.SOFT_SEAT_AIR_CONDITIONER.getValue(), "5");
        User user = new User(email, password);
        basePage.openHomePage();
        basePage.clickTab("Login");
        loginPage.login(user);
        basePage.clickTab("Book ticket");
        bookTicketPage.bookTicket(ticket);
        basePage.clickTab("My ticket");

    }
}
