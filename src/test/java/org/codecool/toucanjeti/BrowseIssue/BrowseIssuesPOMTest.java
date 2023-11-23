package org.codecool.toucanjeti.BrowseIssue;

import org.codecool.toucanjeti.Login.LoginPOM;
import org.codecool.toucanjeti.Logout.LogoutPOM;
import org.codecool.toucanjeti.mainPage.MainPagePOM;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BrowseIssuesPOMTest {
    private static WebDriver driver;
    private static LoginPOM loginPOM;
    private static MainPagePOM mainPagePOM;
    private static BrowseIssuesPOM browseIssuePOM;
    private static LogoutPOM logoutPOM;

    @BeforeAll
    static void setUpAll() {
        driver = new ChromeDriver();
    }
    @BeforeEach
    void setUp() {
        loginPOM = new LoginPOM(driver);
        mainPagePOM = new MainPagePOM(driver);
        browseIssuePOM = new BrowseIssuesPOM(driver);
        logoutPOM = new LogoutPOM(driver);
    }
    private void login(String username, String password) {
        loginPOM.enterUsername(username);
        loginPOM.enterPassword(password);
        loginPOM.clickLogIn();
    }
    private void navigateToIssues() {
        mainPagePOM.clickOnIssues();
        mainPagePOM.clickOnSreachForIssues();
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/LegitLoginDatas.csv", numLinesToSkip = 1)
    void searchResultExistTest(String username, String password) {
        this.login(username,password);
        this.navigateToIssues();
        assertTrue(browseIssuePOM.searchResultExist("asfaegebehrb"));
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/SearchDatas.csv", numLinesToSkip = 1)
    void findIssueWithSearchbarTest(String username, String password, String projectName) throws InterruptedException {
        this.login(username, password);
        this.navigateToIssues();
        List<String> issueData = browseIssuePOM.findIssueWithSearchbar(projectName);
        assertTrue(issueData.contains(projectName));
    }

    @Test
    void filterIssuesByProjectTest() {
    }
}