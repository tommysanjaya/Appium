package org.Appium.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AndroidActions extends AppiumUtils{
	
	AndroidDriver driver;
	
	public AndroidActions(AndroidDriver driver) {
//		super();
		this.driver = driver;
//		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public void LongPressAction(WebElement ele) {
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture"
				, ImmutableMap.of("elementId",((RemoteWebElement)ele).getId(),"duration",2000));
	}
	
	public void ScrollToEnd() {
		boolean canScrollMore;
		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
				    "left", 100, "top", 100, "width", 200, "height", 200,
				    "direction", "down",
				    "percent", 3.0
				));	
		}while(canScrollMore);		
	}
	
	public void ScrollToText(String text) {
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector())"
				+ ".scrollIntoView(text(\""+text+"\"));"));
	}
	
	public void SwipeAction(WebElement firstImage, String direction) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement)firstImage).getId(),
			    "direction", direction,
			    "percent", 0.75
			));
	}
	
}
