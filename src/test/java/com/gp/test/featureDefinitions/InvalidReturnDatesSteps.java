package com.gp.test.featureDefinitions;

import com.gp.au.pageObjects.HomePage;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;

public class InvalidReturnDatesSteps {

	HomePage home = new HomePage();
	SoftAssert softAssert = new SoftAssert();


	@When("^return date \"([^\"]*)\" is less than 1 year from the departure \"([^\"]*)\"$")
	public void returnDateIsLessThan1YearFromDeparture(String returnDate, String departure) {
		softAssert.assertTrue(home.verifyFlightSearch(departure, returnDate), "Flight Search is incorrect!");
		softAssert.assertAll();
	}

	@When("^return date \"([^\"]*)\" is more than 1 year from the departure \"([^\"]*)\"$")
	public void returnDateIsMoreThan1YearFromDeparture(String returnDate, String departure) {
		softAssert.assertTrue(home.verifyFlightSearch(departure, returnDate), "Flight Search is incorrect!");
		softAssert.assertAll();
	}

}
