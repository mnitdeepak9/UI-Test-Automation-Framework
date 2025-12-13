package com.ui.tests;

import static com.constants.Browser.*;
import com.ui.pages.HomePage;
import com.ui.pojo.User;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;





@Listeners(com.ui.listeners.TestListener.class)
public class LoginTest extends TestBase{

    Logger logger = LoggerUtility.getLogger(this.getClass());



    @Test(description = "Verifies if the valid user is able to login into Application",groups = {"e2e","sanity"},
            dataProviderClass = com.ui.dataproviders.LoginDataProvider.class,
            dataProvider = "LoginJSONDataProvider")
    public void LoginTest1(User user){

        //String userName = homePage.goToLoginPage().doLoginWith("automationpractice1@yopmail.com","password").getUserName();

        //Read data from JSON
        String userName = homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName();

        Assert.assertEquals(userName,"Deepak GUPTA");
        System.out.println("Logged in username is \n"+ userName);
    }



    @Test(description = "Verifies if the valid user is able to login into Application",groups = {"e2e","sanity"},
            dataProviderClass = com.ui.dataproviders.LoginDataProvider.class,
            dataProvider = "LoginCSVTestDataProvider")
    public void LoginCSVTest(User user) {

        //String userName = homePage.goToLoginPage().doLoginWith("automationpractice1@yopmail.com","password").getUserName();

        //Read data from JSON
        String userName = homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName();

        Assert.assertEquals(userName,"Deepak GUPTA");
        System.out.println("Logged in username is \n"+ userName);
    }

    @Test(description = "Verifies if the valid user is able to login into Application",groups = {"e2e","sanity"},
            dataProviderClass = com.ui.dataproviders.LoginDataProvider.class,
            dataProvider = "LoginExcelTestDataProvider",
            retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class)
    public void LoginExcelTest(User user) {
        //String userName = homePage.goToLoginPage().doLoginWith("automationpractice1@yopmail.com","password").getUserName();


        //logger.info("Started my login excel Test");
        //Read data from JSON
        String userName = homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName();

        Assert.assertEquals(userName,"Deepak GUPTA");
        System.out.println("Logged in username is \n"+ userName);
        //logger.info("Login Excel test completed!!!");
    }
}
