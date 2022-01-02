package com.orangehrm.hybridframe_2021.utility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Helper {

	public static String captureScreenshots(WebDriver driver) {
		
		
		String screenshotpath = System.getProperty("user.dir")+"\\Screenshot\\Orangehrm_"
		+ getCurrentDateTime()+".png";
		
	try {
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File(screenshotpath));
		System.out.println("Screenshot captured successfully");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return screenshotpath;	
	}
	
	public static String getCurrentDateTime() {
	SimpleDateFormat customDateTime = new SimpleDateFormat("MM_DD_YYYY_HH_MM_SS");
	Date currentDate = new Date();
	//System.out.println(currentDate);
	String dateFormat = customDateTime.format(currentDate);
	
	//System.out.println(dateFormat);
	
	return dateFormat;
	
	
		
	}
	

}
