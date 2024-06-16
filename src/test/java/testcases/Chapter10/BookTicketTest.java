package testcases.Chapter10;

import enums.RailwayStation;
import enums.SeatType;
import model.BookTicket;
import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import testcases.base.BaseTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.testng.AssertJUnit.assertTrue;

public class BookTicketTest extends BaseTest {
    private BasePage basePage = new BasePage();
    private LoginPage loginPage = new LoginPage();
    private BookTicketPage bookTicketPage = new BookTicketPage();
    private TimeTablePage timeTablePage = new TimeTablePage();
    private TicketPricePage ticketPricePage = new TicketPricePage();
    User user = new User(email, password);

    @Test(description = "User can book 1 ticket at a time")
    public void TC12() {
        BookTicket ticket = new BookTicket(calculateNextDepartDate(12), RailwayStation.NHA_TRANG.getValue(), RailwayStation.HUE.getValue(), SeatType.SOFT_BED_AIR_CONDITIONER.getValue(), "1");
        basePage.openHomePage();
        basePage.clickTab("Login");
        loginPage.login(user);
        basePage.clickTab("Book ticket");
        bookTicketPage.bookTicket(ticket);
        boolean result = bookTicketPage.verifySelectedBooking(RailwayStation.NHA_TRANG.getValue(), RailwayStation.HUE.getValue(), SeatType.SOFT_BED_AIR_CONDITIONER.getValue(), null, null, "1");
        assertTrue("The booking should be verified correctly", result);
    }

    @Test(description = "User can book many tickets at a time")
    public void TC13() {
        BookTicket ticket = new BookTicket(calculateNextDepartDate(25), RailwayStation.NHA_TRANG.getValue(), RailwayStation.SAI_GON.getValue(), SeatType.SOFT_SEAT_AIR_CONDITIONER.getValue(), "5");
        basePage.openHomePage();
        basePage.clickTab("Login");
        loginPage.login(user);
        basePage.clickTab("Book ticket");
        bookTicketPage.bookTicket(ticket);
        boolean result = bookTicketPage.verifySelectedBooking(RailwayStation.NHA_TRANG.getValue(), RailwayStation.SAI_GON.getValue(), SeatType.SOFT_SEAT_AIR_CONDITIONER.getValue(), null, null, "5");
        assertTrue("The booking should be verified correctly", result);
    }

    @Test(description = "User can check price of ticket from Timetable")
    public void TC14() {
        basePage.openHomePage();
        basePage.clickTab("Login");
        loginPage.login(user);
        basePage.clickTab("Timetable");
        timeTablePage.clickCheckPrice("Đà Nẵng", "Sài Gòn");
        ticketPricePage.verifyTitleTable("Đà Nẵng", "Sài Gòn");
        Assert.assertEquals(ticketPricePage.getPriceOfSeatType("HS"), 310000, "Price of Hard seat is wrong");
        Assert.assertEquals(ticketPricePage.getPriceOfSeatType("SS"), 335000, "Price of Soft seat is wrong");
        Assert.assertEquals(ticketPricePage.getPriceOfSeatType("SSC"), 360000, "Price of Soft seat with air conditioner is wrong");
        Assert.assertEquals(ticketPricePage.getPriceOfSeatType("HB"), 410000, "Price of Hard bed is wrong");
        Assert.assertEquals(ticketPricePage.getPriceOfSeatType("SB"), 460000, "Price of Soft bed is wrong");
        Assert.assertEquals(ticketPricePage.getPriceOfSeatType("SBC"), 510000, "Price of Soft bed with air conditioner is wrong");

    }

    @Test(description = "User can book ticket from Timetable")
    public void TC15() {
        BookTicket ticket = new BookTicket(calculateNextDepartDate(10), null, null, null, "5");
        basePage.openHomePage();
        basePage.clickTab("Login");
        loginPage.login(user);
        basePage.clickTab("Timetable");
        timeTablePage.clickBookTicket("Quảng Ngãi", "Huế");
        bookTicketPage.bookTicket(ticket);
        boolean result = bookTicketPage.verifySelectedBooking("Quảng Ngãi", "Huế", null, null, null, "5");
        assertTrue("The booking should be verified correctly", result);
    }

    private String calculateNextDepartDate(int daysToAdd) {
        LocalDate departDate = LocalDate.now().plusDays(daysToAdd);
        return departDate.format(DateTimeFormatter.ofPattern("M/d/yyyy"));
    }
}
