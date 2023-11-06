package org.Appium;

import static org.testng.Assert.assertEquals;

import java.awt.RenderingHints.Key;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.Appium.TestUtils.BaseTest;
import org.Appium.pageObjects.android.cartPage;
import org.Appium.pageObjects.android.formPage;
import org.Appium.pageObjects.android.pageCatalogue;
import org.Appium.utils.AppiumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.collections.Objects;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import net.bytebuddy.asm.Advice.Enter;

public class checkout extends BaseTest{
	
	@BeforeMethod
	public void preSetup() {
		formPage formPage = new formPage(driver);
		formPage.setActivities();
	}
	
	@Test
	public void transactionDemo() throws InterruptedException{
		
		formPage formPage = new formPage(driver);
		formPage.setNameField("Tommy Test");
		formPage.setGender("female");
		formPage.chooseCountry("Argentina");
		formPage.submitForm();
		
		pageCatalogue pageCatalogue = new pageCatalogue (driver);
		pageCatalogue.AddItemByIndex(0);
		pageCatalogue.AddItemByIndex(1);
		pageCatalogue.ClickButtonCart();
		
		cartPage cartPage = new cartPage (driver);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.
				attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title"))
						, "text", "Cart"));
		
		double totalPrice = cartPage.getPrice();
		double displayedPrice = cartPage.getDisplayedTotalPrice();
		Assert.assertEquals(totalPrice, displayedPrice);
		
		cartPage.acceptTermsConditions();
		cartPage.submitOrder();
	
		
//		Thread.sleep(2000);
//		
//		//get the web context name before changing into web
//		Set<String> context1 = driver.getContextHandles(); 
//		for(String contextName : context1) {
//			System.out.println(contextName);
//		}
//		
//		//change into hybrid (web)
//		driver.context("WEBVIEW_com.androidsample.generalstore"); 
//		driver.findElement(By.name("q")).sendKeys("Test Automation");
//		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
//		
//		//back to native apps
//		driver.pressKey(new KeyEvent(AndroidKey.BACK));
	}
	
	@Test(dataProvider = "getDataJson")
	public void transactionWithDataDriven(HashMap<String,String> input) throws InterruptedException{
	//for data provider using json
		
	//for data provider input manually
	//public void transactionWithDataDriven(String name, String gender, String country) throws InterruptedException{
		
		formPage formPage = new formPage(driver);
		formPage.setNameField(input.get("name"));
		formPage.setGender(input.get("gender"));
		formPage.chooseCountry(input.get("country"));
		formPage.submitForm();
		
		pageCatalogue pageCatalogue = new pageCatalogue (driver);
		pageCatalogue.AddItemByIndex(0);
		pageCatalogue.AddItemByIndex(1);
		pageCatalogue.ClickButtonCart();
	}
	
//	@DataProvider
//	public Object[][] getData() {
//		//input data manually
//		//return new Object [][] { {"Test data driven", "male", "Argentina"},{"data driven 2", "female", "Australia"} };
//	}
	
	@DataProvider
	public Object[][] getDataJson() throws IOException{
		//import data from json
		List<HashMap<String,String>> data = getJsonData(System.getProperty("user.dir")+"\\src\\test\\java\\org\\Appium\\TestData\\checkout.json");
		return new Object[][] { {data.get(0)}, {data.get(1)} };
	}
	
}
	
	
