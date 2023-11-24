package org.codecool.toucanjeti.editIssue;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

public class EditIssuePOM {

    @FindBy(id = "edit-issue")
    private WebElement editIssue;

    @FindBy(id = "edit-issue-submit")
    private WebElement updateIssue;

    @FindBy(id = "summary")
    private WebElement summary;

    @FindBy(id = "summary-val")
    private WebElement summaryText;

    @FindBy(id = "issuetype-field")
    private WebElement issueType;

    @FindBy(id = "type-val")
    private WebElement issueTypeValue;

    @FindBy(id = "description")
    private WebElement description;

    @FindBy(xpath = "//*[@id='description-val']/div/p")
    private WebElement descriptionValue;

    @FindBy(id = "priority-field")
    private WebElement priority;

    @FindBy(id = "priority-val")
    private WebElement priorityValue;

    @FindBy(id = "labels-textarea")
    private WebElement labels;

    @FindBy(id = "comment")
    private WebElement comment;

    private final Logger logger = Logger.getLogger(getClass().getName());

    private final WebDriver webDriver;

    private final WebDriverWait wait;

    public EditIssuePOM(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        PageFactory.initElements(webDriver, this);
    }

    public void clickEditButton(By locator) {
        try {
            if (this.editIssue.isDisplayed()) {
                this.editIssue.click();
            } else {
                throw new NoSuchElementException("Edit button is not displayed or accessible.");
            }
            this.wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (NoSuchElementException e) {
            this.logger.info("Exception: " + e.getMessage());
        }
    }

    public void clickUpdateButton(By locator) {
        this.updateIssue.click();
        this.wait.until(ExpectedConditions.presenceOfElementLocated(locator));
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

    // TODO: fix method!
    public void changeDescription(By element, String newDescription) {
        By popUp = By.id("edit-issue-dialog");
        wait.until(ExpectedConditions.visibilityOfElementLocated(popUp));
        WebElement descEle = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        descEle.click();
        wait.until(ExpectedConditions.stalenessOf(descEle));
        WebElement newDescEle = wait.until(ExpectedConditions.elementToBeClickable(element));
        newDescEle.clear();
        newDescEle.sendKeys(newDescription);
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

    public By getSummaryLocator() {
        return By.id("summary");
    }

    public By getSummaryTextLocator() {
        return By.id("summary-val");
    }

    public String getSummaryTextValue() {
        return this.summaryText.getText();
    }

    public By getIssueType() {
        return By.id("issuetype-field");
    }

    public By getIssueTypeUpdateValue() {
        return By.id("type-val");
    }

    public String getIssueTypeValue() {
        return this.issueTypeValue.getText();
    }

    public By getDescription() {
        return By.id("description");
    }

    public String getDescriptionValue() {
        return this.descriptionValue.getText();
    }

    public By getPriority() {
        return By.id("priority-field");
    }

    public By getPriorityLocator() {
        return By.id("priority-val");
    }

    public String getPriorityValue() {
        return this.priorityValue.getText();
    }
}
