package com.mystore.testcases;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.utility.Log;

public class LoginPageTest extends BaseClass {

	IndexPage indexPage;
	LoginPage loginPage;
	HomePage homePage;

	@Parameters("browser")
	@BeforeMethod(groups={"Smoke","Sanity","Regression"})
	public void setup(String browser) throws InterruptedException {
		launchApp(browser);
	}
	
	@AfterMethod(groups={"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}

	@Test(dataProvider ="Credentials", dataProviderClass = DataProviders.class,groups= {"Smoke","Sanity"} )
	public void loginTest(String  uname,String  pswd) throws Throwable {
		//Log log = new Log();
		Log.startTestCase("login");
		indexPage = new IndexPage();
		Log.info("user is going to click on SignIn");
		loginPage = indexPage.clickOnSignIn();
		Log.info("enter username and password");
		//homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		
		//this is for config.pro
		//homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"), homePage);
		
		homePage = loginPage.login(uname, pswd, homePage);
		
		String actualURL = homePage.getCurrentURL();
		String expectedURL = "http://automationpractice.com/index.php?controller=my-account";
        Log.info("Verifying if user is able to login");
		Assert.assertEquals(actualURL, expectedURL);
		Log.info("Login is Success");
		Log.endTestCase("loginTest");
	}

}
