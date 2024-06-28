package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;


public class DriverManager {
    public static WebDriver driver;
    private static final String HUB_URL = " http://192.168.60.15:4444";

    public static void initDriver(String browserType) {
        // browserType = Config.getProperty("browser");
        if (browserType == null || browserType.isEmpty()) {
            browserType = "chrome"; // Default to Chrome if browser type is not provided
        }
        browserType = browserType.toLowerCase();

        switch (browserType) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
        DriverManager.driver.manage().window().maximize();


    }

    public static void initRemoteDriver(String browser) throws MalformedURLException {

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setPlatform(Platform.ANY);
        switch (browser) {
            case "chrome":
                cap.setPlatform(Platform.ANY);
                cap.setBrowserName("chrome");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.merge(cap);
                driver = new RemoteWebDriver(new URL(HUB_URL), cap);
                break;
            case "firefox":
                cap.setPlatform(Platform.ANY);
                cap.setBrowserName("firefox");
                FirefoxOptions ffOptions = new FirefoxOptions();
                ffOptions.merge(cap);
                driver = new RemoteWebDriver(new URL(HUB_URL), cap);
                break;
            case "edge":
                cap.setPlatform(Platform.ANY);
                cap.setBrowserName("chrome");
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.merge(cap);
                driver = new RemoteWebDriver(new URL(HUB_URL), cap);
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browser);
        }
        DriverManager.driver.manage().window().maximize();

    }

    public static void navigateToRailWay() {
        String railwayUrl = Config.getProperty("railway.url");
        DriverManager.driver.get(railwayUrl);
        // railway = WebDriverConfig.driver.getWindowHandle();
    }

    public static void navigateToMailPage() {
        String mailUrl = Config.getProperty("tempmail.url");
        DriverManager.driver.get(mailUrl);
        //email = WebDriverConfig.driver.getWindowHandle();
    }


    public static void waitForElementToBeVisible(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(DriverManager.driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitForClickableElement(String xpathExpression) {
        int timeoutInSeconds = Config.getTimeInSeconds("timeout");
        WebDriverWait wait = new WebDriverWait(DriverManager.driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathExpression)));
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
