package org.codecool.toucanjeti.createissue;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

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
        projectInputField.clear();
        projectInputField.sendKeys();
        projectInputField.sendKeys(Keys.RETURN);
        titleOfCreateIssuePopup.click();
    }

    public void chooseTypeOfIssue(String type) {
        typeInputField.clear();
        typeInputField.sendKeys(type);
        typeInputField.sendKeys(Keys.RETURN);
        titleOfCreateIssuePopup.click();
    }

    public void setSummaryOfIssue(String summary) {
        summaryTitleInput.clear();
        summaryTitleInput.sendKeys(summary);
    }
}
