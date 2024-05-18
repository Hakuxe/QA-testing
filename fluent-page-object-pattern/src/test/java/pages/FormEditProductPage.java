package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormEditProductPage {

    private final WebDriver browser;

    public FormEditProductPage(WebDriver browser) {
        this.browser = browser;
    }


    public String getToastMessage() {
        return browser.findElement(By.cssSelector(".toast.rounded")).getText();
    }

}
