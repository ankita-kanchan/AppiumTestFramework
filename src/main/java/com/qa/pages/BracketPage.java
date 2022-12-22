package com.qa.pages;

import com.qa.BaseTest;
import com.qa.utils.TestUtils;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class BracketPage extends BaseTest {
	TestUtils utils = new TestUtils();
	@AndroidFindBy (id = "et_email") 
	private MobileElement usernameTxtFld;

	@AndroidFindBy (id = "et_password")
	private MobileElement passwordTxtFld;
	
	@AndroidFindBy (id = "btn_login") 
	private MobileElement loginBtn;
	
	@AndroidFindBy (id = "btn_skip") 
	private MobileElement skipBtn;
	
	@AndroidFindBy (id = "img_bracket_home") 
	private MobileElement imgBracketBTN;
	
	@AndroidFindBy (id = "iv_create_bracket") 
	private MobileElement addBracketBTN;
	
	@AndroidFindBy (id="tournamentListSpinner")
	MobileElement tournamentSpinner;
	
	@AndroidFindBy (id="tournament_spinner")
	MobileElement fragmentTournamentSpinner;
	
	@AndroidFindBy (id = "et_bracket_name")
	private MobileElement bracketNameTxtFld;
	
	@AndroidFindBy (xpath="//*[@text='CREATE']")
	private MobileElement  createBTN;	
	
	@AndroidFindBy(xpath ="//*[@text='NEXT']")
	private MobileElement NextBtn;
	
	@AndroidFindBy(id ="//*[@text='Delete']")
	private MobileElement DeleteBtn;
	
	@AndroidFindBy(id ="iv_more_option")
	private MobileElement MoreBtn;
	
	public BracketPage enterUserName(String username) {
		clear(usernameTxtFld);	
		sendKeys(usernameTxtFld, username, "Email is " + username,"Email");
		return this;
	}
	
	public BracketPage enterPassword(String password) {
		clear(passwordTxtFld);
		sendKeys(passwordTxtFld, password, "Password is " + password,"Password");
		return this;
	}
		
	public BracketPage enterBracketName(String name) {
		clear(bracketNameTxtFld);	
		sendKeys(bracketNameTxtFld, name, "Bracket Name is " + name,"Bracket Name");
		return this;
	}
	
	public String getErrTxt() {	    
	    String text = getToast();
	    return text;	    
	}
	
	public BracketPage clickImgBracketBTN() {
		click(imgBracketBTN,"Clicking on Bracket Home Button ","Bracket Home Button");
		return this;
	}
	public BracketPage clickAddBracketBTN() {
		click(addBracketBTN,"Clicking on Add Bracket Button ","Add Bracket Button");
		return this;
	}
	public BracketPage clickCreateBTN() {
		click(createBTN,"Clicking on Create Bracket Button ","Create Bracket Button");
		return this;
	}
	public BracketPage selectTournament(String tournament_name) {
		
		selectItem(tournamentSpinner,tournament_name,"Clicking on Tounament List","Tounament List");
		return this;
	}
	public BracketPage selectTournamentFragment(String tournament_name) {
		
		selectItem(fragmentTournamentSpinner,tournament_name,"Clicking on Tounament List","Tounament List");
		return this;
	}
	public BracketPage selectBracket(String bracket) {
		selectBracketList(bracket,"Selecting the bracket");
		return this;
	}
	
	public BracketPage clickLogin() {
		click(loginBtn,"Clicking on Login Button","Login Button");
		return this;
	}
	public BracketPage clickSkipBtn() {
		click(skipBtn,"Clicking on Skip Button","Skip Button");
		return this;
	}
	public BracketPage clickNextBTN() {
		click(NextBtn,"Clicking on Next Button","Next Button");
		return this;
	}
	public BracketPage clickDeleteBTN() {
		click(DeleteBtn,"Clicking on Delete Button","Delete Button");
		return this;
	}
	public BracketPage clickMoreBTN() {
		click(MoreBtn,"Clicking on More Button","More Button");
		return this;
	}
}



