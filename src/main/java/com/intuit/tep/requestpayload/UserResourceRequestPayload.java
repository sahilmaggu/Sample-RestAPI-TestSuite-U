package com.intuit.tep.requestpayload;

public class UserResourceRequestPayload {

	public static String getRequestPayload(String username, String password,
			String full_name, String email) {

		String body = "{\"username\": \"" + username + "\","
				+ "\"password\": \"" + password + "\"," + "\"full_name\": \""
				+ full_name + "\"," + "\"email\":\"" + email + "\"}";

		return body;
	}

	public static String getRequestPayloadForLogin(String username,
			String password) {

		String body = "{\"username\": \"" + username + "\","
				+ "\"password\": \"" + password + "\",";

		return body;
	}

}
