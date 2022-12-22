package com.qa.pages;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;
import org.openqa.selenium.WebElement;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.qa.BaseTest;
import com.qa.utils.TestUtils;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;


public class ForgotPasswordPage extends BaseTest {
	TestUtils utils = new TestUtils();
	
	@AndroidFindBy (id = "forgot_password")
	private MobileElement forgotPasswordBTN;
	
	@AndroidFindBy (id = "et_email_forget_pass") 
	private MobileElement emailTxtFld;

	@AndroidFindBy (id = "btnSendOtp")
	private MobileElement sendOTPBtn;
	
	@AndroidFindBy (id = "btn_login") 
	private MobileElement loginBtn;
	
	@AndroidFindBy (id="editText1")
	private MobileElement editTxt1;
	
	@AndroidFindBy (id="editText2")
	private MobileElement editTxt2;
	
	@AndroidFindBy (id="editText3")
	private MobileElement editTxt3;
	
	@AndroidFindBy (id="editText4")
	private MobileElement editTxt4;
	
	@AndroidFindBy (xpath = "//*[@text='SUBMIT']") 
	private MobileElement verifyBtn;
	
	@AndroidFindBy (id="etNewPasswordReset")
	private MobileElement passwordResetFld;
	
	@AndroidFindBy (id="etConfirmedPasswordReset")
	private MobileElement confirmedPasswordResetFld;
	
	@AndroidFindBy (id="save_login")
	private MobileElement save_login;
	
	public ForgotPasswordPage enterEmail(String username) {
		clear(emailTxtFld);	
		sendKeys(emailTxtFld, username, "Email is " + username,"Email");
		return this;
	}
	
	public ForgotPasswordPage enterOtp1(String Otp1) {
		clear(editTxt1);
		sendKeys(editTxt1, Otp1, "OTP Digit 1 is " + Otp1,"OTP Digit 1");
		return this;
	}
	
	public ForgotPasswordPage enterOtp2(String Otp2) {
		clear(editTxt2);
		sendKeys(editTxt2, Otp2, "OTP Digit 2 is " + Otp2,"OTP Digit 2");
		return this;
	}
	
	public ForgotPasswordPage enterOtp3(String Otp3) {
		clear(editTxt3);
		sendKeys(editTxt3, Otp3, "OTP Digit 3 is " + Otp3,"OTP Digit 3");
		return this;
	}
	
	public ForgotPasswordPage enterOtp4(String Otp4) {
		clear(editTxt4);
		sendKeys(editTxt4, Otp4, "OTP Digit 4 is " + Otp4,"OTP Digit 4");
		return this;
	}
	
	public ForgotPasswordPage enterPasswordReset(String passwordReset) {
		clear(passwordResetFld);
		sendKeys(passwordResetFld, passwordReset, "Resent Password is " + passwordReset,"Resent Password");
		return this;
	}
	
	public ForgotPasswordPage enterConfirmedPasswordReset(String confirmedPasswordReset) {
		clear(confirmedPasswordResetFld);
		sendKeys(confirmedPasswordResetFld, confirmedPasswordReset, "Confirm Password is " + confirmedPasswordReset,"Confirm Password");
		return this;
	}
	
	public String getErrTxt() {	    
	    String text = getToast();
	    return text;   	
	}
	
	public ForgotPasswordPage clickSendOTP() {
		click(sendOTPBtn,"Clicking on Send OTP Button","Send OTP");
		return this;
	}
	public ForgotPasswordPage clickVerifyBtn() {
		click(sendOTPBtn,"Clicking on Verify Button","Verify Button");
		return this;
	}
	public ForgotPasswordPage clickSaveLogin() {
		click(save_login,"Clicking on Save Login Button","Save Login");
		return this;
	}
	public ForgotPasswordPage clickForgotPassword() {
		click(forgotPasswordBTN);
		return this;
	}
	public String getOTP(String userEmail) throws IOException {
		JSONObject data;
		final String POST_PARAMS = "{\n" + "\"email\": \""+userEmail+"\"\n}";
		URL obj = new URL("http://userauthentication-env.eba-azcrmt4q.ap-south-1.elasticbeanstalk.com/micro/users/forgotPasswordTesting");
		HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
		postConnection.setRequestMethod("POST");
		postConnection.setRequestProperty("email", userEmail);
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
				     data= (JSONObject) myResponse.get("body");
				     
				     System.out.println(data.get("message"));
				     System.out.println(data.get("status"));
				 }
		
		return (String) data.get("message");
		
		}
	
}


