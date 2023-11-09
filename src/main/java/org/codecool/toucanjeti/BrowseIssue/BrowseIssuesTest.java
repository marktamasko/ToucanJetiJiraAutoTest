package org.codecool.toucanjeti.BrowseIssue;

import org.codecool.toucanjeti.technical.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class BrowseIssuesTest {
    //Login
    private static final String LEGIT_USERNAME = "automation58";
    private static final String LEGIT_PASSWORD = "CCAutoTest19.";
    //Get to Issues
    private static final By ISSUE_MENU_ID = By.id("find_link");
    private static final By ALL_ISSUES_TEXT = By.linkText("Search for issues");
    //Check issue page
    private static final By LIST_OF_ISSUES_CLASS = By.className("issue-list");
    //Pagination bar
    private static final By NEXT_BUTTON_CLASS = By.className("aui-iconfont-chevron-right");
    private static final By PREVIOUS_BUTTON_CLASS = By.className("aui-iconfont-chevron-left");
    //Filters
    private static final By PROJECT_SELECT_BUTTON_CSS = By.cssSelector("[data-id='project']");
    private static final By ISSUE_IDS_CLASS = By.className("issue-link-key");

    private WebDriver driver;
    private Wait<WebDriver> wait;


    public BrowseIssuesTest(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void runTests() {
        testPagesOfAllIssue();
    }

    private void getToBrowseIssues() {
        Login.toJiraWith(LEGIT_USERNAME, LEGIT_PASSWORD, driver);
        driver.findElement(ISSUE_MENU_ID).click();
        wait.until(ExpectedConditions.elementToBeClickable(ALL_ISSUES_TEXT));
        driver.findElement(ALL_ISSUES_TEXT).click();
    }

    private void testPagesOfAllIssue() {
        getToBrowseIssues();
        int i = 1;
        Map<Integer, List<String>> issuesOfPages = new HashMap<>();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(String.format("%d",i))));
        while (!driver.findElements(By.linkText(String.format("%d",i))).isEmpty()) {
            wait.until(ExpectedConditions.presenceOfElementLocated(LIST_OF_ISSUES_CLASS));
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ISSUE_IDS_CLASS));
                issuesOfPages.put(
                        i,
                        driver.findElement(LIST_OF_ISSUES_CLASS)
                                .findElements(ISSUE_IDS_CLASS)
                                .stream()
                                .map(WebElement::getText)
                                .toList()
                );
                wait.until(ExpectedConditions.elementToBeClickable(By.linkText(String.format("%d",i))));
                driver.findElement(By.linkText(String.format("%d",i))).click();
                i++;
        }
        i -= 1;
        while (i != 0) {
            wait.until(ExpectedConditions.presenceOfElementLocated(LIST_OF_ISSUES_CLASS));
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ISSUE_IDS_CLASS));
            List<String> issueIDs = driver.findElement(LIST_OF_ISSUES_CLASS)
                    .findElements(ISSUE_IDS_CLASS)
                    .stream()
                    .map(WebElement::getText)
                    .toList();
            System.out.println(issueIDs.equals(issuesOfPages.get(i)));
            i--;
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText(String.format("%d",i))));
            driver.findElement(By.linkText(String.format("%d",i))).click();
        }
    }
    private void filterIssuesBySearch(By filter, String filterValue, By filterInput) {
        getToBrowseIssues();
        driver.findElement(filter).click();
        driver.findElement(filterInput).sendKeys(filterValue);
        driver.findElement(filterInput).sendKeys(Keys.ENTER);

    }
}
