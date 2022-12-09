package test_scripts;
import org.testng.Assert;

import org.testng.annotations.Test;

import applicationUtil.LibraryPageUtil;

public class MyLibraryTest extends BaseTest {

	LibraryPageUtil MyLibraryUtilUtilObj;

	@Test
	public void verifyMyLibrary() {
		boolean result = true;
		MyLibraryUtilUtilObj = new LibraryPageUtil(driver);
		result = MyLibraryUtilUtilObj.verifyMyLibrary(driver);
		Assert.assertEquals(result, true, MyLibraryUtilUtilObj.libraryPageMsgList.toString());
	}

	@Test
	public void verifyMyLibraryFromDropDown() {
		boolean result = true;
		MyLibraryUtilUtilObj = new LibraryPageUtil(driver);
		result = MyLibraryUtilUtilObj.verifyLibraryItemFromHomeDropDown(driver);
		Assert.assertEquals(result, true, MyLibraryUtilUtilObj.libraryPageMsgList.toString());
	}

	@Test
	public void verifyCourseContentFromLibrary() {
		boolean result = true;
		MyLibraryUtilUtilObj = new LibraryPageUtil(driver);
		result = MyLibraryUtilUtilObj.verifyCourseContentFromLibrary(driver);
		Assert.assertEquals(result, true, MyLibraryUtilUtilObj.libraryPageMsgList.toString());
	}
	
	@Test
	public void VideoConsumption() {
		boolean result = true;
		MyLibraryUtilUtilObj = new LibraryPageUtil(driver);
		result = MyLibraryUtilUtilObj.verifyVideoConsumptionInLibrary(driver);
		Assert.assertEquals(result, true, MyLibraryUtilUtilObj.libraryPageMsgList.toString());
	}

}
