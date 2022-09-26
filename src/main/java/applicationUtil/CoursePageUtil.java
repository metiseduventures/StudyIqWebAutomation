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
		String strCourseType;
		try {

			strCourseType = testData.getCourseType().toString();
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
				testData.setCourseName(strCourseSlug.replace("-", " "));
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

				result = verifyPackages(strCourseSlug, strCourseType);
				if (!result) {
					return result;
				}

				result = verifyClickOfferPromo(driver, testData.getOfferName());
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
				result = verifyPackages(strCourseSlug, strCourseType);
				if (!result) {
					return result;
				}
				result = verifyClickOfferPromo(driver, testData.getOfferName());
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

	public boolean verifyPackages(String strCourseSlug, String strCourseType) {
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

			if (strCourseType.contains("books")) {

				result = verifyPackageFormFormBooks();
				if (!result) {
					return result;
				}

			} else {

				result = verifyPackageFeature(courseViewObj);
				if (!result) {
					return result;
				}
			}
		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("verficationOfPackages_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyClickOfferPromo(WebDriver driver, String offerName) {
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
					} else {
						coursePageMsgList.add("The amount is not same of packages before and after");
						return false;
					}
				}
			}

			if (coursePackages.size() == 0) {
				cfObj.commonClick(coursePageORobj.buyNowMain());
			}

			// wait for payment detail page to be opned

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".package-payment-details", "css", 30);
			if (!result) {
				coursePageMsgList.add("Payment page not opned after click on buy now button");
				return result;
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

			if (coursePageORobj.getListBreakDownItem().size() > 1) {
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
					}else
					{
						break;
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

			if (!courseViewObj.getData().getCourseType().getCourseTypeName().equalsIgnoreCase("Books")) {
				if (coursePageORobj.getChoosePackagelist().size() != courseViewObj.getData().getPackages().get(0)
						.getPackages().size()) {
					coursePageMsgList.add("Mismatch in course package count with UI and API");
					return false;
				}

				for (int i = 0; i < coursePageORobj.getChoosePackagelist().size(); i++) {
					strPackageType = coursePageORobj.getChoosePackageTitle().get(i).getText().toString();
					strPackageValidity = coursePageORobj.getChoosePackageValidity().get(i).getText().toString();
					strPackagePrice = coursePageORobj.getChoosePackagePrice().get(i).getText().toString();
					System.out.println(strPackageType + strPackageValidity + strPackagePrice);
				}
			} else {
				if (coursePageORobj.getListBookDetailOnPackagePopUp().size() == 0) {
					coursePageMsgList.add("Books Package is not display on package pop ");
					return false;
				}
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
			coursePageMsgList.add("verifyPackageFeature_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyPackageFormFormBooks() {
		boolean result = true;
		try {

			// booking form should be visible in package pop for books
			if (coursePageORobj.getListBookingForm().size() == 0) {
				coursePageMsgList.add("Booking form is not visible for course type book in package pop up");
				return false;
			}

			if (coursePageORobj.getListBookingFormName().size() == 0) {
				coursePageMsgList.add("Name field is not display in booking form");
				result = false;
			}
			if (coursePageORobj.getListBookingFormPhoneNo().size() == 0) {
				coursePageMsgList.add("Phone number field is not display in booking form");
				result = false;
			}
			if (coursePageORobj.getListBookingFormEmail().size() == 0) {
				coursePageMsgList.add("Email field is not display in booking form");
				result = false;
			}
			if (coursePageORobj.getListBookingFormAddress().size() == 0) {
				coursePageMsgList.add("Address field is not display in booking form");
				result = false;
			}
			if (coursePageORobj.getListBookingFormPinCode().size() == 0) {
				coursePageMsgList.add("Pin code field is not display in booking form");
				result = false;
			}
			if (coursePageORobj.getListBookingFormCity().size() == 0) {
				coursePageMsgList.add("City field is not display in booking form");
				result = false;
			}
			if (coursePageORobj.getListBookingFormState().size() == 0) {
				coursePageMsgList.add("State field is not display in booking form");
				result = false;
			}

			if (!result) {
				return result;
			}

			if (coursePageORobj.getListBookingFormEmail().get(0).getAttribute("value").toString().length() < 0) {
				coursePageMsgList.add("Email field is empty");
				return result;
			}
			if (coursePageORobj.getListBookingFormName().get(0).getAttribute("value").toString().length() < 0) {
				coursePageMsgList.add("Name field is empty");
				return result;
			}
			if (coursePageORobj.getListBookingFormPhoneNo().get(0).getAttribute("value").toString().length() < 0) {
				coursePageMsgList.add("Phone field is empty");
				return result;
			}
			result = cfObj.commonSetTextTextBox(coursePageORobj.getListBookingFormAddress().get(0),
					"Unitech Cyber park");
			if (!result) {
				coursePageMsgList.add("not able to add address");
				return result;
			}

			result = cfObj.commonSetTextTextBox(coursePageORobj.getListBookingFormPinCode().get(0), "122003");
			if (!result) {
				coursePageMsgList.add("not able to add address");
				return result;
			}

			if (!coursePageORobj.getListBookingFormCity().get(0).getAttribute("value").toString()
					.equalsIgnoreCase("GURUGRAM")) {
				coursePageMsgList.add("Mismatch in city expected to be Gurugram");
				return result;
			}

			if (!coursePageORobj.getListBookingFormState().get(0).getAttribute("value").toString()
					.equalsIgnoreCase("HARYANA")) {
				coursePageMsgList.add("Mismatch in State expected to be HARYANA");
				return result;
			}

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("verifyPackageFormFormBooks_Exception" + e.getMessage());
		}
		return result;
	}
}
