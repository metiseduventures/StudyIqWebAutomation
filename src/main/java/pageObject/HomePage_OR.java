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

	@FindBy(xpath = "//input[@aria-label='name']")
	private List<WebElement> listTextEmail;
	
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
	
	@FindBy(xpath="(//a[@class='dropdown-list dropdown-item'])[2]")
	private WebElement MyProfile_Button;
	
	
	public  WebElement getMyProfile_Button() {
		return MyProfile_Button;
	}
	
	@FindBy(xpath="(//a[@class='dropdown-item'])[1]")
	private WebElement MyLibraryButton;
	
	
	public  WebElement getMyLibrary_Button() {
		return MyLibraryButton;
	}
	

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
}
