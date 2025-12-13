package com.ui.pages;

import com.constants.Browser;
import static com.constants.Env.*;
import com.utility.BrowserUtility;
import static com.utility.PropertiesUtil.*;

import com.utility.JSONUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class HomePage extends BrowserUtility {

    private static final By SIGN_IN_LINK_LOCATOR = By.cssSelector("a[class='login']");
    Logger logger = LoggerUtility.getLogger(this.getClass());


    public HomePage(Browser browserName, boolean isHeadLess) {
        super(browserName,isHeadLess);
        //goToWebsite(readProperty(QA,"URL"));    //Read from property file
        goToWebsite(JSONUtility.readJSON(QA).getUrl());
        maximizeWindow();

    }

    public HomePage(WebDriver driver) {
        super(driver);
        //goToWebsite(readProperty(QA,"URL"));    //Read from property file
        goToWebsite(JSONUtility.readJSON(QA).getUrl());
        maximizeWindow();
    }

    public LoginPage goToLoginPage(){       //Void return type can not use in page function
        logger.info("Finding sign in button and Clicking on the sign in button");
        clickOn(SIGN_IN_LINK_LOCATOR);
        LoginPage loginPage=new LoginPage(getDriver());
        return loginPage;
    }


}
