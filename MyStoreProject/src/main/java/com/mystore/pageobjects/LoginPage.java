package com.mystore.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

public class LoginPage extends BaseClass {


	Action action= new Action();
	
	@FindBy(id="email")
	 WebElement userName;
	
	@FindBy(id="passwd")
	 WebElement password;

	@FindBy(id="SubmitLogin")
	 WebElement signInBtn;
	
	@FindBy(name="email_create")
	 WebElement emailForNewAccount;
	
	@FindBy(name="SubmitCreate")
	 WebElement createNewAccountBtn;
	
	public LoginPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	public HomePage login(String uname, String pswd, HomePage homePage) throws Throwable {
		action.type(userName, uname);
		action.type(password, pswd);
		action.click(getDriver(), signInBtn);
		Thread.sleep(2000);
		return new HomePage();
	}
	
	
	public AddressPage login1(String uname, String pswd) throws Throwable {
	
		action.type(userName, uname);
		action.type(password, pswd);
		action.click(getDriver(), signInBtn);
		Thread.sleep(2000);
		return  new AddressPage();
	}
	
	public AccountCreationPage createNewAccount(String newEmail) throws Throwable {
		action.type(emailForNewAccount, newEmail);
		action.click(getDriver(), createNewAccountBtn);
		return new AccountCreationPage();
	}
	
	
}
