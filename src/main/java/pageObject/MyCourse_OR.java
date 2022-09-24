package pageObject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyCourse_OR {

	
	@FindBy(xpath="(//button[@class='start_cb btn btn-primary'])[2]")
	private WebElement Start_MyCourse;
	
	public WebElement getStart_MyCourse()
	{
		return Start_MyCourse;
	}
	
	@FindBy(xpath="(//button[@class='start_cb btn btn-primary'])[17]")
	private WebElement Start_MicroMyCourse;
	
	public WebElement getStart_MicroMyCourse()
	{
		return Start_MicroMyCourse;
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
	
	@FindBy(xpath="(//div[@class='collapse show'])/div/div/span[2]")
	private WebElement Video_OfSingle_Course;
	
	public WebElement getVideo_OfSingle_Course()
	{
		return Video_OfSingle_Course;
	}
	
	@FindBy(xpath="//span[@class='navbar-brand']/a")
	private WebElement ClickOn_HomePage;
	
	public WebElement getClickOn_HomePage()
	{
		return ClickOn_HomePage;
	}
	@FindBy(xpath="//div[@class='html5-video-player ytp-hide-info-bar iv-module-loaded paused-mode']")
	private WebElement SmartCourseVideo;
	
	public WebElement getSmartCourseVideo()
	{
		return SmartCourseVideo;
	}
	
}
