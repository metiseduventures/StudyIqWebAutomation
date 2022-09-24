package pageObject;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LibraryPage_OR {
		
	@FindBy(xpath = "//span[@class='course_title']")
	List<WebElement> TitlesInLibrary;
	
	public List<WebElement> libraryTitles(){
		return TitlesInLibrary;
	}
	
	@FindBy(xpath = "//button[@id='dropdown-basic-button']")
	WebElement accountBtn;
	
	public WebElement accountClick() {
		return accountBtn;
	}
	
	@FindBy(xpath = "//a[@class='dropdown-item']")
	List<WebElement> myAccountItem;
	
	public List<WebElement> accountItemCheck(){
		return myAccountItem;
	}
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
	
	@FindBy(xpath="(//div[@class='library_list_item list-group-item'])[3]")
	private WebElement Books;
	
	public WebElement getBooks()
	{
		return Books;
	}
	
	@FindBy(xpath="(//div[@class='library_list_item list-group-item'])[4]")
	private WebElement TestSeries;
	
	public WebElement getTestSeries()
	{
		return TestSeries;
	}
	
	@FindBy(xpath="(//*[contains(text(),'My Library')])[1]")
	private WebElement MyLibrary_Button;
	
	
	public  WebElement getMyLibrary_Button() {
		return MyLibrary_Button;
	}
	
	@FindBy(xpath="//button[@class='btn btn-primary']")
	private WebElement Explore_Courses;
	
	public WebElement getExplore_Courses()
	{
		return Explore_Courses;
	}
	
	@FindBy(xpath="(//*[contains(text(),'Smart Course')])[2]")
	private WebElement DotSmart;
	
	public WebElement getDotSmart()
	{
		return DotSmart;
	}
	
	@FindBy(xpath="(//*[contains(text(),'Micro Course')])")
	private WebElement DotMicro;
	
	public WebElement getDotMicro()
	{
		return DotMicro;
	}
	
	@FindBy(xpath="(//div[@class='dropdown-item dropdown-sublist'])[3]")
	private WebElement DotBook;
	
	public WebElement getDotBook()
	{
		return DotBook;
	}
	
	@FindBy(xpath="(//div[@class='dropdown-item dropdown-sublist'])[4]")
	private WebElement DotTestSeries;
	
	public WebElement getDotTestSeries()
	{
		return DotTestSeries;
	}
	@FindBy(xpath="//div[@class='li_collapse_wrapper collapse show']//div[@class='li_card_body card-body']//div[1]//div[2]//div[1]//button[1]")
	private WebElement Start_MyCourse;
	
	public WebElement getStart_MyCourse()
	{
		return Start_MyCourse;
	}
	
	
	@FindBy(xpath="//button[@data-id='CLOSE']")
	private WebElement Close;
	
	public WebElement getClose()
	{
		return Close;
	}

	@FindBy(xpath="//button[@class='close']")
	private WebElement Close_ExtendCourse;
	
	public WebElement getClose_ExtendCourse()
	{
		return Close_ExtendCourse;
	}
	
	@FindBy(xpath="//div[@class='ci_content_wrapper']/div/div")
	private List<WebElement> Topics_Of_Syllabus;
	
	public List<WebElement> getTopics_Of_Syllabus()
	{
		return Topics_Of_Syllabus;
	}

	@FindBy(xpath="//div[@class='collapse show']/div/div/div")
	private List<WebElement> Containt_InsideTopics;
	
	public List<WebElement> getContaint_InsideTopics()
	{
		return Containt_InsideTopics;
	}
	
	@FindBy(xpath="(//div[@class='collapse show'])[2]/div/div/span[2]")
	private List<WebElement> Video_OfCourse;
	
	public List<WebElement> getVideo_OfCourse()
	{
		return Video_OfCourse;
	}
	
	@FindBy(xpath="//div[@class='ci_content_wrapper']/div/div[1]")
	private WebElement Topics_Single;
	
	public WebElement getTopics_Single()
	{
		return Topics_Single;
	}
	
	@FindBy(xpath="//div[@class='collapse show']/div/div/div[1]")
	private WebElement Containt_Inside_SingleTopics;
	
	public WebElement getContaint_Inside_SingleTopics()
	{
		return Containt_Inside_SingleTopics;
	}
	
	@FindBy(xpath="((//div[@class='collapse show'])[2]/div/div/span[2])[1]")
	private WebElement contant_OfSingle_Course;
	
	public WebElement getcontant_OfSingle_Course()
	{
		return contant_OfSingle_Course;
	}

}

