package pageObject;

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
	
	@FindBy(xpath = "(//label[@class='form-label'])[2]")
	private WebElement YourCity_text;
	
	
	public WebElement getYourCity_text() {
		return YourCity_text;
	}
	
	@FindBy(xpath = "(//input[@type='text'])[3]")
	private WebElement YourCity_input;
	
	
	public WebElement getYourCity_input() {
		return YourCity_input;
	}
	
	@FindBy(xpath = "(//label[@class='form-label'])[3]")
	private WebElement YourAddress_text;
	
	
	public WebElement getYourAddress_text() {
		return YourAddress_text;
	}
	
	@FindBy(xpath = "(//input[@type='text'])[4]")
	private WebElement YourAddress_input;
	
	
	public WebElement getYourAddress_input() {
		return YourAddress_input;
	}
	
	@FindBy(xpath = "(//label[@class='form-label'])[4]")
	private WebElement YourState_text;
	
	
	public WebElement getYourState_text() {
		return YourState_text;
	}
	
	@FindBy(xpath = "(//input[@type='text'])[5]")
	private WebElement YourState_input;
	
	
	public WebElement getYourState_input() {
		return YourState_input;
	}
	
	@FindBy(xpath = "(//label[@class='form-label'])[5]")
	private WebElement YourPinCode_text;
	
	
	public WebElement getYourPinCode_text() {
		return YourPinCode_text;
	}
	
	@FindBy(xpath = "(//input[@class='form-control'])[5]")
	private WebElement YourPinCode_input;
	
	
	public WebElement getYourPinCode_input() {
		return YourPinCode_input;
	}
	
	@FindBy(css = "button[class='btn btn-primary']")
	private WebElement UpdateProfile_button;
	
	
	public WebElement getUpdateProfile_button() {
		return UpdateProfile_button;
	}
	
}
