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

public class IssuePage implements BaseTest {
	HomePage home = new HomePage();

	public void goToIssuePage() {
		Log.INFO("On Home Page");
		home.clickOnButton(CSS, Constants.REPORT_AN_ISSUE_LINK_CSS, "Report an issue link");
		Log.INFO("Navigated to Issue Report Page");
	}

}
