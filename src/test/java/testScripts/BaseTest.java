package testScripts;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import util.Common_Function;

public class BaseTest {
	
	Common_Function cfObj = new Common_Function();
	public static WebDriver driver;
	
	@BeforeTest
	public void setUp() {
		try {
			driver = cfObj.commonStartAndOpenURLBrowser();
			System.out.println("SessionId: " + driver);

		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	@AfterTest
	public void tearDown() {
		try {

			if (driver != null) {
				driver.quit();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
