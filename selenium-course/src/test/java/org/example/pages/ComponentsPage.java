package org.example.pages;

import org.example.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ComponentsPage  {

    private WebDriver driver;

    public ComponentsPage(WebDriver driver){
        this.driver = driver;
    }

    By btnRegister = By.id("elementosForm:cadastrar");
    By nameInput = By.id("elementosForm:nome");
    By surnameInput = By.id("elementosForm:sobrenome");
    By radioButton = By.id("elementosForm:sexo:0");

    public void fillName(String name) {
        driver.findElement(nameInput).sendKeys(name);
    }

    public void fillSurname(String surname) {
        driver.findElement(surnameInput).sendKeys(surname);
    }

    public void fillGender(String gender) {
        driver.findElement(By.xpath(String.format("//label[contains(text(),'%s')]", gender))).click();
    }
}
