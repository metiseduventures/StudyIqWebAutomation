package test_scripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.util.List;

import org.testng.*;

import applicationUtil.CoursePageUtil;
import pojo.TestData;
import util.ConfigFileReader;

public class CourseTest extends BaseTest {
	CoursePageUtil coursePageUtilObj;
	ConfigFileReader cfr = new ConfigFileReader();

	@Test(dataProvider = "getData", enabled = true)
	public void verifyCourseBuy(TestData testData) {
		coursePageUtilObj = new CoursePageUtil(driver);
		boolean result = true;

		result = coursePageUtilObj.verifybuy_login(driver, testData, ConfigFileReader.strUserMobileNumber);
		Assert.assertEquals(result, true, coursePageUtilObj.coursePageMsgList.toString());
	}
	
	@DataProvider
	public Object[][] getData() throws Exception {
		JsonElement jsonData = new JsonParser().parse(new FileReader("src/main/resources/TestData.json"));
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
