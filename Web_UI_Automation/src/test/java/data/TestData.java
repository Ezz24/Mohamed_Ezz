package data;

import org.testng.annotations.DataProvider;

import java.util.Random;

public class TestData {

    @DataProvider(name = "LoginData")
    public Object[][] getLoginData() {
        return new Object[][]{
                {"mjemyezz@hotmail.com", "Admin@123"}
        };
    }

//    @DataProvider(name = "RegistrationData")
//    public Object[][] getRegistrationData() {
//        Random random = new Random();
//        String email = "ezz" + random.nextInt(1000) + "@example.com";
//        return new Object[][]{
//                {"Ezz", "Ahmed", "M5V3L9", email, "Admin@123"}
//        };
//    }

    @DataProvider(name = "registrationData")
    public Object[][] getRegistrationData() {
        Random random = new Random();
        String email = "ezz" + random.nextInt(1000) + "@example.com";
        return new Object[][]{
                {"Ezz", "Ahmed", "M5V3L9", email, "Admin@123"}
        };
    }
}
