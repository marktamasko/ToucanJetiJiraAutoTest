package org.codecool.toucanjeti.BrowseIssue;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BrowseIssuesPOM {
    private final WebDriver driver;
    private final WebDriverWait wait;
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
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    //TODO method search for title, return with list of title, project and type
    //TODO method for search result exist
    public boolean searchResultExist(String keyword) {
        wait.until(ExpectedConditions.visibilityOf(searchbar));
        searchbar.sendKeys(keyword);
        searchbar.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOf(missingResultDiv));
        return missingResultDiv.isDisplayed();
    }

    public List<String> findIssueWithSearchbar(String keyword) {
        searchbar.sendKeys(keyword);
        searchbar.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOf(issueTitle));
        wait.until(ExpectedConditions.visibilityOf(issueProjectName));
        wait.until(ExpectedConditions.visibilityOf(issueType));
        return List.of(issueTitle.getText(), issueProjectName.getText(), issueType.getText());
    }

    public boolean filterIssuesByProject(String projectName) {
        projectFilter.click();
        projectFilterSearchbar.sendKeys(projectName);
        projectFilterSearchbar.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOf(paginationNextButton));

        List<String> issueIDs = new ArrayList<>();
        while (paginationNextButton.isDisplayed()) {
            wait.until(ExpectedConditions.visibilityOfAllElements(issueKeys));
            issueIDs.addAll(issueKeys.stream().map(WebElement::getText).toList());
            wait.until(ExpectedConditions.visibilityOf(paginationNextButton));
            paginationNextButton.click();
        }
        return issueIDs.stream().filter(id -> !id.contains(projectName)).toList().size() > 0;
    }
}
