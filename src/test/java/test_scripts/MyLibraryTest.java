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
import applicationUtil.CoursePageUtil;
import applicationUtil.LibraryPageUtil;
import pojo.TestData;
import util.ConfigFileReader;

public class MyLibraryTest extends BaseTest {
	
	LibraryPageUtil MyLibraryUtilUtilObj;
	CoursePageUtil coursePageUtilObj;

	@Test(dataProvider = "getData", enabled = true)
	public void verifyMyLibrary(TestData testData) {
		boolean result = true;
		MyLibraryUtilUtilObj = new LibraryPageUtil(driver);
		result = MyLibraryUtilUtilObj.VerifyExtRen_Courses(driver, testData);
		Assert.assertEquals(result, true, MyLibraryUtilUtilObj.libraryPageMsgList.toString());
	}
	
	
	@SuppressWarnings("serial")
	@DataProvider
	public Object[][] getData() throws Exception {
		JsonElement jsonData = new JsonParser().parse(new FileReader("src/main/resources/library.json"));
		JsonElement dataSet = jsonData.getAsJsonObject().get(ConfigFileReader.strEnv);
		List<TestData> testData = new Gson().fromJson(dataSet, new TypeToken<List<TestData>>() {
		}.getType());
		Object[][] returnValue = new Object[testData.size()][1];
		int index = 0;
		for (Object[] each : returnValue) {
			each[0] = testData.get(index++);
		}
		return returnValue;
	}
	
	@Test

	public void LibraryCourse() {
		boolean result = true;
		MyLibraryUtilUtilObj = new LibraryPageUtil(driver);
		result = MyLibraryUtilUtilObj.verifyMyLibrary(driver);
		Assert.assertEquals(result, true, MyLibraryUtilUtilObj.libraryPageMsgList.toString());
	}

}
