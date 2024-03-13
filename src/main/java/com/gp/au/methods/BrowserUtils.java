package com.gp.au.methods;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.gp.au.environment.BrowserFactory;
import org.apache.commons.io.FileUtils;

import org.junit.ClassRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gp.au.environment.BaseTest;
import com.gp.au.logger.Log;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

/**
 * Browser Utils functions
 * @author Gaurav Purwar
 *
 */
public class BrowserUtils extends SelectElementByType implements BaseTest {
	private WebElement element = null;
	private String urlToNavigate = null;

	WebDriver driver;
	WebDriverWait wait;

	/**
	 * Method to open link
	 * 
	 * @author Gaurav Purwar
	 * 
	 * @param url String : URL for navigation
	 */
	public void navigateTo(String url) {
		BrowserFactory browserFactory = new BrowserFactory();
		driver = browserFactory.getBrowser("Chrome");
		driver.get(url);
		wait = new WebDriverWait(driver, 10);
		Log.INFO("Navigate to: " + url);
		driver.manage().window().maximize();
	}

	public void navigateToUrl(String url) {
		if (url.contains("http")) {
			urlToNavigate = url;
		}else {
			urlToNavigate = props.getProperty(url);
		}

		Log.INFO("Navigate to: " + urlToNavigate);
		driver.get(urlToNavigate);
		driver.manage().window().maximize();
	}

	public WebDriver getDriver() {
		return driver;
	}

	public boolean isDriverNull() {
		return driver == null;
	}

	/**
	 * Method to quite webdriver instance
	 */
	public void closeDriver() { 
		  driver.quit();
	  }
	  
	

	/**
	 * Method to get element text
	 * 
	 * @author Gaurav Purwar
	 * @param selectorType : String : Locator type (id, name, class, xpath, css)
	 * @param accessName   : String : Locator value
	 * @return String
	 */
	public String getElementText(SelectorType selectorType, String accessName)
			throws TimeoutException, NoSuchElementException {
		element = wait.until(ExpectedConditions.presenceOfElementLocated(getElementByType(selectorType, accessName)));
		return element.getText();

	}

	/**
	 * Method to get the web element
	 * 
	 * @author Gaurav Purwar
	 * @param selectorType
	 * @param accessName
	 * @return
	 */
	public WebElement getWebelement(SelectorType selectorType, String accessName) {
		WebElement ele = driver.findElement(getElementByType(selectorType, accessName));
		return ele;

	}

	/**
	 * Method to click on an element
	 * 
	 * @author Gaurav Purwar
	 * @param selectorType : String : Locator type (id, name, class, xpath, css)
	 * @param accessName   : String : Locator value
	 */
	public void click(SelectorType selectorType, String accessName) {
		element = wait.until(ExpectedConditions.presenceOfElementLocated(getElementByType(selectorType, accessName)));
		element.click();
	}


	/** Method to print desktop configuration */
	public void printDesktopConfiguration() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		Calendar cal = Calendar.getInstance();
		Log.INFO("Test started: " + dateFormat.format(cal.getTime()));
	}


	/**
	 * Method to enter text into text field, using send keys
	 * 
	 * @author Gaurav Purwar
	 * @param selectorType : String : Locator type (id, name, class, xpath, css)
	 * @param text         : String : Text value to enter in field
	 * @param accessName   : String : Locator value
	 */
	public void enterTextBySendKeys(SelectorType selectorType, String text, String accessName) {
		wait.until(ExpectedConditions.presenceOfElementLocated(getElementByType(selectorType, accessName)));
		try {
			wait("1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
		} 
		clearText(selectorType, accessName);
		driver.findElement(getElementByType(selectorType, accessName)).sendKeys(text);
	}

	/**
	 * Method to clear text of text field
	 * 
	 * @author Gaurav Purwar
	 * @param selectorType : String : Locator type (id, name, class, xpath, css)
	 * @param accessName   : String : Locator value
	 */
	public void clearText(SelectorType selectorType, String accessName) {
		wait.until(ExpectedConditions.presenceOfElementLocated(getElementByType(selectorType, accessName)));
		driver.findElement(getElementByType(selectorType, accessName)).clear();
	}

	/**
	 * Method to wait
	 * 
	 * @author Gaurav Purwar
	 * @param time   : String : Time to wait
	 * @throws NumberFormatException
	 * @throws InterruptedException
	 */
	public void wait(String time) throws NumberFormatException, InterruptedException {
		// sleep method takes parameter in milliseconds
		Log.INFO("Wait: " + time + " sec");
		Thread.sleep(Integer.parseInt(time) * 1000);
	}

	public String getSnapshotFolderPath() {
		File currentDirFile = new File("Screenshots");
		String path = currentDirFile.getAbsolutePath();

		return path;
	}

	/**
	 * Method to take screen shot and save in ./Screenshots folder
	 * 
	 * @return
	 */
	public String takeScreenShot() throws IOException {

		Log.INFO("Taking snapshot");
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar cal = Calendar.getInstance();

		String snapshotFileName = "screenshot" + dateFormat.format(cal.getTime()) + ".png";
		String pathToSnapshot = getSnapshotFolderPath() + "/" + snapshotFileName;

		FileUtils.copyFile(scrFile, new File(pathToSnapshot));

		return snapshotFileName;

	}

	/**
	 * Method to take screenshot to allure report
	 * 
	 * @return
	 */
	public byte[] embedScreenshotInReport() throws IOException {

		final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

		return screenshot;

	}

	@ClassRule
	public TestWatcher screenshotOnFailure = new TestWatcher() {
		@Override
		protected void failed(Throwable e, Description description) {
			makeScreenshotOnFailure();
		}

		@Attachment("Screenshot on failure")
		public byte[] makeScreenshotOnFailure() {
			Log.INFO("Taking screenshot");
			return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		}
	};

	/**
	 * ATTACH SNAPSHOT TO REPORT
	 */
	public void attachSnapshotToReport() {

		Log.INFO("Add snapshot to report");

		Path content = null;
		String snapshotFileName = null;
		try {
			snapshotFileName = takeScreenShot();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		content = Paths.get(getSnapshotFolderPath() + "/" + snapshotFileName);
		try (InputStream is = Files.newInputStream(content)) {
			Allure.addAttachment(snapshotFileName, is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Method to check if an element is present
	 *
	 * @param selectorType : String : Locator type (id, name, class, xpath, css)
	 * @param accessName   : String : Locator value
	 * @return boolean
	 */
	public boolean isElementPresent(SelectorType selectorType, String accessName) {
		try {
			element = wait.until(ExpectedConditions.presenceOfElementLocated(getElementByType(selectorType, accessName)));
			return true;
		} catch (TimeoutException | NoSuchElementException e) {
			return false;
		}
	}

	public boolean selectValueFromDropdown(SelectorType selectorType, String accessName, String valueToSelect, String message) {

		try {
			Select dropdown = new Select(browser.getWebelement(selectorType, accessName));
			dropdown.selectByVisibleText(valueToSelect);
			return true;
		} catch (NoSuchElementException | TimeoutException | StaleElementReferenceException e) {
			Log.ERROR("Unable to select value from the " + message + " dropdown");
			return false;
		}
	}

	public List<String> getAllDropdownValues(SelectorType selectorType, String accessName, String message) {
		List<String> dropdownValues = new ArrayList<>();

		try {
			Select dropdown = new Select(browser.getWebelement(selectorType, accessName));
			List<WebElement> allOptions = dropdown.getOptions();

			if (allOptions.isEmpty()) {
				Log.INFO("No values found in the " + message + " dropdown");
			} else {
				for (WebElement option : allOptions) {
					dropdownValues.add(option.getText());
				}
			}
		} catch (NoSuchElementException | TimeoutException | StaleElementReferenceException e) {
			Log.ERROR("Unable to get values from the " + message + " dropdown");
		}

		return dropdownValues;
	}


	public boolean isElementClickable(SelectorType selectorType, String selector) {
		try {
			WebElement element = getWebelement(selectorType, selector);

			// Check if the element has any event listeners attached
			Object eventListenersObj = ((JavascriptExecutor) driver).executeScript("return window.getEventListeners(arguments[0]).length > 0", element);
			boolean hasEventListeners = (eventListenersObj instanceof Boolean) && (Boolean) eventListenersObj;

			// Check if the element has pointer-events set to none
			Object pointerEventsObj = ((JavascriptExecutor) driver).executeScript("return getComputedStyle(arguments[0]).getPropertyValue('pointer-events');", element);
			String pointerEvents = (pointerEventsObj instanceof String) ? (String) pointerEventsObj : "";
			boolean pointerEventsNone = "none".equals(pointerEvents.trim());

			// If the element has event listeners or pointer-events set to none, it's not clickable
			return !(hasEventListeners || pointerEventsNone);
		} catch (Exception e) {
			// If an exception occurs, consider the element as not clickable
			return false;
		}
	}


}
