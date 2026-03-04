package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailPage extends BasePage {

    private final By productName = By.cssSelector(".inventory_details_name");
    private final By productPrice = By.className("inventory_details_price");
    private final By backToProductsButton = By.id("back-to-products");

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return getText(productName);
    }

    public String getProductPrice() {
        return getText(productPrice);
    }

    public void clickBackToProducts() {
        click(backToProductsButton);
    }
}
