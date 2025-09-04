package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.ShoppingCartPage;

public class ShoppingCartTest extends BaseTest{
	



	@BeforeClass

	public void ShoppingCartSetup() throws InterruptedException {

	//perform the login

	accPage = loginPage.doLogin("march2024@open.com", "Selenium@12345");

	}


	@Test


	public void productHeaderTest() {

	searchResultsPage = accPage.doSearch("macbook");

	productInfoPage = searchResultsPage.selectProduct("MacBook Pro");


	String actHeader = productInfoPage.getProductHeader();

	Assert.assertEquals(actHeader, "MacBook Pro");



	}



	@Test


	public void productImagesCountTest() {


	searchResultsPage = accPage.doSearch("samsung");

	productInfoPage = searchResultsPage.selectProduct("Samsung SyncMaster 941BW");


	int actImagesCount = productInfoPage.getProductImages();

	Assert.assertEquals(actImagesCount, 1);




	}





	@Test


	public void productToCartTest() {

   ShoppingCartPage shoppingCartPage = productInfoPage.doAddToCart("5");


	}


	@Test

	public void shoppingCartHeaderTest() {

	String actHeader = shoppingCartPage.getShoppingCartHeader();

	Assert.assertEquals(actHeader, "Shopping Cart");

	}



	}