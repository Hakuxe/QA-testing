package org.example.base;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class DSL extends TestBase {
    private WebDriver driver;

    public DSL(WebDriver driver) {
        this.driver = driver;
    }

    public void sendKeys(By locator, String value) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(value);
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public String getElementAttribute(By locator, String attribute) {
        return driver.findElement(locator).getAttribute(attribute);
    }

    public boolean isSelected(By locator) {
        return driver.findElement(locator).isSelected();
    }


    // selects
    public void selectByVisibleText(By locator, String text) {
        WebElement el = driver.findElement(locator);
        Select select = new Select(el);

        select.selectByVisibleText(text);
    }

    public int getSelectNumberOfOptions(By locator) {
        WebElement el = driver.findElement(locator);
        Select select = new Select(el);
        List<WebElement> options = select.getOptions();

        return options.size();
    }

    public String getTextFirstSelectedOption(By locator) {
        WebElement el = driver.findElement(locator);
        Select select = new Select(el);

        return select.getFirstSelectedOption().getText();
    }

    public List<WebElement> getAllSelectedOptions(By locator) {
        WebElement el = driver.findElement(locator);
        Select select = new Select(el);
        List<WebElement> options = select.getAllSelectedOptions();

        return options;
    }

    public void clearSelectedOption(By locator) {
        WebElement el = driver.findElement(locator);
        Select select = new Select(el);
        select.deselectAll();
    }


    // Alerts
    public void dismissAlert() {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    public void acceptAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public String getAlertText() {
        Alert alert = driver.switchTo().alert();

        return alert.getText();
    }


    // windows
    public void switchFocusToFrame(String locator) {
        driver.switchTo().frame(locator);
    }

    public void switchFocusToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public void switchFocusToWindow(String locator) {
        driver.switchTo().window(locator);
    }

}
