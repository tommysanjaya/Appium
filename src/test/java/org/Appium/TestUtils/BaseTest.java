package org.Appium.TestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

import org.Appium.utils.AppiumUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class BaseTest extends AppiumUtils{
	
	public AndroidDriver driver;
	
	@BeforeClass(alwaysRun = true)
	public void ConfigureAppium() throws IOException{
		//to get the data from data properties
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\org\\Appium\\resources\\data.properties");

		//required if want to execute using maven + choosing ipAddress from cmd
		String ipAddress = System.getProperty("ipAddress")!=null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress");
		prop.load(fis);
		System.out.println(ipAddress);
		//String ipAddress = prop.getProperty("ipAddress");
		String port = prop.getProperty("port");
		String deviceName = prop.getProperty("AndroidDeviceName");
		
		//start the appium server:		
		server = startAppiumServer(ipAddress, Integer.parseInt(port));
		
		//add device and apk
		UiAutomator2Options options = new UiAutomator2Options();
		//emulator device (android studio)
		options.setDeviceName(deviceName);
		
		//real device
		//options.setDeviceName("Android Devices");
		
		options.setApp(System.getProperty("user.dir")+"\\src\\test\\java\\org\\Appium\\resources\\General-Store.apk");
		
		//set the chomedriver for hybrid apps
		options.setChromedriverExecutable("C:\\Users\\tommy\\eclipse-workspace\\chromedriver.exe");
		
		//Create AndroidDriver first
		driver = new AndroidDriver(server.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	}
	
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		//quit apps
		driver.quit();
		//stop the appium server
		server.stop();
	}

}
