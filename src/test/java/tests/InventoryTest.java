package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.InventoryPage;
import pages.ProductDetailPage;
import java.util.Collections;
import java.util.List;

public class InventoryTest extends BaseTest {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private ProductDetailPage productDetailPage;

    @BeforeMethod
    public void setupPages() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        productDetailPage = new ProductDetailPage(driver);
        
        loginPage.navigateTo("https://www.saucedemo.com/");
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test(description = "Validar que los productos se puedan ordenar por precio de menor a mayor")
    public void testSortByPriceLowToHigh() {
        inventoryPage.selectSortOption("lohi");
        List<Double> prices = inventoryPage.getProductPrices();
        List<Double> sortedPrices = new java.util.ArrayList<>(prices);
        Collections.sort(sortedPrices);
        
        Assert.assertEquals(prices, sortedPrices, "Los precios no están ordenados correctamente de menor a mayor");
    }

    @Test(description = "Validar la navegación a los detalles de un producto")
    public void testNavigateToProductDetails() {
        String productName = "Sauce Labs Backpack";
        inventoryPage.clickProductByName(productName);
        
        Assert.assertEquals(productDetailPage.getProductName(), productName, "El nombre del producto no coincide en los detalles");
        Assert.assertTrue(productDetailPage.getProductPrice().contains("29.99"), "El precio del producto no es el esperado");
    }
}
