package chapter5.pages;

import chapter5.base.BaseSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TicketPricePage extends BaseSetup {
    private String seat = "//*[@id='content']/table/tbody/tr[td[text()='%s']]//a[@class='BoxLink'][normalize-space()='Book ticket']";

    public TicketPricePage(WebDriver driver) {
        this.driver = driver;
    }

    public void chooseTypeSeat(String typeSeat) {
        String xpathExpression = String.format(seat, typeSeat);
        WebElement chooseSeat = driver.findElement(By.xpath(xpathExpression));
        chooseSeat.click();
    }
}
