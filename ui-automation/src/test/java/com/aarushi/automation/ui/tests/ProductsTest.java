package com.aarushi.automation.ui.tests;

import com.aarushi.automation.common.utilities.ConfigReader;
import com.aarushi.automation.ui.base.BaseTest;
import com.aarushi.automation.ui.pages.HomePage;
import com.aarushi.automation.ui.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductsTest extends BaseTest
{

    @Test(groups = "ui")
    public void verifySearchProductsFunctionality()
    {
        logger.info("*******STARTING -  VerifySearchProductsFunctionality test");
        driver.get(ConfigReader.get("url"));
        HomePage homePage = new HomePage(driver);
        homePage.ClickProducts();
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.EnterProductName("jeans");
        productsPage.ClickSearchButton();
        Assert.assertEquals("SEARCHED PRODUCTS", productsPage.GetSearchedProductTitle(), "Searched Products title does not match");
        Assert.assertTrue(productsPage.IsSearchedProductsRelevant("jeans"), "Some searched products are not relevant");
    }

}
