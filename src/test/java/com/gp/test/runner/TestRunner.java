package com.gp.test.runner;

import com.gp.au.environment.BaseTest;
import com.gp.au.logger.Log;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
	    features ="src/test/resources/Features",
	    glue = {"com.gp.test.featureDefinitions"},
	    plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		tags = "@travel",
		publish =true,
	    monochrome = true
)
/**
 * Cucumber Runner Class
 * @author Gaurav Purwar
 *
 */
public class TestRunner extends AbstractTestNGCucumberTests implements BaseTest {


	@BeforeClass()
    public void beforeClass()  {
		Log.INFO("**********************Before Class Executed**********************");
		browser.navigateTo(props.getProperty("baseUrl"));
    }


	@AfterClass()
    public void afterClass()  {
		browser.closeDriver();
		Log.INFO("**********************After Class Executed**********************");
    }
}