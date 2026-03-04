package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepTwoPage extends BasePage {

    // Locator robusto
    private final By finishButton = By.cssSelector("[data-test='finish']");

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
    }

    public void clickFinish() {
        waitForPageLoad();
        jsClick(finishButton);
        waitForUrlContains("checkout-complete.html");
    }
}
