package org.codecool.toucanjeti.Logout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutTest {
    private WebDriver driver;
    private static final String LEGIT_USERNAME = "automation58";
    private static final String LEGIT_PASSWORD = "CCAutoTest19.";
    private static final String URL = "https://jira-auto.codecool.metastage.net/login.jsp";
    private static final By USERNAME_INPUT_FIELD_ID = By.id("login-form-username");
    private static final By PASSWORD_INPUT_FIELD_ID = By.id("login-form-password");
    private static final By LOGIN_BUTTON_ID = By.id("login-form-submit");
    private static final By AVATAR_ID = By.id("header-details-user-fullname");
    private static final By LOGOUT_ID = By.id("log_out");

    public LogoutTest(WebDriver driver) {
        this.driver = driver;
    }
    public void logout() {
        driver.get(URL);
        driver.findElement(USERNAME_INPUT_FIELD_ID).sendKeys(LEGIT_USERNAME);
        driver.findElement(PASSWORD_INPUT_FIELD_ID).sendKeys(LEGIT_PASSWORD);
        driver.findElement(LOGIN_BUTTON_ID).click();
        driver.findElement(AVATAR_ID).click();
        driver.findElement(LOGOUT_ID).click();
    }
}
