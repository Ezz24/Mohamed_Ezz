package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    private By loginButton = By.id("Login_Btn");
    private By usernameField = By.id("Login_Page_username");
    private By passwordField = By.id("Login_Page_password");
    private By submitButton = By.id("Login_Page_submit");
    private By profileButton = By.cssSelector("button.desktopProfileMenu-module--profile__accountBtn--c4917 span");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openLoginPage() {
        driver.findElement(loginButton).click();
    }

    public void login(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(submitButton).click();
    }

    public boolean isProfileButtonDisplayed() {
        return driver.findElement(profileButton).isDisplayed();
    }

    public String getProfileName() {
        return driver.findElement(profileButton).getText();
    }
}
