package test_scripts;



import org.testng.Assert;
import org.testng.annotations.Test;


import applicationUtil.HomePageUtil;
import util.ConfigFileReader;

public class LoginTest extends BaseTest {

	HomePageUtil homePageUtilObj;
	
	@Test
	public void VerifyFaildLogin_Case() {
		boolean result=true;
		homePageUtilObj = new HomePageUtil(driver);
		result=homePageUtilObj.verifyLoginFailCase(driver);
		Assert.assertEquals(result, true,homePageUtilObj.homePageMsgList.toString());
	}
  
	@Test
	public void verifyLogin() {

		boolean result = true;
		homePageUtilObj = new HomePageUtil(driver);
		result = homePageUtilObj.verifyLogin(driver, ConfigFileReader.strUserMobileNumber);
		Assert.assertEquals(result, true, homePageUtilObj.homePageMsgList.toString());

	}
	
	@Test
	public void VerifyFaildSignUp_Case() {
		boolean result=true;
		homePageUtilObj = new HomePageUtil(driver);
		result=homePageUtilObj.verifySignUpFailCase(driver);
		Assert.assertEquals(result, true,homePageUtilObj.homePageMsgList.toString());
	}
	
	@Test
	public void verifySignUp() {

		boolean result = true;
		homePageUtilObj = new HomePageUtil(driver);
		result = homePageUtilObj.verifySignUp(driver);
		Assert.assertEquals(result, true, homePageUtilObj.homePageMsgList.toString());

	}
	

}
