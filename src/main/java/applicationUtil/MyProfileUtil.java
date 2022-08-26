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
			result = validateMyProfile_Page();
			if(!result) {
				MyProfileORobjMsgList.add(result+" My Profile Page is not Validated ");
			}
			
		} catch (Exception e) {
			result = false;
			MyProfileORobjMsgList.add("verifyMyProfile_Page_Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean validateMyProfile_Page() {
		boolean result = true;
		try {
			result=MyProfileORobj.getYourName_text().isDisplayed();
			if (!result) {
				MyProfileORobjMsgList.add("Your Name Title is not Available");
			}
			MyProfileORobj.getYourName_input().sendKeys("Harsh Raj Sinha");
			
			
			result=MyProfileORobj.getYourCity_text().isDisplayed();
			if (!result) {
				MyProfileORobjMsgList.add("Your City Title is not Available");
			}
			MyProfileORobj.getYourCity_input().sendKeys("Gurgaon");
			
			
			result=MyProfileORobj.getYourAddress_text().isDisplayed();
			if (!result) {
				MyProfileORobjMsgList.add("Your Address Title is not Available");
			}
			MyProfileORobj.getYourAddress_input().sendKeys("Gurgaon,Haryana");;
		
			
			result=MyProfileORobj.getYourState_text().isDisplayed();
			if (!result) {
				MyProfileORobjMsgList.add("Your State Title is not Available");
			}
			MyProfileORobj.getYourState_input().sendKeys("Haryana");
						
			result=MyProfileORobj.getYourPinCode_text().isDisplayed();
			if (!result) {
				MyProfileORobjMsgList.add("Your PinCode Title is not Available");
			}
			MyProfileORobj.getYourPinCode_input().sendKeys("12201");;
			
			
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
