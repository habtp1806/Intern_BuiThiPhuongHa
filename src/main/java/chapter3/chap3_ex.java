package chapter3;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class chap3_ex {
    public static void main(String[] args) {
        WebDriver chromeDriver = new ChromeDriver();

        executeTest(chromeDriver, "Chrome");

    }

    private static void executeTest(WebDriver driver, String browserName) {
        try {
            // Step 1: Get a free email from Guerrilla Mail
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            driver.get("https://www.guerrillamail.com/inbox");
            driver.manage().window().maximize();
            WebElement emailElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='inbox-id']")));
            String emailAddress = emailElement.getText() + "@guerrillamail.com";
            String password = "123456789";
            System.out.println("Temporary Email Address: " + emailAddress);
            Thread.sleep(2000);  // Wait for the page to load completely
            // Step 2: Navigate to the Saferailway site
            driver.get("http://saferailway.somee.com/");
            driver.manage().window().maximize();

            //Step 3:  Register account with email step 1

            driver.findElement(By.xpath("//div[@id='menu']//li/a[span[text()='Register']]")).click();
            ;

            driver.findElement(By.id("email")).sendKeys(emailAddress);
            driver.findElement(By.id("password")).sendKeys(password);
            driver.findElement(By.id("confirmPassword")).sendKeys(password);
            driver.findElement(By.id("pid")).sendKeys("123456789");
            WebElement element = driver.findElement(By.xpath("//input[@title='Register']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            element.click();

            // Step 4: Confirm email (assuming there is a confirmation link)
            driver.get("https://www.guerrillamail.com/inbox");

            // Wait for the confirmation email to arrive
            WebElement confirmationEmail = new WebDriverWait(driver, Duration.ofSeconds(50)).until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(), 'Please confirm')]")));
            confirmationEmail.click();

            // Find the confirmation link in the email and click it
            WebElement confirmationLink = new WebDriverWait(driver, Duration.ofSeconds(50)).until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, 'saferailway')]")));
            confirmationLink.click();

            System.out.println("Account successfully registered and confirmed!");

            // Step 5: Book 2 ticket to Phan Thiet
            driver.switchTo().newWindow(WindowType.TAB);
            driver.get("http://saferailway.somee.com/Page/HomePage.cshtml");
            WebElement loginTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='menu']//li/a[span[text()='Login']]")));
            loginTab.click();
            driver.findElement(By.xpath("//input[@id='username']")).sendKeys(emailAddress);
            driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
            driver.findElement(By.xpath("//input[@title='Login']")).click();

            // book ticket form
            driver.findElement(By.xpath("//div[@id='menu']//li/a[span[text()='Book ticket']]")).click();
            driver.findElement(By.xpath("//select[@name='ArriveStation']")).sendKeys("Phan Thiet");
            Select arriveAtDropdown = new Select(driver.findElement(By.xpath("//select[@name='ArriveStation']")));
            arriveAtDropdown.selectByVisibleText("Phan Thiết");
            Select ticketAountDropdown = new Select(driver.findElement(By.xpath("//select[@name='TicketAmount']")));
            ticketAountDropdown.selectByVisibleText("2");
            driver.findElement(By.xpath("//input[@title='Book ticket']")).click();


        } catch (Exception e) {
            System.out.println(browserName + ": Exception occurred while executing test: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
