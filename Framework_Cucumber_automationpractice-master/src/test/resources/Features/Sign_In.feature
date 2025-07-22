#Author: Suraj Bobade
#Scenario: To login QA onprem.

Feature: QA Onprem Application

  Scenario Outline: QA Onprem Login
    Given User opened browser
    And User navigates to the application url
    And User add valid credentials to login the application
    When User enters the valid username and password to click on sign in button
    Then User navigated successfully to the welcome screen

#    Examples: 
#      | username | password |
#     | surajb   | Suraj@1122 |