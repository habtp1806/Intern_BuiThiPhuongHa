package chapter5.pages;

import chapter5.base.BaseSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BaseSetup {

    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public HomePage openhomePage() {
        driver.get(raiwayUrl);
        return this;
    }

    public void clickTab(String tabName) {
        String xpathExpression = String.format("//div[@id='menu']//li/a[span[text()='%s']]", tabName);
        WebElement tabElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathExpression)));
        tabElement.click();
    }

    
}
