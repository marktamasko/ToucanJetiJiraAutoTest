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
    public void testIssueCreation(String username, String password, String projectName, String issueType) {
        UUID uuid = UUID.randomUUID();
        String summary = projectName + " " + issueType + " " + uuid;
        login.enterUsername(username);
        login.enterPassword(password);
        login.clickLogIn();
        mainPage.clickOnCreate();
        createIssue.chooseProject(projectName);
        createIssue.chooseTypeOfIssue(issueType);
        createIssue.setSummaryOfIssue(summary);
        createIssue.clickOnCreateButton();
        mainPage.clickOnIssues();
        mainPage.clickOnSearchForIssues();
        List<String> searchResultDetails = browseIssues.findIssueWithSearchbar(uuid.toString());

//        System.out.println(searchResultDetails);
//        System.out.println(projectName + " " + issueType + " " + uuid);

        String resultSummaryTitle = searchResultDetails.get(0);
        String resultProjectName = searchResultDetails.get(1);
        String resultIssueType = searchResultDetails.get(2);

        boolean resultTitleIncludesUUID = resultSummaryTitle.contains(uuid.toString());
        boolean resultProjectNameIncludesTestDataProjectName = resultProjectName.contains(projectName);
        boolean resultIssueTypeIncludesTestDataIssueType = resultIssueType.contains(issueType);

        assertTrue(resultTitleIncludesUUID);
        assertTrue(resultProjectNameIncludesTestDataProjectName);
        assertTrue(resultIssueTypeIncludesTestDataIssueType);
    }

    @AfterEach
    public void logout() {
        logout.logout();
    }

    @AfterAll
    public static void tearDown() {
        driver.close();
    }
}
