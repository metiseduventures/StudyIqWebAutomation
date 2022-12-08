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
	
	@FindBy(xpath="//*[@id='root']/div/div/div[3]/div/div[2]/div[2]/div/div/div[1]/div")
	private List<WebElement> CourseTopic_Title01;
	
	public List<WebElement> getCourseTopic_Title01()
	{
		return CourseTopic_Title01;
	}
	
	@FindBy(css="div[class='collapse show'] div[class='video_name']")
	private List<WebElement> TitleJustInsideVideo;
	
	public List<WebElement> getTitleJustInsideVideo()
	{
		return TitleJustInsideVideo;
	}
	
	@FindBy(css="div[class='collapse show'] div[class='subchapter_header list-group-item'] div")
	private List<WebElement> TitleJustInsideTitle;
	
	public List<WebElement> getTitleJustInsideTitle()
	{
		return TitleJustInsideTitle;
	}
	
	@FindBy(xpath="//div[@class='collapse show'] /div[@class='video_list_body card-body']/div/div/span")
	private List<WebElement> TitleJustInsideTitle_IntoVideo;
	
	public List<WebElement> getTitleJustInsideTitle_IntoVideo()
	{
		return TitleJustInsideTitle_IntoVideo;
	}
	
	@FindBy(xpath="//div[@class='col-lg-9']//div[@class='fnp_card_header card-header']//div[@class='fnp_card_header_title']")
	private List<WebElement> TitleOf_FreeCourses;
	
	public List<WebElement> getTitleOf_FreeCourses()
	{
		return TitleOf_FreeCourses;
	}
	
	@FindBy(xpath="//div[@class='col-lg-9']//div[@class='fnp_wrapper accordion']//div[@class='collapse show']//div[@class='fdp_item list-group-item']")
	private List<WebElement> TitleInsideContentOf_FreeCourses;
	
	public List<WebElement> getTitleInsideContentOf_FreeCourses()
	{
		return TitleInsideContentOf_FreeCourses;
	}
	
	@FindBy(xpath="//div[@class='col-lg-9']//span[@class='fdp_vname']")
	private List<WebElement> VideoContent_Course;
	
	public List<WebElement> getVideoContent_Course()
	{
		return VideoContent_Course;
	}
	
	@FindBy(xpath="//button[@class='btn btn-link']")
	private WebElement TestSeries_Course_CloseButton;
	
	public WebElement getTestSeries_Course_CloseButton()
	{
		return TestSeries_Course_CloseButton;
	}
	
	@FindBy(xpath="//ul[@class='test-series-lesson-course-packages']/li")
	private List<WebElement> TestSeries_TestContent;
	
	public List<WebElement> getTestSeries_TestContent()
	{
		return TestSeries_TestContent;
	}
	
	@FindBy(xpath="//span[@class='test-series-lesson-test--title']")
	private WebElement TestSeries_Course_TestName;
	
	public WebElement getTestSeries_Course_TestName()
	{
		return TestSeries_Course_TestName;
	}
	
	@FindBy(xpath="//button[@class='btn btn-lint']")
	private WebElement TestSeries_Course_BackButton;
	
	public WebElement getTestSeries_Course_BackButton()
	{
		return TestSeries_Course_BackButton;
	}
	
	@FindBy(xpath="//span[@class='navbar-brand']")
	private WebElement HomePage;
	
	public WebElement getHomePage()
	{
		return HomePage;
	}
	
	@FindBy(css=".new_video_player_wrapper")
	private WebElement VideoPlayer;
	
	public WebElement getVideoPlayer()
	{
		return VideoPlayer;
	}
	
	@FindBy(xpath="//div[@class='ci_content_wrapper']/div/div/div/div/img")
	private List<WebElement>  DropDown_Arrow;
	
	public List<WebElement> getDropDown_Arrow()
	{
		return DropDown_Arrow;
	}
	
	@FindBy(xpath="//div[@class='collapse show']/div/div/div/div[@class='subchapter_header list-group-item']/div/img")
	private List<WebElement>  InsideDropDown_Arrow;
	
	public List<WebElement> getInsideDropDown_Arrow()
	{
		return InsideDropDown_Arrow;
	}
	
	@FindBy(xpath="//div[@class='collapse show']/div/div/span/img[contains(@src,'pdf.png')]")
	private List<WebElement>  PDF_Logo;
	
	public List<WebElement> getPDF_Logo()
	{
		return PDF_Logo;
	}
	
	@FindBy(xpath="//div[@class='collapse show']/div/div/div/img[contains(@src,'video_icon.png')]")
	private List<WebElement>  VIDEO_Logo;
	
	public List<WebElement> getVIDEO_Logo()
	{
		return VIDEO_Logo;
	}
	
	@FindBy(xpath="//div[@class='collapse show']/div/div/div/img[contains(@src,'quiz_icon.png')]")
	private List<WebElement>  QUIZ_Logo;
	
	public List<WebElement> getQUIZ_Logo()
	{
		return QUIZ_Logo;
	}
	
	@FindBy(xpath="//div[@class='collapse show']/div/div/div[@class='video_name']/span[1]")
	private List<WebElement>  VIDEO_LogoCourse;
	
	public List<WebElement> getVIDEO_LogoCourse()
	{
		return VIDEO_LogoCourse;
	}
	
	@FindBy(xpath="//div[@class='new_video_name']")
	private WebElement VideoName_ContentPage;
	
	public WebElement getVideoName_ContentPage()
	{
		return VideoName_ContentPage;
	}
	
	@FindBy(xpath="//button[@id='moe-dontallow_button']")
	private WebElement ColseNotification;

	public WebElement getColseNotification() {
	return ColseNotification;
	}
	
}
