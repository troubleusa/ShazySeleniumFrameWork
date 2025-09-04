package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.ProductInfoPage;

public class ProductInfoTest extends BaseTest{

	
	//@BT(chrome+url) -> @BC(login) -> @test
	@BeforeClass
//	public void ProdInfoSetup() throws InterruptedException {
//
//	accPage = loginPage.doLogin("march2024@open.com", "Selenium@12345");
//	}
//	@Test
//	public void productHeaderTest() {
//	searchResultsPage = accPage.doSearch("macbook");
//	productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
//	String actHeader = productInfoPage.getProductHeader();
//	Assert.assertEquals(actHeader, "MacBook Pro");
//	}
//	
//	
//	@Test
//	public void productImagesCountTest() {
//	//doSearch() method is already available on the AccountsPage, so we can call that method using the Account Page object reference
//	searchResultsPage = accPage.doSearch("samsung");
//	productInfoPage = searchResultsPage.selectProduct("Samsung SyncMaster 941BW");
//	int actImagesCount = productInfoPage.getProductImages();
//	Assert.assertEquals(actImagesCount, 1);
//	}
//	@Test
//
//
//	public void productToCartTest() {
//
//
//	shoppingCartPage = productInfoPage.doAddToCart("5");
//
//
//	}

//	//BT(chrome+url) --> BC(login) --> @Test
//	
//		@BeforeClass
		public void prodInfoSetup() {
			accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
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

//		
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
		@Test
		public void getProductDataInfo() {
			searchResultsPage = accPage.doSearch("macbook");
			productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
			Map<String, String> productDataMap = productInfoPage.getProductData();
			SoftAssert softAssert = new SoftAssert();
			
	//	softAssert.assertEquals(productDataMap.get("productname"), "MacBook Pro");
			
			softAssert.assertEquals(productDataMap.get("Brand"), "Apple");
			softAssert.assertEquals(productDataMap.get("Availability"), "Out Of Stock");
			softAssert.assertEquals(productDataMap.get("Reward Points"), "800");
			softAssert.assertEquals(productDataMap.get("Product Code"), "Product 18");

			softAssert.assertEquals(productDataMap.get("productprice"), "$2,000.00");
			softAssert.assertEquals(productDataMap.get("extaxprice"), "$2,000.00");

			softAssert.assertAll();
			
		}
		
		//AAA pattern -- Arrange Act Assert
		// we can have multiple soft assertions in a single test case
		//but only one hard assert in the test case
		
		

	}