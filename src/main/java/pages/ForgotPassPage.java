package pages;

import base.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.SeleniumHelper;

public class ForgotPassPage {
    private final By emailXPath = By.xpath("//input[@id='email']");
    private final By sendBtnXPath = By.xpath("//input[@value='Send Instructions']");

    public void sendForgotPass(String email) {
        enterEmail(email);
        clickSend();
    }

    public void clickSend() {
        SeleniumHelper.clickElement(sendBtnXPath);
    }

    public void enterEmail(String email) {
        SeleniumHelper.enter(emailXPath, email);
    }
}
