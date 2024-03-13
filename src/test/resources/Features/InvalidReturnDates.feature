@all
Feature: Invalid Return Dates

  Scenario Outline: Searching with a return date less than 1 year from departure
    Given Mark is on the MarsAir website
    When return date "<Return>" is less than 1 year from the departure "<Departure>"
    Then Verify the result type "<ExpectedResultMessage>" for each inputs
    Examples:
      | Departure                        | Return                        | ExpectedResultMessage                |
      | July                             | July                          | Unfortunately, this schedule is not possible. Please try again. |
      | July                             | December                      | Unfortunately, this schedule is not possible. Please try again. |
      | July (two years from now)        | December (two years from now) | Unfortunately, this schedule is not possible. Please try again. |
      | July (next year)                 | July (next year)              | Unfortunately, this schedule is not possible. Please try again. |
      | December (next year)             | July (two years from now)     | Unfortunately, this schedule is not possible. Please try again. |
      | July (next year)                 | December (next year)          | Unfortunately, this schedule is not possible. Please try again. |


  Scenario Outline: Searching with a return date more than 1 year from departure
    Given Mark is on the MarsAir website
    When return date "<Return>" is more than 1 year from the departure "<Departure>"
    Then Verify the result type "<ExpectedResultMessage>" for each inputs
    Examples:
      | Departure               | Return                        | ExpectedResultMessage           |
      | July                    | July (two years from now)     | Sorry, there are no more seats available. |
      | July                    | December (two years from now) | Seats available! Call now on 0800 MARSAIR to book! |
      | July (next year)        | July (two years from now)     | Sorry, there are no more seats available. |
      | July (next year)        | December (two years from now) | Sorry, there are no more seats available. |
