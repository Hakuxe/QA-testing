package seleniumFramework.pages;

import org.openqa.selenium.By;
import seleniumFramework.core.PageBase;

public class MainPage extends PageBase {

    By button = By.id("buttonDelay");
    By input = By.id("novoCampo");

    public void clickButton(){
        click(button);
    }

    public void fillInputSlowResponse(String text){
        sendKeys(input, text);
    }

    public String getValueInputSlowResponse(){
        return getValueInput(input);
    }

    public void waitForInput(long time){
        waitForElementByTime(input,time);
    }
}
