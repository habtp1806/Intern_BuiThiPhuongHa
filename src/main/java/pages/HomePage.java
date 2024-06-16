package pages;

import base.DriverManager;
import org.openqa.selenium.By;
import utils.SeleniumHelper;

public class HomePage extends BasePage {
    private By linkRegisterXPath = By.xpath("//a[normalize-space()='create an account']");
    private By welcomeUserXPath = By.xpath("//div[@id='banner']//strong");

    public boolean isWelcomeUserDisplayed() {
        return SeleniumHelper.isElementDisplayed(welcomeUserXPath);
    }

    public boolean isGuideLinkPresent() {
        return SeleniumHelper.isElementDisplayed(linkRegisterXPath);
    }

}
