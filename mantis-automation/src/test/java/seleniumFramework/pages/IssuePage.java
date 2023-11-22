package seleniumFramework.pages;

import org.openqa.selenium.By;
import seleniumFramework.core.PageBase;

public class IssuePage extends PageBase {


    By reportLink = By.xpath("//a[@href='/bug_report_page.php']");

    By selectProjectBtn = By.xpath("//*[@value='Select Project']");


    // form ------------------------------------------------------------------------
    By fieldComboBoxReproducibility = By.xpath("//*[@id='reproducibility']");
    By fieldComboBoxSeverity = By.xpath("//*[@id='severity']");
    By fieldComboBoxPriority = By.xpath("//*[@id='priority']");
    By fieldSummary = By.xpath("//*[@id='summary']");
    By fieldDescription = By.xpath("//*[@id='description']");

    By btnSubmitIssue = By.xpath("value='Submit Issue'");



    public void goToPageReportIssue(){
        click(reportLink);
    }

    public void clickSelectProjectBtn(){
        click(selectProjectBtn);
    }

    // form ------------------------------------------------------------------------
    public  void selectReproducibilityByText(String reproducibility){
        comboBoxSelectByVisibleText(fieldComboBoxReproducibility, reproducibility);
    }

    public  void selectSeverityByText(String severity){
        comboBoxSelectByVisibleText(fieldComboBoxSeverity, severity);
    }

    public  void selectPriorityByText(String severity){
        comboBoxSelectByVisibleText(fieldComboBoxPriority, severity);
    }

    public void fillSummary(String text){
        sendKeys(fieldSummary, text);
    }

    public void fillDescription(String text){
        sendKeys(fieldDescription, text);
    }

    public void clickSubmitIssueBtn(){
        click(btnSubmitIssue);
    }

}
