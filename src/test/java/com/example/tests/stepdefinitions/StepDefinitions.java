package com.example.tests.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.framework.models.ActivityModel;
import org.example.framework.models.AuthorsModel;
import org.example.framework.models.BooksModel;
import org.example.framework.services.ActivitiesService;
import org.example.framework.services.AuthorsService;
import org.example.framework.services.BooksService;
import org.junit.Assert;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class StepDefinitions {

    private int activityId;
    private ActivityModel activity;
    private List<Map<String, Object>> activities;
    private Response response;

    private List<BooksModel> books;
    private BooksModel selectedBook;
    private List<AuthorsModel> authors;
    private AuthorsModel selectedAuthor;



    @Given("the activity ID is {string}")
    public void theActivityIDIs(String id) {
        activityId = Integer.parseInt(id);
    }

    @Given("I have the activities API endpoint")
    public void iHaveActivitiesAPIEndpoint() {

        // No action needed as the API endpoint is already defined
    }

    @Given("I have a valid activity payload")
    public void iHaveValidActivityPayload() {
        // Create the activity payload
        activity = new ActivityModel(0, "string", "2023-05-16T19:08:52.097", true);
    }

    @When("I send a GET request to retrieve the activity")
    public void iSendGETRequestToRetrieveActivity() {
        response = ActivitiesService.getActivityById(activityId);
    }

    @When("I send a GET request to retrieve all activities")
    public void iSendGETRequestToRetrieveAllActivities() {
        response = ActivitiesService.getAllActivities();
        activities = response.jsonPath().getList("$");

        String responseBody = response.getBody().asString();
        System.out.println("Response: " + responseBody);
    }

    @When("I send a POST request to create a new activity")
    public void iSendPOSTRequestToCreateNewActivity() {
        response = ActivitiesService.createActivity(activity);
    }

    @When("I send a DELETE request to delete the activity")
    public void iSendDELETERequestToDeleteActivity() {
        response = ActivitiesService.deleteActivityById(activityId);

        String responseBody = response.getBody().asString();
        System.out.println("Response: " + responseBody);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(expectedStatusCode, actualStatusCode);

        // Handle different status codes using a switch statement
        switch (actualStatusCode) {
            case 200:
                Assert.assertEquals(200, actualStatusCode);
                // Additional assertions or actions for status code 200
                break;
            case 404:
                Assert.assertEquals(404, actualStatusCode);
                // Additional assertions or actions for status code 404
                break;
            case 400:
                Assert.assertEquals(400, actualStatusCode);
                break;
            // Add more cases for other status codes if needed
            default:
                // Handle unexpected status codes
                Assert.fail("Unexpected status code: " + actualStatusCode);
        }
    }

    @Then("the response body should contain the activity details")
    public void theResponseBodyShouldContainActivityDetails() {
        ActivityModel activity = response.as(ActivityModel.class);
        Assert.assertNotNull(activity);

        String responseBody = response.getBody().asString();
        System.out.println("Response: " + responseBody);
        // Additional assertions on the activity details if needed
    }

    @Then("the response body should contain the {string} error message")
    public void theResponseBodyShouldContainErrorMessage(String errorMessage) {
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains(errorMessage));
        System.out.println("Response: "+responseBody);
    }

    @Then("I should receive a list of activities")
    public void iShouldReceiveListOfActivities() {
        Assert.assertNotNull(activities);
        Assert.assertFalse(activities.isEmpty());

        int statusCode = response.getStatusCode();
        Assert.assertEquals(200, statusCode);
    }

    @Then("the response body should contain the created activity details")
    public void theResponseBodyShouldContainCreatedActivityDetails() {

        ActivityModel createdActivity = response.as(ActivityModel.class);
        Assert.assertEquals(activity.getId(), createdActivity.getId());
        Assert.assertEquals(activity.getTitle(), createdActivity.getTitle());
        Assert.assertEquals(activity.getDueDate(), createdActivity.getDueDate());
        Assert.assertEquals(activity.isCompleted(), createdActivity.isCompleted());
    }

    @When("I send a PUT request to update the activity")
    public void iSendPUTRequestToUpdateActivity() {
        response = ActivitiesService.updateActivityById(activityId, activity);
    }

    @When("I send a POST request with an empty duedate to create a new activity")
    public void iSendPOSTRequestWithEmptyIDToCreateNewActivity() {
        activity.setDueDate(""); // Set the ID to empty or 0
        response = ActivitiesService.createActivity(activity);
    }

    @Given("I have the books API endpoint")
    public void iHaveBooksAPIEndpoint() {
        // No action needed as the API endpoint is already defined
    }

    @When("I send a GET request to retrieve all books")
    public void iSendGETRequestToRetrieveAllBooks() {
        response = BooksService.getAllBooks();
        books = response.jsonPath().getList(".", BooksModel.class);

        String responseBody = response.getBody().asString();
        System.out.println("Response: " + responseBody);
    }

    @Then("I should receive a list of books")
    public void iShouldReceiveListOfBooks() {
        Assert.assertNotNull(books);
        Assert.assertFalse(books.isEmpty());

        int statusCode = response.getStatusCode();
        Assert.assertEquals(200, statusCode);
    }

    @Then("I retrieve the first book ID and store it")
    public void iRetrieveFirstBookIDAndStoreIt() {
        selectedBook = books.get(0);

        // Assert that the book ID was retrieved successfully
        Assert.assertNotNull(selectedBook);

        System.out.println("BOOK ID: " + selectedBook.getId());

    }

    @When("I send a GET request to retrieve the author by book ID")
    public void iSendGETRequestToRetrieveAuthorByBookID() {
        AuthorsService authorsService = new AuthorsService(selectedBook);
        response = authorsService.getAuthorByBookID();
    }

    @Then("I should receive the author details")
    public void iShouldReceiveAuthorDetails() {
        Assert.assertNotNull(response);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(200, statusCode);

        // Handle the response body according to your requirements
        String responseBody = response.getBody().asString();
        System.out.println("Response: " + responseBody);

        // Parse the response body if it contains author details
        // and assign it to the selectedAuthor variable
        // selectedAuthor = ...
    }


}
