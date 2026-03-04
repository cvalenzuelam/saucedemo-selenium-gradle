package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class CheckoutTest extends BaseTest {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutStepOnePage checkoutStepOnePage;

    @BeforeMethod
    public void setupPages() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutStepOnePage = new CheckoutStepOnePage(driver);
        
        loginPage.navigateTo("https://www.saucedemo.com/");
        loginPage.login("standard_user", "secret_sauce");
        
        inventoryPage.addFirstItemToCart();
        inventoryPage.goToCart();
        cartPage.clickCheckout();
    }

    @Test(description = "Validar que el formulario requiera el First Name")
    public void testFirstNameRequired() {
        checkoutStepOnePage.fillInformation("", "Perez", "12345");
        checkoutStepOnePage.clickContinue();
        Assert.assertTrue(checkoutStepOnePage.getErrorMessage().contains("First Name is required"));
    }

    @Test(description = "Validar que el formulario requiera el Last Name")
    public void testLastNameRequired() {
        checkoutStepOnePage.fillInformation("Juan", "", "12345");
        checkoutStepOnePage.clickContinue();
        Assert.assertTrue(checkoutStepOnePage.getErrorMessage().contains("Last Name is required"));
    }

    @Test(description = "Validar que el formulario requiera el Postal Code")
    public void testPostalCodeRequired() {
        checkoutStepOnePage.fillInformation("Juan", "Perez", "");
        checkoutStepOnePage.clickContinue();
        Assert.assertTrue(checkoutStepOnePage.getErrorMessage().contains("Postal Code is required"));
    }
}
