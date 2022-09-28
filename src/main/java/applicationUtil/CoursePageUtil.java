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

				result = verifyPackages(strCourseSlug, strCourseType, driver);
				if (!result) {
					return result;
				}

				result = verifyClickOfferPromo(driver, testData.getOfferName());
				if (!result) {
					return result;
				}
				result = verifyPayCheckout(driver);
				if (!result) {
					return result;
				}
				if ((ConfigFileReader.strEnv).equalsIgnoreCase("staging")
						|| (ConfigFileReader.strEnv).equalsIgnoreCase("dev")) {

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
				result = verifyPackages(strCourseSlug, strCourseType, driver);
				if (!result) {
					return result;
				}
				result = verifyClickOfferPromo(driver, testData.getOfferName());
				if (!result) {
					return result;
				}
				result = verifyPayCheckout(driver);
				if (!result) {
					return result;
				}

				if ((ConfigFileReader.strEnv).equalsIgnoreCase("staging")
						|| (ConfigFileReader.strEnv).equalsIgnoreCase("dev")) {

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
		result = goToLibrary(driver);
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

	public boolean verifyPackages(String strCourseSlug, String strCourseType, WebDriver driver) {
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

				result = verifyPackageFeature(courseViewObj, driver);
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

			String noOfOfferText = coursePageORobj.noOfOfferText().getText();
			String[] arr = noOfOfferText.split(" ");
			int countOfOffers = Integer.parseInt(arr[0]);

			if (countOfOffers == 0) {

				result = cfObj.commonWaitForElementToBeVisible(driver, coursePageORobj.buyNowMain(), 10);
				if (!result) {
					coursePageMsgList.add("The buy now btn is not visible");
					return result;
				}

				cfObj.commonClick(coursePageORobj.buyNowMain());

				// wait for payment detail page to be opned
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".package-payment-details", "css", 30);
				if (!result) {
					coursePageMsgList.add("Payment page not opned after click on buy now button");
					return result;
				}

				return true;
			} else {

				// It clicks on the offer desired and checks
				for (int i = 0; i < coursePackages.size(); i++) {
					String bestValueTitleString = links.get(i).getText();
					if (bestValueTitleString.equalsIgnoreCase(offerName)) {

						result = cfObj.commonWaitForElementToBeVisible(driver, links.get(i), 10);
						if (!result) {
							coursePageMsgList.add("The course package is not visible");
							return result;
						}

						cfObj.commonClick(coursePackages.get(i));

						String packageAmountString = coursePageORobj.packageAmounts().get(i).getText();
						Double packageAmount = amountCorrectFormat(packageAmountString);

						result = cfObj.commonWaitForElementToBeVisible(driver, coursePageORobj.promoClick(), 10);
						if (!result) {
							coursePageMsgList.add("The course available btn is not visible");
							return result;
						}

						cfObj.commonClick(coursePageORobj.promoClick());

						result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".card-title.h5", "css", 10);
						if (!result) {
							coursePageMsgList.add("The avaiable offers section is not visible");
							return result;
						}

						result = cfObj.commonWaitForElementToBeVisible(driver, coursePageORobj.applyCode().get(0), 10);
						if (!result) {
							coursePageMsgList.add("The apply btn is not visible");
							return result;
						}

						cfObj.commonClick(coursePageORobj.applyCode().get(0));

						result = cfObj.commonWaitForElementToBeVisible(driver, coursePageORobj.couponCodeElement(), 10);
						if (!result) {
							coursePageMsgList.add("The coupon code is not visible after applying");
							return result;
						}

						String couponCode = coursePageORobj.couponCodeElement().getText();

						result = cfObj.commonWaitForElementToBeVisible(driver, coursePageORobj.RApplyCodeClick(), 10);
						if (!result) {
							coursePageMsgList.add("The remove btn is not visible after applying");
							return result;
						}

						cfObj.commonClick(coursePageORobj.RApplyCodeClick());

						cfObj.commonClick(coursePageORobj.promoClick());

						String invalidPromo = "INVALID123";

						result = cfObj.commonWaitForElementToBeVisible(driver, coursePageORobj.inputCode(), 10);
						if (!result) {
							coursePageMsgList.add("The input box for coupon is not visible");
							return result;
						}

						result = cfObj.commonSetTextTextBox(coursePageORobj.inputCode(), invalidPromo);
						if (!result) {
							coursePageMsgList.add("The input is not working");
							return result;
						}

						cfObj.commonClick(coursePageORobj.applyCodeMain());

						result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "coupon-error", "class", 10);
						if (!result) {
							coursePageMsgList.add("The coupon is wrong but error msg not visible");
							return result;
						}

						result = cfObj.commonSetTextTextBox(coursePageORobj.inputCode(), couponCode);
						if (!result) {
							coursePageMsgList.add("The input is not working");
							return result;
						}

						cfObj.commonClick(coursePageORobj.applyCodeMain());

						result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "coupon-error", "class", 10);
						if (!result) {

							result = cfObj.commonWaitForElementToBeVisible(driver, coursePageORobj.couponCodeElement(),
									10);
							if (!result) {
								coursePageMsgList.add("The coupon code is not visible after applying");
								return result;
							}

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

							// wait for payment detail page to be opened
							result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".package-payment-details",
									"css", 30);
							if (!result) {
								coursePageMsgList.add("Payment page not opned after click on buy now button");
								return result;
							}

							return true;

						} else {
							System.out.println("The coupon is correct but it is not valid msg is not visible");

							cfObj.commonClick(coursePageORobj.buyNowMain());

							// wait for payment detail page to be opned
							result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".package-payment-details",
									"css", 30);
							if (!result) {
								coursePageMsgList.add("Payment page not opned after click on buy now button");
								return result;
							}

							return true;
						}

					}
				}
			}

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("clickOfferPromoBtn_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyPayCheckout(WebDriver driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".modal-title.h4", "css", 10);
			if (!result) {
				coursePageMsgList.add("The checkout page is not open or the title is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, coursePageORobj.viewDetails(), 10);
			if (!result) {
				coursePageMsgList.add("The view details btn is not visible");
				return result;
			}

			cfObj.commonClick(coursePageORobj.viewDetails());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".label", "css", 10);
			if (!result) {
				coursePageMsgList.add("The Amount payable text is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, coursePageORobj.amountPayableCheckout(), 10);
			if (!result) {
				coursePageMsgList.add("The Amount payable amount is not visible");
				return result;
			}

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

						result = links.get(i).isDisplayed();
						if (!result) {
							coursePageMsgList.add("The payment method is not visible");
							return result;
						}
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

				result = cfObj.commonWaitForElementToBeVisible(driver, links.get(i), 10);
				if (!result) {
					coursePageMsgList.add("The pay method is not visible");
					return result;
				}

				String methodString = links.get(i).getText();

				if (methodString.equalsIgnoreCase(paymentMethod) && paymentMethod.equalsIgnoreCase("Netbank")) {

					cfObj.commonClick(links.get(i));

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".oops", "css", 10);
					if (result) {

						coursePageMsgList.add("The method is not working, some error " + methodString + " failure.");
						return false;

					}

					result = verifyNetbankMethod(driver, testData);
					if (!result) {
						return result;
					}

				} else if (methodString.equalsIgnoreCase(paymentMethod) && paymentMethod.equalsIgnoreCase("Paytm")) {

					cfObj.commonClick(links.get(i));

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".oops", "css", 10);
					if (result) {

						coursePageMsgList.add("The method is not working, some error " + methodString + " failure.");
						return false;

					}

					result = verifyPaytmMethod(driver, testData, bankName);
					if (!result) {
						return result;
					}

				} else if (methodString.equalsIgnoreCase(paymentMethod)) {

					cfObj.commonClick(links.get(i));

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".oops", "css", 10);
					if (result) {

						coursePageMsgList.add("The method is not working, some error " + methodString + " failure.");
						return false;

					}

					verifyOtherPayMethods(driver);

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
					testData.getCityBill(), testData.getStateBill(), testData.getNumberBill(), testData.getEmailBill(),
					driver);
			if (!result) {
				return result;
			}

			result = verifyMerchantStatus(driver);
			if (!result) {
				return result;
			}

			result = verifyPaymentStatus(driver);
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

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//img[@src='https://static-staging.paytm.in/pgp/web/assets/paytm-pg-blue.svg']", "xpath", 10);
			if (!result) {
				coursePageMsgList.add("The payment page is not of paytm");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, coursePageORobj.netbankingInPaytm(), 10);
			if (!result) {
				coursePageMsgList.add("The netbank btn is not visible in paytm method");
				return result;
			}

			cfObj.commonClick(coursePageORobj.netbankingInPaytm());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".pu-title", "css", 10);
			if (!result) {
				coursePageMsgList.add("Select bank pop up is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, coursePageORobj.payBtnClick(), 10);
			if (!result) {
				coursePageMsgList.add("The netbank btn is not visible in paytm method");
				return result;
			}

			cfObj.commonClick(coursePageORobj.payBtnClick());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "div[class='midCont'] h1", "css", 10);
			if (!result) {
				coursePageMsgList.add("bank demo page is not visible or open");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, coursePageORobj.successInPaytm(), 10);
			if (!result) {
				coursePageMsgList.add("The successful btn is not visible in paytm method");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, coursePageORobj.failureInPaytm(), 10);
			if (!result) {
				coursePageMsgList.add("The failure btn is not visible in paytm method");
				return result;
			}

			cfObj.commonClick(coursePageORobj.successInPaytm());

			result = verifyPaymentStatus(driver);
			if (!result) {
				return result;
			}

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("verifyPaytmMethod_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyOtherPayMethods(WebDriver driver) {
		boolean result = true;
		try {

			System.out.println("The pay method is working but no page is open");
			return false;

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("verifyOtherPayMethod_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyBillingInfo(String name, String address, String zip, String city, String state, String number,
			String email, WebDriver driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, coursePageORobj.nameInfo(), 10);
			if (!result) {
				coursePageMsgList.add("The name input box is not visible");
				return result;
			}

			cfObj.commonSetTextTextBox(coursePageORobj.nameInfo(), name);

			result = cfObj.commonWaitForElementToBeVisible(driver, coursePageORobj.addressInfo(), 10);
			if (!result) {
				coursePageMsgList.add("The address input box is not visible");
				return result;
			}

			cfObj.commonSetTextTextBox(coursePageORobj.addressInfo(), address);

			result = cfObj.commonWaitForElementToBeVisible(driver, coursePageORobj.pincodeInfo(), 10);
			if (!result) {
				coursePageMsgList.add("The pincode input box is not visible");
				return result;
			}

			cfObj.commonSetTextTextBox(coursePageORobj.pincodeInfo(), zip);

			result = cfObj.commonWaitForElementToBeVisible(driver, coursePageORobj.cityInfo(), 10);
			if (!result) {
				coursePageMsgList.add("The city input box is not visible");
				return result;
			}

			cfObj.commonSetTextTextBox(coursePageORobj.cityInfo(), city);

			result = cfObj.commonWaitForElementToBeVisible(driver, coursePageORobj.stateInfo(), 10);
			if (!result) {
				coursePageMsgList.add("The state input box is not visible");
				return result;
			}

			cfObj.commonSetTextTextBox(coursePageORobj.stateInfo(), state);

			result = cfObj.commonWaitForElementToBeVisible(driver, coursePageORobj.numberInfo(), 10);
			if (!result) {
				coursePageMsgList.add("The number input box is not visible");
				return result;
			}

			cfObj.commonSetTextTextBox(coursePageORobj.numberInfo(), number);

			result = cfObj.commonWaitForElementToBeVisible(driver, coursePageORobj.emailInfo(), 10);
			if (!result) {
				coursePageMsgList.add("The email input box is not visible");
				return result;
			}

			cfObj.commonSetTextTextBox(coursePageORobj.emailInfo(), email);

			WebElement methods = coursePageORobj.bankMethods();
			Select select = new Select(methods);
			select.selectByIndex(1);

			result = cfObj.commonWaitForElementToBeVisible(driver, coursePageORobj.payClick(), 10);
			if (!result) {
				coursePageMsgList.add("The pay btn is not visible");
				return result;
			}

			cfObj.commonClick(coursePageORobj.payClick());

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("BiilingInfo_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyMerchantStatus(WebDriver driver) {
		boolean result = true;
		try {

			WebElement method = coursePageORobj.transactionStatusCheck();
			Select select = new Select(method);
			String status = select.getFirstSelectedOption().getText();

			if (status.equalsIgnoreCase("Y")) {

				result = cfObj.commonWaitForElementToBeVisible(driver, coursePageORobj.merchantClick(), 10);
				if (!result) {
					coursePageMsgList.add("The merchant click is not visible");
					return result;
				}

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

	public boolean verifyPaymentStatus(WebDriver driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".payment-h", "css", 10);
			if (!result) {
				coursePageMsgList.add("The status of course purchase is not visible or wrong page");
				return result;
			}

			String expectedPayStatus = "Payment Successful";
			String actualPayStatus = coursePageORobj.statusPay().getText();

			if (expectedPayStatus.equalsIgnoreCase(actualPayStatus)) {
				return true;
			} else {
				coursePageMsgList.add("Payment Unsuccessful");
				return false;
			}

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("PaymentStatus_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean goToLibrary(WebDriver driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, coursePageORobj.golibrary(), 10);
			if (!result) {
				coursePageMsgList.add("The library btn is not visible");
				return result;
			}

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

	public boolean verifyPackageFeature(CourseView courseViewObj, WebDriver driver) {
		boolean result = true;
		String strCurrentPackageTitle, strChoosePackageTitle;
		try {
			for (int i = 0; i < coursePageORobj.getChoosePackagelist().size(); i++) {

				result = cfObj.commonWaitForElementToBeVisible(driver, coursePageORobj.getChoosePackagelist().get(i),
						10);
				if (!result) {
					coursePageMsgList.add("The pacakge is not visible");
					return result;
				}

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
