package com.kiwi.api;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 *
 * @author Sahil Maggu
 */

public class Utility {
	final static Logger logger = Logger.getLogger(Utility.class);
	public static Properties prop = null;
	FileInputStream fs;

	public Utility() {
		if (prop == null) {
			try {
				prop = new Properties();
				System.out.println(System.getProperty("user.dir"));
				fs = new FileInputStream(
						System.getProperty("user.dir")
								+ "/src/test/resources/EnvProperties/Globaldatafile.properties");
				prop.load(fs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		overrideProperty();
		logger.info("Env set --> " + prop.get("ENV").toString());
		getEnvPropertiesFile();
	}

	private void overrideProperty() {
		if (prop.getProperty("ENV").toString().equalsIgnoreCase("MAVEN")) {

			prop.setProperty("ENV", System.getProperty("ENV"));

		}
	}

	private void getEnvPropertiesFile() {

		if (prop.get("ENV").toString().equalsIgnoreCase("E2E")) {
			try {
				fs = new FileInputStream(
						System.getProperty("user.dir")
								+ "/src/test/resources/EnvProperties/E2Edatafile.properties");
				prop.load(fs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (prop.get("ENV").toString().equalsIgnoreCase("PERF")) {
			try {
				fs = new FileInputStream(
						System.getProperty("user.dir")
								+ "/src/test/resources/EnvProperties/PRFdatafile.properties");
				prop.load(fs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (prop.get("ENV").toString().equalsIgnoreCase("PROD")) {
			try {
				fs = new FileInputStream(
						System.getProperty("user.dir")
								+ "/src/test/resources/EnvProperties/PRODdatafile.properties");
				prop.load(fs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static String getCokkie() {

		return null;
	}

	public String getEnvURL() {
		if (prop.get("ENV").toString().equalsIgnoreCase("E2E")) {
			return prop.getProperty("E2EUrl");
		} else if (prop.get("ENV").toString().equalsIgnoreCase("PROD")) {
			return prop.getProperty("ProdUrl");
		} else if (prop.get("ENV").toString().equalsIgnoreCase("PERF")) {
			return prop.getProperty("PRFUrl");
		}
		return "Please set the environment";
	}

	/**
	 * Method to return currentDateTime in required format
	 * 
	 * @param format
	 * @return ex : "yyyy/MM/ddHH:mm:ss" , "yyyy-MM-dd"
	 */
	public static String getCurrentDateTime(String format) {

		DateFormat dateFormat = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		String currentDateTime = dateFormat.format(cal.getTime());
		System.out.println(currentDateTime);
		return currentDateTime;
	}

}
