package seleniumFramework.pages;

import org.openqa.selenium.By;
import seleniumFramework.core.PageBase;

public class MainPage extends PageBase {


    By menuAccount = By.xpath("//*[@id='navbar']//li[@class='dropdown']");

    By insertAccountBtn = By.xpath("//a[@href='/addConta']");
    public void clickAccount(){
        click(menuAccount);
    }

    public void clickInsertAccount(){
        click(insertAccountBtn);
    }
}
