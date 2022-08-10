package test_scripts;

import org.testng.Assert;
import org.testng.annotations.Test;
import applicationUtil.TestSeriesUtil;

public class TestSeriesTest extends BaseTest {

	TestSeriesUtil testSeriesUtilObj;

	@Test
	public void verifyTestAttempFlow() {

		boolean result = true;
		testSeriesUtilObj = new TestSeriesUtil(driver);
		result = testSeriesUtilObj.verifyTestExecutionFlow(driver);
		Assert.assertEquals(result, true, testSeriesUtilObj.testAttemptPageMsgList.toString());

	}
	
	
	@Test
	public void verifyResumeTestFlow() {

		boolean result = true;
		testSeriesUtilObj = new TestSeriesUtil(driver);
		result = testSeriesUtilObj.verifyResumeTest(driver);
		Assert.assertEquals(result, true, testSeriesUtilObj.testAttemptPageMsgList.toString());

	}

}
