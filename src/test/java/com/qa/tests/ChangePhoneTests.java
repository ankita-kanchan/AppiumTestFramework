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

public class ChangePhoneTests extends BaseTest{
	ProfileSettingsPage profileSettingsPage;
	JSONObject changePhoneNo;
	TestUtils utils = new TestUtils();
	
	  @BeforeClass
	  public void beforeClass() throws Exception {
			InputStream datais = null;
		  try {
			  String dataFileName = "data/changePhoneNo.json";
			  datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
			  JSONTokener tokener = new JSONTokener(datais);
			  changePhoneNo = new JSONObject(tokener);
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
	  public void validData() {
		  try{
			  startTest("User Profile Setting : Change Phone no. with Valid Data ","Change Phone no. With Valid Data");
			  getTest().log(LogStatus.INFO, "Testing against","Valid PhoneNo and OTP Scenario");
			  //Thread.sleep(2000);
			  profileSettingsPage.enterUserName(changePhoneNo.getJSONObject("validData").getString("username"));
			  profileSettingsPage.enterPassword(changePhoneNo.getJSONObject("validData").getString("password"));
			  profileSettingsPage.clickLogin();
			  Thread.sleep(1000);
			  getTest().log(LogStatus.INFO, "Received Message : ",profileSettingsPage.getErrTxt());
			  profileSettingsPage.clickSkipBtn();
			  Thread.sleep(1000);
			  profileSettingsPage.clickMenuBtn();
			  profileSettingsPage.clickProfileSettings();
			  Thread.sleep(1000);
			  profileSettingsPage.clickVerfifyPhone();
			  Thread.sleep(1000);
			  profileSettingsPage.enterPhoneNo(changePhoneNo.getJSONObject("validData").getString("phone"));
			  profileSettingsPage.clickSubmitBtn();
			  Thread.sleep(1000);
			  String otp=ProfileSettingsPage.getOTP(changePhoneNo.getJSONObject("validData").getString("uid"));
			  Thread.sleep(1000);
			  profileSettingsPage.enterOtp(otp);
			  profileSettingsPage.clickSubmitBtn();
			  String expectedErrTxt ="PhoneNo updated Successfully";
			  Thread.sleep(2000);
			  String actualErrTxt = profileSettingsPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  @Test
	  public void invalidPhoneNo() {
		  try{
			  startTest("User Profile Setting : Change Phone no. with Blank Phone No.","Change Phone no. with Blank Phone No.");
			  getTest().log(LogStatus.INFO, "Testing against","Blank Phone No. Scenario");
			  //Thread.sleep(2000);
			  profileSettingsPage.enterUserName(changePhoneNo.getJSONObject("invalidPhoneNo").getString("username"));
			  profileSettingsPage.enterPassword(changePhoneNo.getJSONObject("invalidPhoneNo").getString("password"));
			  profileSettingsPage.clickLogin();
			  Thread.sleep(1000);
			  getTest().log(LogStatus.INFO, "Received Message : ",profileSettingsPage.getErrTxt());
			  profileSettingsPage.clickSkipBtn();
			  Thread.sleep(1000);
			  profileSettingsPage.clickMenuBtn();
			  profileSettingsPage.clickProfileSettings();
			  Thread.sleep(1000);
			  profileSettingsPage.clickVerfifyPhone();
			  Thread.sleep(1000);
			  profileSettingsPage.enterPhoneNo(changePhoneNo.getJSONObject("invalidPhoneNo").getString("phone"));
			  profileSettingsPage.clickSubmitBtn();
			  String expectedErrTxt ="Please enter your mobile number";
			  Thread.sleep(2000);
			  String actualErrTxt = profileSettingsPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  @Test
	  public void invalidOtp() {
		  try{
			  startTest("User Profile Setting : Change PhoneNo with invalid OTP Data","Change PhoneNo With invalid OTP Data");
			  getTest().log(LogStatus.INFO, "Testing against","Change PhoneNo with invalid OTP Data");
			  //Thread.sleep(2000);
			  profileSettingsPage.enterUserName(changePhoneNo.getJSONObject("invalidOtp").getString("username"));
			  profileSettingsPage.enterPassword(changePhoneNo.getJSONObject("invalidOtp").getString("password"));
			  profileSettingsPage.clickLogin();
			  Thread.sleep(1000);
			  getTest().log(LogStatus.INFO, "Received Message : ",profileSettingsPage.getErrTxt());
			  profileSettingsPage.clickSkipBtn();
			  Thread.sleep(1000);
			  profileSettingsPage.clickMenuBtn();
			  profileSettingsPage.clickProfileSettings();
			  Thread.sleep(1000);
			  profileSettingsPage.clickVerfifyPhone();
			  Thread.sleep(1000);
			  profileSettingsPage.enterPhoneNo(changePhoneNo.getJSONObject("invalidOtp").getString("phone"));
			  profileSettingsPage.clickSubmitBtn();
			  Thread.sleep(1000);
			  String otp="1234";
			  Thread.sleep(1000);
			  profileSettingsPage.enterOtp(otp);
			  profileSettingsPage.clickSubmitBtn();
			  String expectedErrTxt ="please enter valid OTP";
			  Thread.sleep(2000);
			  String actualErrTxt = profileSettingsPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  @Test
	  public void existingPhoneNo() {
		  try{
			  startTest("User Profile Setting : Change PhoneNo with Existing PhoneNo Data","Change PhoneNo with Existing PhoneNo Data");
			  getTest().log(LogStatus.INFO, "Testing against","Existing PhoneNo Data Scenario");
			  //Thread.sleep(2000);
			  profileSettingsPage.enterUserName(changePhoneNo.getJSONObject("existingPhoneNo").getString("username"));
			  profileSettingsPage.enterPassword(changePhoneNo.getJSONObject("existingPhoneNo").getString("password"));
			  profileSettingsPage.clickLogin();
			  Thread.sleep(1000);
			  getTest().log(LogStatus.INFO, "Received Message : ",profileSettingsPage.getErrTxt());
			  profileSettingsPage.clickSkipBtn();
			  Thread.sleep(1000);
			  profileSettingsPage.clickMenuBtn();
			  profileSettingsPage.clickProfileSettings();
			  Thread.sleep(1000);
			  profileSettingsPage.clickVerfifyPhone();
			  Thread.sleep(1000);
			  profileSettingsPage.enterPhoneNo(changePhoneNo.getJSONObject("existingPhoneNo").getString("phone"));
			  profileSettingsPage.clickSubmitBtn();
			  String expectedErrTxt ="please enter new mobile number to change";
			  Thread.sleep(2000);
			  String actualErrTxt = profileSettingsPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  
	  @Test
	  public void invalidPhoneDigitCount() {
		  try{
			  startTest("User Profile Setting : Change Phone no. with Invalid Phone Digit Count","Change Phone no. With Invalid Phone Digit Count");
			  getTest().log(LogStatus.INFO, "Testing against","Invalid Phone Digit Count");
			  //Thread.sleep(2000);
			  profileSettingsPage.enterUserName(changePhoneNo.getJSONObject("invalidPhoneDigitCount").getString("username"));
			  profileSettingsPage.enterPassword(changePhoneNo.getJSONObject("invalidPhoneDigitCount").getString("password"));
			  profileSettingsPage.clickLogin();
			  Thread.sleep(1000);
			  getTest().log(LogStatus.INFO, "Received Message : ",profileSettingsPage.getErrTxt());
			  profileSettingsPage.clickSkipBtn();
			  Thread.sleep(1000);
			  profileSettingsPage.clickMenuBtn();
			  profileSettingsPage.clickProfileSettings();
			  Thread.sleep(1000);
			  profileSettingsPage.clickVerfifyPhone();
			  Thread.sleep(1000);
			  profileSettingsPage.enterPhoneNo(changePhoneNo.getJSONObject("invalidPhoneDigitCount").getString("phone"));
			  profileSettingsPage.clickSubmitBtn();
			  String expectedErrTxt ="Please enter valid phone number";
			  Thread.sleep(2000);
			  String actualErrTxt = profileSettingsPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  
	  
	  
}
