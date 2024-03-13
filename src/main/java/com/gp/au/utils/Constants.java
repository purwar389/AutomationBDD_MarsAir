package com.gp.au.utils;

public class Constants {
	
	// Search Home Page
    public static final String MARS_AIR_LOGO_CSS = "#app > h1 > a";
    public static final String HOME_WELCOME_TITLE_CSS = "#content > h2";
    public static final String HOME_WELCOME_SUB_TITLE_CSS = "#content > form > h3";
    public static final String HOME_DEPARTING_LABEL_XPATH = "//*[@for='departing']";
    public static final String HOME_RETURNING_LABEL_XPATH = "//*[@for='returning']";
    public static final String HOME_PROMOTIONAL_CODE_LABEL_XPATH = "//*[@for='promotional_code']";
    public static final String HOME_DEPARTING_DROPDOWN_XPATH = "//*[@id='departing']";
    public static final String HOME_RETURNING_DROPDOWN_XPATH = "//*[@id='returning']";
    public static final String HOME_PROMOTIONAL_CODE_FIELD_ID = "promotional_code";
    public static final String HOME_SEARCH_BUTTON_XPATH = "//*[@type='submit']";

    // Search Results Page
    public static final String SEARCH_RESULTS_TITLE_XPATH = "//p";
    public static final String SEARCH_RESULTS_SEAT_AVAILABILITY_TEXT_XPATH = "//*[@id='content']/p[1]";
    public static final String SEARCH_RESULTS_PROMO_CODE_TEXT_XPATH = "//*[@class='promo_code']";
    public static final String SEARCH_RESULTS_CALL_NOW_TEXT_XPATH = "//*[contains(text(),'Call')]";
    public static final String SEARCH_RESULTS_BACK_LINK_XPATH = "//a[text()=' Back']";

    // Footers
    public static final String REPORT_AN_ISSUE_LINK_CSS = "#report_issue > ul > li:nth-child(1) > a";
    public static final String PROBLEM_DEFINITION_LINK_CSS = "#report_issue > ul > li:nth-child(2) > a";
    public static final String PRIVACY_POLICY_LINK_CSS = "#report_issue > ul > li:nth-child(3) > a";

}
