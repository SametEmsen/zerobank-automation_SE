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

    @FindBy(css = "[name=\"fromDate\"]")
    public WebElement fromDate;
    @FindBy(css = "[name=\"toDate\"]")
    public WebElement toDate;
    @FindBy(xpath = "//button[.='Find']")
    public WebElement findBtn;
    @FindBy(xpath = "//div[@id=\"filtered_transactions_for_account\"]//td[1]")
    public List<WebElement> datesResult;

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

    public void navigateToFindTransTab() {
        findTransTab.click();
    }

    public void setDateRange(String firstDate, String lastDate) {
        fromDate.sendKeys(firstDate);
        toDate.sendKeys(lastDate);
        findBtn.click();
    }

    public void verifyResultDatesInRange(String firstDate, String lastDate) {
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date fDate=sdf.parse(firstDate);
            Date lDate=sdf.parse(lastDate);

            //webelement olan stingli alır date objesine çevirerek aralıkta mı yada eşit mi diye kontol eder
            for (int i = 0; i < datesResult.size(); i++) {
                if (sdf.parse(datesResult.get(i).getText()).equals(fDate) || sdf.parse(datesResult.get(i).getText()).after(fDate)){
                    Assert.assertTrue(true);
                } else if (sdf.parse(datesResult.get(i).getText()).equals(lDate) || sdf.parse(datesResult.get(i).getText()).before(lDate)) {
                    Assert.assertTrue(true);
                }else{
                    Assert.fail();
                }
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void verifyIsItMostRecentDate(String lastDate){
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

            // date için list oluşturur
            List<Date> dateList = new ArrayList<>();
            // webElement içinden tek tek StringDate text leri alıp date objesine çevirir ve dateList e atar
            for (String dateString : BrowserUtils.getElementsText(datesResult)) {
                Date date=sdf.parse(dateString);
                dateList.add(date);
            }
            //datelist içindekileri en yakından uzağa doğru sıralar
            dateList.sort(Collections.reverseOrder());

            //webElement den alınanlarla çevirilenlerin aynı olup olmadığını kontrol eder
            for (int i = 0; i < datesResult.size(); i++) {
                if (dateList.get(i).equals(sdf.parse(datesResult.get(i).getText()))){
                    Assert.assertTrue(true);
                }else{
                    Assert.fail();
                }
            }

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
