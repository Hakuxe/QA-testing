package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormProductPage {

    private final WebDriver browser;

    public FormProductPage(WebDriver browser) {
        this.browser = browser;
    }


    public FormProductPage fillProductName(String name) {
        browser.findElement(By.id("produtonome")).sendKeys(name);
        return new FormProductPage(browser);
    }

    public FormProductPage fillProductValue(String value) {
        browser.findElement(By.id("produtovalor")).sendKeys(value);
        return new FormProductPage(browser);
    }

    public FormProductPage fillProductColors(String colors) {
        browser.findElement(By.id("produtocores")).sendKeys(colors);
        return new FormProductPage(browser);
    }

    public ProductListPage sendFormWithErrors(){
        browser.findElement(By.cssSelector("[type='submit']")).click();
        return new ProductListPage(browser);
    }

    public FormEditProductPage saveProduct(){
        browser.findElement(By.cssSelector("[type='submit']")).click();
        return new FormEditProductPage(browser);
    }

}
