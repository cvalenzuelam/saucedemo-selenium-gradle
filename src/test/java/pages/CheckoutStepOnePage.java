package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutStepOnePage extends BasePage {

    // Locators usando IDs estándar que son muy estables en Saucedemo
    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By postalCodeField = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By errorMessageContainer = By.cssSelector("[data-test='error']");

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
    }

    public String getErrorMessage() {
        waitForPageLoad();
        WebElement errorElement = wait.until(d -> {
            WebElement e = d.findElement(errorMessageContainer);
            String text = e.getText();
            return (text != null && !text.trim().isEmpty()) ? e : null;
        });
        return errorElement.getText().trim();
    }

    public void fillInformation(String firstName, String lastName, String postalCode) {
        waitForPageLoad();
        type(firstNameField, firstName);
        type(lastNameField, lastName);
        type(postalCodeField, postalCode);
    }

    public void clickContinue() {
        click(continueButton);
    }
}
