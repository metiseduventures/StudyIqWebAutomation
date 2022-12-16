package applicationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
	public List<String> LeactureName;
	
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
	
	public boolean OpenCourse_ofVideo(WebDriver driver) {
		boolean result = true;
		LibraryUtilObj=new LibraryPageUtil(driver);
		LeactureName=new ArrayList<String>();
		try {
			List<WebElement> tx5=myCourses_OR.getDropDown_Arrow();
			List<WebElement> tx6=myCourses_OR.getInsideDropDown_Arrow();
			List<WebElement> tx10=myCourses_OR.getVIDEO_LogoCourse();
			int count=0;
            
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='ci_content_wrapper']/div/div/div/div/img", "xpath", 30);
			if(result) {
				if(tx5.size()>0)
				{
					for(int j=0;j<tx5.size();j++) {
						
						if(count>=4) {
							System.out.println(LeactureName.size());
							driver.navigate().back();
							break;
						}
						cfObj.commonClick(myCourses_OR.getDropDown_Arrow().get(j));
						result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='collapse show']/div/div/div/div[@class='subchapter_header list-group-item']/div/img",
								"xpath", 30);
						if(result) {
							if(tx6.size()>0) {
								for(int k=0;k<tx6.size();k++) {
									cfObj.commonClick(myCourses_OR.getInsideDropDown_Arrow().get(k));
									result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='collapse show']/div/div/div/img[contains(@src,'video_icon.png')]",
											"xpath", 20);
									if(result) {
										if(myCourses_OR.getVIDEO_Logo().size()>0 & myCourses_OR.getVIDEO_LogoCourse().size()>0 ) {
											for(int i=0;i<tx10.size();i++)
									        {
												result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='collapse show']/div/div/div[@class='video_name']",
														"xpath", 10);
												if(result) {
													count++;
										        	cfObj.commonClick(myCourses_OR.getVIDEO_LogoCourse().get(i));
										        	Thread.sleep(5000);
													result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".new_video_player_wrapper", "css", 30);
													if(result) {
														cfObj.commonClick(myCourses_OR.getVideoPlayer());
														Thread.sleep(10000);
														LeactureName.add(myCourses_OR.getVideoName_ContentPage().getText());
														driver.navigate().back();
														if(count==4) {
															break;
														}
														driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
													}else {
														myCoursePageMsgList.add("Video Leacture is not displayed");
														return result;
													}
												}else {
													myCoursePageMsgList.add("Not Click on Video Course ");
													return result;
												}
									        }	
										}
									}else {
										result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='collapse show']/div/div/span/img[contains(@src,'pdf.png')]",
												"xpath", 10);
										if(result) {
											myCoursePageMsgList.add("Contain PDF Document ");
											result=true;
										}else {
											result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='collapse show']/div/div/div/img[contains(@src,'quiz_icon.png')]",
													"xpath", 10);
											if(result) {
												myCoursePageMsgList.add("Contain Quiz ");
												result=true;
											}
										}
									}
									cfObj.commonClick(myCourses_OR.getInsideDropDown_Arrow().get(k));
									if(count==4) {
										break;
									}
								}
							}
						}else if(cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='collapse show']/div/div/div/img[contains(@src,'video_icon.png')]",
								"xpath", 10)) {
							result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='collapse show']/div/div/div[@class='video_name']",
									"xpath", 10);
							if(result) {
								for(int i=0;i<tx10.size();i++)
						        {
									result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='collapse show']/div/div/div[@class='video_name']",
											"xpath", 10);
									if(result) {
										count++;
							        	cfObj.commonClick(tx10.get(i));
							        	Thread.sleep(5000);
										result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='new_video_player_wrapper ']", "xpath", 30);
										if(result) {
											cfObj.commonClick(myCourses_OR.getVideoPlayer());
											Thread.sleep(20000);
											LeactureName.add(myCourses_OR.getVideoName_ContentPage().getText());
											driver.navigate().back();
											if(count==4) {
												break;
											}
											driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
										}else {
											myCoursePageMsgList.add("Video Leacture is not displayed");
											return result;
										}
									}
						        }
							}
						}else if(cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='collapse show']/div/div/span/img[contains(@src,'pdf.png')]",
								"xpath", 10)) {
							myCoursePageMsgList.add("Contain PDF Document ");
							result=true;
						}else if(cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='collapse show']/div/div/div/img[contains(@src,'quiz_icon.png')]",
								"xpath", 10)) {
							myCoursePageMsgList.add("Contain Quiz ");
							result=true; 
						}else if(cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='collapse show']/div/div/li",
								"xpath", 10)) {
							myCoursePageMsgList.add("Contain Test");
							result=true;
						}
						if(j==tx5.size()-1) {
							driver.navigate().back();
							break;
						}
					}
				}else {
					return result;
				}
			}

		} catch (Exception e) {
			result = false;
			myCoursePageMsgList.add("OpenCourse_ofVideo_Exception: " + e.getMessage());
		}
		return result;
	}
	
	
}
