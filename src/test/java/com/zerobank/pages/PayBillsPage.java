package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PayBillsPage extends BasePage {
    @FindBy(id = "np_new_payee_name")
    public WebElement payeeName;
    @FindBy(id = "np_new_payee_address")
    public WebElement payeeAdress;
    @FindBy(id = "np_new_payee_account")
    public WebElement payeeAccount;
    @FindBy(id = "np_new_payee_details")
    public WebElement payeeDetails;
    @FindBy(id = "add_new_payee")
    public WebElement addBtn;
    @FindBy(id = "alert_content")
    public WebElement alertContentMessage;
    @FindBy(id = "pc_currency")
    public WebElement currencyDropdown;

    @FindBy(id = "pc_calculate_costs")
    public WebElement calculateCostsBtn;

    public void addNewPayee(String name, String adress, String account, String details) {
        payeeName.sendKeys(name);
        payeeAdress.sendKeys(adress);
        payeeAccount.sendKeys(account);
        payeeDetails.sendKeys(details);
        addBtn.click();
    }

    public List<WebElement> getCurrencies() {
        Select select = new Select(currencyDropdown);
        return select.getOptions();
    }
    public void verifyAlertIsDisplayed(){
        WebDriverWait wait=new WebDriverWait(Driver.get(),3);
        Alert alert= wait.until(ExpectedConditions.alertIsPresent());
        if (alert != null){
            Assert.assertTrue(true);
        }else{
            Assert.fail();
        }
    }
}
