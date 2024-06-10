package pages;

import base.Config;
import base.WebDriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    private WebDriverWait wait;
    private static String railway;

    public void openHomePage() {
        String railwayUrl = Config.getProperty("railway.url");
        WebDriverConfig.driver.get(railwayUrl);
        railway = WebDriverConfig.driver.getWindowHandle();
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

    public static void refreshPage() {
        WebDriverConfig.driver.navigate().refresh();
    }

    public static void switchToWindow(String windowHandle) {
        WebDriverConfig.driver.switchTo().window(windowHandle);
    }

    public void waitForElementToBeVisible(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(WebDriverConfig.driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


}
