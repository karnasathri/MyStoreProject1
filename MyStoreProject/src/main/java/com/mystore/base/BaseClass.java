package com.mystore.base;

import java.awt.Desktop.Action;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.utility.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static Properties prop;
	// public static WebDriver driver;

	// DeclareThreadLocal Driver
	public static ThreadLocal<Object> driver1 = new ThreadLocal<Object>();

	@BeforeSuite(groups = { "Smoke", "Sanity", "Regression" })
	public void loadConfig() {
		ExtentManager.setExtent();
		DOMConfigurator.configure("log4j.xml");

		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "\\Configuration\\Config.properties");
			prop.load(ip);

		} catch (FileNotFoundException e)

		{
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static WebDriver getDriver() {
		// Get Driver from threadLocalmap
		return (WebDriver) driver1.get();
	}

	public static void launchApp(String browserName) throws InterruptedException {

		// String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			// driver = new ChromeDriver();
			// Set Browser to ThreadLocalMap
			driver1.set(new ChromeDriver());

		}

		// Thread.sleep(3000);

		else if (browserName.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver();
			driver1.set(new FirefoxDriver());
		} else if (browserName.equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();
			// driver = new InternetExplorerDriver();
			driver1.set(new InternetExplorerDriver());
		}

		getDriver().manage().window().maximize();

		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		getDriver().manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		// prop.getProperty("implisitlyWaite"); //prop.getProperty("pageLoadTimeout");
		Thread.sleep(3000);
		getDriver().get(prop.getProperty("url"));
	}

	@AfterSuite
	public void afterSuite() {
		ExtentManager.endReport();
	}

}
