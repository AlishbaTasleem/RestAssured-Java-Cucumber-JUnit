# RestAssured-Java-Cucumber-JUnit

This test automation framework is based on Cucumber, JUnit, and Rest Assured. It is designed to automate the testing of services/APIs for https://fakerestapi.azurewebsites.net/index.html. This framework provides an organized and scalable approach to writing and executing service-level tests.

## Features
1. **BDD Approach**: The framework follows the Behavior-Driven Development (BDD) approach using Cucumber, allowing tests to be written in a human-readable format.
2. **Service Testing**: The framework is specifically designed for testing services and APIs, enabling the validation of endpoints, request/response payloads, status codes, and more.
3. **JUnit Integration**: The framework integrates with JUnit.
4. **Rest Assured**: Rest Assured library is utilized to interact with services and perform assertions on API responses.
5. **Reusable Step Definitions**: The framework promotes reusable step definitions, allowing efficient test development and maintenance.

## Prerequisites
Before getting started with the Testing Framework, ensure that the following prerequisites are met:

- JDK 11+ is installed
- Maven is installed and configured

## Running Tests
To run the tests, execute the following command(s):

```shell
$ mvn test
