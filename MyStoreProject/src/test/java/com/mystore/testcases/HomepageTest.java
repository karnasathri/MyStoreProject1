package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;

public class HomepageTest extends BaseClass {
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
	
	@Test(groups="Smoke")
	public void wishListTest() throws Throwable {
		indexPage = new IndexPage();
		loginPage = indexPage.clickOnSignIn();
		//homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"), homePage);
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"), homePage);
        boolean result=homePage.validateMyWishList();
        Assert.assertTrue(result);
		
	}
	
	@Test(groups="Smoke")
	public void orderHisstoryandDetailsTest() throws Throwable {
		indexPage = new IndexPage();
		loginPage = indexPage.clickOnSignIn();
		//homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"), homePage);
		homePage = loginPage.login(prop.getProperty("username"),prop.getProperty("password"), homePage);
        boolean result=homePage.validateOrderHistory();
        Assert.assertTrue(result);
     
   }
}

