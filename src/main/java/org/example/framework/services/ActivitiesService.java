package org.example.framework.services;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.example.framework.api.APIClient;
import org.example.framework.configuration.Configuration;
import org.example.framework.models.ActivityModel;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.example.framework.configuration.Configuration.BASE_URL;

public class ActivitiesService {

    private static RequestSpecification requestSpec;

    static {
        // Build the request specification with common properties
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(BASE_URL);
        requestSpecBuilder.log(LogDetail.URI); // Enable logging of the URI
        requestSpecBuilder.log(LogDetail.BODY); // Enable logging of the request body
        requestSpecBuilder.addFilter(new RequestLoggingFilter(LogDetail.METHOD));


        requestSpec = requestSpecBuilder.build();


    }

    public static Response getAllActivities() {
        System.out.println("\n GET ALL ACTIVITIES");
        String endpoint = Configuration.getActivitiesEndpoint();
        Response response = APIClient.get(endpoint, requestSpec);

        response.then().log().body();

        return response;

    }

    public static Response createActivity(ActivityModel activity) {

        System.out.println("\n POST ACTIVITY");

        String endpoint = Configuration.getActivitiesEndpoint();
        String requestBody = new Gson().toJson(activity);

        // Set the "Content-Type" header to "application/json" and include the request body
        Response response = given()
                .spec(requestSpec)
                .contentType("application/json")
                .body(requestBody)
                .post(BASE_URL + endpoint);
        response.then().log().body();


        return response;
    }

    public static Response getActivityById(int id) {
        System.out.println("\n GET ACTIVITY BY ID");
        String endpoint = Configuration.getActivitiesEndpoint() + "/" + id;
        return APIClient.get(endpoint, requestSpec);
    }

    public static Response deleteActivityById(int id) {
        System.out.println("\n DELETE ACTIVITY");
        String endpoint = Configuration.getActivitiesEndpoint() + "/" + id;
        Response response = APIClient.delete(endpoint, requestSpec);

        response.then().log().body();

        return response;
    }

    public static Response updateActivityById(int id, ActivityModel activity){
        System.out.println("\n UPDATE ACTIVITY");
        String endpoint = Configuration.getActivitiesEndpoint() + "/" + id;
        String requestBody = new Gson().toJson(activity);

        // Set the "Content-Type" header to "application/json" and include the request body
        Response response = given()
                .spec(requestSpec)
                .contentType("application/json")
                .body(requestBody)
                .put(BASE_URL + endpoint);

        response.then().log().body();

        return response;
    }
}

