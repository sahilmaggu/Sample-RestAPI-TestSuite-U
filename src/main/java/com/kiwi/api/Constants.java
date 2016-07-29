package com.kiwi.api;

public final class Constants {
	private Constants() {

	}

	public static String baseUrl = new Utility().getEnvURL();
	public static String cookie = "";

	// UserAPI test Constants
	public static String password = Utility.prop.getProperty("password");

}
