package com.zerobank.stepdefinitions;

import com.zerobank.pages.AccSumPage;
import com.zerobank.utilities.BrowserUtils;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class AccSum_stepDefs {
    AccSumPage accSumPage=new AccSumPage();
    @Then("The user should see this followings")
    public void the_user_should_see_this_followings(List<String> list) {
        List<String> actualList = BrowserUtils.getElementsText(accSumPage.accounts);
        System.out.println("actualList.size() = " + actualList.size());
        Assert.assertEquals(list,actualList);
    }
    @Then("The user should see this followings in Credit Accounts table")
    public void the_user_should_see_this_followings_in_table(List<String> table) {
        List<String> actualList = BrowserUtils.getElementsText(accSumPage.creditAccsTitles);
        System.out.println("actualList.size() = " + actualList.size());
        Assert.assertEquals(table,actualList);
    }

    @Then("The user verifies that dropdown is at {string}")
    public void the_user_verifies_that_dropdown_is_at(String ddValueName) {
        accSumPage.verifyFirstSelected(ddValueName);
    }
    @Then("The user verifies that dropdown menu has these followings")
    public void the_user_verifies_that_dropdown_menu_has_these_followings(List<String> ddValues) {
        List<String> actualDropdownValues = BrowserUtils.getElementsText(accSumPage.getAllDropdownValues());
        Assert.assertEquals(ddValues,actualDropdownValues);
    }
    @Then("The user should see this followings in Show Transactions table")
    public void the_user_should_see_this_followings_in_show_transactions_table(List<String> transTitles) {
        List<String> actualList = BrowserUtils.getElementsText(accSumPage.transactionTitles);
        Assert.assertEquals(transTitles,actualList);
    }
    @Then("The user should see the successful payment message")
    public void the_user_should_see_the_successful_payment_message() {
        accSumPage.verifySuccesfullPayment();
    }

    @When("The user fills {string}, {string}, {string}")
    public void the_user_fills(String amount, String date, String description) {
        accSumPage.fillPayment(amount,date,description);
    }

    @Then("The user should see the alert message")
    public void the_user_should_see_the_alert_message() {
        accSumPage.verifyValidationMsjIsDisplayed();
    }

}
