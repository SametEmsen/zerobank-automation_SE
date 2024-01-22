@regression
Feature: Add new payee under pay bills

  Background:
    Given The user navigates to signin page
    When The user enters valid credentials
    When The user navigates to "Online Banking" and "Pay Bills"
    Given The user accesses the "Add New Payee" tab


  Scenario: Add a new payee
    When The user creates new payee using following information
      | Payee Name    | The Law Offices of Hyde, Price & Scharks |
      | Payee Address | 100 Same st, Anytown, USA, 10001         |
      | Account       | Checking                                 |
      | Payee details | XYZ account                              |
    Then The user verifies that message should be displayed
      | The new payee The Law Offices of Hyde, Price & Scharks was successfully created. |