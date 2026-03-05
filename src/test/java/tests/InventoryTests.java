package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class InventoryTests extends BaseTest {

    @Test
    public void verifyProductInventoryTitle() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertEquals(inventoryPage.getTitle(), "Products", "Title should be 'Products'");
    }

    @Test
    public void addAndRemoveProductFromCart() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        
        // Add to cart
        inventoryPage.addBackpackToCart();
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), "1", "Badge count should be 1");

        // Remove from cart
        inventoryPage.removeBackpackFromCart();
        Assert.assertFalse(inventoryPage.isCartBadgePresent(), "Badge should not be present after removing item");
    }
}
