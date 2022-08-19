package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import apiUtil.OtpUtil;
import pageObject.HomePage_OR;
import pojo.TestData;
import util.Common_Function;
import util.ConfigFileReader;

public class HomePageUtil {

	HomePage_OR homePageORObj;
	public List<String> homePageMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();
	OtpUtil otpUtilObj;
	ConfigFileReader fileReader = new ConfigFileReader();

	public HomePageUtil(WebDriver driver) {

		homePageORObj = new HomePage_OR();
		PageFactory.initElements(driver, homePageORObj);
	}

	public boolean verifyLogin(WebDriver driver, String strMobileNumber) {
		boolean result = true;
		 String strOtp = null;
		try {
			//strMobileNumber = ConfigFileReader.strUserMobileNumber;	//Not needed but using
			
			// enter mobile number
			result = enterMobileNumber(strMobileNumber);
			if (result) {
				return result;
			}

			// click on get OTP

			result = clickOnContinueButton();

			if (!result) {
				return result;
			}
			otpUtilObj = new OtpUtil();

			strOtp = otpUtilObj.getOtp(strMobileNumber);

			// enter OTP
			result = enterOtp(strOtp);

			if (!result) {
				return result;
			}

			
			//Thread.sleep(18000);

			// Click on Continue button
			result = clickOnContinueButton();

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("verifyLogin_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean verifySearch(WebDriver driver, TestData testData) {
		boolean result = true;
		try {
			// click on search button
			result = searchItemOption(driver, testData.getCourseName());
			if (!result) {
				return result;
			}

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("verifySearch_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnLoginRegisterButton(WebDriver driver) {
		boolean result = true;
		try {

			cfObj.commonClick(homePageORObj.getListBtnLoginRegister().get(0));

			// Wait for mobile number pop to be opened

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".login-btn", "css", 30);
			if (!result) {
				homePageMsgList.add("Mobile number pop up is not opened when click on login/register button");
			}

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("clickOnLoginRegisterButton_Exception: " + e.getMessage());

		}
		return result;
	}

	public boolean enterMobileNumber(String strMobileNo) {
		boolean result = true;
		try {

			result = cfObj.commonSetTextTextBox(homePageORObj.getListTextMobileNumber().get(0), strMobileNo);
			if (!result) {
				homePageMsgList.add("Not able to enter mobile number");
			}
		} catch (Exception e) {
			result = false;
			homePageMsgList.add("enterMobileNumber_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean enterOtp(String strOtp) {
		boolean result = true;
		try {

			for (int i = 0; i <= 5; i++) {
				cfObj.commonSetTextTextBox(homePageORObj.getListTextOtp().get(i), String.valueOf(strOtp.charAt(i)));
			}

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("enterOtp_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnContinueButton() {
		boolean result = true;
		try {

			cfObj.commonClick(homePageORObj.getListBtnContinue().get(0));

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("clickOnContinueButton_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnVerifyButton(WebDriver driver) {
		boolean result = true;
		try {
			cfObj.commonClick(homePageORObj.getListBtnContinue().get(0));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "dropdown-basic-button", "id", 30);
			if (!result) {
				homePageMsgList.add("Home Page not opened after login");
			}

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("clickOnVerifyButton_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean verifySignUp(WebDriver driver) {
		boolean result = true;
		String strOtp = null;
		String strMobileNumber = null;
		try {
			strMobileNumber = Common_Function.randomPhoneNumber(10, "3");

			// click on login/register button
			result = clickOnLoginRegisterButton(driver);
			if (!result) {
				return result;
			}
			// enter mobile number
			result = enterMobileNumber(strMobileNumber);

			if (result) {
				return result;
			}

			// click on get OTP

			result = clickOnContinueButton();

			if (!result) {
				return result;
			}
			otpUtilObj = new OtpUtil();

			strOtp = otpUtilObj.getOtp(strMobileNumber);

			// enter OTP
			result = enterOtp(strOtp);

			if (!result) {
				return result;
			}

			// Enter name

			result = enterName("Test" + strMobileNumber);

			if (!result) {
				return result;
			}

			// Enter Email
			result = enterEmail("Test" + strMobileNumber + "@gmail.com");

			if (!result) {
				return result;
			}
			// Click on Continue button

			result = clickOnContinueButton();

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("verifySignUp_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean enterName(String strName) {
		boolean result = true;
		try {

			result = cfObj.commonSetTextTextBox(homePageORObj.getListTextName().get(0), strName);
			if (!result) {
				homePageMsgList.add("Not able to enter name");
			}
		} catch (Exception e) {
			result = false;
			homePageMsgList.add("enterName_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean enterEmail(String strEmail) {
		boolean result = true;
		try {

			result = cfObj.commonSetTextTextBox(homePageORObj.getListTextEmail().get(0), strEmail);
			if (!result) {
				homePageMsgList.add("Not able to enter email");
			}
		} catch (Exception e) {
			result = false;
			homePageMsgList.add("enterEmail_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean searchItemOption(WebDriver driver, String strSearchItem) {
		boolean result = true;
		try {
			homePageORObj.searchItem().sendKeys(strSearchItem);

			List<WebElement> links = homePageORObj.searchElements();

			for (int i = 0; i < links.size();) {
				links.get(7).click();
				break;
			}

			Thread.sleep(2000);

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("clickInputSearch_Exception: " + e.getMessage());
		}
		return result;
	}

}
