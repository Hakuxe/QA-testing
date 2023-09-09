package seleniumFramework.flows;

import seleniumFramework.core.Properties;
import seleniumFramework.pages.LoginPage;

public class LoginFlows {

    LoginPage loginPage;

    public LoginFlows(){
        loginPage = new LoginPage();
    }

    public void login(){
        loginPage.fillUsername(Properties.DEFAULT_USER);
        loginPage.fillPassword(Properties.DEFAULT_PASSWORD);

        loginPage.clickSubmitButton();
    }

}
