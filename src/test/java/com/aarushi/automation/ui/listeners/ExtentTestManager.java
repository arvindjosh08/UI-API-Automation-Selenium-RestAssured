package com.aarushi.automation.ui.listeners;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentTestManager {

    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    private ExtentTestManager() {}

    public static void setTest(ExtentTest test) {
        testThread.set(test);
    }

    public static ExtentTest getTest() {
        return testThread.get();
    }

    public static ExtentTest createTest(String testName, String description) {
        ExtentTest test = ExtentManager.getExtentReports().createTest(testName, description);
        setTest(test);
        return test;
    }
}
