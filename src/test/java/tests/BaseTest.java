package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Leemos la propiedad "headless" desde la consola o sistema
        // Por defecto será true si no se especifica
        String headlessProp = System.getProperty("headless", "true");
        boolean isHeadless = Boolean.parseBoolean(headlessProp);

        ChromeOptions options = new ChromeOptions();
        
        if (isHeadless) {
            options.addArguments("--headless=new");
        }
        
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        
        // Solo WebDriverWait en BasePage, aquí solo timeout de carga inicial
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
        
        if (!isHeadless) {
            driver.manage().window().maximize();
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
