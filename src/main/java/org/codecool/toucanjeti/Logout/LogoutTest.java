package org.codecool.toucanjeti.Logout;

import org.codecool.toucanjeti.technical.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutTest {
    private WebDriver driver;
    private static final String LEGIT_USERNAME = "automation58";
    private static final String LEGIT_PASSWORD = "CCAutoTest19.";
    private static final By AVATAR_ID = By.id("header-details-user-fullname");
    private static final By LOGOUT_ID = By.id("log_out");

    public LogoutTest(WebDriver driver) {
        this.driver = driver;
    }
    public void logout() {
        Login.toJiraWith(LEGIT_USERNAME, LEGIT_PASSWORD, driver);
        driver.findElement(AVATAR_ID).click();
        driver.findElement(LOGOUT_ID).click();
    }
}
