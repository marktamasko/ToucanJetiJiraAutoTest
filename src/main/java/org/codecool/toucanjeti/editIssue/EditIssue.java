package org.codecool.toucanjeti.editIssue;

import org.codecool.toucanjeti.technical.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class EditIssue {

    private WebDriver webDriver;
    private static final String LEGIT_USERNAME = "automation59";
    private static final String LEGIT_PASSWORD = "CCAutoTest19.";
    private static final String EDITED_ISSUE_URL = "https://jira-auto.codecool.metastage.net/browse/MTP-4389";
    private static final By EDIT_BUTTON_ID = By.id("edit-issue");
    private static final By UPDATE_BUTTON_ID = By.id("edit-issue-submit");

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

}
