package com.gp.au.pageObjects;

import com.gp.au.environment.BaseTest;
import com.gp.au.logger.Log;
import com.gp.au.methods.SelectorType;
import com.gp.au.utils.Constants;

import java.util.List;

import static com.gp.au.methods.SelectorType.*;


/**
 * 
 * Methods for Search Home Page
 * @author Gaurav Purwar
 *
 */

public class ResultsPage implements BaseTest {

	HomePage home = new HomePage();

	public boolean goToResultPage(String departureValue, String returnValue, String resultType) {
		Log.INFO("On Home Page");
		boolean isResultsPage = home.verifyFlightSearch(departureValue, returnValue);

		if (isResultsPage) {
			Log.INFO("Navigated to " + resultType + " Search Results Page!");
		} else {
			Log.ERROR("Failed to navigate to " + resultType + " Search Results Page!");
		}

		return isResultsPage;
	}

	public String getTextFromElement(SelectorType selectorType, String accessName, String fldName) {
		Log.INFO("Get Text From Element On Search Results Page: " + fldName);

		String elementText = browser.getElementText(selectorType, accessName);

		if (elementText != null && !elementText.isEmpty()) {
			Log.INFO(fldName + " text is: " + elementText);
		} else {
			Log.ERROR("Unable to retrieve text from " + fldName);
		}

		return elementText;
	}

	public String getResultsMessageText() {
		Log.INFO("Get Result Message Text From Element On Search Results Page");

		String messageText = getTextFromElement(XPATH, Constants.SEARCH_RESULTS_SEAT_AVAILABILITY_TEXT_XPATH,"Search Result Message");

		if (messageText != null && messageText.contains("Seats available")) {
			String callMessageText = getTextFromElement(XPATH, Constants.SEARCH_RESULTS_CALL_NOW_TEXT_XPATH,"Call Text Result Message");
			messageText = messageText+" "+callMessageText;
			Log.INFO(" Result text is: " + messageText);
		}

		return messageText;
	}

	public String getPromoCodeMessage() {
		Log.INFO("Get Promo Code Result Message Text From Element On Search Results Page");
		// Get the promotional code message from the DOM using XPath
		String promoText = getTextFromElement(XPATH, Constants.SEARCH_RESULTS_PROMO_CODE_TEXT_XPATH, "Search Result Message");
		return promoText;
	}

	public void clickSubmit() {
		Log.INFO("Click Search on Home Page");
        browser.click(XPATH, Constants.HOME_SEARCH_BUTTON_XPATH);
        Log.INFO("Search Button Clicked!");
		
	}

	public boolean clickOnBookNowLink() {
		Log.INFO("Click Subtitle on Home Page");
		if (browser.isElementClickable(CSS, Constants.HOME_WELCOME_SUB_TITLE_CSS)) {
			browser.click(CSS, Constants.HOME_WELCOME_SUB_TITLE_CSS);
			Log.INFO("“Book a ticket to the red planet now!” is Clicked!");
			return true;
		} else {
			Log.ERROR("Subtitle on Home Page is not clickable");
			return false;
		}
	}

}
