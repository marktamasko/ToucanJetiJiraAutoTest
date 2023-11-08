package org.codecool.toucanjeti.technical;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {
    private static final String URL = "https://jira-auto.codecool.metastage.net/login.jsp";
    private static final By USERNAME_INPUT_FIELD_ID = By.id("login-form-username");
    private static final By PASSWORD_INPUT_FIELD_ID = By.id("login-form-password");
    private static final By LOGIN_BUTTON_ID = By.id("login-form-submit");


    public static void toJiraWith(String username, String password, WebDriver driver) {
        driver.get(URL);
        driver.findElement(USERNAME_INPUT_FIELD_ID).sendKeys(username);
        driver.findElement(PASSWORD_INPUT_FIELD_ID).sendKeys(password);
        driver.findElement(LOGIN_BUTTON_ID).click();
    }
}
