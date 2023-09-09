package seleniumFramework.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    public static WebDriver DRIVER;

    public static WebDriver createDriver() {
        if (DRIVER == null) {

            switch (Properties.BROWSER_DEFAULT) {
                case CHROME:
                    WebDriverManager.chromedriver().setup();
                    DRIVER = new ChromeDriver();
                    break;

                case FIREFOX:
                    WebDriverManager.firefoxdriver().setup();
                    DRIVER = new FirefoxDriver();
                    break;
            }

        }
        return DRIVER;
    }

    public static void quitDriver() {
        if (DRIVER != null) {
            DRIVER.quit();
            DRIVER = null;
        }
    }
}
