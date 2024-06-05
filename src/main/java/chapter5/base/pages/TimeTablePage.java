package chapter5.base.pages;

import chapter5.base.BaseSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TimeTablePage extends BaseSetup {
    private By checkPrice = By.xpath("//tr[td[text()='%s' and following-sibling::td[text()='%s']]]//a[contains(text(),'check price')]");

    public TimeTablePage(WebDriver driver) {
        this.driver = driver;
    }
}
