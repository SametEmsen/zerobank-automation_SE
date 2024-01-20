@regression
Feature: Account option features

  Background:
    Given The user navigates to signin page
    When The user enters valid credentials


  Scenario: Account summary page test
    When The user navigates to "Online Banking" and "Account Summary"
    Then The user should be able to see "Account Summary" page
    Then The user should see this followings
      | Cash Accounts       |
      | Investment Accounts |
      | Credit Accounts     |
      | Loan Accounts       |
    And The user should see this followings in Credit Accounts table
      | Account     |
      | Credit Card |
      | Balance     |


  Scenario: Account Activity page test
    When The user navigates to "Online Banking" and "Account Activity"
    Then The user should be able to see "Account Activity" page
    And The user verifies that dropdown is at "Savings"
    And The user verifies that dropdown menu has these followings
      | Savings     |
      | Checking    |
      | Savings     |
      | Loan        |
      | Credit Card |
      | Brokerage   |
    And The user should see this followings in Show Transactions table
      | Date        |
      | Description |
      | Deposit     |
      | Withdrawal  |


  Scenario Outline: Pay Bills page positive test
    When The user navigates to "Online Banking" and "Pay Bills"
    Then The user should be able to see "Pay Bills" page
    When The user fills "<Amount>", "<Date>", "<Description>"
    Then The user should see the successful payment message
    Examples:
      | Amount | Date       | Description |
      | 500    | 11-12-2012 | tax         |

  @wip
  Scenario Outline: Pay Bills page negative test
    When The user navigates to "Online Banking" and "Pay Bills"
    Then The user should be able to see "Pay Bills" page
    When The user fills "<Amount>", "<Date>", "<Description>"
    Then The user should see the alert message
    Examples:
      | Amount | Date | Description |
      |        | Date | Description |
      | Amount |      | Description |

