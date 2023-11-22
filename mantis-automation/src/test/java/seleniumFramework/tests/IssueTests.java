package seleniumFramework.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import seleniumFramework.core.GlobalProperties;
import seleniumFramework.core.TestBase;
import seleniumFramework.flows.LoginFlows;
import seleniumFramework.pages.IssuePage;
import seleniumFramework.pages.LoginPage;

public class IssueTests extends TestBase {
    //    Testes
    //    [ ] = Cadastrar um Issue
    //    [ ] =

    LoginFlows loginFlows;

    IssuePage issuePage;

    @BeforeMethod
    public void init() {
        loginFlows = new LoginFlows();
        issuePage = new IssuePage();

       loginFlows.loginDefaultUser();
    }

    @Test
    public void createAPublicIssue(){

        String viewStatus = "public";
        String priority = "high";
        String severity = "major";
        String reproducibility = "always";
        String summary = "Esta é uma issue aberta por meio de teste com selenium 22-11";
        String description = "Esta é a descrição da issue aberta por meio de teste com selenium 22-11";
        String stepReproduce = "Passo a passo para reproduzir os erros......";
        String additionalInfo = "Info adicional vai aki ";

        String expectedSuccessMessage = "Operation successful.";

        issuePage.goToPageReportIssue();
        issuePage.clickSelectProjectBtn();

        issuePage.selectReproducibilityByText("always");
        issuePage.selectSeverityByText("major");
        issuePage.selectPriorityByText("high");
        issuePage.fillSummary("Esta é uma issue aberta por meio de teste com selenium 22-11");
        issuePage.fillDescription("Esta é a descrição da issue aberta por meio de teste com selenium 22-11");
        issuePage.fillStepsToReproduce("Passo a passo para reproduzir os erros......");
        issuePage.fillAdditionalInfo("Info adicional vai aki ");
        issuePage.clickSubmitIssueBtn();

        Assert.assertEquals(issuePage.getTextCreatedIssueSuccessAlert(), expectedSuccessMessage);


        Assert.assertEquals(issuePage.getTextIssueViewStatus(), viewStatus);
//        Assert.assertEquals(issuePage.getTextIssueSubmittedDate(),);
        Assert.assertEquals(issuePage.getTextIssuePriority(),priority);
        Assert.assertEquals(issuePage.getTextIssueSeverity(),severity);
        Assert.assertEquals(issuePage.getTextIssueReproducibility(),reproducibility);

        // TODO o número da issue fica na frente do summary
        Assert.assertEquals(issuePage.getTextIssueSummary(),summary);
        Assert.assertEquals(issuePage.getTextIssueDescription(),description);
        Assert.assertEquals(issuePage.getTextIssueStepsToReproduce(),stepReproduce);
        Assert.assertEquals(issuePage.getTextIssueAdditionalInfo(),additionalInfo);

        // confirir se os dados estão batendo na issue



        System.out.println("aki");
    }

}
