package test_scripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import applicationUtil.HomePageUtil;
import util.ConfigFileReader;

public class HomePageTest extends BaseTest {
	HomePageUtil homepageUtilObj;
	@Test
	public void MyProfilePageFlow() throws InterruptedException {

		boolean result= true;
		homepageUtilObj = new HomePageUtil(driver);
		result = homepageUtilObj.validateHomePage(driver,ConfigFileReader.strUserMobileNumber);
		Assert.assertEquals(result, true, homepageUtilObj.homePageMsgList.toString());
	}

}
