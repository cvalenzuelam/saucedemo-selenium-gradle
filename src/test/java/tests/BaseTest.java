package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Silenciamos logs innecesarios
        System.setProperty("webdriver.chrome.silentOutput", "true");
        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);

        ChromeOptions options = new ChromeOptions();
        
        // Modo Headless moderno y estable
        options.addArguments("--headless=new"); 
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");
        
        // Desactivamos extensiones para velocidad en CI
        options.addArguments("--disable-extensions");
        
        options.setCapability("goog:loggingPrefs", java.util.Map.of("browser", "OFF", "driver", "OFF"));

        driver = new ChromeDriver(options);
        
        // Timeouts de red y carga
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        
        // NOTA: No usamos implicitlyWait para evitar conflictos con WebDriverWait en BasePage
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                // Ignore
            }
        }
    }
}
