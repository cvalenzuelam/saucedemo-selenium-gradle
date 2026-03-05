package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        // 30 segundos es el estándar de oro para estabilidad en CI
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void navigateTo(String url) {
        driver.get(url);
        waitForPageLoad();
    }

    public void waitForPageLoad() {
        wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
    }

    protected WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void click(By locator) {
        // En CI, el scroll es vital para que el elemento sea interactuable
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            element.click();
        } catch (Exception e) {
            // Fallback robusto con JS si el clic nativo falla en Headless
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public void type(By locator, String text) {
        WebElement element = findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        element.clear();
        if (text != null && !text.isEmpty()) {
            element.sendKeys(text);
            
            // Verificación para React/Vue: Si el sendKeys es muy rápido para el CI, forzamos el valor
            String currentValue = element.getAttribute("value");
            if (!text.equals(currentValue)) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", element, text);
                // Disparamos evento 'input' para que React sepa que el valor cambió
                ((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", element);
            }
        }
    }

    public String getText(By locator) {
        return findElement(locator).getText().trim();
    }

    public void waitForUrlContains(String partialUrl) {
        wait.until(ExpectedConditions.urlContains(partialUrl));
    }

    public void waitForInvisibility(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    
    public boolean isElementPresent(By locator) {
        try {
            return !driver.findElements(locator).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}
