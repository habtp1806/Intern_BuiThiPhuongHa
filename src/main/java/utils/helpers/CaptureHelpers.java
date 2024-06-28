package utils.helpers;

import base.Config;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Reporter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import base.Config;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Reporter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CaptureHelpers {

    // Get the project directory path
    static String projectPath = System.getProperty("user.dir") + "/";
    // Date format for appending to screenshot names
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");

    public static void captureScreenshot(WebDriver driver, String screenName) {
        // Load properties from config.properties
        Config.loadProperties();

        try {
            Reporter.log("Driver for Screenshot: " + driver);
            // Take screenshot using Selenium's TakesScreenshot interface
            TakesScreenshot ts = (TakesScreenshot) driver;
            // Convert screenshot to FILE format
            File source = ts.getScreenshotAs(OutputType.FILE);

            // Check if exportCapturePath exists, create if not
            String exportCapturePath = Config.getProperty("exportCapturePath");
            File theDir = new File(projectPath + exportCapturePath);
            if (!theDir.exists()) {
                theDir.mkdirs();
            }

            // Construct file name with screenName and timestamp
            String timestamp = dateFormat.format(new Date());
            String screenshotPath = projectPath + exportCapturePath + "/" + screenName + "_" + timestamp + ".png";

            // Save screenshot to the specified path
            FileHandler.copy(source, new File(screenshotPath));
            System.out.println("Screenshot taken: " + screenName);

            // Log current URL where screenshot was taken
            Reporter.log("Screenshot taken current URL: " + driver.getCurrentUrl(), true);
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        }
    }

    public static void startRecord(String testName) {
        // Implement your video recording start logic here
        System.out.println("Starting video recording for test: " + testName);
    }

    public static void stopRecord() {
        // Implement your video recording stop logic here
        System.out.println("Stopping video recording");
    }
}
