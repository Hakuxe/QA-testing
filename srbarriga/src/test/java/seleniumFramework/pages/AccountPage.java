package seleniumFramework.pages;

import org.openqa.selenium.By;
import seleniumFramework.core.PageBase;

public class AccountPage extends PageBase {

    By fieldName = By.xpath("//*[@id='nome']");

    By btnSubmit = By.xpath("//*[@type='submit']");

    By editBtnFirstAccountTable = By.xpath("//table[@id='tabelaContas']//tbody//tr[1]//td//a[@href=\"/editarConta?id" +
            "=1900315\"]");

    By accountsTable= By.xpath("//table[@id='tabelaContas']");


    public void fillAccountName(String name) {
        clearAndSendKeys(fieldName, name);
    }

    public void clickSubmitButton() {
        click(btnSubmit);
    }

    public String getTextAlert() {
        return getText(alertMessage);
    }

    public void clickAlterAccount() {
        click(editBtnFirstAccountTable);
    }

    public String getTextCell(String colName, String cellValue){
       return  getCellOfTable(accountsTable,colName, cellValue).getText();
    }

}
