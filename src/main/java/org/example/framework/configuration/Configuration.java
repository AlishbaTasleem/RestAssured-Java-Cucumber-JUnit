package org.example.framework.configuration;

public class Configuration {
    public static final String BASE_URL = "https://fakerestapi.azurewebsites.net";
    public static final String ACTIVITIES_ENDPOINT = "/api/v1/Activities";

    public static final String BOOKS_ENDPOINT = "/api/v1/Books";
    public static final String AUTHORS_ENDPOINT = "/api/v1/Authors/authors/books";

    // Add other configuration properties as needed

    public static String getBaseURL() {
        return BASE_URL;
    }

    public static String getActivitiesEndpoint() {
        return ACTIVITIES_ENDPOINT;
    }

    public static String getBooksEndpoint() { return BOOKS_ENDPOINT; }

    public static String getAuthorsEndpoint() { return AUTHORS_ENDPOINT; }

    // Add other methods to retrieve additional configuration properties
}
