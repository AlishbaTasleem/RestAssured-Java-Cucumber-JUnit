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

public class BooksService {

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

    public static Response getAllBooks(){
        System.out.println("\n GET ALL BOOKS");
        String endpoint = Configuration.getBooksEndpoint();
        Response response = APIClient.get(endpoint, requestSpec);

        response.then().log().body();

        return response;
    }
}
