package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    public void navigateTo(String url) {
        driver.get(url);
        waitForPageLoad();
        hardWait(2000); 
    }

    public void hardWait(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }

    public void waitForPageLoad() {
        wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
    }

    protected void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    protected WebElement findElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        scrollToElement(element);
        return element;
    }

    public void click(By locator) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                scrollToElement(element);
                wait.until(ExpectedConditions.elementToBeClickable(element));
                jsClick(locator);
                hardWait(2000); 
                return;
            } catch (Exception e) {
                attempts++;
                if (attempts == 3) throw e;
                hardWait(2000);
            }
        }
    }

    protected void jsClick(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void type(By locator, String text) {
        WebElement element = findElement(locator);
        element.clear();
        if (text != null && !text.isEmpty()) {
            element.sendKeys(text);
            // Verificación de seguridad: si el texto no se escribió, reintentamos
            if (!element.getAttribute("value").equals(text)) {
                element.clear();
                element.sendKeys(text);
            }
        }
        hardWait(500);
    }

    public String getText(By locator) {
        return findElement(locator).getText();
    }

    public boolean isElementPresent(By locator) {
        try {
            return !driver.findElements(locator).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public void waitForUrlContains(String partialUrl) {
        wait.until(ExpectedConditions.urlContains(partialUrl));
        hardWait(2000); 
    }

    public void waitForInvisibility(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
}
