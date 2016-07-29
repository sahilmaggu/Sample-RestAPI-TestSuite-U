package com.kiwi.commons;

import com.jayway.restassured.response.Response;

import static com.kiwi.api.Constants.*;

public class UserResourceCommon {

	/**
	 * Method to Get User by ID
	 * 
	 * @param userID
	 * @return
	 */

	public static Response doGetUserByID(String userId) {

		String url = baseUrl + "/account/" + userId + "/details";
		Response response = HTTPCommonMethods.doHTTPGet(url);
		return response;

	}

	/**
	 * Method to create new user
	 * 
	 * @param cookie
	 * @param body
	 * @return
	 */
	public static Response doPostCreateNewUser(String body) {

		String url = baseUrl + "/create";
		Response response = HTTPCommonMethods.doHTTPPost(body, url);
		return response;

	}

	/**
	 * Method to create new user
	 * 
	 * @param body
	 * @return
	 */
	public static Response doPostNewUser(String body) {

		String url = baseUrl + "/login";
		Response response = HTTPCommonMethods.doHTTPPost(body, url);
		return response;

	}

}
