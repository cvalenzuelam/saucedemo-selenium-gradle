package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepOnePage extends BasePage {

    // Locators
    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By postalCodeField = By.id("postal-code");
    private final By continueButton = By.id("continue");

    private final By errorMessage = By.cssSelector("[data-test='error']");

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
        // waitForUrlContains("checkout-step-one.html"); 
        // Comentado para no forzarlo si se usa antes de navegar, 
        // pero lo haremos en los tests de forma manual para mayor claridad.
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public void fillInformation(String firstName, String lastName, String postalCode) {
        type(firstNameField, firstName);
        type(lastNameField, lastName);
        type(postalCodeField, postalCode);
    }

    public void clickContinue() {
        click(continueButton);
    }
}
