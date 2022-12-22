package com.qa.pages;

import org.openqa.selenium.WebElement;

import com.qa.BaseTest;
import com.qa.utils.TestUtils;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SignupPage extends BaseTest {
	TestUtils utils = new TestUtils();
	@AndroidFindBy (xpath = "//*[@text='Create Account']") 
	private MobileElement createAccountBtn;

	@AndroidFindBy (id = "et_first_name_sign_up")
	private MobileElement fnameTxtFld;
	
	@AndroidFindBy (id="et_last_name_sign_up")
	private MobileElement lnameTxtFld;
	
	@AndroidFindBy(id ="et_email_sign_up")
	private MobileElement emailTxtFld;
	
	@AndroidFindBy (xpath="//*[@text='+1']")
	private MobileElement code1;
	
	@AndroidFindBy (xpath="//*[@text='+91']")
	private MobileElement code91;
	
	@AndroidFindBy (id="et_phone_no_sign_up")
	private MobileElement phoneTxtFld;
	
	@AndroidFindBy (id = "et_password")
	private MobileElement passwordTxtFld;
	
	@AndroidFindBy (xpath="//*[@text='NEXT']")
	private MobileElement nextBTN;
	
	@AndroidFindBy (xpath="//*[@text='Male']")
	private MobileElement gender1;
	
	@AndroidFindBy (xpath="//*[@text='Female']")
	private MobileElement gender2;
	
	@AndroidFindBy (xpath="//*[@text='Other']")
	private MobileElement gender3;
	
	@AndroidFindBy (id = "edit_dob") 
	private MobileElement dobTxtFld;
	
	@AndroidFindBy (id="cbTermsAndCondition")
	private MobileElement checkTerms;
	
	@AndroidFindBy (id="edit_referral_code")
	private MobileElement referTxtFld;
	
	public SignupPage enterFirstName(String fname) {
		clear(fnameTxtFld);	
		sendKeys(fnameTxtFld, fname, "First Name is " + fname,"FirstName");
		return this;
	}
	public SignupPage enterLastName(String lname) {
		clear(lnameTxtFld);	
		sendKeys(lnameTxtFld, lname, "Last Name is " + lname,"LastName");
		return this;
	}
	public SignupPage enterEmail(String email) {
		clear(emailTxtFld);	
		sendKeys(emailTxtFld, email, "Email is " + email,"Email");
		return this;
	}
	public SignupPage selectCode(String code) {
		click(code1);
		if(code.equals("+1")){
			click(code1,"Clicked on "+code,"Code");
		}else {
			click(code91,"Clicked on "+code,"Code");
		}
		return this;
	}
	public SignupPage selectGender(String gender) {
		click(gender1);
		if(gender.equals("Male")){
			click(gender1,"Selecting Gender "+gender,"Gender");
		}else if(gender.equals("Female")) {
			click(gender2,"Selecting Gender "+gender,"Gender");
		}else {
			click(gender3,"Selecting Gender "+gender,"Gender");
		}
		return this;
	}
	public SignupPage selectDate(String date) {
		clear(dobTxtFld);	
		sendKeys(dobTxtFld, date, "Date of Birth is " + date,"BOD");
		return this;
	}
	public SignupPage clickTerms() {
		click(checkTerms);
		return this;
	}
	public SignupPage enterPhone(String phone) {
		clear(phoneTxtFld);	
		sendKeys(phoneTxtFld, phone, "Contact No is " + phone,"Phone no.");
		return this;
	}
	
	public SignupPage enterPassword(String password) {
		clear(passwordTxtFld);
		sendKeys(passwordTxtFld, password, "Password is " + password ,"Password");
		return this;
	}

	public String getErrTxt() {
	    String text = getToast();
	    return text;
	    	
	}
	
	public SignupPage clickNext() {
		click(nextBTN,"Clicking on Next","Next");
		return this;
	}
	
	public SignupPage clickAccept() {
		click(nextBTN);
		return this;
	}
	
	public SignupPage clickCreate() {
		click(createAccountBtn);
		return this;
	}
	
}


