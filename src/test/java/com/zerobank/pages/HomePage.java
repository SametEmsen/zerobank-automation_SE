package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
    @FindBy(id = "homeMenu")
    public WebElement homeMenu;

    @FindBy(id = "onlineBankingMenu")
    public WebElement onlineBankingMenu;

    @FindBy(id = "feedback")
    public WebElement feedback;

}
