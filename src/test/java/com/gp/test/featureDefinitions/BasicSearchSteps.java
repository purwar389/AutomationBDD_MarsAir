package com.gp.test.featureDefinitions;

import com.gp.au.pageObjects.HomePage;
import com.gp.au.pageObjects.ResultsPage;
import com.gp.au.utils.Constants;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

import static com.gp.au.methods.SelectorType.*;

public class BasicSearchSteps {

	HomePage home = new HomePage();
	ResultsPage result = new ResultsPage();
	SoftAssert softAssert = new SoftAssert();

	@Given("^Mark is on the MarsAir website")
	public void markIsOnMarsAirWebsite() {
		home.goToHomePage();
	}

	@When("^He is on Search Home Page")
	public void heIsOnSearchHomePage() {
		softAssert.assertTrue(home.verifyElementPresent(CSS, Constants.HOME_WELCOME_TITLE_CSS, "Welcome Title"), "Welcome Title is NOT present on the Home Page!");
		softAssert.assertAll();

	}

	@Then("^There should be title ‘Welcome to MarsAir!’")
	public void verifyTitle() {
		softAssert.assertTrue(home.verifyElementPresent(CSS, Constants.HOME_WELCOME_TITLE_CSS, "Welcome Title"), "Welcome Title is NOT present on the Home Page!");
		softAssert.assertEquals(home.getTextFromElement(CSS, Constants.HOME_WELCOME_TITLE_CSS,"Welcome Title"), "Welcome to MarsAir!","Welcome Title Message text didn't match!");
		softAssert.assertAll();

	}

	@And("^There should be subtitle ‘Book a ticket to the red planet now!’")
	public void verifySubtitle() {
		softAssert.assertTrue(home.verifyElementPresent(CSS, Constants.HOME_WELCOME_SUB_TITLE_CSS, "Welcome Sub Title"), "Welcome Title is NOT present on the Home Page!");
		softAssert.assertEquals(home.getTextFromElement(CSS, Constants.HOME_WELCOME_SUB_TITLE_CSS,"Welcome Sub Title"), "Book a ticket to the red planet now!","Welcome Sub Title Message text didn't match!");
		softAssert.assertAll();

	}

	@And("^There should be ‘Departure’ dropdown field on a search form")
	public void verifyDepartureDropdown() {
		softAssert.assertTrue(home.verifyElementPresent(XPATH, Constants.HOME_DEPARTING_LABEL_XPATH, "Departure Label"), "Departure Label is NOT present on the Home Page!");
		softAssert.assertTrue(home.verifyElementPresent(XPATH, Constants.HOME_DEPARTING_DROPDOWN_XPATH, "Departure Dropdown"), "Departure Dropdown is NOT present on the Home Page!");
		softAssert.assertEquals(home.getTextFromElement(XPATH, Constants.HOME_DEPARTING_LABEL_XPATH,"Departure Label"), "Departure","Departure Label text didn't match!");
		softAssert.assertAll();

	}

	@And("^There should be ‘Return’ dropdown field on a search form")
	public void verifyReturnDropdown() {
		softAssert.assertTrue(home.verifyElementPresent(XPATH, Constants.HOME_RETURNING_LABEL_XPATH, "Return Label"), "Return Label is NOT present on the Home Page!");
		softAssert.assertTrue(home.verifyElementPresent(XPATH, Constants.HOME_RETURNING_DROPDOWN_XPATH, "Return Dropdown"), "Return Dropdown is NOT present on the Home Page!");
		softAssert.assertEquals(home.getTextFromElement(XPATH, Constants.HOME_RETURNING_LABEL_XPATH,"Return Label"), "Return","Return Label text didn't match!");
		softAssert.assertAll();

	}

	@And("^There should be ‘Promotional Code’ editable field on a search form")
	public void verifyPromotionalCodeField() {
		softAssert.assertTrue(home.verifyElementPresent(XPATH, Constants.HOME_PROMOTIONAL_CODE_LABEL_XPATH, "Promotional Code Label"), "Promotional Code Label is NOT present on the Home Page!");
		softAssert.assertTrue(home.verifyElementPresent(ID, Constants.HOME_PROMOTIONAL_CODE_FIELD_ID, "Promotional Code Field"), "Promotional Code Field is NOT present on the Home Page!");
		softAssert.assertEquals(home.getTextFromElement(XPATH, Constants.HOME_PROMOTIONAL_CODE_LABEL_XPATH,"Promotional Code Label"), "Promotional Code","Promotional Code Label text didn't match!");
		softAssert.assertAll();

	}

	@And("^Flights leave every six months, in July and December, both ways")
	public void verifyFlightSchedule() {
		List<String> expectedDropdownValues = Arrays.asList("Select...", "July", "December", "July (next year)", "December (next year)", "July (two years from now)", "December (two years from now)");
		// Departure
		List<String> actualDepartureDropdownValues = home.getDropdownValues(XPATH, Constants.HOME_DEPARTING_DROPDOWN_XPATH, "Departure Dropdown");
		softAssert.assertEquals(actualDepartureDropdownValues, expectedDropdownValues, "Dropdown values don't match the expected order");
		// Return
		List<String> actualReturnDropdownValues = home.getDropdownValues(XPATH, Constants.HOME_RETURNING_DROPDOWN_XPATH, "Return Dropdown");
		softAssert.assertEquals(actualReturnDropdownValues, expectedDropdownValues, "Dropdown values don't match the expected order");
		softAssert.assertAll();

	}

	@And("^There should be ‘Search’ button on a search form")
	public void verifySearchButton() {
		softAssert.assertTrue(home.verifyElementPresent(XPATH, Constants.HOME_SEARCH_BUTTON_XPATH, "Search Button"), "Search Button is NOT present on the Home Page!");
		softAssert.assertAll();

	}

	@Then("^Verify that trips for the next two years should be searchable")
	public void verifyTripsSearchable() {
		softAssert.assertTrue(home.verifyFlightSearch("July","December (two years from now)"),"The trips are not searchable");
		softAssert.assertAll();
	}

	@When("^He searches for a flight with departure in July and return in December with available seats")
	public void searchForAvailableFlightsWithSeatsAvailable() {
		softAssert.assertTrue(result.goToResultPage("July","December (two years from now)", "Success"),"Flight Search was unsuccessful!");
		softAssert.assertAll();
	}

	@Then("^If there are seats, display 'Seats available! Call 0800 MARSAIR to book!'")
	public void displaySeatsAvailableMessage() {
		//result.goToResultPage("July","December (two years from now)", "Success");
		softAssert.assertEquals(result.getResultsMessageText(), "Seats available! Call 0800 MARSAIR to book!","Seats available text didn't match!");
		softAssert.assertAll();
	}

	@When("^He searches for a fully booked flight with departure in July and return in December")
	public void searchForAvailableFlightsWithNoSeatsAvailable() {
		softAssert.assertTrue(result.goToResultPage("July","July", "Failed"),"Flight Search was successful instead of unsuccess!");
		softAssert.assertAll();
	}

	@Then("^If there are no seats, display 'Sorry, there are no more seats available.'")
	public void displayNoSeatsAvailableMessage() {
		softAssert.assertEquals(result.getTextFromElement(XPATH, Constants.SEARCH_RESULTS_SEAT_AVAILABILITY_TEXT_XPATH,"No Seats Available Text"), "Sorry, there are no more seats available.","No Seats available text didn't match!");
		softAssert.assertAll();
	}

	@When("^He searches for a flight with a valid departure in \"([^\"]*)\" and return in \"([^\"]*)\"")
	public void searchForAvailableFlightsWithDifferentInputs(String departureValue, String returnValue) {
		softAssert.assertTrue(home.verifyFlightSearch(departureValue, returnValue), "Flight Search is incorrect!");
		softAssert.assertAll();
	}

	@Then("^Verify the result type \"([^\"]*)\" for each inputs")
	public void displaySeatsAvailableMessageWithDifferentInputs(String expectedResultMessage) {
		softAssert.assertEquals(result.getResultsMessageText(), expectedResultMessage,"Search Result Message text didn't match!");
		softAssert.assertAll();
	}

	@When("^He validates 'Departure' dropdown field")
	public void validateDeparture() {
		softAssert.assertTrue(home.verifyElementPresent(XPATH, Constants.HOME_DEPARTING_DROPDOWN_XPATH, "Departure Dropdown"), "Departure Dropdown is NOT present on the Home Page!");
		softAssert.assertAll();

	}

	@When("^He validates 'Return' dropdown field")
	public void validateReturn() {
		softAssert.assertTrue(home.verifyElementPresent(XPATH, Constants.HOME_RETURNING_DROPDOWN_XPATH, "Return Dropdown"), "Return Dropdown is NOT present on the Home Page!");
		softAssert.assertAll();
	}

	@Then("^The 'Departure' dropdown field should not contain values for months before the current month")
	public void validateDepartureDropdownValues() {
		List<String> expectedDropdownValues = home.generateExpectedDropdownValues();
		List<String> actualReturnDropdownValues = home.getDropdownValues(XPATH, Constants.HOME_DEPARTING_DROPDOWN_XPATH, "Departure Dropdown");

		softAssert.assertEquals(actualReturnDropdownValues, expectedDropdownValues, "Dropdown values don't match the expected order");
		softAssert.assertAll();
	}

	@Then("^The 'Return' dropdown field should not contain values for months before the current month")
	public void validateReturnDropdownValues() {
		List<String> expectedDropdownValues = home.generateExpectedDropdownValues();
		List<String> actualReturnDropdownValues = home.getDropdownValues(XPATH, Constants.HOME_RETURNING_DROPDOWN_XPATH, "Return Dropdown");

		softAssert.assertEquals(actualReturnDropdownValues, expectedDropdownValues, "Dropdown values don't match the expected order");
		softAssert.assertAll();
	}
}
