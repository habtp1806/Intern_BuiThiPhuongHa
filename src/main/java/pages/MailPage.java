package pages;

import base.Config;
import base.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SeleniumHelper;

import java.time.Duration;

public class MailPage extends BasePage {
    private By confirmationEmail = By.xpath("//td[contains(text(), 'Please confirm')]");
    private By resetEmail = By.xpath("//td[contains(text(), 'Please reset')]");
    private By resetLink = By.xpath("//*[@class='email_body']//a[contains(@href,'PasswordReset')]");
    private By confirmationLink = By.xpath("//a[contains(@href, 'saferailway')]");
    private By emailLocator = By.xpath("//span[@id='inbox-id']");
    private By emailExtension = By.xpath("//select[@id='gm-host-select']");
    private By setBtn = By.xpath("//button[normalize-space()='Set']");
    private By emailBox = By.xpath("//span[@id='inbox-id']/input[@type='text']");

    private static String email;

    public static void openMailPage() {
        String mailUrl = Config.getProperty("tempmail.url");
        DriverManager.driver.get(mailUrl);
        //email = WebDriverConfig.driver.getWindowHandle();
    }

    public void setMail(String name, String domain) {
        clickMailBox();
        enterMail(name);
        clickSetBtn();
        selectDomainMail(domain);
    }

    public void clickMailBox() {
        SeleniumHelper.clickElement(emailLocator);
    }

    public void clickSetBtn() {
        SeleniumHelper.clickElement(setBtn);
    }

    public static void switchToEmail() {
        DriverManager.driver.switchTo().window(email);
    }

    public String getMail() {
        WebElement emailElement = SeleniumHelper.findElement(emailBox);
        String emailAddress = emailElement.getText() + "@guerrillamail.com";
        System.out.println("Temporary Email Address: " + emailAddress);
        return emailAddress;
    }

    public void enterMail(String email) {
        SeleniumHelper.enterText(emailBox, email);
    }

    public void selectDomainMail(String domain) {
        SeleniumHelper.selectByVisibleText(emailExtension, domain);
    }

    public void verifyMail() {
        waitForElementToBeVisible(confirmationEmail, 50);
        SeleniumHelper.clickElement(confirmationEmail);

        waitForElementToBeVisible(confirmationLink, 50);
        SeleniumHelper.clickElement(confirmationLink);
    }

    public void clickResetLink() {
        waitForElementToBeVisible(resetEmail, 50);
        SeleniumHelper.clickElement(resetEmail);

        // Wait for the confirmation link to be visible and click it
        waitForElementToBeVisible(resetLink, 50);
        SeleniumHelper.clickElement(resetLink);
    }
}
