package org.Appium;

import static org.testng.Assert.assertEquals;

import java.awt.RenderingHints.Key;
import java.time.Duration;
import java.util.Set;

import org.Appium.TestUtils.BaseTest;
import org.Appium.pageObjects.android.formPage;
import org.Appium.pageObjects.android.pageCatalogue;
import org.Appium.utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import net.bytebuddy.asm.Advice.Enter;

public class checkoutExperimen extends BaseTest{
	
	@Test
	public void transactionDemo() throws InterruptedException{
				
		formPage formPage = new formPage(driver);
		formPage.setActivities();
		
		formPage.setNameField("Tommy Test");
		formPage.setGender("female");
		formPage.chooseCountry("Argentina");
		formPage.submitForm();
		
		pageCatalogue pageCatalogue = new pageCatalogue (driver);
		pageCatalogue.ScrollToText("Converse All Star");
//		pageCatalogue.countProductName();
		
		//add product 1
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector())"
				+ ".scrollIntoView(text(\"Converse All Star\"));"));		
		
		int productCount = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
		System.out.println("product count : "+productCount +"\n");
		
		for(int i = 0; i < productCount; i++) {
			String productName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
			System.out.println("product name : "+productName);
			
			if (productName.equalsIgnoreCase("Converse All Star")){
				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
			}
		}
		System.out.println();
		
		//add product 2
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector())"
				+ ".scrollIntoView(text(\"Jordan Lift Off\"));"));
		
		int productCount1 = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
		System.out.println("product count1 : "+productCount1 +"\n");
		
		for(int j = 0; j < productCount1; j++) {
			String productName1 = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(j).getText();
			System.out.println("product name1 : "+productName1); 
			
			if (productName1.equalsIgnoreCase("Jordan Lift Off")){
				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(j).click();
			}
		}
		
		System.out.println();
		
		//go to cart page
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.
//				visibilityOfElementLocated(By.id("com.androidsample.generalstore:id/toolbar_title")));
				attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title"))
				, "text", "Cart"));
		
		//count the total of product in the cart page
		int totalProduct = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
		System.out.println("tota product adalah: " +totalProduct);
		
		//array to store the name of the product
		String [] listlastProduct = new String [totalProduct];
		String [] sumPrice = new String [totalProduct];
		
		//storing the name of all the product + price in the cart page
		for(int i = 0; i < totalProduct; i++) {
			String lastProduct = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
			listlastProduct [i] = lastProduct;
			
			String Price = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i).getText();
			sumPrice[i] = Price;
		}
		
		//printing the list of product in the cart page
		for(int j = 0; j < totalProduct; j++) {
			System.out.println("list product di cart : " + listlastProduct[j]);
			Assert.assertEquals(listlastProduct[0], "Converse All Star");
//			Assert.assertEquals(listlastProduct[1], "Jordan Lift Off");
		}
		
		System.out.println();
		
		double storeTotalPrice = 0;
		
		//printing the list of price + sum
		for(int k = 0; k < totalProduct; k++) {
			System.out.println("list harga product : " + sumPrice[k]);
			
			//remove the dollar sign		
			double sumPriceTotal = Double.parseDouble(sumPrice[k].substring(1));
			
			storeTotalPrice += sumPriceTotal;
		}
		
		System.out.println();
		System.out.println("Total Harga: $" + storeTotalPrice);
		
		//assert the value of storeTotalPrice
		String totalAmount = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
		
		double cleanTotalAmount = Double.parseDouble(totalAmount.substring(1));
		System.out.println("Total Harga Sistem: $" + cleanTotalAmount);
		Assert.assertEquals(cleanTotalAmount, storeTotalPrice);
		
		//click the t&c using longpress
		WebElement ele = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
		AndroidActions androidAct = new AndroidActions(driver);
		androidAct.LongPressAction(ele);
		
		driver.findElement(By.xpath("//android.widget.Button[@text='CLOSE']")).click();
		driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
		Thread.sleep(2000);
		
		//get the web context name before changing into web
		Set<String> context1 = driver.getContextHandles(); 
		for(String contextName : context1) {
			System.out.println(contextName);
		}
		
		//change into hybrid (web)
		driver.context("WEBVIEW_com.androidsample.generalstore"); 
		driver.findElement(By.name("q")).sendKeys("Test Automation");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		
		//back to native apps
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
	}
}
