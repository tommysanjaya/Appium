package org.Appium.pageObjects.android;

import java.util.List;

import org.Appium.utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class pageCatalogue extends AndroidActions{
	
	//multi level inheritance, AppiumUtils -> AndroidActions -> pageCatalogue
	
	AndroidDriver driver;
	
	@AndroidFindBy (id="com.androidsample.generalstore:id/productAddCart")
	private List<WebElement> addToCart;
	//driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(0).click();
	
	@AndroidFindBy (id="com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement ButtonCart;
	//driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
	
	public pageCatalogue(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public void AddItemByIndex(int index) {
		addToCart.get(index).click();;
	}
	
	public cartPage ClickButtonCart() {
		ButtonCart.click();
		return new cartPage(driver);
	}

}
