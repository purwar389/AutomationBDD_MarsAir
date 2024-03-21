package com.gp.test.featureDefinitions;

import com.gp.au.pageObjects.ApiUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;

import static com.gp.au.environment.BaseTest.props;


public class APIValidationSteps {

	ApiUtil apiUtil = new ApiUtil();

	SoftAssert softAssert = new SoftAssert();

	private String apiEndpoint;
	private boolean authenticationRequired;

	@Given("^the API endpoint from properties file$")
	public void setApiEndpoint() {
		this.apiEndpoint = props.getProperty("baseUrl");
	}

	@When("^I send a POST request without any authentication parameters$")
	public void sendPostRequest() {
		// Implementation to send POST request without authentication parameters
		authenticationRequired = apiUtil.checkAuthentication(apiEndpoint);
	}

	@Then("^the response status code should be 200 OK$")
	public void verifyResponseStatusCode() {
		// Implementation to verify response status code
		softAssert.assertEquals(true, authenticationRequired,"Authentication Check Failed!");
		softAssert.assertAll();
	}

	@Then("^authentication should not be required$")
	public void verifyAuthenticationNotRequired() {
		softAssert.assertEquals(false, authenticationRequired,"Authentication Check Failed!");
		softAssert.assertAll();
	}
}
