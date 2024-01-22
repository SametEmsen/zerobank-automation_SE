package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    public BasePage(){
        PageFactory.initElements(Driver.get(),this);
    }

    @FindBy(id = "signin_button")
    private WebElement signin_button;



    public void clickSingInBtn(){
        signin_button.click();
    }
    public void verifyPageIsVisible(String pageName){
        Assert.assertTrue(Driver.get().findElement(By.xpath("//strong[contains(text(),'"+pageName+"')]")).isDisplayed());
    }
    public void verifySubmenuIsVisible(String submenu){
        Assert.assertTrue(Driver.get().findElement(By.xpath("//a[.='"+submenu+"']")).isDisplayed());
    }

    public void navigateToMenu(String menuName){
        Driver.get().findElement(By.xpath("//strong[contains(text(),'"+menuName+"')]")).click();
    }

    public void navigateToSubmenu(String submenuName){
        Driver.get().findElement(By.xpath("//*[contains(text(),'"+submenuName+"')]")).click();
    }
}
