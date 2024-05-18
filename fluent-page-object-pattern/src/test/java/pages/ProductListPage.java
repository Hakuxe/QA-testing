package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductListPage {

    private final WebDriver browser;

    public ProductListPage(WebDriver browser) {
        this.browser = browser;
    }


    public FormProductPage openFormToAddProduct() {
        browser.findElement(By.linkText("Adicionar produto".toUpperCase())).click();
        return new FormProductPage(browser);
    }

    public String getToastMessage() {
        return browser.findElement(By.cssSelector(".toast.rounded")).getText();
    }

}
