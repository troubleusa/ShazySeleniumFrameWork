package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ShoppingCartPage {
	private WebDriver driver;

	private ElementUtil eleUtil;

	//1. Create the constructor of the page

	public ShoppingCartPage(WebDriver driver) {

	this.driver = driver;

	eleUtil = new ElementUtil(driver);



	}

	//2. create the private By locator

	private final By header = By.xpath("//*[@id=\"checkout-cart\"]/ul/li[2]/a");


	public String getShoppingCartHeader() {
	System.out.println("opened the new page");
	String headerVal = eleUtil.waitForElementVisible(header, AppConstants.DEFAULT_SHORT_WAIT).getText();

	System.out.println("product header is --->" + headerVal);

	return headerVal;

	}

	}
	