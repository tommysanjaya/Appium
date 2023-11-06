package org.Appium.pageObjects.android;

import org.Appium.utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class formPage extends AndroidActions{
	
	AndroidDriver driver;
	
	public formPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy (id="com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	 //driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Tommy Test");
	
	@AndroidFindBy (id="com.androidsample.generalstore:id/radioFemale")
	private WebElement femaleOptions;
	//driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
	
	@AndroidFindBy (id="com.androidsample.generalstore:id/radioMale")
	private WebElement maleOptions;
	//driver.findElement(By.id("com.androidsample.generalstore:id/radioMale")).click();
	
	@AndroidFindBy (id="com.androidsample.generalstore:id/spinnerCountry")
	private WebElement countrySelection;
	//driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
	
	@AndroidFindBy (id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement shopButton;
	//driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
	
	@AndroidFindBy (xpath="//android.widget.Toast[@text='Please enter your name']")
	private WebElement alert;
	//driver.findElement(By.xpath("//android.widget.Toast[@text='Please enter your name']"));
	
	public void setActivities() {
		//start from home screen
		//to get the package name + activity name via cmd
		//adb shell dumpsys window | find "mCurrentFocus" < for windows
		
		Activity activity = new Activity ("com.androidsample.generalstore","com.androidsample.generalstore.MainActivity");
		driver.startActivity(activity);
	}
	
	public void setNameField(String name) {
		nameField.sendKeys(name);
	}
	
	public void setGender(String gender) {
		if(gender.contains("female")) {
			femaleOptions.click();
		}else if (gender.contains("male")){
			maleOptions.click();
		}	
	}
	
	public void chooseCountry(String country) {
		countrySelection.click();
		ScrollToText(country);
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+country+"']")).click();
	}
	
	public void submitForm() {
		shopButton.click();
	}
	
	public String checkAlert() {
		String alertText = alert.getText();
		return alertText;
	}

	

	
	
	
	
	
	
	
}
