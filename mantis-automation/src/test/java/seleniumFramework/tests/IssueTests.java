package seleniumFramework.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import seleniumFramework.core.GlobalProperties;
import seleniumFramework.core.TestBase;
import seleniumFramework.flows.LoginFlows;
import seleniumFramework.pages.IssuePage;
import seleniumFramework.pages.LoginPage;
import seleniumFramework.utils.Utils;

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
        String additionalInfo = "Info adicional vai aki";

        String expectedSuccessMessage = "Operation successful.";

        issuePage.goToPageReportIssue();
        issuePage.clickSelectProjectBtn();

        issuePage.selectReproducibilityByText(reproducibility);
        issuePage.selectSeverityByText(severity);
        issuePage.selectPriorityByText(priority);
        issuePage.fillSummary(summary);
        issuePage.fillDescription(description);
        issuePage.fillStepsToReproduce(stepReproduce);
        issuePage.fillAdditionalInfo(additionalInfo);
        issuePage.clickSubmitIssueBtn();

        Assert.assertEquals(issuePage.getTextCreatedIssueSuccessAlert(), expectedSuccessMessage);


        Assert.assertEquals(issuePage.getTextIssueViewStatus(), viewStatus);

        String actualDate = issuePage.getTextIssueSubmittedDate().split(" ")[0];
        Assert.assertEquals(actualDate, Utils.getNowDate("yyyy-MM-dd"));

        Assert.assertEquals(issuePage.getTextIssuePriority(),priority);
        Assert.assertEquals(issuePage.getTextIssueSeverity(),severity);
        Assert.assertEquals(issuePage.getTextIssueReproducibility(),reproducibility);
        Assert.assertTrue(issuePage.getTextIssueSummary().contains(summary));
        Assert.assertEquals(issuePage.getTextIssueDescription(),description);
        Assert.assertEquals(issuePage.getTextIssueStepsToReproduce(),stepReproduce);
        Assert.assertEquals(issuePage.getTextIssueAdditionalInfo(),additionalInfo);

    }

    @Test
    public void createAPrivateIssue(){

        String viewStatus = "private";
        String priority = "high";
        String severity = "major";
        String reproducibility = "always";
        String summary = "Esta é uma issue aberta como private";
        String description = "Esta é a descrição da issue aberta por meio de teste com selenium";
        String stepReproduce = "Passo a passo para reproduzir os erros......";
        String additionalInfo = "Info adicional vai aki";

        String expectedSuccessMessage = "Operation successful.";

        issuePage.goToPageReportIssue();
        issuePage.clickSelectProjectBtn();

        issuePage.selectReproducibilityByText(reproducibility);
        issuePage.selectSeverityByText(severity);
        issuePage.selectPriorityByText(priority);
        issuePage.fillSummary(summary);
        issuePage.fillDescription(description);
        issuePage.fillStepsToReproduce(stepReproduce);
        issuePage.fillAdditionalInfo(additionalInfo);
        issuePage.setViewStatusToPrivate();
        issuePage.clickSubmitIssueBtn();

        Assert.assertEquals(issuePage.getTextCreatedIssueSuccessAlert(), expectedSuccessMessage);


        Assert.assertEquals(issuePage.getTextIssueViewStatus(), viewStatus);

        String actualDate = issuePage.getTextIssueSubmittedDate().split(" ")[0];
        Assert.assertEquals(actualDate, Utils.getNowDate("yyyy-MM-dd"));

        Assert.assertEquals(issuePage.getTextIssuePriority(),priority);
        Assert.assertEquals(issuePage.getTextIssueSeverity(),severity);
        Assert.assertEquals(issuePage.getTextIssueReproducibility(),reproducibility);
        Assert.assertTrue(issuePage.getTextIssueSummary().contains(summary));
        Assert.assertEquals(issuePage.getTextIssueDescription(),description);
        Assert.assertEquals(issuePage.getTextIssueStepsToReproduce(),stepReproduce);
        Assert.assertEquals(issuePage.getTextIssueAdditionalInfo(),additionalInfo);

    }

}
