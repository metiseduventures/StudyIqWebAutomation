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

import applicationUtil.LibraryPageUtil;
import pojo.TestData;
import util.ConfigFileReader;

public class LibraryTest extends BaseTest {
	LibraryPageUtil libraryPageUtil;
	ConfigFileReader configFileReader = new ConfigFileReader();
	
	@Test(dataProvider = "getData", enabled = true)
	public void verifyLibraryPage(TestData testData) {
		boolean result = true;
		
		LoginTest test = new LoginTest();
		test.verifyLogin(testData);
		
		CourseTest test2 = new CourseTest();
		test2.verifyCourseBuy(testData);
		
		
		libraryPageUtil = new LibraryPageUtil(driver);
		
		result = libraryPageUtil.verifyCourse(testData);
		Assert.assertEquals(result, true,libraryPageUtil.libraryPageMsgList.toString());
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
