package com.zerobank.pages;

import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FindTransPage extends BasePage {
    @FindBy(css = "[name=\"fromDate\"]")
    public WebElement fromDate;
    @FindBy(css = "[name=\"toDate\"]")
    public WebElement toDate;
    @FindBy(xpath = "//button[.='Find']")
    public WebElement findBtn;
    @FindBy(xpath = "//div[@id=\"filtered_transactions_for_account\"]//td[1]")
    public List<WebElement> datesResult;

    @FindBy(id = "aa_description")
    public WebElement descriptionBox;

    @FindBy(id = "aa_type")
    public WebElement typeDropdown;

    public void setDateRange(String firstDate, String lastDate) {
        fromDate.sendKeys(firstDate);
        toDate.sendKeys(lastDate);
        findBtn.click();
    }

    public void verifyResultDatesInRange(String firstDate, String lastDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fDate = sdf.parse(firstDate);
            Date lDate = sdf.parse(lastDate);

            //webelement olan stingli alır date objesine çevirerek aralıkta mı yada eşit mi diye kontol eder
            for (int i = 0; i < datesResult.size(); i++) {
                if (sdf.parse(datesResult.get(i).getText()).equals(fDate) || sdf.parse(datesResult.get(i).getText()).after(fDate)) {
                    Assert.assertTrue(true);
                } else if (sdf.parse(datesResult.get(i).getText()).equals(lDate) || sdf.parse(datesResult.get(i).getText()).before(lDate)) {
                    Assert.assertTrue(true);
                } else {
                    Assert.fail();
                }
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void verifyIsItMostRecentDate(String lastDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            // date için list oluşturur
            List<Date> dateList = new ArrayList<>();
            // webElement içinden tek tek StringDate text leri alıp date objesine çevirir ve dateList e atar
            for (String dateString : BrowserUtils.getElementsText(datesResult)) {
                Date date = sdf.parse(dateString);
                dateList.add(date);
            }
            //datelist içindekileri en yakından uzağa doğru sıralar
            dateList.sort(Collections.reverseOrder());

            //webElement den alınanlarla çevirilenlerin aynı olup olmadığını kontrol eder
            for (int i = 0; i < datesResult.size(); i++) {
                if (dateList.get(i).equals(sdf.parse(datesResult.get(i).getText()))) {
                    Assert.assertTrue(true);
                } else {
                    Assert.fail();
                }
            }

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method first : parameter converst ""Deposit" or "Withdrawal"
     * and adds wanted webElements into list
     * then check webElement for isEmpty or not
     * if webElement isn't Empty then assert true
     *
     * @param transType
     */
    public boolean findTransactions(String transType) {
        if (transType.equalsIgnoreCase("Deposit")) {
            List<WebElement> depositElements = Driver.get().findElements(By.xpath("//div[@id=\"filtered_transactions_for_account\"]//td[3]"));
            for (WebElement depositElement : depositElements) {
                if (!depositElement.getText().isEmpty()) {
                    System.out.println("depositElement.getText() = " + depositElement.getText());
                    return true;
                }
            }
        } else if (transType.equalsIgnoreCase("Withdrawal")) {
            List<WebElement> withdrawalElements = Driver.get().findElements(By.xpath("//div[@id=\"filtered_transactions_for_account\"]//td[4]"));

            for (WebElement withdrawalElement : withdrawalElements) {
                if (!withdrawalElement.getText().isEmpty()) {
                    System.out.println("withdrawalElement.getText() = " + withdrawalElement.getText());
                    return true;
                }
            }
        }
        return false;
    }


    public void verifyDescription(String description) {
        List<WebElement> descriptElements = Driver.get().findElements(By.xpath("//div[@id=\"filtered_transactions_for_account\"]//td[2]"));
        for (WebElement descriptElement : descriptElements) {
            if (descriptElement.getText().contains("ONLINE")) {
                Assert.assertTrue(true);
            } else {
                Assert.fail();
            }
        }
    }

    public void enterDescription(String description) {
        descriptionBox.sendKeys(description);
    }

    /**
     * Deposit or Withdrawal
     *
     * @param type
     */
    public void selectType(String type) {
        Select select = new Select(typeDropdown);
        select.selectByVisibleText(type);
    }


}
