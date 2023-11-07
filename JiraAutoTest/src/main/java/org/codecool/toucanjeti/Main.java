package org.codecool.toucanjeti;

import org.codecool.toucanjeti.Login.LoginTest;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Main {
    public static void main(String[] args) {
        LoginTest loginTest = new LoginTest(new ChromeDriver());
        loginTest.runTests();
    }
}