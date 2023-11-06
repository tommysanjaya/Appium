package org.Appium.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public abstract class AppiumUtils {
	
	public AppiumDriverLocalService server;
	
	public AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {
		//start the appium server:
		//Method 1<< NOT WORKING
//		AppiumDriverLocalService server = new AppiumServiceBuilder()
//				.withAppiumJS(new File("\\Users\\tommy\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
//				.withIPAddress(ipAddress).usingPort(port).build();
//				.withIPAddress("127.0.0.1").usingPort(4723).build();
//		server.start();
//		return server;
				
		//Method 2<< Currently Working
		HashMap<String, String> environment = new HashMap();
	     environment.put("PATH", "/usr/local/bin:" + System.getenv("PATH"));
	     AppiumServiceBuilder builder = new AppiumServiceBuilder();
	     builder
	             .withAppiumJS(new File("\\Users\\tommy\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
	             .usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
	             .usingPort(4723)
	             .withEnvironment(environment)
	             .withArgument(GeneralServerFlag.LOCAL_TIMEZONE)
	             .withLogFile(new File("AppiumLog.txt"));
	     server = AppiumDriverLocalService.buildService(builder);
	     System.out.println("Server started at :"+server.getUrl());
	     server.start();
	     return server;
	}

	public void waitForElementAppear(WebElement ele, AppiumDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains((ele), "text", "Cart"));
	}
	
	public Double getFormattedAmount(String amount) 
	{
		Double price = Double.parseDouble(amount.substring(1));
		return price;
	}
	
	public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException{
		//convert data from json file into string
		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath),StandardCharsets.UTF_8);
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data =  mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String,String>>>() {
				});
		
		return data;
		}
			
	public String getScreenshotPath (String testCaseName, AppiumDriver driver) throws IOException {
		File source = driver.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir")+"\\reports"+testCaseName+".png";
		FileUtils.copyFile(source, new File (destinationFile));
		return destinationFile;
	}
}
