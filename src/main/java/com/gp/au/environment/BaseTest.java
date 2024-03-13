package com.gp.au.environment;

import com.gp.au.methods.BrowserUtils;
import com.gp.au.methods.PropertiesManagementMethods;

/**
 * 
 * @author Gaurav Purwar
 *
 */
public interface BaseTest {
	PropertiesManagementMethods props = new PropertiesManagementMethods();
	BrowserUtils browser = new BrowserUtils();
}
