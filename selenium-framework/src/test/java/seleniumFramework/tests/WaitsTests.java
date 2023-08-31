package seleniumFramework.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import seleniumFramework.core.TestBase;
import seleniumFramework.pages.MainPage;

import java.util.concurrent.TimeUnit;

public class WaitsTests extends TestBase {
    By button = By.id("buttonDelay");
    By input = By.id("novoCampo");

    MainPage mainPage;


    @Test
    public void waitFixo() throws InterruptedException {
        String text = "Valor preenchido";
        mainPage = new MainPage();

        mainPage.clickButton();
        //Para a execução do teste durante o tempo específicado
        Thread.sleep(5000);

        mainPage.fillInputSlowResponse(text);

        Assert.assertEquals(mainPage.getValueInputSlowResponse(), text);
    }


    @Test
    public void waitExplicita() {
        String text = "Valor preenchido";
        mainPage = new MainPage();

        mainPage.clickButton();

        //Mais indicado
        mainPage.waitForInput(30);

        mainPage.fillInputSlowResponse(text);

        Assert.assertEquals(mainPage.getValueInputSlowResponse() , text);


    }
}
