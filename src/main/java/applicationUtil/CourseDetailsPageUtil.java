package applicationUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import apiUtil.CourseApiUtil;
import pageObject.CourseDetailsPage_OR;
import pojo.TestData;
import pojo.courseView.CourseView;
import util.Common_Function;
import util.ConfigFileReader;

public class CourseDetailsPageUtil {

	HomePageUtil homeUtilObj;
	CourseDetailsPage_OR CourseDetailsPageORObj;
	public List<String> CourseDetailsPageMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();
	public ConfigFileReader configReaderObj = new ConfigFileReader();

	public CourseDetailsPageUtil(WebDriver driver) {

		CourseDetailsPageORObj = new CourseDetailsPage_OR();
		PageFactory.initElements(driver, CourseDetailsPageORObj);
	}

	public boolean verifyCourseDetailsPage(WebDriver driver, TestData testData) {
		boolean result = true;
		CourseApiUtil courseApiUtilObj;
		CourseView courseViewObj;
		String strCourseSlug = null;

		try {
			homeUtilObj = new HomePageUtil(driver);
			if (testData.getCourseType().contains("smart")) {
				result = homeUtilObj.clickOnSmartCourseOnHomePage(driver);
				if (!result) {
					CourseDetailsPageMsgList.addAll(homeUtilObj.homePageMsgList);
					return result;
				}
			} else if (testData.getCourseType().contains("micro")) {
				result = homeUtilObj.clickOnMicroCourseOnHomePage(driver);
				if (!result) {
					CourseDetailsPageMsgList.addAll(homeUtilObj.homePageMsgList);
					return result;
				}
			} else if (testData.getCourseType().contains("books")) {
				result = homeUtilObj.clickOnBookOnHomePage(driver);
				if (!result) {
					CourseDetailsPageMsgList.addAll(homeUtilObj.homePageMsgList);
					return result;
				}
			} else if (testData.getCourseType().contains("live")) {
				result = homeUtilObj.clickOnLiveCoursesOnHomePage(driver);
				if (!result) {
					CourseDetailsPageMsgList.addAll(homeUtilObj.homePageMsgList);
					return result;
				}
			} else if (testData.getCourseType().contains("test-series")) {
				result = homeUtilObj.clickOnTestSeriesOnHomePage(driver);
				if (!result) {
					CourseDetailsPageMsgList.addAll(homeUtilObj.homePageMsgList);
					return result;
				}
			}
			// click on Courses Info
			strCourseSlug = driver.getCurrentUrl().split("course-detail/")[1];
			System.out.println("strCourseSlug:" + strCourseSlug);
			courseApiUtilObj = new CourseApiUtil();
			courseViewObj = courseApiUtilObj.getCourseViewData(strCourseSlug);
			if (courseViewObj == null) {
				CourseDetailsPageMsgList.add("Error in course detail api");
				return false;
			}
			// System.out.println(courseViewObj.getData().getPriceInfo());
			result = verifyCourseInfo(driver, courseViewObj);
			if (!result) {
				CourseDetailsPageMsgList.add("Course Info is not Present");
				return result;
			}
			// Close Notification
			cfObj.commonClick(CourseDetailsPageORObj.getColseNotification());

			result = verifyTestSeriesMockTest(driver, courseViewObj);
			if (!result) {
				return result;
			}
			// click on Pricing

			result = clickOnPricing(driver);

			if (!result) {
				return result;
			}

			if (courseViewObj.getData().getCourseType().getCourseTypeName().equalsIgnoreCase("Testseries")) {

				// click on Description
				result = VerifyDescription(driver);

				if (!result) {
					return result;
				}
			}

			// enter Exams Covered
			result = clickOnExamsCovered(driver, courseViewObj);

			if (!result) {
				return result;
			}

			// click on About Authors
			result = verifyAboutAuthor(driver, courseViewObj);

			if (!result) {
				return result;
			}

			if ((ConfigFileReader.strEnv).equalsIgnoreCase("prod")) {
				// click on Demo Videos
				result = veirfyDemoVideos(driver, courseViewObj);

				if (!result) {
					return result;
				}
			}

			// Verify Cross Sell
			result = verifySimilarCourses(driver, courseViewObj);
			if (!result) {
				return result;
			}

			// click on Our Packages

			result = clickOnOurPackages(driver);

			if (!result) {
				return result;
			}

			// click on Frequently Asked Questions

			result = clickFAQ(driver, courseViewObj);

			if (!result) {
				return result;
			}

			if (!courseViewObj.getData().getCourseType().getCourseTypeName().equalsIgnoreCase("Testseries")) {
				if (!courseViewObj.getData().getCourseType().getCourseTypeName().equalsIgnoreCase("books")) {

					// click on Get Free With This Course
					result = clickOnThisCourse(driver);

					if (!result) {
						return result;
					}

					// Click on Features

					result = clickOnFeatures(driver, courseViewObj);

					if (!result) {
						return result;
					}
				}

				// click on Course Content

				result = clickOnCourseContent(driver);

				if (!result) {
					return result;
				}

			}

		} catch (Exception e) {
			result = false;
			CourseDetailsPageMsgList.add("verifyCourseDetailsPage_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean verifyCourseInfo(WebDriver driver, CourseView courseViewObj) {
		boolean result = true;
		try {

			if (CourseDetailsPageORObj.getListCourseTitle().size() == 0) {
				CourseDetailsPageMsgList.add("Course title is not visible on course detail Page");
				result = false;
			}

			if (CourseDetailsPageORObj.getCourseInfoImage().size() == 0) {
				CourseDetailsPageMsgList.add("Course image is not visible on course detail Page");
				result = false;
			}
			if (CourseDetailsPageORObj.getDiscounted_Price().size() != 2) {
				CourseDetailsPageMsgList.add("No discounted Price");
				result = false;
			}

			if (!courseViewObj.getData().getCourseType().getCourseTypeName().equalsIgnoreCase("Live Classes")) {
				if (CourseDetailsPageORObj.getBuyNow_Button().size() != 1) {
					CourseDetailsPageMsgList.add("Buy now button is not display on course detail page");
					result = false;
				}

			} else {
				if (CourseDetailsPageORObj.getBtnBuyNowCourseInfo().size() != 1) {
					CourseDetailsPageMsgList.add("Buy now button is not display on course info page");
					result = false;
				}
			}

			/*
			 * if
			 * (courseViewObj.getData().getCourseType().getCourseTypeName().equalsIgnoreCase
			 * ("Testseries") ||
			 * courseViewObj.getData().getCourseType().getCourseTypeName().
			 * equalsIgnoreCase("Live Classes")) {
			 * 
			 * if (CourseDetailsPageORObj.getListTestSeriesCourseInfo().size() == 0) {
			 * CourseDetailsPageMsgList.
			 * add("Test series Course info is not display in course detail page"); result =
			 * false; } else {
			 * 
			 * }
			 * 
			 * } else { if (CourseDetailsPageORObj.getListCourseInfo().size() == 0) {
			 * CourseDetailsPageMsgList.
			 * add("Course info is not display in course detail page"); result = false; }
			 * else {
			 * 
			 * } }
			 */

		} catch (Exception e) {
			result = false;
			CourseDetailsPageMsgList.add("verifyCourseInfo_Exception: " + e.getMessage());

		}
		return result;
	}

	public boolean clickOnExamsCovered(WebDriver driver, CourseView courseViewObj) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, CourseDetailsPageORObj.getExamsCovered_TextButton(),
					2);

			if (courseViewObj.getData().getExamCovered().size() > 0) {
				if (result) {
					cfObj.commonClick(CourseDetailsPageORObj.getExamsCovered_TextButton());
				} else {

					CourseDetailsPageMsgList.add("Exam covered navigation is not display on course detail page");
					return result;

				}

				if (CourseDetailsPageORObj.getExamsCovered_Textinside().size() == 0) {
					CourseDetailsPageMsgList
							.add("Exam covered list is not display on course detail page when click on exam covered");
					return false;
				}

				if (CourseDetailsPageORObj.getExamsCovered_Textinside().size() != courseViewObj.getData()
						.getExamCovered().size()) {
					CourseDetailsPageMsgList.add("Mismatch in exam covered list in api and UI");
					return false;
				}
			} else {
				if (result) {
					CourseDetailsPageMsgList.add("Exam covered section section should not be display for course: "
							+ courseViewObj.getData().getCourseDetail().getCourseTitle());
					result = false;
				} else {
					result = true;
				}
			}

		} catch (Exception e) {
			result = false;
			CourseDetailsPageMsgList.add("clickOnExamsCovered_Exception: " + e.getMessage());

		}
		return result;
	}

	public boolean verifyAboutAuthor(WebDriver driver, CourseView courseViewObj) {
		boolean result = true;
		try {

			if (courseViewObj.getData().getAuthors().size() != 0) {

				result = cfObj.commonWaitForElementToBeVisible(driver, CourseDetailsPageORObj.getAboutAuthors_Button(),
						2);
				if (result) {
					cfObj.commonClick(CourseDetailsPageORObj.getAboutAuthors_Button());
				} else {
					CourseDetailsPageMsgList.add("About Author naviagtion is not display on the list");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, CourseDetailsPageORObj.getAboutAuthors_Title(),
						2);
				if (!result) {
					CourseDetailsPageMsgList.add("Title of About Authors is not Present ");
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, CourseDetailsPageORObj.getAboutAuthors_img(), 2);
				if (!result) {
					CourseDetailsPageMsgList.add("Image of Autor is Not Present");
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, CourseDetailsPageORObj.getAboutAuthors_Name(),
						2);
				if (!result) {
					CourseDetailsPageMsgList.add("Name of The Autor is not Present");
				}
			} else {

				result = cfObj.commonWaitForElementToBeVisible(driver, CourseDetailsPageORObj.getAboutAuthors_Button(),
						2);
				if (result) {
					CourseDetailsPageMsgList.add("Author should not be display in course detail page");
					result = false;
				} else {
					result = true;
				}
			}

		} catch (Exception e) {
			result = false;
			CourseDetailsPageMsgList.add("verifyAboutAuthor_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean veirfyDemoVideos(WebDriver driver, CourseView courseViewObj) {
		boolean result = true;
		int intStartTime, intPauseTime, intFinalTime;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, CourseDetailsPageORObj.getDemo_Videos_Button(), 20);
			if (courseViewObj.getData().getDemoUrls().size() > 0) {
				if (result) {
					cfObj.commonClick(CourseDetailsPageORObj.getDemo_Videos_Button());
				} else {

					CourseDetailsPageMsgList.add("Demo video not present navigation menu");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, CourseDetailsPageORObj.getDemoVideos_Text(), 20);
				if (!result) {
					CourseDetailsPageMsgList.add("Title of Demo video is not Available");
				}

				List<WebElement> tx3 = CourseDetailsPageORObj.getListOf_Videos();
				int sizeofVideo = tx3.size();
				for (int i = 0; i < sizeofVideo; i++) {
					cfObj.commonClick(tx3.get(i));
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".shaka-current-time", "css", 20);
					if (!result) {
						CourseDetailsPageMsgList.add("Demo video not opned when click on videos from the list");
						return result;
					}

					// get video time
					intStartTime = Integer.valueOf(cfObj.commonGetElement(driver, ".shaka-current-time", "css")
							.getText().toString().split("/")[0].trim().split(":")[2].trim());
					System.out.println(intStartTime);

					if (intStartTime > 0) {
						CourseDetailsPageMsgList.add("Start time should be zero in starting of the video for course: "
								+ courseViewObj.getData().getCourseDetail().getCourseTitle());
						return false;
					}

					// Click on play button

					cfObj.commonClick(cfObj.commonGetElement(driver, ".shaka-small-play-button", "css"));

					Thread.sleep(5000);

					// Click on pause button

					cfObj.commonClick(cfObj.commonGetElement(driver, ".shaka-small-play-button", "css"));
					intPauseTime = Integer.valueOf(cfObj.commonGetElement(driver, ".shaka-current-time", "css")
							.getText().toString().split("/")[0].trim().split(":")[2].trim());
					System.out.println(intPauseTime);
					if (intPauseTime < 5) {
						CourseDetailsPageMsgList.add("Video is not playing when click on start button for course: "
								+ courseViewObj.getData().getCourseDetail().getCourseTitle());
						return false;
					}

					Thread.sleep(5000);

					intFinalTime = Integer.valueOf(cfObj.commonGetElement(driver, ".shaka-current-time", "css")
							.getText().toString().split("/")[0].trim().split(":")[2].trim());
					System.out.println(intFinalTime);

					if (intPauseTime != intFinalTime) {
						CourseDetailsPageMsgList.add("Video is not paused when click on paused button for course: "
								+ courseViewObj.getData().getCourseDetail().getCourseTitle());
						return false;
					}
					Thread.sleep(5000);
				}
			} else {
				if (result) {
					CourseDetailsPageMsgList.add("Demo video should not be display for course name: "
							+ courseViewObj.getData().getCourseDetail().getCourseTitle().toString());
					result = false;
				} else {
					result = true;
				}
			}

		} catch (Exception e) {
			result = false;
			CourseDetailsPageMsgList.add("veirfyDemoVideos_Exception: " + e.getMessage());

		}
		return result;
	}

	public boolean clickOnThisCourse(WebDriver driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, CourseDetailsPageORObj.getThisCourses_Title(), 2);
			if (!result) {
				CourseDetailsPageMsgList.add("Title is not Available");
			}

			List<WebElement> tx3 = CourseDetailsPageORObj.getThisCourses_AllElement();
			for (int i = 0; i < tx3.size(); i++) {
				result = cfObj.commonWaitForElementToBeVisible(driver, tx3.get(i), 2);
				if (!result) {
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
			result = cfObj.commonWaitForElementToBeVisible(driver, CourseDetailsPageORObj.getCourseContent_Button(), 2);
			if (result == true) {
				cfObj.commonClick(CourseDetailsPageORObj.getCourseContent_Button());
			} else {
				result = false;
				CourseDetailsPageMsgList.add("Course Content button is not Working");
			}
			result = cfObj.commonWaitForElementToBeVisible(driver, CourseDetailsPageORObj.getCourseContent_Title(), 2);
			if (!result) {
				CourseDetailsPageMsgList.add("Title of Course is not Available");
			}

			cfObj.commonClick(CourseDetailsPageORObj.getCourseContent_viewAll());

			List<WebElement> tx3 = CourseDetailsPageORObj.getContent_AllElement();
			for (int i = 0; i < tx3.size(); i++) {
				cfObj.commonClick(tx3.get(i));
			}

			List<WebElement> tx4 = CourseDetailsPageORObj.getContent_AllElementInside();
			for (int i = 0; i < tx4.size(); i++) {
				tx4.get(i).isDisplayed();
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

			result = cfObj.commonWaitForElementToBeVisible(driver, CourseDetailsPageORObj.getOurPackages_title(), 2);
			if (!result) {
				CourseDetailsPageMsgList.add("Title of Packages is not Available");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, CourseDetailsPageORObj.getOurPackages_priceText(),
					2);
			if (!result) {
				CourseDetailsPageMsgList.add("Price Box is not Available");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, CourseDetailsPageORObj.getOurPackages_priceText(),
					2);
			if (!result) {
				CourseDetailsPageMsgList.add("Price Box is not Available");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, CourseDetailsPageORObj.getOurPackages_validityText(),
					2);
			if (!result) {
				CourseDetailsPageMsgList.add("validity Box is not Available");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, CourseDetailsPageORObj.getOurPackages_EMIText(), 2);
			if (!result) {
				CourseDetailsPageMsgList.add("EMI Box is not Available");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, CourseDetailsPageORObj.getOurPackages_Premium(), 2);
			if (!result) {
				CourseDetailsPageMsgList.add("Premium Box is not Available");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,
					CourseDetailsPageORObj.getOurPackages_priceOriginal(), 2);
			if (!result) {
				CourseDetailsPageMsgList.add("Original Price Box is not Available");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,
					CourseDetailsPageORObj.getOurPackages_priceDiscounted(), 2);
			if (!result) {
				CourseDetailsPageMsgList.add("Discounted Price Box is not Available");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, CourseDetailsPageORObj.getOurPackages_validityTime(),
					2);
			if (!result) {
				CourseDetailsPageMsgList.add("validity Time Box is not Available");
			}

		} catch (Exception e) {
			result = false;
			CourseDetailsPageMsgList.add("clickOnOurPackages_Exception: " + e.getMessage());

		}
		return result;
	}

	public boolean clickOnFeatures(WebDriver driver, CourseView courseViewObj) {
		boolean result = true;
		try {

			if (courseViewObj.getData().getPackages().get(0).getPackages().get(0).getFeatures().size() > 0) {

				result = cfObj.commonWaitForElementToBeVisible(driver, CourseDetailsPageORObj.getFeatures_Text(), 2);
				if (!result) {
					CourseDetailsPageMsgList.add("Title of Features is not Available");
				}

				cfObj.commonClick(CourseDetailsPageORObj.getexpand_featurest());

				List<WebElement> tx5 = CourseDetailsPageORObj.getFeatures_TextElement();
				for (int i = 0; i < tx5.size(); i++) {
					result = cfObj.commonWaitForElementToBeVisible(driver, tx5.get(i), 1);
					if (!result) {
						CourseDetailsPageMsgList.add("Features Content is not Available");
					}
				}

			} else {
				result = cfObj.commonWaitForElementToBeVisible(driver, CourseDetailsPageORObj.getFeatures_Text(), 2);
				if (result) {
					CourseDetailsPageMsgList.add("Title of Features should not be Available for course: "
							+ courseViewObj.getData().getCourseDetail().getCourseTitle());
					result = false;
				} else {
					result = true;
				}

			}

		} catch (Exception e) {
			result = false;
			CourseDetailsPageMsgList.add("clickOnFeatures_Exception: " + e.getMessage());

		}
		return result;
	}

	public boolean clickFAQ(WebDriver driver, CourseView courseViewObj) {
		boolean result = true;
		try {

			if (courseViewObj.getData().getCourseFaqs().size() > 0) {

				result = cfObj.commonWaitForElementToBeVisible(driver, CourseDetailsPageORObj.getFAQ_Button(), 2);
				if (result == true) {
					cfObj.commonClick(CourseDetailsPageORObj.getFAQ_Button());
				} else {
					result = false;
					CourseDetailsPageMsgList.add("FAQ button is not Working");
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, CourseDetailsPageORObj.getFAQ_Text(), 2);
				if (!result) {
					CourseDetailsPageMsgList.add("Title of FAQ is not Available");
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, CourseDetailsPageORObj.getFAQ_ViewAllButton(),
						2);
				if (result == true) {
					cfObj.commonClick(CourseDetailsPageORObj.getFAQ_ViewAllButton());
				} else {
					result = false;
					CourseDetailsPageMsgList.add("FAQ ViewAll button is not Working");
				}

				List<WebElement> tx7 = CourseDetailsPageORObj.getFAQ_Question();
				List<WebElement> tx8 = CourseDetailsPageORObj.getFAQ_TextInside();
				for (int i = 0; i < tx7.size(); i++) {
					cfObj.commonClick(tx7.get(i));
					result = cfObj.commonWaitForElementToBeVisible(driver, tx7.get(i), 2);
					if (!result) {
						CourseDetailsPageMsgList.add("Question Content is not Available");
					}

					for (int j = 0; j < tx8.size(); j++) {
						result = cfObj.commonWaitForElementToBeVisible(driver, tx8.get(i), 2);
						if (!result) {
							CourseDetailsPageMsgList.add("Answer Content is not Available");
						}

					}
				}

				List<WebElement> tx4 = CourseDetailsPageORObj.getContent_AllElementInside();
				for (int i = 0; i < tx4.size(); i++) {
					result = cfObj.commonWaitForElementToBeVisible(driver, tx4.get(i), 2);
					if (!result) {
						CourseDetailsPageMsgList.add("Course Content inside Course is not Available");
					}
				}
			} else {
				result = cfObj.commonWaitForElementToBeVisible(driver, CourseDetailsPageORObj.getFAQ_Button(), 2);
				if (result) {
					CourseDetailsPageMsgList.add("FAQ button is display for the course");
					result = false;
				} else {
					result = true;

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

			result = cfObj.commonWaitForElementToBeVisible(driver, CourseDetailsPageORObj.getPricing_Button(), 2);
			if (result == true) {
				cfObj.commonClick(CourseDetailsPageORObj.getPricing_Button());
			} else {
				result = false;
				CourseDetailsPageMsgList.add("Pricing button is not Working");
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//img[@class='crossImage']", "xpath", 10);

			if (result == true) {
				cfObj.commonClick(CourseDetailsPageORObj.getREVAMPcloseButton());
			} else {
				result = false;
				CourseDetailsPageMsgList.add("Close button is not Available");
			}

		} catch (Exception e) {
			result = false;
			CourseDetailsPageMsgList.add("clickOnPricing_Exception: " + e.getMessage());

		}
		return result;
	}

	public boolean VerifyDescription(WebDriver driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//button[contains(text(),'Description')]",
					"xpath", 10);
			if (result == true) {
				cfObj.commonClick(CourseDetailsPageORObj.getListDescription_Button());

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//div[@class='course_title page_titles']", "xpath", 10);
				if (result == true) {
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
							"//span[contains(text(),'show more')]", "xpath", 10);
					if (result == true) {
						cfObj.commonClick(CourseDetailsPageORObj.getShowMore_Button());
					} else {
						CourseDetailsPageMsgList.add("Show_More Button is not Available");
					}

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
							"//div[@class='description_formatted']", "xpath", 10);
					if (result == true) {
						CourseDetailsPageMsgList.add("About Description is Available");
					} else {
						return result;
					}
				}
			} else {
				result = false;
				CourseDetailsPageMsgList.add("Description Title is not Available");
			}

		} catch (Exception e) {
			result = false;
			CourseDetailsPageMsgList.add("VerifyDescription_Exception: " + e.getMessage());

		}
		return result;
	}

	public boolean clickOnBuyNow(WebDriver driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"(//button[contains(text(),'Buy Now')])[1]", "xpath", 10);
			if (result == true) {
				cfObj.commonClick(CourseDetailsPageORObj.getNavBar_BuyNow_Button());
			} else {
				result = false;
				CourseDetailsPageMsgList.add("BuyNow button is not Available");
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//img[@class='close_icon']", "xpath", 10);
			if (result == true) {
				cfObj.commonClick(CourseDetailsPageORObj.getcloseButton());
			}

		} catch (Exception e) {
			result = false;
			CourseDetailsPageMsgList.add("clickOnBuyNow_Exception: " + e.getMessage());

		}
		return result;
	}

	public boolean verifyTestSeriesMockTest(WebDriver driver, CourseView courseViewObj) {
		boolean result = true;
		String noOfCount;

		try {

			if (courseViewObj.getData().getCourseType().getCourseTypeName().equalsIgnoreCase("Testseries")) {
				if (CourseDetailsPageORObj.getListTestSeriesButton().size() == 0) {
					CourseDetailsPageMsgList.add("Mock test series button is not display on test series type course");
					return false;
				} else {
					cfObj.commonClick(CourseDetailsPageORObj.getListTestSeriesButton().get(0));
					// wait for test series list pop up to be opened
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
							".test-series-lesson-course-packages", "css", 5);
					if (!result) {
						CourseDetailsPageMsgList.add(
								"test series pop up is not display on when click mock test button on course detail page");
						return result;
					} else {

						noOfCount = CourseDetailsPageORObj.getListTestSeriesButton().get(0).getText().toString();
						System.out.println("noOfCount: " + noOfCount);
						System.out.println(courseViewObj.getData().getCourseDetail().getReferenceCount());

						// click on close pop up
						cfObj.commonClick(cfObj.commonGetElement(driver, ".btn-link", "css"));
						result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
								".test-series-lesson-course-packages", "css", 5);
						if (result) {
							CourseDetailsPageMsgList
									.add("test series pop up is not closed after click on cross button");
							return false;

						} else {
							return true;
						}
					}
				}

			} else {
				if (CourseDetailsPageORObj.getListTestSeriesButton().size() != 0) {
					CourseDetailsPageMsgList.add("Mock test series button is display on non test series type course");
					return false;
				}

			}

		} catch (Exception e) {
			result = false;
			CourseDetailsPageMsgList.add("verifyTestSeriesMockTest_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifySimilarCourses(WebDriver driver, CourseView courseViewObj) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"(//h1[contains(text(),'Similar Courses')])[2]", "xpath", 10);

			if (courseViewObj.getData().getCrossSellDetails().size() > 0) {
				if (result) {
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
							"(//div[@class='crossSell-Card-main-slick'])", "xpath", 10);
					if (result == true) {
						List<WebElement> L1 = CourseDetailsPageORObj.getlistOF_similarCourse();
						for (int i = 0; i < L1.size(); i++) {
							String NameOfCOurse = CourseDetailsPageORObj.getNameOf_listOF_similarCourse().get(i)
									.getText();

							cfObj.commonClick(CourseDetailsPageORObj.getViewButton_similarCourse().get(i));

							Set<String> windowsId = driver.getWindowHandles();
							Iterator<String> itr = windowsId.iterator();
							String defaultwindowId = itr.next();
							String childwindowId = itr.next();
							driver.switchTo().window(childwindowId);
							result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
									"//div[@class='title_wrapper']/div[@class='title']", "xpath", 30);
							if (result == true) {
								String CourseTitle = CourseDetailsPageORObj.getCourseTitle_similarCourse().getText();
								result = NameOfCOurse.contains(CourseTitle);
								if (result == true) {
									CourseDetailsPageMsgList.add("Course Name is Equal");
								} else {
									CourseDetailsPageMsgList.add("Course Name is not Equal");
									return result;
								}
								driver.close();
								driver.switchTo().window(defaultwindowId);
								Thread.sleep(7000);
							} else {
								result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
										"//button[@class='not-found--button']", "xpath", 30);
								if (result) {
									driver.close();
									driver.switchTo().window(defaultwindowId);
									Thread.sleep(7000);
								}
							}
						}
					} else {
						CourseDetailsPageMsgList.add("No Similar Course is available");
						return result;
					}
				} else {
					result = false;
					CourseDetailsPageMsgList.add("BuyNow button is not Available");
				}
			} else {

				if (result) {
					CourseDetailsPageMsgList.add("Similar Course is available if data is blank in api");
					return false;
				} else {
					return true;
				}
			}

		} catch (Exception e) {
			result = false;
			CourseDetailsPageMsgList.add("verifySimilarCourses_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyCourseSell(WebDriver driver) {
		boolean result = true;
		CourseApiUtil courseApiUtilObj;
		CourseView courseViewObj;
		String strCourseSlug = null;

		try {
			homeUtilObj = new HomePageUtil(driver);

			driver.navigate()
					.to(configReaderObj.getBaseUrlWeb() + "course-detail/" + configReaderObj.getCrossSellCourseSlug());

			// click on Courses Info
			Thread.sleep(20000);
			strCourseSlug = configReaderObj.getCrossSellCourseSlug();
			System.out.println("strCourseSlug:" + strCourseSlug);
			courseApiUtilObj = new CourseApiUtil();
			courseViewObj = courseApiUtilObj.getCourseViewData(strCourseSlug);
			System.out.println(courseViewObj.getData().getPriceInfo());
			result = verifyCourseInfo(driver, courseViewObj);
			if (!result) {
				CourseDetailsPageMsgList.add("Course Info is not Present");
			}
			// Verify Cross Sell
			result = verifySimilarCourses(driver, courseViewObj);
			if (!result) {
				return result;
			}

		} catch (Exception e) {
			result = false;
			CourseDetailsPageMsgList.add("verifyCourseSell_Exception: " + e.getMessage());
		}

		return result;
	}

}
