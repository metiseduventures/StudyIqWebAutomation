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
	
	public boolean validateFirst_Course(WebDriver driver) {
		boolean result = true;
		LibraryUtilObj=new LibraryPageUtil(driver);
		try {
			cfObj.commonClick(myCourses_OR.getClose_ExtendCourse());
			List<WebElement> tx=myCourses_OR.getCourseTopic_Title01();
			List<WebElement> tx1=myCourses_OR.getTitleJustInsideVideo();
			List<WebElement> tx2=myCourses_OR.getTitleJustInsideTitle();
			List<WebElement> tx3=myCourses_OR.getTitleOf_FreeCourses();
			List<WebElement> tx4=myCourses_OR.getTestSeries_TestContent();
            
			if(myCourses_OR.getCourseTopic_Title01().get(0).isDisplayed())
			{
				cfObj.commonClick(myCourses_OR.getCourseTopic_Title01().get(0));
				if(myCourses_OR.getTitleJustInsideVideo().get(0).isDisplayed()) {
					cfObj.commonClick(myCourses_OR.getCourseTopic_Title01().get(0));
					for(int i=0;i<tx.size();i++)
			        {
			        	cfObj.commonClick(tx.get(i));
			        	
			        	for(int j=0;j<tx1.size();j++)
			            {
			            	cfObj.commonClick(tx1.get(j));
			              }
			        }
					cfObj.commonClick(myCourses_OR.getCourseTopic_Title01().get(0));
					cfObj.commonClick(myCourses_OR.getTitleJustInsideVideo().get(0));
					cfObj.commonClick(myCourses_OR.getHomePage());
				}else if(myCourses_OR.getTitleJustInsideTitle().get(0).isDisplayed()) {
					cfObj.commonClick(myCourses_OR.getCourseTopic_Title01().get(0));
					for(int i=0;i<tx.size();i++)
			        {
			        	cfObj.commonClick(tx.get(i));
			        	
			        	for(int j=0;j<tx2.size();j++)
			            {
			            	cfObj.commonClick(tx1.get(j));
			              }
			        }
					cfObj.commonClick(myCourses_OR.getCourseTopic_Title01().get(0));
					cfObj.commonClick(myCourses_OR.getTitleJustInsideTitle().get(0));
					cfObj.commonClick(myCourses_OR.getTitleJustInsideTitle_IntoVideo().get(0));	
					cfObj.commonClick(myCourses_OR.getHomePage());
				}
			}else if(myCourses_OR.getTitleOf_FreeCourses().get(0).isDisplayed()) {
				cfObj.commonClick(myCourses_OR.getTitleOf_FreeCourses().get(0));
				for(int i=0;i<tx.size();i++)
		        {
		        	cfObj.commonClick(tx3.get(i));
		        }
				cfObj.commonClick(myCourses_OR.getTitleOf_FreeCourses().get(0));
				cfObj.commonClick(myCourses_OR.getTitleInsideContentOf_FreeCourses().get(0));
				cfObj.commonClick(myCourses_OR.getHomePage());
			}else if(myCourses_OR.getVideoContent_Course().get(0).isDisplayed()) {
				cfObj.commonClick(myCourses_OR.getVideoContent_Course().get(0));
				cfObj.commonClick(myCourses_OR.getHomePage());
			}else if(myCourses_OR.getTestSeries_TestContent().get(0).isDisplayed()) {
				for(int i=0;i<tx4.size();i++)
		        {
		        	cfObj.commonClick(tx4.get(i));
		        	cfObj.commonClick(myCourses_OR.getTestSeries_Course_BackButton());
		        }
				cfObj.commonClick(myCourses_OR.getTestSeries_Course_CloseButton());
				cfObj.commonClick(myCourses_OR.getHomePage());
			}

		} catch (Exception e) {
			result = false;
			myCoursePageMsgList.add("validateFirst_VideoCourse_Exception: " + e.getMessage());
		}
		return result;
	}
	
}
