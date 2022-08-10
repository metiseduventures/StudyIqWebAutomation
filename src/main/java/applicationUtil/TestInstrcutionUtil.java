package applicationUtil;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageObject.TestInstrcutionPage_OR;
import util.Common_Function;

public class TestInstrcutionUtil {

	TestInstrcutionPage_OR testInstPageObj;
	public Common_Function cfObj = new Common_Function();

	public ArrayList<String> testInstrcutionPageMsgList = new ArrayList<String>();

	public TestInstrcutionUtil(WebDriver driver) {

		testInstPageObj = new TestInstrcutionPage_OR();
		PageFactory.initElements(driver, testInstPageObj);

	}

	public boolean clickOnStartTest(WebDriver driver) {
		boolean result = true;
		try {

			if (testInstPageObj.getListBtnStartTest().get(0).isEnabled()) {
				testInstrcutionPageMsgList.add("Start button should be disabled if check box is not checked");
				return false;
			}

			// check instruction check box
			cfObj.commonClick(testInstPageObj.getListCheckBoxInstrcution().get(0));
			if (!testInstPageObj.getListBtnStartTest().get(0).isEnabled()) {
				testInstrcutionPageMsgList.add("Start button should be enable if check box is checked");
				return false;
			}

			cfObj.commonClick(testInstPageObj.getListBtnStartTest().get(0));

			// wait for test page to opened
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".test-info", "css", 20);
			if (!result) {
				testInstrcutionPageMsgList.add("Test page not opened when click on start test");
				return result;
			}

		} catch (Exception e) {
			result = false;
			testInstrcutionPageMsgList.add("clickOnStartTest_Exception: " + e.getMessage());
		}

		return result;
	}

}
