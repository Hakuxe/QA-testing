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
    By fieldStepsToReproduce = By.xpath("//*[@id='steps_to_reproduce']");
    By fieldAdditionalInfo = By.xpath("//*[@id='additional_info']");

    By fieldViewStatusPrivate = By.xpath("//span[text()='private']");


    By btnSubmitIssue = By.xpath("//*[@value='Submit Issue']");
    By alertSuccessIssue = By.xpath("//*[contains(@class, 'alert-success')]//p");


    // view issue  ------------------------------------------------------------------------

    By issueViewStatus = By.xpath("//td[@class='bug-view-status']");
    By issueSubmittedDate = By.xpath("//td[@class='bug-date-submitted']");
    By issuePriority = By.xpath("//td[@class='bug-priority']");
    By issueSeverity = By.xpath("//td[@class='bug-severity']");
    By issueReproducibility = By.xpath("//td[@class='bug-reproducibility']");
    By issueSummary = By.xpath("//td[@class='bug-summary']");
    By issueDescription = By.xpath("//td[@class='bug-description']");
    By issueStepsToReproduce = By.xpath("//td[@class='bug-steps-to-reproduce']");
    By issueAdditionalInfo = By.xpath("//td[@class='bug-additional-information']");


    public void goToPageReportIssue() {
        click(reportLink);
    }

    public void clickSelectProjectBtn() {
        click(selectProjectBtn);
    }

    // form ------------------------------------------------------------------------
    public void selectReproducibilityByText(String reproducibility) {
        comboBoxSelectByVisibleText(fieldComboBoxReproducibility, reproducibility);
    }

    public void selectSeverityByText(String severity) {
        comboBoxSelectByVisibleText(fieldComboBoxSeverity, severity);
    }

    public void selectPriorityByText(String severity) {
        comboBoxSelectByVisibleText(fieldComboBoxPriority, severity);
    }

    public void fillSummary(String text) {
        sendKeys(fieldSummary, text);
    }

    public void fillDescription(String text) {
        sendKeys(fieldDescription, text);
    }

    public void fillStepsToReproduce(String text) {
        sendKeys(fieldStepsToReproduce, text);
    }

    public void fillAdditionalInfo(String text) {
        sendKeys(fieldAdditionalInfo, text);
    }

    public void setViewStatusToPrivate(){
        click(fieldViewStatusPrivate);
    }

    public void clickSubmitIssueBtn() {
        click(btnSubmitIssue);
    }

    public String getTextCreatedIssueSuccessAlert() {
        return getText(alertSuccessIssue);
    }


    // view issue  ------------------------------------------------------------------------

    public String getTextIssueViewStatus() {
        return getText(issueViewStatus);
    }

    public String getTextIssueSubmittedDate() {
        return getText(issueSubmittedDate);
    }

    public String getTextIssuePriority() {
        return getText(issuePriority);
    }

    public String getTextIssueSeverity() {
        return getText(issueSeverity);
    }

    public String getTextIssueReproducibility() {
        return getText(issueReproducibility);
    }

    public String getTextIssueSummary() {
        return getText(issueSummary);
    }

    public String getTextIssueDescription() {
        return getText(issueDescription);
    }

    public String getTextIssueStepsToReproduce() {
        return getText(issueStepsToReproduce);
    }

    public String getTextIssueAdditionalInfo() {
        return getText(issueAdditionalInfo);
    }


}
