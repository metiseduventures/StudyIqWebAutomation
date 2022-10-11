package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import pageObject.CoursePage_OR;
import pageObject.HomePage_OR;
import pageObject.LibraryPage_OR;
import pageObject.MyCourse_OR;
import pojo.TestData;
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
	ConfigFileReader rConfigFileReader;
	HomePageUtil homPageUtilObj;

	public LibraryPageUtil(WebDriver driver) {
		libraryPage_OR = new LibraryPage_OR();
		PageFactory.initElements(driver, libraryPage_OR);
	}

	public boolean verifyMyLibrary(WebDriver driver) {
		boolean result = true;
		coursePageUtil = new CoursePageUtil(driver);
		homPageUtilObj = new HomePageUtil(driver);
		try {

			result = homPageUtilObj.verifyLogin(driver, ConfigFileReader.strUserMobileNumber);
			if (!result) {
				libraryPageMsgList.add("Fail to Login/Register");
				return result;
			}
			result = DropDownLibrary_Course(driver);
			if (!result) {
				libraryPageMsgList.add("Fail to validateMyLibrary");
				return result;
			}
			result = VerifySmart_Course(driver);
			if (!result) {
				libraryPageMsgList.add("Fail to verify Smart Course");
				return result;
			}
			result = VerifyMicro_Course(driver);
			if (!result) {
				libraryPageMsgList.add("Fail to verify Smart Course");
				return result;
			}
			result = Verify_Books(driver);
			if (!result) {
				libraryPageMsgList.add("Fail to verify Books Page");
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
					
				}else
				{
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

	public boolean DropDownLibrary_Course(WebDriver driver) {
		boolean result = true;
		homeUtilObj = new HomePageUtil(driver);
		try {

			// click on DropDown button
			result = homeUtilObj.clickOnDropDown(driver);
			if (!result) {
				libraryPageMsgList.add("DropDown is not working");
				return result;
			}

			// click on Smart-Course button
			result = clickOnSmart(driver);
			if (!result) {
				libraryPageMsgList.add("Smart Course is not Available");
				return result;
			}

			// click on Micro-Course button
			result = clickOnMicro(driver);
			if (!result) {
				libraryPageMsgList.add("Micro Course is not Available");
				return result;
			}

			// click on Book button
			result = clickOnBook(driver);
			if (!result) {
				libraryPageMsgList.add("Book is not Available");
				return result;
			}

			// click on TestSeries button
			result = clickOnTestSeries(driver);
			if (!result) {
				libraryPageMsgList.add("TestSeries is not Available");
				return result;
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("verifyMyLibrary_Exception: " + e.getMessage());
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
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "(//*[contains(text(),'Books')])[1]",
					"xpath", 10);
			if (result == true) {
				cfObj.commonClick(libraryPage_OR.getDotBook());
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

}
