package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage extends BasePage {

    // Locators estandarizados con data-test e IDs
    private final By title = By.cssSelector("[data-test='title']");
    private final By cartBadge = By.cssSelector("[data-test='shopping-cart-badge']");
    private final By cartLink = By.cssSelector("[data-test='shopping-cart-link']");
    private final By inventoryItems = By.cssSelector("[data-test='inventory-item']");
    private final By sortDropdown = By.cssSelector("[data-test='product-sort-container']");
    private final By inventoryItemPrices = By.cssSelector("[data-test='inventory-item-price']");
    
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

    public void goToCart() {
        // En CI/CD el clic en el icono a veces es caprichoso
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
        // XPath sigue siendo útil para buscar por texto exacto
        click(By.xpath("//div[@data-test='inventory-item-name' and text()='" + name + "']"));
    }

    public void removeFirstItemFromCart() {
        click(firstRemoveBtn);
    }
}
