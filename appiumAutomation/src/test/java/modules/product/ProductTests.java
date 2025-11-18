package modules.product;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import screens.LoginScreen;

import java.net.MalformedURLException;
import java.net.URL;

@DisplayName("Testes do módulo de produto mobile")
public class ProductTests {
    private  WebDriver driver;

    @BeforeEach
    public void beforeEach() throws MalformedURLException{
        //Características do dispositivo usado nos testes do appium
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Google Pixel 3");
        capabilities.setCapability("platform", "Android");
        capabilities.setCapability("udid", "192.168.56.104:5555");
        capabilities.setCapability("appPackage", "com.lojinha");
        capabilities.setCapability("appActivity", "com.lojinha.ui.MainActivity");
        capabilities.setCapability("app", ""); //local onde o app está no disco local para instalar no emulador

        // A url para o driver fica no appium hub
        this.driver = new RemoteWebDriver(new URL("https://127.0.0.1/wd/hub"), capabilities);

    }

    @AfterEach
    public void afterEach(){
       driver.quit();
    }

    @Test
    public void validateProductValueNotAllowed()  {

        String message = new LoginScreen(driver)
                .fillUserName("luffy")
                .fillPassword("luffy")
                .logIn()
                .clickAddProduct()
                .fillProductName("ps2")
                .fillProductValue("200")
                .fillProductColor("preto, branco")
                .clickSaveProductButton()
                .getToastMessage();

        // checar mensagem de erro  
        Assertions.assertEquals("o valor do produto deve estar entre R$ 0,01 e R$ 7000,00, ", message);
    }
}
