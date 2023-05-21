package com.example.tests.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
public class Hooks {

    @Before
    public void setup() {
        // Perform setup actions before each scenario
        System.out.println("Before Hook: Setup");
    }

    @After
    public void teardown() {
        // Perform teardown actions after each scenario
        System.out.println("After Hook: Teardown");
    }


}
