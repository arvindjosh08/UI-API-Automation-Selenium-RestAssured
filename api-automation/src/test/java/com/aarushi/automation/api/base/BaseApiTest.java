package com.aarushi.automation.api.base;

import com.aarushi.automation.api.listeners.ApiTestListener;
import com.aarushi.automation.common.reporting.ExtentTestManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(ApiTestListener.class)
public class BaseApiTest {

    protected static final Logger logger = LogManager.getLogger(BaseApiTest.class);
    @BeforeMethod
    public void setUp() {
        // Any common setup per test
        System.out.println("API Test setup...");
    }

    @AfterMethod
    public void tearDown() {
        // Clean thread-local request spec to avoid memory leaks
        RestClientFactory.remove();
        System.out.println("API Test cleanup...");
    }
}
