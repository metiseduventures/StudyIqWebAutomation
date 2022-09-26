package test_scripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import applicationUtil.CourseDetailsPageUtil;
import applicationUtil.HomePageUtil;


public class CourseDetailPageTest extends BaseTest {

	CourseDetailsPageUtil CourseDetailsUtilObj;
	HomePageUtil homeUtilObj;
	
	@Test
	public void CourseDetailsPageFlow() {

		boolean result = true;
		CourseDetailsUtilObj = new CourseDetailsPageUtil(driver);
		result = CourseDetailsUtilObj.verifyCourseDetailsPage(driver);
		Assert.assertEquals(result, true, CourseDetailsUtilObj.CourseDetailsPageMsgList.toString());
		
	}
}
