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

import applicationUtil.HomePageUtil;
import applicationUtil.MyProfileUtil;
import pojo.TestData;
import util.ConfigFileReader;

public class MyProfileTest extends BaseTest {

	MyProfileUtil MyProfileUtilObj;
	
	
	@Test(dataProvider = "getData",enabled = true)
	public void MyProfilePageFlow(TestData testData) throws InterruptedException {

		boolean result = true;
		
		Thread.sleep(3000);
		MyProfileUtilObj = new MyProfileUtil(driver);
		result = MyProfileUtilObj.verifyMyProfile_Page(driver,testData);
		Assert.assertEquals(result, true, MyProfileUtilObj.MyProfileORobjMsgList.toString());
	}
		
		@DataProvider
		public Object[][] getData() throws Exception {
			JsonElement jsonData = new JsonParser().parse(new FileReader("src/main/resources/TestData.json"));
			JsonElement dataSet = jsonData.getAsJsonObject().get("dataSet");
			@SuppressWarnings("serial")
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
	
	
