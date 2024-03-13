package com.gp.test.featureDefinitions;

import com.gp.au.pageObjects.HomePage;
import com.gp.au.pageObjects.ResultsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;


public class PromotionalCodesSteps {

	HomePage home = new HomePage();
	ResultsPage result = new ResultsPage();
	SoftAssert softAssert = new SoftAssert();

	@When("^he enters the given promotional code \"([^\"]*)\"$")
	public void heEntersTheGivenPromotionalCode(String promoCode) {
		home.enterPromotionalCode(promoCode);
	}

	@Then("^The promotional code should be in the format XX9-XXX-999$")
	public void validatePromotionalCodeFormat() {
		softAssert.assertTrue(home.isPromoCodeFormatValid(), "Promotional code format is incorrect");
		softAssert.assertAll();
	}

	@And("^The first digit should indicate the discount percentage as \"([^\"]*)\"$")
	public void validateDiscountPercentage(String expectedDiscounts) {
		softAssert.assertTrue(home.checkDiscountPercentage(expectedDiscounts), "Discount percentage is incorrect");
		softAssert.assertAll();
	}

	@And("^The final digit is a check digit and equal to the sum of all other digits$")
	public void validateCheckDigit() {
		boolean isCheckDigitValid = home.checkPromoCodeCheckDigit();
		softAssert.assertTrue(isCheckDigitValid, "Check digit is invalid");
		softAssert.assertAll();
	}

	@Then("^The search result should be displayed as Promotional code \"([^\"]*)\" used \"([^\"]*)\" discount! message$")
	public void validateSearchResultMessage(String promoCode, String expectedDiscounts) {
		String expectedMessage = String.format("Promotional code %s used: %s discount!", promoCode, expectedDiscounts);
		String actualMessage = result.getPromoCodeMessage(); // Assuming you have a method to get the search result message

		softAssert.assertEquals(actualMessage, expectedMessage, "Search result message is incorrect");
		softAssert.assertAll();
	}

	@Then("^The search result should be displayed as \"Sorry code \"([^\"]*)\" is not valid\"$")
	public void validateInvalidPromoCode(String promoCode) {
		String expectedMessage = String.format("Sorry, code %s is not valid", promoCode);
		String actualMessage = result.getPromoCodeMessage(); // Assuming you have a method to get the search result message
		softAssert.assertEquals(actualMessage, expectedMessage, "Search result message is incorrect");
		softAssert.assertAll();
	}

}
