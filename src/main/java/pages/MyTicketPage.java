package pages;

import base.DriverManager;
import model.BookTicket;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SeleniumHelper;

import java.time.Duration;

public class MyTicketPage {
    private String xpathTicket = "//table[@class='MyTable']//tr[td[text()='%s' and following-sibling::td[text()='%s'" +
            " and following-sibling::td[text()='%s' and following-sibling::td[text()='%s' " +
            "and following-sibling::td[text()='%s']]]]]]//input[contains(@onclick, 'Delete')]";

    public void cancelTicket(BookTicket ticket) {
        By ticketLocator = By.xpath(String.format(xpathTicket, ticket.getDepartStation().getValue(), ticket.getArrivalStation().getValue(),
                ticket.getSeatType().getValue(), ticket.getDepartDate(), ticket.getTicketAmount()));
        SeleniumHelper.clickElement(ticketLocator);
    }

    public void confirmCancel() {
        DriverManager.driver.switchTo().alert().accept();
    }

    public boolean checkTicketDisappear(BookTicket ticket) {
        String xpathTicket = String.format("//table[@class='MyTable']//tr[td[text()='%s' and following-sibling::td[text()='%s' and following-sibling::td[text()='%s' and following-sibling::td[text()='%s' and following-sibling::td[text()='%s']]]]]]//input[contains(@onclick, 'Delete')]",
                ticket.getDepartStation().getValue(),
                ticket.getArrivalStation().getValue(),
                ticket.getSeatType().getValue(),
                ticket.getDepartDate(),
                ticket.getTicketAmount());

        WebDriverWait wait = new WebDriverWait(DriverManager.driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathTicket)));
        return !element.isDisplayed();
    }
}
