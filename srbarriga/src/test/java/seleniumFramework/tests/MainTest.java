package seleniumFramework.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import seleniumFramework.core.TestBase;
import seleniumFramework.flows.LoginFlows;
import seleniumFramework.flows.MainFlows;

public class MainTest extends TestBase {

    LoginFlows loginFlows;
    MainFlows mainFlows;


    @BeforeMethod
    public void init(){
        mainFlows = new MainFlows();
        loginFlows = new LoginFlows();
    }

    @Test
    public  void basicLogin(){
        loginFlows.login();
    }

    @Test
    public void insertAccount(){
        loginFlows.login();
        mainFlows.navigateToInsertAccount();
    }

}
