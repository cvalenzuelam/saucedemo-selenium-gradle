package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.InventoryPage;

public class CartTest extends BaseTest {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @BeforeMethod
    public void setupPages() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        
        loginPage.navigateTo("https://www.saucedemo.com/");
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test(description = "Validar que el contador del carrito se actualice correctamente")
    public void testCartBadgeCount() {
        inventoryPage.addFirstItemToCart();
        Assert.assertEquals(inventoryPage.getCartItemCount(), "1");
        
        inventoryPage.removeFirstItemFromCart();
        boolean badgeExists = true;
        try {
            inventoryPage.getCartItemCount();
        } catch (Exception e) {
            badgeExists = false;
        }
        Assert.assertFalse(badgeExists, "El badge del carrito no debería existir si está vacío");
    }
}
