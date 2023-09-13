package seleniumFramework.flows;

import seleniumFramework.pages.AccountPage;
import seleniumFramework.pages.MainPage;

public class MainFlows {

    MainPage mainPage;

    public MainFlows(){
        mainPage = new MainPage();
    }

    public void navigateToInsertAccount(){
        mainPage.clickAccount();
        mainPage.clickInsertAccount();
    }

    public void navigateToAccountList(){
        mainPage.clickAccount();
        mainPage.clickAccountListButton();
    }

    public void navigateToTransactionsPage(){
       mainPage.goToTransactionsPage();
    }
}
