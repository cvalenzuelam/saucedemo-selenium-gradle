package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {

    // Locator robusto
    private final By completeHeader = By.cssSelector("[data-test='complete-header']");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String getCompleteHeaderText() {
        return getText(completeHeader);
    }
}
