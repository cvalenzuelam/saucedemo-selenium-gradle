package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class EndToEndTest extends BaseTest {

    @Test(description = "Validar el flujo completo de compra")
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
        
        // 2. Agregar al carrito y Navegar
        inventoryPage.addFirstItemToCart();
        inventoryPage.goToCart();

        // 3. Checkout Paso 1 (Formulario)
        cartPage.clickCheckout();
        checkoutStepOnePage.fillInformation("Juan", "Perez", "12345");
        // Aseguramos que pasamos al siguiente paso antes de seguir con el test
        checkoutStepOnePage.clickContinueValidating("checkout-step-two.html");

        // 4. Checkout Paso 2 (Resumen)
        checkoutStepTwoPage.clickFinish();

        // 5. Finalización
        Assert.assertEquals(checkoutCompletePage.getCompleteHeaderText(), "Thank you for your order!",
            "El mensaje de confirmación de compra no es el esperado");
    }
}
