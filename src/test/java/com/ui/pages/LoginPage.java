package com.ui.pages;

import com.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BrowserUtility {
    static final private By EMAIL_TEXT_BOX_LOCATOR = By.id("email");
    static final private By PASSWORD_TEXT_BOX_LOCATOR = By.id("passwd");
    static final private By SUBMIT_BUTTON_LOCATOR = By.id("SubmitLogin");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public MyAccountPage doLoginWith(String email, String password){
        enterText(EMAIL_TEXT_BOX_LOCATOR,email);
        enterText(PASSWORD_TEXT_BOX_LOCATOR,password);
        clickOn(SUBMIT_BUTTON_LOCATOR);
        MyAccountPage myAccountPage = new MyAccountPage(getDriver());
        return myAccountPage;
    }
}
