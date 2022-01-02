package com.orangehrm.hybridframe_2021.testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.orangehrm.hybridframe_2021.pageObject.DashboardPage;
import com.orangehrm.hybridframe_2021.pageObject.LoginPage;
import com.orangehrm.hybridframe_2021.testBase.TestBase;

public class LoginTC01 extends TestBase {

	@Test
	public void loginToOrangeHRMTC01() throws InterruptedException {
		LoginPage lp = new LoginPage(driver);
		String uname = excelDataProvider.getStringCellData("Sheet1", 1, 0);
		String pwd = excelDataProvider.getStringCellData("Sheet1", 1, 1);

		lp.setUserName(uname);
		lp.setPassword(pwd);

		DashboardPage dp = lp.clickOnLoginBtn();
		if (driver.findElement(By.xpath("//a[@id='menu_dashboard_index']")).isDisplayed()) {
			Assert.assertTrue(true);
			dp.logoutOrangeHRM();

		} else {
			Assert.assertFalse(false, "Dashboard page title does not contain mentioned text");
		}
	}

}
