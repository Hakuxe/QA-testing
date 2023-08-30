package seleniumFramework.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBase {

    // Métodos comuns entre classes page
    protected WebDriver driver = null;
    protected WebDriverWait wait = null;

    public PageBase() {
        driver = DriverFactory.DRIVER;
        wait = new WebDriverWait (DriverFactory.DRIVER, Properties.TIMEOUT);
    }
}
