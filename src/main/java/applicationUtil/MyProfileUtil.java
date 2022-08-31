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
				MyProfileORobjMsgList.add(result+" Fail to Login/Register");
			}
			
			result=homeUtilObj.verifyMyProfile(driver);
			if (!result) { 
				MyProfileORobjMsgList.add(result+" Fail to Open My Profile");
			}
			
			result = validateMyProfile_Page(driver);
			if(!result) {
				MyProfileORobjMsgList.add(result+" My Profile Page is not Validated ");
			}
			result=clickOnUpdateprofile();
			if(!result) {
				MyProfileORobjMsgList.add(result+" Update Profile Button is not Working");
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
			result=cfObj.commonWaitForElementToBeVisible(driver,MyProfileORobj.getYourName_text(),10);
			if (!result) {
				MyProfileORobjMsgList.add("Your Name Title is not Available");
			}
			result = cfObj.commonSetTextTextBox(MyProfileORobj.getYourName_input(),"Harsh Raj Sinha");
			if (!result) {
				MyProfileORobjMsgList.add("Your_Name input is not Working");
			}
			
			result=cfObj.commonWaitForElementToBeVisible(driver,MyProfileORobj.getYourCity_text(),10);
			if (!result) {
				MyProfileORobjMsgList.add("Your City Title is not Available");
			}
			result = cfObj.commonSetTextTextBox(MyProfileORobj.getYourCity_input(),"Gurgaon");
			if (!result) {
				MyProfileORobjMsgList.add("Your_City input is not Working");
			}
			
			result=cfObj.commonWaitForElementToBeVisible(driver,MyProfileORobj.getYourAddress_text(),10);
			if (!result) {
				MyProfileORobjMsgList.add("Your Address Title is not Available");
			}
			result = cfObj.commonSetTextTextBox(MyProfileORobj.getYourAddress_input(),"Gurgaon,Haryana");
			if (!result) {
				MyProfileORobjMsgList.add("Your_Address input is not Working");
			}
			
			result=cfObj.commonWaitForElementToBeVisible(driver,MyProfileORobj.getYourState_text(),10);
			if (!result) {
				MyProfileORobjMsgList.add("Your State Title is not Available");
			}
			result = cfObj.commonSetTextTextBox(MyProfileORobj.getYourState_input(),"Haryana");
			if (!result) {
				MyProfileORobjMsgList.add("Your_State input is not Working");
			}
			
			result=cfObj.commonWaitForElementToBeVisible(driver,MyProfileORobj.getYourPinCode_text(),10);
			if (!result) {
				MyProfileORobjMsgList.add("Your PinCode Title is not Available");
			}
			result = cfObj.commonSetTextTextBox(MyProfileORobj.getYourPinCode_input(),"122001");
			if (!result) {
				MyProfileORobjMsgList.add("Your_Pincode input is not Working");
			}
			
		} catch (Exception e) {
			result = false;
			MyProfileORobjMsgList.add("validateMyProfile_Page_Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean clickOnUpdateprofile() {
		boolean result = true;
		try {

			cfObj.commonClick(MyProfileORobj.getUpdateProfile_button());
		} catch (Exception e) {
			result = false;
			MyProfileORobjMsgList.add("clickOnUpdateprofile_Exception: " + e.getMessage());
		}
		return result;
	}
	
	
}
