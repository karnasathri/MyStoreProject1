package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.AccountCreationPage;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;

public class AccountCreationPageTest extends BaseClass {
	
	IndexPage indexPage;
	LoginPage loginPage;
    AccountCreationPage accountCeationPage;	 
	
    @Parameters("browser")
	@BeforeMethod(groups={"Smoke","Sanity","Regression"})
	public void setup(String browser) throws InterruptedException {
		launchApp(browser);
	}
	
	@AfterMethod(groups={"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
   	}
	
	@Test(groups="Sanity")
	public void verifyCreateAccountPageTest() throws Throwable {
		indexPage = new IndexPage();
		loginPage = indexPage.clickOnSignIn();
		accountCeationPage = loginPage.createNewAccount("manteshhugar724@gmail.com");
        boolean result = accountCeationPage.validateAcountCreatePage();
        Assert.assertTrue(result);
        
		

	}

}
