package com.ui.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ui.tests.TestBase;
import com.utility.BrowserUtility;
import com.utility.ExtentReporterUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class TestListener implements ITestListener  {
    Logger logger = LoggerUtility.getLogger(this.getClass());

    ExtentSparkReporter extentSparkReporter;     //Provide you functionality to create HTML Report, their look, style etc.
    ExtentReports extentReports;                //do the Heavy Lifting (Data to be dumped into the HTML Report)
    ExtentTest extentTest;                      //To store information about Test

    public void onTestStart(ITestResult result) {
        logger.info(result.getMethod().getMethodName());
        logger.info(result.getMethod().getDescription());
        logger.info(Arrays.toString(result.getMethod().getGroups()));

        //extentTest = extentReports.createTest(result.getMethod().getMethodName());
        ExtentReporterUtility.createExtentText(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        logger.info(result.getMethod().getMethodName()+" PASSED!!");

        //extentTest.log(Status.PASS, result.getMethod().getMethodName()+" PASSED!!");
        ExtentReporterUtility.getTest().log(Status.PASS, result.getMethod().getMethodName()+" PASSED!!");
    }

    public void onTestFailure(ITestResult result) {
        logger.error(result.getMethod().getMethodName()+" FAILED!!");
        logger.error(result.getThrowable().getMessage());

        //extentTest.log(Status.FAIL, result.getMethod().getMethodName()+" FAILED!!");
        ExtentReporterUtility.getTest().log(Status.FAIL, result.getMethod().getMethodName()+" FAILED!!");
        ExtentReporterUtility.getTest().log(Status.FAIL, result.getThrowable().getMessage());

        Object testClass = result.getInstance();
        BrowserUtility browserUtility = ((TestBase)testClass).getInstance();
        String screenShotPath = browserUtility.takeScreenShot(result.getMethod().getMethodName());
        logger.info("Attaching the Screenshot to the HTML File");
        ExtentReporterUtility.getTest().addScreenCaptureFromPath(screenShotPath);
    }

    public void onTestSkipped(ITestResult result) {
        logger.warn(result.getMethod().getMethodName()+" SKIPPED!!");

        //extentTest.log(Status.SKIP, result.getMethod().getMethodName()+" SKIPPED!!");
        ExtentReporterUtility.getTest().log(Status.SKIP, result.getMethod().getMethodName()+" SKIPPED!!");
    }

    public void onStart(ITestContext context) {
        logger.info("Test Suite Started");
        /*extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"//extentReportFile.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);*/
        ExtentReporterUtility.setupSparkReporter("extentReportFile.html");
    }

    public void onFinish(ITestContext context) {
        logger.info("Test Suite Completed");
        //extentReports.flush();
        ExtentReporterUtility.flushReport();
    }
}
