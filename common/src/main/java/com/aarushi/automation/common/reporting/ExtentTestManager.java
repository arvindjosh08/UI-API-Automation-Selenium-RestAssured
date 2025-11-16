package com.aarushi.automation.common.reporting;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {

    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static synchronized ExtentTest startTest(String testName) {
        ExtentTest extentTest = ExtentManager.getExtent().createTest(testName);
        test.set(extentTest);
        return extentTest;
    }

    public static synchronized ExtentTest getTest() {
        return test.get();
    }
}