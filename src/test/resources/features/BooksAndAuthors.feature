Feature: Retrieve Books and author of first book

  Scenario: Retrieve first Book's ID and get its Author
    Given I have the books API endpoint
    When I send a GET request to retrieve all books
    Then I should receive a list of books
    Then I retrieve the first book ID and store it
    Then I send a GET request to retrieve the author by book ID
    Then I should receive the author details
