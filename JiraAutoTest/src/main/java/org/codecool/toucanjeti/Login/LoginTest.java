package org.codecool.toucanjeti.Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginTest {
    private WebDriver driver;
    private static final String LEGIT_USERNAME = "automation58";
    private static final String LEGIT_PASSWORD = "CCAutoTest19.";
    private static final String INVALID_USERNAME = "KISKUTYA";
    private static final String INVALID_PASSWORD = "KISEBBKUTYA";
    private static final String URL = "https://jira-auto.codecool.metastage.net/login.jsp";
    private static final By USERNAME_INPUT_FIELD_ID = By.id("login-form-username");
    private static final By PASSWORD_INPUT_FIELD_ID = By.id("login-form-password");
    private static final By LOGIN_BUTTON_ID = By.id("login-form-submit");
    private static final By AVATAR_ID = By.id("header-details-user-fullname");
    private static final By PROFILE_ID = By.id("view_profile");
    private static final By PROFILE_USERNAME_FIELD_ID = By.id("up-d-username");
    private static final By ERROR_MESSAGE_CLASS = By.className("aui-message-error");

    public LoginTest(WebDriver driver) {
        this.driver = driver;
    }

    public void goToLink() {
        driver.get(URL);
    }
    public void successfulLogin() {
        driver.findElement(USERNAME_INPUT_FIELD_ID).sendKeys(LEGIT_USERNAME);
        driver.findElement(PASSWORD_INPUT_FIELD_ID).sendKeys(LEGIT_PASSWORD);
        driver.findElement(LOGIN_BUTTON_ID).click();
        driver.findElement(AVATAR_ID).click();
        driver.findElement(PROFILE_ID).click();
        System.out.println("Logged in the right account: " +
                driver.findElement(PROFILE_USERNAME_FIELD_ID).getText().equals(LEGIT_USERNAME));
    }
    public void emptyFieldsLogin() {
        driver.findElement(USERNAME_INPUT_FIELD_ID).sendKeys("");
        driver.findElement(PASSWORD_INPUT_FIELD_ID).sendKeys("");
        driver.findElement(LOGIN_BUTTON_ID).click();
        System.out.println("Error message presents: " + driver.findElement(ERROR_MESSAGE_CLASS).isDisplayed());
    }
    public void wrongUsernamePasswordFieldsLogin() {
        driver.findElement(USERNAME_INPUT_FIELD_ID).sendKeys(INVALID_USERNAME);
        driver.findElement(PASSWORD_INPUT_FIELD_ID).sendKeys(INVALID_PASSWORD);
        driver.findElement(LOGIN_BUTTON_ID).click();
        System.out.println("Error message presents: " + driver.findElement(ERROR_MESSAGE_CLASS).isDisplayed());
    }
    public void legitUsernameWrongPasswordFieldsLogin() {
        driver.findElement(USERNAME_INPUT_FIELD_ID).sendKeys(LEGIT_USERNAME);
        driver.findElement(PASSWORD_INPUT_FIELD_ID).sendKeys(INVALID_PASSWORD);
        driver.findElement(LOGIN_BUTTON_ID).click();
        System.out.println("Error message presents: " + driver.findElement(ERROR_MESSAGE_CLASS).isDisplayed());
    }
}
