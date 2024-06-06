package chapter5.pages;

import chapter5.base.BaseSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TimeTablePage extends BaseSetup {
    private String checkPrice = "//tr[td[text()='%s' and following-sibling::td[text()='%s']]]//a[contains(text(),'check price')]";

    public TimeTablePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCheckPrice(String departure, String destination) {
        String xpathExpression = String.format(checkPrice, departure, destination);
        WebElement checkPriceLink = driver.findElement(By.xpath(xpathExpression));
        checkPriceLink.click();
    }
}
