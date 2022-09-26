package applicationUtil;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pageObject.CourseDetailsPage_OR;
import util.Common_Function;


public class CourseDetailsPageUtil {

	HomePageUtil homeUtilObj;
	CourseDetailsPage_OR CourseDetailsPageORObj;
	public List<String> CourseDetailsPageMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();

	public CourseDetailsPageUtil(WebDriver driver) {

		CourseDetailsPageORObj = new CourseDetailsPage_OR();
		PageFactory.initElements(driver, CourseDetailsPageORObj);
	}
	
	public boolean verifyCourseDetailsPage(WebDriver driver) {
		boolean result = true;
		try {
            homeUtilObj=new HomePageUtil(driver);
			result=homeUtilObj.clickOnBestSelling_Button(0);
			if (!result) { 
				CourseDetailsPageMsgList.add("Fail to BestSelling Courses");
			}
			// click on Courses Info
			  Thread.sleep(20000);
			result = OnCoursesInfo(driver);
			if (!result) {
				return result;
			}
              
			// enter Exams Covered
			result = clickOnExamsCovered(driver);

			if (result) {
				return result;
			}

			// click on About Authors
			result = clickOnAboutAuthors(driver);

			if (!result) {
				return result;
			}
			
			// click on Demo Videos
			result=clickOnDemoVideos(driver);
			
			if (!result) {
				return result;
			}
			
			// click on Get Free With This Course
			result=clickOnThisCourse(driver);
						
		    if (!result) {
				return result;
			}
		    
		   // click on Course Content
			
		    result=clickOnCourseContent(driver);
		 						
		     if (!result) {
		       return result;
		 	}
		    
		   // click on Our Packages
			
		   result=clickOnOurPackages(driver);
		
			if (!result) {
				return result;
			}

			// Click on Features

			result = clickOnFeatures(driver);
			
			//click on Frequently Asked Questions
			
			result=clickFAQ(driver);
			
			if(!result)
			{
				return result;
			}
			
          //click on Pricing
			
			result=clickOnPricing(driver);
			
			if(!result)
			{
				return result;
			}
			
		} catch (Exception e) {
			result = false;
			CourseDetailsPageMsgList.add("verifyCourseDetailsPage_Exception: " + e.getMessage());
		}

		return result;
	}
	
	
	public boolean OnCoursesInfo(WebDriver driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, CourseDetailsPageORObj.getCourseName_By(), 2);
	        if (!result) {
				CourseDetailsPageMsgList.add("Name of the Course is Available");
			}
			
			result = cfObj.commonWaitForElementToBeVisible(driver, CourseDetailsPageORObj.getDiscounted_Price(), 2);
	        if (!result) {
				CourseDetailsPageMsgList.add("No discounted Price");
			}
	        
	        result = cfObj.commonWaitForElementToBeVisible(driver, CourseDetailsPageORObj.getOriginal_Price(), 2);
	        if (!result) {
				CourseDetailsPageMsgList.add("No Original Price");
			}
			
	        result = cfObj.commonWaitForElementToBeVisible(driver, CourseDetailsPageORObj.getDiscount_Date(), 2);
	        if (!result) {
				CourseDetailsPageMsgList.add("There is No any Discounted Date");
			}
			
	        result = cfObj.commonWaitForElementToBeVisible(driver,CourseDetailsPageORObj.getBuyNow_Button(), 2);
			if (result==true) {
				cfObj.commonClick(CourseDetailsPageORObj.getBuyNow_Button());
			}
			else {
				result=false;
				CourseDetailsPageMsgList.add("BuyNow Button is not working");
		    }
			
			cfObj.commonClick(CourseDetailsPageORObj.getBuyNow_close());

			result = cfObj.commonWaitForElementToBeVisible(driver,CourseDetailsPageORObj.getPrice_increase(),2);
	        if (!result) {
				CourseDetailsPageMsgList.add("There is No any Price increasing time");
			}
			
	        List<WebElement> im=CourseDetailsPageORObj.getCourseInfo_Img2();
	        for(int i=0;i<im.size();i++)
	        {
	        	result = cfObj.commonWaitForElementToBeVisible(driver,im.get(i),2);
	        	if(!result) {
		        	CourseDetailsPageMsgList.add("Course Info not Contain item Image");
		        }
	        }
	        
	        List<WebElement> tx=CourseDetailsPageORObj.getCourseInfo_ImgText();
	        for(int i=0;i<tx.size();i++)
	        {
	        	result = cfObj.commonWaitForElementToBeVisible(driver,tx.get(i),2);
	        	if(!result) {
		        	CourseDetailsPageMsgList.add("Containt of Course is not available");
		        }
	        }

		} catch (Exception e) {
			result = false;
			CourseDetailsPageMsgList.add("clickOnCoursesInfo_Exception: " + e.getMessage());

		}
		return result;
	}
	
	public boolean clickOnExamsCovered(WebDriver driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver,CourseDetailsPageORObj.getExamsCovered_TextButton(), 2);
			if (result==true) {
				cfObj.commonClick(CourseDetailsPageORObj.getExamsCovered_TextButton());
			}
			else {
				result=false;
				CourseDetailsPageMsgList.add("Not of Exam Cover is not Available");
			}
			
			
			List<WebElement> tx1=CourseDetailsPageORObj.getExamsCovered_Textinside();
	        for(int i=0;i<tx1.size();i++)
	        {
	        	result=cfObj.commonWaitForElementToBeVisible(driver,tx1.get(i), 2);
	        	if(!result) {
		        	CourseDetailsPageMsgList.add("Containt of Exam Cover is not Available");
		        }
	        }	

		} catch (Exception e) {
			result = false;
			CourseDetailsPageMsgList.add("clickOnExamsCovered_Exception: " + e.getMessage());

		}
		return result;
	}
	
	
	public boolean clickOnAboutAuthors(WebDriver driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver,CourseDetailsPageORObj.getAboutAuthors_Button(), 2);
			if (result==true) {
				cfObj.commonClick(CourseDetailsPageORObj.getAboutAuthors_Button());
			}
			else {
				result=false;
				CourseDetailsPageMsgList.add("About Authors is not Working");
			}
			
			result = cfObj.commonWaitForElementToBeVisible(driver,CourseDetailsPageORObj.getAboutAuthors_Title(), 2);
			if (!result) {
				CourseDetailsPageMsgList.add("Title of About Authors is not Present ");
			}
			
			result = cfObj.commonWaitForElementToBeVisible(driver,CourseDetailsPageORObj.getAboutAuthors_img (), 2);
			if (!result) {
				CourseDetailsPageMsgList.add("Image of Autor is Not Present");
			}
			
			result = cfObj.commonWaitForElementToBeVisible(driver,CourseDetailsPageORObj.getAboutAuthors_Name(), 2);
			if (!result) {
				CourseDetailsPageMsgList.add("Name of The Autor is not Present");
			}
			
			result = cfObj.commonWaitForElementToBeVisible(driver,CourseDetailsPageORObj.getAboutAuthors_Department(), 2);
			if (!result) {
				CourseDetailsPageMsgList.add("Department of Author is not Present");
			}
			
			List<WebElement> tx2=CourseDetailsPageORObj.getAboutAuthors_Details();
	        for(int i=0;i<tx2.size();i++)
	        {
				result = cfObj.commonWaitForElementToBeVisible(driver,tx2.get(i), 2);
	        	if(!result) {
		        	CourseDetailsPageMsgList.add("Details of Autor is not Present");
		        }
	        }

		} catch (Exception e) {
			result = false;
			CourseDetailsPageMsgList.add("clickOnAboutAuthors_Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean clickOnDemoVideos(WebDriver driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver,CourseDetailsPageORObj.getDemo_Videos_Button(), 2);
			if (result==true) {
				cfObj.commonClick(CourseDetailsPageORObj.getDemo_Videos_Button());
			}
			else {
				result=false;
				CourseDetailsPageMsgList.add("Demo video is not Working");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,CourseDetailsPageORObj.getDemoVideos_Text(), 2);
			if (!result) {
				CourseDetailsPageMsgList.add("Title of Demo video is not Available");
			}
			
			
			List<WebElement> tx3=CourseDetailsPageORObj.getListOf_Videos();
			for(int i=0;i<tx3.size();i++)
	        {
	        	cfObj.commonClick(tx3.get(i));
				result = cfObj.commonWaitForElementToBeVisible(driver,tx3.get(i), 2);
	        	if(!result) {
		        	CourseDetailsPageMsgList.add("Course Content is not Available");
		        }
	        }

		} catch (Exception e) {
			result = false;
			CourseDetailsPageMsgList.add("clickOnDemoVideos_Exception: " + e.getMessage());

		}
		return result;
	}
	

	public boolean clickOnThisCourse(WebDriver driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver,CourseDetailsPageORObj.getThisCourses_Title(), 2);
			if (!result) {
				CourseDetailsPageMsgList.add("Title is not Available");
			}
			
			List<WebElement> tx3=CourseDetailsPageORObj.getThisCourses_AllElement();
	        for(int i=0;i<tx3.size();i++)
	        {
				result = cfObj.commonWaitForElementToBeVisible(driver,tx3.get(i), 2);
	        	if(!result) {
		        	CourseDetailsPageMsgList.add("Course Content is not Available");
		        }
	        }

		} catch (Exception e) {
			result = false;
			CourseDetailsPageMsgList.add("clickOnThisCourse_Exception: " + e.getMessage());

		}
		return result;
	}
	
	public boolean clickOnCourseContent(WebDriver driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver,CourseDetailsPageORObj.getCourseContent_Button(), 2);
			if (result==true) {
				cfObj.commonClick(CourseDetailsPageORObj.getCourseContent_Button());
			}
			else {
				result=false;
				CourseDetailsPageMsgList.add("Course Content button is not Working");
			}
			result = cfObj.commonWaitForElementToBeVisible(driver,CourseDetailsPageORObj.getCourseContent_Title(), 2);
			if (!result) {
				CourseDetailsPageMsgList.add("Title of Course is not Available");
			}
			
			result = cfObj.commonWaitForElementToBeVisible(driver,CourseDetailsPageORObj.getCourseContent_viewAll(), 2);
			if (result==true) {
				cfObj.commonClick(CourseDetailsPageORObj.getCourseContent_viewAll());
			}
			else {
				result=false;
				CourseDetailsPageMsgList.add("viewAll button is not Working");
			}
			
			
			List<WebElement> tx3=CourseDetailsPageORObj.getContent_AllElement();
	        for(int i=0;i<tx3.size();i++)
	        {
	        	cfObj.commonClick(tx3.get(i));
	        	result = cfObj.commonWaitForElementToBeVisible(driver,tx3.get(i), 2);
	        	if(!result) {
		        	CourseDetailsPageMsgList.add("Course Content is not Available");
		        }
	        }
	        
	        List<WebElement> tx4=CourseDetailsPageORObj.getContent_AllElementInside();
	        for(int i=0;i<tx4.size();i++)
	        {
	        	result = cfObj.commonWaitForElementToBeVisible(driver,tx4.get(i), 1);
	        	if(!result) {
		        	CourseDetailsPageMsgList.add("Course Content inside Course is not Available");
		        }
	        }
	         

		} catch (Exception e) {
			result = false;
			CourseDetailsPageMsgList.add("clickOnCourseContent_Exception: " + e.getMessage());

		}
		return result;
	}
	
	public boolean clickOnOurPackages(WebDriver driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver,CourseDetailsPageORObj.getOurPackages_title(), 2);
			if (!result) {
				CourseDetailsPageMsgList.add("Title of Packages is not Available");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,CourseDetailsPageORObj.getOurPackages_priceText(), 2);
			if (!result) {
				CourseDetailsPageMsgList.add("Price Box is not Available");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,CourseDetailsPageORObj.getOurPackages_priceText(), 2);
			if (!result) {
				CourseDetailsPageMsgList.add("Price Box is not Available");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,CourseDetailsPageORObj.getOurPackages_validityText(), 2);
			if (!result) {
				CourseDetailsPageMsgList.add("validity Box is not Available");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,CourseDetailsPageORObj.getOurPackages_EMIText(), 2);
			if (!result) {
				CourseDetailsPageMsgList.add("EMI Box is not Available");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,CourseDetailsPageORObj.getOurPackages_Premium(), 2);
			if (!result) {
				CourseDetailsPageMsgList.add("Premium Box is not Available");
			}
			
			result = cfObj.commonWaitForElementToBeVisible(driver,CourseDetailsPageORObj.getOurPackages_priceOriginal(), 2);
			if (!result) {
				CourseDetailsPageMsgList.add("Original Price Box is not Available");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,CourseDetailsPageORObj.getOurPackages_priceDiscounted(), 2);
			if (!result) {
				CourseDetailsPageMsgList.add("Discounted Price Box is not Available");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,CourseDetailsPageORObj.getOurPackages_validityTime(), 2);
			if (!result) {
				CourseDetailsPageMsgList.add("validity Time Box is not Available");
			}
		
		} catch (Exception e) {
			result = false;
			CourseDetailsPageMsgList.add("clickOnOurPackages_Exception: " + e.getMessage());

		}
		return result;
	}
	
	public boolean clickOnFeatures(WebDriver driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver,CourseDetailsPageORObj.getFeatures_Text(), 2);
			if (!result) {
				CourseDetailsPageMsgList.add("Title of Features is not Available");
			}
			
			cfObj.commonClick(CourseDetailsPageORObj.getexpand_featurest());
			
			List<WebElement> tx5=CourseDetailsPageORObj.getFeatures_TextElement();
	        for(int i=0;i<tx5.size();i++)
	        {
				result = cfObj.commonWaitForElementToBeVisible(driver,tx5.get(i), 1);
	        	if(!result) {
		        	CourseDetailsPageMsgList.add("Features Content is not Available");
		        }
	        }
	        
	        List<WebElement> tx6=CourseDetailsPageORObj.getFeatures_TextElement1();
	        for(int i=0;i<tx6.size();i++)
	        {
				result = cfObj.commonWaitForElementToBeVisible(driver,tx6.get(i), 1);
	        	if(!result) {
		        	CourseDetailsPageMsgList.add("inside Features Content is not Available");
		        }
	        }
	        
		} catch (Exception e) {
			result = false;
			CourseDetailsPageMsgList.add("clickOnFeatures_Exception: " + e.getMessage());

		}
		return result;
	}
	
	public boolean clickFAQ(WebDriver driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver,CourseDetailsPageORObj.getFAQ_Button(), 2);
			if (result==true) {
				cfObj.commonClick(CourseDetailsPageORObj.getFAQ_Button());
			}
			else {
				result=false;
				CourseDetailsPageMsgList.add("FAQ button is not Working");
			}
			
			result = cfObj.commonWaitForElementToBeVisible(driver,CourseDetailsPageORObj.getFAQ_Text(), 2);
			if (!result) {
				CourseDetailsPageMsgList.add("Title of FAQ is not Available");
			}
		
			result = cfObj.commonWaitForElementToBeVisible(driver,CourseDetailsPageORObj.getFAQ_ViewAllButton(), 2);
			if (result==true) {
				cfObj.commonClick(CourseDetailsPageORObj.getFAQ_ViewAllButton());
			}
			else {
				result=false;
				CourseDetailsPageMsgList.add("FAQ ViewAll button is not Working");
			}
			
			List<WebElement> tx7=CourseDetailsPageORObj.getFAQ_Question();
			List<WebElement> tx8=CourseDetailsPageORObj.getFAQ_TextInside();
	        for(int i=0;i<tx7.size();i++)
	        {
	        	cfObj.commonClick(tx7.get(i));
				result = cfObj.commonWaitForElementToBeVisible(driver,tx7.get(i), 2);
	        	if(!result) {
		        	CourseDetailsPageMsgList.add("Question Content is not Available");
		        }
	        	
	        	for(int j=0;j<tx8.size();j++)
	        	{
	    			result = cfObj.commonWaitForElementToBeVisible(driver,tx8.get(i), 2);
		        	if(!result) {
			        	CourseDetailsPageMsgList.add("Answer Content is not Available");
			        }
	        		
	        	}
	        }
	        
	        List<WebElement> tx4=CourseDetailsPageORObj.getContent_AllElementInside();
	        for(int i=0;i<tx4.size();i++)
	        {
				result = cfObj.commonWaitForElementToBeVisible(driver,tx4.get(i), 2);
	        	if(!result) {
		        	CourseDetailsPageMsgList.add("Course Content inside Course is not Available");
		        }
	        }
	         

		} catch (Exception e) {
			result = false;
			CourseDetailsPageMsgList.add("clickFAQ_Exception: " + e.getMessage());

		}
		return result;
	}
	
	public boolean clickOnPricing(WebDriver driver) {
		boolean result = true;
		try {
            
			result = cfObj.commonWaitForElementToBeVisible(driver,CourseDetailsPageORObj.getPricing_Button(), 2);
			if (result==true) {
				cfObj.commonClick(CourseDetailsPageORObj.getPricing_Button());
			}
			else {
				result=false;
				CourseDetailsPageMsgList.add("Pricing button is not Working");
			}
			
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//img[@class='close_icon']", "xpath", 10);
		    if (!result) {
				CourseDetailsPageMsgList.add("Close Button not working");
			}

		} catch (Exception e) {
			result = false;
			CourseDetailsPageMsgList.add("clickOnPricing_Exception: " + e.getMessage());

		}
		return result;
	}
		
}
