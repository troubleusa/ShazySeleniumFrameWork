package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.CsvUtil;
import com.qa.opencart.utils.StringUtils;
import com.qa.opencart.base.BaseTest;



public class RegisterPageTest extends BaseTest{
	
	@BeforeClass
	public void goToregisterPage() {
	  registerPage= loginPage.navigateToRegisterPage();
	}
  
	@DataProvider
	public Object[][]  getRegData() {
		return new Object[][] {
			{"harpreet", "automation", "987878787777", "harpreet@123", "yes" },
			{"ratul", "shaha", "987878787766", "ratul@123", "no" },
			{"sandhya", "automation", "987878787755", "sandhya@123", "yes" },
		};
	}
	
	
	@DataProvider
	public Object[][] getRegCSVData() {
		return CsvUtil.csvData("Register");
	}
	
	
	@Test(dataProvider ="getRegCSVData")
	public void registerTest(String firstName, String lastName, String telephone, String password, String subscribe) {
		Assert.assertTrue
		(registerPage.userRegister(firstName, lastName, StringUtils.getRandomEMail(), telephone, password, subscribe));
	}
}
