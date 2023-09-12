package seleniumFramework.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import seleniumFramework.core.TestBase;
import seleniumFramework.flows.LoginFlows;
import seleniumFramework.flows.MainFlows;
import seleniumFramework.pages.AccountPage;
import seleniumFramework.pages.MainPage;

public class MainTest extends TestBase {

    LoginFlows loginFlows;
    MainFlows mainFlows;
    MainPage mainPage;
    AccountPage accountPage;


    @BeforeMethod
    public void init() {
        mainFlows = new MainFlows();
        mainPage = new MainPage();
        loginFlows = new LoginFlows();
        accountPage = new AccountPage();

        loginFlows.login();
    }

    @Test
    public void basicLogin() {
        loginFlows.login();
    }

    @Test
    public void insertAccount() {
        String name = "naruto";
        String expectedSuccessMessage = "Conta adicionada com sucesso!";


        mainFlows.navigateToInsertAccount();
        accountPage.fillAccountName(name);
        accountPage.clickSubmitButton();

        Assert.assertEquals(accountPage.getTextAlert(), expectedSuccessMessage);
    }

    @Test
    public void editAccount() {
        String name = "conta alterada";
        String expectedSuccessMessage = "Conta alterada com sucesso!";

        mainFlows.navigateToAccountList();


        accountPage.clickAlterAccount();

        accountPage.fillAccountName(name);
        accountPage.clickSubmitButton();
        Assert.assertEquals(accountPage.getTextAlert(), expectedSuccessMessage);

        Assert.assertEquals(accountPage.getTextCell("Conta", name), name);
    }

}
