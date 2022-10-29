package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
	public static String strEnv,strRunMode,strUserMobileNumber;
	
	static {
		strRunMode = System.getProperty("runMode");
		strRunMode = "local";
		strUserMobileNumber = System.getProperty("userMobileNumber");
		strUserMobileNumber = "9958544199";
	}

	private Properties properties;

	public ConfigFileReader() {
		BufferedReader reader;
		String strPropertyPath = null;
		//strEnv = System.getProperty("env");
		strEnv = "prod";
		//strEnv = "staging";						

		try {
			if (strEnv.equalsIgnoreCase("staging")) {
				strPropertyPath = "src/main/resources/config/staging.properties";

			} else if (strEnv.equalsIgnoreCase("dev")) {

				strPropertyPath = "src/main/resources/config/dev.properties";

			} else if (strEnv.equalsIgnoreCase("prod")) {

				strPropertyPath = "src/main/resources/config/prod.properties";

			}
			reader = new BufferedReader(new FileReader(strPropertyPath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + strPropertyPath);
		}
	}

	public String getEnv() {
		String strEnv = properties.getProperty("env");
		if (strEnv != null)
			return strEnv;
		else
			throw new RuntimeException(strEnv + "not specified in the Configuration properties file.");
	}

	public String getBaseUrl() {
		String strBaseUrl = properties.getProperty("BaseUrl");
		if (strBaseUrl != null)
			return strBaseUrl;
		else
			throw new RuntimeException("Base url is not defined.");
	}

	public String getOtpToken() {
		String strOtpToken = properties.getProperty("otpToken");
		if (strOtpToken != null)
			return strOtpToken;
		else
			throw new RuntimeException("Otp token is not defined.");
	}
	
	public String getBaseUrlWeb() {
		String strBaseUrlWeb = properties.getProperty("BaseUrlWeb");
		if (strBaseUrlWeb != null)
			return strBaseUrlWeb;
		else
			throw new RuntimeException("Base url web is not defined.");
	}
	
	public String getCourseTestId() {
		String strCourseTestId = properties.getProperty("courseTestId");
		if (strCourseTestId != null)
			return strCourseTestId;
		else
			throw new RuntimeException("strCourseTestId is not defined.");
	}
	public String getCrossSellCourseSlug() {
		String strCrossSellCourseSlug = properties.getProperty("crossSellSlug");
		if (strCrossSellCourseSlug != null)
			return strCrossSellCourseSlug;
		else
			throw new RuntimeException("strCrossSellCourseSlug is not defined.");
	}

}
