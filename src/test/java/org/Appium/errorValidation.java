package org.Appium;

import org.Appium.TestUtils.BaseTest;
import org.Appium.pageObjects.android.formPage;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.testng.Assert;

public class errorValidation extends BaseTest{
	
	@Test(groups = {"Smoke"})
	public void homeErrorValidation() {
		
		formPage formPage = new formPage(driver);
		formPage.setActivities();
		
		formPage.setGender("male");
		formPage.submitForm();
		formPage.checkAlert();
		Assert.assertEquals(formPage.checkAlert(), "Please enter your name");
		
	}
	
}
