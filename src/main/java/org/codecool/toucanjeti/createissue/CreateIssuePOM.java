package org.codecool.toucanjeti.createissue;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateIssuePOM {
    private final WebDriver driver;
    @FindBy(id = "create-issue-submit") private WebElement createIssueSubmitButton;
    @FindBy(id = "project-field") private WebElement projectInputField;
    @FindBy(id = "issuetype-field") private WebElement typeInputField;
    @FindBy(id = "summary") private WebElement summaryTitleInput;
    @FindBy(tagName = "h2") private WebElement titleOfCreateIssuePopup;

    public CreateIssuePOM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnCreateButton() {
        createIssueSubmitButton.click();
    }

    public void chooseProject(String projectName) {
        setInputFieldWithOptions(projectInputField, projectName);
    }

    public void chooseTypeOfIssue(String type) {
        setInputFieldWithOptions(typeInputField, type);
    }

    public void setSummaryOfIssue(String summary) {
        summaryTitleInput.clear();
        summaryTitleInput.sendKeys(summary);
    }

    private void setInputFieldWithOptions(WebElement element, String inputValue) {
        element.clear();
        element.sendKeys(inputValue);
        element.sendKeys(Keys.RETURN);
        titleOfCreateIssuePopup.click();
    }
}
