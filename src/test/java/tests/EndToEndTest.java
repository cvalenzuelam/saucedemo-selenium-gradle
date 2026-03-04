package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class EndToEndTest extends BaseTest {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutStepOnePage checkoutStepOnePage;
    private CheckoutStepTwoPage checkoutStepTwoPage;
    private CheckoutCompletePage checkoutCompletePage;

    @BeforeMethod
    public void setupPages() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutStepOnePage = new CheckoutStepOnePage(driver);
        checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);
    }

    @Test(description = "Validar el flujo completo de compra")
    public void testFullPurchaseFlow() {
        // 1. Login
        loginPage.navigateTo("https://www.saucedemo.com/");
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(inventoryPage.getTitle(), "Products");

        // 2. Agregar al carrito
        inventoryPage.addFirstItemToCart();
        Assert.assertEquals(inventoryPage.getCartItemCount(), "1");
        inventoryPage.goToCart();

        // 3. Checkout Step One
        cartPage.clickCheckout();
        checkoutStepOnePage.fillInformation("Juan", "Perez", "12345");
        checkoutStepOnePage.clickContinue();

        // 4. Checkout Step Two
        checkoutStepTwoPage.clickFinish();

        // 5. Checkout Complete
        Assert.assertEquals(checkoutCompletePage.getCompleteHeaderText(), "Thank you for your order!");
    }
}
