package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {

    private final By title = By.cssSelector(".title");
    private final By shoppingCartIcon = By.className("shopping_cart_link");
    private final By addBackpackToCartButton = By.id("add-to-cart-sauce-labs-backpack");
    private final By addBikeLightToCartButton = By.id("add-to-cart-sauce-labs-bike-light");

    private final By shoppingCartBadge = By.className("shopping_cart_badge");
    private final By removeBackpackButton = By.id("remove-sauce-labs-backpack");
    private final By firstItemName = By.cssSelector(".inventory_item_name");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return getText(title);
    }

    public void addBackpackToCart() {
        click(addBackpackToCartButton);
    }

    public void removeBackpackFromCart() {
        click(removeBackpackButton);
    }

    public String getCartBadgeCount() {
        return getText(shoppingCartBadge);
    }

    public boolean isCartBadgePresent() {
        return driver.findElements(shoppingCartBadge).size() > 0;
    }

    public String getFirstItemName() {
        return getText(firstItemName);
    }

    public void addBikeLightToCart() {
        click(addBikeLightToCartButton);
    }

    public void goToCart() {
        click(shoppingCartIcon);
    }
}
