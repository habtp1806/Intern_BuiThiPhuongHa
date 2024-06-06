package chapter5.pages;

import chapter5.base.BaseSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BookTicketPage extends BaseSetup {
    private By departDate = By.xpath("//select[@name='Date']");
    private By ticketAmount = By.xpath("//select[@name='TicketAmount']");
    private By bookTicketBtn = By.xpath("//input[@value='Book ticket']");
    private By bookTicketTable = By.xpath("//table[@class='MyTable WideTable']");

    public BookTicketPage(WebDriver driver) {
        this.driver = driver;
    }

    public void bookTicket() {
        waitForElementToBeVisible(departDate, 10);
        selectDepartDateNextWeek();
        selectTicketAmount("2");
        clickBookTicket();
        isConfirmationPage();

    }

    public void selectDepartDate(String date) {
        Select dateDropdown = new Select(driver.findElement(departDate));
        dateDropdown.selectByVisibleText(date);

    }

    public void selectTicketAmount(String amount) {
        Select amountDropdown = new Select(driver.findElement(ticketAmount));
        amountDropdown.selectByVisibleText(amount);
    }

    public void selectDepartDateNextWeek() {
        LocalDate nextWeekDate = LocalDate.now().plusDays(7);
        String formattedDate = nextWeekDate.format(DateTimeFormatter.ofPattern("M/d/yyyy"));
        selectDepartDate(formattedDate);
    }

    public String getSelectedDepartDate() {
        Select dateDropdown = new Select(driver.findElement(departDate));
        return dateDropdown.getFirstSelectedOption().getText();
    }

    public int calculateTotalPrice(int ticketAmount) {
        return ticketAmount * 90000;
    }

    public void clickBookTicket() {
        WebElement bookBtn = driver.findElement(bookTicketBtn);
        if (bookBtn.isDisplayed()) {
            // Scroll to the login button
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", bookBtn);

            // Click on the login button
            bookBtn.click();
        }

    }

    public String getPageTitle() {
        return driver.getTitle();
    }


    public boolean isConfirmationPage() {
        String expectedTitle = "Confirmation Page Title";
        String actualTitle = getPageTitle();
        return actualTitle.equals(expectedTitle);
    }

    public boolean verifySelectedBooking(String from, String to, String seatType, String bookingDate, String expriedDate, String amount, String total) {
        // get infor of table
        WebElement table = driver.findElement(bookTicketTable);
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
                String actualToatal = cells.get(7).getText();

                if (actualDepartStation.equals(from) && actualArriveStation.equals(to) && actualSeatType.equals(seatType)
                        && actualBookingDate.equals(bookingDate) && actualExpriedDate.equals(expriedDate) && actualAmount.equals(amount) && actualToatal.equals(total)) {
                    return true;
                }
            }
        }
        return false;
    }

}
