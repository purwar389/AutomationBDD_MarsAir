package com.gp.au.pageObjects;

import com.gp.au.environment.BaseTest;
import com.gp.au.logger.Log;
import com.gp.au.methods.SelectorType;
import com.gp.au.utils.Constants;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.gp.au.methods.SelectorType.ID;
import static com.gp.au.methods.SelectorType.XPATH;


/**
 * 
 * Methods for Search Home Page
 * @author Gaurav Purwar
 *
 */

public class TravelPage implements BaseTest{
	private String promoCode;
	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}


	public void goToHomePage() {
		Log.INFO("Go to Travel Page");
		if(!browser.isDriverNull()){
			browser.navigateToUrl("https://www.travelandleisure.cloud/Start");
		}else{
			browser.navigateTo("https://www.travelandleisure.cloud/Start");
		}
		Log.INFO("Mark is on the Travel website successfully!");

	}


	public void performTripActivity() throws InterruptedException {
		Log.INFO("Enter "+promoCode+" on Promotional Code Field");
		browser.click(XPATH, "//*[contains(text(),'Get Started')]");
		//Username:   felix6940
		//Password:    aa123321
		browser.enterTextBySendKeys(XPATH, "felix6940", "//*[@type='text']");
		browser.enterTextBySendKeys(XPATH, "aa123321", "//*[@type='password']");
		browser.click(XPATH, "//*[@id='app']/div/div/div/div/div[2]/div[5]/button");

		int numberOfIterations = 40;

		for (int i = 0; i < numberOfIterations; i++) {

			browser.click(XPATH, "//*[@id='app']/div/div[2]/div[2]");
			Thread.sleep(5000);
			browser.click(XPATH, "//*[@id='app']/div/div[1]/div/div/div[2]/div[2]/button");
			Thread.sleep(2000);
			browser.click(XPATH, "//*[@id='app']/div/div/div/div/div[3]/div/div[2]/div/div[2]/div[5]/i");
			browser.click(XPATH, "//*[@id='app']/div/div/div/div/div[3]/div/div[3]/button");
			Thread.sleep(5000);

		}

		Log.INFO(promoCode+ " has been entered!");
	}

	public boolean verifyElementPresent(SelectorType selectorType, String accessName, String fldName) {
		Log.INFO("Verify Element On Search Home Page: " + fldName);

		boolean isElementPresent = browser.isElementPresent(selectorType, accessName);

		if (isElementPresent) {
			Log.INFO(fldName + " is present on the Home Page!");
		} else {
			Log.ERROR(fldName + " is NOT present on the Home Page!");
		}

		return isElementPresent;
	}

	public String getTextFromElement(SelectorType selectorType, String accessName, String fldName) {
		Log.INFO("Get Text From Element On Search Home Page: " + fldName);

		String elementText = browser.getElementText(selectorType, accessName);

		if (elementText != null && !elementText.isEmpty()) {
			Log.INFO(fldName + " text is: " + elementText);
			// Additional actions or assertions for the retrieved text
		} else {
			Log.ERROR("Unable to retrieve text from " + fldName);
			// Additional actions or assertions for the absence of text
		}

		return elementText;
	}


	public void selectDropdownValue(SelectorType selectorType, String accessName, String valueToSelect, String fldName) {
		Log.INFO("Selecting value from the dropdown on Search Home Page: " + fldName);
		boolean isValueSelected = browser.selectValueFromDropdown(selectorType, accessName, valueToSelect, "Departure");

		if (isValueSelected) {
			Log.INFO("Selected value from the " + fldName + " dropdown: " + valueToSelect);
		} else {
			Log.ERROR("Unable to select value from the " + fldName + " dropdown");
		}
	}

	public List<String> getDropdownValues(SelectorType selectorType, String accessName, String fldName) {
		Log.INFO("Getting all values from the dropdown on Search Home Page: " + fldName);
		List<String> dropdownValues = browser.getAllDropdownValues(selectorType, accessName, fldName);

		if (dropdownValues == null || dropdownValues.isEmpty()) {
			Log.ERROR("No values found in the dropdown for " + fldName);
		} else {
			Log.INFO("Dropdown values for " + fldName + ": " + dropdownValues);
		}

		return dropdownValues;
	}

	public boolean verifyFlightSearch(String departureValue, String returnValue) {
		Log.INFO("Verify Flight Search on Home Page");
		selectDropdownValue(XPATH, Constants.HOME_DEPARTING_DROPDOWN_XPATH, departureValue,"Departure Dropdown");
		selectDropdownValue(XPATH, Constants.HOME_RETURNING_DROPDOWN_XPATH, returnValue,"Return Dropdown");
		clickOnButton(XPATH, Constants.HOME_SEARCH_BUTTON_XPATH, "Search Button");
		boolean isSearchResultPage = browser.isElementPresent(XPATH, Constants.SEARCH_RESULTS_TITLE_XPATH);

		if (isSearchResultPage) {
			Log.INFO("Flight is Searchable!");
		} else {
			Log.ERROR("Flight is not Searchable!");
		}

		return isSearchResultPage;
	}


	public void clickOnButton(SelectorType selectorType, String accessName, String btnName) {
		Log.INFO("Click "+btnName+" on Home Page");
        browser.click(selectorType, accessName);
        Log.INFO(btnName+" Clicked!");
		
	}
	
	public void enterPromotionalCode(String promoCode) {
		Log.INFO("Enter "+promoCode+" on Promotional Code Field");
		setPromoCode(promoCode);
		browser.enterTextBySendKeys(ID, promoCode, Constants.HOME_PROMOTIONAL_CODE_FIELD_ID);
        Log.INFO(promoCode+ " has been entered!");
	}

	public List<String> generateExpectedDropdownValues() {
		List<String> dropdownValues = new ArrayList<>();
		LocalDate currentDate = LocalDate.now();

		// Add the default option
		dropdownValues.add("Select...");

		int currentMonth = currentDate.getMonthValue();
		// Include July if it's the current month or a future month
		if (currentMonth <= Month.JULY.getValue()) {
			dropdownValues.add(currentDate.withMonth(Month.JULY.getValue()).format(DateTimeFormatter.ofPattern("MMMM")));
		}
		// Include December if it's the current month or a future month
		if (currentMonth <= Month.DECEMBER.getValue()) {
			dropdownValues.add(currentDate.withMonth(Month.DECEMBER.getValue()).format(DateTimeFormatter.ofPattern("MMMM")));
		}
		dropdownValues.add(currentDate.withMonth(Month.JULY.getValue()).format(DateTimeFormatter.ofPattern("MMMM")) + " (next year)");
		dropdownValues.add(currentDate.withMonth(Month.DECEMBER.getValue()).format(DateTimeFormatter.ofPattern("MMMM")) + " (next year)");
		dropdownValues.add(currentDate.withMonth(Month.JULY.getValue()).format(DateTimeFormatter.ofPattern("MMMM")) + " (two years from now)");
		dropdownValues.add(currentDate.withMonth(Month.DECEMBER.getValue()).format(DateTimeFormatter.ofPattern("MMMM")) + " (two years from now)");

		return dropdownValues;
	}

	public boolean isPromoCodeFormatValid() {
		String regex = "[A-Z]{2}\\d{1}-[A-Z]{3}-\\d{3}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(promoCode);
		return matcher.matches();
	}

	public boolean checkDiscountPercentage(String expectedDiscounts) {
		char firstDigitChar = promoCode.charAt(2);
		int firstDigit = Character.getNumericValue(firstDigitChar);
		// Extracting the expected discount from the step
		int expectedDiscount = Integer.parseInt(expectedDiscounts.replace("%", ""));
		// Calculating the expected discount based on the first digit of the promo code
		int expectedDiscountPercentage = firstDigit * 10;
		// Comparing the expected and actual discounts
		return expectedDiscount == expectedDiscountPercentage;
	}

	public boolean checkPromoCodeCheckDigit() {
		// Remove non-numeric characters from the promo code
		String sanitizedPromoCode = promoCode.replaceAll("[^\\d]", "");

		// Extracting the check digit from the promo code
		int checkDigit = Character.getNumericValue(sanitizedPromoCode.charAt(sanitizedPromoCode.length() - 1));

		// Calculating the sum of all other digits
		int sumOfOtherDigits = 0;
		for (int i = 0; i < sanitizedPromoCode.length() - 1; i++) {
			sumOfOtherDigits += Character.getNumericValue(sanitizedPromoCode.charAt(i));
		}

		// Checking if the check digit is equal to the sum of all other digits
		return checkDigit == sumOfOtherDigits % 10;
	}
}
