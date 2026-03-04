package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.InventoryPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LogoutTest extends BaseTest {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @BeforeMethod
    public void setupPages() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        
        loginPage.navigateTo("https://www.saucedemo.com/");
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test(description = "Validar el cierre de sesión exitoso")
    public void testLogout() {
        driver.findElement(By.id("react-burger-menu-btn")).click();
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link"))).click();
        
        // Esperar a que el botón de login sea visible tras el logout
        boolean isLoginVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button"))).isDisplayed();
        
        Assert.assertTrue(isLoginVisible, "El botón de login no es visible tras logout");
        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"), "No se redirigió a la página de login");
    }
}
