package pages;


import base.WebDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TimeTablePage {
    private String checkPrice = "//tr[td[text()='%s' and following-sibling::td[text()='%s']]]//a[contains(text(),'check price')]";


    public void clickCheckPrice(String departure, String destination) {
        String xpathExpression = String.format(checkPrice, departure, destination);
        WebElement checkPriceLink = WebDriverConfig.driver.findElement(By.xpath(xpathExpression));
        checkPriceLink.click();
    }
}
