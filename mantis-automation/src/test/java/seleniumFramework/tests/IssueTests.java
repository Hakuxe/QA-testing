package seleniumFramework.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import seleniumFramework.core.GlobalProperties;
import seleniumFramework.core.TestBase;
import seleniumFramework.pages.IssuePage;
import seleniumFramework.pages.LoginPage;

public class IssueTests extends TestBase {
    //    Testes
    //    [ ] = Cadastrar um Issue
    //    [ ] =

    LoginPage loginPage;

    IssuePage issuePage;

    @BeforeMethod
    public void init() {
        loginPage = new LoginPage();
        issuePage = new IssuePage();


        loginPage.fillUsername(GlobalProperties.DEFAULT_USER);
        loginPage.clickSubmitButton();
        loginPage.fillPassword(GlobalProperties.DEFAULT_PASSWORD);
        loginPage.uncheckAllowOnlySessionFromIp();
        loginPage.clickSubmitButton();
    }

    @Test
    public void createAnIssue(){
        issuePage.goToPageReportIssue();
        issuePage.clickSelectProjectBtn();

        issuePage.selectReproducibilityByText("always");
        issuePage.selectSeverityByText("major");
        issuePage.selectPriorityByText("high");
        issuePage.fillSummary("Esta é uma issue aberta por meio de teste com selenium 22-11");
        issuePage.fillDescription("Esta é a descrição da issue aberta por meio de teste com selenium 22-11");
        issuePage.clickSubmitIssueBtn();


        // mantis bloqueia a cada 10 issues criadas
        // quanto tempo até desbloquear



        System.out.println("aki");
    }

}
