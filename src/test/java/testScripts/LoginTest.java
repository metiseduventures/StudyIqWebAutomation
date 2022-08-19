package testScripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.testng.Assert;
import applicationUtil.HomePageUtil;
import pojo.TestData;

import java.io.FileReader;
import java.util.List;

import util.ConfigFileReader;

public class LoginTest extends BaseTest {

	HomePageUtil homePageUtilObj;

	ConfigFileReader cfr = new ConfigFileReader();

	@Test(dataProvider = "getData", enabled = true)
	public void verifyLogin(TestData testData) {

		boolean result = true;
		homePageUtilObj = new HomePageUtil(driver);

		result = homePageUtilObj.verifyLogin(driver, testData);
		Assert.assertEquals(result, true, homePageUtilObj.homePageMsgList.toString());

		result = homePageUtilObj.verifySearch(driver, testData);
		Assert.assertEquals(result, true, homePageUtilObj.homePageMsgList.toString());
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
