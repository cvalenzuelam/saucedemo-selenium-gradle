package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    @Test
    public void loginWithLockedOutUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("locked_out_user", "secret_sauce");
        
        String actualError = loginPage.getErrorMessage();
        Assert.assertTrue(actualError.contains("Epic sadface: Sorry, this user has been locked out."), 
            "Error message mismatch for locked out user");
    }

    @Test
    public void loginWithInvalidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("invalid_user", "wrong_password");
        
        String actualError = loginPage.getErrorMessage();
        Assert.assertTrue(actualError.contains("Epic sadface: Username and password do not match any user in this service"), 
            "Error message mismatch for invalid credentials");
    }
}
