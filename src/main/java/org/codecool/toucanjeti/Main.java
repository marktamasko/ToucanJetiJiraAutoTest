package org.codecool.toucanjeti;

import org.codecool.toucanjeti.BrowseIssue.BrowseIssuesTest;
import org.codecool.toucanjeti.Login.LoginTest;
import org.codecool.toucanjeti.Logout.LogoutTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;

public class Main {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        LoginTest loginTest = new LoginTest(driver);
        LogoutTest logoutTest = new LogoutTest(driver);
        BrowseIssuesTest browseIssues = new BrowseIssuesTest(driver);
        loginTest.runTests();
        logoutTest.logout();
        browseIssues.runTests();
        driver.close();
    }
}