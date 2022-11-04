package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import apiUtil.CourseApiUtil;
import pageObject.TestAttempPage_OR;
import pojo.courseView.CourseView;
import util.Common_Function;
import util.ConfigFileReader;

public class TestSeriesUtil {

	TestAttempPage_OR testAttempPageObj;
	public ArrayList<String> testAttemptPageMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();
	TestInstrcutionUtil testInstructionUtilObj;
	public int intAnsCount, intNotAnsCount, intNotVisitedCount, intMarkForReviewCount, intAnsAndMarkCount,
			intTotalAnsweredCount;
	public HomePageUtil homePageUtilObj;
	public ConfigFileReader configObject = new ConfigFileReader();

	public TestSeriesUtil(WebDriver driver) {

		testAttempPageObj = new TestAttempPage_OR();
		PageFactory.initElements(driver, testAttempPageObj);
	}

	public boolean verifyTestExecutionFlow(WebDriver driver) {
		boolean result = true;
		String strCourseSlug;
		CourseApiUtil courseApiUtilObj;
		CourseView courseViewObj;
		CoursePageUtil coursePageUtilObj;
		try {
			homePageUtilObj = new HomePageUtil(driver);
			result = homePageUtilObj.verifySignUp(driver);
			if (!result) {
				testAttemptPageMsgList.addAll(homePageUtilObj.homePageMsgList);
				return result;
			}

			result = homePageUtilObj.clickOnTestSeriesOnHomePage(driver);
			if (!result) {
				testAttemptPageMsgList.addAll(homePageUtilObj.homePageMsgList);
				return result;
			}

			strCourseSlug = driver.getCurrentUrl().split("course-detail/")[1];
			System.out.println("strCourseSlug:" + strCourseSlug);
			courseApiUtilObj = new CourseApiUtil();
			courseViewObj = courseApiUtilObj.getCourseViewData(strCourseSlug);
			System.out.println(courseViewObj.getData().getPriceInfo());

			coursePageUtilObj = new CoursePageUtil(driver);

			result = coursePageUtilObj.selectExamPrefrences(driver);
			if (!result) {
				testAttemptPageMsgList.addAll(coursePageUtilObj.coursePageMsgList);
				return result;
			}
			driver.navigate()
					.to(configObject.getBaseUrl() + "test-series/" + configObject.getCourseTestId() + "/UPSC%20Quiz");

			Thread.sleep(10000);
			testInstructionUtilObj = new TestInstrcutionUtil(driver);
			result = testInstructionUtilObj.clickOnStartTest(driver);
			if (!result) {
				testAttemptPageMsgList.addAll(testInstructionUtilObj.testInstrcutionPageMsgList);
				return result;
			}
			result = attmepTest(driver);

		} catch (Exception e) {
			result = false;
			testAttemptPageMsgList.add("verifyTestExecutionFlow_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean attmepTest(WebDriver driver) {
		boolean result = true;
		int noOfSection;
		String strSectionName;
		List<String> viewResult;
		int noOfQuestion;

		try {

			viewResult = new ArrayList<String>();

			noOfSection = testAttempPageObj.getListLabelTestSection().size();

			// check first section should be selected

			if (!testAttempPageObj.getListLabelTestSection().get(0).getAttribute("class").toString()
					.equalsIgnoreCase("blue")) {
				testAttemptPageMsgList.add("First section is not selected");
				return false;
			}
			strSectionName = testAttempPageObj.getListLabelTestSection().get(0).getText().toString();
			noOfQuestion = testAttempPageObj.getListNoOfQuestionPerSection().size();
			result = verifyQuestionStatus(strSectionName, 0, 0, noOfQuestion, 0, 0);
			if (!result) {
				return result;
			}
			for (int i = 0; i < noOfSection; i++) {
				strSectionName = testAttempPageObj.getListLabelTestSection().get(i).getText().toString();
				cfObj.commonClick(testAttempPageObj.getListLabelTestSection().get(i));
				noOfQuestion = testAttempPageObj.getListNoOfQuestionPerSection().size();
				intAnsCount = 0;
				intNotAnsCount = 0;
				intNotVisitedCount = 0;
				intMarkForReviewCount = 0;
				intAnsAndMarkCount = 0;
				if (!testAttempPageObj.getListLabelTestSection().get(i).getAttribute("class").toString()
						.equalsIgnoreCase("blue")) {
					testAttemptPageMsgList.add(i + " Section is not selected");
					return false;
				}

				for (int j = 1; j <= noOfQuestion - 1; j++) {
					int index = Common_Function.RandomNumber(1, 4);
					System.out.println("index: " + index);
					attempQuestion(index);
					intNotVisitedCount = noOfQuestion
							- (intAnsCount + intNotAnsCount + intMarkForReviewCount + intAnsAndMarkCount);
				}

				viewResult.add(strSectionName + "_" + (intAnsCount + intAnsAndMarkCount) + "_"
						+ (noOfQuestion - (intAnsCount + intAnsAndMarkCount + intNotVisitedCount)) + "_"
						+ intNotVisitedCount + "_" + noOfQuestion);
				result = verifyQuestionStatus(strSectionName, intAnsCount, intNotAnsCount, intNotVisitedCount,
						intMarkForReviewCount, intAnsAndMarkCount);

				if (!result) {
					return result;
				}

				result = verifyQuestionPanel(strSectionName, intAnsCount, intNotAnsCount, intNotVisitedCount,
						intMarkForReviewCount, intAnsAndMarkCount);
				if (!result) {
					return result;
				}
			}
			// Click on submit button

			cfObj.commonClick(testAttempPageObj.getListTestSeriesActionButton().get(3));
			driver.switchTo().alert().accept();

			// wait for view result pop up

			result = verifyViewResultPopUp(driver, viewResult);
			if (!result) {
				return result;
			}

		} catch (Exception e) {
			result = false;
			testAttemptPageMsgList.add("attmepTest_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean verifyQuestionStatus(String strSectionName, int strAns, int strNotAns, int strNotVisited,
			int strMarkForReview, int strAnsAndMarkForReview) {
		boolean result = true;
		int actAns = 0, actNotAns = 0, actNotVisited = 0, actMarkForReview = 0, actAnsAndMarkForReview = 0;
		try {

			System.out.println(strSectionName + "_" + strAns + "," + strNotAns + "," + strNotVisited + ","
					+ strMarkForReview + "," + strAnsAndMarkForReview);

			actAns = Integer.parseInt(testAttempPageObj.getListQuestionStatusAnswered().get(0).getText().toString());
			actNotAns = Integer
					.parseInt(testAttempPageObj.getListQuestionStatusNotAnswered().get(0).getText().toString());
			actNotVisited = Integer
					.parseInt(testAttempPageObj.getListQuestionStatusNotVisited().get(0).getText().toString());
			actMarkForReview = Integer
					.parseInt(testAttempPageObj.getListQuestionStatusMarkForReview().get(0).getText().toString());
			actAnsAndMarkForReview = Integer.parseInt(
					testAttempPageObj.getListQuestionStatusAnsweredAndMarkForReview().get(0).getText().toString());
			System.out.println(actMarkForReview + actAnsAndMarkForReview);
			if (strAns != actAns) {
				testAttemptPageMsgList.add("Mismtach in Answered question count for Section: " + strSectionName
						+ "actaul Count: " + actAns + " expected count: " + strAns);
				result = false;
			}
			if (strNotAns != actNotAns) {
				testAttemptPageMsgList.add("Mismtach in Not Answered question count for Section: " + strSectionName
						+ "actaul Count: " + actNotAns + " expected count: " + strNotAns);
				result = false;
			}
			if (strNotVisited != actNotVisited) {
				testAttemptPageMsgList.add("Mismtach in not visited count for Section: " + strSectionName
						+ "actaul Count: " + actNotVisited + " expected count: " + strNotVisited);
				result = false;
			}

		} catch (Exception e) {
			result = false;
			testAttemptPageMsgList.add("verifyQuestionStatus_Exception: " + e.getMessage());

		}
		return result;
	}

	public void attempQuestion(int choice) {
		try {
			if (choice == 1) {

				intAnsCount = intAnsCount + 1;
				cfObj.commonClick(testAttempPageObj.getListAnswerOption().get(0));
				// click on save and next
				cfObj.commonClick(testAttempPageObj.getListTestSeriesActionButton().get(2));

			} else if (choice == 2) {

				// click on save and next
				cfObj.commonClick(testAttempPageObj.getListTestSeriesActionButton().get(2));
				intNotAnsCount = intNotAnsCount + 1;

			} else if (choice == 3) {
				intMarkForReviewCount = intMarkForReviewCount + 1;

				// click on mark for review
				cfObj.commonClick(testAttempPageObj.getListTestSeriesActionButton().get(1));

			} else if (choice == 4) {
				intAnsAndMarkCount = intAnsAndMarkCount + 1;

				cfObj.commonClick(testAttempPageObj.getListAnswerOption().get(0));
				// click on mark for review
				cfObj.commonClick(testAttempPageObj.getListTestSeriesActionButton().get(1));

			} else if (choice == 5) {
				intNotAnsCount = intNotAnsCount + 1;

				// Clear Response
				cfObj.commonClick(testAttempPageObj.getListAnswerOption().get(0));
				// click on clear response
				cfObj.commonClick(testAttempPageObj.getListTestSeriesActionButton().get(0));
				// click on save and next
				cfObj.commonClick(testAttempPageObj.getListTestSeriesActionButton().get(2));

			}

		} catch (Exception e) {
			testAttemptPageMsgList.add("attempQuestion_Exception: " + e.getMessage());
		}
	}

	public boolean verifyQuestionPanel(String strSectionName, int strAns, int strNotAns, int strNotVisited,
			int strMarkForReview, int strAnsAndMarkForReview) {
		boolean result = true;
		int actAns = 0, actNotAns = 0, actNotVisited = 0, actMarkForReview = 0, actAnsAndMarkForReview = 0;
		try {

			System.out.println(strSectionName + "_" + strAns + "," + strNotAns + "," + strNotVisited + ","
					+ strMarkForReview + "," + strAnsAndMarkForReview);

			actAns = testAttempPageObj.getListQuestionStatusAnsweredStatus().size();
			actNotAns = testAttempPageObj.getListQuestionStatusNotAnsweredStatus().size();
			actNotVisited = testAttempPageObj.getListQuestionStatusNotVisitedStatus().size();
			actMarkForReview = testAttempPageObj.getListQuestionStatusMarkForReviewStatus().size();
			actAnsAndMarkForReview = testAttempPageObj.getListQuestionStatusAnsweredAndMarkForReviewStatus().size();
			System.out.println(actMarkForReview + actAnsAndMarkForReview);

			if (strAns != actAns) {
				testAttemptPageMsgList.add("Mismtach in Answered question count for Section: " + strSectionName
						+ "actaul Count: " + actAns + " expected count: " + strAns);
				result = false;
			}
			if (strNotAns != actNotAns) {
				testAttemptPageMsgList.add("Mismtach in Not Answered question count for Section: " + strSectionName
						+ "actaul Count: " + actNotAns + " expected count: " + strNotAns);
				result = false;
			}
			if (strNotVisited != actNotVisited) {
				testAttemptPageMsgList.add("Mismtach in not visited count for Section: " + strSectionName
						+ "actaul Count: " + actNotVisited + " expected count: " + strNotVisited);
				result = false;
			}

		} catch (Exception e) {
			result = false;
			testAttemptPageMsgList.add("verifyQuestionPanel_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyViewResultPopUp(WebDriver driver, List<String> viewResultData) {
		boolean result = true;

		List<String> strActualViewResultData;
		String strSectorName;
		int intAns, intNotVisited, intSkipped, intTotal;
		try {

			strActualViewResultData = new ArrayList<String>();

			for (WebElement element : testAttempPageObj.getListViewResultData()) {

				List<WebElement> resultData = element.findElements(By.tagName("td"));

				strSectorName = resultData.get(0).getText().toString();
				intAns = Integer.parseInt(resultData.get(1).getText().toString());
				intSkipped = Integer.parseInt(resultData.get(2).getText().toString());
				intNotVisited = Integer.parseInt(resultData.get(3).getText().toString());
				intTotal = Integer.parseInt(resultData.get(4).getText().toString());

				strActualViewResultData
						.add(strSectorName + "_" + intAns + "_" + intSkipped + "_" + intNotVisited + "_" + intTotal);

			}

			for (String resultData : viewResultData) {

				System.out.println("resultDataExpected :" + resultData);

				if (!strActualViewResultData.contains(resultData)) {
					result = false;
				}
			}

			if (!result) {
				testAttemptPageMsgList.add("Expected: " + viewResultData + " Actual: " + strActualViewResultData);
			}

		} catch (Exception e) {
			result = false;
			testAttemptPageMsgList.add("verifyViewResultPopUp_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyResumeTest(WebDriver driver) {
		boolean result = true;
		String strCourseSlug;
		CourseApiUtil courseApiUtilObj;
		CourseView courseViewObj;
		CoursePageUtil coursePageUtilObj;
		try {
			homePageUtilObj = new HomePageUtil(driver);
			result = homePageUtilObj.verifySignUp(driver);

			if (!result) {
				testAttemptPageMsgList.addAll(homePageUtilObj.homePageMsgList);
				return result;
			}

			result = homePageUtilObj.clickOnTestSeriesOnHomePage(driver);
			if (!result) {
				testAttemptPageMsgList.addAll(homePageUtilObj.homePageMsgList);
				return result;
			}


			strCourseSlug = driver.getCurrentUrl().split("course-detail/")[1];
			System.out.println("strCourseSlug:" + strCourseSlug);
			courseApiUtilObj = new CourseApiUtil();
			courseViewObj = courseApiUtilObj.getCourseViewData(strCourseSlug);
			System.out.println(courseViewObj.getData().getPriceInfo());

			coursePageUtilObj = new CoursePageUtil(driver);

			result = coursePageUtilObj.selectExamPrefrences(driver);
			if (!result) {
				testAttemptPageMsgList.addAll(coursePageUtilObj.coursePageMsgList);
				return result;
			}
			driver.navigate()
					.to(configObject.getBaseUrl() + "test-series/" + configObject.getCourseTestId() + "/UPSC%20Quiz");

			Thread.sleep(10000);
			testInstructionUtilObj = new TestInstrcutionUtil(driver);
			result = testInstructionUtilObj.clickOnStartTest(driver);
			if (!result) {
				testAttemptPageMsgList.addAll(testInstructionUtilObj.testInstrcutionPageMsgList);
				return result;
			}
			result = ResumeTest(driver);

		} catch (Exception e) {
			result = false;
			testAttemptPageMsgList.add("verifyResumeTest_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean ResumeTest(WebDriver driver) {
		boolean result = true;
		int noOfSection;
		String strSectionName;
		String strResumeState, strActualResumeState;
		int intRandomSection;
		int noOfQuestion;

		try {

			noOfSection = testAttempPageObj.getListLabelTestSection().size();

			// check first section should be selected

			if (!testAttempPageObj.getListLabelTestSection().get(0).getAttribute("class").toString()
					.equalsIgnoreCase("blue")) {
				testAttemptPageMsgList.add("First section is not selected");
				return false;
			}
			noOfQuestion = testAttempPageObj.getListNoOfQuestionPerSection().size();
			strSectionName = testAttempPageObj.getListLabelTestSection().get(0).getText().toString();
			result = verifyQuestionStatus(strSectionName, 0, 0, noOfQuestion, 0, 0);
			if (!result) {
				return result;
			}
			intRandomSection = Common_Function.RandomNumber(1, noOfSection);
			for (int i = 0; i < intRandomSection; i++) {
				strSectionName = testAttempPageObj.getListLabelTestSection().get(i).getText().toString();
				cfObj.commonClick(testAttempPageObj.getListLabelTestSection().get(i));
				noOfQuestion = testAttempPageObj.getListNoOfQuestionPerSection().size();
				intAnsCount = 0;
				intNotAnsCount = 0;
				intNotVisitedCount = 0;
				intMarkForReviewCount = 0;
				intAnsAndMarkCount = 0;
				if (!testAttempPageObj.getListLabelTestSection().get(i).getAttribute("class").toString()
						.equalsIgnoreCase("blue")) {
					testAttemptPageMsgList.add(i + " Section is not selected");
					return false;
				}

				for (int j = 1; j <= noOfQuestion - 1; j++) {
					int index = Common_Function.RandomNumber(1, 4);
					attempQuestion(index);
					intNotVisitedCount = noOfQuestion
							- (intAnsCount + intNotAnsCount + intMarkForReviewCount + intAnsAndMarkCount);
				}

			}

			strResumeState = testAttempPageObj.getListLabelTestSection().get(intRandomSection - 1).getText().toString()
					+ "_" + intRandomSection + "_" + intAnsCount + "_" + intNotAnsCount + "_" + intNotVisitedCount + "_"
					+ (intMarkForReviewCount + intAnsAndMarkCount);

			System.out.println("strResumeState: " + strResumeState);

			driver.navigate()
					.to(configObject.getBaseUrl() + "test-series/" + configObject.getCourseTestId() + "/UPSC%20Quiz");

			Thread.sleep(10000);
			testInstructionUtilObj = new TestInstrcutionUtil(driver);
			result = testInstructionUtilObj.clickOnStartTest(driver);
			if (!result) {
				testAttemptPageMsgList.addAll(testInstructionUtilObj.testInstrcutionPageMsgList);
				return result;
			}

			strActualResumeState = testAttempPageObj.getListLabelTestSection().get(intRandomSection - 1).getText()
					.toString() + "_" + testAttempPageObj.getListQuestionStatusAnswered().get(0).getText().toString()
					+ "_" + testAttempPageObj.getListQuestionStatusNotAnswered().get(0).getText().toString() + "_"
					+ testAttempPageObj.getListQuestionStatusNotAnswered().get(0).getText().toString() + "_"
					+ testAttempPageObj.getListQuestionStatusNotVisited().get(0).getText().toString() + "_"
					+ Integer.parseInt(
							testAttempPageObj.getListQuestionStatusMarkForReview().get(0).getText().toString())
					+ +Integer.parseInt(testAttempPageObj.getListQuestionStatusAnsweredAndMarkForReview().get(0)
							.getText().toString());

			if (!strActualResumeState.equalsIgnoreCase(strResumeState)) {
				testAttemptPageMsgList.add("Wrong state maintain when user resume the test actaul state: "
						+ strActualResumeState + " expected state: " + strResumeState);
				return false;
			}

		} catch (Exception e) {
			result = false;
			testAttemptPageMsgList.add("ResumeTest_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean verifyViewResult(WebDriver driver) {
		boolean result = true;
		try {

		} catch (Exception e) {
			result = false;
			testAttemptPageMsgList.add("verifyViewResult_Exception: " + e.getMessage());
		}

		return result;
	}

}
