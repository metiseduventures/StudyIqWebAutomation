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
	//OtpUtil otpUtilObj;

	public CourseDetailsPageUtil(WebDriver driver) {

		CourseDetailsPageORObj = new CourseDetailsPage_OR(driver);
		PageFactory.initElements(driver, CourseDetailsPageORObj);
	}
	
	public boolean verifyCourseDetailsPage(WebDriver driver) {
		boolean result = true;
		try {
			
			// click on Courses Info
			Thread.sleep(20000);
			result = OnCoursesInfo(driver);
			if (!result) {
				return result;
			}
              
			// enter Exams Covered
			result = clickOnExamsCovered();

			if (result) {
				return result;
			}

			// click on About Authors

			result = clickOnAboutAuthors();

			if (!result) {
				return result;
			}
			
			// click on Demo Videos
			
			result=clickOnDemoVideos();
			
			if (!result) {
				return result;
			}
			
			// click on Get Free With This Course
			
			Thread.sleep(2000);
			
			result=clickOnThisCourse();
						
		    if (!result) {
				return result;
			}
		    
		   // click on Course Content
			
		    result=clickOnCourseContent(driver);
		 						
		     if (!result) {
		       return result;
		 	}
		    
		   // click on Our Packages
			
		   result=clickOnOurPackages();
		
			if (!result) {
				return result;
			}

			// Click on Features

			result = clickOnFeatures();
			
			//click on Frequently Asked Questions
			
			result=clickFAQ();
			
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

			
			result = cfObj.commonWaitForElementToBeVisible(driver, CourseDetailsPageORObj.getDiscounted_Price(), 10);
	        if (!result) {
				CourseDetailsPageMsgList.add("No discounted Price");
			}
	        
	        result = cfObj.commonWaitForElementToBeVisible(driver, CourseDetailsPageORObj.getOriginal_Price(), 10);
	        if (!result) {
				CourseDetailsPageMsgList.add("No Original Price");
			}
			
	        result = cfObj.commonWaitForElementToBeVisible(driver, CourseDetailsPageORObj.getDiscount_Date(), 10);
	        if (!result) {
				CourseDetailsPageMsgList.add("There is No any Discounted Date");
			}
			
	     
	        boolean b1=CourseDetailsPageORObj.getBuyNow_Button().isDisplayed();
			if (b1==true) {
				CourseDetailsPageORObj.getBuyNow_Button().click();
				Thread.sleep(2000);
			}
			else {
				result=false;
				CourseDetailsPageMsgList.add("BuyNow Button is not working");
		}
	
			
			cfObj.commonClick(CourseDetailsPageORObj.getBuyNow_close());

			
			result=CourseDetailsPageORObj.getPrice_increase().isDisplayed();
	        if (!result) {
				CourseDetailsPageMsgList.add("There is No any Price increasing time");
			}
			
	        List<WebElement> im=CourseDetailsPageORObj.getCourseInfo_Img2();
	        for(int i=0;i<im.size();i++)
	        {
	        	result=im.get(i).isDisplayed();
	        	if(!result) {
		        	CourseDetailsPageMsgList.add("Course Info not Contain item Image");
		        }
	        }
	        
	        List<WebElement> tx=CourseDetailsPageORObj.getCourseInfo_ImgText();
	        for(int i=0;i<tx.size();i++)
	        {
	        	result=tx.get(i).isDisplayed();
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
	
	public boolean clickOnExamsCovered() {
		boolean result = true;
		try {

			boolean b1=CourseDetailsPageORObj.getExamsCovered_TextButton().isDisplayed();
			if (b1==true) {
				CourseDetailsPageORObj.getExamsCovered_TextButton().click();
				Thread.sleep(2000);
			}
			else {
				result=false;
				CourseDetailsPageMsgList.add("Not of Exam Cover is not Available");
			}
			
			
			List<WebElement> tx1=CourseDetailsPageORObj.getExamsCovered_Textinside();
	        for(int i=0;i<tx1.size();i++)
	        {
	        	result=tx1.get(i).isDisplayed();
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
	
	
	public boolean clickOnAboutAuthors() {
		boolean result = true;
		try {

			//cfObj.commonClick(CourseDetailsPageORObj.getAboutAuthors_Button());
			
			boolean b1=CourseDetailsPageORObj.getAboutAuthors_Button().isDisplayed();
			if (b1==true) {
				CourseDetailsPageORObj.getAboutAuthors_Button().click();
				Thread.sleep(2000);
			}
			else {
				result=false;
				CourseDetailsPageMsgList.add("About Authors is not Working");
			}
			
			result=CourseDetailsPageORObj.getAboutAuthors_Title().isDisplayed();
			if (!result) {
				CourseDetailsPageMsgList.add("Title of About Authors is not Present ");
			}
			
			result=CourseDetailsPageORObj.getAboutAuthors_img ().isDisplayed();
			if (!result) {
				CourseDetailsPageMsgList.add("Image of Autor is Not Present");
			}
			
			result=CourseDetailsPageORObj.getAboutAuthors_Name().isDisplayed();
			if (!result) {
				CourseDetailsPageMsgList.add("Name of The Autor is not Present");
			}
			
			result=CourseDetailsPageORObj.getAboutAuthors_Department().isDisplayed();
			if (!result) {
				CourseDetailsPageMsgList.add("Department of Author is not Present");
			}
			
			List<WebElement> tx2=CourseDetailsPageORObj.getAboutAuthors_Details();
	        for(int i=0;i<tx2.size();i++)
	        {
	        	result=tx2.get(i).isDisplayed();
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
	
	public boolean clickOnDemoVideos() {
		boolean result = true;
		try {
			
			
			boolean b1=CourseDetailsPageORObj.getDemo_Videos_Button().isDisplayed();
			if (b1==true) {
				CourseDetailsPageORObj.getDemo_Videos_Button().click();
				Thread.sleep(2000);
			}
			else {
				result=false;
				CourseDetailsPageMsgList.add("Demo video is not Working");
			}

			result=CourseDetailsPageORObj.getDemoVideos_Text().isDisplayed();
			if (!result) {
				CourseDetailsPageMsgList.add("Title of Demo video is not Available");
			}
			
			
			List<WebElement> tx3=CourseDetailsPageORObj.getListOf_Videos();
			for(int i=0;i<tx3.size();i++)
	        {
	        	cfObj.commonClick(tx3.get(i));
	        	result=tx3.get(i).isDisplayed();
	        	if(!result) {
		        	CourseDetailsPageMsgList.add("Course Content is not Available");
		        }
	        	
	        	Thread.sleep(10000);
	        }

		} catch (Exception e) {
			result = false;
			CourseDetailsPageMsgList.add("clickOnDemoVideos_Exception: " + e.getMessage());

		}
		return result;
	}
	

	public boolean clickOnThisCourse() {
		boolean result = true;
		try {

			result=CourseDetailsPageORObj.getThisCourses_Title().isDisplayed();
			if (!result) {
				CourseDetailsPageMsgList.add("Title is not Available");
			}
			
			List<WebElement> tx3=CourseDetailsPageORObj.getThisCourses_AllElement();
	        for(int i=0;i<tx3.size();i++)
	        {
	        	result=tx3.get(i).isDisplayed();
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

			//cfObj.commonClick(CourseDetailsPageORObj.getCourseContent_Button());
			
			boolean b1=CourseDetailsPageORObj.getCourseContent_Button().isDisplayed();
			if (b1==true) {
				CourseDetailsPageORObj.getCourseContent_Button().click();
				Thread.sleep(2000);
			}
			else {
				result=false;
				CourseDetailsPageMsgList.add("Course Content button is not Working");
			}
			
			result=CourseDetailsPageORObj.getCourseContent_Title().isDisplayed();
			if (!result) {
				CourseDetailsPageMsgList.add("Title of Course is not Available");
			}
			
			//cfObj.commonClick(CourseDetailsPageORObj.getCourseContent_viewAll());
			
			boolean b2=CourseDetailsPageORObj.getCourseContent_viewAll().isDisplayed();
			if (b2==true) {
				CourseDetailsPageORObj.getCourseContent_viewAll().click();
				Thread.sleep(2000);
			}
			else {
				result=false;
				CourseDetailsPageMsgList.add("viewAll button is not Working");
			}
			
			
			List<WebElement> tx3=CourseDetailsPageORObj.getContent_AllElement();
	        for(int i=0;i<tx3.size();i++)
	        {
	        	cfObj.commonClick(tx3.get(i));
	        	result=tx3.get(i).isDisplayed();
	        	if(!result) {
		        	CourseDetailsPageMsgList.add("Course Content is not Available");
		        }
	        }
	        
	        List<WebElement> tx4=CourseDetailsPageORObj.getContent_AllElementInside();
	        for(int i=0;i<tx4.size();i++)
	        {
	        	result=tx4.get(i).isDisplayed();
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
	
	public boolean clickOnOurPackages() {
		boolean result = true;
		try {

			result=CourseDetailsPageORObj.getOurPackages_title().isDisplayed();
			if (!result) {
				CourseDetailsPageMsgList.add("Title of Packages is not Available");
			}
			
			result=CourseDetailsPageORObj.getOurPackages_priceText().isDisplayed();
			if (!result) {
				CourseDetailsPageMsgList.add("Price Box is not Available");
			}
			
			result=CourseDetailsPageORObj.getOurPackages_priceText().isDisplayed();
			if (!result) {
				CourseDetailsPageMsgList.add("Price Box is not Available");
			}
			
			result=CourseDetailsPageORObj.getOurPackages_validityText().isDisplayed();
			if (!result) {
				CourseDetailsPageMsgList.add("validity Box is not Available");
			}
			
			result=CourseDetailsPageORObj.getOurPackages_EMIText().isDisplayed();
			if (!result) {
				CourseDetailsPageMsgList.add("EMI Box is not Available");
			}
			
			result=CourseDetailsPageORObj.getOurPackages_Premium().isDisplayed();
			if (!result) {
				CourseDetailsPageMsgList.add("Premium Box is not Available");
			}
			
			result=CourseDetailsPageORObj.getOurPackages_priceOriginal().isDisplayed();
			if (!result) {
				CourseDetailsPageMsgList.add("Original Price Box is not Available");
			}
			
			result=CourseDetailsPageORObj.getOurPackages_priceDiscounted().isDisplayed();
			if (!result) {
				CourseDetailsPageMsgList.add("Discounted Price Box is not Available");
			}
			
			result=CourseDetailsPageORObj.getOurPackages_validityTime().isDisplayed();
			if (!result) {
				CourseDetailsPageMsgList.add("validity Time Box is not Available");
			}
		
		} catch (Exception e) {
			result = false;
			CourseDetailsPageMsgList.add("clickOnOurPackages_Exception: " + e.getMessage());

		}
		return result;
	}
	
	public boolean clickOnFeatures() {
		boolean result = true;
		try {

			result=CourseDetailsPageORObj.getFeatures_Text().isDisplayed();
			if (!result) {
				CourseDetailsPageMsgList.add("Title of Features is not Available");
			}
			
			cfObj.commonClick(CourseDetailsPageORObj.getexpand_featurest());
			
			List<WebElement> tx5=CourseDetailsPageORObj.getFeatures_TextElement();
	        for(int i=0;i<tx5.size();i++)
	        {
	        	result=tx5.get(i).isDisplayed();
	        	if(!result) {
		        	CourseDetailsPageMsgList.add("Features Content is not Available");
		        }
	        }
	        
	        List<WebElement> tx6=CourseDetailsPageORObj.getFeatures_TextElement1();
	        for(int i=0;i<tx6.size();i++)
	        {
	        	result=tx6.get(i).isDisplayed();
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
	
	public boolean clickFAQ() {
		boolean result = true;
		try {

			//cfObj.commonClick(CourseDetailsPageORObj.getFAQ_Button());
			
			boolean b2=CourseDetailsPageORObj.getFAQ_Button().isDisplayed();
			if (b2==true) {
				CourseDetailsPageORObj.getFAQ_Button().click();
				Thread.sleep(2000);
			}
			else {
				result=false;
				CourseDetailsPageMsgList.add("FAQ button is not Working");
			}
			
			result=CourseDetailsPageORObj.getFAQ_Text().isDisplayed();
			if (!result) {
				CourseDetailsPageMsgList.add("Title of FAQ is not Available");
			}
		
			
			boolean b=CourseDetailsPageORObj.getFAQ_ViewAllButton().isDisplayed();
			if (b==true) {
				CourseDetailsPageORObj.getFAQ_ViewAllButton().click();
				Thread.sleep(2000);
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
	        	result=tx7.get(i).isDisplayed();
	        	if(!result) {
		        	CourseDetailsPageMsgList.add("Question Content is not Available");
		        }
	        	
	        	for(int j=0;j<tx8.size();j++)
	        	{
	        		result=tx8.get(i).isDisplayed();
		        	if(!result) {
			        	CourseDetailsPageMsgList.add("Answer Content is not Available");
			        }
	        		
	        	}
	        }
	        
	        List<WebElement> tx4=CourseDetailsPageORObj.getContent_AllElementInside();
	        for(int i=0;i<tx4.size();i++)
	        {
	        	result=tx4.get(i).isDisplayed();
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
            
			
			boolean b=CourseDetailsPageORObj.getPricing_Button().isDisplayed();
			if (b==true) {
				CourseDetailsPageORObj.getPricing_Button().click();
				Thread.sleep(2000);
			}
			else {
				result=false;
				CourseDetailsPageMsgList.add("Pricing button is not Working");
			}
			
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//img[@class='close_icon']", "xpath", 30);
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
