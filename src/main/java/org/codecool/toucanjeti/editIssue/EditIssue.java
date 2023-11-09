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
    private static final String UPLOAD_FILE_PATH =
            "/Users/rekamark/CODECOOL-marktamasko/ReStart/Test_Auto/toucanjeti/ToucanJetiJiraAutoTest/src/main/" +
                    "resources/images/test-automation-logos.png";
    private static final File UPLOAD_FILE = new File("src/main/resources/images/test-automation-logos.png");

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

    public void changeSummaryField(String text) {
        navigateToTheIssue();
        this.webDriver.findElement(By.id("summary")).clear();
        this.webDriver.findElement(By.id("summary")).sendKeys(text);
        updateIssue();
    }

    public void changeIssueType(String issueType) {
        navigateToTheIssue();
        this.webDriver.findElement(By.id("issuetype-field")).click();
        this.webDriver.findElement(By.id("issuetype-field")).sendKeys(issueType);
        updateIssue();
    }

    public void changeDescriptionField(String text) {
        navigateToTheIssue();
        this.webDriver.findElement(By.id("description")).clear();
        this.webDriver.findElement(By.id("description")).sendKeys(text);
        updateIssue();
    }

    public void changePriorityField(String priority) {
        navigateToTheIssue();
        this.webDriver.findElement(By.id("priority-field")).click();
        this.webDriver.findElement(By.id("priority-field")).sendKeys(priority);
        updateIssue();
    }

    public void changeLabelField(String label) {
        navigateToTheIssue();
        this.webDriver.findElement(By.id("labels-textarea")).click();
        this.webDriver.findElement(By.id("labels-textarea")).sendKeys(label);
        updateIssue();
    }

    public void addNewComment(String comment) {
        navigateToTheIssue();
        this.webDriver.findElement(By.id("comment")).click();
        this.webDriver.findElement(By.id("comment")).sendKeys(comment);
        updateIssue();
    }

    public void addNewAttachment() {
        navigateToTheIssue();
        WebElement fileInput = this.webDriver.findElement(By.cssSelector(".field-group #attachment-browse-button"));
        fileInput.click();
        fileInput.sendKeys(UPLOAD_FILE.getAbsolutePath());
        updateIssue();
    }
}
