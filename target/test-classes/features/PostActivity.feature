Feature: Create a new activity

  Scenario: Create a new activity successfully
    Given I have a valid activity payload
    When I send a POST request to create a new activity
    Then the response status code should be 200
    And the response body should contain the created activity details


  Scenario: Create a new activity with empty duedate
    Given I have the activities API endpoint
    And I have a valid activity payload
    When I send a POST request with an empty duedate to create a new activity
    Then the response status code should be 400
