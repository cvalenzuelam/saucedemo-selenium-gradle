package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {

    private final By title = By.cssSelector(".title");
    private final By shoppingCartIcon = By.className("shopping_cart_link");
    private final By addBackpackToCartButton = By.id("add-to-cart-sauce-labs-backpack");
    private final By addBikeLightToCartButton = By.id("add-to-cart-sauce-labs-bike-light");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return getText(title);
    }

    public void addBackpackToCart() {
        click(addBackpackToCartButton);
    }

    public void addBikeLightToCart() {
        click(addBikeLightToCartButton);
    }

    public void goToCart() {
        click(shoppingCartIcon);
    }
}
