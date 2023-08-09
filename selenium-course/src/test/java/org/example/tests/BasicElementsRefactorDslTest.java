package org.example.tests;

import org.example.base.DSL;
import org.example.base.TestBase;
import org.example.pages.BasicElementsPOMPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class BasicElementsRefactorDslTest extends TestBase {

    private DSL dsl;
    BasicElementsPOMPage basicElementsPOMPage;


    @BeforeMethod
    public void initializeDsl() {
        basicElementsPOMPage = new BasicElementsPOMPage(driver);
    }

    @Test
    public void TextField() {

        basicElementsPOMPage.fillName("olá");
        basicElementsPOMPage.fillSurname("olá");
        basicElementsPOMPage.fillTextarea("olá");

        Assert.assertEquals(basicElementsPOMPage.getNameInputAttribute("value"), "olá");
        Assert.assertEquals(basicElementsPOMPage.getSurnameInputAttribute("value"), "olá");
        Assert.assertEquals(basicElementsPOMPage.getTextAreaAttribute("value"), "olá");
    }

    @Test
    public void radioButton() {
        basicElementsPOMPage.fillGender("Masculino");

        Assert.assertTrue(basicElementsPOMPage.radioMaleIsSelected());
        Assert.assertFalse(basicElementsPOMPage.radioMaleIsSelected());
    }

    @Test
    public void checkBox() {

        basicElementsPOMPage.selectPizzaCheckBox();
        basicElementsPOMPage.selectMeatCheckBox();

        Assert.assertTrue(basicElementsPOMPage.isCheckboxPizzaSelected());
        Assert.assertTrue(basicElementsPOMPage.isCheckboxMeatSelected());
        Assert.assertFalse(basicElementsPOMPage.isCheckboxChickenSelected());
        Assert.assertFalse(basicElementsPOMPage.isCheckboxVegSelected());
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

}
