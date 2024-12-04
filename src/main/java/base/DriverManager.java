package base;

import base.Config;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.Platform;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<String> browserThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<String> runmodeThreadLocal = new ThreadLocal<>();
    private static final String HUB_URL = "http://localhost:4444/wd/hub";

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }

    public static String getBrowser() {
        return browserThreadLocal.get();
    }

    public static void setBrowser(String browser) {
        browserThreadLocal.set(browser);
    }

    public static String getRunmode() {
        return runmodeThreadLocal.get();
    }

    public static void setRunmode(String runmode) {
        runmodeThreadLocal.set(runmode);
    }

    public static void initDriver(String browserType) {
        if (browserType == null || browserType.isEmpty()) {
            browserType = "chrome"; // Default to Chrome if browser type is not provided
        }
        browserType = browserType.toLowerCase();

        setBrowser(browserType); // Set the browser type using ThreadLocal

        switch (browserType) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                setDriver(new ChromeDriver());
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                setDriver(new FirefoxDriver());
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                setDriver(new EdgeDriver());
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
        getDriver().manage().window().maximize();
    }

    public static void initRemoteDriver(String browser) throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setPlatform(Platform.ANY);

        setBrowser(browser); // Set the browser type using ThreadLocal

        switch (browser) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.merge(cap);
                setDriver(new RemoteWebDriver(new URL(HUB_URL), chromeOptions));
                break;
            case "firefox":
                FirefoxOptions ffOptions = new FirefoxOptions();
                ffOptions.merge(cap);
                setDriver(new RemoteWebDriver(new URL(HUB_URL), ffOptions));
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.merge(cap);
                setDriver(new RemoteWebDriver(new URL(HUB_URL), edgeOptions));
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browser);
        }
        getDriver().manage().window().maximize();
    }

    public static void navigateToRailWay() {
        String railwayUrl = Config.getProperty("railway.url");
        getDriver().get(railwayUrl);
    }

    public static void navigateToMailPage() {
        String mailUrl = Config.getProperty("tempmail.url");
        getDriver().get(mailUrl);
    }

    public static void waitForElementToBeVisible(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitForClickableElement(String xpathExpression) {
        int timeoutInSeconds = Config.getTimeInSeconds("timeout");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathExpression)));
    }

    public static void refreshPage() {
        getDriver().navigate().refresh();
    }

    public static void switchToWindow(String windowHandle) {
        getDriver().switchTo().window(windowHandle);
    }

    public static String getWindowHandle() {
        return getDriver().getWindowHandle();
    }
    
}
