package com.ui.tests;

import com.constants.Browser;
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.ui.pages.MyAccountPage;
import com.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest2 {
    public static void main(String[] args) {
        //WebDriver driver = new ChromeDriver();  // Browsr session is created and Browser is launched

        HomePage homePage = new HomePage(Browser.CHROME, true);
        //homePage.goToWebsite("http://www.automationpractice.pl/index.php");
        homePage.maximizeWindow();
        LoginPage loginpage = homePage.goToLoginPage();
        MyAccountPage myAccountPage = loginpage.doLoginWith("automationpractice1@yopmail.com","password");
    }
}
