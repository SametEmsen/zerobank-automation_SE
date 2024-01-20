@regression
Feature: Login Functionalty

  Background:
    Given The user navigates to signin page

  Scenario: Positive login test
    When The user enters valid credentials
    And The user navigates to "Online Banking" and "Account Summary"
    Then The user should be able to see "Account Summary" page


  Scenario Outline: Negative login test
    When The user enters credentials as "<userName>" and "<passWord>"
    Then The user should see the "<message>"
    Examples:
      | userName     | passWord     | message                          |
      | userName3562 | passWord3562 | Login and/or password are wrong. |
      | userName     |              | Login and/or password are wrong. |
      |              | passWord     | Login and/or password are wrong. |