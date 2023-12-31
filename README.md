# Appium
Appium is an open-source automation tool for mobile applications. It allows developers and testers to automate the testing of mobile applications on different platforms (iOS, Android) using a single codebase written in various programming languages. Appium supports both native and hybrid mobile apps and provides a consistent API, making it easier to write tests that work across different mobile platforms.

Tech Stack Used for this Mobile Automation:
- IDE: Eclipse
- Framework: Appium, Selenium, TestNG
- Language: Java
- Supporting Application: AppiumInspector, AndroidStudio

## Initial Setup:
1.	Install Java, Android Studio, Node
2.	Setup Java, Android SDK, Node Home path in Windows System (Environment Variable)
3.	Setup Android Virtual Device on Android Studio
4.	Install Appium via CMD (npm install -g Appium)
5.	Install Eclipse
6.	Install Appium Inspector
   
## Pre-Automation
1.	Create Maven Project (maven-archetype-quickstart)
2.	On Eclipse, Add Maven Repository / Dependencies to pom.xml file:
    - Java Client (io.appium)
    - TestNG
    - Selenium Support
3. On Eclipse, Install "TestNG for Eclipse" Extention (Open Help -> Eclipse Marketplace -> Search TestNG for Eclipse -> Click Install)
4.	Install via CMD: 
    - For Android: uiautomator2 -> “appium driver install uiautomator2”
    - For iOS: xcuitest -> “appium driver install xcuitest”
5.	On Eclipse create new Class in src/test/java folder -> Name it as “BaseTest”, and add @BeforeClass annotation (So for every test we run, this BeforeClass will run first)
6.	Set the Device:
    - UiAutomator2Options options = new UiAutomator2Options()
    - options.setDeviceName("deviceNameInAndroidStudio")
7.	Set the APK:
    - Under src/test/java folder create a new folder resource
    - Put the apk file in resource folder
    - options.setDeviceName(path to apk file)
8.	Create new AndroidDriver object -> AndroidDriver driver = new AndroidDriver(url,options)
9.	Create Method inside the BaseTest named TearDown(); Inside this put driver.quit(); And add @AfterClass (for every test we run, at the end this AfterClass will be executed)
10.	Start the Appium Server via CMD (“appium”)
11.	Open and Configure Appium Inspector:
    - Set the App location
    - Set the Device
    - Set the Platform
    - Set automationName (UiAutomator2)
12.	Click Start to Start Inspecting Element on Android Application

## Start your Automation:
1.	Create a Java Class in src/test/java -> this will be our test case
2.	In the class, add Extends BaseTest. To inherit all the setup we need to run the Test Case
3.	inside the class add @Test annotation
4.	Start to Create the Automation Script (driver.findElementBy)
5.	To run the test case, Click Run As -> TestNG Test

## Glossary
1.	Android Studio: IDE that provides Android Virtual Device (will be used demonstrate the Automation)
2.	Appium Inspector: To inspect element for Android Application
3.	Appium: Open-Source automation tools
4.	Eclipse: IDE where we will store all the Automation Codes + setup
5.	Java: Programming Languange we use to Create the Automation
6.	Maven: tool that helps Java developers easily build and manage their projects.
7.	Node: Javascript Runtime (to Install the Appium)
8.	Selenium: Framework for Web Automation
9.	TestNG: Test Framework for Java.
10.	Uiautomator2: Framework for Android Mobile Automation
11.	Xcuitest: Framework for iOS Mobile Automation

## Explanation for Each Folder/Package/File:
```
Project-Name
	|
	|_src/main/java
	|	|_org.Appium.pageObject.android
	|	|	|_cartPage.java
	|	|	|_formPage.java
	|	|	|_pageCatalogue.java
	|	|_org.Appium.resource
	|	|	|_data.properties
	|	|_org.Appium.utils
	|		|_AndroidActions.java
	|		|_AppiumUtils.java
	|_src/test/java
	|	|_org.Appium
	|	|	|_checkout.java
	|	|	|_checkoutExperimen.java
	|	|	|_errorValidation.java
	|	|_org.Appium.resources
	|	|	|_General-Store.apk
	|	|_org.Appium.TestData
	|	|	|_checkout.json
	|	|_org.Appium.TestUtils
	|	|	|_BaseTest.java
	|	|	|_ExtentReporterNG.java
	|	|	|_Listener.java
	|_reports
	|	|_index.html
	|_testNGSuites
	|	|_testng_smoke.xml
	|	|_testng.xml
	|_test-output
	|_pom.xml
```
1.	src/main/java: Folder to put all your Main Source Code
    - org.Appium.pageObject.android: Package to put all Object/Element per page
    - org.Appium.resource: Package to put resources (ipAddress, port, DeviceName, etc)
    - org.Appium.utils:
        - AndroidActions: File to put Method of commonly used Android Actions (Scroll, Long Press, etc)
        - AppiumUtils: File to put Method of commonly used feature (Starting Appium Server via Code, getJsonData, getScreenshotPath, etc)
2.	src/test/java: Folder to put all your Test Code
    - org.Appium: Package to put all of your test cases
    - org.Appium.resources: Package to put your resource (Apk, etc)
    - org.Appium.TestData: Package to put your Test data (If want to execute TDD)
        - checkout.json: File to put data that will be executed while running the test case
    - org.Appium.TestUtils: Package to put all important stuff to support the Test
        - BaseTest: File to put all the Important Setup (Device, Apps, @BeforeClass, @AfterClass)
        - ExtentReporterNG: File to help generate ExtentReport after executing the test case
        - Listener: File to help generate ExtentTest and Help to run testNGSuites
3.	reports: Folder to put the Test Result generated by ExtentReport
4.	testNGSuites: Folder to put a which group of tests will be executed
    - testing_smoke: File to put only selected test case to execute (Smoke test)
    - testing: File to put all test cases (Regression test)
5.	test-output: Folder to put all Test report
6.	Pom.xml: File to put all the Dependencies / Repositories
