package pages;

import base.DriverManager;
import model.BookTicket;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.SeleniumHelper;

import java.util.List;

public class BookTicketPage extends BasePage {
    private By departDateXPath = By.xpath("//select[@name='Date']");
    private By ticketAmountXPath = By.xpath("//select[@name='TicketAmount']");
    private By bookTicketBtnXPath = By.xpath("//input[@value='Book ticket']");
    private By bookTicketTableXPath = By.xpath("//table[@class='MyTable WideTable']");
    private By arriveAtXPath = By.xpath("//select[@name='ArriveStation']");
    private By departFromXPath = By.xpath("//select[@name='DepartStation']");
    private By typeSeatXPath = By.xpath("//select[@name='SeatType']");

    public void bookTicket(BookTicket ticket) {

        if (ticket == null) {
            throw new IllegalArgumentException("BookTicket object cannot be null");
        }

        if (ticket.getDepartDate() != null) {
            selectDepartDate(ticket.getDepartDate());
        }

        if (ticket.getDepartFrom() != null) {
            selectDepartFrom(ticket.getDepartFrom());
        }


        if (ticket.getSeatType() != null) {
            selectTypeSeat(ticket.getSeatType());
        }

        if (ticket.getTicketAmount() != null) {
            selectTicketAmount(ticket.getTicketAmount());
        }
        if (ticket.getArriveAt() != null) {
            //  selectArriveAt(ticket.getArriveAt());
            enterArriveAt(ticket.getArriveAt());
        }
        clickBookTicket();
        isConfirmationPage();

    }

    public void selectDepartDate(String date) {
        SeleniumHelper.selectByVisibleText(departDateXPath, date);
    }

    public void selectTicketAmount(String amount) {
        SeleniumHelper.selectByVisibleText(ticketAmountXPath, amount);
    }

    public String getSelectedDepartDate() {
        Select dateDropdown = new Select(DriverManager.driver.findElement(departDateXPath));
        return dateDropdown.getFirstSelectedOption().getText();
    }

    public void selectArriveAt(String arriveStation) {
        SeleniumHelper.selectByVisibleText(arriveAtXPath, arriveStation);
    }

    public void enterArriveAt(String arriveStation) {
        SeleniumHelper.enterText(arriveAtXPath, arriveStation);
    }

    public void selectDepartFrom(String departStation) {
        SeleniumHelper.selectByVisibleText(departFromXPath, departStation);
    }


    public void selectTypeSeat(String seatType) {
        SeleniumHelper.selectByVisibleText(typeSeatXPath, seatType);
    }


    public void clickBookTicket() {
        SeleniumHelper.scrollToElement(bookTicketBtnXPath);
        SeleniumHelper.clickElement(bookTicketBtnXPath);
    }

    public String getPageTitle() {
        return DriverManager.driver.getTitle();
    }


    public boolean isConfirmationPage() {
        String expectedTitle = "Confirmation Page Title";
        String actualTitle = getPageTitle();
        return actualTitle.equals(expectedTitle);
    }

    public boolean verifySelectedBooking(String from, String to, String seatType, String bookingDate, String expriedDate, String amount) {
        // get infor of table
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
                // String actualToatal = cells.get(7).getText();
                System.out.println("Actual Depart Station: " + actualDepartStation);
                System.out.println("Actual Arrive Station: " + actualArriveStation);
                System.out.println("Actual Seat Type: " + actualSeatType);
                //  System.out.println("Actual Depart Date: " + actualDepartDate);
                System.out.println("Actual Booking Date: " + actualBookingDate);
                System.out.println("Actual Expired Date: " + actualExpriedDate);
                System.out.println("Actual Amount: " + actualAmount);
                //  System.out.println("Actual Total: " + actualTotal);
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
