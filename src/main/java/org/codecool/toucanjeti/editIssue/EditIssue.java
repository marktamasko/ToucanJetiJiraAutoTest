package org.codecool.toucanjeti.editIssue;

import org.codecool.toucanjeti.technical.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.time.Duration;

public class EditIssue {

    private final WebDriver webDriver;
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
    }

    private void clickEditButton() {
        this.webDriver.findElement(EDIT_BUTTON_ID).click();
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    private void updateIssue() {
        this.webDriver.findElement(UPDATE_BUTTON_ID).click();
    }

    private void closeDriver() {
        this.webDriver.close();
    }

    public void changeSummaryField() {
        navigateToTheIssue();
        clickEditButton();
        this.webDriver.findElement(By.id("summary")).clear();
        this.webDriver.findElement(By.id("summary")).sendKeys(EditIssue.TEST_SUMMARY_FIELD);
        updateIssue();
        closeDriver();
    }

    public void changeIssueType() {
        navigateToTheIssue();
        clickEditButton();
        this.webDriver.findElement(By.id("issuetype-field")).click();
        this.webDriver.findElement(By.id("issuetype-field")).sendKeys(EditIssue.TEST_ISSUE_TYPE);
        updateIssue();
        closeDriver();
    }

    public void changeDescriptionField() {
        navigateToTheIssue();
        clickEditButton();
        this.webDriver.findElement(By.id("description")).clear();
        this.webDriver.findElement(By.id("description")).sendKeys(EditIssue.TEST_DESCRIPTION_FIELD);
        updateIssue();
        closeDriver();
    }

    public void changePriorityField() {
        navigateToTheIssue();
        clickEditButton();
        this.webDriver.findElement(By.id("priority-field")).click();
        this.webDriver.findElement(By.id("priority-field")).sendKeys(EditIssue.TEST_PRIORITY_FIELD);
        updateIssue();
        closeDriver();
    }

    public void changeLabelField() {
        navigateToTheIssue();
        clickEditButton();
        this.webDriver.findElement(By.id("labels-textarea")).click();
        this.webDriver.findElement(By.id("labels-textarea")).sendKeys(EditIssue.TEST_LABEL_FIELD);
        updateIssue();
        closeDriver();
    }

    public void addNewComment() {
        navigateToTheIssue();
        clickEditButton();
        this.webDriver.findElement(By.id("comment")).click();
        this.webDriver.findElement(By.id("comment")).sendKeys(EditIssue.TEST_COMMENT_FIELD);
        updateIssue();
        closeDriver();
    }

    public void addNewAttachment() {
        navigateToTheIssue();
        clickEditButton();
        WebElement fileInput = this.webDriver.findElement(By.cssSelector(".field-group #attachment-browse-button"));
        fileInput.click();
        fileInput.sendKeys(UPLOAD_FILE.getAbsolutePath());
        updateIssue();
        closeDriver();
    }
}
