package com.gp.test.featureDefinitions;

import java.io.IOException;

import com.gp.au.environment.BaseTest;
import com.gp.au.logger.Log;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


public class Hooks implements BaseTest {


    @Before()
    public void beforeScenario(Scenario scenario) throws IOException {
        Log.INFO("Scenario: " + scenario.getName() + " started");
        browser.printDesktopConfiguration();
    }

    @After()
    public void afterScenario(Scenario scenario) throws IOException {

        if (scenario.isFailed()) {
            Log.ERROR("Scenario: " + scenario.getName() + " failed");
        } else {
            Log.INFO("Scenario: " + scenario.getName() + " passed");
        }

        browser.attachSnapshotToReport();
    }

}


