package pages;

import base.Config;
import base.WebDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MailPage {
    private By confirmationEmail = By.xpath("//td[contains(text(), 'Please confirm')]");
    private By confirmationLink = By.xpath("//a[contains(@href, 'saferailway')]");
    private By emailLocator = By.xpath("//span[@id='inbox-id']");
    private static String email;

    public static void openMailPage() {
        String mailUrl = Config.getProperty("tempmail.url");
        WebDriverConfig.driver.get(mailUrl);
        email = WebDriverConfig.driver.getWindowHandle();
    }

    public static void switchToEmail() {
        WebDriverConfig.driver.switchTo().window(email);
    }

    public String getMail() throws Exception {
        WebElement emailElement = new WebDriverWait(WebDriverConfig.driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOfElementLocated(emailLocator)
        );
        String emailAddress = emailElement.getText() + "@guerrillamail.com";
        System.out.println("Temporary Email Address: " + emailAddress);
        return emailAddress;
    }

    public void verifyMail() {
        // Wait for the confirmation email to be visible and click it
        WebElement emailBox = new WebDriverWait(WebDriverConfig.driver, Duration.ofSeconds(50)).until(
                ExpectedConditions.visibilityOfElementLocated(confirmationEmail)
        );
        emailBox.click();

        // Wait for the confirmation link to be visible and click it
        WebElement linkElement = new WebDriverWait(WebDriverConfig.driver, Duration.ofSeconds(50)).until(
                ExpectedConditions.visibilityOfElementLocated(confirmationLink)
        );
        linkElement.click();
    }

}
