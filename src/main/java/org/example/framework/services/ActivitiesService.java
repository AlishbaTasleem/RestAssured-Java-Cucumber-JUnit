package org.example.framework.services;

import com.google.gson.Gson;
import org.example.framework.api.APIClient;
import org.example.framework.configuration.Configuration;
import org.example.framework.models.ActivityModel;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class ActivitiesService {
    public static Response getAllActivities() {
        String endpoint = Configuration.getActivitiesEndpoint();
        return APIClient.get(endpoint);
    }

    public static Response createActivity(ActivityModel activity) {
        String endpoint = Configuration.getActivitiesEndpoint();
        String requestBody = new Gson().toJson(activity);

        // Set the "Content-Type" header to "application/json" and include the request body
        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .post(endpoint);

        return response;
    }

    public static Response getActivityById(int id) {
        String endpoint = Configuration.getActivitiesEndpoint() + "/" + id;
        return APIClient.get(endpoint);
    }

    public static Response deleteActivityById(int id) {
        String endpoint = Configuration.getActivitiesEndpoint() + "/" + id;
        return APIClient.delete(endpoint);
    }
}

