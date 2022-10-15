package test_scripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import applicationUtil.HomePageUtil;
import util.ConfigFileReader;

public class HomePageTest extends BaseTest {
	HomePageUtil homepageUtilObj;

	@Test
	public void verifyHomePageMenuList() throws InterruptedException {

		boolean result = true;
		homepageUtilObj = new HomePageUtil(driver);
		result = homepageUtilObj.verifyHomePageMenuList(driver);
		Assert.assertEquals(result, true, homepageUtilObj.homePageMsgList.toString());
	}

	@Test
	public void verifyTestimonial() throws InterruptedException {

		boolean result = true;
		homepageUtilObj = new HomePageUtil(driver);
		result = homepageUtilObj.VerifyTestimonial(driver);
		Assert.assertEquals(result, true, homepageUtilObj.homePageMsgList.toString());
	}

	@Test
	public void verifySocialMediaIcon() throws InterruptedException {

		boolean result = true;
		homepageUtilObj = new HomePageUtil(driver);
		result = homepageUtilObj.VerifySocialMediaIcon(driver);
		Assert.assertEquals(result, true, homepageUtilObj.homePageMsgList.toString());
	}

	@Test
	public void validateHomePage() throws InterruptedException {

		boolean result = true;
		homepageUtilObj = new HomePageUtil(driver);
		result = homepageUtilObj.validateHomePage(driver, ConfigFileReader.strUserMobileNumber);
		Assert.assertEquals(result, true, homepageUtilObj.homePageMsgList.toString());
	}

}
