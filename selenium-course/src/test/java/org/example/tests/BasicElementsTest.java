package org.example.tests;

import org.example.base.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.List;

public class BasicElementsTest extends TestBase {
    @Test
    public void TextField() {

        By nameInput = By.id("elementosForm:nome");
        By surnameInput = By.id("elementosForm:sobrenome");
        By textArea = By.id("elementosForm:sugestoes");

        driver.findElement(nameInput).sendKeys("olá");
        driver.findElement(surnameInput).sendKeys("olá");
        driver.findElement(textArea).sendKeys("olá");

        Assert.assertEquals(driver.findElement(nameInput).getAttribute("value"), "olá");
        Assert.assertEquals(driver.findElement(surnameInput).getAttribute("value"), "olá");
        Assert.assertEquals(driver.findElement(textArea).getAttribute("value"), "olá");
    }

    @Test
    public void radioButton() {

        By radioMale = By.id("elementosForm:sexo:0");
        By radioFemale = By.id("elementosForm:sexo:1");

        driver.findElement(radioMale).click();

        Assert.assertTrue(driver.findElement(radioMale).isSelected());
        Assert.assertFalse(driver.findElement(radioFemale).isSelected());

    }

    @Test
    public void checkBox() {

        By pizzaCheckBox = By.id("elementosForm:comidaFavorita:2");
        By meatCheckBox = By.id("elementosForm:comidaFavorita:0");
        By chickenCheckBox = By.id("elementosForm:comidaFavorita:1");
        By vegCheckBox = By.id("elementosForm:comidaFavorita:3");

        driver.findElement(pizzaCheckBox).click();
        driver.findElement(meatCheckBox).click();

        Assert.assertTrue(driver.findElement(pizzaCheckBox).isSelected());
        Assert.assertTrue(driver.findElement(meatCheckBox).isSelected());
        Assert.assertFalse(driver.findElement(chickenCheckBox).isSelected());
        Assert.assertFalse(driver.findElement(vegCheckBox).isSelected());

    }

    @Test
    public void selects() {
        By schooling = By.id("elementosForm:escolaridade");

        WebElement element = driver.findElement(schooling);
        Select select = new Select(element);

        List<WebElement> options = select.getOptions();
        Assert.assertEquals(options.size(), 8);

        // select.selectByIndex(0);
        // Assert.assertEquals(select.getFirstSelectedOption().getText(), "1o grau incompleto");
        // select.selectByValue("2grauincomp");
        // Assert.assertEquals(select.getFirstSelectedOption().getText(), "2o grau incompleto");
        select.selectByVisibleText("2o grau incompleto");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "2o grau incompleto");
    }

    @Test
    @Ignore
    public void multiOptionSelect() {
        By schooling = By.id("elementosForm:esportes");

        WebElement element = driver.findElement(schooling);
        Select select = new Select(element);

        select.selectByVisibleText("Natacao");
        select.selectByVisibleText("Futebol");
        select.selectByVisibleText("Karate");

        List<WebElement> selectedOptions = select.getAllSelectedOptions();
        Assert.assertEquals(3, selectedOptions.size());

        select.deselectAll();
        selectedOptions = select.getAllSelectedOptions();
        Assert.assertEquals(0, selectedOptions.size());
    }

    @Test
    public void alertBasic() {
        By alertButton = By.id("alert");

        driver.findElement(alertButton).click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Alert Simples", alert.getText());
        alert.dismiss();
    }

    @Test
    public void alertConfirm() {
        By alertButton = By.id("confirm");

        driver.findElement(alertButton).click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Confirm Simples", alert.getText());

        alert.accept();
        Assert.assertEquals("Confirmado", alert.getText());

        alert.dismiss();

        driver.findElement(alertButton).click();
        alert = driver.switchTo().alert();

        Assert.assertEquals("Confirm Simples", alert.getText());
        alert.dismiss();

        Assert.assertEquals("Negado", alert.getText());

    }

    @Test
    public void challenge() {
        By nameInput = By.id("elementosForm:nome");
        By surnameInput = By.id("elementosForm:sobrenome");
        By radioMale = By.id("elementosForm:sexo:0");

        By pizzaCheckBox = By.id("elementosForm:comidaFavorita:2");
        By schooling = By.id("elementosForm:escolaridade");
        By sports = By.id("elementosForm:esportes");
        By submitButton = By.id("elementosForm:cadastrar");

        By descNome = By.id("descNome");
        By descSobrenome = By.id("descSobrenome");
        By descSexo = By.id("descSexo");
        By descComida = By.id("descComida");
        By descEscolaridade = By.id("descEscolaridade");
        By descEsportes = By.id("descEsportes");

        String name = "thiago";
        String surName = "jessico";
        String sex = "Masculino";
        String favFood = "Frango";
        String userSchooling = "Superior";
        String userSport = "Natacao";

        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(surnameInput).sendKeys(surName);
        //    driver.findElement(radioMale).click();
        driver.findElement(By.xpath("//label[text()=' " + sex + "']")).click();
        //    driver.findElement(pizzaCheckBox).click();
        driver.findElement(By.xpath(String.format("//label[text()=' %s']", favFood))).click();

        WebElement element = driver.findElement(schooling);
        Select select = new Select(element);
        select.selectByVisibleText(userSchooling);

        element = driver.findElement(sports);
        select = new Select(element);
        select.selectByVisibleText(userSport);

        driver.findElement(submitButton).click();


        Assert.assertTrue(driver.findElement(descNome).getText().contains(name));
        Assert.assertTrue(driver.findElement(descSobrenome).getText().contains(surName));
        Assert.assertTrue(driver.findElement(descSexo).getText().contains(sex));
        Assert.assertTrue(driver.findElement(descComida).getText().contains(favFood));
        //    Assert.assertTrue(driver.findElement(descEscolaridade).getText().contains(userSchooling));
        //    Assert.assertTrue(driver.findElement(descEsportes).getText().contains(userSport));

    }

    @Test
    public void iframe() {
        By frameId = By.id("frame1");
        By button = By.id("frameButton");

        driver.switchTo().frame("frame1");
        driver.findElement(button).click();

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.dismiss();

        driver.switchTo().defaultContent(); //voltar pra janela normal
        driver.findElement(By.id("elementosForm:nome")).sendKeys(alertText);
        Assert.assertEquals("Frame OK!", alertText);

    }

    @Test
    public void window() {
        By popup = By.id("buttonPopUpEasy");

        driver.findElement(popup).click();

        //external window
        driver.switchTo().window("Popup");
        driver.findElement(By.tagName("textarea")).sendKeys("Working");
//    driver.close();

        driver.switchTo().defaultContent();
        driver.findElement(By.tagName("textarea")).sendKeys("comeback");


    }

    @Test
    public void windowHandlers() {
        By popup = By.id("buttonPopUpEasy");

        driver.findElement(popup).click();

        //external window
        System.out.println(driver.getWindowHandle()); //mostrar a janela utilizada
        System.out.println(driver.getWindowHandles()); //mostrar os ids de todas as janela capturadas pelo selenium

        driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
        driver.findElement(By.tagName("textarea")).sendKeys("Working");
//    driver.close();

        driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
        driver.findElement(By.tagName("textarea")).sendKeys("comeback");


    }

    @Test
    public void scrollJS(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement frame = driver.findElement(By.id("frame2"));

        js.executeScript("window.scrollBy(0, arguments[0])", frame.getLocation().y);
        driver.switchTo().frame("frame2");

        driver.findElement(By.id("frameButton")).click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "Frame OK!");


    }
}
