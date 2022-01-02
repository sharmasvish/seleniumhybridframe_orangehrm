package com.orangehrm.hybridframe_2021.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;

	public LoginPage(WebDriver ldriver) {
		this.driver = ldriver;
		PageFactory.initElements(driver, this);
	}

	// Object Repository

	@FindBy(name = "txtUsername")
	@CacheLookup
	WebElement username;

	@FindBy(name = "txtPassword")
	@CacheLookup
	WebElement password;

	@FindBy(id = "btnLogin")
	@CacheLookup
	WebElement loginBtn;

	public void setUserName(String uname) {
		username.sendKeys(uname);
	}

	public void setPassword(String pwd) {
		password.sendKeys(pwd);
	}

	public DashboardPage clickOnLoginBtn() {
		try {
			loginBtn.click();
			return new DashboardPage(driver);

		} catch (Exception e) {
			return null;
		}
	}
}
