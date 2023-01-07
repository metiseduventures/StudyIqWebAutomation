package applicationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import apiUtil.LibraryApiUtil;
import apiUtil.LoginUtil;
import pageObject.CoursePage_OR;
import pageObject.HomePage_OR;
import pageObject.LibraryPage_OR;
import pageObject.MyCourse_OR;
import pojo.TestData;
import pojo.login.Login;
import pojo.myLibrary.MyLibrary;
import util.Common_Function;
import util.ConfigFileReader;

public class LibraryPageUtil {

	LibraryPage_OR libraryPage_OR;
	MyCourse_OR myCourse_OR;
	CoursePage_OR coursePage_OR;
	HomePage_OR homePageORObj;
	MyCourseUtil myCoursePageUtil;
	CoursePageUtil coursePageUtil;
	HomePageUtil homeUtilObj;
	public List<String> libraryPageMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();
	ConfigFileReader fileReader = new ConfigFileReader();
	ConfigFileReader rConfigFileReader;
	HomePageUtil homPageUtilObj;
	MyCourseUtil myCourseUtilObj;
	LoginUtil loginApiUtilObj;
	public List<String> VideoName_in_Watch_List;

	public LibraryPageUtil(WebDriver driver) {
		libraryPage_OR = new LibraryPage_OR();
		PageFactory.initElements(driver, libraryPage_OR);
	}

	public boolean verifyMyLibrary(WebDriver driver) {
		boolean result = true;
		LibraryApiUtil libraryApiObj;
		MyLibrary myLibrayApiObj;
		String strApiToken, strUserId;
		try {
			driver.navigate().to(fileReader.getBaseUrlWeb() + "my-library");
			homPageUtilObj = new HomePageUtil(driver);
			result = homPageUtilObj.verifyLogin(driver, ConfigFileReader.strUserMobileNumber);
			if (!result) {
				libraryPageMsgList.add("Fail to Login/Register");
				return result;
			}
			Thread.sleep(5000);
			strApiToken = cfObj.getApiTokenfromCookies(driver);
			strUserId = cfObj.getUserIdFromCookies(driver);
			libraryApiObj = new LibraryApiUtil();

			myLibrayApiObj = libraryApiObj.getLibraryData(strApiToken, Integer.parseInt(strUserId));

			if (myLibrayApiObj == null) {
				libraryPageMsgList.add("Error in my library api");
				return result;
			}

			result = homPageUtilObj.navigateToMyLibrary(driver);
			if (!result) {
				libraryPageMsgList.addAll(homPageUtilObj.homePageMsgList);
				return result;
			}
			result = verifyMyLibraryMenu(myLibrayApiObj);

			if (!result) {
				return result;
			}

			result = verifyLibraryMenuItem(myLibrayApiObj);
			if (!result) {
				return result;
			}

			result = verifyExpiredCourse(driver, myLibrayApiObj);

			if (!result) {
				return result;
			}

			result = verifyExpiringSoonCourse(driver, myLibrayApiObj);

			if (!result) {
				return result;
			}
		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("verifyMyLibrary_Page_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean firstLibraryCheck(WebDriver driver) {
		boolean result = true;
		homPageUtilObj = new HomePageUtil(driver);
		try {
			result = homPageUtilObj.verifyLogin(driver, ConfigFileReader.strUserMobileNumber);
			if (!result) {
				libraryPageMsgList.add("Fail to Login/Register");
			}

			result = Check_Library(driver);
			if (!result) {
				libraryPageMsgList.add("Fail to validateMyLibrary");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.getExplore_Courses(), 2);
			if (result == true) {
				cfObj.commonClick(libraryPage_OR.getExplore_Courses());
			} else {
				libraryPageMsgList.add("No new Login");
			}
		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("firstLibraryCheck_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyPuchasedCourseOnMyLibrary(WebDriver driver, TestData testData, String courseName) {
		boolean result = true;
		String strActualCourseType;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//span[@class='title']", "xpath", 10);
			if (!result) {
				libraryPageMsgList.add("This is not a library page");
				return result;
			}

			if (libraryPage_OR.getCourseTypeMenu().size() == 0) {
				libraryPageMsgList.add("Course type menu is not display after purchase course in library");

			}

			strActualCourseType = libraryPage_OR.getCourseTypeMenu().get(0).getText().toString().toLowerCase();
			if (!strActualCourseType.contains(testData.getCourseType().toString().toLowerCase())) {
				libraryPageMsgList.add("Course type is different: Expected course type in library should be :"
						+ testData.getCourseType().toString());
				result = false;
			}
			List<WebElement> linkCourses = libraryPage_OR.libraryTitles();

			int noOfCourses = linkCourses.size();

			if (!testData.getCourseType().equalsIgnoreCase("books")) {
				if (noOfCourses == 0) {

					libraryPageMsgList.add("The course is bought but there is no course present");
					return false;

				} else {
					result = verifyBoughtCourse(courseName);
					if (!result) {
						return result;
					}
				}
			} else {
				if (noOfCourses != 0) {
					libraryPageMsgList.add("Purchased books course display in the library");
					return false;
				}
				// Click on course from library page
				cfObj.commonClick(libraryPage_OR.libraryTitles().get(0));
				// wait for course Content page to be opened

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".course_dashboard_content_wrapper",
						"css", 30);
				if (!result) {
					libraryPageMsgList.add("Course Content page is not opened");
					return result;
				}
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("verifyCourse_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyBoughtCourse(String searchCourseName) {
		boolean result = true;
		try {
			List<WebElement> linkCourses = libraryPage_OR.libraryTitles();

			for (int i = 0; i < linkCourses.size(); i++) {
				String courseInLibrary = linkCourses.get(i).getText().toLowerCase();
				if (!courseInLibrary.equalsIgnoreCase(searchCourseName)) {
					libraryPageMsgList.add("Mismatch in purchase course in libabry : Expected Name : "
							+ searchCourseName + ": Actaul: " + courseInLibrary);

				} else {
					break;
				}
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("verifyBoughtCourse_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean VerifySmart_Course(WebDriver driver) {
		boolean result = true;
		myCoursePageUtil = new MyCourseUtil(driver);
		try {
			cfObj.commonClick(libraryPage_OR.getSmart_Courses());
			cfObj.commonClick(libraryPage_OR.getStart_MyCourse());
			result = myCoursePageUtil.validateFirst_SmartCourse(driver);
			if (!result) {
				libraryPageMsgList.add("Not Validate First Course of Smart Courses");
				return result;
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("VerifySmart_Course_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean VerifyMicro_Course(WebDriver driver) {
		boolean result = true;
		myCoursePageUtil = new MyCourseUtil(driver);
		try {

			cfObj.commonClick(libraryPage_OR.getMicro_Courses());
			cfObj.commonClick(libraryPage_OR.getStart_MyCourse());
			result = myCoursePageUtil.validateFirst_MicroCourse(driver);
			if (!result) {
				libraryPageMsgList.add("Not Validate First Course of Micro Courses");
				return result;
			}
		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("VerifyMicro_Course_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean Verify_Books(WebDriver driver) {
		boolean result = true;
		myCoursePageUtil = new MyCourseUtil(driver);
		try {

			cfObj.commonClick(libraryPage_OR.getBooks());
			cfObj.commonClick(libraryPage_OR.getStart_MyCourse());
			result = myCoursePageUtil.validateFirst_Books(driver);
			if (!result) {
				libraryPageMsgList.add("Not Validate First Books Page");
				return result;
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("VerifyMicro_Course_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyDropDownLibraryCourse(WebDriver driver, MyLibrary myLibraryObj) {
		boolean result = true;
		homeUtilObj = new HomePageUtil(driver);
		try {
			Thread.sleep(7000);
			// click on DropDown button
			result = homeUtilObj.clickOnDropDown(driver);
			if (!result) {
				libraryPageMsgList.add("DropDown is not working");
				return result;
			}

			if (libraryPage_OR.getListOfsourse().size() != myLibraryObj.getData().size()) {

				libraryPageMsgList.add("Mismatch in library course count in api and from drop down");
				return result;

			}
			// verify course name in drop down
			for (int i = 0; i < myLibraryObj.getData().size(); i++) {
				String strCourseNameUI = libraryPage_OR.getListOfsourse().get(i).getText().toString().trim();
				String strCourseNameApi = myLibraryObj.getData().get(i).getCourseDisplayName().toString();
				if (!strCourseNameUI.equalsIgnoreCase(strCourseNameApi)) {
					libraryPageMsgList.add("Mismatch in course name from api in drom down and UI in drop down");
					result = false;
				}
			}

			for (int i = 0; i < myLibraryObj.getData().size(); i++) {

				cfObj.commonClick(libraryPage_OR.getListOfsourse().get(i));

				// wait for specific library course page to be opened
				result = homeUtilObj.clickOnDropDown(driver);
				if (!result) {
					libraryPageMsgList.add("DropDown is not working");
					return result;
				}

			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("verifyDropDownLibraryCourse_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean clickOnSmart(WebDriver driver) {
		boolean result = true;
		homeUtilObj = new HomePageUtil(driver);
		try {

			result = homeUtilObj.clickOnDropDown(driver);
			if (!result) {
				libraryPageMsgList.add("DropDown is not working");
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"(//*[contains(text(),'Smart Course')])[2]", "xpath", 10);
			if (result == true) {
				cfObj.commonClick(libraryPage_OR.getDotSmart());
			} else {
				libraryPageMsgList.add("TestSeries is Not Available");
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("clickOnSmart_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean clickOnMicro(WebDriver driver) {
		boolean result = true;
		homeUtilObj = new HomePageUtil(driver);
		try {
			result = homeUtilObj.clickOnDropDown(driver);
			if (!result) {
				libraryPageMsgList.add("DropDown is not working");
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "(//*[contains(text(),'Micro Course')])",
					"xpath", 10);
			if (result == true) {
				cfObj.commonClick(libraryPage_OR.getDotMicro());
			} else {
				libraryPageMsgList.add("TestSeries is Not Available");
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("clickOnMicro_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean clickOnBook(WebDriver driver) {
		boolean result = true;
		homeUtilObj = new HomePageUtil(driver);
		try {
			result = homeUtilObj.clickOnDropDown(driver);
			if (!result) {
				libraryPageMsgList.add("DropDown is not working");
			}
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "(//div[contains(text(),'Books')])[1]",
					"xpath", 10);
			if (result == true) {
				cfObj.commonClick(libraryPage_OR.getDropDownBook());
			} else {
				libraryPageMsgList.add("TestSeries is Not Available");
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("clickOnBook_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnTestSeries(WebDriver driver) {
		boolean result = true;
		homeUtilObj = new HomePageUtil(driver);
		try {
			result = homeUtilObj.clickOnDropDown(driver);
			if (!result) {
				libraryPageMsgList.add("DropDown is not working");
				return result;
			}
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"(//div[contains(text(),'Test Series')])[1]", "xpath", 10);
			if (result == true) {
				cfObj.commonClick(libraryPage_OR.getDotTestSeries());
			} else {
				libraryPageMsgList.add("TestSeries is Not Available");
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("clickOnTestSeries_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean Check_Library(WebDriver driver) {
		boolean result = true;
		homeUtilObj = new HomePageUtil(driver);
		try {

			// click on DropDown button
			result = homeUtilObj.clickOnDropDown(driver);
			if (!result) {
				libraryPageMsgList.add("DropDown is not working");
				return result;
			}

			// click on My Library button
			result = clickOnMyLibrary(driver);
			if (result) {
				libraryPageMsgList.add(result + "My Library Button is not working");
				return result;
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("verifyMyLibrary_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean clickOnMyLibrary(WebDriver driver) {
		boolean result = true;
		try {
			cfObj.commonClick(libraryPage_OR.getMyLibrary_Button());
		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("clickOnMyLibrary_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnExploreCourses(WebDriver driver) {
		boolean result = true;
		try {
			cfObj.commonClick(libraryPage_OR.getExplore_Courses());

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("clickOnExploreCourses_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean ClickOnHomePage(WebDriver driver) {
		boolean result = true;
		try {
			cfObj.commonClick(myCourse_OR.getClickOn_HomePage());
		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("VerifySmart_Course_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnLiveClasses(WebDriver driver) {
		boolean result = true;
		homeUtilObj = new HomePageUtil(driver);
		try {

			result = homeUtilObj.clickOnDropDown(driver);
			if (!result) {
				libraryPageMsgList.add("DropDown is not working");
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "(//div[contains(text(),'Live Classes')])",
					"xpath", 10);
			if (result == true) {
				cfObj.commonClick(libraryPage_OR.getDotLiveClasses());
			} else {
				libraryPageMsgList.add("Live_Classes is Not Available");
				return result;
			}
		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("clickOnLiveClasses_Exception: " + e.getMessage());
		}
		return result;
	}

	// Update Library

	public boolean VerifyExtRen_Courses(WebDriver driver, TestData testData) {
		boolean result = true;
		myCoursePageUtil = new MyCourseUtil(driver);
		homPageUtilObj = new HomePageUtil(driver);
		try {
			result = homPageUtilObj.verifyLogin(driver, ConfigFileReader.strUserMobileNumber);
			if (!result) {
				libraryPageMsgList.add("Failed to Login");
				return result;
			}

			Thread.sleep(7000);
			if (testData.getCourseType().contains("Micro")) {
				result = clickOnVideoMicCourses(driver);
				if (!result) {
					libraryPageMsgList.add("Video-Course is not Available");
					return result;
				}

			} else if (testData.getCourseType().contains("live")) {
				result = clickOnLiveClasses(driver);
				if (!result) {
					libraryPageMsgList.add("live Course is not Available");
					return result;
				}

			} else if (testData.getCourseType().contains("TestSeries")) {
				result = clickOnTestSeries(driver);
				if (!result) {
					libraryPageMsgList.add("test-series is not Available");
					return result;
				}
			}

			if (testData.isExpires() == false) {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//button[contains(text(),'Remove')]",
						"xpath", 10);
				if (result == true) {
					if (libraryPage_OR.getCourseInsideLibrary().size() > 0) {
						cfObj.commonClick(libraryPage_OR.getRemoveBUtton().get(0));
					} else {
						libraryPageMsgList.add("Courses is not avaible ");
					}

				} else {
					if (testData.getCourseType().contains("TestSeries")) {
						result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
								"//span[@class='info-validity']", "xpath", 10);
						if (result == true) {
							cfObj.commonClick(libraryPage_OR.getValidCourse().get(0));
							cfObj.commonClick(libraryPage_OR.getCloseButton_ofTestSeriesCourses());
						} else {
							result = true;
							libraryPageMsgList.add("Valid Course of test-series is Not Available");
							return result;
						}
					} else {
						result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
								"//span[@class='info-validity']", "xpath", 10);
						if (result == true) {
							if (libraryPage_OR.getCourseInsideLibrary().size() > 0) {
								cfObj.commonClick(libraryPage_OR.getValidCourse().get(0));
								Thread.sleep(10000);
								cfObj.commonClick(libraryPage_OR.getClose_ExtendCourse());
								result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
										"//span[contains(text(),'More')]", "xpath", 10);
								if (result == true) {
									cfObj.commonClick(libraryPage_OR.getMore_Button());
									cfObj.commonClick(libraryPage_OR.getClose_ExtendCourse());
									result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
											"//span[contains(text(),'Extend Validity')]", "xpath", 10);
									if (result == true) {
										cfObj.commonClick(libraryPage_OR.getExtend_Validity());
										Thread.sleep(5000);
										result = CompletePayment(driver, testData);
										if (!result) {
											libraryPageMsgList.add("Payment is not completed");
										}
									} else {
										libraryPageMsgList.add("Extend-Validity Button is not available");
										return result;
									}
								} else {
									libraryPageMsgList.add("More Button is not available");
								}
							} else {
								libraryPageMsgList.add("Courses is not avaible ");
							}
						} else {
							result = true;
							libraryPageMsgList.add("Valid Courses is not Available");
							return result;
						}
					}
				}
			} else if (testData.isExpires() == true) {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//button[contains(text(),'Renew Now')]", "xpath", 10);
				if (result == true) {
					if (libraryPage_OR.getCourseInsideLibrary().size() > 0) {
						cfObj.commonClick(libraryPage_OR.getRenewBUtton().get(0));
						Thread.sleep(5000);
						result = CompletePayment(driver, testData);
						if (!result) {
							libraryPageMsgList.add("Payment is not completed");
						}
					} else {
						libraryPageMsgList.add("Courses is not Available ");
					}
				} else {
					result = true;
					libraryPageMsgList.add("Renew Course is Not Available");
					return result;
				}
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("VerifyExtRen_Courses_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean CompletePayment(WebDriver driver, TestData testData) {
		boolean result = true;
		CoursePageUtil coursePageUtil_Obj = new CoursePageUtil(driver);
		try {

			result = coursePageUtil_Obj.verifyPromoCode(driver);
			if (!result) {
				return result;
			}

			result = coursePageUtil_Obj.verifyPayCheckout(driver);
			if (!result) {
				return result;
			}

			result = coursePageUtil_Obj.verifyClickPay(testData.getPaymentMethod(), driver, testData,
					testData.getbankNameForPaytm());
			if (!result) {
				return result;
			}

			if ((ConfigFileReader.strEnv).equalsIgnoreCase("staging")
					|| (ConfigFileReader.strEnv).equalsIgnoreCase("dev")) {
				if (testData.getIsKey().equalsIgnoreCase("pass")) {
					result = coursePageUtil_Obj.goToLibrary(driver);
					if (!result) {
						return result;
					}
				}

			} else if ((ConfigFileReader.strEnv).equalsIgnoreCase("prod")) {

				System.out.println("The envirnonment is production, everything working fine");

			} else {
				libraryPageMsgList.add("The envirnoment is different from dev and prod");
				return false;
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("CompletePayment_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnVideoCourses(WebDriver driver) {
		boolean result = true;
		homeUtilObj = new HomePageUtil(driver);
		try {

			result = homeUtilObj.clickOnDropDown(driver);
			if (!result) {
				libraryPageMsgList.add("DropDown is not working");
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"(//*[contains(text(),'Video Course')])[1]", "xpath", 10);
			if (result == true) {
				cfObj.commonClick(libraryPage_OR.getDotvideocourses());
			} else {
				libraryPageMsgList.add("Video-Courses is Not Available");
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("clickOnVideoCourses_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean VerifyContentOfCourse(WebDriver driver) {
		boolean result = true;
		homeUtilObj = new HomePageUtil(driver);
		try {

			// click on DropDown button
			result = homeUtilObj.clickOnDropDown(driver);
			if (!result) {
				libraryPageMsgList.add("DropDown is not working");
				return result;
			}

			if (libraryPage_OR.getListOfsourse().size() > 0) {

				// click on Video-Courses button
				result = VerifyConententInside_VideoCourses(driver);
				if (!result) {
					libraryPageMsgList.add("Video Courses is not Available");
				}

				// click on TestSeries button
				result = VerifyConententInside_LiveCourses(driver);
				if (!result) {
					libraryPageMsgList.add("Live_Classes is not Available");
				}

				// click on Live_Classes button
				result = VerifyConententInside_TestSeries(driver);
				if (!result) {
					libraryPageMsgList.add("Test-Series is not Available");
				}
				result = true;
				return result;
			} else {
				if (libraryPage_OR.getListOfsourse().size() == 0) {
					result = false;
					return result;
				}
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("VerifyContentOfCourse_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean VerifyConententInside_VideoCourses(WebDriver driver) {
		boolean result = true;
		homeUtilObj = new HomePageUtil(driver);
		try {

			result = homeUtilObj.clickOnDropDown(driver);
			if (!result) {
				libraryPageMsgList.add("DropDown is not working");
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"(//*[contains(text(),'Video Course')])[1]", "xpath", 10);
			if (result == true) {
				cfObj.commonClick(libraryPage_OR.getDotvideocourses());
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//div[@id='library-listing-wrapper']/div[2]/div/div", "xpath", 10);
				if (result == true) {
					if (libraryPage_OR.getValidCourse().get(0).isDisplayed()
							|| libraryPage_OR.getRemoveBUtton().get(0).isDisplayed()) {
						cfObj.commonClick(libraryPage_OR.getCourseInsideLibrary().get(0));
						result = myCoursePageUtil.validateFirst_Course(driver);
						if (!result) {
							libraryPageMsgList.add("Not Validating Course of Video-Courses");
							return result;
						}
					} else if (libraryPage_OR.getRenewBUtton().get(0).isDisplayed()) {
						libraryPageMsgList.add("Course is Expired");
						return result;
					}
				} else {
					libraryPageMsgList.add("Courses is Not Available in Video-Course");
					return false;
				}
			} else {
				libraryPageMsgList.add("Video-Courses is Not Available");
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("VerifyConententInside_VideoCourses_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean VerifyConententInside_LiveCourses(WebDriver driver) {
		boolean result = true;
		homeUtilObj = new HomePageUtil(driver);
		try {

			result = homeUtilObj.clickOnDropDown(driver);
			if (!result) {
				libraryPageMsgList.add("DropDown is not working");
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "(//div[contains(text(),'Live Classes')])",
					"xpath", 10);
			if (result == true) {
				cfObj.commonClick(libraryPage_OR.getDotLiveClasses());
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//div[@id='library-listing-wrapper']/div[2]/div/div", "xpath", 10);
				if (result == true) {
					if (libraryPage_OR.getValidCourse().get(0).isDisplayed()
							|| libraryPage_OR.getRemoveBUtton().get(0).isDisplayed()) {
						cfObj.commonClick(libraryPage_OR.getCourseInsideLibrary().get(0));
						result = myCoursePageUtil.validateFirst_Course(driver);
						if (!result) {
							libraryPageMsgList.add("Not Validating Course of Video-Courses");
							return result;
						}
					} else if (libraryPage_OR.getRenewBUtton().get(0).isDisplayed()) {
						libraryPageMsgList.add("Course is Expired");
						return result;
					}
				} else {
					libraryPageMsgList.add("Courses is Not Available in Video-Course");
					return false;
				}
			} else {
				libraryPageMsgList.add("Live-Courses is Not Available");
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("VerifyConententInside_LiveCourses_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean VerifyConententInside_TestSeries(WebDriver driver) {
		boolean result = true;
		homeUtilObj = new HomePageUtil(driver);
		try {

			result = homeUtilObj.clickOnDropDown(driver);
			if (!result) {
				libraryPageMsgList.add("DropDown is not working");
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"(//div[contains(text(),'Test Series')])[1]", "xpath", 10);
			if (result == true) {
				cfObj.commonClick(libraryPage_OR.getDotTestSeries());
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//div[@id='library-listing-wrapper']/div[2]/div/div", "xpath", 10);
				if (result == true) {
					if (libraryPage_OR.getValidCourse().get(0).isDisplayed()
							|| libraryPage_OR.getRemoveBUtton().get(0).isDisplayed()) {
						cfObj.commonClick(libraryPage_OR.getCourseInsideLibrary().get(0));
						result = myCoursePageUtil.validateFirst_Course(driver);
						if (!result) {
							libraryPageMsgList.add("Not Validating Course of Video-Courses");
							return result;
						}
					} else if (libraryPage_OR.getRenewBUtton().get(0).isDisplayed()) {
						libraryPageMsgList.add("Course is Expired");
						return result;
					}
				} else {
					libraryPageMsgList.add("Courses is Not Available in Video-Course");
					return false;
				}
			} else {
				libraryPageMsgList.add("TestSeries is Not Available");
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("VerifyConententInside_TestSeries_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean clickOnVideoMicCourses(WebDriver driver) {
		boolean result = true;
		homeUtilObj = new HomePageUtil(driver);
		try {

			result = homeUtilObj.clickOnDropDown(driver);
			if (!result) {
				libraryPageMsgList.add("DropDown is not working");
			}

			if ((ConfigFileReader.strEnv).equalsIgnoreCase("staging")
					|| (ConfigFileReader.strEnv).equalsIgnoreCase("dev")) {

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"(//*[contains(text(),'Video Course')])[1]", "xpath", 10);
				if (result == true) {
					cfObj.commonClick(libraryPage_OR.getDotvideocourses());
				} else {
					libraryPageMsgList.add("Video-Courses is Not Available");
				}
			} else if ((ConfigFileReader.strEnv).equalsIgnoreCase("prod")) {

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"(//*[contains(text(),'Micro Course')])", "xpath", 10);
				if (result == true) {
					cfObj.commonClick(libraryPage_OR.getDotMicro());
				} else {
					libraryPageMsgList.add("TestSeries is Not Available");
				}
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("clickOnVideoMicCourses_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean verifyMyLibraryMenu(MyLibrary myLibraryObj) {
		boolean result = true;
		int size;
		try {
			size = myLibraryObj.getData().size();
			// check for my library course type menu item

			if (size > 0) {

				if (myLibraryObj.getData().size() != libraryPage_OR.getListLibaryMenuItem().size()) {
					libraryPageMsgList.add("Mismatch in library menu item from api and ui");
					return false;
				}

				// verify menu text

				for (int i = 0; i < size; i++) {
					String strCourseType = libraryPage_OR.getListLibaryMenuItem().get(i).getText().toString().trim();
					String strcourseTypeApi = myLibraryObj.getData().get(i).getCourseDisplayName().toString();
					if (!strCourseType.equalsIgnoreCase(strcourseTypeApi)) {
						result = false;
						libraryPageMsgList.add("Mismatch in course type name from api and UI: Expected: "
								+ strcourseTypeApi + ": actual: " + strCourseType);
					}
				}

				if (!result) {
					return result;
				}
				// verify first course type should selected

				if (!libraryPage_OR.getListLibaryMenuItem().get(0).getAttribute("class").toString()
						.equalsIgnoreCase("nav-item active")) {
					libraryPageMsgList.add("first course type is not active on my libraray");
					result = false;
				}

				for (int i = 1; i < size; i++) {
					if (!libraryPage_OR.getListLibaryMenuItem().get(i).getAttribute("class").toString()
							.equalsIgnoreCase("nav-item")) {
						libraryPageMsgList.add((i + 1) + " secourse type is not be active on my libraray");
						result = false;
					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			libraryPageMsgList.add("verifyMyLibraryMenu_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyLibraryMenuItem(MyLibrary mylibraryObj) {
		boolean result = true;
		int size;
		try {
			size = mylibraryObj.getData().size();

			for (int i = 0; i < size; i++) {
				// click on menu item
				cfObj.commonClick(libraryPage_OR.getListLibaryMenuItem().get(i));
				if (!libraryPage_OR.getListLibaryMenuItem().get(i).getAttribute("class").toString()
						.equalsIgnoreCase("nav-item active")) {
					libraryPageMsgList.add("course type is not active after click on menu item in library");
					result = false;
				}

				// verify the course count

				if (mylibraryObj.getData().get(i).getCourseData().size() != libraryPage_OR.getListLibaryMenuCourseItem()
						.size()) {
					libraryPageMsgList.add("mismatch in course count from api and ui for course type:"
							+ mylibraryObj.getData().get(i).getCourseDisplayName());
					result = false;
				}

			}
		} catch (Exception e) {
			result = false;

		}
		return result;
	}

	public boolean verifyExpiredCourse(WebDriver driver, MyLibrary mylibraryObj) {
		boolean result = true;
		int size;
		try {
			size = mylibraryObj.getData().size();

			for (int i = 0; i < size; i++) {
				// click on menu item
				cfObj.commonClick(libraryPage_OR.getListLibaryMenuItem().get(i));
				if (!libraryPage_OR.getListLibaryMenuItem().get(i).getAttribute("class").toString()
						.equalsIgnoreCase("nav-item active")) {
					libraryPageMsgList.add("course type is not active after click on menu item in library");
					result = false;
				}

				for (int j = 0; j < mylibraryObj.getData().get(i).getCourseData().size(); j++) {
					if (mylibraryObj.getData().get(i).getCourseData().get(j).getValidityDaysLeft() == 0) {
						// click on expired courses
						cfObj.commonClick(libraryPage_OR.getListLibaryMenuCourseItem().get(i)
								.findElement(By.cssSelector(".renew")));
						// wait for package modal to be visible
						result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".packages-section", "css",
								10);
						if (!result) {
							libraryPageMsgList.add("Pricing modal is not display when click on renew button");
							return result;
						} else {
							// close modal
							cfObj.commonClick(cfObj.commonGetElement(driver, ".btn-link", "css"));
							driver.navigate().back();
							return result;
						}

					}
				}

			}
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean verifyExpiringSoonCourse(WebDriver driver, MyLibrary mylibraryObj) {
		boolean result = true;
		int size;
		try {
			size = mylibraryObj.getData().size();

			for (int i = 0; i < size; i++) {
				// click on menu item
				cfObj.commonClick(libraryPage_OR.getListLibaryMenuItem().get(i));
				if (!libraryPage_OR.getListLibaryMenuItem().get(i).getAttribute("class").toString()
						.equalsIgnoreCase("nav-item active")) {
					libraryPageMsgList.add("course type is not active after click on menu item in library");
					result = false;
				}

				for (int j = 0; j < mylibraryObj.getData().get(i).getCourseData().size(); j++) {
					if (mylibraryObj.getData().get(i).getCourseData().get(j).getValidityDaysLeft() == 1) {
						// click on expired courses
						cfObj.commonClick(libraryPage_OR.getListLibaryMenuCourseItem().get(i)
								.findElement(By.cssSelector(".renew")));
						// wait for package modal to be visible
						result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".packages-section", "css",
								10);
						if (!result) {
							libraryPageMsgList.add("Pricing modal is not display when click on renew button");
							return result;
						} else {
							// close modal
							cfObj.commonClick(cfObj.commonGetElement(driver, ".btn-link", "css"));
							driver.navigate().back();
							return result;
						}

					}
				}

			}
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean verifyLibraryItemFromHomeDropDown(WebDriver driver) {
		boolean result = true;
		coursePageUtil = new CoursePageUtil(driver);
		homPageUtilObj = new HomePageUtil(driver);
		LibraryApiUtil libraryApiObj;
		MyLibrary myLibrayApiObj;
		String strApiToken, strUserId;
		try {
			result = homPageUtilObj.verifyLogin(driver, ConfigFileReader.strUserMobileNumber);
			if (!result) {
				libraryPageMsgList.add("Fail to Login/Register");
				return result;
			}
			Thread.sleep(5000);
			strApiToken = cfObj.getApiTokenfromCookies(driver);
			strUserId = cfObj.getUserIdFromCookies(driver);
			libraryApiObj = new LibraryApiUtil();

			myLibrayApiObj = libraryApiObj.getLibraryData(strApiToken, Integer.parseInt(strUserId));

			if (myLibrayApiObj == null) {
				libraryPageMsgList.add("Error in my library api");
				return result;
			}
			result = verifyDropDownLibraryCourse(driver, myLibrayApiObj);
			if (!result) {
				return result;
			}

		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean verifyCourseContentFromLibrary(WebDriver driver) {
		boolean result = true;
		coursePageUtil = new CoursePageUtil(driver);
		homPageUtilObj = new HomePageUtil(driver);
		LibraryApiUtil libraryApiObj;
		MyLibrary myLibrayApiObj;
		String strApiToken, strUserId;
		try {
			result = homPageUtilObj.verifyLogin(driver, ConfigFileReader.strUserMobileNumber);
			if (!result) {
				libraryPageMsgList.add("Fail to Login/Register");
				return result;
			}
			Thread.sleep(5000);
			strApiToken = cfObj.getApiTokenfromCookies(driver);
			strUserId = cfObj.getUserIdFromCookies(driver);
			libraryApiObj = new LibraryApiUtil();

			myLibrayApiObj = libraryApiObj.getLibraryData(strApiToken, Integer.parseInt(strUserId));

			if (myLibrayApiObj == null) {
				libraryPageMsgList.add("Error in my library api");
				return result;
			}
			result = clickOnLibCourses(driver);
			if (!result) {
				return result;
			}
			result = homPageUtilObj.navigateToMyLibrary(driver);
			if (!result) {
				libraryPageMsgList.addAll(homPageUtilObj.homePageMsgList);
				return result;
			}
			result = verifyCourseContent(driver, myLibrayApiObj);

		} catch (Exception e) {
			result = false;
		}

		return result;
	}

	public boolean verifyCourseContent(WebDriver driver, MyLibrary mylibraryObj) {
		boolean result = true;
		int size;
		try {
			size = mylibraryObj.getData().size();
			for (int i = 0; i < size; i++) {
				// click on menu item
				cfObj.commonClick(libraryPage_OR.getListLibaryMenuItem().get(i));
				if (!libraryPage_OR.getListLibaryMenuItem().get(i).getAttribute("class").toString()
						.equalsIgnoreCase("nav-item active")) {
					libraryPageMsgList.add("course type is not active after click on menu item in library");
					result = false;
				}

				for (int j = 0; j < mylibraryObj.getData().get(i).getCourseData().size(); j++) {

					String strCourseSlug = mylibraryObj.getData().get(i).getCourseData().get(j).getSlug();
					// click on first item and verify the course content
					cfObj.commonClick(libraryPage_OR.getListLibaryMenuCourseItem().get(j));

					if (!mylibraryObj.getData().get(i).getCourseDisplayName().equalsIgnoreCase("Test Series")) {

						if (!driver.getCurrentUrl().contains(strCourseSlug)) {
							libraryPageMsgList.add("Course content not open for course: "
									+ mylibraryObj.getData().get(i).getCourseData().get(j).getCourseTitle()
									+ ": from library");
							result = false;
						}

						driver.navigate().back();
						Thread.sleep(5000);

					}

				}
			}

		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean clickOnLibCourses(WebDriver driver) {
		boolean result = true;
		homeUtilObj = new HomePageUtil(driver);
		try {

			result = homeUtilObj.clickOnDropDown(driver);
			if (!result) {
				libraryPageMsgList.add("DropDown is not working");
			}
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//div[@class='dropdown']/div[text()='My Library']", "xpath", 10);
			if (result) {
				cfObj.commonClick(libraryPage_OR.getlistLibCourseItem().get(0));
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("clickOnLibCourses_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean clickOnDropDownLibcourse(WebDriver driver) {
		boolean result = true;
		homeUtilObj = new HomePageUtil(driver);
		try {
			// click on DropDown button
			result = homeUtilObj.clickOnDropDown(driver);
			if (!result) {
				libraryPageMsgList.add("DropDown is not working");
				return result;
			}

			if (libraryPage_OR.getListOfsourse().size() > 0) {

				if ((ConfigFileReader.strEnv).equalsIgnoreCase("staging")
						|| (ConfigFileReader.strEnv).equalsIgnoreCase("dev")) {

					// click on Video-Course button
					result = clickOnVideoCourses(driver);
					if (!result) {
						libraryPageMsgList.add("Video Course is not Available");
					}
				} else if ((ConfigFileReader.strEnv).equalsIgnoreCase("prod")) {
					// click on Smart Courses
					result = clickOnSmart(driver);
					if (!result) {
						libraryPageMsgList.add("Smart Course is not Available");
					}

					// click on Micro Course
					result = clickOnMicro(driver);
					if (!result) {
						libraryPageMsgList.add("Micro Course is not Available");
					}
				}

				// click on TestSeries button
				result = clickOnTestSeries(driver);
				if (!result) {
					libraryPageMsgList.add("TestSeries is not Available");
				}

				// click on Live_Classes button
				result = clickOnLiveClasses(driver);
				if (!result) {
					libraryPageMsgList.add("Live_Classes is not Available");
				}
				result = true;
				return result;
			} else {
				if (libraryPage_OR.getListOfsourse().size() == 0) {
					result = false;
					return result;
				}
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("clickOnDropDownLibcourse_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyVideoConsumptionInLibrary(WebDriver driver) {
		boolean result = true;
		homeUtilObj = new HomePageUtil(driver);
		String strApiToken, strUserId;
		try {
			result = homeUtilObj.verifyLogin(driver, ConfigFileReader.strUserMobileNumber);
			if (!result) {
				libraryPageMsgList.add("Failed to Login");
				return result;
			}
			Thread.sleep(5000);
			strApiToken = cfObj.getApiTokenfromCookies(driver);
			strUserId = cfObj.getUserIdFromCookies(driver);
			System.out.println(strApiToken + "" + strUserId);

			result = clickOnLibCourses(driver);
			if (!result) {
				return result;
			}
			// Close Notification if present
			closeNotification();

			result = checkLibrary(driver);
			if (!result) {
				return result;
			}

			if (!result) {
				return result;
			}
			
			result = ValidateVideoConsumption(driver);
			if (!result) {
				return result;
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("verifyVideoConsumption_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean checkLibrary(WebDriver driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//h3[contains(text(),'Your library is empty')]", "xpath", 20);
			if (result) {
				libraryPageMsgList.add("Library is Empty");
				result = false;
			} else {
				result = true;
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("checkLibrary_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean CheckMyLibraryMenu_Course(MyLibrary myLibraryObj) {
		boolean result = true;
		int size;
		try {

			size = myLibraryObj.getData().size();
			// check for my library course type menu item

			if (size > 0) {

				if (myLibraryObj.getData().size() != libraryPage_OR.getListLibaryMenuItem().size()) {
					libraryPageMsgList.add("Mismatch in library menu item from api and ui");
					return false;
				}

				// verify menu text

				for (int i = 0; i < size; i++) {
					String strCourseType = libraryPage_OR.getListLibaryMenuItem().get(i).getText().toString().trim();
					String strcourseTypeApi = myLibraryObj.getData().get(i).getCourseDisplayName().toString();
					if (!strCourseType.equalsIgnoreCase(strcourseTypeApi)) {
						result = false;
						libraryPageMsgList.add("Mismatch in course type name from api and UI: Expected: "
								+ strcourseTypeApi + ": actual: " + strCourseType);
					}
				}

				String strCourseType = libraryPage_OR.getListLibaryMenuItem().get(0).getText().toString().trim();
				String strcourseTypeApi = myLibraryObj.getData().get(0).getCourseDisplayName().toString();
				if (strCourseType.equalsIgnoreCase("Test Series") & strcourseTypeApi.equalsIgnoreCase("Test Series")) {
					libraryPageMsgList.add("Video Consumption Not for Test Series");
					result = false;
				}
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("CheckMyLibraryMenu_Course_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean ValidateVideoConsumption(WebDriver driver) {
		boolean result = true;
		int size;
		MyCourseUtil myCourseUtilOBJ = new MyCourseUtil(driver);
		try {
			size = libraryPage_OR.getListLibaryMenuItem().size();
			List<WebElement> tx01=libraryPage_OR.getValidCourse();

			for (int i = 0; i < size; i++) {

				if (!libraryPage_OR.getListLibaryMenuItem().get(i).getText().contains("Test Series")) {

					// click on menu item
					cfObj.commonClick(libraryPage_OR.getListLibaryMenuItem().get(i));
					if (!libraryPage_OR.getListLibaryMenuItem().get(i).getAttribute("class").toString()
							.equalsIgnoreCase("nav-item active")) {
						libraryPageMsgList.add("course type is not active after click on menu item in library");
						result = false;
					}

					for (int j = 0; j < tx01.size(); j++) {
						if (tx01.size() < 0) {
							libraryPageMsgList.add("Either Course is Expired OR Free Course");
							result=false;

						} else if (tx01.size() > 0) {
							// click on Valid courses
							int index = i;
							if (i >= 1) {
								index = i - 1;
							}
							cfObj.commonClick(libraryPage_OR.getListLibaryMenuCourseItem().get(index));
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
							result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
									"//div[@class='course_dashboard_content_wrapper']", "xpath", 10);
							if (result) {
								result = myCourseUtilOBJ.OpenCourse_ofVideo(driver);
								if (!result) {
									libraryPageMsgList.add("Course Page is not Verifed");
									return result;
								}
								result = VerifyPlayLIst(driver,myCourseUtilOBJ);
								if (!result) {
									libraryPageMsgList.add("PlayList is not verifid");
									return result;
								}
							} else {
								return result;
							}

						}
						break;
					}
				}

			}
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean VerifyPlayLIst(WebDriver driver,MyCourseUtil myCourseUtilOBJ) {
		boolean result = true;
		myCourseUtilObj = new MyCourseUtil(driver);
		List<WebElement> tx02=libraryPage_OR.getVideoName_in_Watch_List();
		VideoName_in_Watch_List=new ArrayList<String>();
		int Sizeof_VideoName_in_Watch_List,SizeOf_LeactureName;
		try {
			Thread.sleep(10000);//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//span[contains(text(),'Continue from Where You Left')]", "xpath",30);
			if (result) {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//div[@class='card-description mt-1']", "xpath", 20);
				if (result) {
					Sizeof_VideoName_in_Watch_List=libraryPage_OR.getVideoName_in_Watch_List().size();
					
					for(int i=0;i<Sizeof_VideoName_in_Watch_List;i++) {
						if(Sizeof_VideoName_in_Watch_List>3 & i>2) {
							cfObj.commonClick(libraryPage_OR.getFarwardButton());
							VideoName_in_Watch_List.add(tx02.get(i).getText());
						}else {
							VideoName_in_Watch_List.add(libraryPage_OR.getVideoName_in_Watch_List().get(i).getText());
						}
					}
					
					if(Sizeof_VideoName_in_Watch_List>3 & libraryPage_OR.getBackButton().isDisplayed()) {
						for(int q=0;q<Sizeof_VideoName_in_Watch_List;q++) {
							cfObj.commonClick(libraryPage_OR.getBackButton());
						}
					}
					
					SizeOf_LeactureName=myCourseUtilOBJ.LeactureName.size();
					System.out.println(SizeOf_LeactureName);
					
					for(int k=0,l=SizeOf_LeactureName-1;k<SizeOf_LeactureName & l>=0;k++,l--) {
						if(myCourseUtilOBJ.LeactureName.get(k).contains(VideoName_in_Watch_List.get(l))) {
							
							result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
									"//button[contains(text(),'Continue watching')]", "xpath", 20);
							if (result) {
								if(Sizeof_VideoName_in_Watch_List>3 & k>2) {
									cfObj.commonClick(libraryPage_OR.getFarwardButton());
								}
								cfObj.commonClick(libraryPage_OR.getContinueButton().get(k));
								Thread.sleep(5000);
								result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
										"//div[@class='new_video_player_wrapper ']", "xpath", 20);
								if (result) {
									cfObj.commonClick(libraryPage_OR.getVideoPlayer());
									Thread.sleep(5000);
									driver.navigate().back();
								} else {
									return result;
								}
							}
						}else {
							libraryPageMsgList.add("Played video is not Equal to Video at PlayList");
							result=false;
							return result;
						}
					}
				}else {
					libraryPageMsgList.add("Video is not Avialable");
					result=false;
					return result;
				}
			}else {
				libraryPageMsgList.add("Continue List of video is not Not Available");
				result=false;
				return result;
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("VerifyPlayLIst_Exception: " + e.getMessage());
		}

		return result;
	}

	public void closeNotification() {
		try {
			cfObj.commonClick(libraryPage_OR.getColseNotification());

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
