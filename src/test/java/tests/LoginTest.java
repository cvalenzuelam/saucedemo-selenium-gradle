package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.InventoryPage;

public class LoginTest extends BaseTest {

    @Test(description = "Validar el inicio de sesión exitoso")
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);

        loginPage.navigateTo("https://www.saucedemo.com/");
        loginPage.login("standard_user", "secret_sauce");
        
        String title = inventoryPage.getTitle();
        Assert.assertTrue(title.equalsIgnoreCase("Products"), 
            "Esperaba 'Products' pero encontré: [" + title + "]");
    }

    @Test(description = "Validar el inicio de sesión fallido")
    public void testFailedLogin() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.navigateTo("https://www.saucedemo.com/");
        loginPage.login("locked_out_user", "secret_sauce");
        
        String actualError = loginPage.getErrorMessage().toLowerCase();
        Assert.assertTrue(actualError.contains("locked out"), 
            "El mensaje de error no contiene la frase esperada. Recibido: [" + actualError + "]");
    }
}
