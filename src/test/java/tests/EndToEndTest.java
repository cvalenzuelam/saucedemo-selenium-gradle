package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class EndToEndTest extends BaseTest {

    @Test
    public void successfulCheckoutFlow() {
        // 1. Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        // 2. Add products to cart
        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertEquals(inventoryPage.getTitle(), "Products", "Should be on Inventory Page");
        inventoryPage.addBackpackToCart();
        inventoryPage.addBikeLightToCart();
        inventoryPage.goToCart();

        // 3. Cart - Proceed to checkout
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        // 4. Checkout Step One - Information
        CheckoutStepOnePage checkoutStepOne = new CheckoutStepOnePage(driver);
        checkoutStepOne.fillInformation("John", "Doe", "12345");

        // 5. Checkout Step Two - Overview
        CheckoutStepTwoPage checkoutStepTwo = new CheckoutStepTwoPage(driver);
        checkoutStepTwo.clickFinish();

        // 6. Checkout Complete - Verification
        CheckoutCompletePage checkoutComplete = new CheckoutCompletePage(driver);
        Assert.assertEquals(checkoutComplete.getCompleteMessage(), "Thank you for your order!", "Checkout complete message mismatch");
    }
}
