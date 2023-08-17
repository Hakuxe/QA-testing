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
        Assert.assertEquals(basicElementsPOMPage.getSchoolingOptionNumber(), 8);

        // select.selectByIndex(0);
        // Assert.assertEquals(select.getFirstSelectedOption().getText(), "1o grau incompleto");
        // select.selectByValue("2grauincomp");
        // Assert.assertEquals(select.getFirstSelectedOption().getText(), "2o grau incompleto");
        basicElementsPOMPage.selectSchooling("2o grau incompleto");
        Assert.assertEquals(basicElementsPOMPage.getSchoolingSelectedOpetion(), "2o grau incompleto");
    }

    @Test
//    @Ignore
    public void multiOptionSelect() {
        basicElementsPOMPage.selectMultipleOptions(new String[]{"Natacao", "Futebol", "Karate"});

        Assert.assertEquals(3, basicElementsPOMPage.getSelectedOptions());

        basicElementsPOMPage.clearOptionsSelected();

        Assert.assertEquals(0, basicElementsPOMPage.getSelectedOptions());
    }



}
