package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {

    private final By title = By.className("title");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartLink = By.className("shopping_cart_link");
    private final By firstAddToCartBtn = By.cssSelector("[id^='add-to-cart']");
    private final By firstRemoveBtn = By.cssSelector("[id^='remove']");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public void addFirstItemToCart() {
        waitForPageLoad();
        click(firstAddToCartBtn);
    }

    public String getCartItemCount() {
        return getText(cartBadge);
    }

    public boolean isCartBadgePresent() {
        return isElementPresent(cartBadge);
    }

    public void goToCart() {
        click(cartLink);
        waitForUrlContains("cart.html");
    }

    public void removeFirstItemFromCart() {
        click(firstRemoveBtn);
    }
}
