package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepTwoPage extends BasePage {

    // Locator por ID que es el estándar más seguro en Saucedemo
    private final By finishButton = By.id("finish");

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
    }

    public void clickFinish() {
        waitForPageLoad();
        // Usamos el ID directamente
        click(finishButton);
        waitForUrlContains("checkout-complete.html");
    }
}
