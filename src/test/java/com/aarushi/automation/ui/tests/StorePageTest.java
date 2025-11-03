package com.aarushi.automation.ui.tests;

import com.aarushi.automation.ui.base.BaseClass;
import com.aarushi.automation.ui.base.DriverFactory;
import com.aarushi.automation.ui.pages.StorePage;
import com.aarushi.automation.ui.utilities.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StorePageTest extends BaseClass {



    @Test
    public void selectProducts(){
        DriverFactory.getDriver().get(ConfigReader.get("url"));
        StorePage storePage = new StorePage();
        storePage.addProducts();

    }

    @Test
    public void verifyPageName(){
        DriverFactory.getDriver().get(ConfigReader.get("url"));
        StorePage storePage = new StorePage();
        Assert.assertEquals(storePage.getPageName(),"STORE");
    }

    @Test
    public void verifyPageDescription(){
        DriverFactory.getDriver().get(ConfigReader.get("url"));
        StorePage storePage = new StorePage();
        Assert.assertEquals(storePage.getPageDescription(),"This is the Store Page.");

    }


}
