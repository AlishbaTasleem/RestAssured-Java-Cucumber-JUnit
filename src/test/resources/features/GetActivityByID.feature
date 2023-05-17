Feature: Retrieve activity by ID

  Scenario: Retrieve activity with a specific ID
    Given the activity ID is "10"
    When I send a GET request to retrieve the activity
    Then the response status code should be 200
    And the response body should contain the activity details


  Scenario: Retrieve activity with an invalid ID
    Given the activity ID is "9999"
    When I send a GET request to retrieve the activity
    Then the response status code should be 404
    And the response body should contain the "Not Found" error message