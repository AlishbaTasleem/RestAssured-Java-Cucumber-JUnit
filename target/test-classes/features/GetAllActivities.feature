Feature: Retrieve Activities API

  Scenario: Retrieve all activities
    Given I have the activities API endpoint
    When I send a GET request to retrieve all activities
    Then I should receive a list of activities


