package screens;

import base.BaseScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginScreen extends BaseScreen {


    public LoginScreen(WebDriver driver) {
        super(driver);
    }

    public LoginScreen fillUserName(String username){
        driver.findElement(By.id("com.lojinha:id/productName")).click();
        driver.findElement(By.id("com.lojinha:id/productName"))
                .findElement(By.id("com.lojinha:id/editText")).sendKeys("ps2");

        return new LoginScreen(driver);

    }

    public LoginScreen fillPassword(String password){
        driver.findElement(By.id("com.lojinha:id/productValue")).click();
        driver.findElement(By.id("com.lojinha:id/productValue"))
                .findElement(By.id("com.lojinha:id/editText")).sendKeys("200");

        return this;
    }

    public ProductListScreen logIn(){
        driver.findElement(By.id("com.lojinha:id/saveButton")).click();

        return new ProductListScreen(driver);
    }
}
