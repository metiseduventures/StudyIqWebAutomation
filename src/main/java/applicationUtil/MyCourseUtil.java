package applicationUtil;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pageObject.LibraryPage_OR;
import pageObject.MyCourse_OR;
import util.Common_Function;

public class MyCourseUtil {
	LibraryPage_OR libraryPage_OR;
	MyCourse_OR myCourses_OR;
	public Common_Function cfObj = new Common_Function();
	public List<String> myCoursePageMsgList = new ArrayList<String>();
	HomePageUtil homeUtilObj;
	LibraryPageUtil LibraryUtilObj;
	
	public MyCourseUtil(WebDriver driver) {
		myCourses_OR = new MyCourse_OR();
		PageFactory.initElements(driver, myCourses_OR);
	}
	

	public boolean validateFirst_SmartCourse(WebDriver driver) {
		boolean result = true;
		LibraryUtilObj=new LibraryPageUtil(driver);
		try {
			cfObj.commonClick(myCourses_OR.getClose_ExtendCourse());
			List<WebElement> tx=myCourses_OR.getTopics_Of_Syllabus();
			List<WebElement> tx1=myCourses_OR.getContaint_InsideTopics();

	        for(int i=0;i<tx.size();i++)
	        {
	        	cfObj.commonClick(tx.get(i));
	        	
	        	for(int j=0;j<tx1.size();j++)
	            {
	            	cfObj.commonClick(tx1.get(j));
	              }
	        }
	
			cfObj.commonClick(myCourses_OR.getTopics_Single());
			cfObj.commonClick(myCourses_OR.getContaint_Inside_SingleTopics());
			cfObj.commonClick(myCourses_OR.getVideo_OfSingle_Course());
			
			result=LibraryUtilObj.Check_Library(driver);
	        if(!result)
	        {
	        	myCoursePageMsgList.add("Library Page is not Open");
				return result;
	        }

		} catch (Exception e) {
			result = false;
			myCoursePageMsgList.add("validateFirst_SmartCourse_Exception: " + e.getMessage());
		}
		return result;
	}

	
	public boolean validateFirst_MicroCourse(WebDriver driver) {
		boolean result = true;
		LibraryUtilObj=new LibraryPageUtil(driver);
		try {
			cfObj.commonClick(myCourses_OR.getClose_ExtendCourse());
			
			List<WebElement> tx=myCourses_OR.getTopics_Of_Syllabus();
			List<WebElement> tx1=myCourses_OR.getContaint_InsideTopics();

	        for(int i=0;i<tx.size();i++)
	        {
	        	cfObj.commonClick(tx.get(i));
	        	
	        	for(int j=0;j<tx1.size();j++)
	            {
	            	cfObj.commonClick(tx1.get(j));
	              }
	        }

	        result=LibraryUtilObj.Check_Library(driver);
	        if(!result)
	        {
	        	myCoursePageMsgList.add("Library Page is not Open");
				return result;
	        }

		} catch (Exception e) {
			result = false;
			myCoursePageMsgList.add("validateFirst_MicroCourse_Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean validateFirst_Books(WebDriver driver) {
		boolean result = true;
		LibraryUtilObj=new LibraryPageUtil(driver);
		try {
			cfObj.commonClick(myCourses_OR.getClose_ExtendCourse());		
			cfObj.commonClick(myCourses_OR.getClose());
			
			List<WebElement> tx=myCourses_OR.getTopics_Of_Syllabus();
			List<WebElement> tx1=myCourses_OR.getContaint_InsideTopics();

	        for(int i=0;i<tx.size();i++)
	        {
	        	cfObj.commonClick(tx.get(i));
	        	
	        	for(int j=0;j<tx1.size();j++)
	            {
	            	cfObj.commonClick(tx1.get(j));
	              }
	        }
		} catch (Exception e) {
			result = false;
			myCoursePageMsgList.add("validateFirst_Books_Exception: " + e.getMessage());
		}
		return result;
	}
	
}

