package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.ProfileSettingsPage;
import com.qa.pages.FriendPage;
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

public class SendFriendRequestTests extends BaseTest{
	FriendPage friendPage;
	JSONObject sendFriendRequest;
	TestUtils utils = new TestUtils();
	
	  @BeforeClass
	  public void beforeClass() throws Exception {
			InputStream datais = null;
		  try {
			  String dataFileName = "data/sendFriendRequest.json";
			  datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
			  JSONTokener tokener = new JSONTokener(datais);
			  sendFriendRequest = new JSONObject(tokener);
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
		  friendPage = new FriendPage();
		 launchApp();
	  }

	  @AfterMethod
	  public void afterMethod() {	
		 closeApp();
	  }
	  
	  @Test
	  public void newRequest() {
		  try{
			  startTest("User Send New Friend Request","User Send New Friend Request");
			  getTest().log(LogStatus.INFO, "Testing against","Send New Friend Request");
			  Thread.sleep(2000);
			  friendPage.enterUserName(sendFriendRequest.getJSONObject("NewRequest").getString("username"));
			  friendPage.enterPassword(sendFriendRequest.getJSONObject("NewRequest").getString("password"));
			  friendPage.clickLogin();
			  Thread.sleep(1000);
			  getTest().log(LogStatus.INFO, "Received Message : ",friendPage.getErrTxt());
			  friendPage.clickSkipBtn();
			  Thread.sleep(1000);
			  friendPage.clickHomeSearch();
			  Thread.sleep(1000);
			  friendPage.clickSearchHere();
			  Thread.sleep(2000);
			  friendPage.selectFriend(sendFriendRequest.getJSONObject("NewRequest").getString("friend_name"));
			  Thread.sleep(1000);
			  friendPage.clickPlusBTN();
			  Thread.sleep(1000);
			  friendPage.clickAddFriendBTN();			  		  
			  String expectedErrTxt ="Friend Request Sent";
			  Thread.sleep(2000);
			  String actualErrTxt = friendPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  @Test
	  public void alreadySentRequest() {
		  try{
			  startTest("User Already Sent Friend Request","User Already Sent Friend Request");
			  getTest().log(LogStatus.INFO, "Testing against","Already Sent Friend Request");
			  Thread.sleep(2000);
			  friendPage.enterUserName(sendFriendRequest.getJSONObject("AlreadyRequest").getString("username"));
			  friendPage.enterPassword(sendFriendRequest.getJSONObject("AlreadyRequest").getString("password"));
			  friendPage.clickLogin();
			  Thread.sleep(1000);
			  getTest().log(LogStatus.INFO, "Received Message : ",friendPage.getErrTxt());
			  friendPage.clickSkipBtn();
			  Thread.sleep(1000);
			  friendPage.clickHomeSearch();
			  Thread.sleep(1000);
			  friendPage.clickSearchHere();
			  Thread.sleep(2000);
			  friendPage.selectFriend(sendFriendRequest.getJSONObject("AlreadyRequest").getString("friend_name"));
			  Thread.sleep(1000);
			  friendPage.clickPlusBTN();
			  Thread.sleep(1000);
			  friendPage.clickAddFriendBTN();			  		  
			  String expectedErrTxt ="already Request sent";
			  Thread.sleep(2000);
			  String actualErrTxt = friendPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	 
}
