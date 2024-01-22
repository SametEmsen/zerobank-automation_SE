package com.zerobank.stepdefinitions;

import com.zerobank.pages.AccSumPage;
import com.zerobank.pages.FindTransPage;
import com.zerobank.pages.PayBillsPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AccSum_stepDefs {
    AccSumPage accSumPage = new AccSumPage();
    FindTransPage findTransPage = new FindTransPage();

    PayBillsPage payBillsPage = new PayBillsPage();

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

    @Given("The user accesses the {string} tab")
    public void the_user_accesses_the_tab(String submenuTab) {
        accSumPage.navigateTuSubmenuTabs(submenuTab);
    }

    @When("The user enters date range from {string} to {string}")
    public void the_user_enters_date_range_from_to(String firstDate, String lastDate) {
        findTransPage.setDateRange(firstDate, lastDate);
    }

    @Then("The user should only be able to see the results between the {string} to {string}")
    public void the_user_should_only_be_able_to_see_the_results_between_the_to(String firstDate, String lastDate) {
        findTransPage.verifyResultDatesInRange(firstDate, lastDate);
    }

    @Then("The user should be able to see result sorted by most recent date {string}")
    public void the_user_should_be_able_to_see_result_sorted_by_most_recent_date(String lastDate) {
        Assert.assertEquals(findTransPage.datesResult.get(0).getText(), lastDate);
    }

    @When("The user clicks Find button")
    public void the_user_clicks_find_button() {
        findTransPage.findBtn.click();
    }

    @Then("The user should be able to see results table show at least one result under {string}")
    public void the_user_should_be_able_to_see_results_table_show_at_least_one_result_under(String transactionType) {
        Assert.assertTrue(findTransPage.findTransactions(transactionType));
    }

    @When("The user enters description {string}")
    public void the_user_enters_description(String description) {
        findTransPage.enterDescription(description);
    }

    @Then("The user should be able to see results table only show descriptions containing {string}")
    public void the_user_should_be_able_to_see_results_table_only_show_descriptions_containing(String description) {
        findTransPage.verifyDescription(description);
    }

    @When("The user selects type {string} and clicks on Find")
    public void the_user_selects_type_and_clicks_on_find(String type) {
        findTransPage.selectType(type);
        findTransPage.findBtn.click();
    }

    @Then("The user should be able to see results table should show no result under {string}")
    public void the_user_should_be_able_to_see_results_table_should_show_no_result_under(String description) {
        Assert.assertFalse(findTransPage.findTransactions(description));
    }

    @When("The user creates new payee using following information")
    public void the_user_creates_new_payee_using_following_information(Map<String, String> info) {
        payBillsPage.addNewPayee(info.get("Payee Name"), info.get("Payee Address"), info.get("Account"), info.get("Payee details"));
    }

    @Then("The user verifies that message should be displayed")
    public void the_user_verifies_that_message_should_be_displayed(List<String> message) {
        String alertText = BrowserUtils.getElementText(payBillsPage.alertContentMessage);
        Assert.assertEquals(message.get(0), alertText);
    }

    @Then("The user should be able to see following currencies are available")
    public void the_user_should_be_able_to_see_following_currencies_are_available(List<String> currencies) {
/**
 * it works
 */
//        for (int i = 0; i < currencies.size(); i++) {
//            Assert.assertTrue(BrowserUtils.getElementsText(payBillsPage.getCurrencies()).contains(currencies.get(i)));
//            }

        List<String> elementsText = BrowserUtils.getElementsText(payBillsPage.getCurrencies());
        System.out.println("elementsText = " + elementsText);
        System.out.println("currencies = " + currencies);
/**
 * it doesn't works
 */
//        if (elementsText.containsAll(currencies)){
//            Assert.assertTrue(true);
//        }else{
//            Assert.fail();
//        }
        /**
         * it works
         */
        Assert.assertTrue(elementsText.containsAll(currencies));

/**
 * it works
 */
//        for (String currency : currencies) {
//            Assert.assertTrue(elementsText.contains(currency));
//        }


    }

    @When("The user tries to calculate cost without selecting a currency")
    public void the_user_tries_to_calculate_cost_without_selecting_a_currency() {
        payBillsPage.calculateCostsBtn.click();
    }

    /**
     * this method needs to comment out one Driver class method (Driver.get().manage().window.....)
     */
    @Then("The user should be able to see error message is displayed")
    public void the_user_should_be_able_to_see_error_message_is_displayed() {
        //payBillsPage.verifyAlertIsDisplayed();
        Assert.assertTrue(payBillsPage.isAlertPresent());

    }
}


