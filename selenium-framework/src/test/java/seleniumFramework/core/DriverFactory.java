package seleniumFramework.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    public static WebDriver DRIVER;

    public static WebDriver createDriver() {
        if (DRIVER == null) {
            DRIVER = new ChromeDriver();
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
