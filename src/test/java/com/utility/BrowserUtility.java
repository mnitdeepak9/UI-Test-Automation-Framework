package com.utility;

import com.constants.Browser;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BrowserUtility {
    //private WebDriver driver;
    private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    Logger logger = LoggerUtility.getLogger(this.getClass());

    public BrowserUtility(WebDriver driver) {
         this.driver.set(driver);       //this.driver = driver;
    }
    public BrowserUtility(String browserName) {
        logger.info("Launching Browser: "+browserName);
        if(browserName.equalsIgnoreCase("Chrome"))
            driver.set(new ChromeDriver());  //driver = new ChromeDriver();
        else if(browserName.equalsIgnoreCase("edge"))
            driver.set(new EdgeDriver());   //driver = new EdgeDriver();
        else if(browserName.equalsIgnoreCase("firefox"))
            driver.set(new FirefoxDriver());   //driver = new FirefoxDriver();
        else{
            logger.error("Invalid Browser Name...");
            System.err.println("Invalid Browser Name...");
        }
    }

    public BrowserUtility(Browser browserName) {
        logger.info("Launching Browser: "+browserName);
        if(browserName ==Browser.CHROME)
            driver.set(new ChromeDriver());  //driver = new ChromeDriver();
        else if(browserName == Browser.EDGE)
            driver.set(new EdgeDriver());   //driver = new EdgeDriver();
        else if(browserName == Browser.FIREFOX)
            driver.set(new FirefoxDriver());   //driver = new FirefoxDriver();
        else {
            logger.error("Invalid Browser Name...");
            System.err.println("Invalid Browser Name...");
        }
    }

    public BrowserUtility(Browser browserName, boolean isHeadLess) {
        logger.info("Launching Browser: "+browserName);
        if(browserName ==Browser.CHROME) {
            if(isHeadLess) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new"); //Headless
                options.addArguments("--window-size=1920,1080");
                driver.set(new ChromeDriver(options));  //driver = new ChromeDriver();
            }else
                driver.set(new ChromeDriver());
        }
        else if(browserName == Browser.EDGE)
            if(isHeadLess) {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--headless=old"); //Headless
                options.addArguments("disable-gpu");
                driver.set(new EdgeDriver(options));  //driver = new ChromeDriver();
            }else
                driver.set(new EdgeDriver());   //driver = new EdgeDriver();
        else if(browserName == Browser.FIREFOX)
            if(isHeadLess) {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless=old"); //Headless
                driver.set(new FirefoxDriver(options));  //driver = new ChromeDriver();
            }else
                driver.set(new FirefoxDriver());   //driver = new FirefoxDriver();
        else {
            logger.error("Invalid Browser Name...");
            System.err.println("Invalid Browser Name...");
        }
    }

    public WebDriver getDriver() {
        return driver.get();        //return driver;
    }

    public void goToWebsite(String url){
        logger.info("opening the website"+url);
        driver.get().get(url);
    }

    public void maximizeWindow(){
        logger.info("Maximizing the browser window");
        driver.get().manage().window().maximize();
    }

    public void clickOn(By locator){
        logger.info("Finding Element with the locator:"+locator);
        WebElement webElement = driver.get().findElement(locator);
        logger.info("Element found and now performing the click operation");
        webElement.click();
    }

    public void enterText(By locator, String text){
        logger.info("Finding Element with the locator:"+locator);
        WebElement webElement = driver.get().findElement(locator);
        logger.info("Element found and now enter the text");
        webElement.sendKeys(text);
    }

    public String getVisibleText(By locator){
        logger.info("Finding Element with the locator:"+locator);
        WebElement element = driver.get().findElement(locator);
        logger.info("Element found and now returning the visible text: "+element.getText());
        return element.getText();
    }

    public String takeScreenShot(String name){
        TakesScreenshot screenshot = (TakesScreenshot) driver.get();
        File screenShotData = screenshot.getScreenshotAs(OutputType.FILE);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH=mm-ss");
        String timeStamp = format.format(date);

        String filePath = System.getProperty("user.dir")+"//screenshots//"+name+" - "+timeStamp+".png";
        File screenShotFile = new File(filePath);

        try {
            FileUtils.copyFile(screenShotData,screenShotFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return filePath;
    }

    public void quit(){
        driver.get().quit();
    }
}
