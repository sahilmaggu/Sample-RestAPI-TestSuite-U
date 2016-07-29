package com.kiwi.commons;

import static com.jayway.restassured.RestAssured.given;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class HTTPCommonMethods {

	/**
	 * Method to call HTTP GET Method
	 * 
	 * @param cookie
	 * @param fullApiKey
	 * @param url
	 * @return
	 */
	public static Response doHTTPGet(String url) {

		Response httpResponse = given()
				.header("Content-Type", "application/json").log().all().when()
				.accept(ContentType.JSON).get(url);
		httpResponse.then().log().all();

		return httpResponse;

	}

	/**
	 * Method to call HTTP POST Method
	 * 
	 * @param cookie
	 * @param fullApiKey
	 * @param body
	 * @param url
	 * @return
	 */
	public static Response doHTTPPost(String body, String url) {

		Response httpResponse = given()
				.header("Content-Type", "application/json").body(body).log()
				.all().when().accept(ContentType.JSON).post(url);
		httpResponse.then().log().all();
		return httpResponse;
	}

}
