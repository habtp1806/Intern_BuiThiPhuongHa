package pages;

import base.Config;
import base.WebDriverConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MailPage {
    private By confirmationEmail = By.xpath("//td[contains(text(), 'Please confirm')]");
    private By resetEmail = By.xpath("//td[contains(text(), 'Please reset')]");
    private By resetLink = By.xpath("//*[@class='email_body']//a[contains(@href,'PasswordReset')]");
    private By confirmationLink = By.xpath("//a[contains(@href, 'saferailway')]");
    private By emailLocator = By.xpath("//span[@id='inbox-id']");
    private By emailExtension = By.xpath("//select[@id='gm-host-select']");
    private By setBtn = By.xpath("//button[normalize-space()='Set']");
    private String emailBox = "//span[@id='inbox-id']/input[@type='text']";

    private static String email;

    public static void openMailPage() {
        String mailUrl = Config.getProperty("tempmail.url");
        WebDriverConfig.driver.get(mailUrl);
        //email = WebDriverConfig.driver.getWindowHandle();
    }

    public void setMail(String name, String domain) {
        clickMailBox();
        enterMail(name);
        clickSetBtn();
        selectDomainMail(domain);
    }

    public void clickMailBox() {
        WebElement mailBox = WebDriverConfig.driver.findElement(emailLocator);
        mailBox.click();
    }

    public void clickSetBtn() {
        WebElement setButton = WebDriverConfig.driver.findElement(setBtn);
        setButton.click();
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

    public void enterMail(String email) {
        String xpathExpression = String.format(emailBox, email);
        WebElement mailBox = WebDriverConfig.driver.findElement(By.xpath(xpathExpression));
        mailBox.sendKeys(email);
    }

    public void selectDomainMail(String domain) {
        Select dateDropdown = new Select(WebDriverConfig.driver.findElement(emailExtension));
        dateDropdown.selectByVisibleText(domain);
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

    public void clickResetLink() {
        // Wait for the confirmation email to be visible and click it
        WebElement resetBox = new WebDriverWait(WebDriverConfig.driver, Duration.ofSeconds(50)).until(
                ExpectedConditions.visibilityOfElementLocated(resetEmail)
        );
        resetBox.click();

        // Wait for the confirmation link to be visible and click it
        WebElement linkElement = new WebDriverWait(WebDriverConfig.driver, Duration.ofSeconds(50)).until(
                ExpectedConditions.visibilityOfElementLocated(resetLink)
        );
        linkElement.click();
    }
}
