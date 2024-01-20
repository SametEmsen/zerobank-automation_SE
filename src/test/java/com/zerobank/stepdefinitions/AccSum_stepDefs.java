package com.zerobank.stepdefinitions;

import com.zerobank.pages.AccSumPage;
import com.zerobank.utilities.BrowserUtils;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class AccSum_stepDefs {
    AccSumPage accSumPage = new AccSumPage();

    @Then("The user should see this followings")
    public void the_user_should_see_this_followings(List<String> list) {
        List<String> actualList = BrowserUtils.getElementsText(accSumPage.accounts);
        System.out.println("actualList.size() = " + actualList.size());
        Assert.assertEquals(list, actualList);
    }

    @Then("The user should see this followings in Credit Accounts table")
    public void the_user_should_see_this_followings_in_table(List<String> table) {
        List<String> actualList = BrowserUtils.getElementsText(accSumPage.creditAccsTitles);
        System.out.println("actualList.size() = " + actualList.size());
        Assert.assertEquals(table, actualList);
    }

    @Then("The user verifies that dropdown is at {string}")
    public void the_user_verifies_that_dropdown_is_at(String ddValueName) {
        accSumPage.verifyFirstSelected(ddValueName);
    }

    @Then("The user verifies that dropdown menu has these followings")
    public void the_user_verifies_that_dropdown_menu_has_these_followings(List<String> ddValues) {
        List<String> actualDropdownValues = BrowserUtils.getElementsText(accSumPage.getAllDropdownValues());
        Assert.assertEquals(ddValues, actualDropdownValues);
    }

    @Then("The user should see this followings in Show Transactions table")
    public void the_user_should_see_this_followings_in_show_transactions_table(List<String> transTitles) {
        List<String> actualList = BrowserUtils.getElementsText(accSumPage.transactionTitles);
        Assert.assertEquals(transTitles, actualList);
    }

    @Then("The user should see the successful payment message")
    public void the_user_should_see_the_successful_payment_message() {
        accSumPage.verifySuccesfullPayment();
    }

    @When("The user fills {string}, {string}, {string}")
    public void the_user_fills(String amount, String date, String description) {
        accSumPage.fillPayment(amount, date, description);
    }

    @Then("The user should see the alert message")
    public void the_user_should_see_the_alert_message() {
        accSumPage.verifyValidationMsjIsDisplayed();
    }

    @When("The user clicks on {string} link on the Account Summary page")
    public void the_user_clicks_on_link_on_the_account_summary_page(String linkName) {
        accSumPage.clickLink(linkName);
    }

    @Given("The user accesses the Find Transactions tab")
    public void the_user_accesses_the_find_transactions_tab() {
        accSumPage.navigateToFindTransTab();
    }

    @When("The user enters date range from {string} to {string}")
    public void the_user_enters_date_range_from_to(String firstDate, String lastDate) {
        accSumPage.setDateRange(firstDate, lastDate);
    }

    @Then("The user should only be able to see the results between the {string} to {string}")
    public void the_user_should_only_be_able_to_see_the_results_between_the_to(String firstDate, String lastDate) {
        accSumPage.verifyResultDatesInRange(firstDate, lastDate);
    }

    @Then("The user should be able to see result sorted by most recent date {string}")
    public void the_user_should_be_able_to_see_result_sorted_by_most_recent_date(String lastDate) {
        Assert.assertEquals(accSumPage.datesResult.get(0).getText(), lastDate);
    }


}
