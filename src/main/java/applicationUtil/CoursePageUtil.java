package applicationUtil;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pageObject.CoursePage_OR;
import pojo.TestData;
import util.Common_Function;
import util.ConfigFileReader;

public class CoursePageUtil {

	CoursePage_OR coursePageORobj;
	public List<String> coursePageMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();
	ConfigFileReader rConfigFileReader;
	HomePageUtil util;
	LibraryPageUtil librayUtilObj;

	public CoursePageUtil(WebDriver driver) {
		coursePageORobj = new CoursePage_OR();
		PageFactory.initElements(driver, coursePageORobj);
	}

	public boolean verifyCoursePurchase(WebDriver driver, TestData testData) {
		boolean result = true;
		util = new HomePageUtil(driver);

		try {
			// for existing user
			if (testData.getIsUserGuest() == true) {
				
				// handle the popup
				int no = coursePageORobj.sizePopUp().size();
				if (no > 0) {
					coursePageORobj.popUpClose().click();
				}

				result = util.verifySearch(driver, testData);
				if (!result) {
					coursePageMsgList.addAll(util.homePageMsgList);
					return result;
				}

				result = verifyClickBuy(testData.getIsUserGuest(), driver);
				if (!result) {
					return result;
				}

				result = util.verifyLogin(driver, ConfigFileReader.strUserMobileNumber);
				if (!result) {
					coursePageMsgList.addAll(util.homePageMsgList);
					return result;
				}

				result = verifyClickBuy(testData.getIsUserGuest(), driver);
				if (!result) {
					return result;
				}

				result = verifyPackages(testData.getOfferName());
				if (!result) {
					return result;
				}

				result = verifyClickOfferPromo(testData.getCouponCode());
				if (!result) {
					return result;
				}

				result = verifyPayCheckout(testData.getPaymentMethod(), driver, testData,
						testData.getbankNameForPaytm());
				if (!result) {
					return result;
				}

				result = verifyLibraryCourse(driver, testData);
				if (!result) {
					return result;
				}

			}
			// for new user
			else {

				// handle the popup
				int no = coursePageORobj.sizePopUp().size();
				if (no > 0) {
					coursePageORobj.popUpClose().click();
				}

				result = util.verifySignUp(driver);
				if (!result) {
					coursePageMsgList.addAll(util.homePageMsgList);
					return result;
				}

				result = util.verifySearch(driver, testData);
				if (!result) {
					coursePageMsgList.addAll(util.homePageMsgList);
					return result;
				}

				result = verifyClickBuy(testData.getIsUserGuest(), driver);
				if (!result) {
					return result;
				}

				result = verifyPackages(testData.getOfferName());
				if (!result) {
					return result;
				}

				result = verifyClickOfferPromo(testData.getCouponCode());
				if (!result) {
					return result;
				}

				result = verifyPayCheckout(testData.getPaymentMethod(), driver, testData,
						testData.getbankNameForPaytm());
				if (!result) {
					return result;
				}

				result = verifyLibraryCourse(driver, testData);
				if (!result) {
					return result;
				}
			}

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("verifyCoursePurchase_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyLibraryCourse(WebDriver driver, TestData testData) {
		boolean result = true;

		// call goLibaray function
		result = goToLibrary();
		if (!result) {
			return result;
		}

		// verify in library the course
		librayUtilObj = new LibraryPageUtil(driver);
		result = librayUtilObj.verifyPuchasedCourseOnMyLibrary(driver, testData);
		if (!result) {
			coursePageMsgList.addAll(librayUtilObj.libraryPageMsgList);
			return result;
		}

		return result;

	}

	public boolean verifyClickBuy(boolean isGuestUser, WebDriver driver) {
		boolean result = true;

		try {
			String BuyText = coursePageORobj.buyNowClick().getText();

			if (BuyText.equalsIgnoreCase("Buy Now")) {
				coursePageORobj.buyNowClick().click();
			} else {
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

			// It checks all the offers like premium, gold, silver

			List<WebElement> coursePackages = coursePageORobj.packagesChooseClick();
			for (int i = 0; i < coursePackages.size(); i++) {
				coursePackages.get(i).click();
				String bestValueTitleString = links.get(i).getText().toLowerCase();
				String packageTitleString = packageTitleElement.getText().toLowerCase();

				if (bestValueTitleString.equalsIgnoreCase(packageTitleString)) {
					result = true;
				} else {
					coursePageMsgList.add("The course offer given is not matching offer ");
					return false;
				}
			}

			// It clicks on the offer desired
			for (int i = 0; i < coursePackages.size(); i++) {
				String bestValueTitleString = links.get(i).getText();
				if (bestValueTitleString.equalsIgnoreCase(offerName)) {
					coursePackages.get(i).click();
					return true;
				}
			}
			coursePageMsgList.add("The offer is not present for this course");
			result = false;

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("verficationOfPackages_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyClickOfferPromo(String couponCode) {
		boolean result = true;
		try {

			coursePageORobj.promoClick().click();

			coursePageORobj.applyCode().click();

			coursePageORobj.RApplyCodeClick().click();

			coursePageORobj.promoClick().click();

			coursePageORobj.inputCode().sendKeys(couponCode);

			coursePageORobj.applyCodeMain().click();

			if ((ConfigFileReader.strEnv).equalsIgnoreCase("dev")) {

				result = true;
				coursePageORobj.buyNowMain().click();

			} else if ((ConfigFileReader.strEnv).equalsIgnoreCase("prod")) {

				result = false;
				coursePageMsgList.add("The prod is working fine and closes before payCheckOut");
			}

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("clickOfferPromoBtn_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyPayCheckout(String paymentMethod, WebDriver driver, TestData testData, String bankName) {
		boolean result = true;
		try {

			coursePageORobj.viewDetails().click();

			List<WebElement> links = coursePageORobj.payMethodClick();

			for (int i = 0; i < links.size(); i++) {

				String methodString = links.get(i).getText();

				if (methodString.equalsIgnoreCase(paymentMethod) && paymentMethod.equalsIgnoreCase("Netbank")) {
					links.get(i).click();

					result = verifyNetbankMethod(driver, testData);
					if (!result) {
						return result;
					}
				} else if (methodString.equalsIgnoreCase(paymentMethod) && paymentMethod.equalsIgnoreCase("Paytm")) {
					links.get(i).click();

					result = verifyPaytmMethod(driver, testData, bankName);
					if (!result) {
						return result;
					}
				} else if (methodString.equalsIgnoreCase(paymentMethod)) {
					links.get(i).click();
					verifyOtherPayMethods();
				} else {
					result = false;
				}
			}
			if (!result) {
				coursePageMsgList.add("There is no matching payment method found");
				return false;
			}

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("Checkout_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyNetbankMethod(WebDriver driver, TestData testData) {
		boolean result = true;
		try {

			result = verifyBillingInfo(testData.getNameBill(), testData.getAddressBill(), testData.getZipBill(),
					testData.getCityBill(), testData.getStateBill(), testData.getNumberBill(), testData.getEmailBill());
			if (!result) {
				return result;
			}

			result = verifyMerchantStatus();
			if (!result) {
				return result;
			}

			result = verifyPaymentStatus();
			if (!result) {
				return result;
			}

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("verifyNetbankMethod_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyPaytmMethod(WebDriver driver, TestData testData, String bankNameFromUser) {
		boolean result = true;
		try {
			coursePageORobj.netbankingInPaytm().click();
			coursePageORobj.payBtnClick().click();
			coursePageORobj.successInPaytm().click();

			result = verifyPaymentStatus();
			if (!result) {
				return result;
			}

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("verifyPaytmMethod_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyOtherPayMethods() {
		boolean result = true;
		try {

			coursePageMsgList.add("The payment method is not working");
			return false;

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("verifyOtherPayMethod_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyBillingInfo(String name, String address, String zip, String city, String state, String number,
			String email) {
		boolean result = true;
		try {
			coursePageORobj.nameInfo().clear();
			coursePageORobj.nameInfo().sendKeys(name);

			coursePageORobj.addressInfo().clear();
			coursePageORobj.addressInfo().sendKeys(address);

			coursePageORobj.pincodeInfo().clear();
			coursePageORobj.pincodeInfo().sendKeys(zip);

			coursePageORobj.cityInfo().clear();
			coursePageORobj.cityInfo().sendKeys(city);

			coursePageORobj.stateInfo().clear();
			coursePageORobj.stateInfo().sendKeys(state);

			coursePageORobj.numberInfo().clear();
			coursePageORobj.numberInfo().sendKeys(number);

			coursePageORobj.emailInfo().clear();
			coursePageORobj.emailInfo().sendKeys(email);

			WebElement methods = coursePageORobj.bankMethods();
			Select select = new Select(methods);
			select.selectByIndex(1);

			coursePageORobj.payClick().click();

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("BiilingInfo_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyMerchantStatus() {
		boolean result = true;
		try {
			WebElement method = coursePageORobj.transactionStatusCheck();
			Select select = new Select(method);
			String status = select.getFirstSelectedOption().getText();
			if (status.equalsIgnoreCase("Y")) {
				coursePageORobj.merchantClick().click();
			} else {
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

			if (expectedPayStatus.equalsIgnoreCase(actualPayStatus)) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("PaymentStatus_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean goToLibrary() {
		boolean result = true;
		try {
			coursePageORobj.golibrary().click();

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("Library Button not working " + e.getMessage());
		}
		return result;
	}
}
