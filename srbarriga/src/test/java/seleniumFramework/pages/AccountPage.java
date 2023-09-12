package seleniumFramework.pages;

import org.openqa.selenium.By;
import seleniumFramework.core.PageBase;

public class AccountPage extends PageBase {

    By fieldName = By.xpath("//*[@id='nome']");

    By btnSubmit = By.xpath("//*[@type='submit']");

    By firstRowTableAccount = By.xpath("//table[@id='tabelaContas']//tbody//tr[1]//td");


    public void fillAccountName(String name){
        sendKeys(fieldName, name);
    }

    public void clickSubmitButton(){
        click(btnSubmit);
    }

    public String getTextAlert(){
        return getText(alertMessage);
    }

}
