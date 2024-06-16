package utils;

import base.Config;
import base.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import static pages.BasePage.waitForElementToBeVisible;

public class SeleniumHelper {

    public static void selectByText(By element, String text) {
        Select select = new Select(findElement(element));
        select.selectByVisibleText(text);
    }

    public static WebElement findElement(By element) {
        return DriverManager.driver.findElement(element);
    }

    public static String getElementText(By element) {
        return SeleniumHelper.findElement(element).getText();
    }

    public static void enterText(By locator, String text) {
        waitForElementToBeVisible(locator, Config.getTimeInSeconds("timeout"));
        WebElement element = DriverManager.driver.findElement(locator);
        // element.clear();
        element.sendKeys(text);
    }

    public static void selectByVisibleText(By locator, String text) {
        Select dropdown = new Select(DriverManager.driver.findElement(locator));
        dropdown.selectByVisibleText(text);
    }

    public static void clickElement(By locator) {
        WebElement element = DriverManager.driver.findElement(locator);
        element.click();
    }

    public static void verifyTextEquals(By locator, String expectedText) {
        String actualText = getElementText(locator);
        Assert.assertEquals(actualText, expectedText, "Text does not match.");
    }

    public static boolean isElementDisplayed(By locator) {
        try {
            WebElement element = DriverManager.driver.findElement(locator);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static void scrollToElement(By locator) {
        WebElement element = DriverManager.driver.findElement(locator);
        ((JavascriptExecutor) DriverManager.driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

}
