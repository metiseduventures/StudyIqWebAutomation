package util;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.xml.parsers.ParserConfigurationException;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.mozilla.javascript.ast.WhileLoop;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.SAXException;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import io.github.bonigarcia.wdm.WebDriverManager;

@SuppressWarnings("unused")
public class Common_Function {
	public APIUtils apiUtilObj;
	public APIResponse apiResponseObj;
	public WebDriver driver;
	public ConfigFileReader configReaderObj = new ConfigFileReader();

	public WebDriver commonStartAndOpenURLBrowser() throws ParserConfigurationException, SAXException, IOException {

		DesiredCapabilities capability = null;
		WebDriver driver = null;
		String strBrowser = "Chrome";
		try {
			capability = new DesiredCapabilities();
			if (strBrowser.equalsIgnoreCase("Chrome")) {
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--start-maximized");
				capability.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
				capability.setBrowserName("Chrome");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(chromeOptions);
			}

			driver.get(configReaderObj.getBaseUrlWeb());
			Dimension newDimension = new Dimension(1512, 982);
			driver.manage().window().setSize(newDimension);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			if (commonGetElements(driver, ".close_img", "css").size() > 0) {
				commonClick(commonGetElement(driver, ".close_img", "css"));
			}
		}

		catch (

		Exception e) {
			e.printStackTrace();

		} finally {

		}
		return driver;
	}

	public boolean commonWaitForElementToBeVisible(WebDriver driver, WebElement elementforWait, int timeOutInSeconds) {
		boolean result = true;
		try {
			// Element is Clickable - it is Displayed and Enabled.
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			WebElement element = wait.until(ExpectedConditions.visibilityOf(elementforWait));
			result = element.isDisplayed();

		} catch (Exception e) {
			result = false;
			System.out.println("Element is not visible ");
		}

		return result;
	}

	public boolean commonWaitForElementToBeLocatedAndVisible(WebDriver driver, String elementforWait,
			String strfindType, int timeOutInSeconds) {
		boolean result = true;
		WebElement element = null;
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			// Element is Clickable - it is Displayed and Enabled.
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			if (strfindType.equalsIgnoreCase("xpath")) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementforWait)));
			} else if (strfindType.equalsIgnoreCase("id")) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elementforWait)));
			} else if (strfindType.equalsIgnoreCase("class")) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(elementforWait)));
			} else if (strfindType.equalsIgnoreCase("css")) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(elementforWait)));
			}
			result = element.isDisplayed();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		} catch (Exception e) {
			result = false;
			System.out.println("Element is not visible: " + elementforWait);
		}
		return result;
	}

	public void commonClick(WebElement iclickInfo) throws IOException, SAXException, ParserConfigurationException {

		try {
			iclickInfo.click();
			Thread.sleep(500);

		} catch (Exception e) {

		}
	}

	/**
	 * @param iTextBoxInfo WebElement reference
	 * @param sText        String type text which will be set in text box
	 * @return boolean True/False as a result on the basis of findings
	 * @throws IOException                  if IO exception occurred
	 * @throws ParserConfigurationException if parse configuration exception
	 *                                      occurred
	 * @throws SAXException                 if SAX exception occurred
	 */
	public boolean commonSetTextTextBox(WebElement iTextBoxInfo, String sText)
			throws IOException, ParserConfigurationException, SAXException {

		boolean Result = false;
		try {
			iTextBoxInfo.clear();
			iTextBoxInfo.click();
			iTextBoxInfo.sendKeys(sText);
			Result = commonVerifyValueTextBox(iTextBoxInfo, sText);

		} catch (Exception ex) {
			Result = false;
		}
		return Result;
	}

	/**
	 * @param iTextBoxInfo   WebElement reference
	 * @param sExpectedValue String type expected value
	 * @return boolean True/False as a result on the basis of verification pass or
	 *         fail
	 * @throws IOException                  if IO exception occurred
	 * @throws SAXException                 if SAX exception occurred
	 * @throws ParserConfigurationException if parser configuration exception
	 *                                      occurred
	 */
	public boolean commonVerifyValueTextBox(WebElement iTextBoxInfo, String sExpectedValue)
			throws IOException, SAXException, ParserConfigurationException {

		String sTempStr = null;
		boolean Result = false;

		try {
			sExpectedValue = sExpectedValue.trim().toLowerCase();

			sTempStr = iTextBoxInfo.getAttribute("value").trim().toLowerCase();

			if ((sTempStr.contains(sExpectedValue))) {
				Result = true;
			} else {
				Result = false;
			}
		} catch (Exception e) {
			Result = false;
		}
		return Result;
	}

	public JsonObject getJsonData(String strfileLocation) {
		JsonElement jsonData = null;
		try {
			jsonData = new JsonParser().parse(new FileReader(strfileLocation));
			System.out.println(jsonData.getAsJsonObject());
			System.out.println(jsonData.getAsJsonObject().get("मराठी").getAsJsonObject().get("title"));
		} catch (Exception e) {

		}
		return jsonData.getAsJsonObject();
	}

	public boolean commonWaitForListSizeTobeGreaterThanZero(WebDriver driver, By locator, int timeOutInSeconds) {
		boolean result = true;
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			List<WebElement> elementLst = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
			if (elementLst.size() == 0) {
				return result;
			}

		} catch (Exception e) {
			result = false;
		}

		return result;
	}

	public boolean commonWaitpresenceOfElementLocated(WebDriver driver, int timeOutInSeconds, By locator)
			throws TimeoutException {

		boolean result = true;
		WebElement element = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			if (element == null) {
				result = false;
			}
		} catch (NoSuchElementException e) {

		} catch (TimeoutException e) {

		} catch (Exception e) {

		}
		return result;
	}

	public static String randomPhoneNumber(long len, String start) {
		String num = null;
		try {
			if (len > 10)
				throw new IllegalStateException("To many digits");
			long tLen = (long) Math.pow(10, len - 1) * 9;

			long number = (long) (Math.random() * tLen) + (long) Math.pow(10, len - 1) * 1;

			String tVal = start + number + "";

			num = tVal.substring(0, 10);

			if (num.length() != len) {

				throw new IllegalStateException("The random number '" + num + "' is not '" + len + "' digits");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to generate Random Mobile number");

		}
		return num;
	}

	public static String getCurrentDateTime() {
		LocalDateTime currentDateTime = java.time.LocalDateTime.now();
		return currentDateTime.toString();
	}

	public int getRandomNumber(int start, int end) {
		Random rand = new Random();
		int randomNum = rand.nextInt((end - start) + 1) + start;
		return randomNum;
	}

	public boolean commonWaitForElementToPresent(WebDriver driver, String elementforWait, int timeOutInSeconds) {
		boolean result = true;
		try {
			// Element to be present
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementforWait)));
			result = element.isDisplayed();
		} catch (Exception e) {
			result = false;
			System.out.println("Element is not visible ");
		}

		return result;
	}

	public static String convert(String str) {

		// Create a char array of given String
		char ch[] = str.toCharArray();
		for (int i = 0; i < str.length(); i++) {

			// If first character of a word is found
			if (i == 0 && ch[i] != ' ' || ch[i] != ' ' && ch[i - 1] == ' ') {

				// If it is in lower-case
				if (ch[i] >= 'a' && ch[i] <= 'z') {

					// Convert into Upper-case
					ch[i] = (char) (ch[i] - 'a' + 'A');
				}
			}

			// If apart from first character
			// Any one is in Upper-case
			else if (ch[i] >= 'A' && ch[i] <= 'Z')

				// Convert into Lower-Case
				ch[i] = (char) (ch[i] + 'a' - 'A');
		}

		// Convert the char array to equivalent String
		String st = new String(ch);
		System.out.println("new string: " + st);
		return st;
	}

	public WebElement commonGetElement(WebDriver driver, String elementforWait, String strfindType) {
		WebElement element = null;
		try {
			if (strfindType.equalsIgnoreCase("xpath")) {
				element = driver.findElement(By.xpath(elementforWait));
			} else if (strfindType.equalsIgnoreCase("id")) {
				element = driver.findElement(By.id(elementforWait));
			} else if (strfindType.equalsIgnoreCase("class")) {
				element = driver.findElement(By.className(elementforWait));
			} else if (strfindType.equalsIgnoreCase("css")) {
				element = driver.findElement(By.cssSelector(elementforWait));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return element;
	}

	public List<WebElement> commonGetElements(WebDriver driver, String elementforWait, String strfindType) {
		List<WebElement> element = null;
		try {
			if (strfindType.equalsIgnoreCase("xpath")) {
				element = driver.findElements(By.xpath(elementforWait));
			} else if (strfindType.equalsIgnoreCase("id")) {
				element = driver.findElements(By.id(elementforWait));
			} else if (strfindType.equalsIgnoreCase("class")) {
				element = driver.findElements(By.className(elementforWait));
			} else if (strfindType.equalsIgnoreCase("css")) {
				element = driver.findElements(By.cssSelector(elementforWait));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return element;
	}

	public List<WebElement> commonWaitForElementToBeGreaterThan(WebDriver driver, String elementforWait,
			String strfindType, int timeOutInSeconds) {
		List<WebElement> elements = null;
		try {
			// Element is Clickable - it is Displayed and Enabled.
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			if (strfindType.equalsIgnoreCase("xpath")) {
				elements = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(strfindType), 1));
			} else if (strfindType.equalsIgnoreCase("id")) {
				elements = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.id(strfindType), 1));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return elements;
	}

	public static String getCurrentDateInGivenFormat(String dateFormat) {
		SimpleDateFormat DateFor;
		String stringDate = null;
		try {
			Date date = new Date();
			DateFor = new SimpleDateFormat(dateFormat);
			stringDate = DateFor.format(date);
			System.out.println("Date Format with E, dd MMM yyyy:" + stringDate);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return stringDate;
	}

	public void closePopUpIfExist(WebDriver driver) {
		List<WebElement> popups;
		try {
			Thread.sleep(10000);
			popups = commonGetElements(driver, "android.widget.ImageView", "class");
			if (popups.size() == 2) {
				commonClick(popups.get(1));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}

	public String getTimedifference(long start, long end) {
		String strTime = null;
		try {
			NumberFormat formatter = new DecimalFormat("#0.00000");
			strTime = formatter.format((end - start) / 1000d) + " seconds";
			System.out.println(strTime);

		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}

		return strTime;

	}

	public static String getFutureDateTime(String currentDate, int numberOfDays) {
		String newDate = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();

			c.setTime(sdf.parse(currentDate));

			// Incrementing the date by n day
			c.add(Calendar.DAY_OF_MONTH, numberOfDays);
			newDate = sdf.format(c.getTime());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return newDate;
	}

	public static String getCurrentate(String strPattern) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strPattern);

		String date = simpleDateFormat.format(new Date());
		System.out.println(date);
		return date;
	}

	public static Integer RandomNumber(int start, int end) {
		Random rand = new Random();
		int randomNum = rand.nextInt((end - start) + 1) + start;
		return randomNum;

	}

	public static Date convertStringToDate(String strDate) {
		Date date = null;
		try {
			DateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
			date = formatter.parse(strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public static boolean isSorted(List<Date> dateString) {
		for (int i = 0; i < dateString.size() - 1; ++i) {
			System.out.println(dateString.get(i));
			if (dateString.get(i).compareTo(dateString.get(i + 1)) > 0)
				return false;
		}
		return true;
	}

	public void commonClick_Action(WebDriver driver, WebElement iclickInfo)
			throws IOException, SAXException, ParserConfigurationException {

		try {
			Actions action = new Actions(driver);
			action.moveToElement(iclickInfo);
			action.click();
			action.perform();
			Thread.sleep(500);

		} catch (Exception e) {

		}
	}

	public String getApiTokenfromCookies(WebDriver driver) {
		String strApiToken = null;
		Cookie cookieValue;
		try {
			cookieValue = driver.manage().getCookieNamed("apiToken");
			strApiToken = cookieValue.getValue();
			System.out.println("strApiToken: " + strApiToken);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return strApiToken;
	}
	
	public String getUserIdFromCookies(WebDriver driver) {
		String strUserId = null;
		Cookie cookieValue;
		try {
			cookieValue = driver.manage().getCookieNamed("userID");
			strUserId = cookieValue.getValue();
			System.out.println("userID: " + strUserId);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return strUserId;
	}

}
