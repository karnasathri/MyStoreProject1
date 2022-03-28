/**
 * 
 */
package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.AddToCartPage;
import com.mystore.pageobjects.AddressPage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.pageobjects.OrderConformationPage;
import com.mystore.pageobjects.OrderPage;
import com.mystore.pageobjects.OrderSummary;
import com.mystore.pageobjects.PaymentPage;
import com.mystore.pageobjects.SearchResultPage;
import com.mystore.pageobjects.ShippingPage;

/**
 * @author admin
 *
 */



public class EndToEndTest extends BaseClass {

	IndexPage index;
	LoginPage loginPage;
	SearchResultPage searchresultpage;
	AddToCartPage addToCartPage;
	OrderPage orderPage;
	AddressPage addressPage;
	ShippingPage shippingPage;
	PaymentPage paymentPage;
	OrderSummary orderSummary;
	OrderConformationPage orderConformationPage;

	@Parameters("browser")
	@BeforeMethod(groups={"Smoke","Sanity","Regression"})
	public void setup(String browser) throws InterruptedException {
		launchApp(browser);
	}

	@AfterMethod(groups={"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}
	
	
	@Test(groups="Regression")
	public void endToEndTest() throws Throwable {
		index = new IndexPage();
		searchresultpage = index.searchProduct("dress");
		addToCartPage = searchresultpage.clickOnProduct();
		addToCartPage.enterQuantity("2");
		addToCartPage.selectSize("M");
		addToCartPage.clickOnAddToCart();
		Thread.sleep(2000);
		orderPage = addToCartPage.clickOnCheckOut();
		Thread.sleep(2000);
		loginPage = orderPage.clickOnCheckOut();
		Thread.sleep(2000);
		addressPage = loginPage.login1(prop.getProperty("username"), prop.getProperty("password"));
		Thread.sleep(2000);
		shippingPage = addressPage.clickOnCheckOut();
		Thread.sleep(2000);
		shippingPage.checkTheTerms();
		Thread.sleep(2000);
		paymentPage = shippingPage.clickOnProceedToCheckOut();
		Thread.sleep(2000);
		orderSummary = paymentPage.clickOnPaymentMethod();
		Thread.sleep(2000);
		orderConformationPage = orderSummary.clickOnconfirmOrderBtn();
		Thread.sleep(2000);
		String actualMessage = orderConformationPage.validateConfirmMessage();
		Thread.sleep(2000);
		String expectedMsg = "Your order on My Store is complete.";
		Thread.sleep(2000);
		Assert.assertEquals(actualMessage, expectedMsg);

	}

}
