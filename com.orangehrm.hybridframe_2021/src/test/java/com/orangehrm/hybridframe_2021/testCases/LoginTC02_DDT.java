package com.orangehrm.hybridframe_2021.testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orangehrm.hybridframe_2021.pageObject.LoginPage;
import com.orangehrm.hybridframe_2021.testBase.TestBase;

public class LoginTC02_DDT extends TestBase {

	@Test(dataProvider= "getTestData")
	public void validateLoginFunctionality(Object uname, Object pwd) {
		System.out.println(uname);
		System.out.println(pwd);
		
		String un=uname.toString();
		String pw=pwd.toString();
		
		System.out.println(un+"  "+pw);
		
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(un);
		lp.setPassword(pw);
		lp.clickOnLoginBtn();	
		
		if(driver.getPageSource().contains("DashBoard")) {
			Assert.assertTrue(true);
		}
		else if(driver.getPageSource().contains("LOGIN Panel")) {
			String invalidcredentials = driver.findElement(By.xpath("//span[text()='Invalid credentials']")).getText();
			Assert.assertTrue(false, invalidcredentials);
		}
	}
	
	@DataProvider
	public Object[][] getTestData(){
		Object[][] data =excelDataProvider.getExcelData(1);
		return data;
		//return excelDataProvider.getExcelData(1);
	}
	
}
