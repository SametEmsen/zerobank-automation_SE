@regression
Feature:Navigating to specific accounts in Accounts Summary

  Background:
    Given The user navigates to signin page
    When The user enters valid credentials
    When The user navigates to "Online Banking" and "Account Summary"


  Scenario Outline: navigate to link
    When The user clicks on "<Title>" link on the Account Summary page
    Then The user should be able to see "Account Activity" page
    And The user verifies that dropdown is at "<Title>"
    Examples:
      | Title       |
      | Savings     |
      | Brokerage   |
      | Checking    |
      | Credit Card |
      | Loan        |