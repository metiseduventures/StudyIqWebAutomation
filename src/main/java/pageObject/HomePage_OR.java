package pageObject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage_OR {

	@FindBy(css = ".login-btn")
	private List<WebElement> listBtnLoginRegister;

	public List<WebElement> getListBtnLoginRegister() {
		return listBtnLoginRegister;
	}

	public List<WebElement> getListTextMobileNumber() {
		return listTextMobileNumber;
	}

	@FindBy(xpath = "//input[@placeholder='Mobile Number']")
	private List<WebElement> listTextMobileNumber;

	@FindBy(css = ".otp_input")
	private List<WebElement> listTextOtp;

	public List<WebElement> getListTextOtp() {
		return listTextOtp;
	}

	@FindBy(css = ".login_btn.btn-block")
	private List<WebElement> listBtnContinue;

	public List<WebElement> getListBtnContinue() {
		return listBtnContinue;
	}

	@FindBy(xpath = "//input[@aria-label='name']")
	private List<WebElement> listTextName;

	public List<WebElement> getListTextName() {
		return listTextName;
	}

	public List<WebElement> getListTextEmail() {
		return listTextEmail;
	}

	@FindBy(xpath = "//input[@aria-label='email']")
	private List<WebElement> listTextEmail;

	@FindBy(css = "input[placeholder='What do you want to learn?']")
	WebElement inputSearchElement;

	public WebElement searchItem() {
		return inputSearchElement;
	}

	@FindBy(css = "div[class='search_r_item list-group-item']")
	List<WebElement> itemSearch;

	public List<WebElement> searchElements() {
		return itemSearch;
	}
	
	@FindBy(css = ".course_details")
	List<WebElement> listCourse;

	public List<WebElement> getListCourse() {
		return listCourse;
	}
	
	@FindBy(xpath = "(//div[@class='slick-track'])[25]/div/div/div/img")
	List<WebElement> listOfBook;

	public List<WebElement> getlistOfBook() {
		return listOfBook;
	}
	
	@FindBy(xpath="(//div[@class='slick-list'])[2]/div/div/div/div/img")
	private List<WebElement> BestSelling_Button;
	
	
	public  List<WebElement> getBestSelling_Button() {
		return BestSelling_Button;
	}

	@FindBy(xpath="//button[@id='dropdown-basic-button']")
	private WebElement DropDown_Button;
	
	
	public  WebElement getDropDown_Button() {
		return DropDown_Button;
	}
	
	@FindBy(xpath="(//a[@role='button'])[2]")
	private WebElement MyProfile_Button;
	
	
	public  WebElement getMyProfile_Button() {
		return MyProfile_Button;
	}
	
	@FindBy(xpath="//input[@placeholder='What do you want to learn?']")
	private WebElement searchBar;
	
	public WebElement getsearchBar() {
		return searchBar;
	}
	
	@FindBy(xpath="//button[@class='btn btn-primary btn-sm']")
	private WebElement ClickHere_Button;
	
	public WebElement getClickHere_Button()
	{
		return ClickHere_Button;
	}
	
	@FindBy(xpath="//span[@class='navbar-brand']")
	private WebElement HomePage;
	
	public WebElement getHomePage()
	{
		return HomePage;
	}

	@FindBy(xpath="//div[@class='h_menu_items']")
	private List<WebElement> NavBar_Item;
	
	public List<WebElement> getNavBar_Item(){
		return NavBar_Item;
	}
	
	@FindBy(xpath=".not-found--title")
	private WebElement CouldNotFind;
	
	public WebElement getCouldNotFind(){
		return CouldNotFind;
	}
	
	@FindBy(xpath="//button[@class='not-found--button']")
	private WebElement BackToHome;
	
	public WebElement getBackToHome(){
		return BackToHome;
	}
	
	@FindBy(xpath="//div[@class='courses navbar-nav']")
	private WebElement CourseButton;
	
	public WebElement getCourseButton(){
		return CourseButton;
	}
	
	@FindBy(xpath="//img[@src='/src/client/assets/homepage/right-arrow.svg']")
	private WebElement FarwardArrow;
	
	public WebElement getFarwardArrow(){
		return FarwardArrow;
	}
	
	@FindBy(xpath="//img[@src='/src/client/assets/homepage/left-arrow.svg']")
	private WebElement BackwardArrow;
	
	public WebElement getBackwardArrow(){
		return BackwardArrow;
	}
		
	@FindBy(xpath="//div[@class='slick-slide slick-active slick-center slick-current']//div//img")
	private WebElement SlideCoursesOne;
	
	public WebElement getSlideCoursesOne(){
		return SlideCoursesOne;
	}
	
	@FindBy(css="button[class='ant-btn ant-btn-default expl_crs_btn']")
	private WebElement ExploreCourses;
	
	public WebElement getExploreCourses(){
		return ExploreCourses;
	}
	
		
	@FindBy(xpath="(//div[@class='course_category']/button)[1]")
	private WebElement ViewAllButton_BestSelling;
	
	public WebElement getViewAllButton_BestSelling(){
		return ViewAllButton_BestSelling;
	}
	
	@FindBy(xpath="(//div[@class='course_category']/button)[4]")
	private WebElement ViewAllButton_UPSC_CivilServices;
	
	public WebElement getViewAllButton_UPSC_CivilServices(){
		return ViewAllButton_UPSC_CivilServices;
	}
	
	@FindBy(css=".close")
	private WebElement RightNotification_CloseButton;
	
	public WebElement getRightNotification_CloseButton(){
		return RightNotification_CloseButton;
	}
	
	
	@FindBy(xpath="(//div[@class='course_category']/button)[6]")
	private WebElement ViewAllButton_SSC_Bank;
	
	public WebElement getViewAllButton_SSC_Bank(){
		return ViewAllButton_SSC_Bank;
	}
	
	@FindBy(xpath="(//div[@class='course_category']/button)[8]")
	private WebElement ViewAllButton_UPSCOptionals;
	
	public WebElement getViewAllButton_UPSCOptionals(){
		return ViewAllButton_UPSCOptionals;
	}
	
	@FindBy(xpath="(//div[@class='course_category']/button)[9]")
	private WebElement ViewAllButton_Defence;
	
	public WebElement getViewAllButton_Defence(){
		return ViewAllButton_Defence;
	}
	
	@FindBy(xpath="(//div[@class='course_category']/button)[12]")
	private WebElement ViewAllButton_Railways;
	
	public WebElement getViewAllButton_Railways(){
		return ViewAllButton_Railways;
	}
	

	@FindBy(xpath="//div[@class='va_img_wrapper']")
	private List<WebElement> ListOfInsideCourses;
	
	public List<WebElement> getListOfInsideCourses(){
		return ListOfInsideCourses;
	}
	
	@FindBy(xpath="(//div[@class='va_img_wrapper'])[1]")
	private WebElement InsideSingleCourses;
	
	public WebElement getInsideSingleCourses(){
		return InsideSingleCourses;
	}
	
	@FindBy(css="div[class='h_t_view_all_wrapper'] span")
	private WebElement ViewAllButton_ofTestimonial;
	
	public WebElement getViewAllButton_ofTestimonial(){
		return ViewAllButton_ofTestimonial;
	}
	
	@FindBy(xpath="//div[@class='dtlv_img']")
	private List<WebElement> TestimonialVideo;
	
	public List<WebElement> getTestimonialVideo(){
		return TestimonialVideo;
	}
	
	@FindBy(xpath="//div[@class='shaka-scrim-container']")
	private WebElement Video;
	
	public WebElement getVideo(){
		return Video;
	}
	
	@FindBy(xpath="(//div[contains(text(),'My Library')])[1]")
	private WebElement MyLibraryButton;
	
	public WebElement getMyLibraryButton(){
		return MyLibraryButton;
	}
	
	@FindBy(xpath="//a[contains(text(),'My Offers')]")
	private WebElement MyOfferText;
	
	public WebElement getMyOfferText(){
		return MyOfferText;
	}
	
	@FindBy(xpath="(//button[contains(text(),'Explore Courses')])[2]")
	private WebElement LibraryExplore_Courses;
	
	public WebElement getLibraryExplore_Courses()
	{
		return LibraryExplore_Courses;
	}
	
	@FindBy(xpath="(//a[@class='dropdown-list dropdown-item'])[1]")
	private WebElement MyOfferButton;
	
	public WebElement getMyOfferButton(){
		return MyOfferButton;
	}
		
	@FindBy(xpath="(//a[@class='dropdown-list dropdown-item'])[3]")
	private WebElement LogOutButton;
	
	public WebElement getLogOutButton(){
		return LogOutButton;
	}
	
	
	@FindBy(xpath="//button[@class='btn btn-link']")
	private WebElement CloseButton;
	
	public WebElement getCloseButton(){
		return CloseButton;
	}
	
	@FindBy(xpath="//button[@class='btn btn-primary btn-sm']")
	private WebElement ClickhereButton;
	
	public WebElement getContactClickHereButton(){
		return ClickhereButton;
	}
	
	@FindBy(xpath="//img[@class='T75of nm4vBd arM4bb']")
	private WebElement StudyIQApp_Logo;
	
	public WebElement getStudyIQApp_Logo(){
		return StudyIQApp_Logo;
	}
	
	@FindBy(xpath="(//div[@class='social-icons']/span)[1]")
	private WebElement StudyIQ_Youtube_SocialIcon;
	
	public WebElement getStudyIQ_Youtube_SocialIcon(){
		return StudyIQ_Youtube_SocialIcon;
	}
	
	@FindBy(xpath="(//div[@class='social-icons']/span)[2]")
	private WebElement StudyIQ_Facebook_SocialIcon;
	
	public WebElement getStudyIQ_Facebook_SocialIcon(){
		return StudyIQ_Facebook_SocialIcon;
	}

	@FindBy(xpath="(//div[@class='social-icons']/span)[3]")
	private WebElement StudyIQ_Telegram_SocialIcon;
	
	public WebElement getStudyIQ_Telegram_SocialIcon(){
		return StudyIQ_Telegram_SocialIcon;
	}
	
	@FindBy(xpath="(//div[@class='social-icons']/span)[4]")
	private WebElement StudyIQ_Instagram_SocialIcon;
	
	public WebElement getStudyIQ_Instagram_SocialIcon(){
		return StudyIQ_Instagram_SocialIcon;
	}
	
	@FindBy(xpath="(//div[@class='social-icons']/span)[5]")
	private WebElement StudyIQ_Twitter_SocialIcon;
	
	public WebElement getStudyIQ_Twitter_SocialIcon(){
		return StudyIQ_Twitter_SocialIcon;
	}
	
	@FindBy(xpath="(//div[@class='social-icons']/span)[6]")
	private WebElement StudyIQ_LinkedIn_SocialIcon;
	
	public WebElement getStudyIQ_LinkedIn_SocialIcon(){
		return StudyIQ_LinkedIn_SocialIcon;
	}
	
	@FindBy(xpath="(//div[@class='social-icons']/span)[7]")
	private WebElement StudyIQ_Tumblr_SocialIcon;
	
	public WebElement getStudyIQ_Tumblr_SocialIcon(){
		return StudyIQ_Tumblr_SocialIcon;
	}
	
	@FindBy(xpath="(//span[@class='whatsapp'])[1]")
	private WebElement whatsapp_SocialIcon;
	
	public WebElement getwhatsapp_SocialIcon(){
		return whatsapp_SocialIcon;
	}
	
	@FindBy(xpath="(//button[@class='btn btn-dark btn-lg'])[1]")
	private WebElement StudyIQ_Poket_NewsApp;
	
	public WebElement getStudyIQ_Poket_NewsApp(){
		return StudyIQ_Poket_NewsApp;
	}
	
	@FindBy(xpath="(//button[@class='btn btn-dark btn-lg'])[2]")
	private WebElement StudyIQ_TestIQ;
	
	public WebElement getStudyIQ_TestIQ(){
		return StudyIQ_TestIQ;
	}
	
	@FindBy(css=".close_img")
	private WebElement Close_Notification;
	
	public WebElement getClose_Notification(){
		return Close_Notification;
	}

	
	
	
	
}
