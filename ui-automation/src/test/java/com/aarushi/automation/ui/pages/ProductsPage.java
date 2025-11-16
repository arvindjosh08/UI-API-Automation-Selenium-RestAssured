package com.aarushi.automation.ui.pages;


import com.aarushi.automation.ui.actions.ElementActions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage
    {
        private static final Logger logger = LogManager.getLogger(ProductsPage.class);

        private WebDriver driver;
        private ElementActions actions;

        public ProductsPage(WebDriver driver)
        {
            this.driver = driver;
            this.actions = new ElementActions(driver);
        }

        private By searchedProducts = By.xpath("//div[contains(@class,'productinfo')]//p");
        private By searchInputBox = By.name("search");
        private By searchButton = By.id("submit_search");
        private By searchedProductTitle = By.xpath(" //div[contains(@class,'features_items')]//h2[contains(@class,'title')]");

        public void EnterProductName(String productName)
        {
            actions.sendKeys(searchInputBox, productName, "Search Input Box",10);
        }
        public void ClickSearchButton()
        {
            actions.click(searchButton, "Search Button",10);
        }

        public String GetSearchedProductTitle()
        {
            return actions.getText(searchedProductTitle, "Searched Product Title");
        }

        public Boolean IsSearchedProductsRelevant(String expectedKeyword)
        {
            Boolean allRelevant = true;
            List<WebElement> productsList = actions.safelyFindElements(searchedProducts);
            for (WebElement product : productsList)
            {
                String productName = actions.getText(product, "Searched Product Name");
                if (!productName.toLowerCase().contains(expectedKeyword))
                {
                    allRelevant = false;
                    logger.info("Irrelevant product found: " + productName);
                }
            }
            return allRelevant;
        }
    }

