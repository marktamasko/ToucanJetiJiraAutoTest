package org.codecool.toucanjeti.Logout;

import org.codecool.toucanjeti.technical.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogoutPOM {
    private final WebDriver driver;
    private final WebDriverWait wait;
    @FindBy(id = "header-details-user-fullname")
    private WebElement avatar;
    @FindBy(id = "log_out")
    private WebElement logoutButton;

    public LogoutPOM(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver,this);
    }
    public boolean logout() {
        avatar.click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutButton.click();
        return driver.getTitle().equals("Logout - Jira Auto");
    }
}
