package com.aarushi.automation.ui.tests;

import com.aarushi.automation.common.utilities.ConfigReader;
import com.aarushi.automation.ui.base.BaseTest;
import com.aarushi.automation.ui.model.SignUpModel;
import com.aarushi.automation.ui.pages.HomePage;
import com.aarushi.automation.ui.pages.LoginPage;
import com.aarushi.automation.ui.pages.SignUpPage;
import com.aarushi.automation.ui.utilities.TestDataProvider;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class SignUpTest extends BaseTest
    {
        @Test(dataProvider = "signup", dataProviderClass = TestDataProvider.class)
        public  void verifySignUpAndDeleteFunctionality(SignUpModel signUpModel)
        {
            logger.info("*******STARTING -  VerifySignUpAndDeleteFunctionality test");
            driver.get(ConfigReader.get("url"));
            HomePage homePage = new HomePage(driver);
            homePage.ClickLogin();
            LoginPage loginPage = new LoginPage(driver);
            String randomName= loginPage.EnterSignUpName();
            loginPage.EnterSignUpEmail();
            loginPage.ClickSignUpButton();
            SignUpPage signUpPage = new SignUpPage(driver);
            signUpPage.EnterPassword(signUpModel.getPassword());
            signUpPage.EnterFirstName(signUpModel.getFirstName());
            signUpPage.EnterLastName(signUpModel.getLastName());
            signUpPage.EnterAddress(signUpModel.getAddress1());
            signUpPage.EnterState(signUpModel.getState());
            signUpPage.EnterCity(signUpModel.getCity());
            signUpPage.EnterZipCode(signUpModel.getZipCode());
            signUpPage.EnterMobile(signUpModel.getMobileNum());
            signUpPage.ClickSubmit();
            Assert.assertEquals(signUpPage.GetAccountCreateText(),"ACCOUNT CREATED!");
            signUpPage.ClickContinueButton();
            Assert.assertEquals(homePage.GetLoggedInUserName(),"Logged in as" + " " + randomName, "Logged in user name does not match");
            homePage.ClickDeleteAccount();
            Assert.assertEquals(signUpPage.GetAccountDeletionText(),"ACCOUNT DELETED!",  "Account deletion text does not match");
        }
    }
