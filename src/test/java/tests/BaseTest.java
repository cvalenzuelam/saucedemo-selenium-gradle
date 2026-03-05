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
        String ciChromeDriver = System.getenv("CI_CHROMEDRIVER_PATH");
        if (ciChromeDriver != null && !ciChromeDriver.isEmpty()) {
            System.setProperty("webdriver.chrome.driver", ciChromeDriver);
        }
        
        System.setProperty("webdriver.chrome.silentOutput", "true");
        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);

        ChromeOptions options = new ChromeOptions();
        
        if (ciChromeDriver != null && !ciChromeDriver.isEmpty()) {
            options.setBinary("/usr/bin/google-chrome");
        }
        
        options.addArguments("--headless=new"); 
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-setuid-sandbox");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-translate");
        options.addArguments("--disable-features=VizDisplayCompositor");
        
        options.setCapability("goog:loggingPrefs", java.util.Map.of("browser", "OFF", "driver", "OFF"));
        options.setCapability("acceptInsecureCerts", true);

        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
