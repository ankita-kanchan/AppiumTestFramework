package com.qa.pages;

import com.qa.BaseTest;
import com.qa.utils.TestUtils;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class TournamentPage extends BaseTest {
	TestUtils utils = new TestUtils();
	@AndroidFindBy (id = "et_email") 
	private MobileElement usernameTxtFld;

	@AndroidFindBy (id = "et_password")
	private MobileElement passwordTxtFld;
	
	@AndroidFindBy (id = "btn_login") 
	private MobileElement loginBtn;
	
	@AndroidFindBy (id = "btn_skip") 
	private MobileElement skipBtn;
	
	@AndroidFindBy (id = "img_tour_home") 
	private MobileElement imgTournamnentBTN;

	@AndroidFindBy (id = "iv_create_bracket")
	private MobileElement createTournamentBTN;
	
	@AndroidFindBy (id = "et_tournamentName")
	private MobileElement tournamentNameTxtFld;
	
	@AndroidFindBy (xpath="//*[@text='CREATE']")
	private MobileElement  createBTN;
	
	@AndroidFindBy (xpath="//*[@text='Private Tournament']")
	private MobileElement privateCheck;
	
	@AndroidFindBy(xpath ="//*[@text='NEXT']")
	private MobileElement NextBtn;
	
	@AndroidFindBy(xpath ="//*[@text='GOT IT!']")
	private MobileElement GotItBtn;
	
	@AndroidFindBy(id="ib_admin_invite")
	private MobileElement adminInviteBtn;
	
	public TournamentPage enterUserName(String username) {
		clear(usernameTxtFld);	
		sendKeys(usernameTxtFld, username, "Email is " + username,"Email");
		return this;
	}
	
	public TournamentPage enterPassword(String password) {
		clear(passwordTxtFld);
		sendKeys(passwordTxtFld, password, "Password is " + password,"Password");
		return this;
	}
		
	public TournamentPage enterTournamentName(String name) {
		clear(tournamentNameTxtFld);	
		sendKeys(tournamentNameTxtFld, name, "Tournament Name is " + name,"Tournament Name");
		return this;
	}
	
	public String getErrTxt() {	    
	    String text = getToast();
	    return text;	    
	}
	
	public TournamentPage clickImgTournamnentBTN() {
		click(imgTournamnentBTN,"Clicking on Tournamnents Button ","Tournamnents Button");
		return this;
	}
	public TournamentPage clickAddTournamentBTN() {
		click(createTournamentBTN,"Clicking on Add Tournamnents Button ","Add Tournamnents Button");
		return this;
	}
	public TournamentPage clickCreateBTN() {
		click(createBTN,"Clicking on Create Tournamnents Button ","Create Tournamnents Button");
		return this;
	}
	public TournamentPage clickPrivateCheck() {
		click(privateCheck,"Clicking on Private Tournament CheckBox ","Private Tournament CheckBox");
		return this;
	}
	public TournamentPage clickLogin() {
		click(loginBtn,"Clicking on Login Button","Login Button");
		return this;
	}
	public TournamentPage clickSkipBtn() {
		click(skipBtn,"Clicking on Skip Button","Skip Button");
		return this;
	}
	public TournamentPage clickNextBTN() {
		click(NextBtn,"Clicking on Next Button","Next Button");
		return this;
	}
	public TournamentPage clickGotItBtn() {
		click(GotItBtn,"Clicking on Got It Button","Got It");
		return this;
	}
	public TournamentPage clickInviteBtn() {
		click(adminInviteBtn,"Clicking on Participant Invite Button","Invite Button");
		return this;
	}
	
	public TournamentPage selectTournament(String tournament) {
		dynamicListItem(tournament,"Selecting Tournament","Tournament List");
		return this;
	}
	
	public TournamentPage sendRequestBtn(String friend) {
		sendRequestDynamic(friend,"Clicking on Send Request Button");
		return this;
	}
}



