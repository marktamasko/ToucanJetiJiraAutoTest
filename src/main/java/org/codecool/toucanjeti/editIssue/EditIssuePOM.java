package org.codecool.toucanjeti.editIssue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class EditIssuePOM {
    private static String ISSUE_URL = "https://jira-auto.codecool.metastage.net/browse/MTP-4389";

    @FindBy(id = "edit-issue")
    private WebElement editIssue;

    @FindBy(id = "edit-issue-submit")
    private WebElement updateIssue;

    @FindBy(id = "summary")
    private WebElement summary;

    @FindBy(id = "issuetype-field")
    private WebElement issueType;

    @FindBy(id = "description")
    private WebElement description;

    @FindBy(id = "priority-field")
    private WebElement priority;

    @FindBy(id = "labels-textarea")
    private WebElement labels;

    @FindBy(id = "comment")
    private WebElement comment;

    private final WebDriver webDriver;

    public EditIssuePOM(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void clickEditButton() {
        this.editIssue.click();
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    public void clickUpdateButton() {
        this.editIssue.click();
    }

    /**
     * It is the responsibility of the method to be able to change the summary or description of an issue.
     * */
    private void clearAndChangeIssueField(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    /**
     * It is the responsibility of the method to be able to change
     * the issue type, priority, label or comment of an issue.
     * */
    private void clickAndChangeIssueField(WebElement element, String text) {
        element.click();
        element.sendKeys(text);
    }

    public void changeSummaryField(String newSummary) {
        clearAndChangeIssueField(this.summary, newSummary);
    }

    public void changeIssueType(String newIssue) {
        clickAndChangeIssueField(this.issueType, newIssue);
    }

    public void changeDescription(String newDescription) {
        clearAndChangeIssueField(this.description, newDescription);
    }

    public void changePriority(String priority) {
        clickAndChangeIssueField(this.priority, priority);
    }

    public void changeLabel(String label) {
        clickAndChangeIssueField(this.labels, label);
    }

    public void addNewComment(String newComment) {
        clickAndChangeIssueField(this.comment, newComment);
    }
}
