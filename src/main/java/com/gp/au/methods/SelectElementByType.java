package com.gp.au.methods;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class SelectElementByType {
    /**
     * Method to select element 'by' type
     * @author Gaurav Purwar
     * @param type
     *            : String : 'By' type
     * @param access_name
     *            : String : Locator value
     * @return By
     */
    
    public static By getElementByType(SelectorType selectorType, String selectorValue) throws NoSuchElementException {

        switch (selectorType) {
        case CSS:
            return By.cssSelector(selectorValue);
        case ID:
            return By.id(selectorValue);
        case CLASS:
            return By.className(selectorValue);
        case XPATH:
            return By.xpath(selectorValue);
        case NAME:
            return By.name(selectorValue);
        case LINK_TEXT:
            return By.linkText(selectorValue);
        case PARTIAL_LINK_TEXT:
            return By.partialLinkText(selectorValue);
        default:
            return By.cssSelector(selectorValue);
        }
    }

}
