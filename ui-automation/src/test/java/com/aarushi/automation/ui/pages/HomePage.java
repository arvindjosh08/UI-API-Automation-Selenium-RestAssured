package com.aarushi.automation.ui.pages;


import com.aarushi.automation.ui.actions.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage
    {
        private ElementActions actions;
        private WebDriver driver;
        public HomePage(WebDriver driver)
        {
            this.driver = driver;
            this.actions = new ElementActions(driver);
        }

        private By navigationBar = By.xpath("//ul[contains(@class,'navbar')]//a");
        private By loggedInUser = By.xpath("//ul[contains(@class,'navbar')]//a[contains(text(),'Logged')]");

        public void ClickLogin()
        {

            List<WebElement> navigationList = actions.safelyFindElements(navigationBar);
            //List<WebElement> navigationList = driver.findElements(navigationBar);

            for(WebElement navItem : navigationList)
            {
                String loginInnerText = actions.getText(navItem, "Login/Signup navigation item");
                if (loginInnerText.trim().toLowerCase().contains("Signup / Login".toLowerCase()))
                {
                    actions.click(navItem, "Login Navigation Item");
                    break;
                }
            }
        }

        public String GetLoggedInUserName()
        {
            WebElement element=actions.safelyFindElement(loggedInUser);
            return actions.getText(element, "Logged in user name element", 10);
        }

        public void ClickProducts()
        {
            List<WebElement> navigationList = actions.safelyFindElements(navigationBar);
            for(WebElement navItem : navigationList)
            {
                String productsInnerText = actions.getText(navItem, "Products navigation item");
                if (productsInnerText.trim().toLowerCase().contains("Products".toLowerCase()))
                {
                    actions.click(navItem, "Products Navigation Item");
                    break;
                }
            }
        }

        public void ClickDeleteAccount()
        {
            List<WebElement> navigationList = actions.safelyFindElements(navigationBar);
            for (WebElement navItem :navigationList)
            {
                String deleteInnerText = actions.getText(navItem, "Delete Account navigation item");
                if (deleteInnerText.trim().toLowerCase().contains("Delete".toLowerCase()))
                {
                    actions.click(navItem, "Delete Account Navigation Item");
                    break;
                }
            }
        }
    }
