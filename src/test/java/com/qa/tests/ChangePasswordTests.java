package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.ProfileSettingsPage;
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

public class ChangePasswordTests extends BaseTest{
	ProfileSettingsPage profileSettingsPage;
	JSONObject changePassword;
	TestUtils utils = new TestUtils();
	
	  @BeforeClass
	  public void beforeClass() throws Exception {
			InputStream datais = null;
		  try {
			  String dataFileName = "data/changePassword.json";
			  datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
			  JSONTokener tokener = new JSONTokener(datais);
			  changePassword = new JSONObject(tokener);
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
		  profileSettingsPage = new ProfileSettingsPage();
		 launchApp();
	  }

	  @AfterMethod
	  public void afterMethod() {	
		 closeApp();
	  }
	  
	
	  @Test
	  public void invalidOldPassword() {
		  try{
			  startTest("User Change Password with Invalid Old Password","User Change Password With Invalid Old Password Data");
			  getTest().log(LogStatus.INFO, "Testing against","Invalid Old Password Scenario");
			  //Thread.sleep(2000);
			  profileSettingsPage.enterUserName(changePassword.getJSONObject("InvalidOldPassword").getString("username"));
			  profileSettingsPage.enterPassword(changePassword.getJSONObject("InvalidOldPassword").getString("password"));
			  profileSettingsPage.clickLogin();
			  Thread.sleep(1000);
			  getTest().log(LogStatus.INFO, "Received Message : ",profileSettingsPage.getErrTxt());
			  profileSettingsPage.clickSkipBtn();
			  Thread.sleep(1000);
			  profileSettingsPage.clickMenuBtn();
			  profileSettingsPage.clickProfileSettings();
			  Thread.sleep(1000);
			  profileSettingsPage.clickChangeBtn();
			  Thread.sleep(1000);
			  profileSettingsPage.enterOldPassword(changePassword.getJSONObject("InvalidOldPassword").getString("oldPassword"));
			  profileSettingsPage.enterNewPassword(changePassword.getJSONObject("InvalidOldPassword").getString("newPassword"));
			  profileSettingsPage.enterConfirmPassword(changePassword.getJSONObject("InvalidOldPassword").getString("confirmPassword"));
			  profileSettingsPage.clickSaveBtn();
			  String expectedErrTxt ="OldPassword not found";
			  Thread.sleep(2000);
			  String actualErrTxt = profileSettingsPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  
	  @Test
	  public void validOldPassword() {
		  try{
			  startTest("User Change Password with Valid Data ","Change Password With Valid Data");
			  getTest().log(LogStatus.INFO, "Testing against","Valid Data Scenario");
			  //Thread.sleep(2000);
			  profileSettingsPage.enterUserName(changePassword.getJSONObject("ValidOldPassword").getString("username"));
			  profileSettingsPage.enterPassword(changePassword.getJSONObject("ValidOldPassword").getString("password"));
			  profileSettingsPage.clickLogin();
			  Thread.sleep(1000);
			  getTest().log(LogStatus.INFO, "Received Message : ",profileSettingsPage.getErrTxt());
			  profileSettingsPage.clickSkipBtn();
			  Thread.sleep(1000);
			  profileSettingsPage.clickMenuBtn();
			  profileSettingsPage.clickProfileSettings();
			  Thread.sleep(1000);
			  profileSettingsPage.clickChangeBtn();
			  Thread.sleep(1000);
			  profileSettingsPage.enterOldPassword(changePassword.getJSONObject("ValidOldPassword").getString("oldPassword"));
			  profileSettingsPage.enterNewPassword(changePassword.getJSONObject("ValidOldPassword").getString("newPassword"));
			  profileSettingsPage.enterConfirmPassword(changePassword.getJSONObject("ValidOldPassword").getString("confirmPassword"));
			  profileSettingsPage.clickSaveBtn();
			  String expectedErrTxt ="Changed Password Successfully";
			  Thread.sleep(2000);
			  String actualErrTxt = profileSettingsPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  @Test
	  public void blankData() {
		  try{
			  startTest("User Change Password with Blank Data ","Change Password With Blank Data");
			  getTest().log(LogStatus.INFO, "Testing against","Blank Data Scenario");
			  //Thread.sleep(2000);
			  profileSettingsPage.enterUserName(changePassword.getJSONObject("BlankData").getString("username"));
			  profileSettingsPage.enterPassword(changePassword.getJSONObject("BlankData").getString("password"));
			  profileSettingsPage.clickLogin();
			  Thread.sleep(1000);
			  getTest().log(LogStatus.INFO, "Received Message : ",profileSettingsPage.getErrTxt());
			  profileSettingsPage.clickSkipBtn();
			  Thread.sleep(1000);
			  profileSettingsPage.clickMenuBtn();
			  profileSettingsPage.clickProfileSettings();
			  Thread.sleep(1000);
			  profileSettingsPage.clickChangeBtn();
			  Thread.sleep(1000);
			  profileSettingsPage.enterOldPassword(changePassword.getJSONObject("BlankData").getString("oldPassword"));
			  profileSettingsPage.enterNewPassword(changePassword.getJSONObject("BlankData").getString("newPassword"));
			  profileSettingsPage.enterConfirmPassword(changePassword.getJSONObject("BlankData").getString("confirmPassword"));
			  profileSettingsPage.clickSaveBtn();
			  String expectedErrTxt ="Enter your old password";
			  Thread.sleep(2000);
			  String actualErrTxt = profileSettingsPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  
	  @Test
	  public void blankNewPassword() {
		  try{
			  startTest("User Change Password with Blank New Password","Change Password With New Password");
			  getTest().log(LogStatus.INFO, "Testing against","Blank Data Scenario");
			  //Thread.sleep(2000);
			  profileSettingsPage.enterUserName(changePassword.getJSONObject("BlankNewPassword").getString("username"));
			  profileSettingsPage.enterPassword(changePassword.getJSONObject("BlankNewPassword").getString("password"));
			  profileSettingsPage.clickLogin();
			  Thread.sleep(1000);
			  getTest().log(LogStatus.INFO, "Received Message : ",profileSettingsPage.getErrTxt());
			  profileSettingsPage.clickSkipBtn();
			  Thread.sleep(1000);
			  profileSettingsPage.clickMenuBtn();
			  profileSettingsPage.clickProfileSettings();
			  Thread.sleep(1000);
			  profileSettingsPage.clickChangeBtn();
			  Thread.sleep(1000);
			  profileSettingsPage.enterOldPassword(changePassword.getJSONObject("BlankNewPassword").getString("oldPassword"));
			  profileSettingsPage.enterNewPassword(changePassword.getJSONObject("BlankNewPassword").getString("newPassword"));
			  profileSettingsPage.enterConfirmPassword(changePassword.getJSONObject("BlankNewPassword").getString("confirmPassword"));
			  profileSettingsPage.clickSaveBtn();
			  String expectedErrTxt ="Enter new password";
			  Thread.sleep(2000);
			  String actualErrTxt = profileSettingsPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  @Test
	  public void blankConfirmPassword() {
		  try{
			  startTest("User Change Password with Blank Confirm Password","Change Password With Blank Confirm Password");
			  getTest().log(LogStatus.INFO, "Testing against","Blank Confirm Password Scenario");
			  //Thread.sleep(2000);
			  profileSettingsPage.enterUserName(changePassword.getJSONObject("BlankConfirmPassword").getString("username"));
			  profileSettingsPage.enterPassword(changePassword.getJSONObject("BlankConfirmPassword").getString("password"));
			  profileSettingsPage.clickLogin();
			  Thread.sleep(1000);
			  getTest().log(LogStatus.INFO, "Received Message : ",profileSettingsPage.getErrTxt());
			  profileSettingsPage.clickSkipBtn();
			  Thread.sleep(1000);
			  profileSettingsPage.clickMenuBtn();
			  profileSettingsPage.clickProfileSettings();
			  Thread.sleep(1000);
			  profileSettingsPage.clickChangeBtn();
			  Thread.sleep(1000);
			  profileSettingsPage.enterOldPassword(changePassword.getJSONObject("BlankConfirmPassword").getString("oldPassword"));
			  profileSettingsPage.enterNewPassword(changePassword.getJSONObject("BlankConfirmPassword").getString("newPassword"));
			  profileSettingsPage.enterConfirmPassword(changePassword.getJSONObject("BlankConfirmPassword").getString("confirmPassword"));
			  profileSettingsPage.clickSaveBtn();
			  String expectedErrTxt ="Enter confirm password";
			  Thread.sleep(2000);
			  String actualErrTxt = profileSettingsPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  @Test
	  public void invalidNewPassword() {
		  try{
			  startTest("User Change Password with Invalid New Password","Change Password With Invalid New Password");
			  getTest().log(LogStatus.INFO, "Testing against","Invalid New Password Scenario");
			  //Thread.sleep(2000);
			  profileSettingsPage.enterUserName(changePassword.getJSONObject("InvalidNewPassword").getString("username"));
			  profileSettingsPage.enterPassword(changePassword.getJSONObject("InvalidNewPassword").getString("password"));
			  profileSettingsPage.clickLogin();
			  Thread.sleep(1000);
			  getTest().log(LogStatus.INFO, "Received Message : ",profileSettingsPage.getErrTxt());
			  profileSettingsPage.clickSkipBtn();
			  Thread.sleep(1000);
			  profileSettingsPage.clickMenuBtn();
			  profileSettingsPage.clickProfileSettings();
			  Thread.sleep(1000);
			  profileSettingsPage.clickChangeBtn();
			  Thread.sleep(1000);
			  profileSettingsPage.enterOldPassword(changePassword.getJSONObject("InvalidNewPassword").getString("oldPassword"));
			  profileSettingsPage.enterNewPassword(changePassword.getJSONObject("InvalidNewPassword").getString("newPassword"));
			  profileSettingsPage.enterConfirmPassword(changePassword.getJSONObject("InvalidNewPassword").getString("confirmPassword"));
			  profileSettingsPage.clickSaveBtn();
			  String expectedErrTxt ="New Password length should be between 8 to 15 characters";
			  Thread.sleep(2000);
			  String actualErrTxt = profileSettingsPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  @Test
	  public void mismatchPassword() {
		  try{
			  startTest("User Change Password with Mismatch New and Confirm Password","Change Password With Mismatch New and Confirm Password");
			  getTest().log(LogStatus.INFO, "Testing against","Invalid Mismatch New and Confirm Password Scenario");
			  //Thread.sleep(2000);
			  profileSettingsPage.enterUserName(changePassword.getJSONObject("MismatchPassword").getString("username"));
			  profileSettingsPage.enterPassword(changePassword.getJSONObject("MismatchPassword").getString("password"));
			  profileSettingsPage.clickLogin();
			  Thread.sleep(1000);
			  getTest().log(LogStatus.INFO, "Received Message : ",profileSettingsPage.getErrTxt());
			  profileSettingsPage.clickSkipBtn();
			  Thread.sleep(1000);
			  profileSettingsPage.clickMenuBtn();
			  profileSettingsPage.clickProfileSettings();
			  Thread.sleep(1000);
			  profileSettingsPage.clickChangeBtn();
			  Thread.sleep(1000);
			  profileSettingsPage.enterOldPassword(changePassword.getJSONObject("MismatchPassword").getString("oldPassword"));
			  profileSettingsPage.enterNewPassword(changePassword.getJSONObject("MismatchPassword").getString("newPassword"));
			  profileSettingsPage.enterConfirmPassword(changePassword.getJSONObject("MismatchPassword").getString("confirmPassword"));
			  profileSettingsPage.clickSaveBtn();
			  String expectedErrTxt ="New password and Confirm password should be same";
			  Thread.sleep(2000);
			  String actualErrTxt = profileSettingsPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	 
	  
}
