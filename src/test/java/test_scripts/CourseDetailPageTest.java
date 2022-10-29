package test_scripts;

import java.io.FileReader;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import applicationUtil.CourseDetailsPageUtil;
import applicationUtil.HomePageUtil;
import pojo.TestData;


public class CourseDetailPageTest extends BaseTest {

	CourseDetailsPageUtil CourseDetailsUtilObj;
	HomePageUtil homeUtilObj;
	
	@Test(dataProvider = "getData", enabled = true)
	public void CourseDetailsPageFlow(TestData testData) {

		boolean result = true;
		CourseDetailsUtilObj = new CourseDetailsPageUtil(driver);
		result = CourseDetailsUtilObj.verifyCourseDetailsPage(driver,testData);
		Assert.assertEquals(result, true, CourseDetailsUtilObj.CourseDetailsPageMsgList.toString());
		
	}
	
	
	@Test
	public void verifyCrossSellCourse() {

		boolean result = true;
		CourseDetailsUtilObj = new CourseDetailsPageUtil(driver);
		result = CourseDetailsUtilObj.verifyCourseSell(driver);
		Assert.assertEquals(result, true, CourseDetailsUtilObj.CourseDetailsPageMsgList.toString());
		
	}
	
	@SuppressWarnings("serial")
	@DataProvider
	public Object[][] getData() throws Exception {
		JsonElement jsonData = new JsonParser().parse(new FileReader("src/main/resources/CourseData.json"));
		JsonElement dataSet = jsonData.getAsJsonObject().get("dataSet");
		List<TestData> testData = new Gson().fromJson(dataSet, new TypeToken<List<TestData>>() {
		}.getType());
		Object[][] returnValue = new Object[testData.size()][1];
		int index = 0;
		for (Object[] each : returnValue) {
			each[0] = testData.get(index++);
		}
		return returnValue;
	}
}
