package applicationUtil;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import apiUtil.CourseApiUtil;
import pageObject.CoursePage_OR;
import pageObject.HomePage_OR;
import pageObject.LibraryPage_OR;
import pojo.TestData;
import pojo.courseView.CourseView;
import util.Common_Function;
import util.ConfigFileReader;

public class CoursePageUtil {

	CoursePage_OR coursePageORobj;
	LibraryPage_OR libraryPage_OR;
	HomePage_OR homePageORObj;
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
		librayUtilObj = new LibraryPageUtil(driver);
		int no;
		String strCourseSlug = null;
		try {
			// for guest user
			if (testData.getIsUserGuest()) {
				if (testData.getCourseType().contains("smart")) {
					result = util.clickOnSmartCourseOnHomePage(driver);
					if (!result) {
						coursePageMsgList.addAll(util.homePageMsgList);
						return result;
					}
				} else if (testData.getCourseType().contains("micro")) {
					result = util.clickOnMicroCourseOnHomePage(driver);
					if (!result) {
						coursePageMsgList.addAll(util.homePageMsgList);
						return result;
					}
				} else if (testData.getCourseType().contains("books")) {
					result = util.clickOnBookOnHomePage(driver);
					if (!result) {
						coursePageMsgList.addAll(util.homePageMsgList);
						return result;
					}
				}
				strCourseSlug = driver.getCurrentUrl().split("course-detail/")[1];
				System.out.println("strCourseSlug:" + strCourseSlug);
				result = verifyClickBuy(driver, false);
				if (!result) {
					return result;
				}
				result = util.doSignUp();
				if (!result) {
					coursePageMsgList.add("Fail Register");
					return result;
				}
				result = selectExamPrefrences(driver);
				if (!result) {
					return result;
				}
				result = verifyClickBuy(driver, true);
				if (!result) {
					return result;
				}
				result = verifyPackages(strCourseSlug);
				if (!result) {
					return result;
				}
				result = verifyClickOfferPromo(testData.getOfferName());
				if (!result) {
					return result;
				}
				result = verifyPayCheckout();
				if (!result) {
					return result;
				}
				if (!(ConfigFileReader.strEnv).equalsIgnoreCase("prod")) {

					result = verifyClickPay(testData.getPaymentMethod(), driver, testData,
							testData.getbankNameForPaytm());
					if (!result) {
						return result;
					}

					result = verifyLibraryCourse(driver, testData);
					if (!result) {
						return result;
					}

				} else if ((ConfigFileReader.strEnv).equalsIgnoreCase("prod")) {

					System.out.println("The envirnonment is production, everything working fine");

				} else {
					coursePageMsgList.add("The envirnoment is different from dev and prod");
					return false;
				}

			}
			// for new user
			else {

				// handle the pop up
				no = coursePageORobj.sizePopUp().size();
				if (no > 0) {
					coursePageORobj.popUpClose().click();
				}
				result = util.doSignUp();
				if (!result) {
					coursePageMsgList.addAll(util.homePageMsgList);
					return result;
				}
				result = util.clickOnCourseOnHomePage(driver);
				if (!result) {
					coursePageMsgList.addAll(util.homePageMsgList);
					return result;
				}
				result = selectExamPrefrences(driver);
				if (!result) {
					return result;
				}
				result = verifyClickBuy(driver, true);
				if (!result) {
					return result;
				}
				result = verifyPackages(strCourseSlug);
				if (!result) {
					return result;
				}
				result = verifyClickOfferPromo(testData.getOfferName());
				if (!result) {
					return result;
				}
				result = verifyPayCheckout();
				if (!result) {
					return result;
				}

				if (!(ConfigFileReader.strEnv).equalsIgnoreCase("prod")) {
					result = verifyClickPay(testData.getPaymentMethod(), driver, testData,
							testData.getbankNameForPaytm());
					if (!result) {
						return result;
					}
					result = verifyLibraryCourse(driver, testData);
					if (!result) {
						return result;
					}

				} else if ((ConfigFileReader.strEnv).equalsIgnoreCase("prod")) {

					System.out.println("The envirnonment is production, everything working fine");

				} else {
					coursePageMsgList.add("The envirnoment is different from dev and prod");
					return false;
				}
			}

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("verifyCoursePurchase_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyLibraryCourse(WebDriver driver, TestData testData) throws InterruptedException {
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

	public boolean verifyClickBuy(WebDriver driver, boolean isLoggedInUser) {
		boolean result = true;

		try {
			String BuyText = coursePageORobj.buyNowClick().getText();

			if (BuyText.equalsIgnoreCase("Buy Now")) {
				cfObj.commonClick(coursePageORobj.buyNowClick());
			} else {

				coursePageMsgList.add("This is not buy now button");
				return false;
			}

			if (isLoggedInUser) {

				// wait for verify package pop up to be opened
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".modal-title", "css", 30);
				if (!result) {
					coursePageMsgList
							.add("Verify Choose Package pop up is not opened when click on buy now for loged in user ");
					return result;
				}

			} else {
				// Wait for mobile number pop up
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//input[@placeholder='Mobile Number']", "xpath", 30);
				if (!result) {
					coursePageMsgList.add("Mobile number pop up is not visible when click on buy now for guest user");
					return result;
				}
			}
		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("click&VerifyBuyBtn_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyPackages(String strCourseSlug) {
		boolean result = true;
		CourseView courseViewObj;
		CourseApiUtil courseApiUtilObj;

		try {
			courseApiUtilObj = new CourseApiUtil();
			courseViewObj = courseApiUtilObj.getCourseViewData(strCourseSlug);
			System.out.println(courseViewObj.getData().getPriceInfo());

			result = verifyPackageType(courseViewObj);
			if (!result) {
				return result;
			}

			result = verifyPackageFeature(courseViewObj);
			if (!result) {
				return result;
			}
		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("verficationOfPackages_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyClickOfferPromo(String offerName) {
		boolean result = true;
		try {

			List<WebElement> links = coursePageORobj.verifyTitle();
			List<WebElement> coursePackages = coursePageORobj.packagesChooseClick();

			// It clicks on the offer desired and checks
			for (int i = 0; i < coursePackages.size(); i++) {
				String bestValueTitleString = links.get(i).getText();
				if (bestValueTitleString.equalsIgnoreCase(offerName)) {

					cfObj.commonClick(coursePackages.get(i));

					String packageAmountString = coursePageORobj.packageAmounts().get(i).getText();
					Double packageAmount = amountCorrectFormat(packageAmountString);

					cfObj.commonClick(coursePageORobj.promoClick());

					cfObj.commonClick(coursePageORobj.applyCode());

					String couponCode = coursePageORobj.couponCodeElement().getText();

					cfObj.commonClick(coursePageORobj.RApplyCodeClick());

					cfObj.commonClick(coursePageORobj.promoClick());

					cfObj.commonSetTextTextBox(coursePageORobj.inputCode(), "studyIQ");

					cfObj.commonClick(coursePageORobj.applyCodeMain());

					cfObj.commonSetTextTextBox(coursePageORobj.inputCode(), couponCode);

					cfObj.commonClick(coursePageORobj.applyCodeMain());

					String removeCouponAmountString = coursePageORobj.removeAmount().getText();
					Double removeCouponAmount = amountCorrectFormat(removeCouponAmountString);

					String afterpackageAmountString = coursePageORobj.packageAmounts().get(i).getText();
					Double afterPackageAmount = amountCorrectFormat(afterpackageAmountString);

					for (int j = 1; j < coursePackages.size(); j++) {
						cfObj.commonClick(coursePackages.get(j));
					}

					if (packageAmount == afterPackageAmount + removeCouponAmount) {
						cfObj.commonClick(coursePageORobj.buyNowMain());
						return true;
					} else {
						coursePageMsgList.add("The amount is not same of packages before and after");
						return false;
					}
				}
			}

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("clickOfferPromoBtn_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyBookClickOfferPromo(TestData testData) {
		boolean result = true;
		try {

			result = cfObj.commonSetTextTextBox(coursePageORobj.getInputName(), testData.getNameBill());
			if (!result) {
				coursePageMsgList.add("Name Input is not Working");
			}
			result = cfObj.commonSetTextTextBox(coursePageORobj.getInputPhone_Number(), testData.getNumberBill());
			if (!result) {
				coursePageMsgList.add("Phone_Number Input is not Working");
			}

			result = cfObj.commonSetTextTextBox(coursePageORobj.getInputEmail(), testData.getEmailBill());
			if (!result) {
				coursePageMsgList.add("Email Input is not Working");
			}

			result = cfObj.commonSetTextTextBox(coursePageORobj.getInputAddress(), testData.getAddressBill());
			if (!result) {
				coursePageMsgList.add("Address Input is not Working");
			}

			result = cfObj.commonSetTextTextBox(coursePageORobj.getInputCity(), testData.getCityBill());
			if (!result) {
				coursePageMsgList.add("City Input is not Working");
			}

			result = cfObj.commonSetTextTextBox(coursePageORobj.getInputState(), testData.getStateBill());
			if (!result) {
				coursePageMsgList.add("State Input is not Working");
			}

			result = cfObj.commonSetTextTextBox(coursePageORobj.getInputPincode(), testData.getZipBill());
			if (!result) {
				coursePageMsgList.add("Pincode Input is not Working");
			}

			String packageAmountString = coursePageORobj.getBookpackageAmountElements().getText();
			Double packageAmount = amountCorrectFormat(packageAmountString);

			cfObj.commonClick(coursePageORobj.promoClick());

			cfObj.commonClick(coursePageORobj.applyCode());

			String couponCode = coursePageORobj.couponCodeElement().getText();

			cfObj.commonClick(coursePageORobj.RApplyCodeClick());

			cfObj.commonClick(coursePageORobj.promoClick());

			cfObj.commonSetTextTextBox(coursePageORobj.inputCode(), couponCode);

			cfObj.commonClick(coursePageORobj.applyCodeMain());

			String removeCouponAmountString = coursePageORobj.removeAmount().getText();
			Double removeCouponAmount = amountCorrectFormat(removeCouponAmountString);

			String afterpackageAmountString = coursePageORobj.getBookpackageAmountElements().getText();
			Double afterPackageAmount = amountCorrectFormat(afterpackageAmountString);

			// cfObj.commonClick(coursePageORobj.getBookBuyNow());
			if (packageAmount == afterPackageAmount + removeCouponAmount) {
				// cfObj.commonClick(coursePageORobj.buyNowMain());
				cfObj.commonClick(coursePageORobj.getBookBuyNow());

				return true;
			} else {
				coursePageMsgList.add("The amount is not same of packages before and after");
				return false;
			}

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("clickOfferPromoBtn_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyPayCheckout() {
		boolean result = true;
		try {
			cfObj.commonClick(coursePageORobj.viewDetails());
			Thread.sleep(5000);

			String amountPayableString = coursePageORobj.amountPayableCheckout().getText();
			Double amountPayable = amountCorrectFormat(amountPayableString);
			Double amountDiscount = 0.00;
			if (coursePageORobj.amountPayDiscountElement().isDisplayed() == true) {
				String amountDiscountString = coursePageORobj.amountDiscount().getText();
				amountDiscount = amountCorrectFormat(amountDiscountString);
			}
			String totalAmountToPayString = coursePageORobj.totalAmountCheckout().getText();
			Double totalAmountToPay = amountCorrectFormat(totalAmountToPayString);

			if (amountPayable - amountDiscount == totalAmountToPay) {

				// check all payment methods are displayed
				List<WebElement> links = coursePageORobj.payMethodClick();

				for (int i = 0; i < links.size(); i++) {
					links.get(i).isDisplayed();
				}
				return true;

			} else {
				coursePageMsgList.add("The amount while checkout is not same");
				return false;
			}

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("payCheckout_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyClickPay(String paymentMethod, WebDriver driver, TestData testData, String bankName) {
		boolean result = true;
		try {
			List<WebElement> links = coursePageORobj.payMethodClick();

			for (int i = 0; i < links.size(); i++) {
				String methodString = links.get(i).getText();

				if (methodString.equalsIgnoreCase(paymentMethod) && paymentMethod.equalsIgnoreCase("Netbank")) {
					cfObj.commonClick(links.get(i));

					result = verifyNetbankMethod(driver, testData);
					if (!result) {
						return result;
					}
				} else if (methodString.equalsIgnoreCase(paymentMethod) && paymentMethod.equalsIgnoreCase("Paytm")) {
					cfObj.commonClick(links.get(i));

					result = verifyPaytmMethod(driver, testData, bankName);
					if (!result) {
						return result;
					}
				} else if (methodString.equalsIgnoreCase(paymentMethod)) {

					cfObj.commonClick(links.get(i));
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
			coursePageMsgList.add("verifyClickPay_Exception: " + e.getMessage());
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

			cfObj.commonClick(coursePageORobj.netbankingInPaytm());

			cfObj.commonClick(coursePageORobj.payBtnClick());

			cfObj.commonClick(coursePageORobj.successInPaytm());

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
			cfObj.commonSetTextTextBox(coursePageORobj.nameInfo(), name);

			cfObj.commonSetTextTextBox(coursePageORobj.addressInfo(), address);

			cfObj.commonSetTextTextBox(coursePageORobj.pincodeInfo(), zip);

			cfObj.commonSetTextTextBox(coursePageORobj.cityInfo(), city);

			cfObj.commonSetTextTextBox(coursePageORobj.stateInfo(), state);

			cfObj.commonSetTextTextBox(coursePageORobj.numberInfo(), number);

			cfObj.commonSetTextTextBox(coursePageORobj.emailInfo(), email);

			WebElement methods = coursePageORobj.bankMethods();
			Select select = new Select(methods);
			select.selectByIndex(1);

			cfObj.commonClick(coursePageORobj.payClick());

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
				cfObj.commonClick(coursePageORobj.merchantClick());
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
			cfObj.commonClick(coursePageORobj.golibrary());

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("goToLibrary_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean selectExamPrefrences(WebDriver driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "cdp-exam-categories-modal", "id", 30);
			if (result) {
				if (cfObj.commonGetElements(driver, ".cdp-exam-category", "css").size() == 0) {

				} else {
					// Select category
					cfObj.commonClick(cfObj.commonGetElements(driver, ".cdp-exam-category", "css").get(0));

					// click on submit button
					cfObj.commonClick(cfObj.commonGetElement(driver, "div.modal-footer>button", "css"));
				}

			} else {
				result = true;
			}

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("selectExamPrefrences_Exception" + e.getMessage());
		}

		return result;
	}

	public Double amountCorrectFormat(String str) {
		int index = 0;
		for (int k = 0; k < str.length(); k++) {
			if (str.charAt(k) >= 48 && str.charAt(k) <= 57) {
				index = k;
				break;
			}
		}

		String[] arr = str.substring(index).split(",");
		String amnt = "";

		for (int m = 0; m < arr.length; m++) {
			amnt = amnt + arr[m];
		}
		Double amountMain = Double.parseDouble(amnt);
		return amountMain;
	}

	public boolean verifyPackageType(CourseView courseViewObj) {
		boolean result = true;
		String strPackageType, strPackageValidity, strPackagePrice;

		try {
			if (coursePageORobj.getChoosePackagelist().size() != courseViewObj.getData().getPackages().size()) {
				coursePageMsgList.add("Mismatch in course package count with UI and API");
				return false;
			}

			for (int i = 0; i < coursePageORobj.getChoosePackagelist().size(); i++) {
				strPackageType = coursePageORobj.getChoosePackageTitle().get(i).getText().toString();
				strPackageValidity = coursePageORobj.getChoosePackageValidity().get(i).getText().toString();
				strPackagePrice = coursePageORobj.getChoosePackagePrice().get(i).getText().toString();
				System.out.println(strPackageType + strPackageValidity + strPackagePrice);
			}

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("verifyPackageType_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyPackageFeature(CourseView courseViewObj) {
		boolean result = true;
		String strCurrentPackageTitle, strChoosePackageTitle;
		try {
			for (int i = 0; i < coursePageORobj.getChoosePackagelist().size(); i++) {

				// click on first package
				cfObj.commonClick(coursePageORobj.getChoosePackagelist().get(i));

				// verify feature list

				if (coursePageORobj.getListPackageFeature().size() == 0) {
					coursePageMsgList.add("feature list is empty");
					result = false;
				}

				strCurrentPackageTitle = coursePageORobj.getListCurrentPackageTitle().get(0).getText().toString();

				strChoosePackageTitle = coursePageORobj.getChoosePackageTitle().get(i).getText().toString();

				if (!strCurrentPackageTitle.equalsIgnoreCase(strChoosePackageTitle)) {
					coursePageMsgList.add("Mismatch in package title: Current title: " + strCurrentPackageTitle
							+ " ChoosePackageTitle: " + strChoosePackageTitle);
					result = false;
				}
			}

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("_Exception" + e.getMessage());
		}
		return result;
	}
}
