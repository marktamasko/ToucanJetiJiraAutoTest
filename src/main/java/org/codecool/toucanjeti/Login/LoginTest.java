package org.codecool.toucanjeti.Login;

import org.codecool.toucanjeti.technical.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginTest {
    private WebDriver driver;
    private static final String LEGIT_USERNAME = "automation58";
    private static final String LEGIT_PASSWORD = "CCAutoTest19.";
    private static final String INVALID_USERNAME = "KISKUTYA";
    private static final String INVALID_PASSWORD = "KISEBBKUTYA";
    private static final By AVATAR_ID = By.id("header-details-user-fullname");
    private static final By PROFILE_ID = By.id("view_profile");
    private static final By PROFILE_USERNAME_FIELD_ID = By.id("up-d-username");
    private static final By ERROR_MESSAGE_CLASS = By.className("aui-message-error");

    public LoginTest(WebDriver driver) {
        this.driver = driver;
    }

    private void successfulLogin() {
        Login.toJiraWith(LEGIT_USERNAME, LEGIT_PASSWORD, driver);
        driver.findElement(AVATAR_ID).click();
        driver.findElement(PROFILE_ID).click();
        System.out.println("Logged in the right account: " +
                driver.findElement(PROFILE_USERNAME_FIELD_ID).getText().equals(LEGIT_USERNAME));
    }
    private void emptyFieldsLogin() {
        Login.toJiraWith("", "", driver);
        System.out.println("Error message presents: " + driver.findElement(ERROR_MESSAGE_CLASS).isDisplayed());
    }
    private void wrongUsernamePasswordFieldsLogin() {
        Login.toJiraWith(INVALID_USERNAME, INVALID_PASSWORD, driver);
        System.out.println("Error message presents: " + driver.findElement(ERROR_MESSAGE_CLASS).isDisplayed());
    }
    private void legitUsernameWrongPasswordFieldsLogin() {
        Login.toJiraWith(LEGIT_USERNAME, INVALID_PASSWORD, driver);
        System.out.println("Error message presents: " + driver.findElement(ERROR_MESSAGE_CLASS).isDisplayed());
    }
    private void closePage() {
        driver.close();
    }
    public void runTests() {
        //Successful login
        this.successfulLogin();
        //No username, no password
        this.emptyFieldsLogin();
        //Wrong username, wrong password
        this.wrongUsernamePasswordFieldsLogin();
        //Legit username, wrong password
        this.legitUsernameWrongPasswordFieldsLogin();
        this.closePage();
    }
}
