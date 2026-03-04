package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage extends BasePage {

    // Locators
    private final By title = By.className("title");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartLink = By.className("shopping_cart_link");
    private final By inventoryItems = By.className("inventory_item");
    private final By sortDropdown = By.className("product_sort_container");
    private final By inventoryItemPrices = By.className("inventory_item_price");
    
    // Locators de acción
    private final By firstAddToCartBtn = By.cssSelector("[id^='add-to-cart']");
    private final By firstRemoveBtn = By.cssSelector("[id^='remove']");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return getText(title);
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

    public void waitForCartBadgeToDisappear() {
        waitForInvisibility(cartBadge);
    }

    public void goToCart() {
        click(cartLink);
        waitForUrlContains("cart.html");
    }

    public int getInventoryItemCount() {
        return driver.findElements(inventoryItems).size();
    }

    public void selectSortOption(String value) {
        waitForPageLoad();
        Select select = new Select(findElement(sortDropdown));
        select.selectByValue(value);
    }

    public List<Double> getProductPrices() {
        return driver.findElements(inventoryItemPrices).stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .collect(Collectors.toList());
    }

    public void clickProductByName(String name) {
        waitForPageLoad();
        click(By.xpath("//div[text()='" + name + "']"));
    }

    public void removeFirstItemFromCart() {
        click(firstRemoveBtn);
    }
}
