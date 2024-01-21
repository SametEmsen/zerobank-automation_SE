package com.zerobank.pages;

import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AccSumPage extends BasePage {
    @FindBy(className = "board-header")
    public List<WebElement> accounts;
    @FindBy(xpath = "(//table[@class=\"table\"])[3]//th")
    public List<WebElement> creditAccsTitles;
    @FindBy(id = "aa_accountId")
    public WebElement accountDropdown;
    @FindBy(xpath = "//div[@id=\"all_transactions_for_account\"]//th")
    public List<WebElement> transactionTitles;

    @FindBy(id = "sp_amount")
    public WebElement paymentAmountBox;
    @FindBy(id = "sp_date")
    public WebElement paymentDateBox;
    @FindBy(id = "alert_content")
    public WebElement successfulPaymentMessage;

    @FindBy(xpath = "//a[.='Find Transactions']")
    public WebElement findTransTab;



    public void verifyFirstSelected(String ddValue) {
        Select select = new Select(accountDropdown);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), ddValue);
    }

    public List<WebElement> getAllDropdownValues() {
        Select select = new Select(accountDropdown);
        return select.getOptions();
    }

    public void fillPayment(String dollar, String date, String description) {
        Actions actions = new Actions(Driver.get());
        actions.click(paymentAmountBox).sendKeys(dollar + Keys.TAB)
                .sendKeys(date + Keys.TAB)
                .sendKeys(description + Keys.TAB + Keys.ENTER).perform();
    }

    public void verifySuccesfullPayment() {
        Assert.assertTrue(successfulPaymentMessage.isDisplayed());
    }

    public boolean verifyValidationMsjIsDisplayed() {
        if (paymentAmountBox.getAttribute("validationMessage").equals("Please fill out this field.")) {
            return true;
        } else if (paymentDateBox.getAttribute("validationMessage").equals("Please fill out this field.")) {
            return true;
        } else return false;
    }

    public void clickLink(String linkName) {
        Driver.get().findElement(By.xpath("(//a[.='" + linkName + "'])[1]")).click();
    }

    public void navigateTuSubmenuTabs(String submenuTab){
        Driver.get().findElement(By.xpath("//a[.='"+submenuTab+"']")).click();
    }


}
