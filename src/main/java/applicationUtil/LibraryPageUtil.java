package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import pageObject.CoursePage_OR;
import pageObject.LibraryPage_OR;
import pojo.TestData;
import util.Common_Function;
import util.ConfigFileReader;

public class LibraryPageUtil {

	LibraryPage_OR libraryPage_OR;
	CoursePage_OR coursePage_OR;

	public List<String> libraryPageMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();
	ConfigFileReader rConfigFileReader;
	HomePageUtil homPageUtilObj;

	public LibraryPageUtil(WebDriver driver) {
		libraryPage_OR = new LibraryPage_OR();
		PageFactory.initElements(driver, libraryPage_OR);
	}

	public boolean verifyPuchasedCourseOnMyLibrary(WebDriver driver, TestData testData) {
		boolean result = true;
		try {
			result = verifyBoughtCourse(testData.getCourseName());
			if (!result) {
				return result;
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("verifyCourse_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyBoughtCourse(String searchCourseName) {
		boolean result = true;
		coursePage_OR = new CoursePage_OR();
		try {
			List<WebElement> linkCourses = libraryPage_OR.libraryTitles();
			for (int i = 0; i < linkCourses.size(); i++) {
				String courseInLibrary = linkCourses.get(i).getText().toLowerCase();
				if (courseInLibrary.contains(searchCourseName.toLowerCase())) {
					return true;
				}
			}
			result = false;

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("verifyBoughtCourse_Exception: " + e.getMessage());
		}
		return result;
	}

}