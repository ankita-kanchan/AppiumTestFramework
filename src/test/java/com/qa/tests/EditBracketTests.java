package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.ProfileSettingsPage;
import com.qa.pages.EditBracketPage;
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

public class EditBracketTests extends BaseTest{

	JSONObject createBracket;
	TestUtils utils = new TestUtils();
	EditBracketPage EditBracketPage;
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
		  EditBracketPage = new EditBracketPage();
		 launchApp();
	  }

	  @AfterMethod
	  public void afterMethod() {	
		 closeApp();
	  }
	  
	  @Test
	  public void validData() {
		  try{
			  startTest("User Edit Bracket ","User Edit Bracket");
			  getTest().log(LogStatus.INFO, "Testing against"," Valid Bracket Name");
			  Thread.sleep(2000);
			  EditBracketPage.enterUserName("ankita.kanchan@pi-sy.com");
			  EditBracketPage.enterPassword("Ankita@1234");
			  EditBracketPage.clickLogin();
			  Thread.sleep(1000);
			  getTest().log(LogStatus.INFO, "Received Message : ",EditBracketPage.getErrTxt());
			  EditBracketPage.clickSkipBtn();
			  Thread.sleep(1000);
			  EditBracketPage.clickImgBracketBTN();
			  EditBracketPage.clickNextBTN();
			  Thread.sleep(2000);
			  EditBracketPage.testEditBracket();
			  String expectedErrTxt="";
			  String actualErrTxt="";
			  getTest().log(LogStatus.INFO, "Expected Message : "+expectedErrTxt,"Received Message : "+actualErrTxt);
			  Assert.assertEquals(actualErrTxt, expectedErrTxt);
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	  
	
}
