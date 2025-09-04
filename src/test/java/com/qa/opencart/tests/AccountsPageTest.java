package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accPageSetup() {
		accPage = loginPage.doLogin("march2024@open.com", "Selenium@12345");
	}
	
	@DataProvider
	public Object[][] getProducts() {
		return new Object[][] {
			{"macbook", "MacBook Pro"},
			{"samsung", "Samsung SyncMaster 941BW"},
			{"imac", "iMac"},
			{"canon", "Canon EOS 5D"}
		};
	}
		
	@Test(dataProvider = "getProducts")
	public void productHeaderTest(String searchKey, String productName) {
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		String actHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(actHeader, productName);
	}

	
	@DataProvider
	public Object[][] getProductImages() {
		return new Object[][] {
			{"macbook", "MacBook Pro", 4},
			{"samsung", "Samsung SyncMaster 941BW", 1},
			{"canon", "Canon EOS 5D", 3}
		};
	}
	
	
	@Test(dataProvider = "getProductImages")
	public void productImagesCountTest(String searchKey, String productName, int imageCount) {
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		int actImagesCount = productInfoPage.getProductImages();
		Assert.assertEquals(actImagesCount, imageCount);
	}
	
	
	

//	@Test
//	public void isLogoutLinkExistTest() {
//		Assert.assertTrue(accPage.isLogoutLinkExist());
//	}
//	
//	@Test
//	public void accPageHeadersTest() {
//		List<String> actHeadersList = accPage.getAccPageHeaders();
//		Assert.assertEquals(actHeadersList.size(), AppConstants.ACC_PAGE_HEADERS_COUNT);
//		Assert.assertEquals(actHeadersList, AppConstants.expectedAccPageHeadersList);
//	}
//	
}
