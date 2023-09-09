package seleniumFramework.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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


    protected void click(By locator){
        waitForElement(locator);
        driver.findElement(locator).click();
    }

    protected void sendKeys(By locator, String text){
        waitForElement(locator);
        driver.findElement(locator).sendKeys(text);
    }

    protected  String getValueInput(By locator){
        return driver.findElement(locator).getAttribute("value");
    }
}
