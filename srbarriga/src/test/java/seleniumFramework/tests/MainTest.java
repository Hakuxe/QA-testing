package seleniumFramework.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import seleniumFramework.core.TestBase;
import seleniumFramework.flows.LoginFlows;
import seleniumFramework.flows.MainFlows;
import seleniumFramework.pages.AccountPage;

public class MainTest extends TestBase {

    LoginFlows loginFlows;
    MainFlows mainFlows;

    AccountPage accountPage;
    ;


    @BeforeMethod
    public void init() {
        mainFlows = new MainFlows();
        loginFlows = new LoginFlows();
        accountPage = new AccountPage();
    }

    @Test
    public void basicLogin() {
        loginFlows.login();
    }

    @Test
    public void insertAccount() {

        String name = "naruto";
        String expectedSuccessMessage = "Conta adicionada com sucesso!";

        loginFlows.login();
        mainFlows.navigateToInsertAccount();
        accountPage.fillAccountName(name);
        accountPage.clickSubmitButton();

        Assert.assertEquals(accountPage.getTextAlert(), expectedSuccessMessage);
    }

}
