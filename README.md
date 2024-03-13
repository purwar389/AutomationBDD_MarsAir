# MarksAir Project - Automation
## Author: @Gaurav Purwar
## Selenium-Java-Cucumber-Allure-TestNG-Maven-extentReports-Spark
A test automation framework for Web UI based applications.

## Author: Gaurav Purwar


### Reports are published here:
#### View Cucumber Reports:

https://reports.cucumber.io/reports/7a43d0e6-2e4b-47f4-b698-10d9c1d762fa

#Allure Reports:
Run through command line: under project path
allure serve /Users/biluesparesydney8/Documents/WebAutomationTW/allure-results

#Introduction
The purpose of this document is to demonstrate Web Automation Testing Assessment for MarsAir Application using:
1. Core Java
2. Selenium-Webdriver
3. Cucumber-JVM (io.cucumber) BDD based with TestNG Layer
4. The approach of Object Creator Page
5. Maven
6. Allure test report tool 
7. Spark report
8. Extent Report
9. TestNG Report
10. Taking Screenshots if failure

## Functional Test Cases Automated in this framework

Application URL: https://marsair.recruiting.thoughtworks.net/GauravPurwar

User Story List: https://marsair.recruiting.thoughtworks.net/mission.html

All Test Cases : https://reports.cucumber.io/reports/e9f44d23-33d4-496d-9927-f48350abd197

Defects: https://marsair.recruiting.thoughtworks.net/GauravPurwar/issues



## Framework Requirements
* Java Development Kit
* Maven
* WebDriver Selenium
* cucumber-java, cucumber-testng
* org.testng 7.4.0
* cucumber-java
* allure-testng
* Maven
* Environment variables in your Operational System:
* PATH: The path of maven folder + _/bin_



## Automation Framework Structure

```
WebAutomationTW
	src.main.java
		com.gp.au
			environment package
			logger package
			methods package
			pageObjects package
			utils package
    src.main.resource
        downloads
        config.properties			
	src.test.java
		com.gp.test
		    featureDefinitions
                Hooks.java
                BasicSearchSteps.java
		com.gp.test
		    runner
			    TestRunner.java
	src.test.resources
		Features
			BasicSearch.feature
		properties
	allure-results
	spark-reports
	test-output
		testNG Reports
	target
		cucumber-reports
			index.html
	pom.xml
	TestNG.xml
		
```

## Key Highlights

* The framework structure has been defined considering the basic standard framework with possible folder structures 
* The framework supports all 4 user stories, total 46 test cases listed in the cucumber feature file *.feature
* This covers All Test Cases with all possible testng assertions [SoftAssertions] which validates the expected results
* The src.main.java packages contains entities, driver setup, logging and creator classes that represent the object retrieved to the WebDriver elements.
* TestRunner.java_ is the test of endpoint.
* Properties contains common settings such as the base url, browser versions, path variables.
* Data Driven from cucumber feature file
* Supports multiple browsers, running chrome browser and provisioned for Firefox and IE browser in BrowserFactory Class
* Generates Allure html Report
* Generates Cucumber html report
* Generates Spark html report
* tags = "@all", is set in TestRunner.java file


## Running Tests
* Clone the repository from your fork to this directory
* Open the project using any Java IDE
* We can run using different ways:
* Run using IDE simply trigger TestRunner.java
* Run the TestNG.xml
* Run using Maven command line the tests with the script below
* $ mvn clean install
* $ mvn test


## Test Results
* Test report automatically generated on target and reports folder after finished the test execution
* Allure Report generation
* * allure serve sourcePath\allure-results
* * run through command line: under project path
* * Example:
>allure serve /Users/biluesparesydney8/Documents/WebAutomationTW/allure-results


* Spark reports
> spark-reports/Spark.html

* Cucumber Reports - published
* Clicking on one of the published reports 
> https://reports.cucumber.io/reports/e9f44d23-33d4-496d-9927-f48350abd197


* TestNG Default Reports
> /test-output/emailable-report.html


## Screenshots:
/Screenshots folder - all screens are captured for evidence purposes



**cucumber Reports:**

>https://reports.cucumber.io/reports/e9f44d23-33d4-496d-9927-f48350abd197

<img width="1053" alt="Screenshot 2024-03-14 at 1 10 31 am" src="https://github.com/purwar389/AutomationBDD_MarsAir/assets/8833241/8a4897b4-dcff-49e2-ae77-6feeada44b57">


**Run time Allure report:**
Sample Allure report with pass fail stats:

<img width="1048" alt="Screenshot 2024-03-14 at 1 10 53 am" src="https://github.com/purwar389/AutomationBDD_MarsAir/assets/8833241/d60230c4-79c9-41c0-b5d4-c9bd5ccbde9a">

>allure serve /Users/biluesparesydney8/Documents/WebAutomationTW/allure-results

Extent Cucumber spark html report:

<img width="1045" alt="Screenshot 2024-03-14 at 1 11 19 am" src="https://github.com/purwar389/AutomationBDD_MarsAir/assets/8833241/5955bd06-9c72-44ff-9af6-4055cea180a1">

TestNG Emailable Report:

<img width="1048" alt="Screenshot 2024-03-14 at 1 16 35 am" src="https://github.com/purwar389/AutomationBDD_MarsAir/assets/8833241/2fdac84e-e135-4906-9a6a-f108ed1096be">

Console

<img width="1044" alt="Screenshot 2024-03-14 at 1 17 05 am" src="https://github.com/purwar389/AutomationBDD_MarsAir/assets/8833241/bb56dfcc-bcaf-4d7e-925d-3c53790c2490">

Project Folder Structure Screen:

<img width="424" alt="Screenshot 2024-03-14 at 1 17 49 am" src="https://github.com/purwar389/AutomationBDD_MarsAir/assets/8833241/8c81d432-f226-4279-abf6-a6a788bb29d1">
