package com.mystore.pageobjects;

import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

public class AddToCartPage extends BaseClass {

	Action action = new Action();

	// t-shirt

//	@FindBy(id = "quantity_wanted")
//	WebElement quantity;

	// dress
	@FindBy(xpath = "/html/body/div/div[2]/div/div[4]/div/div/div/div[4]/form/div/div[2]/p[1]/input")
	WebElement quantity;

	@FindBy(name = "group_1")
	WebElement size;

	@FindBy(xpath = "//iframe[@class='fancybox-iframe']")
	WebElement iframe;

	@FindBy(xpath = "//span[text()='Add to cart']")
	WebElement addToCartBtn;

	@FindBy(xpath = "/html/body/div/div[1]/header/div[3]/div/div/div[4]/div[1]/div[1]/h2") // *[@id=\"layer_cart\"]//h2/i
	WebElement addToCartMessag;

	@FindBy(xpath = "//span[contains(text(),'Proceed to checkout')]")
	WebElement proceedToCheckOutBtn;

	public AddToCartPage() {
		PageFactory.initElements(getDriver(), this);
	}

	public void enterQuantity(String quantity1) throws Throwable {
		// driver.switchTo().frame(iframe);
		action.type(quantity, quantity1);
	}

	public void selectSize(String size1) throws Throwable {
		// driver.switchTo().frame(iframe);
		action.selectByVisibleText(size1, size);
	}

	public void clickOnAddToCart() throws Throwable {

		// driver.switchTo().frame(iframe);

		// highlight
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].style.border='2px solid red'", addToCartBtn);
		addToCartBtn.click();

		action.click(getDriver(), addToCartBtn);
	}

	public boolean validateAddtoCart() throws Throwable {

		// action.fluentWait(driver, addToCartMessag, 10);
		return action.isDisplayed(getDriver(), addToCartMessag);
	}

	public OrderPage clickOnCheckOut() throws Throwable {
		//action.fluentWait(driver, proceedToCheckOutBtn, 10);
		 Thread.sleep(5000);
		action.click(getDriver(), proceedToCheckOutBtn);

		return new OrderPage();
	}

}
