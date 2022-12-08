package pageObject;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LibraryPage_OR {

	@FindBy(css = ".library-course-item--title")
	List<WebElement> TitlesInLibrary;

	public List<WebElement> libraryTitles() {
		return TitlesInLibrary;
	}

	@FindBy(xpath = "//button[@id='dropdown-basic-button']")
	WebElement accountBtn;

	public WebElement accountClick() {
		return accountBtn;
	}

	@FindBy(xpath = "//a[@class='dropdown-item']")
	List<WebElement> myAccountItem;

	public List<WebElement> accountItemCheck() {
		return myAccountItem;
	}

	@FindBy(xpath = "(//div[@class='library_list_item list-group-item'])[1]")
	private WebElement Smart_Courses;

	public WebElement getSmart_Courses() {
		return Smart_Courses;
	}

	@FindBy(xpath = "(//div[@class='library_list_item list-group-item'])[2]")
	private WebElement Micro_Courses;

	public WebElement getMicro_Courses() {
		return Micro_Courses;
	}

	@FindBy(xpath = "(//div[@class='library_list_item list-group-item'])[3]")
	private WebElement Books;

	public WebElement getBooks() {
		return Books;
	}

	@FindBy(xpath = "(//div[@class='library_list_item list-group-item'])[4]")
	private WebElement TestSeries;

	public WebElement getTestSeries() {
		return TestSeries;
	}

	@FindBy(xpath = "(//*[contains(text(),'My Library')])[1]")
	private WebElement MyLibrary_Button;

	public WebElement getMyLibrary_Button() {
		return MyLibrary_Button;
	}

	@FindBy(xpath = "//button[@class='btn btn-primary']")
	private WebElement Explore_Courses;

	public WebElement getExplore_Courses() {
		return Explore_Courses;
	}

	@FindBy(xpath = "(//*[contains(text(),'Smart Course')])[2]")
	private WebElement DotSmart;

	public WebElement getDotSmart() {
		return DotSmart;
	}

	@FindBy(xpath = "(//*[contains(text(),'Micro Course')])")
	private WebElement DotMicro;

	public WebElement getDotMicro() {
		return DotMicro;
	}

	@FindBy(xpath = "(//*[contains(text(),'Books')])[1]")
	private WebElement DotBook;

	public WebElement getDotBook() {
		return DotBook;
	}

	@FindBy(xpath = "(//div[contains(text(),'Test Series')])[1]")
	private WebElement DotTestSeries;

	public WebElement getDotTestSeries() {
		return DotTestSeries;
	}

	@FindBy(xpath = "//div[@class='li_collapse_wrapper collapse show']//div[@class='li_card_body card-body']//div[1]//div[2]//div[1]//button[1]")
	private WebElement Start_MyCourse;

	public WebElement getStart_MyCourse() {
		return Start_MyCourse;
	}

	@FindBy(xpath = "//button[@data-id='CLOSE']")
	private WebElement Close;

	public WebElement getClose() {
		return Close;
	}

	@FindBy(xpath = "//button[@class='close']")
	private WebElement Close_ExtendCourse;

	public WebElement getClose_ExtendCourse() {
		return Close_ExtendCourse;
	}

	@FindBy(xpath = "//div[@class='ci_content_wrapper']/div/div")
	private List<WebElement> Topics_Of_Syllabus;

	public List<WebElement> getTopics_Of_Syllabus() {
		return Topics_Of_Syllabus;
	}

	@FindBy(xpath = "//div[@class='collapse show']/div/div/div")
	private List<WebElement> Containt_InsideTopics;

	public List<WebElement> getContaint_InsideTopics() {
		return Containt_InsideTopics;
	}

	@FindBy(xpath = "(//div[@class='collapse show'])[2]/div/div/span[2]")
	private List<WebElement> Video_OfCourse;

	public List<WebElement> getVideo_OfCourse() {
		return Video_OfCourse;
	}

	@FindBy(xpath = "//div[@class='ci_content_wrapper']/div/div[1]")
	private WebElement Topics_Single;

	public WebElement getTopics_Single() {
		return Topics_Single;
	}

	@FindBy(xpath = "//div[@class='collapse show']/div/div/div[1]")
	private WebElement Containt_Inside_SingleTopics;

	public WebElement getContaint_Inside_SingleTopics() {
		return Containt_Inside_SingleTopics;
	}

	@FindBy(xpath = "((//div[@class='collapse show'])[2]/div/div/span[2])[1]")
	private WebElement contant_OfSingle_Course;

	public WebElement getcontant_OfSingle_Course() {
		return contant_OfSingle_Course;
	}

	@FindBy(id = "(//div[@class='library-course-item type-video'])")
	private List<WebElement> courseBoxInLibraryList;

	public List<WebElement> listOfCourseBoxInLibrary() {
		return courseBoxInLibraryList;
	}

	@FindBy(css = ".nav-item.active>span")
	private List<WebElement> courseTypeMenu;

	public List<WebElement> getCourseTypeMenu() {
		return courseTypeMenu;
	}
	
	@FindBy(xpath="(//div[@class='dropdown-body open z_index'])/div")
	private List<WebElement> ListOfsourse;

	public List<WebElement> getListOfsourse() {
		return ListOfsourse;
	}

	@FindBy(xpath="(//div[contains(text(),'Live Classes')])")
	private WebElement DotLiveClasses;

	public WebElement getDotLiveClasses()
	{
		return DotLiveClasses;
	}
	
	@FindBy(xpath="(//div[contains(text(),'Books')])[1]")
	private WebElement DropDownBook;

	public WebElement getDropDownBook()
	{
		return DropDownBook;
	} 
	//Update Library 
	
	@FindBy(xpath = "(//*[contains(text(),'Video Course')])[1]")
	private WebElement Dotvideocourses;

	public WebElement getDotvideocourses() {
		return Dotvideocourses;
	}
	
	@FindBy(xpath = "//div[@id='library-listing-wrapper']/div[2]/div/div")
	private List<WebElement> CourseInsideLibrary;

	public List<WebElement> getCourseInsideLibrary() {
		return CourseInsideLibrary;
	}
	
	@FindBy(xpath = "//button[contains(text(),'Renew Now')]")
	private List<WebElement> RenewBUtton;

	public List<WebElement> getRenewBUtton() {
		return RenewBUtton;
	}
	
	@FindBy(xpath = "//div[@id='library-listing-wrapper']/div[2]/div/div/div/div/button[contains(text(),'Extend Validity')]")
	private List<WebElement> ExtendValidity;

	public List<WebElement> getExtendValidity() {
		return ExtendValidity;
	}
	
	@FindBy(xpath = "//button[contains(text(),'Remove')]")
	private List<WebElement> RemoveBUtton;

	public List<WebElement> getRemoveBUtton() {
		return RemoveBUtton;
	}
	
	@FindBy(xpath = "//span[@class='info-validity']")
//	@FindBy(xpath = "//div[@id='library-listing-wrapper']/div[2]/div/div/div[2]/div[2]/span/text()")
	private List<WebElement> ValidCourse;

	public List<WebElement> getValidCourse() {
		return ValidCourse;
	}
	
	@FindBy(xpath = " //span[contains(text(),'More')]")
	private WebElement More_Button;

	public WebElement getMore_Button() {
		return More_Button;
	}
	
	@FindBy(xpath = "//span[contains(text(),'Extend Validity')]")
	private WebElement Extend_Validity;

	public WebElement getExtend_Validity() {
		return Extend_Validity;
	}
	
	@FindBy(xpath = "//li[@class='nav-item active']")
	private List<WebElement> ListOf_SmartCourses;

	public List<WebElement> getListOf_SmartCourses() {
		return ListOf_SmartCourses;
	}
	
	@FindBy(xpath = "//button[@class='btn btn-link']")
	private WebElement CloseButton_ofTestSeriesCourses;

	public WebElement getCloseButton_ofTestSeriesCourses() {
		return CloseButton_ofTestSeriesCourses;
	}
	
	@FindBy(xpath="//button[@id='dropdown-basic-button']")
	private WebElement DropDown_Button;
	
	
	public  WebElement getDropDown_Button() {
		return DropDown_Button;
	}
	
	@FindBy(xpath="(//div[contains(text(),'My Library')])[1]")
	private WebElement MyLibraryButton;
	
	public WebElement getMyLibraryButton(){
		return MyLibraryButton;
	}
	
	@FindBy(xpath="//h3[contains(text(),'Your library is empty')]")
	private WebElement LibraryEmptyText;
	
	public WebElement getLibraryEmptyText()
	{
		return LibraryEmptyText;
	}
	
	@FindBy(xpath="(//button[contains(text(),'Explore Courses')])[2]")
	private WebElement LibraryExplore_Courses;
	
	public WebElement getLibraryExplore_Courses()
	{
		return LibraryExplore_Courses;
	}
	
	@FindBy(xpath="//span[@class='navbar-brand']")
	private WebElement HomePage;
	
	public WebElement getHomePage()
	{
		return HomePage;
	}
	
	@FindBy(css=".close_img")
	private WebElement Close_Notification;
	
	public WebElement getClose_Notification(){
		return Close_Notification;
	}
	
	
	@FindBy(css = ".navigation-menu>li")
	List<WebElement> listLibaryMenuItem;

	public List<WebElement> getListLibaryMenuItem() {
		return listLibaryMenuItem;
	}
	
	
	@FindBy(css = ".library-course-item")
	List<WebElement> listLibaryMenuCourseItem;

	public List<WebElement> getListLibaryMenuCourseItem() {
		return listLibaryMenuCourseItem;
	}
	
	@FindBy(xpath = "//*[@id=\"responsive-navbar-nav\"]/div[1]/div[2]/div/div/div/div[2]/div")
	List<WebElement> listLibaryItem;

	public List<WebElement> getlistLibaryItem() {
		return listLibaryItem;
	}
	
	@FindBy(xpath = "//div[@class='dropdown']/div[text()='My Library']")
	List<WebElement> listLibCourseItem;

	public List<WebElement> getlistLibCourseItem() {
		return listLibCourseItem;
	}
	
	@FindBy(xpath="(//div[@class='slick-list'])")
	private WebElement Watch_List;
	
	public WebElement getWatch_List()
	{
		return Watch_List;
	}
	
	@FindBy(xpath = "//button[contains(text(),'Continue watching')]")
	List<WebElement> ContinueButton;

	public List<WebElement> getContinueButton() {
		return ContinueButton;
	}
	
	@FindBy(xpath = "//div[@class='card-description mt-1']")
	List<WebElement> VideoName_in_Watch_List;

	public List<WebElement> getVideoName_in_Watch_List() {
		return VideoName_in_Watch_List;
	}
	
	@FindBy(xpath="//div[@class='slick-slider slick-initialized']/span")
	private WebElement BackButton;
	
	public WebElement getBackButton()
	{
		return BackButton;
	}
	
	@FindBy(xpath="//div[@class='right_arrow_wrapper right-arrow slick-arrow slick-next']")
	private WebElement FarwardButton;
	
	public WebElement getFarwardButton()
	{
		return FarwardButton;
	}
	
	@FindBy(xpath="(//button[contains(text(),'Login')])[1]")
	private WebElement LoginButton;
	
	public WebElement getLoginButton()
	{
		return LoginButton;
	}
	
	@FindBy(xpath="//button[@id='moe-dontallow_button']")
	private WebElement ColseNotification;

	public WebElement getColseNotification() {
	return ColseNotification;
	}
	
	@FindBy(xpath="//div[@class='new_video_player_wrapper ']")
	private WebElement VideoPlayer;
	
	public WebElement getVideoPlayer()
	{
		return VideoPlayer;
	}

}