Feature: Find Transactions in Account Activity

  Background:
    Given The user navigates to signin page
    When The user enters valid credentials
    When The user navigates to "Online Banking" and "Account Activity"
    Given The user accesses the Find Transactions tab

  @wip
  Scenario Outline: Search date range test
    When The user enters date range from "<From Date>" to "<To Date>"
    Then The user should only be able to see the results between the "<From Date>" to "<To Date>"
    And The user should be able to see result sorted by most recent date "<To Date>"
    Examples:
      | From Date  | To Date    |
      | 2012-09-01 | 2012-09-06 |

