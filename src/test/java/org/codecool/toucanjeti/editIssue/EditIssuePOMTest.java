package org.codecool.toucanjeti.editIssue;

import org.codecool.toucanjeti.Login.LoginPOM;
import org.codecool.toucanjeti.Logout.LogoutPOM;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EditIssuePOMTest {

    private static WebDriver webDriver;
    private static String issueUrl;
    private LoginPOM login;
    private LogoutPOM logout;
    private EditIssuePOM editIssue;

    @BeforeAll
    static void setUpAll() {
        webDriver = new ChromeDriver();
        issueUrl = "https://jira-auto.codecool.metastage.net/browse/MTP-4389";
    }

    @BeforeEach
    void setUp() {
        this.login = new LoginPOM(webDriver);
        this.logout = new LogoutPOM(webDriver);
        this.editIssue = new EditIssuePOM(webDriver);
    }

    @AfterEach
    void tearDown() {
        resetSummaryFieldToOriginal();
        resetIssueTypeFieldToOriginal();
        resetDescriptionFieldToOriginal();
        resetPriorityFieldToOriginal();
        this.logout.logout();
    }

    @AfterAll
    static void tearDownAll() {
        webDriver.quit();
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/LegitLoginDatas.csv", numLinesToSkip = 1)
    void testChangeSummaryField(String username, String password) {

        String expected = "test summary";

        this.login.enterUsername(username);
        this.login.enterPassword(password);
        this.login.clickLogIn();
        webDriver.get(issueUrl);
        this.editIssue.clickEditButton(this.editIssue.getSummaryLocator());
        this.editIssue.changeSummaryField(expected);
        this.editIssue.clickUpdateButton(this.editIssue.getSummaryTextLocator());


        String actual = this.editIssue.getSummaryTextValue();

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/LegitLoginDatas.csv", numLinesToSkip = 1)
    void testChangeIssueType(String username, String password) {

        String expectedIssue = "Story";

        this.login.enterUsername(username);
        this.login.enterPassword(password);
        this.login.clickLogIn();
        webDriver.get(issueUrl);
        this.editIssue.clickEditButton(this.editIssue.getIssueType());
        this.editIssue.changeIssueType(expectedIssue);
        this.editIssue.clickUpdateButton(this.editIssue.getIssueTypeUpdateValue());

        String actual = this.editIssue.getIssueTypeValue();

        assertEquals(expectedIssue, actual);
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/LegitLoginDatas.csv", numLinesToSkip = 1)
    void testChangeDescription(String username, String password) {

        String expected = "it's test description";

        this.login.enterUsername(username);
        this.login.enterPassword(password);
        this.login.clickLogIn();
        webDriver.get(issueUrl);
        this.editIssue.clickEditButton(this.editIssue.getDescription());
        this.editIssue.changeDescription(this.editIssue.getDescription(), expected);
        this.editIssue.clickUpdateButton(this.editIssue.getDescription());

        String actual = this.editIssue.getDescriptionValue();

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/LegitLoginDatas.csv", numLinesToSkip = 1)
    void testChangePriority(String username, String password) {

        String expected = "Low";

        this.login.enterUsername(username);
        this.login.enterPassword(password);
        this.login.clickLogIn();
        webDriver.get(issueUrl);
        this.editIssue.clickEditButton(this.editIssue.getPriority());
        this.editIssue.changePriority(expected);
        this.editIssue.clickUpdateButton(this.editIssue.getPriorityLocator());

        String actual = this.editIssue.getPriorityValue();

        assertEquals(expected, actual);
    }

    @Test
    void testChangeLabel() {
    }

    @Test
    void testAddNewComment() {
    }

    private void resetSummaryFieldToOriginal() {
        if (this.editIssue.getSummaryTextValue().equals("test summary")) {
            this.editIssue.clickEditButton(this.editIssue.getSummaryLocator());
            this.editIssue.changeSummaryField("original summary");
            this.editIssue.clickUpdateButton(this.editIssue.getSummaryTextLocator());
        }
    }

    private void resetIssueTypeFieldToOriginal() {
        if (this.editIssue.getIssueTypeValue().equals("Story")) {
            this.editIssue.clickEditButton(this.editIssue.getIssueType());
            this.editIssue.changeIssueType("Task");
            this.editIssue.clickUpdateButton(this.editIssue.getIssueTypeUpdateValue());
        }
    }

    private void resetDescriptionFieldToOriginal() {
        if (this.editIssue.getSummaryTextValue().equals("it's test description")) {
            this.editIssue.changeSummaryField("it's original description");
        }
    }

    private void resetPriorityFieldToOriginal() {
        if (this.editIssue.getPriorityValue().equals("Low")) {
            this.editIssue.clickEditButton(this.editIssue.getIssueType());
            this.editIssue.changePriority("Lowest");
            this.editIssue.clickUpdateButton(this.editIssue.getIssueTypeUpdateValue());
        }
    }
}