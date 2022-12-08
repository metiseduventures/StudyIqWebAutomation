package applicationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
			
			result=homeUtilObj.verifySignUp(driver);
			if (!result) { 
				MyProfileORobjMsgList.add("Fail to Login/Register");
				return result;
			}
				
			result=homeUtilObj.verifyMyProfile(driver);
			if (!result) { 
				MyProfileORobjMsgList.add("Fail to Open My Profile");
				return result;
			}
			result = verifyProfileRevamp(driver);
			if(!result) {
				MyProfileORobjMsgList.add(" My Profile Page is not Validated ");
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
			
			result=cfObj.commonWaitForElementToBeVisible(driver,MyProfileORobj.getYourAddress_text(),20);
			if (!result) {
				MyProfileORobjMsgList.add("Your Address Title is not Available");
			}
			result = cfObj.commonSetTextTextBox(MyProfileORobj.getYourAddress_input(), "Gurgaon,Haryana");
			if (!result) {
				MyProfileORobjMsgList.add("Your Address is not Available");
			}	
			
			result=cfObj.commonWaitForElementToBeVisible(driver,MyProfileORobj.getYourPinCode_text(),20);
			if (!result) {
				MyProfileORobjMsgList.add("Your PinCode Title is not Available");
			}
			result = cfObj.commonSetTextTextBox(MyProfileORobj.getYourPinCode_input(),"122001");
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
			result=verifyCity.equals("GURUGRAM");
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
			result=verifyState.equals("HARYANA");
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
             Thread.sleep(5000);
			cfObj.commonClick(MyProfileORobj.getUpdateProfile_button());

		} catch (Exception e) {
			result = false;
			MyProfileORobjMsgList.add("verifyInputDetails_Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean verifyProfileInput(WebDriver driver) {
		boolean result = true;
		try {
			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "(//label[@class='form-label'])[1]", "xpath", 1);
			if (!result) {
				MyProfileORobjMsgList.add("Your Name Title is not Available");
			}
			result = cfObj.commonSetTextTextBox(MyProfileORobj.getYourName_input(),"Harsh Raj");
			if (!result) {
				MyProfileORobjMsgList.add("Your Name is not Available");
			}
             
			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "(//label[@class='form-label'])[2]", "xpath", 1);
			if (!result) {
				MyProfileORobjMsgList.add("Your PinCode Title is not Available");
			}
			result = cfObj.commonSetTextTextBox(MyProfileORobj.getPinCode_input(), "122001");
			if (!result) {
				MyProfileORobjMsgList.add("Your City is not Available");
			}
			
			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "(//label[@class='form-label'])[3]", "xpath", 1);
			if (!result) {
				MyProfileORobjMsgList.add("Your Address Title is not Available");
			}
			result = cfObj.commonSetTextTextBox(MyProfileORobj.getAddress_input(), "Gurgaon,Haryana");
			if (!result) {
				MyProfileORobjMsgList.add("Your Address is not Available");
			}
			
			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "(//label[@class='form-label'])[4]", "xpath", 1);
			if (!result) {
				MyProfileORobjMsgList.add("Your State Title is not Available");
			}
			result = cfObj.commonSetTextTextBox(MyProfileORobj.getCity_input(), "Gurgaon");
			if (!result) {
				MyProfileORobjMsgList.add("Your State is not Available");
			}	
			
			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "(//label[@class='form-label'])[5]", "xpath", 1);
			if (!result) {
				MyProfileORobjMsgList.add("Your PinCode Title is not Available");
			}
			result = cfObj.commonSetTextTextBox(MyProfileORobj.getState_input(),"Haryana");
			if (!result) {
				MyProfileORobjMsgList.add("Your PinCode is not Available");
			}
						
		} catch (Exception e) {
			result = false;
			MyProfileORobjMsgList.add("verifyProfileInput_Exception: " + e.getMessage());
		}
		return result;
	}
	//Profile Revamp
	
	public boolean verifyProfileRevamp(WebDriver driver) {
		boolean result = true;
		try {
			//Profile Section
			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='navbar-wrapper']/div[contains(text(),'My Profile')]", "xpath", 1);
			if (result==true) {
				result=MyProfileSection(driver);
				if(!result) {
					MyProfileORobjMsgList.add("My Profile is Not Updated");
					return result;
				}
			}else {
				MyProfileORobjMsgList.add("My-Profile_icon is not visible");
			}
			//My Exam Section
			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='navbar-wrapper']/div[contains(text(),'My Exam')]", "xpath", 1);
			if (result==true) {
				result=MyExamSection(driver);
				if(!result) {
					MyProfileORobjMsgList.add("My Exam Section is Not Executed");
					return result;
				}
				
			}else {
				MyProfileORobjMsgList.add("My-Exam Section is not visible");
            	return result;
			}
			// Account Section
			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='navbar-wrapper']/div[contains(text(),'Account')]", "xpath", 10);
			if (result==true) {
				result=AccountSection(driver);
				if(!result) {
					MyProfileORobjMsgList.add("Account Section is Not Executed");
					return result;
				}
				
			}else {
				MyProfileORobjMsgList.add("My-Account Section is not visible");
            	return result;
			}
			//Transaction Section
			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='navbar-wrapper']/div[contains(text(),'Transactions')]", "xpath", 1);
			if (result==true) {
				result=TransactionsSection(driver);
				if(!result) {
					MyProfileORobjMsgList.add("Transactions Section is Not Executed");
					return result;
				}
			}else {
				MyProfileORobjMsgList.add("Transactions Section is not visible");
            	return result;
			}
					
		} catch (Exception e) {
			result = false;
			MyProfileORobjMsgList.add("verifyProfileInput_Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean MyProfileSection(WebDriver driver) {
		boolean result = true;
		try {
			//Click On My-Profile Section
			cfObj.commonClick(MyProfileORobj.getMyProfile_icon());
			//Your Name
			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='grid-wrapper']/div[@class='grid-item']/label[contains(text(),'Your Name')]", "xpath", 1);
			if (result==true) {
				result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//input[@placeholder='Enter your Name']", "xpath", 10);
				if (result==true) {
					Thread.sleep(5000);
					cfObj.commonSetTextTextBox(MyProfileORobj.getNewYourName_input(),"Test");
				}else {
					MyProfileORobjMsgList.add("Your Name input is not Visible");
				}
				
			}else {
				MyProfileORobjMsgList.add("Your Name Title is not Available");
			}
			//Your Address
			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='grid-wrapper']/div[@class='grid-item']/label[contains(text(),'Your Address')]", "xpath", 1);
			if (result==true) {
				result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "input[placeholder='Enter your Address']", "css", 10);
				if (result==true) {
					Thread.sleep(7000);
					cfObj.commonSetTextTextBox(MyProfileORobj.getNewAddress_input(),"Gurgaon");
				}else {
					MyProfileORobjMsgList.add("Your Address input is not Visible");
				}
				
			}else {
				MyProfileORobjMsgList.add("Your Address Title is not Available");
			}
			//Your Pin code
			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='grid-wrapper']/div[@class='grid-item']/label[contains(text(),'Your Pin Code')]", "xpath", 1);
			if (result==true) {
				result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//input[@placeholder='Enter your Pin Code']", "xpath", 10);
				if (result==true) {
					cfObj.commonSetTextTextBox(MyProfileORobj.getNewPinCode_input(),"122001");
				}else {
					MyProfileORobjMsgList.add("Your PinCode input is not Visible");
				}
				
			}else {
				MyProfileORobjMsgList.add("Your PinCode Title is not Available");
			}
			Thread.sleep(5000);
			//Click On Update Button
			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//button[contains(text(),'Update Profile')]", "xpath", 10);
			if (result==true) {
				cfObj.commonClick(MyProfileORobj.getUpdateProfile_Button());
			}else {
				MyProfileORobjMsgList.add("Update Button is not Clicked");
			}
			
			// Verify Details
			result=verifyProfileDetails();
			if(!result) {
				MyProfileORobjMsgList.add("Profile data are not equal");
				return result;
			}
			
		} catch (Exception e) {
			result = false;
			MyProfileORobjMsgList.add("MyProfileSection_Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean MyExamSection(WebDriver driver) {
		boolean result = true;
		try {
			//Click On My-Exam Section
			cfObj.commonClick(MyProfileORobj.getMyExam_icon());
			
			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[contains(text(),'No goal is selected')]", "xpath", 30);
			if(result==true) {
				cfObj.commonClick(MyProfileORobj.getAddYourExam_Button());
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                result=UpdateExamPreference(driver);
                if(!result) {
                	MyProfileORobjMsgList.add("ExamPrefrences is not visible");
                	return result;
                }
			}else {
				result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='exam-title']/div[1]", "xpath", 10);
				if (result==true) {
					result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//button[contains(text(),'Update Your Exam')]", "xpath", 10);
					if (result==true) {
						cfObj.commonClick(MyProfileORobj.getUpdateYourExam_Button());
						driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
						result=UpdateExamPreference(driver);
	                    if(!result) {
	                    	MyProfileORobjMsgList.add("ExamPrefrences is not visible");
	                    	return result;
	                    }
					}else {
						MyProfileORobjMsgList.add("Update_Your_Button is not visible");
                    	return result;
					}
				}
			}
			
		} catch (Exception e) {
			result = false;
			MyProfileORobjMsgList.add("MyExamSection_Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean AccountSection(WebDriver driver) {
		boolean result = true;
		try {
			//Click On My-Account Section
			cfObj.commonClick(MyProfileORobj.getMyAccount_icon());
			
			//Updating Email-id
			
			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, " //div[@class='grid-item']/label[contains(text(),'Email')]", "xpath", 10);
			if (result==true) {
				result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "(//div[@class='grid-item']//button[contains(text(),'Update')])[1]", "xpath", 10);
				if (result==true) {
					
					//Click On Update Button
					cfObj.commonClick(MyProfileORobj.getEmailUpdate_Button());
					
					result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='grid-item']//input[@placeholder='Enter your Email']", "xpath", 10);
					if (result==true) {
						result=ProfileEnterEmail(driver,"test1234554321@gmail.com ");
						if (!result) {
							return result;
						}
						//Click On Request OTP Button
						cfObj.commonClick(MyProfileORobj.getRequestOTP_Button());
						result=VerifyButtonOf_OTP(driver);
						if (!result) {
							MyProfileORobjMsgList.add("Verify Button is not clicked");
							return result;
						}
					}else {
						MyProfileORobjMsgList.add("Email-Input Box is not Visible");
					}
				}else {
					MyProfileORobjMsgList.add("Update Button is not Visible");
				}
			}else {
				MyProfileORobjMsgList.add("Update Email in Account Section is not Available");
            	return result;
			}
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			//Updating Mobile No.
			
			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "(//div[@class='grid-item']/label[contains(text(),'Mobile Number')])[1]", "xpath", 10);
			if (result==true) {
				result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "(//div[@class='grid-item']//button[contains(text(),'Update')])[1]", "xpath", 10);
				if (result==true) {
					
					//Click On Update Button
					cfObj.commonClick(MyProfileORobj.getUpdate_Button());
					
					result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='grid-item']//input[@placeholder='Mobile Number']", "xpath", 10);
					if (result==true) {
						
						result=ProfileEnterMobileNumber(driver,"5432112345");
						if (!result) {
							return result;
						}
						//Click On Request OTP Button
						cfObj.commonClick(MyProfileORobj.getRequestOTP_Button());
						result=VerifyButtonOf_OTP_Mobile(driver);
						if (!result) {
							MyProfileORobjMsgList.add("Verify Button is not clicked");
							return result;
						}
						
					}else {
						MyProfileORobjMsgList.add("Mobile Number-Input Box is not Visible");
					}
				}else {
					MyProfileORobjMsgList.add("Update Button is not Visible");
				}

			}else {
				MyProfileORobjMsgList.add("Mobile Number in Account Section is not Available");
            	return result;
			}
			
			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[contains(text(),'Please enter valid OTP')]", "xpath", 10);
			if(result) {
				result=true;
			}
		} catch (Exception e) {
			result = false;
			MyProfileORobjMsgList.add("AccountSection_Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean TransactionsSection(WebDriver driver) {
		boolean result = true;
		try {
			//Click On Transactions Section
			cfObj.commonClick(MyProfileORobj.getTransactions_icon());
			
			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//th[contains(text(),'Order Details')]", "xpath", 10);
			if(result) {
				result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='no-transaction']", "xpath", 10);
				if(result==true) {
					return result;
				}else {
					result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//p[@class='success-btn']","xpath", 10);
					if(result==true) {
						MyProfileORobjMsgList.add(MyProfileORobj.getOrderID().get(0).getText());
						MyProfileORobjMsgList.add(MyProfileORobj.getCOurseType().get(0).getText());
						return result;
					}else {
						result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver,"//p[@class='failure-btn']","xpath", 10);
						if(result==true){
							MyProfileORobjMsgList.add(MyProfileORobj.getOrderID().get(0).getText());
							MyProfileORobjMsgList.add(MyProfileORobj.getCOurseType().get(0).getText());
							return result;
						}
						
					}
				}
			}
			
		} catch (Exception e) {
			result = false;
			MyProfileORobjMsgList.add("TransactionsSection_Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean ProfileEnterEmail(WebDriver driver,String strEmail) {
		boolean result = true;
		try {
			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='grid-item']//input[@placeholder='Enter your Email']", "xpath", 10);
			if (result==true) {
				cfObj.commonSetTextTextBox(MyProfileORobj.getEmail_inputBox(),strEmail);
			}else{
				MyProfileORobjMsgList.add("Not able to enter email");
				return result;
			}
		} catch (Exception e) {
			result = false;
			MyProfileORobjMsgList.add("ProfileEnterEmail_Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean enterOtp(WebDriver driver,String strOtp) {
		boolean result = true;
		try {
			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='grid-item']/label[contains(text(),'Enter OTP')]", "xpath", 10);
			if (result==true) {
				cfObj.commonSetTextTextBox(MyProfileORobj.getOTP_inputBox(), String.valueOf(strOtp));
			}else {
				MyProfileORobjMsgList.add("OTP Input Box is Not Visible");
			}

		} catch (Exception e) {
			result = false;
			MyProfileORobjMsgList.add("enterOtp_Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean VerifyButtonOf_OTP(WebDriver driver) {
		boolean result = true;
		try {
			
			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "(//div[@class='grid-item']//button[contains(text(),'Verify')])", "xpath", 10);
			if (result==true) {
				cfObj.commonClick(MyProfileORobj.getVerifyOTP_Button());
			}else {
				MyProfileORobjMsgList.add("Verify Button is Not Visible");
			}
		} catch (Exception e) {
			result = false;
			MyProfileORobjMsgList.add("VerifyButtonOf_OTP_Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean ProfileEnterMobileNumber(WebDriver driver,String strMobileNumber) {
		boolean result = true;
		try {
			result =cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='grid-item']//input[@placeholder='Mobile Number']", "xpath", 10);
			if (result==true) {
				cfObj.commonSetTextTextBox(MyProfileORobj.getMobileNumber_inputBox(),strMobileNumber); 
			}else {
				MyProfileORobjMsgList.add("Not able to enter Mobile-Number");
				return result;
			}
		} catch (Exception e) {
			result = false;
			MyProfileORobjMsgList.add("ProfileEnterMobileNumber_Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean verifyProfileDetails() {
		boolean result = true;
		try {
			String verifyName=MyProfileORobj.getNewYourName_input().getAttribute("value");
			result=verifyName.equals("Test");
			if(result==true) {
				MyProfileORobjMsgList.add("Your Name is Equal");
			}
			else {
				MyProfileORobjMsgList.add("Your Name is not Equal");
				return result;
			}
			
			String verifyCity=MyProfileORobj.getNewYourCity_input().getAttribute("value");
			result=verifyCity.equals("GURUGRAM");
			if(result==true) {
				MyProfileORobjMsgList.add("Your City is Equal");
			}
			else {
				MyProfileORobjMsgList.add("Your City is not Equal");
				return result;
			}
			
			String verifyAddress=MyProfileORobj.getNewAddress_input().getAttribute("value");
			result=verifyAddress.equals("Gurgaon");
			if(result==true) {
				MyProfileORobjMsgList.add("Your Address is Equal");
			}
			else {
				MyProfileORobjMsgList.add("Your Address is not Equal");
				return result;
			}
			
			String verifyState=MyProfileORobj.getNewYourState_input().getAttribute("value");
			result=verifyState.equals("HARYANA");
			if(result==true) {
				MyProfileORobjMsgList.add("Your State is Equal");
			}
			else {
				MyProfileORobjMsgList.add("Your State is not Equal");
				return result;
			}			
			
			String verifyPinCode=MyProfileORobj.getNewPinCode_input().getAttribute("value");
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
			MyProfileORobjMsgList.add("verifyProfileDetails_Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean ClickOnAccountInfo(WebDriver driver) {
		boolean result = true;
		homeUtilObj=new HomePageUtil(driver);
		try {
			// click on DropDown button
			result = homeUtilObj.verifyMyProfile(driver);
			if (!result) {
				MyProfileORobjMsgList.add("Profile  is not open");
				return result;
			}
			
			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='navbar-wrapper']/div[contains(text(),'My Profile')]", "xpath", 1);
			if (result==true) {
				//Click On My-Profile Section
				cfObj.commonClick(MyProfileORobj.getMyProfile_icon());
			}else {
				MyProfileORobjMsgList.add("My-Profile_icon is not visible");
			}
			
			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='navbar-wrapper']/div[contains(text(),'My Exam')]", "xpath", 1);
			if (result==true) {
				//Click On My-Exam Section
				cfObj.commonClick(MyProfileORobj.getMyExam_icon());				
			}else {
				MyProfileORobjMsgList.add("My-Exam Section is not visible");
            	return result;
			}
			
			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='navbar-wrapper']/div[contains(text(),'Account')]", "xpath", 10);
			if (result==true) {
				//Click On My-Account Section
				cfObj.commonClick(MyProfileORobj.getMyAccount_icon());
			}else {
				MyProfileORobjMsgList.add("My-Account Section is not visible");
            	return result;
			}
			
			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='navbar-wrapper']/div[contains(text(),'Transactions')]", "xpath", 1);
			if (result==true) {
				result=TransactionsSection(driver);
				if(!result) {
					MyProfileORobjMsgList.add("Transactions Section is Not Executed");
					return result;
				}
			}else {
				MyProfileORobjMsgList.add("Transactions Section is not visible");
            	return result;
			}
			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='navbar-wrapper']/div[contains(text(),'Impersonate')]", "xpath", 1);
			if (result==true) {
				//Click On Impersonate Section
				cfObj.commonClick(MyProfileORobj.getImpersonate());
				
				cfObj.commonSetTextTextBox(MyProfileORobj.getImpersonate_MobileInputBox(),"8709545754");
				result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//button[contains(text(),'Impersonate Profile')]", "xpath", 10);
				if (result==true) {
					cfObj.commonClick(MyProfileORobj.getImpersonateProfile_Button());
					Thread.sleep(10000);
				}else {
					MyProfileORobjMsgList.add("Impersonate_Profile Button is not visible");
					return result;
				}
			}else {
				result=true;
				MyProfileORobjMsgList.add("You have not any access to Impersonate a User");
				return result;
			}
					
		} catch (Exception e) {
			result = false;
			MyProfileORobjMsgList.add("verifyProfileInput_Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean UpdateExamPreference(WebDriver driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='App exam-modal']/div/div[@class='examContainer']/div/div", "xpath", 30);
			if (result==true) {
				cfObj.commonClick(MyProfileORobj.getListOfUpdateExamPreferenceCOurses().get(2));
			}else {
				MyProfileORobjMsgList.add("Exam Preference is not Visible");
				return result;
			}
			
		} catch (Exception e) {
			result = false;
			MyProfileORobjMsgList.add("UpdateExamPreference_Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean VerifyButtonOf_OTP_Mobile(WebDriver driver) {
		boolean result = true;
		try {
			
			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "(//div[@class='grid-item']//button[contains(text(),'Verify')])[2]", "xpath", 10);
			if (result==true) {
				cfObj.commonClick(MyProfileORobj.getVerifyOTP_Button_Mobile());
			}else {
				MyProfileORobjMsgList.add("Verify Button is Not Visible");
			}
		} catch (Exception e) {
			result = false;
			MyProfileORobjMsgList.add("VerifyButtonOf_OTP_Mobile_Exception: " + e.getMessage());
		}
		return result;
	}
		
	
}
