package org.example.tests;

import org.example.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class WaitsTests extends TestBase {
    By button = By.id("buttonDelay");
    By input = By.id("novoCampo");

    @Test
    public void waitFixo() throws InterruptedException {
        String text = "Valor preenchido";

        driver.findElement(button).click();

        //Para a execução do teste durante o tempo específicado
        Thread.sleep(5000);

        WebElement inputSlowResponse = driver.findElement(input);
        inputSlowResponse.sendKeys(text);

        Assert.assertEquals(inputSlowResponse.getAttribute("value"), text);
    }

    @Test
    public void waitImplicita() {

        String text = "Valor preenchido";

        driver.findElement(button).click();

        /*
            OBS: Warning: Do not mix implicit and explicit waits. Doing so can cause unpredictable wait times. For
            example, setting an implicit wait of 10 seconds and an explicit wait of 15 seconds could cause
            a timeout to occur after 20 seconds.
         */

        // espera um elemento ATÉ o tempo estabelecido OBS: fica checando se o elemento apareceu
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);

        WebElement inputSlowResponse = driver.findElement(input);
        inputSlowResponse.sendKeys(text);

        Assert.assertEquals(inputSlowResponse.getAttribute("value"), text);

        //matar a espera para não afetar o código todo
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
    }

    @Test
    public void waitExplicita(){
        String text = "Valor preenchido";

        driver.findElement(button).click();

        //Mais indicado
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(input));

        WebElement inputSlowResponse = driver.findElement(input);
        inputSlowResponse.sendKeys(text);

        Assert.assertEquals(inputSlowResponse.getAttribute("value"), text);


    }
}
