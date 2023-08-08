package org.example.tests;

import org.example.base.DSL;
import org.example.base.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class BasicElementsRefactorDslTest extends TestBase {

    private DSL dsl;


    @BeforeMethod
    public void initializeDsl() {
        dsl = new DSL(driver);
    }

    @Test
    public void TextField() {

        By nameInput = By.id("elementosForm:nome");
        By surnameInput = By.id("elementosForm:sobrenome");
        By textArea = By.id("elementosForm:sugestoes");

        dsl.sendKeys(nameInput, "olá");
        dsl.sendKeys(surnameInput, "olá");
        dsl.sendKeys(textArea, "olá");

        Assert.assertEquals(dsl.getElementAttribute(nameInput, "value"), "olá");
        Assert.assertEquals(dsl.getElementAttribute(surnameInput, "value"), "olá");
        Assert.assertEquals(dsl.getElementAttribute(textArea, "value"), "olá");
    }

    @Test
    public void radioButton() {
        By radioMale = By.id("elementosForm:sexo:0");
        By radioFemale = By.id("elementosForm:sexo:1");

        dsl.click(radioMale);

        Assert.assertTrue(driver.findElement(radioMale).isSelected());
        Assert.assertFalse(driver.findElement(radioFemale).isSelected());

    }

    @Test
    public void checkBox() {
        By pizzaCheckBox = By.id("elementosForm:comidaFavorita:2");
        By meatCheckBox = By.id("elementosForm:comidaFavorita:0");
        By chickenCheckBox = By.id("elementosForm:comidaFavorita:1");
        By vegCheckBox = By.id("elementosForm:comidaFavorita:3");


        dsl.click(pizzaCheckBox);
        dsl.click(meatCheckBox);


        Assert.assertTrue(dsl.isSelected(pizzaCheckBox));
        Assert.assertTrue(dsl.isSelected(meatCheckBox));
        Assert.assertFalse(dsl.isSelected(chickenCheckBox));
        Assert.assertFalse(dsl.isSelected(vegCheckBox));

    }

    @Test
    public void selects() {
        By schooling = By.id("elementosForm:escolaridade");


        Assert.assertEquals(dsl.getSelectNumberOfOptions(schooling), 8);

        // select.selectByIndex(0);
        // Assert.assertEquals(select.getFirstSelectedOption().getText(), "1o grau incompleto");
        // select.selectByValue("2grauincomp");
        // Assert.assertEquals(select.getFirstSelectedOption().getText(), "2o grau incompleto");
        dsl.selectByVisibleText(schooling, "2o grau incompleto");
        Assert.assertEquals(dsl.getTextFirstSelectedOption(schooling), "2o grau incompleto");
    }

    @Test
//    @Ignore
    public void multiOptionSelect() {
        By schooling = By.id("elementosForm:esportes");


        dsl.selectByVisibleText(schooling, "Natacao");
        dsl.selectByVisibleText(schooling, "Futebol");
        dsl.selectByVisibleText(schooling, "Karate");

        List<WebElement> selectedOptions = dsl.getAllSelectedOptions(schooling);
        Assert.assertEquals(3, selectedOptions.size());

        dsl.clearSelectedOption(schooling);
        selectedOptions = dsl.getAllSelectedOptions(schooling);
        Assert.assertEquals(0, selectedOptions.size());
    }

    @Test
    public void alertBasic() {
        By alertButton = By.id("alert");

        dsl.click(alertButton);

        Assert.assertEquals("Alert Simples", dsl.getAlertText());
        dsl.dismissAlert();
    }

    @Test
    public void alertConfirm() {
        By alertButton = By.id("confirm");

        dsl.click(alertButton);


        Assert.assertEquals("Confirm Simples", dsl.getAlertText());

        dsl.acceptAlert();
        Assert.assertEquals("Confirmado", dsl.getAlertText());

        dsl.dismissAlert();

        dsl.click(alertButton);


        Assert.assertEquals("Confirm Simples", dsl.getAlertText());
        dsl.dismissAlert();

        Assert.assertEquals("Negado", dsl.getAlertText());

    }

    @Test
    public void iframe() {
        By button = By.id("frameButton");

        dsl.switchFocusToFrame("frame1");
        dsl.click(button);


        String alertText = dsl.getAlertText();
        dsl.dismissAlert();

        dsl.switchFocusToDefaultContent(); //voltar pra janela normal
        dsl.sendKeys(By.id("elementosForm:nome"),alertText);
        Assert.assertEquals("Frame OK!", alertText);

    }

    @Test
    public void window() {
        By popup = By.id("buttonPopUpEasy");
        By textarea = By.tagName("textarea");

        dsl.click(popup);

        //external window
        dsl.switchFocusToWindow("Popup");
        dsl.sendKeys(textarea,"Working");
//    driver.close();

        dsl.switchFocusToDefaultContent();
        dsl.sendKeys(textarea,"comeback");
    }

    @Test
    public void windowHandlers() {
        By popup = By.id("buttonPopUpEasy");
        By textarea = By.tagName("textarea");

        dsl.click(popup);

        //external window
        System.out.println(driver.getWindowHandle()); //mostrar a janela utilizada
        System.out.println(driver.getWindowHandles()); //mostrar os ids de todas as janela capturadas pelo selenium

        dsl.switchFocusToWindow((String) driver.getWindowHandles().toArray()[1]);
        dsl.sendKeys(textarea,"Working");
//    driver.close();

        dsl.switchFocusToWindow((String) driver.getWindowHandles().toArray()[0]);
        dsl.sendKeys(textarea,"comeback");


    }
}
