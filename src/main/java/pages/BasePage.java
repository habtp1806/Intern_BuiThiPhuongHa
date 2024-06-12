package pages;

import base.Config;
import base.WebDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    private WebDriverWait wait;
    private static String railway;


    public void openHomePage() {
        String railwayUrl = Config.getProperty("railway.url");
        WebDriverConfig.driver.get(railwayUrl);
        // railway = WebDriverConfig.driver.getWindowHandle();
    }

    public static void switchToRailway() {
        WebDriverConfig.driver.switchTo().window(railway);
    }

    public void clickTab(String tabName) {
        WebDriverWait wait = new WebDriverWait(WebDriverConfig.driver, Duration.ofSeconds(10));
        String xpathExpression = String.format("//div[@id='menu']//li/a[span[text()='%s']]", tabName);
        WebElement tabElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathExpression)));
        tabElement.click();
    }

    public void clickLink(String linkName) {
        WebDriverWait wait = new WebDriverWait(WebDriverConfig.driver, Duration.ofSeconds(10));
        String xpathExpression = String.format("//a[normalize-space()='%s']", linkName);
        WebElement tabElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathExpression)));
        tabElement.click();
    }

    public static void refreshPage() {
        WebDriverConfig.driver.navigate().refresh();
    }

    public static void switchToWindow(String windowHandle) {
        WebDriverConfig.driver.switchTo().window(windowHandle);
    }

    public static void openNewTab(String url) {
        WebDriverConfig.driver.switchTo().newWindow(WindowType.TAB);
        WebDriverConfig.driver.navigate().to(url);
    }

    public static String getWindowHandle() {
        return WebDriverConfig.driver.getWindowHandle();
    }

    public static void zoomIn(Double zoomNumber) {
        ((JavascriptExecutor) WebDriverConfig.driver).executeScript(java.lang.String.format("document.body.style.zoom = '%f'", zoomNumber));
    }

    public boolean isTabPresent(String tabName) {
        return WebDriverConfig.driver.findElements(By.linkText(tabName)).size() > 0;
    }

    public static void waitForElementToBeVisible(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(WebDriverConfig.driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


}
