package org.codecool.toucanjeti;

import org.codecool.toucanjeti.Login.LoginTest;
import org.codecool.toucanjeti.Logout.LogoutTest;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        LoginTest loginTest = new LoginTest(new ChromeDriver());
        LogoutTest logoutTest = new LogoutTest(new ChromeDriver());
        loginTest.runTests();
        logoutTest.logout();
    }
}