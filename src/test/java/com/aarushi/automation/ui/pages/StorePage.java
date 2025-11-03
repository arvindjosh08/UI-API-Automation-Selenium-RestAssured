package com.aarushi.automation.ui.pages;

import com.aarushi.automation.ui.actions.ElementActions;
import com.aarushi.automation.ui.base.BasePage;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class StorePage extends BasePage {

    private ElementActions actions;

    public StorePage() {
        super();  // calls BasePage constructor, which also sets driver
        this.actions = new ElementActions(); // ElementActions fetches driver internally
    }

    @FindBy(xpath = "//div[@class='card card-body']//p")
    private List<WebElement> noOfProducts;

    @FindBy(xpath = "//div[@class='card card-body']//div//button")
    private List<WebElement> listOfButtons;

    @FindBy(xpath="//div[@class='text-center mt-5']//h1")
    private WebElement pageName;

    @FindBy(xpath="//div[@class='text-center mt-5']//p")
    WebElement pageDescription;


    public void addProducts() {
        WebElement price1 = null;
        int size = listOfButtons.size();  //10
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (noOfProducts.get(i).getText().equals("Buffalo - Striploin") || noOfProducts.get(i).getText().equals("Pork - Chop, Frenched")) {
                actions.click(listOfButtons.get(i), "button",20);
            }
        }
    }


    public String getPageName(){
        return actions.getText(pageName,"Page Name",20);

    }

    public String getPageDescription(){
        return actions.getText(pageDescription,"Page Name",20);


    }
}
