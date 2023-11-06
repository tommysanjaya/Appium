package org.Appium.pageObjects.android;

import java.time.Duration;
import java.util.List;

import org.Appium.utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class cartPage extends AndroidActions{ 
	
	AndroidDriver driver;
	
	public cartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/toolbar_title")
	public WebElement titlePage;
	//driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title"))
	
	@AndroidFindBy (id="com.androidsample.generalstore:id/productName")
	private List<WebElement> productName;
	//driver.findElements(By.id("com.androidsample.generalstore:id/productName"))
	
	@AndroidFindBy (id="com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productPrice;
	//driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"))
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalPrice;
	//driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl"))
	
	@AndroidFindBy (id="com.androidsample.generalstore:id/termsButton")
	private WebElement termsButton;
	//driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"))
	
	@AndroidFindBy (xpath="//android.widget.Button[@text='CLOSE']")
	private WebElement closeButton;
	//driver.findElement(By.xpath("//android.widget.Button[@text='CLOSE']"))
	
	@AndroidFindBy (className="android.widget.CheckBox")
	private WebElement checkBox;	
	//driver.findElement(AppiumBy.className("android.widget.CheckBox"))
	
	@AndroidFindBy (id="com.androidsample.generalstore:id/btnProceed")
	private WebElement submitButton;
	//driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
	
//	public void waitForCartPage(WebElement titlePage) {
//		waitForElementAppear(titlePage);
//	}
	
	public double getPrice() {
		//get the sum price from all the product
		int count = productName.size();
		double sumPrice= 0;

		
		for(int i = 0; i < count; i++) {
			String Price = productPrice.get(i).getText();
			Double PriceFormat = getFormattedAmount(Price);
			sumPrice += PriceFormat;
		}
		System.out.println("Total Harga: $" + sumPrice);
		
		return sumPrice;
	}
		
	public double getDisplayedTotalPrice() {		
		//get the displayedTotalPrice by system
		String totalAmount = totalPrice.getText();
		double cleanTotalAmount = getFormattedAmount(totalAmount);
		
		System.out.println("Total Harga Sistem: $" + cleanTotalAmount);
		return cleanTotalAmount;
	}
	
	public void acceptTermsConditions() {
		LongPressAction(termsButton);
		closeButton.click();
	}
	
	public void submitOrder() {
		checkBox.click();
		submitButton.click();
	}
	
	public Double getFormattedAmount(String amount) 
	{
		Double price = Double.parseDouble(amount.substring(1));
		return price;
	}
}
