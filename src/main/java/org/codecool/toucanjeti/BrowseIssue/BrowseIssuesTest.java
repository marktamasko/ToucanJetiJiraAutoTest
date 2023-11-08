package org.codecool.toucanjeti.BrowseIssue;

import org.codecool.toucanjeti.technical.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BrowseIssuesTest {
    private WebDriver driver;
    private static final String LEGIT_USERNAME = "automation58";
    private static final String LEGIT_PASSWORD = "CCAutoTest19.";
    private static final By ISSUE_MENU_ID = By.id("find_link");
    private static final By ALL_ISSUES_ID = By.id("issues_new_search_link_lnk");
    private static final By LIST_OF_ISSUES_CLASS = By.className("issue-list");


    public BrowseIssuesTest(WebDriver driver) {
        this.driver = driver;
    }
    public void runTests() {
        getToBrowseIssues();
    }

    private void getToBrowseIssues() {
        Login.toJiraWith(LEGIT_USERNAME ,LEGIT_PASSWORD ,driver);
        driver.findElement(ISSUE_MENU_ID).click();
        driver.findElement(ALL_ISSUES_ID).click();
        System.out.println(driver.findElement(LIST_OF_ISSUES_CLASS) != null);
    }
}
