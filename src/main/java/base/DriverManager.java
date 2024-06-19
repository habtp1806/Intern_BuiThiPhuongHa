package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class DriverManager {
    public static WebDriver driver;

    public static void initDriver() {
        String browserType = Config.getProperty("browser").toLowerCase();
        if (browserType.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            DriverManager.driver = new ChromeDriver();
        } else if (browserType.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            DriverManager.driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }

        DriverManager.driver.manage().window().maximize();


    }

    public static void openHomePage() {
        String railwayUrl = Config.getProperty("railway.url");
        DriverManager.driver.get(railwayUrl);
        // railway = WebDriverConfig.driver.getWindowHandle();
    }

    public static void openMailPage() {
        String mailUrl = Config.getProperty("tempmail.url");
        DriverManager.driver.get(mailUrl);
        //email = WebDriverConfig.driver.getWindowHandle();
    }

    public static void waitForElementToBeVisible(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(DriverManager.driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickableElement(String xpathExpression) {
        int timeoutInSeconds = Config.getTimeInSeconds("timeout");
        WebDriverWait wait = new WebDriverWait(DriverManager.driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathExpression)));
    }

    public static void refreshPage() {
        DriverManager.driver.navigate().refresh();
    }

    public static void switchToWindow(String windowHandle) {
        DriverManager.driver.switchTo().window(windowHandle);
    }

    public static String getWindowHandle() {
        return DriverManager.driver.getWindowHandle();
    }

}
