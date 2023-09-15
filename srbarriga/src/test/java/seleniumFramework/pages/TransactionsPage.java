package seleniumFramework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import seleniumFramework.core.PageBase;

import java.util.List;

public class TransactionsPage extends PageBase {

    By errorMessages = By.xpath("//*[@role='alert']//li");
    By transactionsTable = By.xpath("//*[@id='tabelaExtrato']");

    By transactionTableLines = By.xpath("//*[@id='tabelaExtrato']//tbody//tr");

    By fieldTransactionType = By.xpath("//*[@id='tipo']");
    By fieldTransactionDate = By.xpath("//*[@id='data_transacao']");
    By fieldPaymentDate = By.xpath("//*[@id='data_pagamento']");
    By fieldDescription = By.xpath("//*[@id='descricao']");
    By fieldIntrested = By.xpath("//*[@id='interessado']");
    By fieldValue = By.xpath("//*[@id='valor']");
    By fieldAccount = By.xpath("//*[@id='conta']");
    By radioButtons = By.xpath("//*[@type=\"radio\" and @name='status']");


    public void clickSubmitButton() {
        click(btnSubmit);
    }

    public List<WebElement> getErrorListMessages() {
        return driver.findElements(errorMessages);
    }

    public boolean checkListContainsText(List<WebElement> list, String value) {
        boolean result = false;

        for (WebElement webElement : list) {
            if (webElement.getText().equals(value)) {
                result = true;
                break;
            }
        }

        return result;
    }

    public void fillFieldTransactionType(String value) {
        sendKeys(fieldTransactionType, value);
    }

    public void fillFieldTransactionDate(String value) {
        sendKeys(fieldTransactionDate, value);
    }

    public void fillFieldPaymentDate(String value) {
        sendKeys(fieldPaymentDate, value);
    }

    public void fillFieldDescription(String value) {
        sendKeys(fieldDescription, value);
    }

    public void fillFieldIntrested(String value) {
        sendKeys(fieldIntrested, value);
    }

    public void fillFieldValue(String value) {
        sendKeys(fieldValue, value);
    }

    public void selectAccount(String value) {
        comboBoxSelectByVisibleText(fieldAccount, value);
    }

    public void selectRadioByLabel(String labelText){
        String clearLocator = radioButtons.toString()
                .concat("/..//label[text() = '$text']")
                .replace("$text", labelText)
                .replace("By.xpath: ", "");
        System.out.println(clearLocator);

        click(By.xpath(clearLocator));
    }

    public String getTextCell(String colName, String cellValue){
        return  getCellOfTable(transactionsTable,colName, cellValue).getText();
    }

    public void removeTransaction(String colName, String cellValue){
        String xp = "./..//td//a";
        getCellOfTable(transactionsTable,colName, cellValue).findElement(By.xpath(xp)).click();
    }

    public int numberOfTransactions(){
       List<WebElement> list =  waitForElement(transactionsTable).findElements(By.xpath(".//tbody//tr"));
       return list.size();
    }

}
