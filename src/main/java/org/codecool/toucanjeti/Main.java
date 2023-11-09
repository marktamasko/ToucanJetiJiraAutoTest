package org.codecool.toucanjeti;

import org.codecool.toucanjeti.Login.LoginTest;
import org.codecool.toucanjeti.Logout.LogoutTest;
import org.codecool.toucanjeti.editIssue.EditIssue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        LoginTest loginTest = new LoginTest(driver);
        LogoutTest logoutTest = new LogoutTest(driver);
        EditIssue editIssue = new EditIssue(driver);

        loginTest.runTests();
        logoutTest.logout();

        editIssue.changeSummaryField();
        editIssue.changeIssueType();
        editIssue.changePriorityField();
        editIssue.changeLabelField();
        editIssue.changeDescriptionField();
        editIssue.addNewAttachment();
        editIssue.addNewComment();
    }
}