package pages;

import base.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import utils.SeleniumHelper;

public class ResetPasswordPage {
    private By newPassXPath = By.xpath("//input[@id='newPassword']");
    private By confirmPassXPath = By.xpath("//input[@id='confirmPassword']");
    private By resetTokenXPath = By.xpath("//input[@id='resetToken']");
    private By resetBtnXPath = By.xpath("//input[@title='Reset password']");

    public void inputDifferentPasswords(String newPassword, String confirmPassword) {
        SeleniumHelper.enterText(newPassXPath, newPassword);
        SeleniumHelper.enterText(confirmPassXPath, confirmPassword);
        clickRest();
    }

    public void clickRest() {
        SeleniumHelper.scrollToElement(resetBtnXPath);
        SeleniumHelper.clickElement(resetBtnXPath);
    }
}
