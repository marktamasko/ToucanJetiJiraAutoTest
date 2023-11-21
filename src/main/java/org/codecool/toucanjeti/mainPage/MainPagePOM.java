package org.codecool.toucanjeti.mainPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// url: https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa

public class MainPagePOM {

    private final WebDriver webDriver;

    @FindBy(id = "home_link")
    private WebElement dashboardsNavElement;

    @FindBy(id = "browse_link")
    private WebElement projectsNavElement;

    @FindBy(id = "aui-responsive-header-dropdown-0-trigger")
    private WebElement moreNavElement;

    @FindBy(id = "create_link")
    private WebElement createNavElement;

    @FindBy(id = "header-details-user-fullname")
    private WebElement userAvatar;

    @FindBy(id = "view_profile")
    private WebElement viewUserProfile;

    @FindBy(id = "quickSearchInput")
    private WebElement searchInput;

    public MainPagePOM(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
}
