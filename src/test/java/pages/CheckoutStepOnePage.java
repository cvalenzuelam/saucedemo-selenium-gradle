package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepOnePage extends BasePage {

    // Locators robustos con data-test
    private final By firstNameField = By.cssSelector("[data-test='firstName']");
    private final By lastNameField = By.cssSelector("[data-test='lastName']");
    private final By postalCodeField = By.cssSelector("[data-test='postalCode']");
    private final By continueButton = By.cssSelector("[data-test='continue']");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public void fillInformation(String firstName, String lastName, String postalCode) {
        waitForPageLoad();
        type(firstNameField, firstName);
        type(lastNameField, lastName);
        type(postalCodeField, postalCode);
    }

    public void clickContinue() {
        jsClick(continueButton);
        // Esperamos a que la navegación ocurra si los datos son válidos
        // pero como los tests prueban fallos, no forzamos URL aquí.
    }
}
