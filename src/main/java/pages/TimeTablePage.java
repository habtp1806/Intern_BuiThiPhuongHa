package pages;


import base.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.SeleniumHelper;

import static utils.SeleniumHelper.scrollToElement;

public class TimeTablePage extends BasePage {
    private String checkPriceXPath = "//tr[td[text()='%s' and following-sibling::td[text()='%s']]]//a[contains(text(),'check price')]";
    private String bookTicketXPath = "//tr[td[text()='%s' and following-sibling::td[text()='%s']]]//a[contains(text(),'book ticket')]";

    public void clickCheckPrice(String departure, String destination) {
        String xpathExpression = String.format(checkPriceXPath, departure, destination);
        By checkPriceLocator = By.xpath(xpathExpression);
        SeleniumHelper.scrollToElement(checkPriceLocator);
        SeleniumHelper.clickElement(checkPriceLocator);
    }

    public void clickBookTicket(String departure, String destination) {
        String xpathExpression = String.format(bookTicketXPath, departure, destination);
        By bookTicketLocator = By.xpath(xpathExpression);
        scrollToElement(bookTicketLocator);
        SeleniumHelper.clickElement(bookTicketLocator);
    }
}
