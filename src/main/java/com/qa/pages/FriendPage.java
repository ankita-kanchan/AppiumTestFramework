package com.qa.pages;

import com.qa.BaseTest;
import com.qa.utils.TestUtils;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class FriendPage extends BaseTest {
	TestUtils utils = new TestUtils();
	@AndroidFindBy (id = "et_email") 
	private MobileElement usernameTxtFld;

	@AndroidFindBy (id = "et_password")
	private MobileElement passwordTxtFld;
	
	@AndroidFindBy (id = "btn_login") 
	private MobileElement loginBtn;
	
	@AndroidFindBy (id = "btn_skip") 
	private MobileElement skipBtn;
	
	@AndroidFindBy (xpath = "//*[@text='Search tournaments and people']") 
	private MobileElement homeSearchBTN;
	
	@AndroidFindBy (xpath = "//*[@text='Search here..']") 
	private MobileElement searchHereBTN;
	
	@AndroidFindBy (id="llAddFriend")
	MobileElement plusBTN;
	
	@AndroidFindBy (xpath="//*[@text='Add Friend']")
	MobileElement addFriendBTN;	
	
	@AndroidFindBy(xpath ="//*[@text='NEXT']")
	private MobileElement NextBtn;
		
	public FriendPage enterUserName(String username) {
		clear(usernameTxtFld);	
		sendKeys(usernameTxtFld, username, "Email is " + username,"Email");
		return this;
	}	
	public FriendPage enterPassword(String password) {
		clear(passwordTxtFld);
		sendKeys(passwordTxtFld, password, "Password is " + password,"Password");
		return this;
	}		
		public String getErrTxt() {	    
	    String text = getToast();
	    return text;	    
	}		
	public FriendPage selectFriend(String friend_name) {
		
		scrollAndClick(friend_name,"Clicking on Friend List");
		return this;
	}
			
	public FriendPage clickLogin() {
		click(loginBtn,"Clicking on Login Button","Login Button");
		return this;
	}
	public FriendPage clickSkipBtn() {
		click(skipBtn,"Clicking on Skip Button","Skip Button");
		return this;
	}
	public FriendPage clickNextBTN() {
		click(NextBtn,"Clicking on Next Button","Next Button");
		return this;
	}
	public FriendPage clickHomeSearch() {
		click(homeSearchBTN,"Clicking on Home Search","Home Search");
		return this;
	}
	public FriendPage clickSearchHere() {
		click(searchHereBTN,"Clicking on Search Here Button","Search Here Button");
		return this;
	}
	public FriendPage clickPlusBTN() {
		click(plusBTN,"Clicking on Plus Button","Plus Button");
		return this;
	}
	public FriendPage clickAddFriendBTN() {
		click(addFriendBTN,"Clicking on Add Friend Button","Add Friend Button");
		return this;
	}
}



