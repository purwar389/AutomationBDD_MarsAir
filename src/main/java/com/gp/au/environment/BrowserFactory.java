package com.gp.au.environment;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * 
 * @author Gaurav Purwar
 *
 */
public class BrowserFactory {

    private static final Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();

    /*
     * Factory method for getting browsers
     */
    public WebDriver getBrowser(String browserName) {
        WebDriver driver = null;

        switch (browserName) {
        case "Firefox":
            driver = drivers.get("Firefox");
            if (driver == null) {
                driver = new FirefoxDriver();
                drivers.put("Firefox", driver);
            }
            break;
        case "IE":
            driver = drivers.get("IE");
            if (driver == null) {
                System.setProperty("webdriver.ie.driver", "\\src\\main\\resources\\downloads\\IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                drivers.put("IE", driver);
            }
            break;
        case "Chrome":
            driver = drivers.get("Chrome");
            if (driver == null) {
                String driverPath = System.getProperty("user.dir") + "/src/main/resources/downloads/chromedriver";
                String chromeBinaryPath = "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome";
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("test-type");
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.setBinary(chromeBinaryPath);
                chromeOptions.addArguments("--disable-infobars");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-browser-side-navigation");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--remote-debugging-port=9222");
                chromeOptions.addArguments("--disable-extensions");
                chromeOptions.setExperimentalOption("useAutomationExtension", false);
                chromeOptions.addArguments("disable-features=VizDisplayCompositor");
                chromeOptions.addArguments("disable-features=NetworkService");

                // Set up the WebDriver service
                ChromeDriverService service = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File(driverPath))
                        .usingAnyFreePort()
                        .build();

                // Create the WebDriver instance
                driver = new ChromeDriver(service, chromeOptions);
                drivers.put("Chrome", driver);
            }
            break;
        }
        return driver;
    }

}