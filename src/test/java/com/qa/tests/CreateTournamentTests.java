package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.ProfileSettingsPage;
import com.qa.pages.TournamentPage;
import com.qa.utils.TestUtils;
import com.relevantcodes.extentreports.LogStatus;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.*;
import static com.qa.reports.ExtentTestManager.startTest;
import static com.qa.reports.ExtentTestManager.getTest;

import java.io.InputStream;
import java.lang.reflect.Method;

public class CreateTournamentTests extends BaseTest{
	TournamentPage tournamentPage;
	JSONObject createTournament;
	TestUtils utils = new TestUtils();
	
	  @BeforeClass
	  public void beforeClass() throws Exception {
			InputStream datais = null;
		  try {
			  String dataFileName = "data/createTournament.json";
			  datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
			  JSONTokener tokener = new JSONTokener(datais);
			  createTournament = new JSONObject(tokener);
			  } catch(Exception e) {
				  e.printStackTrace();
				  throw e;
			  } finally {
				  if(datais != null) {
					  datais.close();
				  }
			  }
		  closeApp();
		  launchApp();
	  }

	  @AfterClass
	  public void afterClass() {
		
	  }
	  
	  @BeforeMethod
	  public void beforeMethod(Method m) {
		  utils.log().info("\n" + "****** starting test:" + m.getName() + "******" + "\n");
		  tournamentPage = new TournamentPage();
		 launchApp();
	  }

	  @AfterMethod
	  public void afterMethod() {	
		 closeApp();
	  }
	  
	  @Test
	  public void validData() {
		  try{
			  startTest("User Create Tournament with Valid Tournament Name","User Create Tournament with Valid Tournament Name");
			  getTest().log(LogStatus.INFO, "Testing against"," Valid Tournament Name");
			  Thread.sleep(2000);
			  tournamentPage.enterUserName(createTournament.getJSONObject("ValidData").getString("username"));
			  tournamentPage.enterPassword(createTournament.getJSONObject("ValidData").getString("password"));
			  tournamentPage.clickLogin();
			  Thread.sleep(1000);
			  getTest().log(LogStatus.INFO, "Received Message : ",tournamentPage.getErrTxt());
			  tournamentPage.clickSkipBtn();
			  Thread.sleep(1000);
			  tournamentPage.clickImgTournamnentBTN();
			  tournamentPage.clickNextBTN();
			  tournamentPage.clickGotItBtn();
			  Thread.sleep(1000);
			  tournamentPage.clickAddTournamentBTN();
			  Thread.sleep(1000);
			  tournamentPage.enterTournamentName(createTournament.getJSONObject("ValidData").getString("name"));
			  tournamentPage.clickCreateBTN();			  
			  String expectedErrTxt ="Tournament created Successfully ";
			  Thread.sleep(2000);
			  String actualErrTxt = tournamentPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  @Test
	  public void blankData() {
		  try{
			  startTest("User Create Tournament with Blank Tournament Name","User Create Tournament with Blank Tournament Name");
			  getTest().log(LogStatus.INFO, "Testing against"," Blank Tournament Name");
			  Thread.sleep(2000);
			  tournamentPage.enterUserName(createTournament.getJSONObject("BlankData").getString("username"));
			  tournamentPage.enterPassword(createTournament.getJSONObject("BlankData").getString("password"));
			  tournamentPage.clickLogin();
			  Thread.sleep(1000);
			  getTest().log(LogStatus.INFO, "Received Message : ",tournamentPage.getErrTxt());
			  tournamentPage.clickSkipBtn();
			  Thread.sleep(1000);
			  tournamentPage.clickImgTournamnentBTN();
			  tournamentPage.clickNextBTN();
			  tournamentPage.clickGotItBtn();
			  Thread.sleep(1000);
			  tournamentPage.clickAddTournamentBTN();
			  Thread.sleep(1000);
			  tournamentPage.enterTournamentName(createTournament.getJSONObject("BlankData").getString("name"));
			  tournamentPage.clickCreateBTN();			  
			  String expectedErrTxt ="Tournament name should between 2 to 25 Character";
			  Thread.sleep(2000);
			  String actualErrTxt = tournamentPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  @Test
	  public void invalidData() {
		  try{
			  startTest("User Create Tournament with Invalid Tournament Name","User Create Tournament with Invalid Tournament Name");
			  getTest().log(LogStatus.INFO, "Testing against"," Invalid Tournament Name");
			  Thread.sleep(2000);
			  tournamentPage.enterUserName(createTournament.getJSONObject("InvalidData").getString("username"));
			  tournamentPage.enterPassword(createTournament.getJSONObject("InvalidData").getString("password"));
			  tournamentPage.clickLogin();
			  Thread.sleep(1000);
			  getTest().log(LogStatus.INFO, "Received Message : ",tournamentPage.getErrTxt());
			  tournamentPage.clickSkipBtn();
			  Thread.sleep(1000);
			  tournamentPage.clickImgTournamnentBTN();
			  tournamentPage.clickNextBTN();
			  tournamentPage.clickGotItBtn();
			  Thread.sleep(1000);
			  tournamentPage.clickAddTournamentBTN();
			  Thread.sleep(1000);
			  tournamentPage.enterTournamentName(createTournament.getJSONObject("InvalidData").getString("name"));
			  tournamentPage.clickCreateBTN();			  
			  String expectedErrTxt ="TournamentName Is not in Proper Format";
			  Thread.sleep(2000);
			  String actualErrTxt = tournamentPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }

	  @Test
	  public void alreadyExistingTournament() {
		  try{
			  startTest("User Create Tournament with Already Existing Tournament Name","User Create Tournament with Already Existing Tournament Name");
			  getTest().log(LogStatus.INFO, "Testing against"," Already Existing Tournament Name");
			  Thread.sleep(2000);
			  tournamentPage.enterUserName(createTournament.getJSONObject("AlreadyExistingTournament").getString("username"));
			  tournamentPage.enterPassword(createTournament.getJSONObject("AlreadyExistingTournament").getString("password"));
			  tournamentPage.clickLogin();
			  Thread.sleep(1000);
			  getTest().log(LogStatus.INFO, "Received Message : ",tournamentPage.getErrTxt());
			  tournamentPage.clickSkipBtn();
			  Thread.sleep(1000);
			  tournamentPage.clickImgTournamnentBTN();
			  tournamentPage.clickNextBTN();
			  tournamentPage.clickGotItBtn();
			  Thread.sleep(1000);
			  tournamentPage.clickAddTournamentBTN();
			  Thread.sleep(1000);
			  tournamentPage.enterTournamentName(createTournament.getJSONObject("AlreadyExistingTournament").getString("name"));
			  tournamentPage.clickCreateBTN();			  
			  String expectedErrTxt ="Invalid Input Try Again!";
			  Thread.sleep(2000);
			  String actualErrTxt = tournamentPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  @Test
	  public void privateTournament() {
		  try{
			  startTest("User Create Private Tournament","User Create Private Tournament");
			  getTest().log(LogStatus.INFO, "Testing against"," Private Tournament");
			  Thread.sleep(2000);
			  tournamentPage.enterUserName(createTournament.getJSONObject("PrivateTournament").getString("username"));
			  tournamentPage.enterPassword(createTournament.getJSONObject("PrivateTournament").getString("password"));
			  tournamentPage.clickLogin();
			  Thread.sleep(1000);
			  getTest().log(LogStatus.INFO, "Received Message : ",tournamentPage.getErrTxt());
			  tournamentPage.clickSkipBtn();
			  Thread.sleep(1000);
			  tournamentPage.clickImgTournamnentBTN();
			  tournamentPage.clickNextBTN();
			  tournamentPage.clickGotItBtn();
			  Thread.sleep(1000);
			  tournamentPage.clickAddTournamentBTN();
			  Thread.sleep(1000);
			  tournamentPage.enterTournamentName(createTournament.getJSONObject("PrivateTournament").getString("name"));
			  Thread.sleep(2000);
			  tournamentPage.clickPrivateCheck();
			  Thread.sleep(1000);
			  getTest().log(LogStatus.INFO, "Received Message : ",tournamentPage.getErrTxt());
			  Thread.sleep(1000);
			  tournamentPage.clickCreateBTN();			  
			  String expectedErrTxt ="Tournament created Successfully ";
			  Thread.sleep(2000);
			  String actualErrTxt = tournamentPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }

}
