package org.codecool.toucanjeti.BrowseIssue;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BrowseIssuesPOM {
    private WebDriver driver;
    //searchbar
    @FindBy(id = "searcher-query")
    private WebElement searchbar;
    //pagination
    @FindBy(className = "aui-iconfont-chevron-right")
    private WebElement paginationNextButton;
//    @FindBy(className = "aui-iconfont-chevron-left")
//    private WebElement paginationPrevButton;
    @FindBy(xpath = "//div[@data-id='project']")
    private WebElement projectFilter;
    @FindBy(id = "searcher-pid-input")
    private WebElement projectFilterSearchbar;
    @FindBy(className = "no-results-message")
    private WebElement missingResultDiv;
    //Issue datas
    @FindBy(id = "project-name-val")
    private WebElement issueProjectName;
    @FindBy(id = "type-val")
    private WebElement issueType;
    @FindBy(id = "summary-val")
    private WebElement issueTitle;
    //Issue IDs
    @FindBy(className = "issue-link-key")
    private List<WebElement> issueKeys;

    public BrowseIssuesPOM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //TODO method search for title, return with list of title, project and type
    //TODO method for search result exist
    public boolean searchResultExist() {
        return missingResultDiv.isDisplayed();
    }

    public List<String> findIssueWithSearchbar(String keyword) {
        searchbar.sendKeys(keyword);
        searchbar.sendKeys(Keys.ENTER);
        return List.of(issueTitle.getText(), issueProjectName.getText(), issueType.getText());
    }

    public boolean filterIssuesByProject(String projectName) {
        projectFilter.click();
        projectFilterSearchbar.sendKeys(projectName);
        projectFilterSearchbar.sendKeys(Keys.ENTER);

        List<String> issueIDs = new ArrayList<>();
        while (paginationNextButton.isDisplayed()) {
            issueIDs.addAll(issueKeys.stream().map(WebElement::getText).toList());
            paginationNextButton.click();
        }
        return issueIDs.stream().filter(id -> !id.contains(projectName)).toList().size() > 0;
    }
}
