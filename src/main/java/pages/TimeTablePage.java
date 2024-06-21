package pages;


import base.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.SeleniumHelper;

import static utils.SeleniumHelper.scrollToElement;

public class TimeTablePage extends BasePage {

    private final String actionXPath = "//tr[td[text()='%s' and following-sibling::td[text()='%s']]]//a[contains(text(),'%s')]";

    private By getXPathForAction(String departure, String destination, String action) {
        String xpathExpression = String.format(actionXPath, departure, destination, action);
        return By.xpath(xpathExpression);
    }

    public void clickCheckPrice(String departure, String destination) {
        By checkPriceLocator = getXPathForAction(departure, destination, "check price");
        SeleniumHelper.scrollToElement(checkPriceLocator);
        SeleniumHelper.clickElement(checkPriceLocator);
    }

    public void clickBookTicket(String departure, String destination) {
        By bookTicketLocator = getXPathForAction(departure, destination, "book ticket");
        SeleniumHelper.scrollToElement(bookTicketLocator);
        SeleniumHelper.clickElement(bookTicketLocator);
    }
}
