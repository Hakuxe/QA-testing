package seleniumFramework.flows;

import seleniumFramework.core.GlobalProperties;
import seleniumFramework.pages.LoginPage;

public class LoginFlows {

    LoginPage loginPage;
    public LoginFlows(){
        loginPage = new LoginPage();
    }

    public void loginDefaultUser(){
        loginPage.fillUsername(GlobalProperties.DEFAULT_USER);
        loginPage.clickSubmitButton();
        loginPage.fillPassword(GlobalProperties.DEFAULT_PASSWORD);
        loginPage.uncheckAllowOnlySessionFromIp();
        loginPage.clickSubmitButton();
    }

}
