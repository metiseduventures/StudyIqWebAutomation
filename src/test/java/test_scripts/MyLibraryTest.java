package test_scripts;

import org.testng.Assert;
import org.testng.annotations.Test;
import applicationUtil.MyLibraryUtil;


public class MyLibraryTest extends BaseTest {

	MyLibraryUtil MyLibraryUtilObj;
	
	@Test
	public void MyLibraryPageFlow() throws InterruptedException {

		boolean result = true;
		MyLibraryUtilObj = new MyLibraryUtil(driver);
		result = MyLibraryUtilObj.verifyMyLibrary_Page(driver);
		Assert.assertEquals(result, true, MyLibraryUtilObj.MyLibraryMsgList.toString());
	}
}
