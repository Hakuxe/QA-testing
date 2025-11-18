package seleniumFramework.core;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBase {

    // Métodos comuns entre classes page
    protected WebDriver driver = null;
    protected WebDriverWait wait = null;

    public PageBase() {
        driver = DriverFactory.DRIVER;
        wait = new WebDriverWait (DriverFactory.DRIVER, Properties.TIMEOUT);
    }

    protected WebElement waitForElement(By locator){
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        wait.until(ExpectedConditions.elementToBeClickable(locator));

        return  driver.findElement(locator);
    }
    protected void waitForElementByTime(By locator, long  time){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

     protected boolean returnIfElementExists(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        return elements.size() != 0;
    }


    protected void click(By locator){
        waitForElement(locator);
        driver.findElement(locator).click();
    }

    protected void sendKeys(By locator, String text){
        waitForElement(locator);
        driver.findElement(locator).sendKeys(text);
    }

     protected void clearAndSendKeys(By locator, String text) {
        WebElement webElement = waitForElement(locator);
        webElement.clear();
        webElement.sendKeys(text);
    }

    protected  String getValueInput(By locator){
        return driver.findElement(locator).getAttribute("value");
    }

    protected String getText(By locator) {
        return waitForElement(locator).getText();
    }

     // table -----------------------------------------------------------------------------------------------------

    public WebElement getCellOfTable(By tableLocator, String columnName, String cellValue){
        //procurar coluna do registro
        WebElement table = driver.findElement(tableLocator);
        int idColumn = getColumnIndex(table, columnName);

        //encontrar a linha do registro
        int idRow = getRowIndex(table, idColumn, cellValue);

        //clicar no botao da celula encontrada
        return table.findElement(By.xpath(".//tr["+idRow+"]/td["+idColumn+"]"));
    }

    private int getRowIndex( WebElement table, int columnIndex, String cellValue) {
        List<WebElement> rows = table.findElements(By.xpath("./tbody/tr/td["+columnIndex+"]"));
        int rowIndex = -1;
        for(int i = 0; i < rows.size(); i++) {
            if(rows.get(i).getText().equals(cellValue)) {
                rowIndex = i+1;
                break;
            }
        }
        return rowIndex;
    }

    private int getColumnIndex(WebElement table, String columnName) {
        List<WebElement> columns = table.findElements(By.xpath(".//th"));
        int columnIndex = -1;

        for(int i = 0; i < columns.size(); i++) {
            if(columns.get(i).getText().equals(columnName)) {
                columnIndex = i+1;
                break;
            }
        }
        return columnIndex;
    }

     // Select ----------------------------------------------------------------------------------------------------

    protected void comboBoxSelectByVisibleText(By locator, String text) {
        Select comboBox = new Select(waitForElement(locator));
        comboBox.selectByVisibleText(text);
    }
}
