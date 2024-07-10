package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegistrationPage {
    private WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openRegistrationPage() {
        driver.findElement(By.id("SignUp_Btn")).click();
    }

    public void register(String firstName, String lastName, String postalCode, String email, String password) {
        driver.findElement(By.id("user_registration_firstName")).sendKeys(firstName);
        driver.findElement(By.id("user_registration_lastName")).sendKeys(lastName);
        driver.findElement(By.id("user_registration_address_postalCode")).sendKeys(postalCode);
        driver.findElement(By.id("user_registration_email")).sendKeys(email);
        driver.findElement(By.id("user_registration_password_first")).sendKeys(password);
        driver.findElement(By.id("user_registration_password_second")).sendKeys(password);

        // Scroll to the sign-up button
        WebElement signUpButton = driver.findElement(By.id("user_registration_submit"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", signUpButton);

        signUpButton.click();
    }
}
