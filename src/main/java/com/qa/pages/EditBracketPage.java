package com.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.BaseTest;
import com.qa.utils.TestUtils;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;

public class EditBracketPage extends BaseTest {
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
	
	public EditBracketPage enterUserName(String username) {
		clear(usernameTxtFld);	
		sendKeys(usernameTxtFld, username, "Email is " + username,"Email");
		return this;
	}
	
	public EditBracketPage enterPassword(String password) {
		clear(passwordTxtFld);
		sendKeys(passwordTxtFld, password, "Password is " + password,"Password");
		return this;
	}
		
	public EditBracketPage enterBracketName(String name) {
		clear(bracketNameTxtFld);	
		sendKeys(bracketNameTxtFld, name, "Bracket Name is " + name,"Bracket Name");
		return this;
	}
	
	public String getErrTxt() {	    
	    String text = getToast();
	    return text;	    
	}
	
	public EditBracketPage clickImgBracketBTN() {
		click(imgBracketBTN,"Clicking on Bracket Home Button ","Bracket Home Button");
		return this;
	}
	public EditBracketPage clickAddBracketBTN() {
		click(addBracketBTN,"Clicking on Add Bracket Button ","Add Bracket Button");
		return this;
	}
	public EditBracketPage clickCreateBTN() {
		click(createBTN,"Clicking on Create Bracket Button ","Create Bracket Button");
		return this;
	}
	public EditBracketPage selectTournament(String tournament_name) {
		
		selectItem(tournamentSpinner,tournament_name,"Clicking on Tounament List","Tounament List");
		return this;
	}
	public EditBracketPage selectTournamentFragment(String tournament_name) {
		
		selectItem(fragmentTournamentSpinner,tournament_name,"Clicking on Tounament List","Tounament List");
		return this;
	}
	public EditBracketPage selectBracket(String bracket) {
		selectBracketList(bracket,"Selecting the bracket");
		return this;
	}
	
	public EditBracketPage clickLogin() {
		click(loginBtn,"Clicking on Login Button","Login Button");
		return this;
	}
	public EditBracketPage clickSkipBtn() {
		click(skipBtn,"Clicking on Skip Button","Skip Button");
		return this;
	}
	public EditBracketPage clickNextBTN() {
		click(NextBtn,"Clicking on Next Button","Next Button");
		return this;
	}
	public EditBracketPage clickDeleteBTN() {
		click(DeleteBtn,"Clicking on Delete Button","Delete Button");
		return this;
	}
	public EditBracketPage clickMoreBTN() {
		click(MoreBtn,"Clicking on More Button","More Button");
		return this;
	}
	public EditBracketPage testEditBracket() {
		EditBracket();
		return this;
	}
	
}



