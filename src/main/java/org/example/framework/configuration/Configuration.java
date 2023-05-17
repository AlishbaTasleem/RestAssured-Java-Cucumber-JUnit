package org.example.framework.configuration;

public class Configuration {
    public static final String BASE_URL = "https://fakerestapi.azurewebsites.net";
    public static final String ACTIVITIES_ENDPOINT = "/api/v1/Activities";
    // Add other configuration properties as needed

    public static String getBaseURL() {
        return BASE_URL;
    }

    public static String getActivitiesEndpoint() {
        return ACTIVITIES_ENDPOINT;
    }

    // Add other methods to retrieve additional configuration properties
}
