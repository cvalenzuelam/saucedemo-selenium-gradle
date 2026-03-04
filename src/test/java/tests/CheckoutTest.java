package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class CheckoutTest extends BaseTest {

    @BeforeMethod
    public void prepareTest() {
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);
        
        loginPage.navigateTo("https://www.saucedemo.com/");
        loginPage.login("standard_user", "secret_sauce");
        
        inventoryPage.addFirstItemToCart();
        inventoryPage.goToCart();
        
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();
    }

    @Test(description = "Validar que el formulario requiera el First Name")
    public void testFirstNameRequired() {
        CheckoutStepOnePage checkoutStepOnePage = new CheckoutStepOnePage(driver);
        checkoutStepOnePage.fillInformation("", "Perez", "12345");
        checkoutStepOnePage.clickContinue();
        String actualError = checkoutStepOnePage.getErrorMessage().toLowerCase();
        Assert.assertTrue(actualError.contains("first name"), 
            "Esperaba que el error mencionara 'first name'. Recibido: [" + actualError + "]");
    }

    @Test(description = "Validar que el formulario requiera el Last Name")
    public void testLastNameRequired() {
        CheckoutStepOnePage checkoutStepOnePage = new CheckoutStepOnePage(driver);
        checkoutStepOnePage.fillInformation("Juan", "", "12345");
        checkoutStepOnePage.clickContinue();
        String actualError = checkoutStepOnePage.getErrorMessage().toLowerCase();
        Assert.assertTrue(actualError.contains("last name"),
            "Esperaba que el error mencionara 'last name'. Recibido: [" + actualError + "]");
    }

    @Test(description = "Validar que el formulario requiera el Postal Code")
    public void testPostalCodeRequired() {
        CheckoutStepOnePage checkoutStepOnePage = new CheckoutStepOnePage(driver);
        checkoutStepOnePage.fillInformation("Juan", "Perez", "");
        checkoutStepOnePage.clickContinue();
        String actualError = checkoutStepOnePage.getErrorMessage().toLowerCase();
        Assert.assertTrue(actualError.contains("postal code"),
            "Esperaba que el error mencionara 'postal code'. Recibido: [" + actualError + "]");
    }
}
