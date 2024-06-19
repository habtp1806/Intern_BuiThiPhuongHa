package pages;


import base.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.SeleniumHelper;

public class TicketPricePage {

    private String seatXPath = "//*[@id='content']/table/tbody/tr[td[text()='%s']]//a[@class='BoxLink'][normalize-space()='Book ticket']";
    private String titleTicketPriceXPath = "//th[contains(text(),'Ticket price from %s to %s')]";
    private String priceSeatTypeXPath = "//table[@class='MyTable MedTable']//th[normalize-space()='Price (VND)']//following-sibling::td[count(//td[text()='%s']/preceding-sibling::td)+1]";

    public void chooseTypeSeat(String typeSeat) {
        String xpathExpression = String.format(seatXPath, typeSeat);
        By chooseSeat = By.xpath(xpathExpression);
        SeleniumHelper.clickElement(chooseSeat);
    }

    public boolean verifyTitleTable(String departStation, String arriveAt) {
        String xpathExpression = String.format(titleTicketPriceXPath, departStation, arriveAt);
        By titleLocator = By.xpath(xpathExpression);
        return SeleniumHelper.isElementDisplayed(titleLocator);

    }

    public Integer getPriceOfSeatType(String seatType) {
        String xpathExpression = String.format(priceSeatTypeXPath, seatType);
        By priceLocator = By.xpath(xpathExpression);
        WebElement priceElement = SeleniumHelper.findElement(priceLocator);
        return Integer.parseInt(priceElement.getText().replace(",", ""));
    }
}
