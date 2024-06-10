package pages;


import base.WebDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TicketPricePage {

    private String seat = "//*[@id='content']/table/tbody/tr[td[text()='%s']]//a[@class='BoxLink'][normalize-space()='Book ticket']";


    public void chooseTypeSeat(String typeSeat) {
        String xpathExpression = String.format(seat, typeSeat);
        WebElement chooseSeat = WebDriverConfig.driver.findElement(By.xpath(xpathExpression));
        chooseSeat.click();
    }
}
