package pages;

import base.WebDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class ResetPasswordPage {
    private By newPass = By.xpath("//input[@id='newPassword']");
    private By confirmPass = By.xpath("//input[@id='confirmPassword']");
    private By resetToken = By.xpath("//input[@id='resetToken']");
    private By resetBtn = By.xpath("//input[@title='Reset password']");

    public void inputDifferentPasswords(String newPassword, String confirmPassword) {
        WebElement newPasswordField = WebDriverConfig.driver.findElement(By.id("newPass"));
        newPasswordField.sendKeys(newPassword);

        WebElement confirmPasswordField = WebDriverConfig.driver.findElement(By.id("confirmPass"));
        confirmPasswordField.sendKeys(confirmPassword);
    }

    public void clickRest() {
        WebElement resetButton = WebDriverConfig.driver.findElement(resetBtn);
        if (resetButton.isDisplayed()) {
            ((JavascriptExecutor) WebDriverConfig.driver).executeScript("arguments[0].scrollIntoView(true);", resetButton);

            resetButton.click();
        }
    }
}
