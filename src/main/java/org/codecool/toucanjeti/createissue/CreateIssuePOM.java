package org.codecool.toucanjeti.createissue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateIssuePOM {
    private final WebDriver driver;
    @FindBy(id = "create_link") private WebElement createButton;


    public CreateIssuePOM(WebDriver driver) {
        this.driver = driver;
    }
}
