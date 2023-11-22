package seleniumFramework.pages;

import org.openqa.selenium.By;
import seleniumFramework.core.PageBase;

public class LoginPage extends PageBase {


    By fieldUsername = By.xpath("//*[@id='username']");
    By fieldPassword = By.xpath("//*[@id='password']");
    By loginBtn = By.xpath("//*[@type='submit']");
    By checkBoxOnlyThisIp = By.xpath("//*[@for='secure-session']");

    By errorMessage = By.xpath("//div[contains(@class, 'alert-danger')]");

    By userInfo = By.xpath("//*[@class='user-info']");

    public void fillUsername(String email){
        sendKeys(fieldUsername, email);
    }

    public void fillPassword(String password){
        sendKeys(fieldPassword, password);
    }

    public void clickSubmitButton(){
        click(loginBtn);
    }

    public void uncheckAllowOnlySessionFromIp(){
        click(checkBoxOnlyThisIp);
    }

    public String getTextErrorLoginMessage(){
        return getText(errorMessage);
    }

    public boolean checkUserInfoIsVisible(){
        return returnIfElementExists(userInfo);
    }

}
