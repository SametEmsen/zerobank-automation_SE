@regression
Feature: Purchase Foreign Currency

  Background:
    Given The user navigates to signin page
    When The user enters valid credentials
    When The user navigates to "Online Banking" and "Pay Bills"
    Given The user accesses the "Purchase Foreign Currency" tab


  Scenario: Available currencies
    Then The user should be able to see following currencies are available
      | Australia (dollar)    |
      | Canada (dollar)       |
      | Switzerland (franc)   |
      | China (yuan)          |
      | Denmark (krone)       |
      | Eurozone (euro)       |
      | Great Britain (pound) |
      | Japan (yen)           |
      | Mexico (peso)         |
      | Norway (krone)        |
      | New Zealand (dollar)  |
      | Singapore (dollar)    |


  Scenario: Error message for not selecting currency
    When The user tries to calculate cost without selecting a currency
    Then The user should be able to see error message is displayed