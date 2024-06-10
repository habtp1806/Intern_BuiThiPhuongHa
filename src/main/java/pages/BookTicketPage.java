package pages;

import base.WebDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BookTicketPage {
    private By departDate = By.xpath("//select[@name='Date']");
    private By ticketAmount = By.xpath("//select[@name='TicketAmount']");
    private By bookTicketBtn = By.xpath("//input[@value='Book ticket']");
    private By bookTicketTable = By.xpath("//table[@class='MyTable WideTable']");
    private By arriveAt = By.xpath("//select[@name='ArriveStation']");
    private By departFrom = By.xpath("//select[@name='DepartStation']");
    private By typeSeat = By.xpath("//select[@name='SeatType']");

    public void bookTicket(String departDate, String departFrom, String ariveAt, String seatType, String ticketAmount) {
        if (departDate != null) {
            selectDepartDate(departDate);
        }
        selectArriveAt(ariveAt);
        selectDepartFrom(departFrom);
        selectTicketAmount(ticketAmount);
        selectTypeSeat(seatType);
        clickBookTicket();
        isConfirmationPage();

    }

    public void selectDepartDate(String date) {
        Select dateDropdown = new Select(WebDriverConfig.driver.findElement(departDate));
        dateDropdown.selectByVisibleText(date);

    }

    public void selectTicketAmount(String amount) {
        Select amountDropdown = new Select(WebDriverConfig.driver.findElement(ticketAmount));
        amountDropdown.selectByVisibleText(amount);
    }


    public String getSelectedDepartDate() {
        Select dateDropdown = new Select(WebDriverConfig.driver.findElement(departDate));
        return dateDropdown.getFirstSelectedOption().getText();
    }

    public String selectArriveAt(String arriveStation) {
        Select arriveAtDropdown = new Select(WebDriverConfig.driver.findElement(arriveAt));
        return arriveAtDropdown.getFirstSelectedOption().getText();
    }

    public String selectDepartFrom(String departStation) {
        Select arriveAtDropdown = new Select(WebDriverConfig.driver.findElement(departFrom));
        return arriveAtDropdown.getFirstSelectedOption().getText();
    }


    public String selectTypeSeat(String seatType) {
        Select seatTypeDropdown = new Select(WebDriverConfig.driver.findElement(typeSeat));
        return seatTypeDropdown.getFirstSelectedOption().getText();
    }


    public void clickBookTicket() {
        WebElement bookBtn = WebDriverConfig.driver.findElement(bookTicketBtn);
        if (bookBtn.isDisplayed()) {
            // Scroll to the login button
            ((JavascriptExecutor) WebDriverConfig.driver).executeScript("arguments[0].scrollIntoView(true);", bookBtn);

            // Click on the login button
            bookBtn.click();
        }

    }

    public String getPageTitle() {
        return WebDriverConfig.driver.getTitle();
    }


    public boolean isConfirmationPage() {
        String expectedTitle = "Confirmation Page Title";
        String actualTitle = getPageTitle();
        return actualTitle.equals(expectedTitle);
    }

    public boolean verifySelectedBooking(String from, String to, String seatType, String bookingDate, String expriedDate, String amount) {
        // get infor of table
        WebElement table = WebDriverConfig.driver.findElement(bookTicketTable);
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
                //
                System.out.println("Actual Depart Station: " + actualDepartStation);
                System.out.println("Actual Arrive Station: " + actualArriveStation);
                System.out.println("Actual Seat Type: " + actualSeatType);
                //  System.out.println("Actual Depart Date: " + actualDepartDate);
                System.out.println("Actual Booking Date: " + actualBookingDate);
                System.out.println("Actual Expired Date: " + actualExpriedDate);
                System.out.println("Actual Amount: " + actualAmount);
                //  System.out.println("Actual Total: " + actualTotal);
                if (actualDepartStation.equals(from) && actualArriveStation.equals(to) && actualSeatType.equals(seatType)
                        && actualBookingDate.equals(bookingDate) && actualExpriedDate.equals(expriedDate) && actualAmount.equals(amount)) {
                    return true;
                }
            }
        }
        return false;
    }

}