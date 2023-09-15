package seleniumFramework.pages;

import org.openqa.selenium.By;
import seleniumFramework.core.PageBase;

public class MainPage extends PageBase {


    By menuAccount = By.xpath("//*[@id='navbar']//li[@class='dropdown']");

    By balanceTable = By.xpath("//*[@id='tabelaSaldo']");

    By insertAccountBtn = By.xpath("//a[@href='/addConta']");
    By linkAccountList = By.xpath("//a[@href='/contas']");
    By linkTransactionsPage = By.xpath("//a[@href='/movimentacao']");
    By linkExtract = By.xpath("//a[@href='/extrato']");
    By linkHome = By.xpath("//a[@href='/']");

    public void clickAccount() {
        click(menuAccount);
    }

    public void clickInsertAccount() {
        click(insertAccountBtn);
    }

    public void clickAccountListButton() {
        click(linkAccountList);
    }

    public void goToHome(){
        click(linkHome);
    }

    public void goToTransactionsPage(){
        click(linkTransactionsPage);
    }

    public void goToMonthResume(){
        click(linkExtract);
    }

    public String getTextAlert() {
        return getText(alertMessage);
    }

    public String getAccountBalance(String accountName){
        By help = By.xpath("./../td[last()]");

        return getCellOfTable(balanceTable, "Conta", accountName)
                .findElement(help)
                .getText();
    }
}
