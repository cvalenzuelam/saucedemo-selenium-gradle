package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        type(usernameField, username);
        type(passwordField, password);
        click(loginButton);
        // Sincronización crítica: No seguir hasta que estemos dentro
        if (username.equals("standard_user")) {
            // Esperamos explícitamente a que el título de la siguiente página sea visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));
        }
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }
}
