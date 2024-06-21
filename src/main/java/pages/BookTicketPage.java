package pages;

import base.Config;
import base.DriverManager;
import enums.RailwayStation;
import enums.SeatType;
import model.BookTicket;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.SeleniumHelper;

import java.util.List;

public class BookTicketPage extends BasePage {
    private final By bookTicketBtnXPath = By.xpath("//input[@value='Book ticket']");
    private final By bookTicketTableXPath = By.xpath("//table[@class='MyTable WideTable']");

    public void bookTicket(BookTicket ticket) {
        selectDepartDate(ticket.getDepartDate());
        selectDepartFrom(ticket.getDepartStation());
        enterTypeSeat(ticket.getSeatType());
        selectTicketAmount(ticket.getTicketAmount());
        enterArriveAt(ticket.getArrivalStation());
        clickBookTicket();
        isConfirmationPageDisplayed();
    }

    private By getXPathByName(String name) {
        return By.xpath(String.format("//select[@name='%s']", name));
    }

    public void selectDepartDate(String date) {
        SeleniumHelper.selectByVisibleText(getXPathByName("Date"), date);
    }

    public void selectTicketAmount(String amount) {
        SeleniumHelper.selectByVisibleText(getXPathByName("TicketAmount"), amount);
    }

    public String getSelectedDepartDate() {
        Select dateDropdown = new Select(DriverManager.driver.findElement(getXPathByName("Date")));
        return dateDropdown.getFirstSelectedOption().getText();
    }

    public void selectArriveAt(RailwayStation arriveStation) {
        SeleniumHelper.selectByVisibleText(getXPathByName("ArriveStation"), arriveStation.getValue());
    }

    public void enterArriveAt(RailwayStation arriveStation) {
        SeleniumHelper.enter(getXPathByName("ArriveStation"), arriveStation.getValue());
    }

    public void selectDepartFrom(RailwayStation departStation) {
        SeleniumHelper.selectByVisibleText(getXPathByName("DepartStation"), departStation.getValue());
    }


    public void selectTypeSeat(SeatType seatType) {
        SeleniumHelper.selectByVisibleText(getXPathByName("SeatType"), seatType.getValue());
    }

    public void enterTypeSeat(SeatType seatType) {
        SeleniumHelper.enter(getXPathByName("SeatType"), seatType.getValue());
    }

    public void clickBookTicket() {
        SeleniumHelper.scrollToElement(bookTicketBtnXPath);
        SeleniumHelper.clickElement(bookTicketBtnXPath);
    }

    public String getPageTitle() {
        return DriverManager.driver.getTitle();
    }


    public boolean isConfirmationPageDisplayed() {
        String expectedTitle = "Confirmation Page Title";
        String actualTitle = getPageTitle();
        return actualTitle.equals(expectedTitle);
    }

    public boolean verifySelectedBooking(String from, String to, String seatType, String bookingDate, String expriedDate, String amount) {
        WebElement table = DriverManager.driver.findElement(bookTicketTableXPath);
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() >= 7) {
                String actualDepartStation = cells.get(0).getText();

                String actualArriveStation = cells.get(1).getText();
                String actualSeatType = cells.get(2).getText();
                String actualDeparteDate = cells.get(3).getText();
                String actualBookingDate = cells.get(4).getText();
                String actualExpriedDate = cells.get(5).getText();
                String actualAmount = cells.get(6).getText();

                if ((from == null || actualDepartStation.equals(from)) &&
                        (to == null || actualArriveStation.equals(to)) &&
                        (seatType == null || actualSeatType.equals(seatType)) &&
                        (bookingDate == null || bookingDate.isEmpty() || actualBookingDate.equals(bookingDate)) &&
                        (expriedDate == null || expriedDate.isEmpty() || actualExpriedDate.equals(expriedDate)) &&
                        (amount == null || amount.isEmpty() || actualAmount.equals(amount))) {
                    return true;
                }
            }
        }
        return false;
    }

}
