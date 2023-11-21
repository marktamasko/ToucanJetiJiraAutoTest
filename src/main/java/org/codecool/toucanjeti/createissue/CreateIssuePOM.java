package org.codecool.toucanjeti.createissue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateIssuePOM {
    private final WebDriver driver;
    @FindBy(id = "create-issue-submit") private WebElement createIssueSubmitButton;
    @FindBy(id = "project-field") private WebElement projectInputField;
    @FindBy(xpath = "//*[@id='project-single-select']/span") private WebElement projectDropdownHandle;
    @FindBy(id = "all-projects") private WebElement projectOptionsSection;
    @FindBy(id = "issuetype-field") private WebElement typeInputField;
    @FindBy(xpath = "//*[@id='issuetype-single-select']/span") private WebElement typeDropdownHandle;
    @FindBy(xpath = "//*[@id='issuetype-suggestions']/descendant::ul") private WebElement typeOptionsSection;
    @FindBy(id = "summary") private WebElement summaryTitleInput;
    @FindBy(css = "button.cancel") private WebElement cancelIssueCreationButton;

    public CreateIssuePOM(WebDriver driver) {
        this.driver = driver;
    }
}
