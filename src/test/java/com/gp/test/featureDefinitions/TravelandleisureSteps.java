package com.gp.test.featureDefinitions;

import com.gp.au.pageObjects.HomePage;
import com.gp.au.pageObjects.ResultsPage;
import com.gp.au.pageObjects.TravelPage;
import com.gp.au.utils.Constants;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

import static com.gp.au.methods.SelectorType.*;

public class TravelandleisureSteps {

	TravelPage travel = new TravelPage();
	ResultsPage result = new ResultsPage();
	SoftAssert softAssert = new SoftAssert();

	@Given("^Mark is on the Travel website")
	public void markIsOnMarsAirWebsite() {
		travel.goToHomePage();
	}

	@When("^He is on Home Page and Perform Trip Activity")
	public void heIsOnSearchHomePage() throws InterruptedException {
		travel.performTripActivity();


	}

}