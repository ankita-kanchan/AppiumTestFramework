package com.qa.pages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import com.qa.BaseTest;
import com.qa.utils.TestUtils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProfileSettingsPage extends BaseTest {
	TestUtils utils = new TestUtils();
	@AndroidFindBy (id = "et_email") 
	private MobileElement usernameTxtFld;

	@AndroidFindBy (id = "et_password")
	private MobileElement passwordTxtFld;
	
	@AndroidFindBy (id = "btn_login") 
	private MobileElement loginBtn;
	
	@AndroidFindBy (id = "btn_skip") 
	private MobileElement skipBtn;
	
	@AndroidFindBy (id = "imageview_hamburger") 
	private MobileElement menuBtn;
	
	@AndroidFindBy (xpath = "//*[@text='Change']") 
	private MobileElement verifyBtn;
	
	@AndroidFindBy (xpath = "//*[@text='Profile Settings']") 
	private MobileElement profileSetting;
	
	@AndroidFindBy (id = "et_phone_no_profile") 
	private MobileElement phoneNoTxtFld;
	
	
	@AndroidFindBy (xpath="//*[@text='SUBMIT']")
	private MobileElement sumbitBtn;
	
	@AndroidFindBy (id="editText1")
	private MobileElement editTxt1;
	
	@AndroidFindBy (id="editText2")
	private MobileElement editTxt2;
	
	@AndroidFindBy (id="editText3")
	private MobileElement editTxt3;
	
	@AndroidFindBy (id="editText4")
	private MobileElement editTxt4;
	
	@AndroidFindBy (id="et_old_password_change_pass")
	private MobileElement oldPasswordTxtFld;
	
	@AndroidFindBy (id="et_new_password_change_pass")
	private MobileElement newPasswordTxtFld;
	
	@AndroidFindBy (id="et_confirm_password_change_pass")
	private MobileElement confirmPasswordTxtFld;
	
	@AndroidFindBy (xpath="//*[@text='Change']")
	private MobileElement changeBtn;
	
	@AndroidFindBy(xpath ="//*[@text='SAVE']")
	private MobileElement saveBtn;
	
	@AndroidFindBy(xpath ="//*[@text='SUBMIT']")
	private MobileElement submitBtn;

	public ProfileSettingsPage enterUserName(String username) {
		clear(usernameTxtFld);	
		sendKeys(usernameTxtFld, username, "Email is " + username,"Email");
		return this;
	}
	
	public ProfileSettingsPage enterPassword(String password) {
		clear(passwordTxtFld);
		sendKeys(passwordTxtFld, password, "Password is " + password,"Password");
		return this;
	}
	public ProfileSettingsPage enterPhoneNo(String password) {
		clear(phoneNoTxtFld);
		sendKeys(phoneNoTxtFld, password, "New Phone No. is " + password,"New Phone No.");
		return this;
	}
	public ProfileSettingsPage enterOldPassword(String password) {
		clear(oldPasswordTxtFld);
		sendKeys(oldPasswordTxtFld, password, "Old Password is " + password,"Old Password");
		return this;
	}
	public ProfileSettingsPage enterNewPassword(String password) {
		clear(newPasswordTxtFld);
		sendKeys(newPasswordTxtFld, password, "New Password is " + password,"New Password");
		return this;
	}
	public ProfileSettingsPage enterConfirmPassword(String password) {
		clear(confirmPasswordTxtFld);
		sendKeys(confirmPasswordTxtFld, password,"Confirm Password is " + password,"Confirm Password");
		return this;
	}
	public ProfileSettingsPage enterOtp(String Otp) {
		String[] otp=Otp.split("");
		clear(editTxt1);
		sendKeys(editTxt1, otp[0], "OTP Digit 1 is " + otp[0],"OTP Digit 1");
		clear(editTxt2);
		sendKeys(editTxt2, otp[1], "OTP Digit 2 is " + otp[1],"OTP Digit 2");
		clear(editTxt3);
		sendKeys(editTxt3, otp[2], "OTP Digit 3 is " + otp[2],"OTP Digit 3");
		clear(editTxt4);
		sendKeys(editTxt4, otp[3], "OTP Digit 4 is " + otp[3],"OTP Digit 4");
		return this;
	}
	
	public String getErrTxt() {	    
	    String text = getToast();
	    return text;  	
	}
	
	public ProfileSettingsPage clickLogin() {
		click(loginBtn,"Clicking on Login Button","Login Button");
		return this;
	}
	public ProfileSettingsPage clickSkipBtn() {
		click(skipBtn,"Clicking on Skip Button","Skip Button");
		return this;
	}
	public ProfileSettingsPage clickMenuBtn() {
		click(menuBtn,"Clicking on Menu Button","Menu Button");
		return this;
	}
	public ProfileSettingsPage clickProfileSettings() {
		click(profileSetting,"Clicking on Profile Settings","Profile Settings");
		return this;
	}
	public ProfileSettingsPage clickVerfifyPhone() {
		click(verifyBtn,"Clicking on Change Phone","Change Phone");
		return this;
	}
	public ProfileSettingsPage clickChangeBtn() {
		click(changeBtn,"Clicking on Change Button ","Change Button");
		return this;
	}
	public ProfileSettingsPage clickSaveBtn() {
		click(saveBtn,"Clicking on Save Button ","Save Button");
		return this;
	}
	public ProfileSettingsPage clickSubmitBtn() {
		click(submitBtn,"Clicking on Submit Button ","Submit Button");
		return this;
	}
	public static String getOTP(String uid) throws IOException {
		String otp="";
		final String POST_PARAMS = "{\"uid\":"+uid+",\n\"otpType\":"+3+"}";
		URL obj = new URL("http://userauthentication-env.eba-azcrmt4q.ap-south-1.elasticbeanstalk.com/micro/users/fetchOtpForTesting");
		HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
		postConnection.setRequestMethod("POST");
		postConnection.setRequestProperty("email", uid);
		postConnection.setRequestProperty("otpType", "3");
		postConnection.setRequestProperty("Content-Type", "application/json");
		postConnection.setRequestProperty("Accept", "application/json");
		postConnection.setDoOutput(true);
		OutputStream os = postConnection.getOutputStream();
		os.write(POST_PARAMS.getBytes());
		os.flush();
		os.close();
		int responseCode = postConnection.getResponseCode();
		try(BufferedReader br = new BufferedReader(
				  new InputStreamReader(postConnection.getInputStream(), "utf-8")))
				 {
				    StringBuilder response = new StringBuilder();
				    String responseLine = null;
				    while ((responseLine = br.readLine()) != null) {
				        response.append(responseLine.trim());
				    }
				    JSONObject myResponse = new JSONObject(response.toString());
				    otp=myResponse.get("message").toString();
				 }
		
		return otp;
		
		}	
	
}
