package tests;

import base.TestBase;
import data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegistrationPage;

import java.time.Duration;

public class RegistrationTest extends TestBase {

    @Test(dataProvider = "registrationData", dataProviderClass = TestData.class)
    public void testRegistration(String firstName, String lastName, String postalCode, String email, String password) {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.openRegistrationPage();
        registrationPage.register(firstName, lastName, postalCode, email, password);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

//        WebElement profileButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[pf-mix-id='headerBtn_profileMenu']")));
//        Assert.assertTrue(profileButton.isDisplayed(), "Profile button is not displayed!");


        WebElement profileName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"desktopMenu-profile\"]")));
        Assert.assertEquals(profileName.getText(), firstName + " " + lastName, "Profile name does not match!");
    }
}
