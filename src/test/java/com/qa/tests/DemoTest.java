package com.qa.tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import com.qa.pages.ProfileSettingsPage;

public class DemoTest {
	public static void main( String args[]) {
		try {
			getOTP("12");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		System.out.println("Hello");
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
