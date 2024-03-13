@all
Feature: Promotional Codes

  Scenario Outline: Use Valid Promotional Code
    Given Mark is on the MarsAir website
    When he enters the given promotional code "<promoCode>"
    Then The promotional code should be in the format XX9-XXX-999
    And The first digit should indicate the discount percentage as "<expectedDiscounts>"
    And The final digit is a check digit and equal to the sum of all other digits
    When He searches for a flight with departure in July and return in December with available seats
    Then The search result should be displayed as Promotional code "<promoCode>" used "<expectedDiscounts>" discount! message
    Examples:
      | promoCode      | expectedDiscounts |
      | AF3-FJK-418    | 30%                |
      | JJ5-OPQ-320    | 50%                |
      | JJ0-OPQ-325    | 00%                |
      | AF1-FEK-236    | 10%               |
      | AF2-FJK-169    | 20%               |
      | AF3-FJK-418    | 30%               |
      | JJ4-OPQ-329    | 40%               |
      | JJ5-OPQ-016    | 50%               |
      | JJ9-OPQ-009    | 90%               |

  Scenario Outline: Use Invalid Promotional Code
    Given Mark is on the MarsAir website
    When he enters the given promotional code "<promoCode>"
    When He searches for a flight with departure in July and return in December with available seats
    Then The search result should be displayed as "Sorry code "<promoCode>" is not valid"
    Examples:
      | promoCode         |
      | invalid           |
      | ABD-EDF-123       |
      | ABC-DEF-S22       |
      | ABC-DEF-S001123   |
      | ABC-123-S555363d  |
      | INV-BUT-S55VALID  |
      | ABC-DEF-GHI       |
      | ABC-456-789       |
      | 1AB-234-567       |
      | ds1-323-g23       |

