package pageObject;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CoursePage_OR {
	
	@FindBy(css = "div[class='title_wrapper'] div[class='title']")
	WebElement titleOfPageElement;
	
	public WebElement titleOfPage() {
		return titleOfPageElement;
	}
	
	@FindBy(css = "button[class='buy_now_btn btn btn-primary']")
	WebElement buyNoWebElement;
	
	public WebElement buyNowClick() {
		return buyNoWebElement;
	}
	
	@FindBy(xpath = "(//div[@class='course-package--details'])/span[@class='course-package--title']")
	List<WebElement> titles_BestValue;
	
	public List<WebElement> verifyTitle() {
		return titles_BestValue;
	}
	
	@FindBy(css = ".course-current-package--title")
	WebElement packageTitleElement;
	
	public WebElement titlePackage(){
		return packageTitleElement;
	}
	
	@FindBy(css = "div[class*='course-package package-']")
	List<WebElement> choosePackages;
	
	public List<WebElement> packagesChooseClick(){
		return choosePackages;
	}
	
	@FindBy(xpath = "//div[@class='card-title h5']")
	WebElement titleOffer;
	
	public WebElement titleCheck() {
		return titleOffer;
	}
	
	@FindBy(xpath = "//span[@class='course-package--offers-title']")
	WebElement titleOfferAvail;
	
	public WebElement titleAvailCheck() {
		return titleOfferAvail;
	}
	
	@FindBy(xpath = "//div[@class='course-package--offers']")
	WebElement promocodeElement;
	
	public WebElement promoClick(){
		return promocodeElement;
	}
	
	@FindBy(xpath = "(//button[@type='button'][text()='APPLY'])[1]")
	WebElement codeApply;
	
	public WebElement applyCode(){
		return codeApply;
	}
	
	@FindBy(xpath = "//a[normalize-space()='Remove']")
	WebElement removeCodeApply;
	
	public WebElement RApplyCodeClick(){
		return removeCodeApply;
	}
	
	@FindBy(css = "input[placeholder='Enter Coupon Code']")
	WebElement inputCodeElement;
	
	public WebElement inputCode() {
		return inputCodeElement;
	}
	
	@FindBy(css = "div[class='input-group'] button[type='button']")
	WebElement applycodeElement;
	
	public WebElement applyCodeMain() {
		return applycodeElement;
	}
	
	@FindBy(css = "button[class='btn btn-brand btn-block']")
	WebElement buyNowButtonElement;
	
	public WebElement buyNowMain() {
		return buyNowButtonElement;
	}
	
	@FindBy(css = "div[class='package-payment-details--typo'] span")
	WebElement viewDetailsElement;
	
	public WebElement viewDetails() {
		return  viewDetailsElement;
	}
	
	@FindBy(xpath = "(//div[@class='payment-method'])")
	List<WebElement> paymentMethod;
	
	public List<WebElement> payMethodClick() {
		return paymentMethod;
	}
	
	@FindBy(xpath = "//input[@id='orderBillName']")
	WebElement inputNameElement;
	
	public WebElement nameInfo() {
		return inputNameElement;
	}
	
	@FindBy(xpath = "//input[@id='orderBillAddress']")
	WebElement inputAddressElement;
	
	public WebElement addressInfo() {
		return inputAddressElement;
	}
	
	@FindBy(xpath = "//input[@id='orderBillZip']")
	WebElement inputPinCodeElement;
	
	public WebElement pincodeInfo() {
		return inputPinCodeElement;
	}
	
	@FindBy(xpath = "//input[@id='orderBillCity']")
	WebElement inputCityElement;
	
	public WebElement cityInfo() {
		return inputCityElement;
	}
	
	@FindBy(xpath = "//input[@id='orderBillState']")
	WebElement inputStateElement;
	
	public WebElement stateInfo() {
		return inputStateElement;
	}
	
	@FindBy(xpath = "//input[@id='orderBillTel']")
	WebElement inputNumberElement;
	
	public WebElement numberInfo() {
		return inputNumberElement;
	}
	
	@FindBy(xpath = "//input[@id='orderBillEmail']")
	WebElement inputEmailElement;
	
	public WebElement emailInfo() {
		return inputEmailElement;
	}
	
	@FindBy(xpath = "//select[@id='netBankingBank']")
	WebElement bankMethod;
	
	public WebElement bankMethods() {
		return bankMethod;
	}
	
	@FindBy(xpath = "//span[normalize-space()='Make Payment']")
	WebElement makePayElement;
	
	public WebElement payClick() {
		return makePayElement;
	}
	
	@FindBy(xpath = "//select[@name='PAID']")
	WebElement transactionStatus;
	
	public WebElement transactionStatusCheck() {
		return transactionStatus;
	}
	
	@FindBy(xpath = "//input[@value='Return To the Merchant Site']")
	WebElement returnMerchantSite;
	
	public WebElement merchantClick() {
		return returnMerchantSite;
	}
	
	@FindBy(xpath = "//h6[normalize-space()='Payment Successful']")
	WebElement payStatus;

	public WebElement statusPay() {
		return payStatus;
	}
	
	@FindBy(css = "button[class='libray-btn btn btn-primary']")
	WebElement libraryBtn;
	
	public WebElement libraryClick() {
		return libraryBtn;
	}
	
	@FindBy(xpath = "//div[@class='modal_content_wrapper row']")
	List<WebElement> loginRegisterBox;
	
	public List<WebElement> box(){
		return loginRegisterBox;
	}
	
}
