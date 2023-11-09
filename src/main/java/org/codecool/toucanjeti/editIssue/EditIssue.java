package org.codecool.toucanjeti.editIssue;

import org.codecool.toucanjeti.technical.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.time.Duration;

public class EditIssue {

    private WebDriver webDriver;
    private static final String LEGIT_USERNAME = "automation59";
    private static final String LEGIT_PASSWORD = "CCAutoTest19.";
    private static final String EDITED_ISSUE_URL = "https://jira-auto.codecool.metastage.net/browse/MTP-4389";
    private static final By EDIT_BUTTON_ID = By.id("edit-issue");
    private static final By UPDATE_BUTTON_ID = By.id("edit-issue-submit");
    private static final File UPLOAD_FILE = new File("src/main/resources/images/test-automation-logos.png");
    private static final String TEST_SUMMARY_FIELD = "test summary";
    private static final String TEST_ISSUE_TYPE = "Task";
    private static final String TEST_DESCRIPTION_FIELD = "test description";
    private static final String TEST_PRIORITY_FIELD = "Lowest";
    private static final String TEST_LABEL_FIELD = "label testing field";
    private static final String TEST_COMMENT_FIELD = "add a new test comment";

    public EditIssue(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private void navigateToTheIssue() {
        Login.toJiraWith(LEGIT_USERNAME, LEGIT_PASSWORD, this.webDriver);
        this.webDriver.get(EDITED_ISSUE_URL);
        this.webDriver.findElement(EDIT_BUTTON_ID).click();
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    private void updateIssue() {
        this.webDriver.findElement(UPDATE_BUTTON_ID).click();
    }

    private void closePage() {
        this.webDriver.close();
    }

    private void changeSummaryField(String text) {
        navigateToTheIssue();
        this.webDriver.findElement(By.id("summary")).clear();
        this.webDriver.findElement(By.id("summary")).sendKeys(text);
        updateIssue();
    }

    private void changeIssueType(String issueType) {
        navigateToTheIssue();
        this.webDriver.findElement(By.id("issuetype-field")).click();
        this.webDriver.findElement(By.id("issuetype-field")).sendKeys(issueType);
        updateIssue();
    }

    private void changeDescriptionField(String text) {
        navigateToTheIssue();
        this.webDriver.findElement(By.id("description")).clear();
        this.webDriver.findElement(By.id("description")).sendKeys(text);
        updateIssue();
    }

    private void changePriorityField(String priority) {
        navigateToTheIssue();
        this.webDriver.findElement(By.id("priority-field")).click();
        this.webDriver.findElement(By.id("priority-field")).sendKeys(priority);
        updateIssue();
    }

    private void changeLabelField(String label) {
        navigateToTheIssue();
        this.webDriver.findElement(By.id("labels-textarea")).click();
        this.webDriver.findElement(By.id("labels-textarea")).sendKeys(label);
        updateIssue();
    }

    private void addNewComment(String comment) {
        navigateToTheIssue();
        this.webDriver.findElement(By.id("comment")).click();
        this.webDriver.findElement(By.id("comment")).sendKeys(comment);
        updateIssue();
    }

    private void addNewAttachment() {
        navigateToTheIssue();
        WebElement fileInput = this.webDriver.findElement(By.cssSelector(".field-group #attachment-browse-button"));
        fileInput.click();
        fileInput.sendKeys(UPLOAD_FILE.getAbsolutePath());
        updateIssue();
    }

    public void runEditIssueTestCases() {
        this.changeSummaryField(TEST_SUMMARY_FIELD);
        this.changeIssueType(TEST_ISSUE_TYPE);
        this.changeDescriptionField(TEST_DESCRIPTION_FIELD);
        this.changePriorityField(TEST_PRIORITY_FIELD);
        this.changeLabelField(TEST_LABEL_FIELD);
        this.addNewComment(TEST_COMMENT_FIELD);
        this.addNewAttachment();
        this.closePage();
    }
}
