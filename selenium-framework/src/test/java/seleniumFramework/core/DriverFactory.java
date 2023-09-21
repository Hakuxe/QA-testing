package seleniumFramework.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    public static WebDriver DRIVER;

    static String execution = Properties.EXECUTION;
    static Properties.Browsers browsers = Properties.BROWSER;
    static String seleniumHubUrl = Properties.SELENIUM_HUB_URL;

    public static WebDriver createDriver() {


        if (execution.equalsIgnoreCase("local")) {
            switch (browsers) {
                case FIREFOX:
                    DRIVER = new FirefoxDriver();
                    break;
                case CHROME:
                    DRIVER = new ChromeDriver();
                    break;
            }
        }
        if (execution.equalsIgnoreCase("remota")) {
            DesiredCapabilities capabilities = null;
            switch (Properties.BROWSER) {
                case FIREFOX:
                    capabilities = DesiredCapabilities.firefox();
                    break;
                case CHROME:
                    capabilities = DesiredCapabilities.chrome();
                    break;
            }
            try {
                DRIVER = new RemoteWebDriver(new URL(seleniumHubUrl), capabilities);
            } catch (MalformedURLException e) {
                System.err.println("Falha na conexão com o GRID");
                e.printStackTrace();
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
