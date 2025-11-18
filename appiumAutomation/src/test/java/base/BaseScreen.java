package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BaseScreen {
    final protected WebDriver driver;

    public BaseScreen(WebDriver driver) {
        this.driver = driver;
    }

    public String getToastMessage() {
        return driver.findElement(By.xpath("//android.widget.Toast")).getText();
    }
}
