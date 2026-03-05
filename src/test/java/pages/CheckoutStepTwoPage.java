package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepTwoPage extends BasePage {

    private final By finishButton = By.id("finish");

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
    }

    public void clickFinish() {
        click(finishButton);
    }
}
