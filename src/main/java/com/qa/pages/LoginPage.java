package com.qa.pages;

import org.openqa.selenium.WebElement;

import com.qa.BaseTest;
import com.qa.utils.TestUtils;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends BaseTest {
	TestUtils utils = new TestUtils();
	@AndroidFindBy (id = "et_email") 
	private MobileElement usernameTxtFld;

	@AndroidFindBy (id = "et_password")
	private MobileElement passwordTxtFld;
	
	//@AndroidFindBy (xpath = "//*[@text='login']") 
	@AndroidFindBy (id = "btn_login") 
	private MobileElement loginBtn;
	
	//@AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView") 
	//private MobileElement errTxt;
	
public LoginPage enterUserName(String username) {
	clear(usernameTxtFld);	
	sendKeys(usernameTxtFld, username, "Email is " + username);
	return this;
}

public LoginPage enterPassword(String password) {
	clear(passwordTxtFld);
	sendKeys(passwordTxtFld, password, "Password is " + password);
	return this;
}

public String getErrTxt() {
    
    String text = getToast();
    return text;
    

}
public LoginPage clickLogin() {
	click(loginBtn);
	return this;
}

	
}


