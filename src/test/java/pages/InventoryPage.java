package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage extends BasePage {

    // Locators
    private final By title = By.className("title");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartLink = By.className("shopping_cart_link");
    private final By inventoryItems = By.className("inventory_item");
    private final By addToCartButton = By.cssSelector(".btn_inventory");
    private final By sortDropdown = By.className("product_sort_container");
    private final By inventoryItemPrices = By.className("inventory_item_price");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return getText(title);
    }

    public void addFirstItemToCart() {
        click(addToCartButton);
    }

    public String getCartItemCount() {
        return getText(cartBadge);
    }

    public boolean isCartBadgePresent() {
        return isElementPresent(cartBadge);
    }

    public void goToCart() {
        click(cartLink);
    }

    public int getInventoryItemCount() {
        List<WebElement> items = driver.findElements(inventoryItems);
        return items.size();
    }

    public void selectSortOption(String value) {
        // value: az, za, lohi, hilo
        org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(findElement(sortDropdown));
        select.selectByValue(value);
    }

    public List<Double> getProductPrices() {
        return driver.findElements(inventoryItemPrices).stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .collect(Collectors.toList());
    }

    public void clickProductByName(String name) {
        driver.findElement(By.xpath("//div[text()='" + name + "']")).click();
    }

    public void removeFirstItemFromCart() {
        click(By.cssSelector(".btn_secondary.btn_inventory"));
    }
}
