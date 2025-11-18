package screens;

import base.BaseScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddProductScreen extends BaseScreen {


    public AddProductScreen(WebDriver driver) {
        super(driver);
    }

    public AddProductScreen fillProductName(String name) {
        driver.findElement(By.id("com.lojinha:id/productName")).click();
        driver.findElement(By.id("com.lojinha:id/productName"))
                .findElement(By.id("com.lojinha:id/editText"))
                .sendKeys(name);
        return this;
    }

    public AddProductScreen fillProductValue(String value) {
        driver.findElement(By.id("com.lojinha:id/productValue")).click();
        driver.findElement(By.id("com.lojinha:id/productValue"))
                .findElement(By.id("com.lojinha:id/editText"))
                .sendKeys(value);
        return this;
    }

    public AddProductScreen fillProductColor(String color) {
        driver.findElement(By.id("com.lojinha:id/productColors")).click();
        driver
                .findElement(By.id("com.lojinha:id/productColors"))
                .findElement(By.id("com.lojinha:id/editText"))
                .sendKeys(color);

        return this;
    }

    public AddProductScreen clickSaveProductButton() {
        driver.findElement(By.id("com.lojinha:id/saveButton")).click();
        return this;
    }

    public String returnErrorMessage(){
       return getToastMessage();
    }




}
