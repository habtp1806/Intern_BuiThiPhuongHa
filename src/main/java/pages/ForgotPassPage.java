package pages;

import base.WebDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class ForgotPassPage {
    private By emailtxt = By.xpath("//input[@id='email']");
    private By sendBtn = By.xpath("//input[@value='Send Instructions']");

    public void sendForgotPass(String email) throws Exception {
        enterEmail(email);
        clickSend();
    }

    public void clickSend() {
        WebElement sendButton = WebDriverConfig.driver.findElement(sendBtn);
        sendButton.click();
    }

    public void enterEmail(String email) {
        WebElement emailTxtBox = WebDriverConfig.driver.findElement(emailtxt);
        if (emailTxtBox.isDisplayed())
            emailTxtBox.sendKeys(email);

    }
}
