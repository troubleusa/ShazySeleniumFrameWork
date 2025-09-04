package com.qa.opencart.tests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPagetest extends BaseTest{
  
		
	@Test
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		ChainTestListener.log("login page tite: "+ actTitle);
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
	}


	@Test
	public void loginPageURLTest() {
		String actURL = loginPage.getLoginPageURL();
		ChainTestListener.log("login page url: "+ actURL);         
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
	}

	@Test
	public void isForgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}

	@Test
	public void isLoginPageHeaderExistTest() {
		Assert.assertTrue(loginPage.isheaderExist());
	}


	@Test(priority = Integer.MAX_VALUE)
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

}
