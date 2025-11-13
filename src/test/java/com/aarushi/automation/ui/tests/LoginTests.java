package com.aarushi.automation.ui.tests;

import com.aarushi.automation.ui.base.BaseTest;
import com.aarushi.automation.ui.pages.HomePage;
import com.aarushi.automation.ui.pages.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.annotations.Test;


public class LoginTests extends BaseTest {

    private static final Logger logger = LogManager.getLogger(LoginTests.class);
    @Test
    public void verifyLoginFunctionality() {
        logger.info("*******STARTING - VerifyLoginFunctionality test");
        driver.get("https://www.automationexercise.com/");
        HomePage homePage = new HomePage(driver);
        homePage.ClickLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.EnterEmailAddress();
        loginPage.EnterPassword();
        loginPage.ClickLogin();
        Assert.assertEquals("Logged in as testing", homePage.GetLoggedInUserName(), "Logged in user name does not match");
    }
}
