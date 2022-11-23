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

	@FindBy(xpath = "//button[text()='Buy Now']")
	List<WebElement> buyNoWebElement;

	public List<WebElement> buyNowClick() {
		return buyNoWebElement;
	}

	@FindBy(xpath = "(//div[@class='course-package--details'])/span[@class='course-package--title']")
	List<WebElement> titles_BestValue;

	public List<WebElement> verifyTitle() {
		return titles_BestValue;
	}

	@FindBy(css = ".course-current-package--title")
	WebElement packageTitleElement;

	public WebElement titlePackage() {
		return packageTitleElement;
	}

	@FindBy(css = "div[class*='course-package package-']")
	List<WebElement> choosePackages;

	public List<WebElement> packagesChooseClick() {
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

	public WebElement promoClick() {
		return promocodeElement;
	}

	@FindBy(xpath = "(//button[@type='button'][text()='APPLY'])")
	List<WebElement> codeApplyList;

	public List<WebElement> applyCode() {
		return codeApplyList;
	}

	@FindBy(xpath = "//a[normalize-space()='Remove']")
	WebElement removeCodeApply;

	public WebElement RApplyCodeClick() {
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

	@FindBy(css = ".btn-brand.btn-block")
	WebElement buyNowButtonElement;

	public WebElement buyNowMain() {
		return buyNowButtonElement;
	}

	@FindBy(css = "div[class='package-payment-details--typo'] span")
	WebElement viewDetailsElement;

	public WebElement viewDetails() {
		return viewDetailsElement;
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

	@FindBy(css = ".payment-h")
	WebElement payStatus;

	public WebElement statusPay() {
		return payStatus;
	}

	@FindBy(css = "button[class='libray-btn btn btn-primary']")
	WebElement libraryBtn;

	public WebElement golibrary() {
		return libraryBtn;
	}

	@FindBy(xpath = "//div[@class='modal_content_wrapper row']")
	List<WebElement> loginRegisterBox;

	public List<WebElement> box() {
		return loginRegisterBox;
	}

	@FindBy(id = "ptm-nb")
	WebElement netBankElement;

	public WebElement netbankingInPaytm() {
		return netBankElement;
	}

	@FindBy(css = ".btn.btn-primary.w100.pos-r._2fNU")
	WebElement payBtn;

	public WebElement payBtnClick() {
		return payBtn;
	}

	@FindBy(xpath = "//button[@class='btn btnd']")
	WebElement successBtnInPaytm;

	public WebElement successInPaytm() {
		return successBtnInPaytm;
	}

	@FindBy(xpath = "//button[@class='btn btnl']")
	WebElement failureBtnInPaytm;

	public WebElement failureInPaytm() {
		return failureBtnInPaytm;
	}

	@FindBy(xpath = "//div[@class='modal-content']")
	List<WebElement> popUps;

	public List<WebElement> sizePopUp() {
		return popUps;
	}

	@FindBy(xpath = "//img[@class='close_img']")
	WebElement startWindowpopupClose;

	public WebElement popUpClose() {
		return startWindowpopupClose;
	}

	@FindBy(xpath = "(//span[@class='course-package--offer-name'])[1]")
	WebElement couponElement;

	public WebElement couponCodeElement() {
		return couponElement;
	}

	@FindBy(xpath = "(//span[@class='course-package--selling-price'])")
	List<WebElement> packageAmountElements;

	public List<WebElement> packageAmounts() {
		return packageAmountElements;
	}

	@FindBy(xpath = "//span[@class='book-detail--selling-price']")
	WebElement BookpackageAmountElements;

	public WebElement getBookpackageAmountElements() {
		return BookpackageAmountElements;
	}

	@FindBy(xpath = "//span[@class='course-package--offer-amount']")
	WebElement removeCouponAmount;

	public WebElement removeAmount() {
		return removeCouponAmount;
	}

	@FindBy(xpath = "//b[1]")
	WebElement amountPayCheckout;

	public WebElement amountPayableCheckout() {
		return amountPayCheckout;
	}

	@FindBy(xpath = "//div[@class='package-payment-details--breakdown']//div[2]")
	WebElement amountDiscountElement;

	public WebElement amountPayDiscountElement() {
		return amountDiscountElement;
	}

	@FindBy(xpath = "//div[@class='package-payment-details--breakdown']//div[2]//span[2]")
	WebElement amountDiscountPay;

	public WebElement amountDiscount() {
		return amountDiscountPay;
	}

	@FindBy(xpath = "//div[@class='package-payment-details--breakdown-total']//span[2]")
	WebElement totalAmountCheckoutElement;

	public WebElement totalAmountCheckout() {
		return totalAmountCheckoutElement;
	}

	@FindBy(xpath = "(//input[@class='form-control'])[1]")
	WebElement InputName;

	public WebElement getInputName() {
		return InputName;
	}

	@FindBy(xpath = "(//input[@class='form-control'])[2]")
	WebElement InputPhone_Number;

	public WebElement getInputPhone_Number() {
		return InputPhone_Number;
	}

	@FindBy(xpath = "(//input[@class='form-control'])[3]")
	WebElement InputEmail;

	public WebElement getInputEmail() {
		return InputEmail;
	}

	@FindBy(xpath = "(//input[@class='form-control'])[4]")
	WebElement InputAddress;

	public WebElement getInputAddress() {
		return InputAddress;
	}

	@FindBy(xpath = "(//input[@class='form-control'])[5]")
	WebElement InputCity;

	public WebElement getInputCity() {
		return InputCity;
	}

	@FindBy(xpath = "(//input[@class='form-control'])[6]")
	WebElement InputState;

	public WebElement getInputState() {
		return InputState;
	}

	@FindBy(xpath = "(//input[@class='form-control'])[7]")
	WebElement InputPincode;

	public WebElement getInputPincode() {
		return InputPincode;
	}

	@FindBy(xpath = "//button[@class='mt-auto btn btn-brand btn-block']")
	WebElement BookBuyNow;

	public WebElement getBookBuyNow() {
		return BookBuyNow;
	}

	@FindBy(css = ".course-packages>div")
	List<WebElement> choosePackagelist;

	public List<WebElement> getChoosePackagelist() {
		return choosePackagelist;
	}

	@FindBy(css = ".course-package--title")
	List<WebElement> choosePackageTitle;

	public List<WebElement> getChoosePackageTitle() {
		return choosePackageTitle;
	}

	public List<WebElement> getChoosePackageValidity() {
		return choosePackageValidity;
	}

	public List<WebElement> getChoosePackagePrice() {
		return choosePackagePrice;
	}

	@FindBy(css = ".course-package--validity")
	List<WebElement> choosePackageValidity;
	@FindBy(css = ".course-package--price")
	List<WebElement> choosePackagePrice;

	@FindBy(css = ".course-package--feature-label")
	List<WebElement> listPackageFeature;

	public List<WebElement> getListPackageFeature() {
		return listPackageFeature;
	}

	@FindBy(css = ".course-current-package--title")
	List<WebElement> listCurrentPackageTitle;

	public List<WebElement> getListCurrentPackageTitle() {
		return listCurrentPackageTitle;
	}

	@FindBy(id = "book-checkout-form")
	List<WebElement> listBookingForm;

	public List<WebElement> getListBookingForm() {
		return listBookingForm;
	}

	public List<WebElement> getListBookingFormName() {
		return listBookingFormName;
	}

	public List<WebElement> getListBookingFormPhoneNo() {
		return listBookingFormPhoneNo;
	}

	public List<WebElement> getListBookingFormEmail() {
		return listBookingFormEmail;
	}

	public List<WebElement> getListBookingFormAddress() {
		return listBookingFormAddress;
	}

	public List<WebElement> getListBookingFormPinCode() {
		return listBookingFormPinCode;
	}

	public List<WebElement> getListBookingFormCity() {
		return listBookingFormCity;
	}

	public List<WebElement> getListBookingFormState() {
		return listBookingFormState;
	}

	@FindBy(name = "name")
	List<WebElement> listBookingFormName;

	@FindBy(name = "phone")
	List<WebElement> listBookingFormPhoneNo;

	@FindBy(name = "email")
	List<WebElement> listBookingFormEmail;

	@FindBy(name = "address")
	List<WebElement> listBookingFormAddress;

	@FindBy(name = "pin_code")
	List<WebElement> listBookingFormPinCode;

	@FindBy(name = "city")
	List<WebElement> listBookingFormCity;

	@FindBy(name = "state")
	List<WebElement> listBookingFormState;

	@FindBy(css = ".package-payment-details--breakdown-item")
	List<WebElement> listBreakDownItem;

	public List<WebElement> getListBreakDownItem() {
		return listBreakDownItem;
	}

	@FindBy(css = ".book-details")
	List<WebElement> listBookDetailOnPackagePopUp;

	public List<WebElement> getListBookDetailOnPackagePopUp() {
		return listBookDetailOnPackagePopUp;
	}

	@FindBy(xpath = "//span[@class='course-package--offers-title']")
	WebElement noOfOfferTextElement;

	public WebElement noOfOfferText() {
		return noOfOfferTextElement;
	}

	@FindBy(css = ".btn.btn-primary.w100.pos-r._2fNU")
	WebElement retryPaymentBtnElement;

	public WebElement retryPaymentBtn() {
		return retryPaymentBtnElement;
	}
	
	@FindBy(css = "button[class='retry-payment-btn btn btn-primary']")
	WebElement retryPaymentStatusBtnElement;

	public WebElement retryPaymentStatusBtn() {
		return retryPaymentStatusBtnElement;
	}
	
	@FindBy(css = "button[class='btn btn-link']")
	WebElement closeCheckoutBoxElement;
	
	public WebElement closeCheckoutBox() {
		return closeCheckoutBoxElement;
	}
	
	@FindBy(css = ".course-package--offers.applied")
	List<WebElement> listOfferApplied;

	public List<WebElement> getListOfferApplied() {
		return listOfferApplied;
	}
	
	@FindBy(css = ".ptm-go-back-btn")
	WebElement arrowLeft;

	public WebElement getArrowLeft() {
		return arrowLeft;
	}
	
	@FindBy(css = ".ptm-cp-btn.ptm-cp-y-btn")
	List<WebElement> listNoYesButtonPopUp;

	public List<WebElement> getListNoYesButtonPopUp() {
		return listNoYesButtonPopUp;
	}
	
	@FindBy(xpath = "//input[@name='pin_code']")
	WebElement inputPincodeInBookElement;
	
	public WebElement inputPincodeInBook() {
		return inputPincodeInBookElement;
	}
	
	@FindBy(xpath = "//iframe[@id='moe-onsite-campaign-63440175126a417df314e181']")
	WebElement popUpFrameElement;
	
	public WebElement popUpFrame() {
		return popUpFrameElement;
	}
	
	@FindBy(xpath = "//button[@id='close-icon']")
	WebElement closePopUpElement;
	
	public WebElement closePopUp() {
		return closePopUpElement;
	}
}
