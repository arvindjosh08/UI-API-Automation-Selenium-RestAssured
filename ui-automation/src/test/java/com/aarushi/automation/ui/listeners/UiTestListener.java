package com.aarushi.automation.ui.listeners;

import com.aarushi.automation.common.reporting.ExtentManager;
import com.aarushi.automation.common.reporting.ExtentTestManager;
import com.aarushi.automation.ui.utilities.ScreenshotUtils;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class UiTestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(UiTestListener.class);

    @Override
    public void onStart(ITestContext context) {
        ExtentManager.initReport("UI_Test_Report");
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTestManager.startTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().pass("PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            ExtentTest test = ExtentTestManager.getTest();
            if (test != null) {
                test.fail(result.getThrowable());

                String base64 = ScreenshotUtils.captureScreenshotBase64(result.getMethod().getMethodName());
                if (base64 != null) {
                    test.fail("Screenshot on Failure",
                            MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
                }
            } else {
                System.err.println("ExtentTest is null for test: " + result.getMethod().getMethodName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.error("Test Failed: " + result.getName(), result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.flush();
    }
}