package testcases;

import chapter5.base.BaseSetup;
import chapter5.pages.*;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookTicketTest extends BaseSetup {

    private RegisterPage registerPage;
    private MailPage mailPage;
    private HomePage homePage;
    private LoginPage loginPage;
    private TimeTablePage timeTablePage;
    private TicketPricePage ticketPricePage;
    private BookTicketPage bookTicketPage;
    private String email;
    private String password = "123456789";
    private String departStation = "Sài Gòn";
    private String arriveStation = "Đà Nẵng";
    private String seatType = "Soft seat";
    private String bookingDate = LocalDate.now().format(DateTimeFormatter.ofPattern("M/d/yyyy"));
    private String expiredDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
    private String amount = "2";
    private String total = "11";


    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        registerPage = new RegisterPage(driver);
        mailPage = new MailPage(driver);
        homePage = new HomePage(driver); // Thêm dòng này để tạo đối tượng HomePage
        loginPage = new LoginPage(driver);
        timeTablePage = new TimeTablePage(driver);
        ticketPricePage = new TicketPricePage(driver);
        bookTicketPage = new BookTicketPage(driver);

    }

    @Test
    public void TC01() throws Exception {
        mailPage.openMailPage();
        String originalWindowHandle = driver.getWindowHandle();
        email = mailPage.getMail();
        driver.switchTo().newWindow(WindowType.TAB);
        homePage.openhomePage();
        homePage.clickTab("Register");
        registerPage.register(email, password, "123456789", "123456789");
        driver.switchTo().window(originalWindowHandle);
        driver.navigate().refresh();
        mailPage.verifyMail();

    }

    @Test
    public void TC02() throws Exception {
        TC01();
        driver.switchTo().newWindow(WindowType.TAB);
        homePage.openhomePage();
        homePage.clickTab("Login");
        loginPage.login(email, password);
        homePage.clickTab("Timetable");
        timeTablePage.clickCheckPrice(departStation, arriveStation);
        ticketPricePage.chooseTypeSeat(seatType);
        bookTicketPage.bookTicket();
        // String selectedDepartDate = bookTicketPage.getSelectedDepartDate();
        bookTicketPage.verifySelectedBooking("Phan Thiết", arriveStation, seatType, bookingDate, expiredDate, amount, total);

    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
