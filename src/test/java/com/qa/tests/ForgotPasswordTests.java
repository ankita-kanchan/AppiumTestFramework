package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.ForgotPasswordPage;
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

public class ForgotPasswordTests extends BaseTest{
	ForgotPasswordPage forgotPasswordPage;
	JSONObject forgotPasswordUser;
	TestUtils utils = new TestUtils();
	
	  @BeforeClass
	  public void beforeClass() throws Exception {
			InputStream datais = null;
		  try {
			  String dataFileName = "data/forgotPassword.json";
			  datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
			  JSONTokener tokener = new JSONTokener(datais);
			  forgotPasswordUser = new JSONObject(tokener);
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
		  forgotPasswordPage = new ForgotPasswordPage();
		  launchApp();
	  }

	  @AfterMethod
	  public void afterMethod() {
		  closeApp();
	  }
	  
	  @Test
	  public void validEmail() {
		  String expectedErrTxt = "Passowrd Updated Successfully!!";
		  try {
			  
			  startTest("User Forgot Password with Valid Email","User Forgot Password with Valid Email");
			  getTest().log(LogStatus.INFO, "Testing against","Forgot Password with Valid Email Scenario");
			  String email=forgotPasswordUser.getJSONObject("ValidEmail").getString("email");
			  forgotPasswordPage.clickForgotPassword();
			  forgotPasswordPage.enterEmail(email);
			  forgotPasswordPage.clickSendOTP();
			  Thread.sleep(1000);
			  getTest().log(LogStatus.INFO, "Received Message : ",forgotPasswordPage.getErrTxt());
			  String[] otp=forgotPasswordPage.getOTP(email).split("");
			  
			  forgotPasswordPage.enterOtp1(otp[0]);
			  forgotPasswordPage.enterOtp2(otp[1]);
			  forgotPasswordPage.enterOtp3(otp[2]);
			  forgotPasswordPage.enterOtp4(otp[3]);
			  forgotPasswordPage.clickVerifyBtn();

			  Thread.sleep(1000);
			  getTest().log(LogStatus.INFO, "Received Message : ",forgotPasswordPage.getErrTxt());
			  forgotPasswordPage.enterPasswordReset(forgotPasswordUser.getJSONObject("ValidEmail").getString("resetPassword"));
			  forgotPasswordPage.enterConfirmedPasswordReset(forgotPasswordUser.getJSONObject("ValidEmail").getString("confirmPassword"));
			  forgotPasswordPage.clickSaveLogin();
			  Thread.sleep(1000);
			  
			  String actualErrTxt = forgotPasswordPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  Assert.assertEquals("", expectedErrTxt);
			  ex.printStackTrace();
		  }
	  }
	  
	  /*@Test
	  public void previousPassword() {
		  String expectedErrTxt = "Cannot use a previous password";
		  try {
			  
			  startTest("User Forgot Password with Previous Password","User Forgot Password with Previous Password");
			  getTest().log(LogStatus.INFO, "Testing against","Forgot Password with Previous Password Scenario");
			  String email=forgotPasswordUser.getJSONObject("PreviousPassword").getString("email");
			  forgotPasswordPage.clickForgotPassword();
			  forgotPasswordPage.enterEmail(email);
			  forgotPasswordPage.clickSendOTP();
			  //Thread.sleep(1000);
			  getTest().log(LogStatus.INFO, "Received Message : ",forgotPasswordPage.getErrTxt());
			  String[] otp=forgotPasswordPage.getOTP(email).split("");
			  
			  forgotPasswordPage.enterOtp1(otp[0]);
			  forgotPasswordPage.enterOtp2(otp[1]);
			  forgotPasswordPage.enterOtp3(otp[2]);
			  forgotPasswordPage.enterOtp4(otp[3]);
			  forgotPasswordPage.clickVerifyBtn();

			 
			  getTest().log(LogStatus.INFO, "Received Message : ",forgotPasswordPage.getErrTxt());
			  Thread.sleep(1000);
			  forgotPasswordPage.enterPasswordReset(forgotPasswordUser.getJSONObject("PreviousPassword").getString("resetPassword"));
			  forgotPasswordPage.enterConfirmedPasswordReset(forgotPasswordUser.getJSONObject("PreviousPassword").getString("confirmPassword"));
			  forgotPasswordPage.clickSaveLogin();
			  Thread.sleep(1000);
			  
			  String actualErrTxt = forgotPasswordPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  Assert.assertEquals("", expectedErrTxt);
			  ex.printStackTrace();
		  }
	  }*/
	  
	 @Test
	  public void invalidEmail() {
		  try{
			  startTest("User Forgot Password with Invalid Email","User Forgot Password with Invalid Email");
			  getTest().log(LogStatus.INFO, "Testing against","Forgot Password with Invalid Email Scenario");
			  String email=forgotPasswordUser.getJSONObject("InvalidEmail").getString("email");
			  forgotPasswordPage.clickForgotPassword();
			  forgotPasswordPage.enterEmail(email);
			  forgotPasswordPage.clickSendOTP();
			  
			  String expectedErrTxt ="email does not exist";
			  
			  Thread.sleep(1000);
			  String actualErrTxt = forgotPasswordPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	 @Test
	  public void invalidPhoneNo() {
		  try{
			  startTest("User Forgot Password with Invalid Email","User Forgot Password with Invalid Email");
			  getTest().log(LogStatus.INFO, "Testing against","Forgot Password with Invalid Email Scenario");
			  String email=forgotPasswordUser.getJSONObject("InvalidPhoneNo").getString("email");
			  forgotPasswordPage.clickForgotPassword();
			  forgotPasswordPage.enterEmail(email);
			  forgotPasswordPage.clickSendOTP();
			  
			  String expectedErrTxt ="Please use a valid email address";
			  
			  Thread.sleep(1000);
			  String actualErrTxt = forgotPasswordPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	 @Test
	  public void invalidOtp() {
		  try{
			  startTest("User Forgot Password with Valid Email and Invalid OTP","User Forgot Password with Valid Email and Invalid OTP");
			  getTest().log(LogStatus.INFO, "Testing against","Forgot Password with Invalid Valid Email and Invalid OTP");
			  String email=forgotPasswordUser.getJSONObject("InvalidOtp").getString("email");
			  forgotPasswordPage.clickForgotPassword();
			  forgotPasswordPage.enterEmail(email);
			  forgotPasswordPage.clickSendOTP();
			  Thread.sleep(1000);
			  getTest().log(LogStatus.INFO, "Received Message : ",forgotPasswordPage.getErrTxt());
			  String[] otp=forgotPasswordUser.getJSONObject("InvalidOtp").getString("otp").split("");
			  forgotPasswordPage.enterOtp1(otp[0]);
			  forgotPasswordPage.enterOtp2(otp[1]);
			  forgotPasswordPage.enterOtp3(otp[2]);
			  forgotPasswordPage.enterOtp4(otp[3]);
			  forgotPasswordPage.clickVerifyBtn();
			  String expectedErrTxt ="otp not valid!!!";
			  
			  Thread.sleep(1000);
			  String actualErrTxt = forgotPasswordPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  
	 
	  
}
