package pages;

import base.Config;
import base.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import utils.SeleniumHelper;

import static base.DriverManager.waitForElementToBeVisible;

public class ResetPasswordPage {
    private final By resetBtnXPath = By.xpath("//input[@title='Reset password']");
    private final By resetTokenXPath = By.xpath("//input[@id='resetToken']");
    private final By formChangePasswordXpath = By.xpath("//form[//*[text()='Password Change Form']]");
    private final By messAboveXPath = By.xpath("//p[contains(@class,'message')]");
    private final By messNextTBoxXPath = By.xpath("//label[@for='confirmPassword' and @class='validation-error']");

    private By getXPathByName(String name) {
        return By.xpath(String.format("//input[@id='%s']", name));
    }

    public void resetPassword(String newPassword, String confirmPassword) {
        SeleniumHelper.enter(getXPathByName("newPassword"), newPassword);
        SeleniumHelper.enter(getXPathByName("confirmPassword"), confirmPassword);
        clickReset();
    }

    public boolean isResetPasswordFormDisplayed() {
        return SeleniumHelper.findElement(formChangePasswordXpath).isDisplayed();
    }

    public String getResetTokenInTextBox() {
        return SeleniumHelper.findElement(resetTokenXPath).getAttribute("value");
    }


    public void clickReset() {
        SeleniumHelper.scrollToElement(resetBtnXPath);
        SeleniumHelper.clickElement(resetBtnXPath);
    }

    public String getMessageAboveForm() {
        return SeleniumHelper.getElementText(messAboveXPath);
    }

    public String getMessageNextToTextBox() {
        return SeleniumHelper.getElementText(messNextTBoxXPath);
    }

}
