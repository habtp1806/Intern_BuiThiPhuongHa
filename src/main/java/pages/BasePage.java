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

public class BasePage {
    
    private static String railway;


    public void openHomePage() {
        String railwayUrl = Config.getProperty("railway.url");
        DriverManager.driver.get(railwayUrl);
        // railway = WebDriverConfig.driver.getWindowHandle();
    }

    public static void switchToRailway() {
        DriverManager.driver.switchTo().window(railway);
    }

    public void clickTab(String tabName) {
        WebDriverWait wait = new WebDriverWait(DriverManager.driver, Duration.ofSeconds(10));
        String xpathExpression = String.format("//div[@id='menu']//li/a[span[text()='%s']]", tabName);
        WebElement tabElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathExpression)));
        tabElement.click();
    }

    public void clickLink(String linkName) {
        WebDriverWait wait = new WebDriverWait(DriverManager.driver, Duration.ofSeconds(10));
        String xpathExpression = String.format("//a[normalize-space()='%s']", linkName);
        WebElement tabElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathExpression)));
        tabElement.click();
    }

    public static void refreshPage() {
        DriverManager.driver.navigate().refresh();
    }

    public static void switchToWindow(String windowHandle) {
        DriverManager.driver.switchTo().window(windowHandle);
    }

    public static void openNewTab(String url) {
        DriverManager.driver.switchTo().newWindow(WindowType.TAB);
        DriverManager.driver.navigate().to(url);
    }


    public static String getWindowHandle() {
        return DriverManager.driver.getWindowHandle();
    }

    public static void zoomIn(Double zoomNumber) {
        ((JavascriptExecutor) DriverManager.driver).executeScript(java.lang.String.format("document.body.style.zoom = '%f'", zoomNumber));
    }

    public boolean isTabPresent(String tabName) {
        return DriverManager.driver.findElements(By.linkText(tabName)).size() > 0;
    }

    public static void waitForElementToBeVisible(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(DriverManager.driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


}
