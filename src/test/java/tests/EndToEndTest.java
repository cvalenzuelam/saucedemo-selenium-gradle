package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class EndToEndTest extends BaseTest {

    @Test(description = "Validar el flujo completo de compra: Login -> Add -> Checkout -> Success")
    public void testFullPurchaseFlow() {
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutStepOnePage checkoutStepOnePage = new CheckoutStepOnePage(driver);
        CheckoutStepTwoPage checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(driver);

        // 1. Login
        loginPage.navigateTo("https://www.saucedemo.com/");
        loginPage.login("standard_user", "secret_sauce");
        
        // 2. Inventario y Carrito
        inventoryPage.addFirstItemToCart();
        inventoryPage.goToCart();

        // 3. Checkout Paso 1 (Información)
        cartPage.clickCheckout();
        checkoutStepOnePage.fillInformation("QA", "Engineer", "12345");
        checkoutStepOnePage.clickContinueValidating("checkout-step-two.html");

        // 4. Checkout Paso 2 (Resumen)
        checkoutStepTwoPage.clickFinish();

        // 5. Finalización
        String message = checkoutCompletePage.getCompleteHeaderText();
        Assert.assertEquals(message, "Thank you for your order!", "El mensaje de éxito no es el esperado");
    }
}
