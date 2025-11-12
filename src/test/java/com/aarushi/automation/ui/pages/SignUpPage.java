package com.aarushi.automation.ui.pages;


import com.aarushi.automation.ui.actions.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignUpPage
    {
        private WebDriver driver;
        private ElementActions actions;
        public SignUpPage(WebDriver driver)
        {
            this.driver = driver;
            this.actions = new ElementActions(driver);
        }


        private By passwordInputBox = By.id("password");
        private By firstNameInputBox = By.id("first_name");
        private By lastNameInputBox = By.id("last_name");
        private By address1InputBox = By.id("address1");
        private By stateInputBox = By.id("state");
        private By cityInputBox = By.id("city");
        private By zipCodeInputBox = By.id("zipcode");
        private By mobileNumInputBox = By.id("mobile_number");
        private By createAccountButton = By.cssSelector("button[data-qa='create-account']");
        private By accountCreateText = By.xpath("//section[@id='form']//h2[contains(@data-qa,'account')]");
        private By continueButton = By.xpath("//section[@id='form']//a[contains(@data-qa,'continue')]");
        private By accountDeletion = By.xpath("//section[@id='form']//h2[contains(@data-qa,'account')]");



        public void EnterPassword(String password)
        {
            actions.sendKeys(passwordInputBox, password, "Password Input Box",20);
        }
        public void EnterFirstName(String firstName)
        {
            actions.sendKeys(firstNameInputBox, firstName, "Fisrtname Input Box",10);
        }
        public void EnterLastName(String lastName)
        {
            actions.sendKeys(lastNameInputBox, lastName, "Lastname Input Box",10);
        }
        public void EnterAddress(String address)
        {
            actions.sendKeys(address1InputBox, address, "Address Input Box",10);
        }
        public void EnterState(String state)
        {
            actions.sendKeys(stateInputBox, state, "State Input Box",10);
        }
        public void EnterCity(String city)
        {
            actions.sendKeys(cityInputBox, city, "City Input Box",10);
        }
        public void EnterZipCode(String zipCode)
        {
            actions.sendKeys(zipCodeInputBox, zipCode, "Zipcode Input Box",10);
        }
        public void EnterMobile(String mobile)
        {
            actions.sendKeys(mobileNumInputBox, mobile, "Mobile Number Input Box",10);
        }

        public void ClickSubmit()
        {
            //JavascriptExecutor js = (JavascriptExecutor)driver;
            //js.executeScript("window.scrollTo(arguments[0], arguments[1]);", 0, 1200);
            actions.click(createAccountButton, "Submit Button",10);
            WaitForContinueButtonToVisible();
        }

        public String GetAccountCreateText()
        {
            return actions.getText(driver.findElement(accountCreateText), "Account Created Text", 10);
        }

        public void ClickContinueButton()
        {
            actions.click(driver.findElement(continueButton), "Continue button");
        }

        public String GetAccountDeletionText()
        {
            return actions.getText(driver.findElement(accountDeletion), "Account Deletion Text");
        }
        public void WaitForContinueButtonToVisible()
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    By.xpath("//section[@id='form']//a[contains(@data-qa,'continue')]"),
                    "Continue"));
        }
    }
