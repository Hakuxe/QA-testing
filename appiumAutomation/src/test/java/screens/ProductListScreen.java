package screens;

import base.BaseScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductListScreen extends BaseScreen {


    public ProductListScreen(WebDriver driver) {
        super(driver);
    }

    public AddProductScreen clickAddProduct(){
        driver.findElement(By.id("com.lojinha:id/floatingActionButton")).click();
        return new AddProductScreen(driver);
    }
}
