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
	
	@FindBy(css = ".course_details_wrapper>div>div.slick-list>div>div")
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
	
	@FindBy(xpath="//span[@class='navbar-brand']")
	private WebElement HomePage;
	
	public WebElement getHomePage()
	{
		return HomePage;
	}
	
	@FindBy(xpath="(//button[@class='btn btn-dark btn-lg'])[1]")
	private WebElement StudyIQ_Poket_NewsApp;
	
	public WebElement getStudyIQ_Poket_NewsApp(){
		return StudyIQ_Poket_NewsApp;
	}
	
	@FindBy(xpath="(//span[@class='whatsapp'])[1]")
	private WebElement whatsapp_SocialIcon;
	
	public WebElement getwhatsapp_SocialIcon(){
		return whatsapp_SocialIcon;
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
	
	@FindBy(xpath="(//a[@class='dropdown-list dropdown-item'])[3]")
	private WebElement LogOutButton;
	
	public WebElement getLogOutButton(){
		return LogOutButton;
	}
	
	@FindBy(xpath="(//a[@class='dropdown-list dropdown-item'])[1]")
	private WebElement MyOfferButton;
	
	public WebElement getMyOfferButton(){
		return MyOfferButton;
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
	
	@FindBy(css="button[class='ant-btn ant-btn-default expl_crs_btn']")
	private WebElement ExploreCourses;
	
	public WebElement getExploreCourses(){
		return ExploreCourses;
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
	
	@FindBy(xpath="//img[@src='/src/client/assets/homepage/right-arrow.svg']")
	private WebElement FarwardArrow;
	
	public WebElement getFarwardArrow(){
		return FarwardArrow;
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
	
	@FindBy(xpath="//div[@class='h_menu_items']")
	private List<WebElement> NavBar_Item;
	
	public List<WebElement> getNavBar_Item(){
		return NavBar_Item;
	}
	
	@FindBy(css=".close_img")
	private WebElement Close_Notification;
	
	public WebElement getClose_Notification(){
		return Close_Notification;
	}
	
	@FindBy(xpath="//button[@class='btn btn-primary btn-sm']")
	private WebElement ClickhereButton;
	
	public WebElement getContactClickHereButton(){
		return ClickhereButton;
	}
	
	@FindBy(xpath="//button[@class='btn btn-link']")
	private WebElement CloseButton;
	
	public WebElement getCloseButton(){
		return CloseButton;
	}
	
	@FindBy(xpath="//a[contains(text(),'My Offers')]")
	private WebElement MyOffer;
	
	public WebElement getMyOffer(){
		return MyOffer;
	}
	
	public List<WebElement> getlistTextpHONENumber() {
		return listTextpHONENumber;
	}

	@FindBy(xpath = "//input[@id='phoneNumber'] ")
	private List<WebElement> listTextpHONENumber;
	
	@FindBy(css = "button[class='loginBtn smallFont btn btn-primary btn-block']")
	private List<WebElement> BtnContinue;

	public List<WebElement> getBtnContinue() {
		return BtnContinue;
	}
	
	@FindBy(xpath = "(//div[@class='otp_record']/input)")
	private List<WebElement> listTextOtpBox;

	public List<WebElement> getlistTextOtpBox() {
		return listTextOtpBox;
	}
	
	@FindBy(xpath = "//button[@class='loginBtn btn btn-primary btn-block']")
	private List<WebElement> BtnVerify;

	public List<WebElement> getBtnVerify() {
		return BtnVerify;
	}
	
	@FindBy(xpath = "//div[@class='inputContainer']")
	private List<WebElement> ListOfExamPreferenceCOurses;

	public List<WebElement> getListOfExamPreferenceCOurses() {
		return ListOfExamPreferenceCOurses;
	}
	
	@FindBy(xpath = "(//div[@class='t_img']/img)")
	private List<WebElement> ListOfFronttestimonialVideo;

	public List<WebElement> getListOfFronttestimonialVideo() {
		return ListOfFronttestimonialVideo;
	}
	
	@FindBy(xpath="//div[@class='shaka-scrim-container']")
	private WebElement PlayListOfFronttestimonialVideo;
	
	public WebElement getPlayListOfFronttestimonialVideo(){
		return PlayListOfFronttestimonialVideo;
	}
	
	@FindBy(xpath="//div[@class='buy_now']/button[contains(text(),'Start My Course')]")
	private WebElement StartMyCourse_button;
	
	public WebElement getStartMyCourse_button(){
		return StartMyCourse_button;
	}
	
	@FindBy(xpath="//a[contains(text(),'Account Info')]")
	private WebElement AccountInfo;
	
	public WebElement getAccountInfo(){
		return AccountInfo;
	}
	
	@FindBy(xpath="//button[@id='moe-dontallow_button']")
	private WebElement ColseNotification;

	public WebElement getColseNotification() {
	return ColseNotification;
	}
	

	@FindBy(css = ".course_details")
	List<WebElement> listOfTestSeries;

	public List<WebElement> getListOfTestSeries() {
		return listOfTestSeries;
	}
	
	@FindBy(xpath="//button[contains(text(),'Start My Course')]")
	private WebElement StartMyCourse_Button;
	
	public WebElement getStartMyCourse_Button(){
		return StartMyCourse_Button;
	}


}
