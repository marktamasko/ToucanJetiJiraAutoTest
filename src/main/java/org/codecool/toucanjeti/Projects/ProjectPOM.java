package org.codecool.toucanjeti.Projects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProjectPOM {
    private WebDriver driver;
    @FindBy(className = "cell-type-key")
    private List<WebElement> projectKeys;

    public ProjectPOM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public boolean isProjectPresent(String key) {
        return projectKeys.stream().map(WebElement::getText).toList().contains(key);
    }
}
