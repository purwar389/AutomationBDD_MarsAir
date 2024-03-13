@all
Feature: Link to Home Page


  Scenario: Clicking on "Book a ticket to the red planet now!"
    Given Mark is on the MarsAir website
    When he clicks on "Book a ticket to the red planet now!" link
    Then he should be redirected to the home page

  Scenario: Clicking on MarsAir logo From Home Page
    Given Mark is on the MarsAir website
    When he clicks on the MarsAir logo on the top left
    Then he should be redirected to the home page

  Scenario: Clicking on MarsAir logo from Search Result Page
    Given Mark is on the MarsAir website
    When Mark is on the Search Result Page
    And he clicks on the MarsAir logo on the top left
    Then he should be redirected to the home page

  Scenario: Clicking on MarsAir logo From Issue Report Page
    Given Mark is on the MarsAir website
    When Mark is on the Issue Report Page
    And he clicks on the MarsAir logo on the top left
    Then he should be redirected to the home page

  Scenario: Clicking on Back button from Search Result Page
    Given Mark is on the MarsAir website
    When Mark is on the Search Result Page
    And he clicks on the Back button at the bottom
    Then he should be redirected to the home page
