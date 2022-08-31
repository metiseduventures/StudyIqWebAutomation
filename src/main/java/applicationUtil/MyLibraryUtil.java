package applicationUtil;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageObject.MyLibrary_OR;
import util.Common_Function;
import util.ConfigFileReader;

public class MyLibraryUtil {

	MyLibrary_OR MyLibraryORobj;
	HomePageUtil homeUtilObj;
	public List<String> MyLibraryMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();
	
	public MyLibraryUtil(WebDriver driver) {
		MyLibraryORobj = new MyLibrary_OR();
		PageFactory.initElements(driver, MyLibraryORobj);
	}
	
	public boolean verifyMyLibrary_Page(WebDriver driver) {
		boolean result = true;
		try {
			homeUtilObj=new HomePageUtil(driver);
			result=homeUtilObj.verifyLogin(driver, ConfigFileReader.strUserMobileNumber);
			if (!result) { 
				 MyLibraryMsgList.add(result+" Fail to Login/Register");
			}
			
			result=homeUtilObj.verifyMyLibrary(driver);
			if (!result) { 
				 MyLibraryMsgList.add(result+" Fail to Open My Library");
			}
			
			result = validateMyLibrary_Page(driver);
			if(!result) {
				 MyLibraryMsgList.add(result+" My Library Page is not Validated ");
			}
			
		} catch (Exception e) {
			result = false;
			MyLibraryMsgList.add("verifyMyLibrary_Page_Exception: " + e.getMessage());
		}
		return result;
	}
	
	
	public boolean validateMyLibrary_Page(WebDriver driver) {
		boolean result = true;
		try {
			
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "(//div[@class='library_list_item list-group-item'])[1]", "xpath", 30);
			if (!result) {
				MyLibraryMsgList.add("Smart Course is Not Available");
			}
			
			
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "(//div[@class='library_list_item list-group-item'])[2]", "xpath", 30);
			if (!result) {
				MyLibraryMsgList.add("Micro Course is Not Available");
			}
			  		  
			
		} catch (Exception e) {
			result = false;
			MyLibraryMsgList.add("validateMyLibrary_Page_Exception: " + e.getMessage());
		}
		return result;
	}
}
