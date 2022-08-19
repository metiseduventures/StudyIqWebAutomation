package applicationUtil;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import pageObject.CoursePage_OR;
import pojo.TestData;
import util.Common_Function;
import util.ConfigFileReader;

public class CoursePageUtil {
	
	CoursePage_OR coursePageORobj;
	public List<String> coursePageMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();
	ConfigFileReader rConfigFileReader;
	
	public CoursePageUtil(WebDriver driver) {
		coursePageORobj = new CoursePage_OR();
		PageFactory.initElements(driver, coursePageORobj);
	}
	
	public boolean verifyProcessBuy(WebDriver driver, TestData testData) {
		boolean result = true;
		try {
			
			Thread.sleep(18000);
					
			result = verifyClickBuy();
			if(!result) {
				return result;
			}
			
			result = verifyPackages(testData.getOfferName());
			if(!result) {
				return result;
			}
			
			result = verifyClickOfferPromo(testData.getCouponCode());
			if(!result) {
				return result;
			}
			
			result = verifyPayCheckout(testData.getPaymentMethod());
			if(!result) {
				return result;
			}
			
			result = verifyBillingInfo(testData.getNameBill(), testData.getAddressBill(),testData.getZipBill(),
					testData.getCityBill(), testData.getStateBill(), testData.getNumberBill(), testData.getEmailBill());
			if(!result) {
				return result;
			}
			
			result = verifyMerchantStatus();
			if(!result) {
				return result;
			}
			
			result= verifyPaymentStatus();
			if(!result) {
				return result;
			}
			
		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("verifyBuy_Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean verifyClickBuy() {
		boolean result =true;
		
		try {
			String BuyText = coursePageORobj.buyNowClick().getText();
			
			if(BuyText.equalsIgnoreCase("Buy Now")) {
				coursePageORobj.buyNowClick().click();
				Thread.sleep(2000);
			}
			else {
				result = false;
				coursePageMsgList.add("This is not buy now button");
			}
			
		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("click&VerifyBuyBtn_Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean verifyPackages(String offerName) {
		boolean result = true;
		
		try {
			List<WebElement> links = coursePageORobj.verifyTitle();
			WebElement packageTitleElement = coursePageORobj.titlePackage();

			//It checks all the offers like premium, gold, silver
			
			List<WebElement> coursePackages = coursePageORobj.packagesChooseClick();
			for (int i = 0; i < coursePackages.size(); i++) {
				coursePackages.get(i).click();
				String bestValueTitleString = links.get(i).getText().toLowerCase();
				String packageTitleString = packageTitleElement.getText().toLowerCase();
				
				Assert.assertEquals(bestValueTitleString, packageTitleString);
				Thread.sleep(2000);
			}
			
			//It clicks on the offer desired
			for(int i=0;i<coursePackages.size();i++) {
				String bestValueTitleString = links.get(i).getText();
				if(bestValueTitleString.equalsIgnoreCase(offerName)) {
					coursePackages.get(i).click();
					return true;
				}
			}
			result=false;
			
		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("verficationOfPackages_Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean verifyClickOfferPromo(String couponCode) {
		boolean result = true;
		try {
			Thread.sleep(2000);
			coursePageORobj.promoClick().click();
			Thread.sleep(2000);
			coursePageORobj.applyCode().click();
			Thread.sleep(2000);
			coursePageORobj.RApplyCodeClick().click();
			Thread.sleep(2000);
			coursePageORobj.promoClick().click();
			Thread.sleep(2000);
			coursePageORobj.inputCode().sendKeys(couponCode);
			Thread.sleep(2000);
			coursePageORobj.applyCodeMain().click();
			Thread.sleep(2000);
			coursePageORobj.buyNowMain().click();
			Thread.sleep(2000);

			
		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("clickOfferPromoBtn_Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean verifyPayCheckout(String paymentMethod) {
		boolean result=true;
		try {
			
			coursePageORobj.viewDetails().click();
			Thread.sleep(2000);

			List<WebElement> links = coursePageORobj.payMethodClick();
			for(int i=0;i<links.size();i++) {
				String methodString = links.get(i).getText();
				if(methodString.equalsIgnoreCase(paymentMethod)) {
					links.get(i).click();
				}
			}
		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("Checkout_Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean verifyBillingInfo(String name,String address, String zip, String city, String state, String number, String email) {
		boolean result = true;
		try {
			coursePageORobj.nameInfo().clear();
			coursePageORobj.nameInfo().sendKeys(name);
			Thread.sleep(2000);
			
			coursePageORobj.addressInfo().clear();
			coursePageORobj.addressInfo().sendKeys(address);
			Thread.sleep(2000);

			coursePageORobj.pincodeInfo().clear();
			coursePageORobj.pincodeInfo().sendKeys(zip);
			Thread.sleep(2000);

			coursePageORobj.cityInfo().clear();
			coursePageORobj.cityInfo().sendKeys(city);
			Thread.sleep(2000);

			coursePageORobj.stateInfo().clear();
			coursePageORobj.stateInfo().sendKeys(state);
			Thread.sleep(2000);

			coursePageORobj.numberInfo().clear();
			coursePageORobj.numberInfo().sendKeys(number);
			Thread.sleep(2000);

			coursePageORobj.emailInfo().clear();
			coursePageORobj.emailInfo().sendKeys(email);
			Thread.sleep(2000);

			WebElement methods = coursePageORobj.bankMethods();
			Select select = new Select(methods);
			select.selectByIndex(1);
			Thread.sleep(2000);

			
			coursePageORobj.payClick().click();
			Thread.sleep(2000);

			
		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("BiilingInfo_Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean verifyMerchantStatus() {
		boolean result= true;
		try {
			WebElement method = coursePageORobj.transactionStatusCheck();
			Select select = new Select(method);
			String status = select.getFirstSelectedOption().getText();
			if(status.equalsIgnoreCase("Y")) {
				coursePageORobj.merchantClick().click();
			}
			else {
				return false;
			}
		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("MerchantSite_Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean verifyPaymentStatus() {
		boolean result = true;
		try {
			String expectedPayStatus = "Payment Successful";
			String actualPayStatus = coursePageORobj.statusPay().getText();

			if(expectedPayStatus.equalsIgnoreCase(actualPayStatus)) {
				coursePageORobj.libraryClick().click();
			}else {
				return false;
			}
			
		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("PaymentStatus_Exception: " + e.getMessage());
		}
		return result;
	}
}
