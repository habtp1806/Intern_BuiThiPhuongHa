package testcases;

import org.testng.annotations.Test;
import pages.BasePage;
import pages.BookTicketPage;
import pages.LoginPage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.testng.AssertJUnit.assertTrue;

public class Chapter10_BookTicketTest extends BaseTest {
    private BasePage basePage = new BasePage();
    private LoginPage loginPage = new LoginPage();
    private BookTicketPage bookTicketPage = new BookTicketPage();
    private String departDate = LocalDate.now().plusDays(12).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
    private String expiredDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
    private String departFrom = "Nha Trang";
    private String arriveAt = "Huế";
    private String seat = "Soft bed with air conditioner";
    private String amount = "1";

    @Test
    public void TC12() throws Exception {
        System.out.println("User can book 1 ticket at a time");
        basePage.openHomePage();
        basePage.clickTab("Login");
        loginPage.login(email, password);
        basePage.clickTab("Book ticket");
        bookTicketPage.bookTicket(departDate, departFrom, arriveAt, seat, amount);
        boolean result = bookTicketPage.verifySelectedBooking(departFrom, arriveAt, seat, "", "", amount);
        assertTrue("The booking should be verified correctly", result);
    }

    public void TC13() throws Exception {
        System.out.println("User can book many tickets at a time");
        basePage.openHomePage();
        basePage.clickTab("Login");
        loginPage.login(email, password);
        basePage.clickTab("Book ticket");
        bookTicketPage.bookTicket(departDate, departFrom, arriveAt, seat, amount);
        boolean result = bookTicketPage.verifySelectedBooking(departFrom, arriveAt, seat, "", "", amount);
        assertTrue("The booking should be verified correctly", result);
    }
}
