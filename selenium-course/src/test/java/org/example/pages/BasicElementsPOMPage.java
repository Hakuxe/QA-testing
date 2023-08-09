package org.example.pages;

import org.example.base.DSL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasicElementsPOMPage extends DSL {

    public BasicElementsPOMPage(WebDriver driver) {
        super(driver);
    }

    By nameInput = By.id("elementosForm:nome");
    By surnameInput = By.id("elementosForm:sobrenome");
    By textArea = By.id("elementosForm:sugestoes");

    By radioMale = By.id("elementosForm:sexo:0");
    By radioFemale = By.id("elementosForm:sexo:1");

    //CHECKBOZ
    By pizzaCheckBox = By.id("elementosForm:comidaFavorita:2");
    By meatCheckBox = By.id("elementosForm:comidaFavorita:0");
    By chickenCheckBox = By.id("elementosForm:comidaFavorita:1");
    By vegCheckBox = By.id("elementosForm:comidaFavorita:3");

    public void fillName(String name) {
        sendKeys(nameInput, name);
    }

    public void fillSurname(String surname) {
        sendKeys(surnameInput, surname);
    }

    public void fillTextarea(String text) {
        sendKeys(textArea, text);
    }

    public void fillGender(String gender) {
        click(By.xpath(String.format("//label[contains(text(),'%s')]", gender)));
    }

    public String getNameInputAttribute(String attr) {
        return getElementAttribute(nameInput, attr);
    }

    public String getSurnameInputAttribute(String attr) {
        return getElementAttribute(surnameInput, attr);
    }

    public String getTextAreaAttribute(String attr) {
        return getElementAttribute(textArea, attr);
    }

    // radio button =======================
    public boolean radioMaleIsSelected() {
        return isSelected(radioMale);
    }

    public boolean radioFemaleIsSelected() {
        return isSelected(radioFemale);
    }

    //checkbox ===============
    public void selectPizzaCheckBox() {
        click(pizzaCheckBox);
    }

    public void selectMeatCheckBox() {
        click(meatCheckBox);
    }

    public boolean isCheckboxPizzaSelected(){
        return isSelected(pizzaCheckBox);
    }
    public boolean isCheckboxMeatSelected(){
        return isSelected(meatCheckBox);
    }
    public boolean isCheckboxChickenSelected(){
        return isSelected(chickenCheckBox);
    }
    public boolean isCheckboxVegSelected(){
        return isSelected(vegCheckBox);
    }

}
