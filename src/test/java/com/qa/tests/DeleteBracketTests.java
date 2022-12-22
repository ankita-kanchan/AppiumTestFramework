package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.BracketPage;
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

public class DeleteBracketTests extends BaseTest{
	BracketPage BracketPage;
	JSONObject deleteBracket;
	TestUtils utils = new TestUtils();
	
	  @BeforeClass
	  public void beforeClass() throws Exception {
			InputStream datais = null;
		  try {
			  String dataFileName = "data/deleteBracket.json";
			  datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
			  JSONTokener tokener = new JSONTokener(datais);
			  deleteBracket = new JSONObject(tokener);
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
		  BracketPage = new BracketPage();
		 launchApp();
	  }

	  @AfterMethod
	  public void afterMethod() {	
		 closeApp();
	  }
	  
	  @Test
	  public void validData() {
		  try{
			  startTest("User delete Bracket with Valid Bracket Name","User delete Bracket with Valid Bracket Name");
			  getTest().log(LogStatus.INFO, "Testing against"," Valid Bracket Name");
			  Thread.sleep(2000);
			  BracketPage.enterUserName(deleteBracket.getJSONObject("ValidData").getString("username"));
			  BracketPage.enterPassword(deleteBracket.getJSONObject("ValidData").getString("password"));
			  BracketPage.clickLogin();
			  Thread.sleep(1000);
			  getTest().log(LogStatus.INFO, "Received Message : ",BracketPage.getErrTxt());
			  BracketPage.clickSkipBtn();
			  Thread.sleep(1000);
			  BracketPage.clickImgBracketBTN();
			  BracketPage.clickNextBTN();
			  Thread.sleep(2000);	  
			  
			  BracketPage.selectTournamentFragment(deleteBracket.getJSONObject("ValidData").getString("name"));
			  Thread.sleep(1000);
			  BracketPage.clickMoreBTN();
			  Thread.sleep(1000);
			  BracketPage.clickDeleteBTN();			  
			  String expectedErrTxt ="Bracket deleted successfully";
			  Thread.sleep(2000);
			  String actualErrTxt = BracketPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  
	  
}
