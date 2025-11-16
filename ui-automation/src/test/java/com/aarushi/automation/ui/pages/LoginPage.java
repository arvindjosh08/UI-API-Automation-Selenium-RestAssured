package com.aarushi.automation.ui.pages;

import com.aarushi.automation.ui.actions.ElementActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.UUID;


public class LoginPage
    {
        private WebDriver driver;
        private ElementActions actions;
        public LoginPage(WebDriver driver)
        {
            this.driver = driver;
            this.actions = new ElementActions(driver); // ElementActions fetches driver internally
        }

        private By emailAddress = By.name("email");
        private By password = By.name("password");
        private By loginButton = By.cssSelector("button[data-qa='login-button']");
        private By signUpName = By.xpath("//input[@name='name']");
        private By signUpEmail = By.xpath("//input[@data-qa='signup-email']");
        private By signUpButton = By.cssSelector("button[data-qa='signup-button']");


        public void EnterEmailAddress()
        {
            actions.sendKeys(emailAddress, "janison@gmail.com", "Email Address Input",10);
        }

        public void EnterPassword()
        {
            actions.sendKeys(password, "testing", "Email Address Input",10);
        }

        public void ClickLogin()
        {
            actions.click(loginButton, "Login Button",10);
        }

        public void EnterSignUpEmail()
        {
            String randomEmail = "User_" + UUID.randomUUID().toString().replace("-", "") + "@example.com";
            actions.sendKeys(signUpEmail, randomEmail, "Email Address Input",10);
        }

        public String EnterSignUpName()
        {
            String randomName = "Product_" + UUID.randomUUID().toString().replace("-", "");
            actions.sendKeys(signUpName, randomName, "SignUp Name Input",20);
            return randomName;
        }

        public void ClickSignUpButton()
        {
            actions.click(signUpButton, "SignUp Button",10);
        }
    }
