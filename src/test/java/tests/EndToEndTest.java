package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class EndToEndTest extends BaseTest {

    @Test(description = "Validar el flujo completo de compra: Login -> Add to Cart -> Checkout")
    public void testFullPurchaseFlow() {
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutStepOnePage checkoutStepOnePage = new CheckoutStepOnePage(driver);
        CheckoutStepTwoPage checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(driver);

        // 1. Navegación y Login
        loginPage.navigateTo("https://www.saucedemo.com/");
        loginPage.login("standard_user", "secret_sauce");
        
        // 2. Verificar que estamos en Inventario y agregar producto
        Assert.assertTrue(inventoryPage.getTitle().contains("Products"), "No se llegó a la página de productos");
        inventoryPage.addFirstItemToCart();
        
        // 3. Ir al carrito
        inventoryPage.goToCart();
        
        // 4. Iniciar Checkout
        cartPage.clickCheckout();
        
        // 5. Llenar información personal
        checkoutStepOnePage.fillInformation("QA", "Engineer", "12345");
        checkoutStepOnePage.clickContinueValidating("checkout-step-two.html");

        // 6. Finalizar compra
        checkoutStepTwoPage.clickFinish();

        // 7. Validar éxito
        String successMessage = checkoutCompletePage.getCompleteHeaderText();
        Assert.assertEquals(successMessage, "Thank you for your order!", "El flujo de compra no finalizó correctamente");
    }
}
