package org.example.framework.api;

import org.example.framework.configuration.Configuration;
import org.example.framework.models.ActivityModel;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIClient {
    private static final String BASE_URL = Configuration.getBaseURL();

    public static Response get(String endpoint, RequestSpecification requestSpec) {
        RequestSpecification request = RestAssured.given();
        request.spec(requestSpec); // Set the request specification

        // Enable logging of the request URI

        request.baseUri(BASE_URL);
        request.basePath(endpoint);
        return request.get();
    }

    public static Response post(String endpoint, ActivityModel activity) {
        RequestSpecification request = RestAssured.given();
        request.baseUri(BASE_URL);
        request.basePath(endpoint);
        request.body(activity);
        return request.post();
    }

    public static Response put(String endpoint, RequestSpecification requestSpec) {
        return RestAssured.given()
                .spec(requestSpec)
                .put(endpoint);
    }

    public static Response delete(String endpoint, RequestSpecification requestSpec) {
        RequestSpecification request = RestAssured.given();
        request.baseUri(BASE_URL);
        request.basePath(endpoint);
        return request.delete();
    }

}
