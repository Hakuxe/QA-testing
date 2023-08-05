package org.example.tests;

import org.example.base.TestBase;
import org.example.pages.ComponentsPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ChallengeTest extends TestBase {

    ComponentsPage componentsPage;

    By btnRegister = By.id("elementosForm:cadastrar");
    By nameInput = By.id("elementosForm:nome");
    By surnameInput = By.id("elementosForm:sobrenome");
    By radioButton = By.id("elementosForm:sexo:0");

    String name = "thiago";


    @Test
    public void checkMessageFieldNameEmpty() {
        String expectedText = "Nome eh obrigatorio";

        driver.findElement(btnRegister).click();
        Alert alert = driver.switchTo().alert();

        Assert.assertEquals(expectedText, alert.getText());

        alert.dismiss();

        driver.findElement(nameInput).sendKeys(name);
        Assert.assertTrue(driver.findElement(nameInput).getAttribute("value").contains(name));

    }

    @Test
    public void checkMessageFieldSurnameEmpty() {
        driver.findElement(nameInput).sendKeys(name);

        String expectedText = "Sobrenome eh obrigatorio";

        driver.findElement(btnRegister).click();
        Alert alert = driver.switchTo().alert();

        Assert.assertEquals(expectedText, alert.getText());

        alert.dismiss();

        driver.findElement(surnameInput).sendKeys(name);
        Assert.assertTrue(driver.findElement(surnameInput).getAttribute("value").contains(name));

    }

    @Test
    public void checkMessageFieldSexEmpty() {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(surnameInput).sendKeys(name);

        String expectedText = "Sexo eh obrigatorio";
        driver.findElement(btnRegister).click();
        Alert alert = driver.switchTo().alert();

        Assert.assertEquals(expectedText, alert.getText());

        alert.dismiss();

        driver.findElement(radioButton).click();

        Assert.assertEquals("true", driver.findElement(radioButton).getAttribute("checked"));

    }

    @Test
    public void verifyErrorVeganAndMeatSelectedInFavoriteFood() {
        componentsPage = new ComponentsPage(driver);
        String expectedAlertText = "Tem certeza que voce eh vegetariano?";

        By checkBoxVegan = By.id("elementosForm:comidaFavorita:3");
        By checkBoxMeat = By.id("elementosForm:comidaFavorita:0");

        componentsPage.fillName( name);
        componentsPage.fillSurname(name);
        componentsPage.fillGender("Masculino");

        driver.findElement(checkBoxVegan).click();
        driver.findElement(checkBoxMeat).click();

        driver.findElement(btnRegister).click();

        Alert alert = driver.switchTo().alert();

        Assert.assertEquals(expectedAlertText, alert.getText());

    }

    @Test
    public void verifyErrorVeganAndChickenSelectedInFavoriteFood() {
        componentsPage = new ComponentsPage(driver);
        String expectedAlertText = "Tem certeza que voce eh vegetariano?";

        By checkBoxVegan = By.id("elementosForm:comidaFavorita:3");
        By checkBoxMeat = By.id("elementosForm:comidaFavorita:1");

        componentsPage.fillName(name);
        componentsPage.fillSurname(name);
        componentsPage.fillGender("Masculino");

        driver.findElement(checkBoxVegan).click();
        driver.findElement(checkBoxMeat).click();

        driver.findElement(btnRegister).click();

        Alert alert = driver.switchTo().alert();

        Assert.assertEquals(expectedAlertText, alert.getText());

    }
}
