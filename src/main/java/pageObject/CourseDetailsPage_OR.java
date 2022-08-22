package pageObject;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CourseDetailsPage_OR {

	public WebDriver driver;

	public CourseDetailsPage_OR(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}

	
	//Course Info
	
	@FindBy(xpath="//div[@class='courses_info_wrapper col-lg-5']/div[1]")
	private WebElement CourseInfo_Text;
	
	public WebElement getCourseInfo_Text() {
		// TODO Auto-generated method stub
		return CourseInfo_Text;
	}
	
	@FindBy(xpath=".image_price_wrapper>div.image_wrapper>img")
	private WebElement CourseInfo_Img1;
	
	public WebElement getCourseInfo_Img1() {
		// TODO Auto-generated method stub
		return CourseInfo_Img1;
	}
	
	@FindBy(xpath="//div[@class='price_details_wrapper']/div[1]/span[1]")
	private WebElement Discounted_Price;
	
	public WebElement getDiscounted_Price() {
		// TODO Auto-generated method stub
		return Discounted_Price;
	}
	
	@FindBy(xpath="//div[@class='price_details_wrapper']/div[1]/span[2]")
	private WebElement Original_Price;
	
	public WebElement getOriginal_Price() {
		// TODO Auto-generated method stub
		return Original_Price;
	}
	
	@FindBy(xpath="//div[@class='price_details_wrapper']/div[2]/span")
	private WebElement Discount_Date;
	
	public WebElement getDiscount_Date() {
		// TODO Auto-generated method stub
		return Discount_Date;
	}
	
	@FindBy(xpath="//div[@class='price_details_wrapper']/div[3]/button")
	private WebElement BuyNow_Button;
	
	public WebElement getBuyNow_Button() {
		// TODO Auto-generated method stub
		return BuyNow_Button;
	}
	
	@FindBy(xpath="//img[@class='close_icon']")
	private WebElement BuyNow_close;
	
	public WebElement getBuyNow_close() {
		// TODO Auto-generated method stub
		return BuyNow_close;
	}

	@FindBy(xpath="//div[@class='price_details_wrapper']/div[4]/span")
	private WebElement  Price_increase;
	
	public WebElement getPrice_increase() {
		// TODO Auto-generated method stub
		return Price_increase;
	}
	
	
	
	@FindBy(xpath="//div[@class='courses_info_wrapper col-lg-5']/div[2]/div/div[1]")
	private List<WebElement> CourseInfo_Img2;
	
	public List<WebElement> getCourseInfo_Img2() {
		// TODO Auto-generated method stub
		return CourseInfo_Img2;
	}
	
	@FindBy(xpath="//div[@class='info_title']")
	private List<WebElement> CourseInfo_ImgText;
	
	public List<WebElement> getCourseInfo_ImgText() {
		// TODO Auto-generated method stub
		return CourseInfo_ImgText;
	}
	
	//Exam Cover
	
	@FindBy(xpath="//div[@class='navigation']/button[1]")
	private WebElement ExamsCovered_TextButton;
	
	public  WebElement getExamsCovered_TextButton() {
		// TODO Auto-generated method stub
		return ExamsCovered_TextButton;
	}
	
	@FindBy(xpath="//div[@class='exam_title page_titles']")
	private WebElement ExamsCovered_Text;
	
	public  WebElement getExamsCovered_Text() {
		// TODO Auto-generated method stub
		return ExamsCovered_Text;
	}
	
	@FindBy(xpath="//div[@class='exams_covered_wrapper only_one']/div[2]/div/div/div/div/div/div/div[2]")
	private List<WebElement> ExamsCovered_Textinside;
	
	public List<WebElement> getExamsCovered_Textinside() {
		// TODO Auto-generated method stub
		return ExamsCovered_Textinside;
	}
	

	
	//About Authors
	
	@FindBy(xpath="//div[@class='navigation']/button[2]")
	private WebElement AboutAuthors_Button ;
	
	public  WebElement getAboutAuthors_Button() {
		// TODO Auto-generated method stub
		return AboutAuthors_Button;
	}
	
	@FindBy(xpath="//div[@class='authors_title page_titles']")
	private WebElement AboutAuthors_Title ;
	
	public  WebElement getAboutAuthors_Title() {
		// TODO Auto-generated method stub
		return AboutAuthors_Title;
	}
	
	@FindBy(xpath="//div[@class='author_box']/div/div[1]/img")
	private WebElement AboutAuthors_img ;
	
	public WebElement getAboutAuthors_img () {
		// TODO Auto-generated method stub
		return AboutAuthors_img ;
	}
	
	@FindBy(xpath="//div[@class='author_box']/div/div[2]/div[1]")
	private WebElement AboutAuthors_Name ;
	
	public WebElement getAboutAuthors_Name() {
		// TODO Auto-generated method stub
		return AboutAuthors_Name;
	}
	
	@FindBy(xpath="//div[@class='author_box']/div/div[2]/div[2]")
	private WebElement AboutAuthors_Department ;
	
	public WebElement getAboutAuthors_Department() {
		// TODO Auto-generated method stub
		return AboutAuthors_Department;
	}
	
	@FindBy(xpath="//div[@class='author_box']/ul/li/span")
	private List<WebElement> AboutAuthors_Details ;
	
	public List<WebElement> getAboutAuthors_Details() {
		// TODO Auto-generated method stub
		return AboutAuthors_Details;
	}
	
	//Demo Videos
	
	@FindBy(xpath="//div[@class='navigation']/button[3]")
	private WebElement Demo_Videos_Button;
	
	public  WebElement getDemo_Videos_Button() {
		// TODO Auto-generated method stub
		return Demo_Videos_Button;
	}
	
	@FindBy(xpath="//div[@class='title col-lg-6']")
	private WebElement DemoVideos_Text ;
	
	public  WebElement getDemoVideos_Text() {
		// TODO Auto-generated method stub
		return DemoVideos_Text;
	}
	
	@FindBy(xpath="//*[@id=\"root\"]/div/div/div[3]/div[6]/div/div[2]/div/div[2]/div/img")
	private List<WebElement> ListOf_Videos ;
	
	public  List<WebElement> getListOf_Videos() {
		// TODO Auto-generated method stub
		return ListOf_Videos;
	}
	
	//Get Free With This Course
	
	@FindBy(xpath="//div[@class='page_titles bundled_title']")
	private WebElement ThisCourses_Title;

	public WebElement getThisCourses_Title() {
		// TODO Auto-generated method stub
		return ThisCourses_Title;
	}
	
	@FindBy(xpath="//div[@class='validity']")
	private List<WebElement> ThisCourses_AllElement;
	
	public List<WebElement> getThisCourses_AllElement() {
		// TODO Auto-generated method stub
		return ThisCourses_AllElement ;
	}
	
	//Course Content
	
	@FindBy(xpath="//div[@class='page_titles course_content_title']")
	private WebElement CourseContent_Title;
	
	public  WebElement getCourseContent_Title() {
		// TODO Auto-generated method stub
		return CourseContent_Title;
	}
	
	@FindBy(xpath="//div[@class='navigation']/button[4]")
	private WebElement CourseContent_Button;
	
	public  WebElement getCourseContent_Button() {
		// TODO Auto-generated method stub
		return CourseContent_Button;
	}
	
	@FindBy(xpath="//div[@class='cdcc_view_more_less_wrapper']")
	private WebElement CourseContent_viewAll;
	
	public WebElement getCourseContent_viewAll() {
		// TODO Auto-generated method stub
		return CourseContent_viewAll;
	}
	
	@FindBy(xpath="//span[@class='cdccw_ftw']")
	private List<WebElement> CourseContent_AllElement;
	
	public List<WebElement> getContent_AllElement() {
		// TODO Auto-generated method stub
		return CourseContent_AllElement;
	}
	
	@FindBy(xpath="//div[@class='cdccw_item']")
	private List<WebElement> CourseContent_AllElementInside;
	
	public List<WebElement> getContent_AllElementInside() {
		// TODO Auto-generated method stub
		return CourseContent_AllElementInside;
	}
	
	//Our Packages
	
	@FindBy(xpath="//div[@class='our_packages_wrapper']/div[1]")
	private WebElement OurPackages_title ;
	
	public WebElement getOurPackages_title() {
		// TODO Auto-generated method stub
		return OurPackages_title;
	}
	
	@FindBy(xpath="//div[@class='our_packages_wrapper']/div[3]/div[1]/div")
	private WebElement OurPackages_Premium;
	
	public WebElement getOurPackages_Premium() {
		// TODO Auto-generated method stub
		return OurPackages_Premium;
	}
	
	@FindBy(xpath="//div[@class='price_title']")
	private WebElement OurPackages_priceText;
	
	public WebElement getOurPackages_priceText() {
		// TODO Auto-generated method stub
		return OurPackages_priceText;
	}
	
	@FindBy(xpath="//div[@class='base_price']")
	private WebElement OurPackages_priceOriginal;
	
	public WebElement getOurPackages_priceOriginal() {
		// TODO Auto-generated method stub
		return OurPackages_priceOriginal;
	}
	
	@FindBy(xpath="//div[@class='dp2']")
	private WebElement OurPackages_priceDiscounted;
	
	public WebElement getOurPackages_priceDiscounted() {
		// TODO Auto-generated method stub
		return OurPackages_priceDiscounted;
	}
	
	@FindBy(xpath="//div[@class='validity_title']")
	private WebElement OurPackages_validityText;
	
	public WebElement getOurPackages_validityText() {
		// TODO Auto-generated method stub
		return OurPackages_validityText;
	}
	
	@FindBy(xpath="//div[@class='validity_time']/div")
	private WebElement OurPackages_validityTime;
	
	public WebElement getOurPackages_validityTime() {
		// TODO Auto-generated method stub
		return OurPackages_validityTime;
	}
	
	@FindBy(xpath="//div[@class='emi_title']")
	private WebElement OurPackages_EMIText;
	
	public WebElement getOurPackages_EMIText() {
		// TODO Auto-generated method stub
		return OurPackages_EMIText;
	}
	
	//Features
	
	@FindBy(xpath="//div[@class='features_wrapper']/div[1]")
	private WebElement Features_Text;
	
	public WebElement getFeatures_Text() {
		// TODO Auto-generated method stub
		return Features_Text ;
	}
	
	@FindBy(xpath="//div[@class='feature_title']")
	private List<WebElement> Features_TextElement;
	
	public List<WebElement> getFeatures_TextElement() {
		// TODO Auto-generated method stub
		return Features_TextElement ;
	}
	
	@FindBy(xpath="//div[@class='expand-features-wrapper']/span")
	private WebElement expand_features;
	
	public WebElement getexpand_featurest() {
		// TODO Auto-generated method stub
		return expand_features ;
	}
	
	@FindBy(xpath="//div[@class='feature_desc']")
	private List<WebElement> Features_TextElement1;
	
	public List<WebElement> getFeatures_TextElement1() {
		// TODO Auto-generated method stub
		return Features_TextElement1 ;
	}
	
	//Frequently Asked Questions
	
	@FindBy(xpath="//div[@class='navigation']/button[5]")
	private WebElement FAQ_Button;
	
	public  WebElement getFAQ_Button() {
		// TODO Auto-generated method stub
		return FAQ_Button;
	}
	
	@FindBy(xpath="//div[@class='page_titles faqs_title']")
	private WebElement FAQ_Text;
	
	public  WebElement getFAQ_Text() {
		// TODO Auto-generated method stub
		return FAQ_Text;
	}
	
	@FindBy(xpath="//div[@class='card']/div[1]/span[2]")
	private List<WebElement> FAQ_Question;
	
	public List<WebElement> getFAQ_Question() {
		// TODO Auto-generated method stub
		return FAQ_Question;
	}
	
	@FindBy(xpath="//span[@class='d-block mx-3']")
	private WebElement FAQ_ViewAllButton;
	
	public WebElement getFAQ_ViewAllButton() {
		// TODO Auto-generated method stub
		return FAQ_ViewAllButton;
	}
	
	@FindBy(xpath="//div[@class='answer card-body']")
	private List<WebElement> FAQ_TextInside;
	
	public List<WebElement> getFAQ_TextInside() {
		// TODO Auto-generated method stub
		return FAQ_TextInside ;
	}
	
	//Pricing
	
	@FindBy(xpath="//div[@class='navigation']/button[6]")
	private WebElement Pricing_Button;
	
	public  WebElement getPricing_Button() {
		// TODO Auto-generated method stub
		return Pricing_Button;
	}
	
	@FindBy(xpath="//img[@class='close_icon']")
	private WebElement Pricing_cut;
	
	public WebElement getPricing_cut() {
		// TODO Auto-generated method stub
		return Pricing_cut;
	}
	
}