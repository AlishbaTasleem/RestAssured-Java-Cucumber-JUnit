Feature: Create a new activity

  Scenario: Create a new activity successfully
    Given I have a valid activity payload
    When I send a POST request to create a new activity
    Then the response status code should be 201
    And the response body should contain the created activity details
