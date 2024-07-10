package tests;

import base.TestBase;
import data.TestData;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends TestBase {

    @Test(dataProvider = "LoginData", dataProviderClass = TestData.class)
    public void testLogin(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();
        loginPage.login(username, password);

        Assert.assertTrue(loginPage.isProfileButtonDisplayed(), "Profile button is not displayed!");
        Assert.assertEquals(loginPage.getProfileName(), "ezz ahmed", "Profile name does not match!");
    }
}
