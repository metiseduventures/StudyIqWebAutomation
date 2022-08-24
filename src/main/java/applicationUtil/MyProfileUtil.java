package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import pageObject.MyProfile_OR;
import pojo.TestData;
import util.Common_Function;
import util.ConfigFileReader;

public class MyProfileUtil {

	MyProfile_OR MyProfileORobj;
	HomePageUtil homeUtilObj;
	public List<String> MyProfileORobjMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();
	ConfigFileReader rConfigFileReader;
	
	public MyProfileUtil(WebDriver driver) {
		MyProfileORobj = new MyProfile_OR();
		PageFactory.initElements(driver, MyProfileORobj);
	}
	
	public boolean verifyMyProfile_Page(WebDriver driver,TestData testData) {
		boolean result = true;
		try {
			
			homeUtilObj=new HomePageUtil(driver);
			homeUtilObj.verifyLogin(driver, ConfigFileReader.strUserMobileNumber);
			Thread.sleep(2000);
			homeUtilObj.verifyMyProfile(driver);
			
			Thread.sleep(3000);
			result = validateMyProfile_Page(testData.getYourName(), testData.getYourCity(),testData.getYourAddress(),
					testData.getYourState(), testData.getYourPinCode());
			if(!result) {
				return result;
			}
			
		} catch (Exception e) {
			result = false;
			MyProfileORobjMsgList.add("verifyMyProfile_Page_Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean validateMyProfile_Page(String Name,String City,String Address,String State,String Pincode) {
		boolean result = true;
		try {
			Thread.sleep(2000);
			result=MyProfileORobj.getYourName_text().isDisplayed();
			if (!result) {
				MyProfileORobjMsgList.add("Your Name Title is not Available");
			}
			Thread.sleep(2000);
			MyProfileORobj.getYourName_input().sendKeys(Name);;
						
			result=MyProfileORobj.getYourCity_text().isDisplayed();
			if (!result) {
				MyProfileORobjMsgList.add("Your City Title is not Available");
			}
			Thread.sleep(2000);
			MyProfileORobj.getYourCity_input().sendKeys(City);
			
			
			result=MyProfileORobj.getYourAddress_text().isDisplayed();
			if (!result) {
				MyProfileORobjMsgList.add("Your Address Title is not Available");
			}
			Thread.sleep(2000);
			MyProfileORobj.getYourAddress_input().sendKeys(Address);;
		
			
			result=MyProfileORobj.getYourState_text().isDisplayed();
			if (!result) {
				MyProfileORobjMsgList.add("Your State Title is not Available");
			}
			Thread.sleep(2000);
			MyProfileORobj.getYourState_input().sendKeys(State);
						
			result=MyProfileORobj.getYourPinCode_text().isDisplayed();
			if (!result) {
				MyProfileORobjMsgList.add("Your PinCode Title is not Available");
			}
			Thread.sleep(2000);
			MyProfileORobj.getYourPinCode_input().sendKeys(Pincode);;
			
			
			boolean b1=MyProfileORobj.getUpdateProfile_button().isDisplayed();
			if (b1==true) {
				MyProfileORobj.getUpdateProfile_button().click();
				Thread.sleep(2000);
			}
			else {
				result=false;
				MyProfileORobjMsgList.add("Update Profile Button is not Working");
			}
			
		} catch (Exception e) {
			result = false;
			MyProfileORobjMsgList.add("validateMyProfile_Page_Exception: " + e.getMessage());
		}
		return result;
	}
	
	
}
