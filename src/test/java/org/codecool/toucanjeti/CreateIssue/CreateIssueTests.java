package org.codecool.toucanjeti.CreateIssue;

import org.codecool.toucanjeti.BrowseIssue.BrowseIssuesPOM;
import org.codecool.toucanjeti.Login.LoginPOM;
import org.codecool.toucanjeti.Logout.LogoutPOM;
import org.codecool.toucanjeti.createissue.CreateIssuePOM;
import org.codecool.toucanjeti.mainPage.MainPagePOM;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateIssueTests {
    private static WebDriver driver;
    private static LoginPOM login;
    private static MainPagePOM mainPage;
    private static BrowseIssuesPOM browseIssues;
    private static CreateIssuePOM createIssue;
    private static LogoutPOM logout;

    @BeforeAll
    public static void setup() {
        driver = new FirefoxDriver();
        login = new LoginPOM(driver);
        mainPage = new MainPagePOM(driver);
        browseIssues = new BrowseIssuesPOM(driver);
        createIssue = new CreateIssuePOM(driver);
        logout = new LogoutPOM(driver);
    }

    @ParameterizedTest(name = "[{index}] {arguments}")
    @CsvFileSource(files = "src/test/resources/CreateIssueData.csv", numLinesToSkip = 1)
    public void testIssueCreation(String username, String password, String projectName, String issueType) throws InterruptedException {
        String uuid = UUID.randomUUID().toString();
        String summary = projectName + " " + issueType + " " + uuid;

        login.enterUsername(username);
        login.enterPassword(password);
        login.clickLogIn();
        mainPage.clickOnCreate();
        createIssue.chooseProject(projectName);
        createIssue.chooseTypeOfIssue(issueType);
        createIssue.setSummaryOfIssue(summary);
        createIssue.clickOnCreateButton();
        List<String> searchResultDetails = searchForCreatedIssueAndGetDetails(uuid);

        assertTrue(assertIfFoundIssueDetailsCorrespondWithGivenDetailsAtCreation(searchResultDetails, uuid, projectName, issueType));
    }

    @AfterEach
    public void logout() {
        logout.logout();
    }

    @AfterAll
    public static void tearDown() {
        driver.close();
    }

    private List<String> searchForCreatedIssueAndGetDetails(String uuid) throws InterruptedException {
        mainPage.clickOnIssues();
        mainPage.clickOnSearchForIssues();

        return browseIssues.findIssueWithSearchbar(uuid);
    }

    private boolean assertIfFoundIssueDetailsCorrespondWithGivenDetailsAtCreation(List<String> searchResultdetails, String uuid, String projectName, String issueType) {
        boolean correspondence = searchResultdetails.get(0).contains(uuid) &&
                searchResultdetails.get(1).contains(projectName) &&
                searchResultdetails.get(2).contains(issueType);

        if (!correspondence) {
            System.out.println("Project name should include: '" + projectName + "' and it is '" + searchResultdetails.get(1) + "'");
            System.out.println("Project issue type should be: " + issueType + " and it is '" + issueType + "'");
        }

        return correspondence;
    }
}
