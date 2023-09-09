package seleniumFramework.flows;

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
}
