package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.InventoryPage;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @BeforeMethod
    public void setupPages() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
    }

    @Test(description = "Validar el inicio de sesión exitoso")
    public void testSuccessfulLogin() {
        loginPage.navigateTo("https://www.saucedemo.com/");
        loginPage.login("standard_user", "secret_sauce");
        
        Assert.assertEquals(inventoryPage.getTitle(), "Products", "No se redirigió correctamente a la página de inventario");
    }

    @Test(description = "Validar el inicio de sesión fallido")
    public void testFailedLogin() {
        loginPage.navigateTo("https://www.saucedemo.com/");
        loginPage.login("locked_out_user", "secret_sauce");
        
        String actualError = loginPage.getErrorMessage();
        Assert.assertTrue(actualError.contains("Epic sadface: Sorry, this user has been locked out."), "El mensaje de error no es el esperado");
    }
}
