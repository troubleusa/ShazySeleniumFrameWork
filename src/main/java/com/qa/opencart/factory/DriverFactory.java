package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.FramWorkExceptions;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	

	public static String highlightEle;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	public OptionsManager optionsManager;
	/**
	 * This method is init the driver on the basis of browser...
	 * 
	 * @param browserName
	 * @return it returns driver
	 */
	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser");
		System.out.println("browser name : " + browserName);
		ChainTestListener.log("Running Browser:" + browserName);
		highlightEle = prop.getProperty("highlight");

		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			// driver = new ChromeDriver();
			//tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			tlDriver.set(new ChromeDriver());
			break;
		case "firefox":
			// driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver());
			break;
		case "edge":
			// driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver());
			//tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
		case "safari":
			// driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;

		default:
			System.out.println(AppError.INVALID_BROWSER_MESG + " : " + browserName);
			throw new FramWorkExceptions("=====INVALID BROWSER====");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();

	}

	/**
	 * this is used to get the local copy of the driver any time..
	 * 
	 * @return
	 */
	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * This method is init the prop with properties file...
	 * 
	 * @return
	 */
	public Properties initProp() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/test/resource/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	
	/**
	 * takescreenshot
	 */

	public static File getScreenshotFile() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp dir
		return srcFile;
	}

	public static byte[] getScreenshotByte() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);// temp dir

	}

	public static String getScreenshotBase64() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);// temp dir

	}
	
	

}