Feature: Update a new activity

  Scenario: Update a new activity successfully
    Given I have a valid activity payload
    When I send a PUT request to update the activity
    Then the response status code should be 200
    And the response body should contain the created activity details