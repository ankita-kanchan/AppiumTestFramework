package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.LoginPage;
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

public class LoginTests extends BaseTest{
	LoginPage loginPage;
	JSONObject loginUsers;
	TestUtils utils = new TestUtils();
	
	  @BeforeClass
	  public void beforeClass() throws Exception {
			InputStream datais = null;
		  try {
			  String dataFileName = "data/loginUsers.json";
			  datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
			  JSONTokener tokener = new JSONTokener(datais);
			  loginUsers = new JSONObject(tokener);
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
		  loginPage = new LoginPage();
	  }

	  @AfterMethod
	  public void afterMethod() {		  
	  }
	  
	  @Test
	  public void invalidUserName() {
		  try {
			  startTest("User Sign In with Invalid UserName and Valid Password","SignIn With Invalid  Username");
			  getTest().log(LogStatus.INFO, "Testing against","Invalid UserName and Valid Password Scenario");
			  
			  loginPage.enterUserName(loginUsers.getJSONObject("invalidUser").getString("username"));
			  loginPage.enterPassword(loginUsers.getJSONObject("invalidUser").getString("password"));
			  loginPage.clickLogin();
			  String expectedErrTxt = "Error: User Doesnt Exist! Or User is not Active";
			  Thread.sleep(2000);
			  String actualErrTxt = loginPage.getErrTxt();
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  
	  @Test
	  public void invalidPassword() {
		  try{
			  startTest("User Sign In with Valid UserName and Invalid Password","SignIn With Invalid Password Data");
			  getTest().log(LogStatus.INFO, "Testing against","Invalid Password Scenario");
			  loginPage.enterUserName(loginUsers.getJSONObject("invalidPassword").getString("username"));
			  loginPage.enterPassword(loginUsers.getJSONObject("invalidPassword").getString("password"));
			  loginPage.clickLogin();
			  
			  String expectedErrTxt ="Error: Invalid Credential";
			  
			  Thread.sleep(2000);
			  String actualErrTxt = loginPage.getErrTxt();
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  
	  @Test
	  public void successfulLogin() {
		  try{
			  startTest("User Sign In with Valid UserName and Valid Password","SignIn With Valid Data"); 
			  getTest().log(LogStatus.INFO, "Testing against","Valid UserName and Valid Password Scenario");
			  loginPage.enterUserName(loginUsers.getJSONObject("validUser").getString("username"));
			  loginPage.enterPassword(loginUsers.getJSONObject("validUser").getString("password"));
			  loginPage.clickLogin();
			  
			  String expectedErrTxt = "Login Successfull";
			  
			  Thread.sleep(2000);
			  String actualErrTxt = loginPage.getErrTxt();
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  @Test
	  public void blankData() {
		  try{startTest("User Sign In with Blank Data","SignIn With Blank Data");
		  getTest().log(LogStatus.INFO, "Testing against","Blank Data Scenario");
			  loginPage.enterUserName(loginUsers.getJSONObject("blankData").getString("username"));
			  loginPage.enterPassword(loginUsers.getJSONObject("blankData").getString("password"));
			  loginPage.clickLogin();
			  
			  String expectedErrTxt = "Enter Email";
			  
			  Thread.sleep(2000);
			  String actualErrTxt = loginPage.getErrTxt();
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  @Test
	  public void blankEmailButValidPassword() {
		  try{startTest("User Sign In with Blank Email But Valid Password","SignIn With Blank Email But Valid Password");
		  getTest().log(LogStatus.INFO, "Testing against","Blank Email But Valid Password");
			  loginPage.enterUserName(loginUsers.getJSONObject("blankEmailButValidPassword").getString("username"));
			  loginPage.enterPassword(loginUsers.getJSONObject("blankEmailButValidPassword").getString("password"));
			  loginPage.clickLogin();
			  
			  String expectedErrTxt = "Enter Email";
			  
			  Thread.sleep(2000);
			  String actualErrTxt = loginPage.getErrTxt();
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  @Test
	  public void blankPasswordButValidEmail() {
		  try{startTest("User Sign In with Blank Password But Valid Email","SignIn With Blank Password But Valid Email");
		  getTest().log(LogStatus.INFO, "Testing against","Blank Password But Valid Email");
			  loginPage.enterUserName(loginUsers.getJSONObject("blankPasswordButValidEmail").getString("username"));
			  loginPage.enterPassword(loginUsers.getJSONObject("blankPasswordButValidEmail").getString("password"));
			  loginPage.clickLogin();
			  
			  String expectedErrTxt = "Password length should be between 8 to 15 characters";
			  
			  Thread.sleep(2000);
			  String actualErrTxt = loginPage.getErrTxt();
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  @Test
	  public void MismatchCredentials() {
		  try{startTest("User Sign In with Mismatch Credentials","SignIn With MismatchCredentials");
		  getTest().log(LogStatus.INFO, "Testing against","Mismatch Credentials");
			  loginPage.enterUserName(loginUsers.getJSONObject("MismatchCredentials").getString("username"));
			  loginPage.enterPassword(loginUsers.getJSONObject("MismatchCredentials").getString("password"));
			  loginPage.clickLogin();
			  
			  String expectedErrTxt = "Error: Invalid Credential";
			  
			  Thread.sleep(2000);
			  String actualErrTxt = loginPage.getErrTxt();
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  
}
