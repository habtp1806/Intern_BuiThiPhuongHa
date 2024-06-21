package testcases.Chapter10;

import enums.RailwayStation;
import enums.RailwayTab;
import enums.SeatType;
import model.BookTicket;
import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import testcases.base.BaseTest;
import utils.DateUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static base.DriverManager.navigateToRailWay;
import static org.testng.AssertJUnit.assertTrue;

public class BookTicketTest extends BaseTest {
    private final BasePage basePage = new BasePage();
    private final LoginPage loginPage = new LoginPage();
    private final BookTicketPage bookTicketPage = new BookTicketPage();
    private final TimeTablePage timeTablePage = new TimeTablePage();
    private final TicketPricePage ticketPricePage = new TicketPricePage();
    User user = new User(email, password);

    @Test(description = "User can book 1 ticket at a time")
    public void verifyBookingATicket() {
        String nextDepartDate = DateUtils.calculateNextDepartDate(12);
        BookTicket ticket = new BookTicket(nextDepartDate, RailwayStation.NHA_TRANG, RailwayStation.HUE, SeatType.SOFT_BED_AIR_CONDITIONER, "1");
        navigateToRailWay();
        BasePage.clickTab(RailwayTab.LOGIN.getValue());
        loginPage.login(user);
        BasePage.clickTab(RailwayTab.BOOK_TICKET.getValue());
        bookTicketPage.bookTicket(ticket);
        boolean result = bookTicketPage.verifySelectedBooking(RailwayStation.NHA_TRANG.getValue(), RailwayStation.HUE.getValue(), SeatType.SOFT_BED_AIR_CONDITIONER.getValue(), null, null, "1");
        assertTrue("The booking should be verified correctly", result);
    }

    @Test(description = "User can book many tickets at a time")
    public void verifyBookingMultipleTickets() {
        String nextDepartDate = DateUtils.calculateNextDepartDate(25);
        BookTicket ticket = new BookTicket(nextDepartDate, RailwayStation.NHA_TRANG, RailwayStation.SAI_GON, SeatType.SOFT_SEAT_AIR_CONDITIONER, "5");
        navigateToRailWay();
        BasePage.clickTab(RailwayTab.LOGIN.getValue());
        loginPage.login(user);
        BasePage.clickTab(RailwayTab.BOOK_TICKET.getValue());
        bookTicketPage.bookTicket(ticket);
        boolean result = bookTicketPage.verifySelectedBooking(RailwayStation.NHA_TRANG.getValue(), RailwayStation.SAI_GON.getValue(), SeatType.SOFT_SEAT_AIR_CONDITIONER.getValue(), null, null, "5");
        assertTrue("The booking should be verified correctly", result);
    }

    @Test(description = "User can check price of ticket from Timetable")
    public void verifyTicketPriceFromTimetable() {
        navigateToRailWay();
        BasePage.clickTab(RailwayTab.LOGIN.getValue());
        loginPage.login(user);
        BasePage.clickTab(RailwayTab.TIMETABLE.getValue());
        timeTablePage.clickCheckPrice(RailwayStation.DA_NANG.getValue(), RailwayStation.SAI_GON.getValue());
        Assert.assertTrue(ticketPricePage.verifyTitleTable(RailwayStation.DA_NANG.getValue(), RailwayStation.SAI_GON.getValue()), "Title is not displayed");
        Assert.assertEquals(ticketPricePage.getPriceOfSeatType("HS"), 310000, "Price of Hard seat is wrong");
        Assert.assertEquals(ticketPricePage.getPriceOfSeatType("SS"), 335000, "Price of Soft seat is wrong");
        Assert.assertEquals(ticketPricePage.getPriceOfSeatType("SSC"), 360000, "Price of Soft seat with air conditioner is wrong");
        Assert.assertEquals(ticketPricePage.getPriceOfSeatType("HB"), 410000, "Price of Hard bed is wrong");
        Assert.assertEquals(ticketPricePage.getPriceOfSeatType("SB"), 460000, "Price of Soft bed is wrong");
        Assert.assertEquals(ticketPricePage.getPriceOfSeatType("SBC"), 510000, "Price of Soft bed with air conditioner is wrong");

    }

    @Test(description = "User can book ticket from Timetable")
    public void verifyBookingFromTimetable() {
        String numTicket = "5";
        String nextDepartDate = DateUtils.calculateNextDepartDate(10);
        BookTicket ticket = new BookTicket(nextDepartDate, null, null, null, numTicket);
        navigateToRailWay();
        BasePage.clickTab(RailwayTab.LOGIN.getValue());
        loginPage.login(user);
        BasePage.clickTab(RailwayTab.TIMETABLE.getValue());
        timeTablePage.clickBookTicket(RailwayStation.QUANG_NGAI.getValue(), RailwayStation.HUE.getValue());
        bookTicketPage.bookTicket(ticket);
        boolean result = bookTicketPage.verifySelectedBooking(RailwayStation.QUANG_NGAI.getValue(), RailwayStation.HUE.getValue(), null, null, null, numTicket);
        assertTrue("The booking should be verified correctly", result);
    }

}
