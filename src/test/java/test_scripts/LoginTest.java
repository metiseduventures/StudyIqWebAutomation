package test_scripts;

import org.testng.Assert;
import org.testng.annotations.Test;
import applicationUtil.HomePageUtil;
import util.ConfigFileReader;

public class LoginTest extends BaseTest {

	HomePageUtil homePageUtilObj;

	@Test
	public void verifyLogin() throws InterruptedException {

		boolean result = true;
		homePageUtilObj = new HomePageUtil(driver);
		result = homePageUtilObj.verifyLogin(driver, ConfigFileReader.strUserMobileNumber);
		Thread.sleep(3000);
		Assert.assertEquals(result, true, homePageUtilObj.homePageMsgList.toString());
		
		result = homePageUtilObj.verifyMyProfile(driver);
		Thread.sleep(3000);
		Assert.assertEquals(result, true, homePageUtilObj.homePageMsgList.toString());
		
		

	}

}
