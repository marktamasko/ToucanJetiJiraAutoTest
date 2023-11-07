package org.codecool.toucanjeti;

import org.codecool.toucanjeti.Login.LoginTest;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        LoginTest loginTest = new LoginTest(new ChromeDriver());
        loginTest.goToLink();
//        loginTest.successfulLogin();
//        loginTest.emptyFieldsLogin();
        loginTest.wrongUsernamePasswordFieldsLogin();
    }
}