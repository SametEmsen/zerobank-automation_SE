package com.zerobank.pages;

import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SigninPage extends BasePage{
    @FindBy(id = "user_login")
    private WebElement l_user_loginBox;
    @FindBy(id = "user_password")
    private WebElement l_user_passwordBox;

    @FindBy(css = "[value=\"Sign in\"]")
    private WebElement l_signinBtn;

    @FindBy(css = "[class=\"alert alert-error\"]")
    private WebElement alertMessage;

    public void signIn(){
        l_user_loginBox.sendKeys(ConfigurationReader.get("userName"));
        l_user_passwordBox.sendKeys(ConfigurationReader.get("passWord"));
        l_signinBtn.click();
        Driver.get().navigate().back();
    }

    public void signIn(String userName, String passWord){
        l_user_loginBox.sendKeys(userName);
        l_user_passwordBox.sendKeys(passWord);
        l_signinBtn.click();
        //Driver.get().navigate().back();    // comment-out for negative login test
    }
    public void verifyAlertMessageDisplay(){
        Assert.assertTrue(alertMessage.isDisplayed());
    }
}
