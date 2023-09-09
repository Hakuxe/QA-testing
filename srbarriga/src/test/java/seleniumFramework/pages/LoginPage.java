package seleniumFramework.pages;

import org.openqa.selenium.By;
import seleniumFramework.core.PageBase;

public class LoginPage extends PageBase {


    By fieldEmail = By.xpath("//*[@id='email']");
    By fieldPassword = By.xpath("//*[@id='senha']");

    By loginBtn = By.xpath("//button[@type='submit']");

    public void fillUsername(String email){
        sendKeys(fieldEmail, email);
    }

    public void fillPassword(String password){
        sendKeys(fieldPassword, password);
    }

    public void clickSubmitButton(){
        click(loginBtn);
    }

}
