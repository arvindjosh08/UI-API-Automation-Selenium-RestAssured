package com.aarushi.automation.api.listeners;

import com.aarushi.automation.api.base.BaseApiTest;
import com.aarushi.automation.common.reporting.ExtentManager;
import com.aarushi.automation.common.reporting.ExtentTestManager;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ApiTestListener implements ITestListener {

    protected static final Logger logger = LogManager.getLogger(ApiTestListener.class);

    @Override
    public void onStart(ITestContext context) {
        ExtentManager.initReport("API_Test_Report");
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println(">>> onTestStart FIRED for " + result.getMethod().getMethodName());
        ExtentTestManager.startTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().pass("PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTestManager.getTest().fail(result.getThrowable());
        logger.error("Test Failed: " + result.getName(), result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.flush();
    }
}