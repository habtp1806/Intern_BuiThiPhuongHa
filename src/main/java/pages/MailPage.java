package pages;

import base.BaseSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MailPage {
    private WebDriver driver;
    private By confirmationEmail = By.xpath("//td[contains(text(), 'Please confirm')]");
    private By confirmationLink = By.xpath("//a[contains(@href, 'saferailway')]");
    private By emailLocator = By.xpath("//span[@id='inbox-id']");

    public MailPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getMail() throws Exception {
        WebElement emailElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOfElementLocated(emailLocator)
        );
        String emailAddress = emailElement.getText() + "@guerrillamail.com";
        System.out.println("Temporary Email Address: " + emailAddress);
        return emailAddress;
    }


}
