package pages;

import base.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import utils.SeleniumHelper;

public class ResetPasswordPage {
    private By resetBtnXPath = By.xpath("//input[@title='Reset password']");
    private By resetTokenXPath = By.xpath("//input[@id='resetToken']");

    private By getXPathByName(String name) {
        return By.xpath(String.format("input[@id='%s']", name));
    }

    public void resetPassword(String newPassword, String confirmPassword) {
        SeleniumHelper.enterText(getXPathByName("newPassword"), newPassword);
        SeleniumHelper.enterText(getXPathByName("confirmPassword"), confirmPassword);
        clickReset();
    }

    public String getResetTokenInTextBox() {
        return SeleniumHelper.getAttributeValue(resetTokenXPath, "value");
    }

    public void clickReset() {
        SeleniumHelper.scrollToElement(resetBtnXPath);
        SeleniumHelper.clickElement(resetBtnXPath);
    }
}
