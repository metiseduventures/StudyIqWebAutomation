package applicationUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import apiUtil.HomePageApiUtil;
import apiUtil.OtpUtil;
import pageObject.HomePage_OR;
import pageObject.LibraryPage_OR;
import pojo.TestData;
import pojo.menuList.MenuList;
import util.Common_Function;
import util.ConfigFileReader;

public class HomePageUtil {

	HomePage_OR homePageORObj;
	LibraryPage_OR LibraryORobj;
	public List<String> homePageMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();
	OtpUtil otpUtilObj;
	ConfigFileReader fileReader = new ConfigFileReader();
	LibraryPageUtil librayUtilObj;
	CoursePageUtil coursePageUtilObj;
	MyProfileUtil myProfileUtilObj;

	public HomePageUtil(WebDriver driver) {

		homePageORObj = new HomePage_OR();
		PageFactory.initElements(driver, homePageORObj);
	}

	public boolean validateHomePage(WebDriver driver, String strMobileNumber) {
		boolean result = true;
		try {
			result = VerifyContacts(driver);
			if (!result) {
				homePageMsgList.add("Contacts is not Verify");
				return result;
			}

			result = clickOnSlideItem(driver);
			if (!result) {
				homePageMsgList.add("Slide Item is not Available");
				return result;
			}

			result = clickOnExploreButton(driver);
			if (!result) {
				homePageMsgList.add("Explore_Button is not Working");
				return result;
			}

			result = verifyLogin(driver, strMobileNumber);
			if (!result) {
				homePageMsgList.add("Failed to Login");
				return result;
			}

			result = VerifyListOfCourse(driver);
			if (!result) {
				homePageMsgList.add("Course Link is not Working");
				return result;
			}

			result = clickOnMyLibraryButton(driver);
			if (!result) {
				homePageMsgList.add("My-Library Button is not Working");
				return result;
			}

			result = clickOnMyOfferButton(driver);
			if (!result) {
				homePageMsgList.add("My-Offer Button is not Working");
				return result;
			}
			result = clickOnMyprofileButton(driver);
			if (!result) {
				homePageMsgList.add("My-profile Button is not Working");
				return result;
			}
			result = clickOnLogOutButton(driver);
			if (!result) {
				homePageMsgList.add("Log-out Button is not Working");
				return result;
			}
			result = clickOnPoketNewApp(driver);
			if (!result) {
				homePageMsgList.add("Poket New App Button is not Working");
				return result;
			}

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("validateHomePage_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean VerifyContacts(WebDriver driver) {
		boolean result = true;
		try {
			cfObj.commonClick(homePageORObj.getClose_Notification());
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='we_are_text']/span",
					"xpath", 1);
			if (result == true) {
				cfObj.commonClick(homePageORObj.getContactClickHereButton());
				Set<String> windowsId = driver.getWindowHandles();
				Iterator<String> itr = windowsId.iterator();
				String defaultwindowId = itr.next();
				String childwindowId = itr.next();
				driver.switchTo().window(childwindowId);
				String title = "StudyIQ";
				result = driver.getTitle().contains(title);
				if (result == true) {
					homePageMsgList.add("StudyIQ Application Download Website");
				} else {
					homePageMsgList.add("Not StudyIQ Application Download Website");
					return result;
				}
				driver.close();
				driver.switchTo().window(defaultwindowId);
			} else {
				homePageMsgList.add("Contacts is not Available");
				return result;

			}
		} catch (Exception e) {
			result = false;
			homePageMsgList.add("clickOnDropDown_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyLogin(WebDriver driver, String strMobileNumber) {
		boolean result = true;
		String strOtp = null;
		try {
			// click on login/register button
			result = clickOnLoginRegisterButton(driver);
			if (!result) {
				homePageMsgList.add(result + " Login/Register Button is not working");
				return result;
			}

			// enter mobile number
			result = enterMobileNumber(strMobileNumber);
			if (!result) {
				return result;
			}

			// click on get OTP
			result = clickOnContinueButton();
			if (!result) {
				return result;
			}

			otpUtilObj = new OtpUtil();
			strOtp = otpUtilObj.getOtp(strMobileNumber, false);
			if (strOtp == null) {
				homePageMsgList.add("Error in getting otp");
				return false;
			}

			// enter OTP
			result = enterOtp(strOtp);
			if (!result) {
				return result;
			}
			

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

			result = cfObj.commonSetTextTextBox(homePageORObj.getlistTextpHONENumber().get(0), strMobileNo);
			if (!result) {
				homePageMsgList.add("Not able to enter mobile number");
				return result;
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
				cfObj.commonSetTextTextBox(homePageORObj.getlistTextOtpBox().get(i), String.valueOf(strOtp.charAt(i)));
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

			cfObj.commonClick(homePageORObj.getBtnContinue().get(0));

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
				return result;
			}

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("clickOnVerifyButton_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean verifySignUp(WebDriver driver) {
		boolean result = true;
		try {

			// click on login/register button
			result = clickOnLoginRegisterButton(driver);
			if (!result) {
				return result;
			}

			result = doSignUp();
			if (!result) {
				return result;
			}

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
				return result;
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
				return result;
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
			cfObj.commonSetTextTextBox(homePageORObj.searchItem(), strSearchItem);

			List<WebElement> links = homePageORObj.searchElements();

			for (int i = 0; i < links.size();) {
				links.get(i).click();
				break;
			}
		} catch (Exception e) {
			result = false;
			homePageMsgList.add("clickInputSearch_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnCourseOnHomePage(WebDriver driver) {
		boolean result = true;
		try {
			if (homePageORObj.getListCourse().size() == 0) {
				homePageMsgList.add("Courses are not display on the home page");
				return false;
			}
			// Select first course
			cfObj.commonClick(homePageORObj.getListCourse().get(0));

			// wait for course detail page to be opened
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".course_basic_info_wrapper", "css", 30);
			if (!result) {
				homePageMsgList.add("Course detail page not opened");
				return result;
			}

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("clickOnCourseOnHomePage_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnSmartCourseOnHomePage(WebDriver driver) {
		boolean result = true;
		try {
			if (homePageORObj.getListCourse().size() == 0) {
				homePageMsgList.add("Courses are not display on the home page");
				return false;
			}
			// Select first course
			cfObj.commonClick(homePageORObj.getListCourse().get(0));

			// wait for course detail page to be opened
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".course_basic_info_wrapper", "css", 30);
			if (!result) {
				homePageMsgList.add("Course detail page not opened");
				return result;
			}

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("clickOnCourseOnHomePage_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnMicroCourseOnHomePage(WebDriver driver) {
		boolean result = true;
		try {
			if (homePageORObj.getListCourse().size() == 0) {
				homePageMsgList.add("Courses are not display on the home page");
				return false;
			}
			// Select first course
			cfObj.commonClick(homePageORObj.getListCourse().get(42));

			// wait for course detail page to be opened
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".course_basic_info_wrapper", "css", 30);
			if (!result) {
				homePageMsgList.add("Course detail page not opened");
				return result;
			}

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("clickOnMicroCourseOnHomePage_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnBookOnHomePage(WebDriver driver) {
		boolean result = true;
		try {
			driver.navigate().to(fileReader.getBaseUrlWeb() + "books");
			if (homePageORObj.getlistOfBook().size() == 0) {
				homePageMsgList.add("Books are not display on the home page");
				return false;
			}
			// Select first book course
			cfObj.commonClick(homePageORObj.getlistOfBook().get(0));

			// wait for course detail page to be opened
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".course_basic_info_wrapper", "css", 30);
			if (!result) {
				homePageMsgList.add("Course detail page not opened");
				return result;
			}

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("clickOnBookOnHomePage_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyMyProfile(WebDriver driver) {
		boolean result = true;
		try {

			// click on DropDown button
			result = clickOnDropDown(driver);
			if (!result) {
				homePageMsgList.add("DropDown is not working");
				return result;
			}

			// click on My Profile button
			result = clickOnMyProfile(driver);
			if (result) {
				homePageMsgList.add("My Profile Button is not working");
				return result;
			}

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("verifyMyProfile_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnDropDown(WebDriver driver) {
		boolean result = true;
		try {
			cfObj.commonClick(homePageORObj.getDropDown_Button());

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("clickOnDropDown_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnMyProfile(WebDriver driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "(//a[@role='button'])[2]", "xpath", 30);
			if (result==true) {
				cfObj.commonClick(homePageORObj.getMyProfile_Button());
			}else {
				homePageMsgList.add("My-Profile Button is not Visible");
			    return result;	
			}

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("clickOnMyProfile_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnBestSelling_Button(int index) {
		boolean result = true;
		try {

			cfObj.commonClick(homePageORObj.getBestSelling_Button().get(index));

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("clickOnBestSelling_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean doSignUp() {
		boolean result = true;
		String strOtp = null;
		String strMobileNumber = null;
		try {
			strMobileNumber = Common_Function.randomPhoneNumber(10, "3");
			// enter mobile number
			result = enterMobileNumber(strMobileNumber);

			if (!result) {
				return result;
			}
			// click on get OTP

			result = clickOnContinueButton();
			if (!result) {
				return result;
			}
			otpUtilObj = new OtpUtil();

			strOtp = otpUtilObj.getOtp(strMobileNumber, true);

			// enter OTP
			result = enterOtp(strOtp);
			if (!result) {
				return result;
			}
			// Enter name

			result = enterName("Test");
			if (!result) {
				return result;
			}

			// Enter Email
			result = enterEmail("Test" + strMobileNumber + "@gmail.com");
			if (!result) {
				return result;
			}
			// Click on Continue button

			result = clickOnVerifyButton();
			if (!result) {
				return result;
			}
			
			//Exam Preference 
			
			result = VerifyExamPreference();
			if (!result) {
				return result;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public boolean verifyHomePageMenuList(WebDriver driver) {
		boolean result = true;
		MenuList menuListObj;
		HomePageApiUtil homePageAPiUtilObj;
		try {

			// get menu list from api
			homePageAPiUtilObj = new HomePageApiUtil();
			menuListObj = homePageAPiUtilObj.getHomePageMenu();
			if (menuListObj == null) {
				homePageMsgList.add("Error in home page menu list api");
				return false;
			}

			if (menuListObj.getData().size() != homePageORObj.getNavBar_Item().size()) {
				homePageMsgList.add("Mismatch in menu list item on web and api");
				return false;
			}
			for (int i = 1; i < homePageORObj.getNavBar_Item().size(); i++) {
				cfObj.commonClick(homePageORObj.getNavBar_Item().get(i));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".course_category", "css", 20);
				if (!result) {
					homePageMsgList.add(menuListObj.getData().get(i).getMenuSlug() + "page is not opened");
					result = false;
				}
			}
			cfObj.commonClick(homePageORObj.getHomePage());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//div[@class='courses navbar-nav']/a[contains(text(),'Courses')]", "xpath", 20);
			if (result == true) {
				cfObj.commonClick(homePageORObj.getCourseButton());
				cfObj.commonClick(homePageORObj.getHomePage());
				cfObj.commonClick(homePageORObj.getClose_Notification());
			} else {
				homePageMsgList.add("Course Button is Not Working");
				return result;
			}

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("verifyHomePageMenuList_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnSlideItem(WebDriver driver) {
		boolean result = true;
		try {
			// Click On Backward Button
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//div[@class='slick-arrow slick-prev left_arrow_wrapper']/img", "xpath", 20);
			if (result == true) {
				for (int j = 0; j < 3; j++) {
					cfObj.commonClick(homePageORObj.getBackwardArrow());
				}

			} else {
				homePageMsgList.add("Backward Button is not Available");
				return result;
			}

			cfObj.commonClick(homePageORObj.getSlideCoursesOne());
			Thread.sleep(21000);
			cfObj.commonClick(homePageORObj.getHomePage());
			cfObj.commonClick(homePageORObj.getClose_Notification());
			// Click On Farward Button
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//div[@class='slick-arrow slick-next right_arrow_wrapper']/img", "xpath", 20);
			if (result == true) {
				for (int i = 0; i < 3; i++) {
					cfObj.commonClick(homePageORObj.getFarwardArrow());
				}
			} else {
				homePageMsgList.add("Farward Button is not Available");
				return result;
			}
			cfObj.commonClick(homePageORObj.getHomePage());

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("clickOnSlideItem_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnExploreButton(WebDriver driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"button[class='ant-btn ant-btn-default expl_crs_btn']", "css", 20);
			if (result == true) {
				cfObj.commonClick(homePageORObj.getExploreCourses());
				cfObj.commonClick(homePageORObj.getHomePage());
				cfObj.commonClick(homePageORObj.getClose_Notification());
			} else {
				homePageMsgList.add("Explore_Courses Button is not Working");
				return result;
			}

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("clickOnExploreButton_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnMyLibraryButton(WebDriver driver) {
		boolean result = true;
		librayUtilObj = new LibraryPageUtil(driver);
		try {
			result = librayUtilObj.DropDownLibrary_Course(driver);
			if (!result) {
				cfObj.commonClick(homePageORObj.getDropDown_Button());
				cfObj.commonClick(homePageORObj.getMyLibraryButton());
				result = cfObj.commonWaitForElementToBeVisible(driver, homePageORObj.getLibraryEmptyText(), 20);
				if (result == true) {
					cfObj.commonClick(homePageORObj.getLibraryExplore_Courses());
					cfObj.commonClick(homePageORObj.getHomePage());
					cfObj.commonClick(homePageORObj.getClose_Notification());
					return result;
				} else {
					homePageMsgList.add("Explore Button is not Working");
					return result;
				}
			}

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("clickOnMyLibraryButton_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnMyOfferButton(WebDriver driver) {
		boolean result = true;
		try {
			result = clickOnDropDown(driver);
			if (!result) {
				homePageMsgList.add("DropDown is not Working");
				return result;
			}
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//a[contains(text(),'My Offers')]",
					"xpath", 20);
			if (result == true) {
				cfObj.commonClick(homePageORObj.getMyOffer());
				cfObj.commonClick(homePageORObj.getHomePage());
				cfObj.commonClick(homePageORObj.getClose_Notification());
			} else {
				homePageMsgList.add("Offer Button is not Working");
				return result;
			}

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("clickOnMyOfferButton_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnLogOutButton(WebDriver driver) {
		boolean result = true;
		try {
			result = clickOnDropDown(driver);
			if (!result) {
				homePageMsgList.add("DropDown is not Working");
				return result;
			}
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//a[contains(text(),'Logout')]", "xpath",
					20);
			if (result == true) {
				cfObj.commonClick(homePageORObj.getLogOutButton());
			} else {
				homePageMsgList.add("Log-Out Button is not Working");
				return result;
			}

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("clickOnLogOutButton_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean VerifyTestimonial(WebDriver driver) {
		boolean result = true;
		try {
			cfObj.commonClick(homePageORObj.getViewAllButton_ofTestimonial());
			List<WebElement> L4 = homePageORObj.getTestimonialVideo();
			for (int i = 0; i < 5; i++) {
				cfObj.commonClick(L4.get(i));
				driver.navigate().back();
				cfObj.commonClick(homePageORObj.getClose_Notification());
				cfObj.commonClick(homePageORObj.getViewAllButton_ofTestimonial());
			}
			cfObj.commonClick(homePageORObj.getViewAllButton_ofTestimonial());
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageORObj.getTestimonialVideo().get(1), 20);
			if (result == true) {
				cfObj.commonClick(homePageORObj.getTestimonialVideo().get(1));
				cfObj.commonClick(homePageORObj.getVideo());
				driver.navigate().back();
				cfObj.commonClick(homePageORObj.getClose_Notification());
			} else {
				homePageMsgList.add("Video is not Available");
				return result;
			}
			cfObj.commonClick(homePageORObj.getHomePage());

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("VerifyTestimonial_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean VerifySocialMediaIcon(WebDriver driver) {
		boolean result = true;
		try {
			result = clickOnYoutube(driver);
			if (!result) {
				homePageMsgList.add("Youtube Icon Is Not Working");
				return result;
			}

			result = clickOnFacebook(driver);
			if (!result) {
				homePageMsgList.add("Facebook Icon Is Not Working");
				return result;
			}

			result = clickOnTelegram(driver);
			if (!result) {
				homePageMsgList.add("Teleagram Icon Is Not Working");
				return result;
			}

			result = clickOnInstagram(driver);
			if (!result) {
				homePageMsgList.add("Instagram Icon Is Not Working");
				return result;
			}

			result = clickOnTwitter(driver);
			if (!result) {
				homePageMsgList.add("Twitter Icon Is Not Working");
				return result;
			}

			result = clickOnLinkedIn(driver);
			if (!result) {
				homePageMsgList.add("LinkedIn Icon Is Not Working");
				return result;
			}

			result = clickOnTumblr(driver);
			if (!result) {
				homePageMsgList.add("Tumblr Icon Is Not Working");
				return result;
			}

			result = clickOnWhatsApp(driver);
			if (!result) {
				homePageMsgList.add("WhatsApp Icon Is Not Working");
				return result;
			}

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("VerifySocialMediaIcon_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnYoutube(WebDriver driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "(//div[@class='social-icons']/span)[1]",
					"xpath", 20);
			if (result == true) {
				cfObj.commonClick(homePageORObj.getStudyIQ_Youtube_SocialIcon());
				Thread.sleep(10000);
				Set<String> windowsId = driver.getWindowHandles();
				Iterator<String> itr = windowsId.iterator();
				String defaultwindowId = itr.next();
				String childwindowId = itr.next();
				driver.switchTo().window(childwindowId);
				driver.navigate().refresh();
				Thread.sleep(10000);
				String title = "StudyIQ IAS - YouTube";
				result = driver.getTitle().contains(title);
				if (result == true) {
					homePageMsgList.add("StudyIQ Youtube Website");
				} else {
					homePageMsgList.add("Not StudyIQ Youtube Website");
					return result;
				}
				driver.close();
				driver.switchTo().window(defaultwindowId);
				Thread.sleep(10000);
			} else {
				homePageMsgList.add("StudyIQ Youtube Social Icon Button is not Available");
				return result;
			}

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("clickOnYoutube_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnFacebook(WebDriver driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "(//div[@class='social-icons']/span)[2]",
					"xpath", 20);
			if (result == true) {
				cfObj.commonClick(homePageORObj.getStudyIQ_Facebook_SocialIcon());
				Thread.sleep(10000);
				Set<String> windowsId = driver.getWindowHandles();
				Iterator<String> itr = windowsId.iterator();
				String defaultwindowId = itr.next();
				String childwindowId = itr.next();
				driver.switchTo().window(childwindowId);
				driver.navigate().refresh();
				Thread.sleep(10000);
				String title = "Study IQ - Home | Facebook";
				result = driver.getTitle().contains(title);
				if (result == true) {
					homePageMsgList.add("StudyIQ Facebook Website");
				} else {
					homePageMsgList.add("Not StudyIQ Facebook Website");
					return result;
				}
				driver.close();
				driver.switchTo().window(defaultwindowId);
				Thread.sleep(10000);
			} else {
				homePageMsgList.add("StudyIQ Facebook Social Icon Button is not Available");
				return result;
			}

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("clickOnFacebook_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean clickOnTelegram(WebDriver driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "(//div[@class='social-icons']/span)[3]",
					"xpath", 20);
			if (result == true) {
				cfObj.commonClick(homePageORObj.getStudyIQ_Telegram_SocialIcon());
				Thread.sleep(10000);
				Set<String> windowsId = driver.getWindowHandles();
				Iterator<String> itr = windowsId.iterator();
				String defaultwindowId = itr.next();
				String childwindowId = itr.next();
				driver.switchTo().window(childwindowId);
				driver.navigate().refresh();
				Thread.sleep(10000);
				String title = "Telegram: Contact @Studyiqeducation";
				result = driver.getTitle().contains(title);
				if (result == true) {
					homePageMsgList.add("StudyIQ Telegram Website");
				} else {
					homePageMsgList.add("Not StudyIQ Telegram Website");
					return result;
				}
				driver.close();
				driver.switchTo().window(defaultwindowId);
				Thread.sleep(10000);
			} else {
				homePageMsgList.add("StudyIQ Telegram Social Icon Button is not Available");
				return result;
			}

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("clickOnTelegram_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean clickOnInstagram(WebDriver driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "(//div[@class='social-icons']/span)[4]",
					"xpath", 20);
			if (result == true) {
				cfObj.commonClick(homePageORObj.getStudyIQ_Instagram_SocialIcon());
				Thread.sleep(10000);
				Set<String> windowsId = driver.getWindowHandles();
				Iterator<String> itr = windowsId.iterator();
				String defaultwindowId = itr.next();
				String childwindowId = itr.next();
				driver.switchTo().window(childwindowId);
				driver.navigate().refresh();
				Thread.sleep(10000);
				String title = "Instagram";
				result = driver.getTitle().contains(title);
				if (result == true) {
					homePageMsgList.add("StudyIQ Instagram  Website");
				} else {
					homePageMsgList.add("Not StudyIQ Instagram Website");
					return result;
				}
				driver.close();
				driver.switchTo().window(defaultwindowId);
				Thread.sleep(10000);
			} else {
				homePageMsgList.add("StudyIQ Instagram Social Icon Button is not Available");
				return result;
			}

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("clickOnInstagram_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean clickOnTwitter(WebDriver driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "(//div[@class='social-icons']/span)[5]",
					"xpath", 20);
			if (result == true) {
				cfObj.commonClick(homePageORObj.getStudyIQ_Twitter_SocialIcon());
				Thread.sleep(10000);
				Set<String> windowsId = driver.getWindowHandles();
				Iterator<String> itr = windowsId.iterator();
				String defaultwindowId = itr.next();
				String childwindowId = itr.next();
				driver.switchTo().window(childwindowId);
				driver.navigate().refresh();
				Thread.sleep(10000);
				String title = driver.getTitle();
				result = driver.getTitle().contains(title);
				if (result == true) {
					homePageMsgList.add("StudyIQ Twitter Website");
				} else {
					homePageMsgList.add("Not StudyIQ Twitter Website");
					return result;
				}
				driver.close();
				driver.switchTo().window(defaultwindowId);
				Thread.sleep(10000);
			} else {
				homePageMsgList.add("StudyIQ Twitter Social Icon Button is not Available");
				return result;
			}

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("clickOnTwitter_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean clickOnLinkedIn(WebDriver driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "(//div[@class='social-icons']/span)[6]",
					"xpath", 20);
			if (result == true) {
				cfObj.commonClick(homePageORObj.getStudyIQ_LinkedIn_SocialIcon());
				Thread.sleep(10000);
				Set<String> windowsId = driver.getWindowHandles();
				Iterator<String> itr = windowsId.iterator();
				String defaultwindowId = itr.next();
				String childwindowId = itr.next();
				driver.switchTo().window(childwindowId);
				driver.navigate().refresh();
				Thread.sleep(10000);
				String title = "LinkedIn";
				result = driver.getTitle().contains(title);
				if (result == true) {
					homePageMsgList.add("StudyIQ LinkedIn Website");
				} else {
					homePageMsgList.add("Not StudyIQ LinkedIn Website");
					return result;
				}
				driver.close();
				driver.switchTo().window(defaultwindowId);
				Thread.sleep(10000);
			} else {
				homePageMsgList.add("StudyIQ LinkedIn Social Icon Button is not Available");
				return result;
			}

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("clickOnLinkedIn_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean clickOnTumblr(WebDriver driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "(//div[@class='social-icons']/span)[7]",
					"xpath", 20);
			if (result == true) {
				cfObj.commonClick(homePageORObj.getStudyIQ_Tumblr_SocialIcon());
				Thread.sleep(10000);
				Set<String> windowsId = driver.getWindowHandles();
				Iterator<String> itr = windowsId.iterator();
				String defaultwindowId = itr.next();
				String childwindowId = itr.next();
				driver.switchTo().window(childwindowId);
				driver.navigate().refresh();
				Thread.sleep(10000);
				String title = "StudyIQ";
				result = driver.getTitle().contains(title);
				if (result == true) {
					homePageMsgList.add("StudyIQ Tumblr Website");
				} else {
					homePageMsgList.add("Not StudyIQ Tumblr Website");
					return result;
				}
				driver.close();
				driver.switchTo().window(defaultwindowId);
				Thread.sleep(10000);
			} else {
				homePageMsgList.add("StudyIQ Tumblr Social Icon Button is not Available");
				return result;
			}

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("clickOnTumblr_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean clickOnWhatsApp(WebDriver driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "(//span[@class='whatsapp'])[1]", "xpath",
					20);
			if (result == true) {
				cfObj.commonClick(homePageORObj.getwhatsapp_SocialIcon());
				Thread.sleep(10000);
				Set<String> windowsId = driver.getWindowHandles();
				Iterator<String> itr = windowsId.iterator();
				String defaultwindowId = itr.next();
				String childwindowId = itr.next();
				driver.switchTo().window(childwindowId);
				driver.navigate().refresh();
				Thread.sleep(10000);
				String title = "Share on WhatsApp";
				result = driver.getTitle().contains(title);
				if (result == true) {
					homePageMsgList.add("StudyIQ whatsapp Website");
				} else {
					homePageMsgList.add("Not StudyIQ whatsapp Website");
					return result;
				}
				driver.close();
				driver.switchTo().window(defaultwindowId);
				Thread.sleep(10000);
			} else {
				homePageMsgList.add("StudyIQ whatsapp Social Icon Button is not Available");
				return result;
			}

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("clickOnwhatsapp_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean clickOnPoketNewApp(WebDriver driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"(//button[@class='btn btn-dark btn-lg'])[1]", "xpath", 20);
			if (result == true) {
				cfObj.commonClick(homePageORObj.getStudyIQ_Poket_NewsApp());
                Thread.sleep(7000);
				Set<String> windowsId = driver.getWindowHandles();
				Iterator<String> itr = windowsId.iterator();
				String defaultwindowId = itr.next();
				String childwindowId = itr.next();
				driver.switchTo().window(childwindowId);
				String title = "Poket News";
				result = driver.getTitle().contains(title);
				if (result == true) {
					homePageMsgList.add("StudyIQ Poket-News App Website");
				} else {
					homePageMsgList.add("Not StudyIQ Poket-News Website");
					return result;
				}
				driver.close();
				driver.switchTo().window(defaultwindowId);
			} else {
				homePageMsgList.add("StudyIQ Poket-News App Button is not Available");
				return result;
			}

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("clickOnPoketNewApp_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean VerifyListOfCourse(WebDriver driver) {
		boolean result = true;
		coursePageUtilObj = new CoursePageUtil(driver);
		try {
			List<WebElement> L3 = homePageORObj.getListCourse();
			for (int i = 0; i < 2; i++) {
				cfObj.commonClick(L3.get(i));
				Thread.sleep(21000);
				cfObj.commonClick(homePageORObj.getCloseButton());
				driver.navigate().back();
				cfObj.commonClick(homePageORObj.getClose_Notification());
			}
			cfObj.commonClick(homePageORObj.getListCourse().get(1));
			Thread.sleep(21000);
			result = coursePageUtilObj.selectExamPrefrences(driver);
			if (!result) {
				return result;
			}
			result = coursePageUtilObj.clickOnBuyNow();
			if (!result) {
				return result;
			}

			result = coursePageUtilObj.PackageVerification();
			if (!result) {
				return result;
			}
			cfObj.commonClick(homePageORObj.getCloseButton());
			cfObj.commonClick(homePageORObj.getHomePage());
			cfObj.commonClick(homePageORObj.getClose_Notification());
		} catch (Exception e) {
			result = false;
			homePageMsgList.add("VerifyListOfCourse_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnMyprofileButton(WebDriver driver) {
		boolean result = true;
		myProfileUtilObj = new MyProfileUtil(driver);
		try {
			result = verifyMyProfile(driver);
			if (!result) {
				homePageMsgList.add("Fail to Open My Profile");
				return result;
			}

			result = myProfileUtilObj.validateMyProfile_Page(driver);
			if (!result) {
				homePageMsgList.add(" My Profile Page is not Validated ");
				return result;
			}

			result = myProfileUtilObj.clickOnUpdateProfile_button();
			if (!result) {
				homePageMsgList.add(" Update Profile Button is not Working");
				return result;
			}

			result = myProfileUtilObj.verifyInputDetails();
			if (!result) {
				homePageMsgList.add("Not Verify Input data");
				return result;
			}

			cfObj.commonClick(homePageORObj.getHomePage());
			cfObj.commonClick(homePageORObj.getClose_Notification());
		} catch (Exception e) {
			result = false;
			homePageMsgList.add("clickOnMyprofileButton_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnTestSeriesOnHomePage(WebDriver driver) {
		boolean result = true;
		try {
			driver.navigate().to(fileReader.getBaseUrlWeb() + "mock-test-series");
			if (homePageORObj.getlistOfBook().size() == 0) {
				homePageMsgList.add("test series are not display on the home page");
				return false;
			}
			// Select first book course
			cfObj.commonClick(homePageORObj.getlistOfBook().get(0));

			// wait for course detail page to be opened
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".course_basic_info_wrapper", "css", 30);
			if (!result) {
				homePageMsgList.add("Course detail page not opened");
				return result;
			}

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("clickOnTestSeriesOnHomePage_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnLiveCoursesOnHomePage(WebDriver driver) {
		boolean result = true;
		try {
			driver.navigate().to(fileReader.getBaseUrlWeb() + "upsc-live");
			if (homePageORObj.getlistOfBook().size() == 0) {
				homePageMsgList.add("Live_Course are not display on the home page");
				return false;
			}
			// Select first book course
			cfObj.commonClick(homePageORObj.getlistOfBook().get(1));

			// wait for course detail page to be opened
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".course_basic_info_wrapper", "css", 30);
			if (!result) {
				homePageMsgList.add("Course detail page not opened");
				return result;
			}

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("clickOnLiveCoursesOnHomePage_Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean clickOnVerifyButton() {
		boolean result = true;
		try {

			cfObj.commonClick(homePageORObj.getBtnVerify().get(0));

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("clickOnVerifyButton_Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean VerifyExamPreference() {
		boolean result = true;
		try {
			cfObj.commonClick(homePageORObj.getListOfExamPreferenceCOurses().get(0));

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("VerifyExamPreference_Exception: " + e.getMessage());
		}
		return result;
	}

}
