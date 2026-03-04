package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutStepOnePage extends BasePage {

    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By postalCodeField = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By errorMessageContainer = By.cssSelector("[data-test='error']");

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
    }

    public String getErrorMessage() {
        // Esperamos a que el mensaje aparezca y tenga contenido real
        wait.until(d -> !d.findElement(errorMessageContainer).getText().trim().isEmpty());
        return getText(errorMessageContainer).trim();
    }

    public void fillInformation(String firstName, String lastName, String postalCode) {
        waitForPageLoad();
        type(firstNameField, firstName);
        type(lastNameField, lastName);
        type(postalCodeField, postalCode);
    }

    public void clickContinue() {
        click(continueButton);
        // Si no hay errores, Saucedemo navega a step-two
        // Si hay errores, se queda en la misma URL (por eso no forzamos wait aquí si se usa en tests de fallo)
    }

    public void clickContinueValidating(String nextPartialUrl) {
        click(continueButton);
        waitForUrlContains(nextPartialUrl);
    }
}
