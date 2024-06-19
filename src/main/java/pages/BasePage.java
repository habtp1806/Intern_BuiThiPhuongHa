package pages;

import base.Config;
import base.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static base.DriverManager.waitForClickableElement;

public class BasePage {

    private static String railway;


    public static void switchToRailway() {
        DriverManager.driver.switchTo().window(railway);
    }

    public static void clickTab(String tabName) {
        String xpathExpression = String.format("//div[@id='menu']//li/a[span[text()='%s']]", tabName);
        WebElement tabElement = waitForClickableElement(xpathExpression);
        tabElement.click();
    }


    public static void clickLink(String linkName) {
        String xpathExpression = String.format("//a[normalize-space()='%s']", linkName);
        WebElement tabElement = waitForClickableElement(xpathExpression);
        tabElement.click();
    }


    public static void openNewTab(String url) {
        DriverManager.driver.switchTo().newWindow(WindowType.TAB);
        DriverManager.driver.navigate().to(url);
    }


    public static boolean isTabDisplayed(String tabName) {
        List<WebElement> tabs = DriverManager.driver.findElements(By.linkText(tabName));
        return !tabs.isEmpty() && tabs.get(0).isDisplayed();
    }


}
