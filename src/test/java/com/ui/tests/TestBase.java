package com.ui.tests;

import com.constants.Browser;
import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.LambdaTestUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import static com.constants.Browser.CHROME;

public class TestBase {
    protected HomePage homePage;
    Logger logger = LoggerUtility.getLogger(this.getClass());
    private boolean isLabdaTest;
    private boolean isHeadless;

    @Parameters({"browser","isLabdaTest","isHeadless"})
    @BeforeMethod(description = "Load the Homepage of the Website")
    public void setup(
            @Optional("Chrome") String browser,
            @Optional("false") boolean isLabdaTest,
            @Optional("true") boolean isHeadless, ITestResult result){
        this.isLabdaTest = isLabdaTest;
        WebDriver lambdaDriver;
        if(isLabdaTest){
            lambdaDriver = LambdaTestUtility.initializeLambdaTestSession(browser,result.getMethod().getMethodName());
            homePage = new HomePage(lambdaDriver);
        }
        else{
        logger.info("Load the Homepage of the Website");
        //WebDriver driver = new ChromeDriver();  // Browsr session is created and Browser is launched
        //homePage = new HomePage(CHROME, isHeadless);
            homePage = new HomePage(Browser.valueOf(browser.toUpperCase()), isHeadless);
        }
    }


    public BrowserUtility getInstance(){
        return homePage;
    }

    @AfterMethod(description = "Tear Downn the Browser")
    public void tearDown(){
        if(isLabdaTest)
            LambdaTestUtility.quiteSession();
        else if(!isHeadless)
            homePage.quit();
    }
}
