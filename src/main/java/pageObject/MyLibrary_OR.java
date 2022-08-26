package pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyLibrary_OR {
	
	@FindBy(xpath="(//div[@class='library_list_item list-group-item'])[1]")
    private WebElement Smart_Courses;
	
	public WebElement getSmart_Courses() {
		return Smart_Courses;
	} 

	@FindBy(xpath="(//div[@class='library_list_item list-group-item'])[2]")
	private WebElement Micro_Courses;
	
	public WebElement getMicro_Courses()
	{
		return Micro_Courses;
	}
}
