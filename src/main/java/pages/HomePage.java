package pages;

import base.WebDriverConfig;
import org.openqa.selenium.By;

public class HomePage extends BasePage {
    private By linkRegister = By.xpath("//a[normalize-space()='create an account']");
    private By welcomeUser = By.xpath("//div[@id='banner']//strong");

    public boolean isWelcomUserDisplayed() {
        return WebDriverConfig.driver.findElement(welcomeUser).isDisplayed();
    }

    public boolean isGuideLinkPresent() {
        return WebDriverConfig.driver.findElements(linkRegister).size() > 0;
    }

}
