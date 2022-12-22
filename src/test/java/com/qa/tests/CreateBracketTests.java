package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.ProfileSettingsPage;
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

public class CreateBracketTests extends BaseTest{
	BracketPage BracketPage;
	JSONObject createBracket;
	TestUtils utils = new TestUtils();
	
	  @BeforeClass
	  public void beforeClass() throws Exception {
			InputStream datais = null;
		  try {
			  String dataFileName = "data/createBracket.json";
			  datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
			  JSONTokener tokener = new JSONTokener(datais);
			  createBracket = new JSONObject(tokener);
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
			  startTest("User Create Bracket with Valid Bracket Name","User Create Bracket with Valid Bracket Name");
			  getTest().log(LogStatus.INFO, "Testing against"," Valid Bracket Name");
			  Thread.sleep(2000);
			  BracketPage.enterUserName(createBracket.getJSONObject("ValidData").getString("username"));
			  BracketPage.enterPassword(createBracket.getJSONObject("ValidData").getString("password"));
			  BracketPage.clickLogin();
			  Thread.sleep(1000);
			  getTest().log(LogStatus.INFO, "Received Message : ",BracketPage.getErrTxt());
			  BracketPage.clickSkipBtn();
			  Thread.sleep(1000);
			  BracketPage.clickImgBracketBTN();
			  BracketPage.clickNextBTN();
			  Thread.sleep(2000);
			  BracketPage.clickAddBracketBTN();
			  Thread.sleep(1000);
			  BracketPage.selectTournament(createBracket.getJSONObject("ValidData").getString("name"));
			  Thread.sleep(1000);
			  BracketPage.enterBracketName(createBracket.getJSONObject("ValidData").getString("bracket"));
			  BracketPage.clickCreateBTN();			  
			  String expectedErrTxt ="Bracket created successfully";
			  Thread.sleep(2000);
			  String actualErrTxt = BracketPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  
	  @Test
	  public void blankData() {
		  try{
			  startTest("User Create Bracket with Blank Bracket Name","User Create Bracket with Blank Bracket Name");
			  getTest().log(LogStatus.INFO, "Testing against"," Valid Bracket Name");
			  Thread.sleep(2000);
			  BracketPage.enterUserName(createBracket.getJSONObject("BlankData").getString("username"));
			  BracketPage.enterPassword(createBracket.getJSONObject("BlankData").getString("password"));
			  BracketPage.clickLogin();
			  Thread.sleep(1000);
			  getTest().log(LogStatus.INFO, "Received Message : ",BracketPage.getErrTxt());
			  BracketPage.clickSkipBtn();
			  Thread.sleep(1000);
			  BracketPage.clickImgBracketBTN();
			  BracketPage.clickNextBTN();
			  Thread.sleep(2000);
			  BracketPage.clickAddBracketBTN();
			  Thread.sleep(1000);
			  BracketPage.selectTournament(createBracket.getJSONObject("BlankData").getString("name"));
			  Thread.sleep(1000);
			  BracketPage.enterBracketName(createBracket.getJSONObject("BlankData").getString("bracket"));
			  BracketPage.clickCreateBTN();			  
			  String expectedErrTxt ="Please enter bracket name between 2 to 25 Character";
			  Thread.sleep(2000);
			  String actualErrTxt = BracketPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  @Test
	  public void invalidData() {
		  String expectedErrTxt ="Bracket name cannot be blank,special characters cannot be entered,one space is allowed between words.";
		  try{
			  startTest("User Create Bracket with Invalid Bracket Name","User Create Bracket with Invalid Bracket Name");
			  getTest().log(LogStatus.INFO, "Testing against"," Valid Bracket Name");
			  Thread.sleep(2000);
			  BracketPage.enterUserName(createBracket.getJSONObject("InvalidData").getString("username"));
			  BracketPage.enterPassword(createBracket.getJSONObject("InvalidData").getString("password"));
			  BracketPage.clickLogin();
			  Thread.sleep(1000);
			  getTest().log(LogStatus.INFO, "Received Message : ",BracketPage.getErrTxt());
			  BracketPage.clickSkipBtn();
			  Thread.sleep(1000);
			  BracketPage.clickImgBracketBTN();
			  BracketPage.clickNextBTN();
			  Thread.sleep(2000);
			  BracketPage.clickAddBracketBTN();
			  Thread.sleep(1000);
			  BracketPage.selectTournament(createBracket.getJSONObject("InvalidData").getString("name"));
			  Thread.sleep(1000);
			  BracketPage.enterBracketName(createBracket.getJSONObject("InvalidData").getString("bracket"));
			  BracketPage.clickCreateBTN();			 
			  Thread.sleep(2000);
			  String actualErrTxt = BracketPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  Assert.assertEquals("", expectedErrTxt);
			  ex.printStackTrace();
			  
		  }
	  }
	  @Test
	  public void alreadyExistingBracket() {
		  try{
			  startTest("User Create Bracket with Already Existing Bracket Name","User Create Bracket with Already Existing Bracket Name");
			  getTest().log(LogStatus.INFO, "Testing against"," Valid Bracket Name");
			  Thread.sleep(2000);
			  BracketPage.enterUserName(createBracket.getJSONObject("AlreadyExistingBracket").getString("username"));
			  BracketPage.enterPassword(createBracket.getJSONObject("AlreadyExistingBracket").getString("password"));
			  BracketPage.clickLogin();
			  Thread.sleep(1000);
			  getTest().log(LogStatus.INFO, "Received Message : ",BracketPage.getErrTxt());
			  BracketPage.clickSkipBtn();
			  Thread.sleep(1000);
			  BracketPage.clickImgBracketBTN();
			  BracketPage.clickNextBTN();
			  Thread.sleep(2000);
			  BracketPage.clickAddBracketBTN();
			  Thread.sleep(1000);
			  BracketPage.selectTournament(createBracket.getJSONObject("AlreadyExistingBracket").getString("name"));
			  Thread.sleep(1000);
			  BracketPage.enterBracketName(createBracket.getJSONObject("AlreadyExistingBracket").getString("bracket"));
			  BracketPage.clickCreateBTN();			  
			  String expectedErrTxt ="Bracket name already exists";
			  Thread.sleep(2000);
			  String actualErrTxt = BracketPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
}
