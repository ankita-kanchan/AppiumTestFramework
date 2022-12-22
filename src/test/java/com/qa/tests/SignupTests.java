package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.SignupPage;
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

public class SignupTests extends BaseTest{
	SignupPage signupPage;
	JSONObject registerUsers;
	TestUtils utils = new TestUtils();
	
	  @BeforeClass
	  public void beforeClass() throws Exception {
			InputStream datais = null;
		  try {
			  String dataFileName = "data/registerUsers.json";
			  datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
			  JSONTokener tokener = new JSONTokener(datais);
			  registerUsers = new JSONObject(tokener);
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
		  closeApp();
	  }
	  
	  @BeforeMethod
	  public void beforeMethod(Method m) {
		  utils.log().info("\n" + "****** starting test:" + m.getName() + "******" + "\n");
		  signupPage = new SignupPage();
		  launchApp();
	  }

	  @AfterMethod
	  public void afterMethod() {
		  closeApp();
	  }
	  
	 @Test
	  public void validData() {
		  try{
			  Thread.sleep(2000);
			  signupPage.clickCreate();
			  startTest("Signup With valid data","User Signup With valid data");
			  getTest().log(LogStatus.INFO, "Testing against","Valid Data");
			  signupPage.enterFirstName(registerUsers.getJSONObject("ValidRecord").getString("fname"));
			  signupPage.enterLastName(registerUsers.getJSONObject("ValidRecord").getString("lname"));
			  signupPage.enterEmail(registerUsers.getJSONObject("ValidRecord").getString("email"));
			  signupPage.selectCode(registerUsers.getJSONObject("ValidRecord").getString("code"));
			  signupPage.enterPhone(registerUsers.getJSONObject("ValidRecord").getString("phonenumber"));
			  signupPage.enterPassword(registerUsers.getJSONObject("ValidRecord").getString("pass"));
			  signupPage.clickNext();
			  Thread.sleep(1000);
			  signupPage.selectDate(registerUsers.getJSONObject("ValidRecord").getString("bod"));
			  signupPage.selectGender(registerUsers.getJSONObject("ValidRecord").getString("gender"));
			  signupPage.clickNext();			  
			  String expectedErrTxt = "Account Created Successfully!";
			  String firstTxt = signupPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+firstTxt);
			  if(firstTxt.equals(expectedErrTxt)) {
				  Thread.sleep(1000);
				  expectedErrTxt = "OTP Sent on Email!!";
				  String secondTxt = signupPage.getErrTxt();
				  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+secondTxt);
				  Assert.assertEquals(secondTxt, expectedErrTxt);
			  }else {
				  Assert.assertEquals("", expectedErrTxt);
			  }
			  
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  @Test
	  public void invalidRecord() {
		  try{
			  Thread.sleep(2000);
			  signupPage.clickCreate();
			  startTest("Signup With Invalid Data","User Signup With Invalid Data");
			  getTest().log(LogStatus.INFO, "Testing against","Invalid Data");
			  signupPage.enterFirstName(registerUsers.getJSONObject("InvalidRecord").getString("fname"));
			  signupPage.enterLastName(registerUsers.getJSONObject("InvalidRecord").getString("lname"));
			  signupPage.enterEmail(registerUsers.getJSONObject("InvalidRecord").getString("email"));
			  signupPage.selectCode(registerUsers.getJSONObject("InvalidRecord").getString("code"));
			  signupPage.enterPhone(registerUsers.getJSONObject("InvalidRecord").getString("phonenumber"));
			  signupPage.enterPassword(registerUsers.getJSONObject("InvalidRecord").getString("pass"));
			  signupPage.clickNext();
			  Thread.sleep(1000);
			  
			  String expectedErrTxt = "Password should contain at least one uppercase letter (A-Z)";
			  String actualErrTxt = signupPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  @Test
	  public void BlankData() {
		  try{
			  Thread.sleep(2000);
			  signupPage.clickCreate();
			  startTest("Signup With Blank Data","User Signup With Blank Data");
			  getTest().log(LogStatus.INFO, "Testing against","Blank Data");
			  signupPage.enterFirstName(registerUsers.getJSONObject("BlankData").getString("fname"));
			  signupPage.enterLastName(registerUsers.getJSONObject("BlankData").getString("lname"));
			  signupPage.enterEmail(registerUsers.getJSONObject("BlankData").getString("email"));
			  //signupPage.selectCode(registerUsers.getJSONObject("BlankData").getString("code"));
			  signupPage.enterPhone(registerUsers.getJSONObject("BlankData").getString("phonenumber"));
			  signupPage.enterPassword(registerUsers.getJSONObject("BlankData").getString("pass"));
			  signupPage.clickNext();
			  Thread.sleep(1000);
			  
			  String expectedErrTxt = "F Name should not be left blank";
			  String actualErrTxt = signupPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  @Test
	  public void BlankEmailData() {
		  try{
			  Thread.sleep(2000);
			  signupPage.clickCreate();
			  startTest("Signup With Blank Email Data","User Signup With Blank Email Data");
			  getTest().log(LogStatus.INFO, "Testing against","Blank Email Data");
			  signupPage.enterFirstName(registerUsers.getJSONObject("BlankEmail").getString("fname"));
			  signupPage.enterLastName(registerUsers.getJSONObject("BlankEmail").getString("lname"));
			  signupPage.enterEmail(registerUsers.getJSONObject("BlankEmail").getString("email"));
			  //signupPage.selectCode(registerUsers.getJSONObject("BlankEmail").getString("code"));
			  signupPage.enterPhone(registerUsers.getJSONObject("BlankEmail").getString("phonenumber"));
			  signupPage.enterPassword(registerUsers.getJSONObject("BlankEmail").getString("pass"));
			  signupPage.clickNext();
			  Thread.sleep(1000);
			  
			  String expectedErrTxt = "Email Id should not be left blank";
			  String actualErrTxt = signupPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  @Test
	  public void BlankPasswordData() {
		  try{
			  Thread.sleep(2000);
			  signupPage.clickCreate();
			  startTest("Signup With Blank Password Data","User Signup With Blank Password Data");
			  getTest().log(LogStatus.INFO, "Testing against","Blank Password Data");
			  signupPage.enterFirstName(registerUsers.getJSONObject("BlankPassword").getString("fname"));
			  signupPage.enterLastName(registerUsers.getJSONObject("BlankPassword").getString("lname"));
			  signupPage.enterEmail(registerUsers.getJSONObject("BlankPassword").getString("email"));
			  //signupPage.selectCode(registerUsers.getJSONObject("BlankPassword").getString("code"));
			  signupPage.enterPhone(registerUsers.getJSONObject("BlankPassword").getString("phonenumber"));
			  signupPage.enterPassword(registerUsers.getJSONObject("BlankPassword").getString("pass"));
			  signupPage.clickNext();
			  Thread.sleep(1000);
			  
			  String expectedErrTxt = "Enter your password";
			  String actualErrTxt = signupPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  @Test
	  public void AlreadyRegisteredPhone() {
		 
		  try{
			  Thread.sleep(2000);
			  signupPage.clickCreate();
			  startTest("Signup With Already Registered Phone Number ","User Signup With Already Registered Phone Number");
			  getTest().log(LogStatus.INFO, "Testing against","Already Registered Phone Number");
			  signupPage.enterFirstName(registerUsers.getJSONObject("AlreadyRegisteredPhone").getString("fname"));
			  signupPage.enterLastName(registerUsers.getJSONObject("AlreadyRegisteredPhone").getString("lname"));
			  signupPage.enterEmail(registerUsers.getJSONObject("AlreadyRegisteredPhone").getString("email"));
			  signupPage.selectCode(registerUsers.getJSONObject("AlreadyRegisteredPhone").getString("code"));
			  signupPage.enterPhone(registerUsers.getJSONObject("AlreadyRegisteredPhone").getString("phonenumber"));
			  signupPage.enterPassword(registerUsers.getJSONObject("AlreadyRegisteredPhone").getString("pass"));
			  signupPage.clickNext();
			  Thread.sleep(1000);
			  signupPage.selectDate(registerUsers.getJSONObject("AlreadyRegisteredPhone").getString("bod"));
			  signupPage.selectGender(registerUsers.getJSONObject("AlreadyRegisteredPhone").getString("gender"));
			  signupPage.clickNext();
			  String actualErrTxt = signupPage.getErrTxt();
			  String expectedErrTxt = "Phone # Already Exists";
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  
	  @Test
	  public void AlreadyRegisteredEmail() {
		 
		  try{
			  Thread.sleep(2000);
			  signupPage.clickCreate();
			  startTest("Signup With Already Registered Email","User Signup With Already Registered Email");
			  getTest().log(LogStatus.INFO, "Testing against","Blank Password Data");
			  signupPage.enterFirstName(registerUsers.getJSONObject("AlreadyRegisteredEmail").getString("fname"));
			  signupPage.enterLastName(registerUsers.getJSONObject("AlreadyRegisteredEmail").getString("lname"));
			  signupPage.enterEmail(registerUsers.getJSONObject("AlreadyRegisteredEmail").getString("email"));
			  signupPage.selectCode(registerUsers.getJSONObject("AlreadyRegisteredEmail").getString("code"));
			  signupPage.enterPhone(registerUsers.getJSONObject("AlreadyRegisteredEmail").getString("phonenumber"));
			  signupPage.enterPassword(registerUsers.getJSONObject("AlreadyRegisteredEmail").getString("pass"));
			  signupPage.clickNext();
			  Thread.sleep(1000);
			  signupPage.selectDate(registerUsers.getJSONObject("AlreadyRegisteredEmail").getString("bod"));
			  signupPage.selectGender(registerUsers.getJSONObject("AlreadyRegisteredEmail").getString("gender"));
			  signupPage.clickNext();
			  String actualErrTxt = signupPage.getErrTxt();
			  String expectedErrTxt = "Email Already Exists";
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			 
			  ex.printStackTrace();
		  }
	  }
	  @Test
	  public void InvalidPhone() {
		  try{
			  Thread.sleep(2000);
			  signupPage.clickCreate();
			  startTest("Signup With Invalid Phone Number","User Signup With Invalid Phone Number");
			  getTest().log(LogStatus.INFO, "Testing against","Invalid Phone Number");
			  signupPage.enterFirstName(registerUsers.getJSONObject("InvalidPhone").getString("fname"));
			  signupPage.enterLastName(registerUsers.getJSONObject("InvalidPhone").getString("lname"));
			  signupPage.enterEmail(registerUsers.getJSONObject("InvalidPhone").getString("email"));
			  signupPage.selectCode(registerUsers.getJSONObject("InvalidPhone").getString("code"));
			  signupPage.enterPhone(registerUsers.getJSONObject("InvalidPhone").getString("phonenumber"));
			  signupPage.enterPassword(registerUsers.getJSONObject("InvalidPhone").getString("pass"));
			  signupPage.clickNext();
			  Thread.sleep(1000);
			  signupPage.selectDate(registerUsers.getJSONObject("InvalidPhone").getString("bod"));
			  signupPage.selectGender(registerUsers.getJSONObject("InvalidPhone").getString("gender"));
			  signupPage.clickNext();
			  
			  String expectedErrTxt = "Please use a valid mobile number";
			  String actualErrTxt = signupPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  @Test
	  public void InvalidPhoneDigitCount() {
		  try{
			  Thread.sleep(2000);
			  signupPage.clickCreate();
			  startTest("Signup With Invalid Less than 7 Digit Phone Number","User Signup With Less 7 than Digit Invalid Phone Number");
			  getTest().log(LogStatus.INFO, "Testing against","Invalid Less than 7 Digit Phone Number");
			  signupPage.enterFirstName(registerUsers.getJSONObject("InvalidPhoneCount").getString("fname"));
			  signupPage.enterLastName(registerUsers.getJSONObject("InvalidPhoneCount").getString("lname"));
			  signupPage.enterEmail(registerUsers.getJSONObject("InvalidPhoneCount").getString("email"));
			  signupPage.selectCode(registerUsers.getJSONObject("InvalidPhoneCount").getString("code"));
			  signupPage.enterPhone(registerUsers.getJSONObject("InvalidPhoneCount").getString("phonenumber"));
			  signupPage.enterPassword(registerUsers.getJSONObject("InvalidPhoneCount").getString("pass"));
			  signupPage.clickNext();
			  Thread.sleep(1000);
			  signupPage.selectDate(registerUsers.getJSONObject("InvalidPhoneCount").getString("bod"));
			  signupPage.selectGender(registerUsers.getJSONObject("InvalidPhoneCount").getString("gender"));
			  signupPage.clickNext();
			  
			  String expectedErrTxt = "Please use a valid mobile number";
			  String actualErrTxt = signupPage.getErrTxt();
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
}
