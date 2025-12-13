package com.ui.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTestOLD {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();  // Browsr session is created and Browser is launched

        /*BrowserUtility browserUtility = new BrowserUtility(driver);
        browserUtility.goToWebsite("http://www.automationpractice.pl/index.php");
        browserUtility.maximizeWindow();

        By signInButton = By.cssSelector("a[class='login']");
        browserUtility.clickOn(signInButton);

        By emailTextBox = By.id("email");
        browserUtility.enterText(emailTextBox,"asd@as.com");

        By passwordTextBox = By.id("passwd");
        browserUtility.enterText(passwordTextBox,"password");

        By submitButton = By.id("SubmitLogin");
        browserUtility.clickOn(submitButton);*/
    }
}
