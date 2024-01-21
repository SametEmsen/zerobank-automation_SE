Feature: Find Transactions in Account Activity

  Background:
    Given The user navigates to signin page
    When The user enters valid credentials
    When The user navigates to "Online Banking" and "Account Activity"
    Given The user accesses the "Find Transactions" tab


  Scenario Outline: Search date range test
    When The user enters date range from "<From Date>" to "<To Date>"
    Then The user should only be able to see the results between the "<From Date>" to "<To Date>"
    And The user should be able to see result sorted by most recent date "<To Date>"
    Examples:
      | From Date  | To Date    |
      | 2012-09-01 | 2012-09-06 |


  Scenario: Search description
    When The user enters description "ONLINE"
    And The user clicks Find button
    Then The user should be able to see results table only show descriptions containing "ONLINE"



  Scenario: Type 1 test
    When The user clicks Find button
    Then The user should be able to see results table show at least one result under "Deposit"
    Then The user should be able to see results table show at least one result under "Withdrawal"


  Scenario: Type-2 test
    When The user selects type "Deposit" and clicks on Find
    Then The user should be able to see results table show at least one result under "Deposit"
    But The user should be able to see results table should show no result under "Withdrawal"


  Scenario: Type-3
    When The user selects type "Withdrawal" and clicks on Find
    Then The user should be able to see results table show at least one result under "Withdrawal"
    But The user should be able to see results table should show no result under "Deposit"
