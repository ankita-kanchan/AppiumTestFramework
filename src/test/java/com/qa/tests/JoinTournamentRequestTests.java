package com.qa.tests;

import com.qa.BaseTest;
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

public class JoinTournamentRequestTests extends BaseTest{
	TournamentPage tournamentPage;
	JSONObject joinTournament;
	TestUtils utils = new TestUtils();
	
	  @BeforeClass
	  public void beforeClass() throws Exception {
			InputStream datais = null;
		  try {
			  String dataFileName = "data/joinTournamentRequest.json";
			  datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
			  JSONTokener tokener = new JSONTokener(datais);
			  joinTournament = new JSONObject(tokener);
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
			  startTest("User Send Tournament Join Request","User Send Tournament Join Request");
			  getTest().log(LogStatus.INFO, "Testing against","Send Tournament Join Request");
			  Thread.sleep(2000);
			  tournamentPage.enterUserName(joinTournament.getJSONObject("ValidData").getString("username"));
			  tournamentPage.enterPassword(joinTournament.getJSONObject("ValidData").getString("password"));
			  tournamentPage.clickLogin();
			  Thread.sleep(1000);
			  getTest().log(LogStatus.INFO, "Received Message : ",tournamentPage.getErrTxt());
			  tournamentPage.clickSkipBtn();
			  Thread.sleep(1000);
			  tournamentPage.clickImgTournamnentBTN();
			  tournamentPage.clickNextBTN();
			  tournamentPage.clickGotItBtn();
			  Thread.sleep(1000);
			  tournamentPage.selectTournament(joinTournament.getJSONObject("ValidData").getString("name"));
			  Thread.sleep(1000);
			  tournamentPage.clickInviteBtn();
			  Thread.sleep(2000);
			  tournamentPage.sendRequestBtn(joinTournament.getJSONObject("ValidData").getString("friend"));			  	  
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
