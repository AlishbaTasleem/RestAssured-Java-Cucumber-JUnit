package com.example.tests.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.example.framework.models.ActivityModel;
import org.example.framework.services.ActivitiesService;
import org.junit.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class StepDefinitions {

    private int activityId;
    private ActivityModel activity;
    private List<Map<String, Object>> activities;
    private Response response;

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
        System.out.println("Response Body: " + responseBody);
    }

    @When("I send a POST request to create a new activity")
    public void iSendPOSTRequestToCreateNewActivity() {
        response = ActivitiesService.createActivity(activity);
    }

    @When("I send a DELETE request to delete the activity")
    public void iSendDELETERequestToDeleteActivity() {
        System.out.println("activity id"+ activityId);
        response = ActivitiesService.deleteActivityById(activityId);

        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);
        System.out.println(response.getStatusCode());
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
        // Additional assertions on the activity details if needed
    }

    @Then("the response body should contain the {string} error message")
    public void theResponseBodyShouldContainErrorMessage(String errorMessage) {
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains(errorMessage));
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
}
