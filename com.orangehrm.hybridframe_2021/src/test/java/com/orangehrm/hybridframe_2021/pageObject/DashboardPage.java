package com.orangehrm.hybridframe_2021.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class DashboardPage {
	
	WebDriver driver;
	public DashboardPage(WebDriver ldriver)
	{
		this.driver=ldriver;
		PageFactory.initElements(driver, this);
	}
	
	//Object Repository
	@FindBy(id="welcome")
	@CacheLookup
	WebElement userId;
	
	@FindBy(xpath="//a[text()='Logout']")
	@CacheLookup
	WebElement logoutLink;
	
	public void logoutOrangeHRM() throws InterruptedException {
	
			userId.click();
			Thread.sleep(5000);
			logoutLink.click();
			
			
	}
	
}


