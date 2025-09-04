package com.qa.opencart.pages;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;

	private ElementUtil eleUtil;
	
	private Map<String, String> productMap;

	//1. Create the constructor of the page

	public ProductInfoPage(WebDriver driver) {

	this.driver = driver;

	eleUtil = new ElementUtil(driver);



	}

	//2. create the private By locator

	private final By header = By.tagName("h1");

	private final By productImages = By.cssSelector("ul.thumbnails img");

	private final By quantity = By.id("input-quantity");

	private final By addToCartBtn = By.id("button-cart");

	private final By successMesg = By.xpath("//div [contains (@class, 'alert-dismissible')]");

	private final By prodtName = By.xpath("(//div [contains (@class, 'alert-dismissible')]//a)[1]");

	private final By shoppingCart = By.xpath("(//div [contains (@class, 'alert-dismissible')]//a)[2]");
	
	private final By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private final By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");

	//3. Create the public methods



	public String getProductHeader() {

	String headerVal = eleUtil.waitForElementVisible(header, AppConstants.DEFAULT_SHORT_WAIT).getText(); 

	System.out.println("product header is --->" + headerVal);

	return headerVal;

	}



	public int getProductImages() {

	int imagesCount = eleUtil.waitForElementsVisible(productImages, AppConstants.DEFAULT_MEDIUM_WAIT).size();

	System.out.println("Total number of image: " + imagesCount);

	return imagesCount;

	}

	//Enter quantity 5 on the quantity edit box


	public ShoppingCartPage doAddToCart(String numOfItems) {

	System.out.println("total number of item(s) ordered ---> : " + numOfItems);

	WebElement quantityEle = eleUtil.waitForElementVisible(quantity, AppConstants.DEFAULT_MEDIUM_WAIT);

	quantityEle.clear();

	quantityEle.sendKeys(numOfItems);

	String ItemsAdded = quantityEle.getAttribute("value");

	System.out.println("Number of items ordered : " + ItemsAdded);

	eleUtil.doClick(addToCartBtn);

	String mesg1 = eleUtil.waitForElementVisible(successMesg, AppConstants.DEFAULT_LARGE_WAIT).getText();//driver.findElement(successMesg).getText();

	String mesg2 = eleUtil.doElementGetText(prodtName);//driver.findElement(prodtName).getText();

	String mesg3 = eleUtil.doElementGetText(shoppingCart);//driver.findElement(shoppingCart).getText();

	System.out.println("Success Message Displayed :" + mesg1 + mesg2 + mesg3 );

	eleUtil.doClick(shoppingCart);

	eleUtil.waitForURLContains("route=checkout/cart", AppConstants.DEFAULT_SHORT_WAIT);

	return new ShoppingCartPage(driver);



	}
      public Map<String , String> getProductData() {
    	  productMap=new TreeMap<String , String>();
    	  productMap.put("getProductname", getProductHeader());
    	  productMap.put("productimages", String.valueOf(getProductImages()));
    	  getProductMetaData();
   		  getProductPriceData();
    	 System.out.println("==============ProductData============: \n" + productMap);
    	
    	 return productMap;
    	 
      }

//  	Brand: Apple
//  	Product Code: Product 18
//  	Reward Points: 800
//  	Availability: Out Of Stock
  	private void getProductMetaData() {
  		List<WebElement> metaList = eleUtil.waitForElementsVisible(productMetaData, AppConstants.DEFAULT_MEDIUM_WAIT);
  		System.out.println("total meta data: " + metaList.size());

  		for (WebElement e : metaList) {
  			String metaData = e.getText();
  			String meta[] = metaData.split(":");
  			String metaKey = meta[0].trim();
  			String metaValue = meta[1].trim();
  			productMap.put(metaKey, metaValue);
  		}

   }
  	
  	private void getProductPriceData() {
  		List<WebElement> priceList= eleUtil.waitForElementsPresence(productPriceData, AppConstants.DEFAULT_MEDIUM_WAIT);
  		System.out.println("total price data: " + priceList.size());// 2
  		String priceValue= priceList.get(0).getText();
  		String exTaxValue =priceList.get(1).getText().split(":")[1].trim();
		productMap.put("productprice", priceValue);
		productMap.put("extaxprice", exTaxValue);

	}
  	}
     
	



