package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepTwoPage extends BasePage {

    // Selector con data-test exacto
    private final By finishButton = By.cssSelector("[data-test='finish']");

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
    }

    public void clickFinish() {
        waitForPageLoad();
        // El botón Finish a veces tarda en cargarse por el resumen del carrito
        click(finishButton);
        waitForUrlContains("checkout-complete.html");
    }
}
