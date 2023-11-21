package org.codecool.toucanjeti.Projects;

import org.codecool.toucanjeti.Login.LoginPOM;
import org.codecool.toucanjeti.mainPage.MainPagePOM;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ProjectPOMTest {


    @ParameterizedTest
//    @MethodSource TODO
    void isProjectPresentTest(String username, String password, String projectName) {
        WebDriver driver = new ChromeDriver();
        LoginPOM loginPOM = new LoginPOM(driver);
        MainPagePOM mainPagePOM = new MainPagePOM(driver);
        ProjectPOM projectPOM = new ProjectPOM(driver);
        loginPOM.enterUsername(username);
        loginPOM.enterPassword(password);
        loginPOM.clickLogIn();
        mainPagePOM.clickOnProjects();
        mainPagePOM.clickOnViewAllProjects();
        assertTrue(projectPOM.isProjectPresent(projectName));
        driver.close();
    }

}