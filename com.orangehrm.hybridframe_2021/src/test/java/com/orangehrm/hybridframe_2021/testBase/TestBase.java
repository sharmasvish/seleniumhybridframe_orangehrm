package com.orangehrm.hybridframe_2021.testBase;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.orangehrm.hybridframe_2021.utility.ConfigDataProvider;
import com.orangehrm.hybridframe_2021.utility.ExcelDataProvider;
import com.orangehrm.hybridframe_2021.utility.Helper;

import io.github.bonigarcia.wdm.WebDriverManager;




public class TestBase {
	
	
	public static WebDriver driver = null;
	public static ConfigDataProvider configDataProvider = null;
	public static ExcelDataProvider excelDataProvider = null;
	
	public static String configDataPath = "./Config/config.properties";
	public static String excelDataPath = "./Data/TestData1.xlsx";
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extentReport;
	public ExtentTest extentTest;
	
	@BeforeSuite
	public void init() {
	 configDataProvider = new ConfigDataProvider(configDataPath);
	 excelDataProvider = new ExcelDataProvider(excelDataPath);

	 String currentDir = System.getProperty("user.dir") + "//Reports//orangehrm_" 
	 + Helper.getCurrentDateTime()+ ".html";
	 
	 htmlReporter = new ExtentHtmlReporter(currentDir);
	 
	 htmlReporter.config().setDocumentTitle("Automation Test Reports");
	 htmlReporter.config().setReportName("Regression Test Reports");
	 htmlReporter.config().setTheme(Theme.STANDARD);
	 
	 extentReport = new ExtentReports();
	 extentReport.attachReporter(htmlReporter);
	 
	 extentReport.setSystemInfo("HostName", "Local Host");
	 extentReport.setSystemInfo("OS", "Windows");
	 extentReport.setSystemInfo("Browser", " Chrome");
	 extentReport.setSystemInfo("TE", " Ravi");
	 extentReport.setSystemInfo("Test Case", "RT");
	 
	}
	
	
	
	
	@BeforeMethod
	@Parameters({"browser"})
	public void setUP(@Optional("Chrome")String browser)  {
		
		if(browser.equals("Chrome")) {
			//System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
		WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browser.equals("Firefox")) {
			//System.setProperty("webdriver.gecko.driver", "./Driver/geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		
		else if(browser.equals("IE")) {
			//System.setProperty("webdriver.gecko.driver", "./Driver/geckodriver.exe");
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
		
		else {
			System.out.println("Browser does not match with expected browser launch");
			}
		
		driver.manage().window().maximize();
		driver.get(configDataProvider.getUrl());
		
		}
		@AfterMethod
		public void tearDown(ITestResult result) throws IOException {
			if(result.getStatus()==ITestResult.FAILURE) {
				//Helper.captureScreenshots(driver);
				
		extentTest.log(Status.FAIL, "TEST CASE FAILED"+   result.getName());
		extentTest.log(Status.FAIL, "TEST CASE FAILED"+   result.getThrowable());

		//String screenshotpath = Helper.captureScreenshots(driver);
		//extentTest.addScreenCaptureFromPath(screenshotpath);
		
		//System.out.println(screenshotpath);
		extentTest.fail("Test Case Failed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshots(driver)).build());
			}
			
			
			else if (result.getStatus()==ITestResult.SKIP) {
				extentTest.log(Status.SKIP, "TEST CASE FAILED"+   result.getName());
				
			}
			
			else if (result.getStatus()==ITestResult.SUCCESS) {
				extentTest.log(Status.PASS, "TEST CASE SUCCESS"+   result.getName());
				
			}
			//driver.quit();
		}
		
		@AfterTest
		public void flushReports() {
			extentReport.flush();
			driver.quit();
		}
		
}