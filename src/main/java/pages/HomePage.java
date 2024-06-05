package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openhomePage() {
        driver.get("http://saferailway.somee.com/");
    }

    public void clickTab(String tabName) {
        String xpathExpression = String.format("//div[@id='menu']//li/a[span[text()='%s']]", tabName);
        WebElement tabElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathExpression)));
        tabElement.click();
    }

    // Định nghĩa các phương thức khác để tương tác với các tab khác trên trang chủ
}
