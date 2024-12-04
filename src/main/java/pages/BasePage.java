package pages;

import base.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.DriverManager;
import enums.RailwayTab;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {

    public static void clickTab(RailwayTab tab) {
        String tabName = tab.getValue();
        String xpathExpression = String.format("//div[@id='menu']//li/a[span[text()='%s']]", tabName);
        waitForClickableElement(xpathExpression);
        DriverManager.getDriver().findElement(By.xpath(xpathExpression)).click();
    }

    public static void clickLink(String linkName) {
        String xpathExpression = String.format("//a[normalize-space()='%s']", linkName);
        waitForClickableElement(xpathExpression);
        DriverManager.getDriver().findElement(By.xpath(xpathExpression)).click();
    }

    public static void switchToRemainingTab(String windowHandleOfFirstTab, String windowHandleOfSecondTab) {
        Set<String> allTabs = DriverManager.getDriver().getWindowHandles();
        for (String tab : allTabs) {
            if (!tab.equals(windowHandleOfFirstTab) && !tab.equals(windowHandleOfSecondTab)) {
                DriverManager.getDriver().switchTo().window(tab);
                break;
            }
        }
    }

    public static void openNewTab(String url) {
        DriverManager.getDriver().switchTo().newWindow(WindowType.TAB);
        DriverManager.getDriver().navigate().to(url);
    }

    public static boolean isTabDisplayed(String tabName) {
        List<WebElement> tabs = DriverManager.getDriver().findElements(By.linkText(tabName));
        return !tabs.isEmpty() && tabs.get(0).isDisplayed();
    }

    private static void waitForClickableElement(String xpathExpression) {
        int timeoutInSeconds = Config.getTimeInSeconds("timeout");
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathExpression)));
    }
}
