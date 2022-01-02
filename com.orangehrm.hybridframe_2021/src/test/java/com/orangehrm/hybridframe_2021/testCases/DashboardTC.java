package com.orangehrm.hybridframe_2021.testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.orangehrm.hybridframe_2021.pageObject.DashboardPage;
import com.orangehrm.hybridframe_2021.pageObject.LoginPage;
import com.orangehrm.hybridframe_2021.testBase.TestBase;

public class DashboardTC extends TestBase{

	@Test
	public void loginToOrangeHrm() throws InterruptedException {
		//driver.out.println("Login to OrangeHRM");
		LoginPage lp=new LoginPage(driver);
		lp.setUserName("Admin");
		lp.setPassword("admin123");
		
		DashboardPage hp=lp.clickOnLoginBtn();
		if(driver.findElement(By.xpath("//a[@id='menu_dashboard_index']")).isDisplayed()) {
			Assert.assertTrue(true);
			hp.logoutOrangeHRM();
			
		}
		else {
			Assert.assertFalse(false, "Dashboard page title does not contain mentioned text");
		}
}
}
