package seleniumFramework.core;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    // TODO mudar estrutura driver factory
    private static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<WebDriver>() {
        @Override
        protected synchronized WebDriver initialValue() {
            return initDriver();
        }
    };

    public static WebDriver getDriver() {
        return threadLocal.get();
    }

    public static WebDriver initDriver() {
        WebDriver DRIVER = null;
        switch (GlobalProperties.BROWSER_DEFAULT) {
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                DRIVER = new ChromeDriver();
                break;

            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                DRIVER = new FirefoxDriver();
                break;

        }
        return DRIVER;
    }

    public static void quitDriver() {
        WebDriver DRIVER = getDriver();
        if (DRIVER != null) {
            DRIVER.quit();
            DRIVER = null;
        }

        if (threadLocal != null) {
            threadLocal.remove();
        }
    }
}
