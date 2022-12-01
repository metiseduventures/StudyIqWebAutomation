package pageObject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyProfile_OR {

	@FindBy(xpath = "(//label[@class='form-label'])[1]")
	private WebElement YourName_text;
	
	
	public WebElement getYourName_text() {
		return YourName_text;
	}
	
	@FindBy(xpath = "(//input[@type='text'])[2]")
	private WebElement YourName_input;
	
	
	public WebElement getYourName_input() {
		return YourName_input;
	}
	
	@FindBy(xpath = "(//label[@class='form-label'])[4]")
	private WebElement YourCity_text;
	
	
	public WebElement getYourCity_text() {
		return YourCity_text;
	}
	
	@FindBy(xpath = "(//input[@type='text'])[4]")
	private WebElement YourCity_input;
	
	
	public WebElement getYourCity_input() {
		return YourCity_input;
	}
	
	@FindBy(xpath = "(//label[@class='form-label'])[3]")
	private WebElement YourAddress_text;
	
	
	public WebElement getYourAddress_text() {
		return YourAddress_text;
	}
	
	@FindBy(xpath = "(//input[@type='text'])[3]")
	private WebElement YourAddress_input;
	
	
	public WebElement getYourAddress_input() {
		return YourAddress_input;
	}
	
	@FindBy(xpath = "(//label[@class='form-label'])[5]")
	private WebElement YourState_text;
	
	
	public WebElement getYourState_text() {
		return YourState_text;
	}
	
	@FindBy(xpath = "(//input[@type='text'])[5]")
	private WebElement YourState_input;
	
	
	public WebElement getYourState_input() {
		return YourState_input;
	}
	
	@FindBy(xpath = "(//label[@class='form-label'])[2]")
	private WebElement YourPinCode_text;
	
	
	public WebElement getYourPinCode_text() {
		return YourPinCode_text;
	}
	
	@FindBy(xpath = "(//input[@class='form-control'])[2]")
	private WebElement YourPinCode_input;
	
	
	public WebElement getYourPinCode_input() {
		return YourPinCode_input;
	}
	
	@FindBy(css = "button[class='btn btn-primary']")
	private WebElement UpdateProfile_button;
	
	
	public WebElement getUpdateProfile_button() {
		return UpdateProfile_button;
	}
	
	@FindBy(xpath = "(//input[@class='form-control'])[2]")
	private WebElement PinCode_input;
	
	
	public WebElement getPinCode_input() {
		return PinCode_input;
	}
	
	@FindBy(xpath = "(//input[@type='text'])[3]")
	private WebElement Address_input;
	
	
	public WebElement getAddress_input() {
		return Address_input;
	}
	
	@FindBy(xpath = "(//input[@type='text'])[4]")
	private WebElement City_input;
	
	
	public WebElement getCity_input() {
		return City_input;
	}
	
	@FindBy(xpath = "(//input[@type='text'])[5]")
	private WebElement State_input;
	
	
	public WebElement getState_input() {
		return State_input;
	}
	
	@FindBy(xpath = "//div[@class='col']")
	private WebElement MassageAfter_UpdateProfile;
	
	
	public WebElement getMassageAfter_UpdateProfile() {
		return MassageAfter_UpdateProfile;
	}
	

	@FindBy(xpath = "//div[@class='navbar-wrapper']/div[contains(text(),'My Profile')]")
	private WebElement MyProfile_icon;
	
	
	public WebElement getMyProfile_icon() {
		return MyProfile_icon;
	}
	
	@FindBy(css = "input[placeholder='Enter your Address']")
	private WebElement NewAddress_input;
	
	
	public WebElement getNewAddress_input() {
		return NewAddress_input;
	}
	
	@FindBy(xpath = "//input[@placeholder='Enter your Pin Code']")
	private WebElement NewPinCode_input;
	
	
	public WebElement getNewPinCode_input() {
		return NewPinCode_input;
	}
	
	@FindBy(xpath = "//button[contains(text(),'Add Your Exam')]")
	private WebElement AddYourExam_Button;
	
	
	public WebElement getAddYourExam_Button() {
		return AddYourExam_Button;
	}
	
	@FindBy(xpath = "//button[contains(text(),'Update Your Exam')]")
	private WebElement UpdateYourExam_Button;
	
	
	public WebElement getUpdateYourExam_Button() {
		return UpdateYourExam_Button;
	}
	
	@FindBy(xpath = "(//div[@class='grid-item']//button[contains(text(),'Update')])[1]")
	private WebElement EmailUpdate_Button;
	
	
	public WebElement getEmailUpdate_Button() {
		return EmailUpdate_Button;
	}
	
	@FindBy(xpath = "//div[@class='grid-item']//input[@placeholder='Enter your Email']")
	private WebElement Email_inputBox;
	
	
	public WebElement getEmail_inputBox() {
		return Email_inputBox;
	}
	
	@FindBy(xpath = "(//div[@class='grid-item']//button[contains(text(),'Request OTP')])")
	private WebElement RequestOTP_Button;
	
	
	public WebElement getRequestOTP_Button() {
		return RequestOTP_Button;
	}
	
	@FindBy(xpath = "//div[@class='grid-item']//input[@placeholder='Enter OTP']")
	private WebElement OTP_inputBox;
	
	
	public WebElement getOTP_inputBox() {
		return OTP_inputBox;
	}
	
	@FindBy(xpath = "(//div[@class='grid-item']//button[contains(text(),'Verify')])")
	private WebElement VerifyOTP_Button;
	
	
	public WebElement getVerifyOTP_Button() {
		return VerifyOTP_Button;
	}
	
	@FindBy(xpath = "(//div[@class='grid-item']//button[contains(text(),'Update')])[2]")
	private WebElement MobileUpdate_Button;
	
	
	public WebElement getMobileUpdate_Button() {
		return MobileUpdate_Button;
	}
	
	@FindBy(xpath = "//div[@class='grid-item']//input[@placeholder='Mobile Number']")
	private WebElement MobileNumber_inputBox;
	
	
	public WebElement getMobileNumber_inputBox() {
		return MobileNumber_inputBox;
	}
	
	@FindBy(xpath = "(//div[@class='main-text'])[2]")
	private WebElement NoTransactionText;
	
	
	public WebElement getNoTransactionText() {
		return NoTransactionText;
	}
	
	@FindBy(xpath="//p[@class='success-btn']")
	private List<WebElement> SuccessTransaction;
	
	
	public  List<WebElement> getSuccessTransaction() {
		return SuccessTransaction;
	}
	
	@FindBy(xpath="//p[@class='ordr-id']")
	private List<WebElement> OrderID;
	
	
	public  List<WebElement> getOrderID() {
		return OrderID;
	}
	
	@FindBy(xpath="//div[@class='ordr-info-wrapper']/p[2]")
	private List<WebElement> COurseType;
	
	
	public  List<WebElement> getCOurseType() {
		return COurseType;
	}
	
	@FindBy(xpath="//p[@class='failure-btn']")
	private List<WebElement> FailTransaction;
	
	
	public  List<WebElement> getFailTransaction() {
		return FailTransaction;
	}
	
	@FindBy(xpath = "//div[@class='navbar-wrapper']/div[contains(text(),'Impersonate')]")
	private WebElement Impersonate;
	
	
	public WebElement getImpersonate() {
		return Impersonate;
	}
	
	@FindBy(xpath = "//input[@placeholder='Enter mobile of user to impersonate']")
	private WebElement Impersonate_MobileInputBox;
	
	
	public WebElement getImpersonate_MobileInputBox() {
		return Impersonate_MobileInputBox;
	}
	
	@FindBy(xpath = "//button[contains(text(),'Impersonate Profile')]")
	private WebElement ImpersonateProfile_Button;
	
	
	public WebElement getImpersonateProfile_Button() {
		return ImpersonateProfile_Button;
	}
	
	@FindBy(xpath = "//div[@class='navbar-wrapper']/div[contains(text(),'My Exam')]")
	private WebElement MyExam_icon;
	
	
	public WebElement getMyExam_icon() {
		return MyExam_icon;
	}
	
	@FindBy(xpath = "//div[@class='navbar-wrapper']/div[contains(text(),'Account')]")
	private WebElement MyAccount_icon;
	
	
	public WebElement getMyAccount_icon() {
		return MyAccount_icon;
	}
	
	@FindBy(xpath = "//div[@class='navbar-wrapper']/div[contains(text(),'Transactions')]")
	private WebElement Transactions_icon;
	
	
	public WebElement getTransactions_icon() {
		return Transactions_icon;
	}
	
	@FindBy(xpath = "//button[contains(text(),'Impersonate Profile')]")
	private WebElement Impersonate_icon;
	
	
	public WebElement getImpersonate_icon() {
		return Impersonate_icon;
	}
	
	@FindBy(xpath = "//button[contains(text(),'Update Profile')]")
	private WebElement UpdateProfile_Button;
	
	
	public WebElement getUpdateProfile_Button() {
		return UpdateProfile_Button;
	}
	
	@FindBy(xpath = "//input[@placeholder='Enter your Name']")
	private WebElement NewYourName_input;
	
	
	public WebElement getNewYourName_input() {
		return NewYourName_input;
	}
	
	@FindBy(xpath = "//input[@placeholder='Enter your City']")
	private WebElement NewYourCity_input;
	
	
	public WebElement getNewYourCity_input() {
		return NewYourCity_input;
	}
	
	@FindBy(xpath = "//input[@placeholder='Enter your State']")
	private WebElement NewYourState_input;
	
	
	public WebElement getNewYourState_input() {
		return NewYourState_input;
	}
	
	@FindBy(xpath = "//div[@class='inputContainer']")
	private List<WebElement> ListOfExamPreferenceCOurses;

	public List<WebElement> getListOfExamPreferenceCOurses() {
		return ListOfExamPreferenceCOurses;
	}
	
	@FindBy(xpath = "//div[@class='App exam-modal']/div/div[@class='examContainer']/div/div")
	private List<WebElement> ListOfUpdateExamPreferenceCOurses;

	public List<WebElement> getListOfUpdateExamPreferenceCOurses() {
		return ListOfUpdateExamPreferenceCOurses;
	}
	
	@FindBy(xpath = "(//div[@class='grid-item']//button[contains(text(),'Update')])[1]")
	private WebElement Update_Button;
	
	
	public WebElement getUpdate_Button() {
		return Update_Button;
	}
	
	@FindBy(xpath = "(//div[@class='grid-item']//button[contains(text(),'Verify')])[2]")
	private WebElement VerifyOTP_Button_Mobile;
	
	
	public WebElement getVerifyOTP_Button_Mobile() {
		return VerifyOTP_Button_Mobile;
	}
	
}
