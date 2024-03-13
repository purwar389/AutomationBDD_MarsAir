package com.gp.test.featureDefinitions;

import com.gp.au.pageObjects.HomePage;
import com.gp.au.pageObjects.IssuePage;
import com.gp.au.pageObjects.ResultsPage;
import com.gp.au.utils.Constants;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;

import static com.gp.au.methods.SelectorType.CSS;
import static com.gp.au.methods.SelectorType.XPATH;

public class LinkToHomePageSteps {

	HomePage home = new HomePage();
	ResultsPage result = new ResultsPage();
	IssuePage issue = new IssuePage();
	SoftAssert softAssert = new SoftAssert();

	@When("^Mark is on the Search Result Page")
	public void markIsOnMarsSearchResultPage() {
		result.goToResultPage("July","December (two years from now)", "Success");
	}
	@When("^he clicks on \"Book a ticket to the red planet now!\" link$")
	public void clickOnBookNowLink() {
		softAssert.assertTrue(result.clickOnBookNowLink(), "Book a ticket to the red planet now! is not Clickable!");
		softAssert.assertAll();
	}

	@Then("^he should be redirected to the home page$")
	public void redirectToHomePage() {
		softAssert.assertTrue(home.verifyElementPresent(CSS, Constants.HOME_WELCOME_TITLE_CSS, "Welcome Title"), "Welcome Title is NOT present on the Home Page!");
		softAssert.assertEquals(home.getTextFromElement(CSS, Constants.HOME_WELCOME_TITLE_CSS,"Welcome Title"), "Welcome to MarsAir!","Welcome Title Message text didn't match!");
		softAssert.assertAll();
	}

	@When("^he clicks on the MarsAir logo on the top left$")
	public void clickOnMarsAirLogo() {
		home.clickOnButton(CSS, Constants.MARS_AIR_LOGO_CSS, "MarsAir Logo");
	}

	@When("^he clicks on the Back button at the bottom$")
	public void clickOnBackButton() {
		home.clickOnButton(XPATH, Constants.SEARCH_RESULTS_BACK_LINK_XPATH, "Back Button");
	}


	@When("^Mark is on the Issue Report Page$")
	public void onIssueReportPage() {
		issue.goToIssuePage();
	}

}
