@all

Feature: Authentication Check for API Endpoint

  Scenario: Verify API Endpoint Authentication
    Given the API endpoint from properties file
    When I send a POST request without any authentication parameters
    Then the response status code should be 200 OK
    And authentication should not be required


