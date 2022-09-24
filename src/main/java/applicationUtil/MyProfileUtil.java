package applicationUtil;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageObject.MyProfile_OR;
import util.Common_Function;
import util.ConfigFileReader;

public class MyProfileUtil {

	MyProfile_OR MyProfileORobj;
	HomePageUtil homeUtilObj;
	public List<String> MyProfileORobjMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();
	
	
	public MyProfileUtil(WebDriver driver) {
		MyProfileORobj = new MyProfile_OR();
		PageFactory.initElements(driver, MyProfileORobj);
	}
	
	public boolean verifyMyProfile_Page(WebDriver driver) {
		boolean result = true;
		try {
			homeUtilObj=new HomePageUtil(driver);
			
			result=homeUtilObj.verifyLogin(driver, ConfigFileReader.strUserMobileNumber);
			if (!result) { 
				MyProfileORobjMsgList.add("Fail to Login/Register");
				return result;
			}
				
			result=homeUtilObj.verifyMyProfile(driver);
			if (!result) { 
				MyProfileORobjMsgList.add("Fail to Open My Profile");
				return result;
			}
			result = validateMyProfile_Page(driver);
			if(!result) {
				MyProfileORobjMsgList.add(" My Profile Page is not Validated ");
				return result;
			}
		
			result = clickOnUpdateProfile_button();
			if (!result) {
				MyProfileORobjMsgList.add(result+" Update Profile Button is not Working");
				return result;
			}
			
			result=verifyInputDetails();
			if (!result) {
				MyProfileORobjMsgList.add("Not Verify Input data");
				return result;
			}
		
		} catch (Exception e) {
			result = false;
			MyProfileORobjMsgList.add("verifyMyProfile_Page_Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean validateMyProfile_Page(WebDriver driver) {
		boolean result = true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver,MyProfileORobj.getYourName_text(),20);
			if (!result) {
				MyProfileORobjMsgList.add("Your Name Title is not Available");
			}
			result = cfObj.commonSetTextTextBox(MyProfileORobj.getYourName_input(),"Harsh Raj");
			if (!result) {
				MyProfileORobjMsgList.add("Your Name is not Available");
			}
             
			result=cfObj.commonWaitForElementToBeVisible(driver,MyProfileORobj.getYourCity_text(),20);
			if (!result) {
				MyProfileORobjMsgList.add("Your City Title is not Available");
			}
			result = cfObj.commonSetTextTextBox(MyProfileORobj.getYourPinCode_input(), "122001");
			if (!result) {
				MyProfileORobjMsgList.add("Your City is not Available");
			}
			
			result=cfObj.commonWaitForElementToBeVisible(driver,MyProfileORobj.getYourAddress_text(),20);
			if (!result) {
				MyProfileORobjMsgList.add("Your Address Title is not Available");
			}
			result = cfObj.commonSetTextTextBox(MyProfileORobj.getYourAddress_input(), "Gurgaon,Haryana");
			if (!result) {
				MyProfileORobjMsgList.add("Your Address is not Available");
			}
			
			result=cfObj.commonWaitForElementToBeVisible(driver,MyProfileORobj.getYourState_text(),20);
			if (!result) {
				MyProfileORobjMsgList.add("Your State Title is not Available");
			}
			result = cfObj.commonSetTextTextBox(MyProfileORobj.getYourCity_input(), "Gurgaon");
			if (!result) {
				MyProfileORobjMsgList.add("Your State is not Available");
			}	
			
			result=cfObj.commonWaitForElementToBeVisible(driver,MyProfileORobj.getYourPinCode_text(),20);
			if (!result) {
				MyProfileORobjMsgList.add("Your PinCode Title is not Available");
			}
			result = cfObj.commonSetTextTextBox(MyProfileORobj.getYourState_input(),"Haryana");
			if (!result) {
				MyProfileORobjMsgList.add("Your PinCode is not Available");
			}
						
		} catch (Exception e) {
			result = false;
			MyProfileORobjMsgList.add("validateMyProfile_Page_Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean verifyInputDetails() {
		boolean result = true;
		try {
			String verifyName=MyProfileORobj.getYourName_input().getAttribute("value");
			result=verifyName.equals("Harsh Raj");
			if(result==true) {
				MyProfileORobjMsgList.add("Your Name is Equal");
			}
			else {
				MyProfileORobjMsgList.add("Your Name is not Equal");
				return result;
			}
			
			String verifyCity=MyProfileORobj.getYourCity_input().getAttribute("value");
			result=verifyCity.equals("Gurgaon");
			if(result==true) {
				MyProfileORobjMsgList.add("Your City is Equal");
			}
			else {
				MyProfileORobjMsgList.add("Your City is not Equal");
				return result;
			}
			
			String verifyAddress=MyProfileORobj.getYourAddress_input().getAttribute("value");
			result=verifyAddress.equals("Gurgaon,Haryana");
			if(result==true) {
				MyProfileORobjMsgList.add("Your Address is Equal");
			}
			else {
				MyProfileORobjMsgList.add("Your Address is not Equal");
				return result;
			}
			
			String verifyState=MyProfileORobj.getYourState_input().getAttribute("value");
			result=verifyState.equals("Haryana");
			if(result==true) {
				MyProfileORobjMsgList.add("Your State is Equal");
			}
			else {
				MyProfileORobjMsgList.add("Your State is not Equal");
				return result;
			}			
			
			String verifyPinCode=MyProfileORobj.getYourPinCode_input().getAttribute("value");
			result=verifyPinCode.equals("122001");
			if(result==true) {
				MyProfileORobjMsgList.add("Your PinCode is Equal");
			}
			else {
				MyProfileORobjMsgList.add("Your PinCode is not Equal");
				return result;
			}
			
		} catch (Exception e) {
			result = false;
			MyProfileORobjMsgList.add("verifyInputDetails_Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean clickOnUpdateProfile_button() {
		boolean result = true;
		try {

			cfObj.commonClick(MyProfileORobj.getUpdateProfile_button());

		} catch (Exception e) {
			result = false;
			MyProfileORobjMsgList.add("clickOnUpdateProfile_button_Exception: " + e.getMessage());
		}
		return result;
	}
		
	
}
