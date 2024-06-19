package pages;

import base.Config;
import base.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SeleniumHelper;

import java.time.Duration;

import static base.DriverManager.waitForElementToBeVisible;

public class MailPage extends BasePage {
    private By confirmationEmailXPath = By.xpath("//td[contains(text(), 'Please confirm')]");
    private By resetEmailXPath = By.xpath("//td[contains(text(), 'Please reset')]");
    private By resetLinkXPath = By.xpath("//*[@class='email_body']//a[contains(@href,'PasswordReset')]");
    private By confirmationLinkXPath = By.xpath("//a[contains(@href, 'saferailway')]");
    private By emailBoxXPath = By.xpath("//span[@id='inbox-id']");
    private By emailExtensionXPath = By.xpath("//select[@id='gm-host-select']");
    private By setBtnXPath = By.xpath("//button[normalize-space()='Set']");
    private By emailTxtBoxXPath = By.xpath("//span[@id='inbox-id']/input[@type='text']");

    private static String email;


    public void setMail(String name, String domain) {
        clickMailBox();
        enterMail(name);
        clickSetBtn();
        selectDomainMail(domain);
    }

    public void clickMailBox() {
        SeleniumHelper.clickElement(emailBoxXPath);
    }

    public void clickSetBtn() {
        SeleniumHelper.clickElement(setBtnXPath);
    }

    public static void switchToEmail() {
        DriverManager.driver.switchTo().window(email);
    }

    public String getMail() {
        WebElement emailElement = SeleniumHelper.findElement(emailTxtBoxXPath);
        String emailAddress = emailElement.getText() + "@guerrillamail.com";
        System.out.println("Temporary Email Address: " + emailAddress);
        return emailAddress;
    }

    public void enterMail(String email) {
        SeleniumHelper.enter(emailTxtBoxXPath, email);
    }

    public void selectDomainMail(String domain) {
        SeleniumHelper.selectByVisibleText(emailExtensionXPath, domain);
    }

    public void verifyMail() {
        waitForElementToBeVisible(confirmationEmailXPath, 50);
        SeleniumHelper.clickElement(confirmationEmailXPath);
        waitForElementToBeVisible(confirmationLinkXPath, 50);
        SeleniumHelper.clickElement(confirmationLinkXPath);
    }

    public void clickResetLink() {
        waitForElementToBeVisible(resetEmailXPath, 50);
        SeleniumHelper.clickElement(resetEmailXPath);

        // Wait for the confirmation link to be visible and click it
        waitForElementToBeVisible(resetLinkXPath, 50);
        SeleniumHelper.clickElement(resetLinkXPath);
    }
}
