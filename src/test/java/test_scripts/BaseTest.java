package test_scripts;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import apiUtil.LoginUtil;
import pojo.login.Login;

import org.openqa.selenium.WebDriver;
import util.Common_Function;
import util.ConfigFileReader;

public class BaseTest {

	Common_Function cfObj = new Common_Function();
	public static WebDriver driver;
	public LoginUtil loginApiUtilObj;
	public Login loginObj;
	@BeforeMethod
	public void setUp() {
		try {
			driver = cfObj.commonStartAndOpenURLBrowser();
			System.out.println("SessionId: " + driver);

		} catch (Exception e)

		{
			e.printStackTrace();
		}

	}
	@AfterMethod
	public void tearDown() {
		try {

			if (driver != null) {
				driver.quit();

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	@BeforeClass
	public void loginViaApi()
	{
		loginApiUtilObj = new LoginUtil();
		loginObj = loginApiUtilObj.doLoginWeb(ConfigFileReader.strUserMobileNumber);
	}

}
