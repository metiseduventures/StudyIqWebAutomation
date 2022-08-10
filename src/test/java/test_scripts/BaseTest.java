package test_scripts;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import util.Common_Function;

public class BaseTest {

	Common_Function cfObj = new Common_Function();
	public static WebDriver driver;

	@BeforeClass

	public void setUp() {
		try {
			driver = cfObj.commonStartAndOpenURLBrowser();
			System.out.println("SessionId: " + driver);

		} catch (Exception e)

		{
			e.printStackTrace();
		}

	}

	@AfterClass
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

}
