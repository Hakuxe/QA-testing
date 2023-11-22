package seleniumFramework.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import seleniumFramework.core.GlobalProperties;
import seleniumFramework.core.TestBase;


import seleniumFramework.pages.LoginPage;


public class LoginTests extends TestBase {

    LoginPage loginPage;

    //    Testes
    //    [x] - Login no sistema com sucesso
    //    [x] - Realizar login com senha inválida

    @BeforeMethod
    public void init() {
        loginPage = new LoginPage();
    }

    @Test
    public void makeLoginSuccessfully() {
        loginPage.fillUsername(GlobalProperties.DEFAULT_USER);
        loginPage.clickSubmitButton();
        loginPage.fillPassword(GlobalProperties.DEFAULT_PASSWORD);
        loginPage.uncheckAllowOnlySessionFromIp();
        loginPage.clickSubmitButton();

        Assert.assertTrue(loginPage.checkUserInfoIsVisible(), "Login não efetuado com sucesso" );
    }

    @Test
    public void makeLoginWithInvalidPassword() {

        String expectedErrorMessage = "Your account may be disabled or blocked or the username/password you entered is incorrect.";

        loginPage.fillUsername(GlobalProperties.DEFAULT_USER);
        loginPage.clickSubmitButton();
        loginPage.fillPassword("1234");
        loginPage.uncheckAllowOnlySessionFromIp();
        loginPage.clickSubmitButton();

        Assert.assertEquals(loginPage.getTextErrorLoginMessage(), expectedErrorMessage);
    }

}
