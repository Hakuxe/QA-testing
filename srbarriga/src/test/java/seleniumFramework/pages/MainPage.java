package seleniumFramework.pages;

import org.openqa.selenium.By;
import seleniumFramework.core.PageBase;

public class MainPage extends PageBase {


    By menuAccount = By.xpath("//*[@id='navbar']//li[@class='dropdown']");

    By insertAccountBtn = By.xpath("//a[@href='/addConta']");
    By linkAccountList = By.xpath("//a[@href='/contas']");

    public void clickAccount(){
        click(menuAccount);
    }

    public void clickInsertAccount(){
        click(insertAccountBtn);
    }

    public void clickAccountListButton(){
    click(linkAccountList);
    }
}
