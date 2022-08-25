package test_scripts;

import org.testng.Assert;
import org.testng.annotations.Test;
import applicationUtil.MyProfileUtil;


public class MyProfileTest extends BaseTest {

	MyProfileUtil MyProfileUtilObj;
		
	@Test
	public void MyProfilePageFlow() throws InterruptedException {

		boolean result = true;
		Thread.sleep(3000);
		MyProfileUtilObj = new MyProfileUtil(driver);
		result = MyProfileUtilObj.verifyMyProfile_Page(driver);
		Assert.assertEquals(result, true, MyProfileUtilObj.MyProfileORobjMsgList.toString());
	}
}
		
	
	
