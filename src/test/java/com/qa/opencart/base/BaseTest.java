package com.qa.opencart.base;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;


 @Listeners(ChainTestListener.class)

	public class BaseTest {

		protected WebDriver driver;
		protected Properties prop;
		DriverFactory df;
		protected LoginPage loginPage;
		protected AccountsPage accPage;
		protected SearchResultsPage searchResultsPage;
		protected ProductInfoPage productInfoPage;
		protected RegisterPage registerPage;

 @Parameters({"browser"})
 @BeforeTest
public void setUp(@Optional("chrome") String browserName) {
		df = new DriverFactory();
		prop = df.initProp();
//		if (browserName!= null) {
//		prop.setProperty("browser", browserName);
//		}
    //	driver = df.initDriver("chrome");
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);





		}
		@AfterMethod // will be running after each @test method
		public void attachScreenshot(ITestResult result) {

			if (!result.isSuccess()) {// only for failure test cases -- true
				ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
			}

			// ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");

		}

		@AfterTest
		public void tearDown() {
			driver.quit();
		}

	}
