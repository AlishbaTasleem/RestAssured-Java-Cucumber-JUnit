Feature: Delete Activity by ID

  Scenario: Delete an activity by ID
    Given the activity ID is "10"
    When I send a DELETE request to delete the activity
    Then the response status code should be 200