package applicationUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
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
		MyProfileUtil ObjMyProfileUtil = new MyProfileUtil(driver);
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

			result = VerifyMyLibCOurse_List(driver);
			if (!result) {
				homePageMsgList.add("My-Library Button is not Working");
				return result;
			}

			result = clickOnMyOfferButton(driver);
			if (!result) {
				homePageMsgList.add("My-Offer Button is not Working");
				return result;
			}
			result = ObjMyProfileUtil.ClickOnAccountInfo(driver);
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
				// Close Notification
				cfObj.commonClick(homePageORObj.getColseNotification());
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

			result = doSignUp(driver);
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
			cfObj.commonClick(homePageORObj.getListCourse().get(1));

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
			cfObj.commonClick(homePageORObj.getListCourse().get(2));

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
			cfObj.commonClick(homePageORObj.getlistOfBook().get(2));

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
			if (!result) {
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
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//a[contains(text(),'Account Info')]",
					"xpath", 30);
			if (result == true) {
				cfObj.commonClick(homePageORObj.getAccountInfo());
			} else {
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

	public boolean doSignUp(WebDriver driver) {
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
			Thread.sleep(5000);
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

			// Exam Preference

			result = VerifyExamPreference(driver);

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
			result = clickOnDropDown(driver);
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
			cfObj.commonClick(homePageORObj.getClose_Notification());

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("clickOnLogOutButton_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean VerifyTestimonial(WebDriver driver) {
		boolean result = true;
		try {
			JavascriptExecutor j1 = (JavascriptExecutor) driver;
			j1.executeScript("window.scrollBy(0,5000)", "");
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageORObj.getViewAllButton_ofTestimonial(), 20);
			if (result == true) {
				cfObj.commonClick(homePageORObj.getViewAllButton_ofTestimonial());
				cfObj.commonClick(homePageORObj.getTestimonialVideo().get(1));
				cfObj.commonClick(homePageORObj.getVideo());
				driver.navigate().back();
				cfObj.commonClick(homePageORObj.getClose_Notification());
				cfObj.commonClick(homePageORObj.getHomePage());
			} else {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "(//div[@class='t_img']/img)", "xpath",
						20);
				if (result == true) {
					cfObj.commonClick(homePageORObj.getListOfFronttestimonialVideo().get(0));
					cfObj.commonClick(homePageORObj.getPlayListOfFronttestimonialVideo());
				}
			}

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
				result = driver.getCurrentUrl().contains("youtube");
				if (!result) {
					homePageMsgList.add("Youtube Not is Open");
					return result;
				}
				driver.close();
				driver.switchTo().window(defaultwindowId);
				Thread.sleep(5000);
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
				result = driver.getCurrentUrl().contains("facebook");
				if (!result) {
					homePageMsgList.add("Facebook Not is Open");
					return result;
				}
				driver.close();
				driver.switchTo().window(defaultwindowId);
				Thread.sleep(5000);
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
				driver.close();
				driver.switchTo().window(defaultwindowId);
				Thread.sleep(5000);
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
				result = driver.getCurrentUrl().contains("instagram");
				if (!result) {
					homePageMsgList.add("Instagram Not is Open");
					return result;
				}
				driver.close();
				driver.switchTo().window(defaultwindowId);
				Thread.sleep(5000);
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
				result = driver.getCurrentUrl().contains("twitter");
				if (!result) {
					homePageMsgList.add("Twitter Not is Open");
					return result;
				}
				driver.close();
				driver.switchTo().window(defaultwindowId);
				Thread.sleep(5000);
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
				result = driver.getCurrentUrl().contains("linkedin");
				if (!result) {
					homePageMsgList.add("StudyIQ LinkedIn Not is Open");
					return result;
				}
				driver.close();
				driver.switchTo().window(defaultwindowId);
				Thread.sleep(5000);
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
				result = driver.getCurrentUrl().contains("tumblr");
				if (!result) {
					homePageMsgList.add("Tumblr Not is Open");
					return result;
				}
				driver.close();
				driver.switchTo().window(defaultwindowId);
				Thread.sleep(5000);
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
				result = driver.getCurrentUrl().contains("whatsapp");
				if (!result) {
					homePageMsgList.add("whatsapp Not is Open");
					return result;
				}
				driver.close();
				driver.switchTo().window(defaultwindowId);
				Thread.sleep(5000);
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
				cfObj.commonClick(homePageORObj.getCloseButton());
				driver.navigate().back();
				cfObj.commonClick(homePageORObj.getClose_Notification());
			}
			cfObj.commonClick(homePageORObj.getListCourse().get(1));
			Thread.sleep(5000);
//			result = coursePageUtilObj.selectExamPrefrences(driver);
//			if (!result) {
//				return result;
//			}
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//button[contains(text(),'Buy Now')]",
					"xpath", 20);
			if (result == true) {
				result = coursePageUtilObj.clickOnBuyNow();
				if (result) {
					result = coursePageUtilObj.PackageVerification();
					if (!result) {
						return result;
					}
					cfObj.commonClick(homePageORObj.getCloseButton());
					cfObj.commonClick(homePageORObj.getHomePage());
					cfObj.commonClick(homePageORObj.getClose_Notification());
				} else {
					return result;
				}
			} else {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//button[contains(text(),'Start My Course')]", "xpath", 20);
				if (result == true) {
					cfObj.commonClick(homePageORObj.getStartMyCourse_Button());
					cfObj.commonClick(homePageORObj.getHomePage());
					cfObj.commonClick(homePageORObj.getClose_Notification());
				}

			}
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
			cfObj.commonClick(homePageORObj.getlistOfBook().get(1));

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
			driver.navigate().to(fileReader.getBaseUrlWeb() + fileReader.getliveClassMenuUrl());
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

	public boolean VerifyExamPreference(WebDriver driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='inputContainer']", "xpath",
					30);
			if (result == true) {
				cfObj.commonClick(homePageORObj.getListOfExamPreferenceCOurses().get(0));
			} else {
				result = true;
				homePageMsgList.add("Exam Preference is not Visible");
				return result;
			}
		} catch (Exception e) {
			result = false;
			homePageMsgList.add("VerifyExamPreference_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean VerifyMyLibCOurse_List(WebDriver driver) {
		boolean result = true;
		librayUtilObj = new LibraryPageUtil(driver);
		try {
			result = librayUtilObj.clickOnDropDownLibcourse(driver);
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
			homePageMsgList.add("VerifyMyLibCOurse_List_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean navigateToMyLibrary(WebDriver driver) {
		boolean result = true;
		try {
			
			result = clickOnDropDown(driver);		
					//result = clickOnDropDown(driver);
			cfObj.commonClick(homePageORObj.getMyLibraryButton());
			//driver.navigate().to(fileReader.getBaseUrlWeb() + "my-library");
			// wait for library to be opened
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".listing-header-container", "css", 10);
			if (!result) {
				homePageMsgList.add("My library page is not opened from home page");
				return result;
			}

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("navigateToMyLibrary_Exception: " + e.getMessage());
		}
		return result;
	}
	
	//Check Negative Scenario Of Login Case
	public boolean verifyLoginFailCase(WebDriver driver) {
		boolean result = true;
		String strMobileNumber0="9135442893",strMobileNumber1="8709545754";
		try {
			
			//Check Without Mobile no.
			result=checkWithoutMobileNumber(driver);
			if(!result) {
				homePageMsgList.add("not Verify Through Mobile No..");
				return result;
			}
			
			// Try many Attempt for OTP 
			result=VerifyOTPPage(driver,strMobileNumber0,strMobileNumber1);
			if(!result) {
				homePageMsgList.add("There is No any Limition Attempt for OTP page");
				return result;
			}
			
			// Login after Failed Attempt(check Error Massage)
			result=verifyLogin_AfterFaliedAttempt(driver,strMobileNumber1);
			if(!result) {
				homePageMsgList.add("Not Login after Failed Logn");
				return result;
			}
			
			// Verify Wrong OTP
			result=VerifyWrongOTP(driver,strMobileNumber0);
			if(!result) {
				homePageMsgList.add("Wrong OTP Not Verify");
				return result;
			}
			
			Thread.sleep(121000);
			
			//Check Login After 2Min..(After Failed Attempt)
			result=verifyLoginAfter_2min(driver,strMobileNumber0);
			if(!result) {
				homePageMsgList.add("Login is Not Verify");
				return result;
			}

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("verifyLogin_Exception: " + e.getMessage());
		}

		return result;
	}
	
	public boolean VerifyOTPPage(WebDriver driver,String strMobileNumber,String strMobileNumber1) {
		boolean result=true;
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

			// click on Continue_Button
			result = clickOnContinueButton();
			if (!result) {
				return result;
			}
			
			// Verify Attempt
			result=VerifyNumberOfAttempt(driver,strMobileNumber1);
			if(!result) {
				return result;
			}
			
           // close on Login_Page
           result=closeLoginPage(driver);
		   if(result) {
			  homePageMsgList.add("Login page is Not Closed");
			  return result;
		    } 
			
		}catch(Exception e) {
			result=false;
			homePageMsgList.add("VerifyOTPPage: " + e.getMessage());
		}
	return result;
	}
	
	public boolean verifyLogin_AfterFaliedAttempt(WebDriver driver, String strMobileNumber) {
		boolean result = true;
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
			
			// check Error Massage 
			result = checkErrorMassage(driver);
			if (!result) {
				homePageMsgList.add("Error Massage is Not Visible");
				return result;
			}
			
			// close on Login_Page
			result=closeLoginPage(driver);
			if(!result) {
				homePageMsgList.add("Login page is Not Closed");
				return result;
			}
			
		} catch (Exception e) {
			result = false;
			homePageMsgList.add("verifyLogin_AfterFaliedAttempt_Exception: " + e.getMessage());
		}

		return result;
	}
	
	public boolean checkErrorMassage(WebDriver driver) {
		boolean result = true;
		try {
			
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[contains(text(),\"You've attempted too many times, Retry after 2 mins,\")]", "xpath", 5);
			if (result) {
				return result;
			}else {
				result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[contains(text(),'User crossed registration limit')]", "xpath", 5);
				if(result) {
					return result;
				}else {
					return result;
				}
				
			}

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("checkErrorMassage_Exception: " + e.getMessage());

		}
		return result;
	}
	
	public boolean VerifyWrongOTP(WebDriver driver, String strMobileNumber) {
		boolean result = true;
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
			
			// check Through Wrong OTP
			result=CheckWrongOTP(driver);
			if(!result) {
				homePageMsgList.add("Through Wrong OTP Login Is not verified");
				return result;
			}
								

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("VerifyWorngOTP_Exception: " + e.getMessage());
		}

		return result;
	}
	
	public boolean CheckWrongOTP(WebDriver driver) {
		boolean result = true;
		try {

			for(int i=0;i<=15;i++) {
				// enter OTP
				result = enterOtp("232323");
				if (!result) {
					return result;
				}
				
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[contains(text(),'Please enter valid OTP')] ", "xpath", 5);
				if (!result) {
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[contains(text(),\"You've attempted too many times, Retry after 2 mins,\")]", "xpath", 5);
					if (result) {
						break;
					}else {
						homePageMsgList.add("Retry after 2min.. Error Text is Not visble");
						result=false;
					}
				}
				
			}
			
			if(result==false) {
				return result;
			}
			
			
			// click on Back_Button
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "p[class='otp-verifcation'] span", "css", 30);
			if (result) {
				cfObj.commonClick(homePageORObj.getBackArrow());
			}else {
				homePageMsgList.add("Back_Button is Not Visible");
				return result;
			}
			
			// click on Continue_Button
			cfObj.commonClick(homePageORObj.getBtnContinue().get(0));
			
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[contains(text(),\"You've attempted too many times, Retry after 2 mins,\")]", "xpath", 5);
			if (!result) {
				homePageMsgList.add("Retry after 2min.. Error Text is Not visble");
				return result;
			}
			
			// Close Login Page
			cfObj.commonClick(homePageORObj.getCrossButton());

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("CheckWrongOTP_Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean closeLoginPage(WebDriver driver) {
		boolean result = true;
		try {
			
			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, " //img[@alt='cross']", "xpath", 10);
			if(result) {
				cfObj.commonClick(homePageORObj.getCrossButton());;
			}else {
				homePageMsgList.add("Cross Button is Not Visible");
				result=false;
			}

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("closeLoginPage_Exception: " + e.getMessage());

		}
		return result;
	}
	
	public boolean VerifyNumberOfAttempt(WebDriver driver,String strMobileNumber) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//p[@class='otp-verifcation']//span", "xpath", 30);
			if (!result) {
				homePageMsgList.add("Back_Button is Not Visible");
				return result;
			}
			
			//1st way
			for(int i=0;i<=25;i++) {
				
				// click on Back_Button
				cfObj.commonClick(homePageORObj.getBackArrow());
				
				// click on Continue_Button
				cfObj.commonClick(homePageORObj.getBtnContinue().get(0));
				
				result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[contains(text(),\"You've attempted too many times, Retry after 2 mins,\")]", "xpath", 5);
				if(result) {
					break;
				}else {
					result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[contains(text(),'User crossed registration limit')]", "xpath", 5);
					if(result) {
						break;
					}else {
						homePageMsgList.add("Retry Massage is Not Visible");
						result=false;
					}
				}
				
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					
			} 
			if(result==false) {
				return result;
			}
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			//2nd Way
			
			// enter mobile number
			result = enterMobileNumber(strMobileNumber);
			if (!result) {
				return result;
			}

			// click on Continue_Button
			result = clickOnContinueButton();
			if (!result) {
				return result;
			}
			
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "p[class='otp-verified'] a span", "css", 10);
			if (!result) {
				homePageMsgList.add("Edit_Button is Not Visible");
				return result;
			}
            for(int j=0;j<=25;j++) {
            	
            	// click on Edit_Button
            	cfObj.commonClick(homePageORObj.getEditButton());
				
            	// click on Continue_Button
				cfObj.commonClick(homePageORObj.getBtnContinue().get(0));
				
				result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[contains(text(),\"You've attempted too many times, Retry after 2 mins,\")]", "xpath", 5);
				if(result) {
					break;
				}else {
					result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[contains(text(),'User crossed registration limit')]", "xpath", 5);
					if(result) {
						break;
					}else {
						homePageMsgList.add("Retry Massage is Not Visible");
						result=false;
					}
				}
				
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
			}
            
            if(result==false) {
				return result;
			}

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("VerifyNumberOfAttempt_Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean checkWithoutMobileNumber(WebDriver driver) {
		boolean result = true;
		try {
			// click on login/register button
			result = clickOnLoginRegisterButton(driver);
			if (!result) {
				homePageMsgList.add(result + " Login/Register Button is not working");
				return result;
			}

			// click on Continue Button
			result = clickOnContinueButton();
			if (!result) {
				return result;
			}
			
			// check Error Massage 
			result = checkErrorMassage_WithoutNumber(driver);
			if (!result) {
				homePageMsgList.add("Error Massage is Not Visible");
				return result;
			}
			
			// close on Login_Page
			result=closeLoginPage(driver);
			if(!result) {
				homePageMsgList.add("Login page is Not Closed");
				return result;
			}
			
		} catch (Exception e) {
			result = false;
			homePageMsgList.add("checkWithoutMobileNumber_Exception: " + e.getMessage());
		}

		return result;
	}
	
	public boolean checkErrorMassage_WithoutNumber(WebDriver driver) {
		boolean result = true;
		try {
			
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[contains(text(),'Please enter your mobile number.')] ", "xpath", 5);
			if (result) {
				return result;
			}else {
				return result;
			}

		} catch (Exception e) {
			result = false;
			homePageMsgList.add("checkErrorMassage_WithoutNumber_Exception: " + e.getMessage());

		}
		return result;
	}
	
	public boolean verifyLoginAfter_2min(WebDriver driver, String strMobileNumber) {
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
			
			// check Error Massage 
			result = checkErrorMassage(driver);
			if (result) {
				homePageMsgList.add("Error Massage is Visible");
				result=false;
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
			homePageMsgList.add("verifyLoginAfter_2min_Exception: " + e.getMessage());
		}

		return result;
	}
	
	//Check Negative Scenario Of Sign-up Case
		public boolean verifySignUpFailCase(WebDriver driver) {
			boolean result = true;
			String strMobileNumber01=null,strMobileNumber02=null;
			try {
				
				strMobileNumber01 = Common_Function.randomPhoneNumber(10, "3");
				strMobileNumber02 = Common_Function.randomPhoneNumber(10, "4");
				
				//Check Without Mobile no.
				result=checkWithoutMobileNumber(driver);
				if(!result) {
					homePageMsgList.add("not Verify Through Mobile No..");
					return result;
				}
				
				// Try many Attempt for OTP 
				result=VerifyOTPPage(driver,strMobileNumber01,strMobileNumber02);
				if(!result) {
					homePageMsgList.add("There is No any Limition Attempt for OTP page");
					return result;
				}
				
				// Sign-Up after Failed Attempt(check Error Massage)
				result=verifyLogin_AfterFaliedAttempt(driver,strMobileNumber02);
				if(!result) {
					homePageMsgList.add("Not Login after Failed Logn");
					return result;
				}
				
				strMobileNumber01 = Common_Function.randomPhoneNumber(10, "6");
				
				//Sign-Up through Wrong OTP
				result=VerifyWrongOTPSignUp(driver,strMobileNumber01);
				if(!result) {
					homePageMsgList.add("Not verify through Wrong OTP");
					return result;
				}
				
				//Sign-Up
				result=verifySignUp(driver);
				if(!result) {
					return result;
				}

			} catch (Exception e) {
				result = false;
				homePageMsgList.add("verifySignUpFailCase_Exception: " + e.getMessage());
			}

			return result;
		}
		
		public boolean VerifyWrongOTPSignUp(WebDriver driver, String strMobileNumber) {
			boolean result = true;
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
				
				// check Through Wrong OTP
				result=CheckWrongOTPSignUp(driver);
				if(!result) {
					homePageMsgList.add("Through Wrong OTP Login Is not verified");
					return result;
				}
									

			} catch (Exception e) {
				result = false;
				homePageMsgList.add("VerifyWrongOTPSignUp_Exception: " + e.getMessage());
			}

			return result;
		}
		
		public boolean CheckWrongOTPSignUp(WebDriver driver) {
			boolean result = true;
			String strMobileNumber03=null;
			try {
				strMobileNumber03 = Common_Function.randomPhoneNumber(10, "5");

				for(int i=0;i<=20;i++) {
					
					if(i==0) {
						result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//button[contains(text(),'Verify')]", "xpath", 5);
						if (result) {
							cfObj.commonClick(homePageORObj.getVerifyButton_SignUp());
						}else {
							homePageMsgList.add("Verify Button is Not visble");
							return result;
						}
						
					}else if(i==1) {
						// enter OTP
						result = enterOtp("232323");
						if (!result) {
							return result;
						}
						
						result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//button[contains(text(),'Verify')]", "xpath", 5);
						if (result) {
							cfObj.commonClick(homePageORObj.getVerifyButton_SignUp());
						}else {
							homePageMsgList.add("Verify Button is Not visble");
							return result;
						}
					}else if(i==2) {
						
						// Enter name
						result = enterName("Test");
						if (!result) {
							return result;
						}
						
						result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//button[contains(text(),'Verify')]", "xpath", 5);
						if (result) {
							cfObj.commonClick(homePageORObj.getVerifyButton_SignUp());
						}else {
							homePageMsgList.add("Verify Button is Not visble");
							return result;
						}
					}else if(i==3) {
						// Enter Email
						result = enterEmail("Test" + strMobileNumber03 + "@gmail.com");
						if (!result) {
							return result;
						}
						
						result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//button[contains(text(),'Verify')]", "xpath", 5);
						if (result) {
							cfObj.commonClick(homePageORObj.getVerifyButton_SignUp());
						}else {
							homePageMsgList.add("Verify Button is Not visble");
							return result;
						}
						
						result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[contains(text(),'Please enter valid OTP')] ", "xpath", 5);
						if (!result) {
							homePageMsgList.add("Alert massage is Not visble");
							return result;
						}
					}else if(i>3) {
					
						result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//button[contains(text(),'Verify')]", "xpath", 5);
						if (result) {
							cfObj.commonClick(homePageORObj.getVerifyButton_SignUp());
						}else {
							homePageMsgList.add("Verify Button is Not visble");
							return result;
						}
						
						result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[contains(text(),\"You've attempted too many times, Retry after 2 mins,\")]", "xpath", 5);
						if(result) {
							break;
						}else {
							homePageMsgList.add("Retry After 2min Massage is Not visble");
							result=false;
						}
						
					}
					
				}
				
				if(result==false) {
					return result;
				}
				
				
				// click on Back_Button
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "p[class='otp-verifcation'] span", "css", 30);
				if (result) {
					cfObj.commonClick(homePageORObj.getBackArrow());
				}else {
					homePageMsgList.add("Back_Button is Not Visible");
					return result;
				}
				
				// Close Login/Sign-up Page
				cfObj.commonClick(homePageORObj.getCrossButton());

			} catch (Exception e) {
				result = false;
				homePageMsgList.add("CheckWrongOTPSignUp_Exception: " + e.getMessage());
			}
			return result;
		}

}
