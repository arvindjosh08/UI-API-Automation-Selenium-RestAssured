package com.aarushi.automation.ui.base;

import com.aarushi.automation.ui.listeners.ExtentTestNGListener;
import com.aarushi.automation.ui.utilities.ConfigReader;
import com.aarushi.automation.ui.utilities.RunContext;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

@Listeners(ExtentTestNGListener.class)
public class BaseClass {

    //protected WebDriver driver;

    /**
     * Initializes the WebDriver before each test method.
     * Supports parallel execution using ThreadLocal driver.
     */
    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        try {
            DriverFactory.setDriver("chrome");
            DriverFactory.getDriver();

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize WebDriver: " + e.getMessage(), e);
        }
        System.out.println("Thread ID: " + Thread.currentThread().getId());
        System.out.println("Driver hashCode: " + DriverFactory.getDriver().hashCode());

    }

    /**
     * Quits the WebDriver after each test method.
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        try {
            if (DriverFactory.getDriver() != null) {
                DriverFactory.quitDriver();
            }
        } finally {
            // End test in Extent report
            //ExtentLogger.endTest();
            //ExtentManager.flushReports();
        }
    }

    @BeforeSuite(alwaysRun = true)
    public void beforeSuiteSetup() {
        ConfigReader.loadConfig();
        RunContext.initialize();
    }

}

