@all
Feature: Basic Search flow

  Scenario: Validate search form fields
    Given Mark is on the MarsAir website
    When He is on Search Home Page
    Then There should be title ‘Welcome to MarsAir!’
    And There should be subtitle ‘Book a ticket to the red planet now!’
    And There should be ‘Departure’ dropdown field on a search form
    And There should be ‘Return’ dropdown field on a search form
    And There should be ‘Promotional Code’ editable field on a search form
    And Flights leave every six months, in July and December, both ways
    And There should be ‘Search’ button on a search form

  Scenario: Search for available flights for the next two years
    Given Mark is on the MarsAir website
    When He is on Search Home Page
    Then Verify that trips for the next two years should be searchable

  Scenario: Search for available flights with seats available
    Given Mark is on the MarsAir website
    When He searches for a flight with departure in July and return in December with available seats
    And If there are seats, display 'Seats available! Call 0800 MARSAIR to book!'

  Scenario: Search for available flights with no seats available
    Given Mark is on the MarsAir website
    When He searches for a fully booked flight with departure in July and return in December
    And If there are no seats, display 'Sorry, there are no more seats available.'

  Scenario Outline: Search for available flights with different inputs
    Given Mark is on the MarsAir website
    When He searches for a flight with a valid departure in "<Departure>" and return in "<Return>"
    And Verify the result type "<ExpectedResultMessage>" for each inputs
    Examples:
      | Departure                        | Return                           | ExpectedResultMessage                                       |
      | July                             | December (two years from now)    | Seats available! Call 0800 MARSAIR to book!          |
      | July                             | December                         | Unfortunately, this schedule is not possible. Please try again.          |
      | December (two years from now)    | July                             | Unfortunately, this schedule is not possible. Please try again.          |
      | July                             | July                             | Unfortunately, this schedule is not possible. Please try again.          |
      | December (next year)             | December (two years from now)    | Sorry, there are no more seats available. |
      | July                             | July (two years from now)        | Sorry, there are no more seats available.            |

  Scenario: Validate Departure Dropdown Field Values Based on Current Month
    Given Mark is on the MarsAir website
    When He validates 'Departure' dropdown field
    Then The 'Departure' dropdown field should not contain values for months before the current month

  Scenario: Validate Return Dropdown Field Values Based on Current Month
    Given Mark is on the MarsAir website
    When He validates 'Return' dropdown field
    Then The 'Return' dropdown field should not contain values for months before the current month

