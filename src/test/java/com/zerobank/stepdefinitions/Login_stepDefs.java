package com.zerobank.stepdefinitions;

import com.zerobank.pages.AccSumPage;
import com.zerobank.pages.HomePage;
import com.zerobank.pages.OnlineBankPage;
import com.zerobank.pages.SigninPage;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Login_stepDefs {
    SigninPage signinPage=new SigninPage();
    OnlineBankPage onlineBankPage=new OnlineBankPage();
    AccSumPage accSumPage=new AccSumPage();
    @Given("The user navigates to signin page")
    public void the_user_navigates_to_signin_page() {
        Driver.get().get(ConfigurationReader.get("url"));
        signinPage.clickSingInBtn();
    }
    @When("The user enters valid credentials")
    public void the_user_enters_valid_credentials() {
        signinPage.signIn();
    }
    @When("The user navigates to {string} and {string}")
    public void the_user_navigates_to(String menuName, String submenuName) {
        onlineBankPage.navigateToMenu(menuName);
        onlineBankPage.navigateToSubmenu(submenuName);
    }
    @Then("The user should be able to see {string} page")
    public void the_user_should_be_able_to_see_account_summary_page(String pageName) {
        accSumPage.verifySubmenuIsVisible(pageName);
    }
    @When("The user enters credentials as {string} and {string}")
    public void the_user_enters_credentials_as_and(String userName, String passWord) {
        signinPage.signIn(userName, passWord);
    }
    @Then("The user should see the {string}")
    public void the_user_should_see_the(String message) {
        signinPage.verifyAlertMessageDisplay();
    }
}
