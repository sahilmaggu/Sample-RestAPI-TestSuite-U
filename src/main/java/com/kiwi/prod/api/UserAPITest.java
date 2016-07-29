package com.kiwi.prod.api;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.intuit.tep.requestpayload.UserResourceRequestPayload;
import com.jayway.restassured.response.Response;
import com.kiwi.api.Utility;
import com.kiwi.commons.UserResourceCommon;

import static com.kiwi.api.Constants.*;

public class UserAPITest {
	final static Logger logger = Logger.getLogger(UserAPITest.class);

	@BeforeClass
	public void setUp() {
		new Utility();
		cookie = Utility.getCokkie();
	}

	/*
	 * Test to create a new User, Get the Details and Login with same
	 */
	@Test
	public void postCreateNewUserTest() {

		logger.info("##############################################################################");
		logger.info("UserAPI->Test to create New user");
		logger.info("##############################################################################");
		String getcurrentTime = Utility
				.getCurrentDateTime("yyyy_MM_ddHH_mm_ss");
		String userName = "Automation" + getcurrentTime;
		String email = "Automation" + getcurrentTime + "@kiwi.qa";

		logger.info("***Get the Request Payload***");
		String reqBody = UserResourceRequestPayload.getRequestPayload(userName,
				password, "test User", email);
		logger.info("***Create new user***");
		Response resp = UserResourceCommon.doPostCreateNewUser(reqBody);
		Assert.assertEquals(resp.getStatusCode(), 200);
		Assert.assertTrue(resp.jsonPath().get("userName").toString()
				.equalsIgnoreCase(userName), "User Name mismatch");
		Assert.assertTrue(resp.jsonPath().get("email").toString()
				.equalsIgnoreCase(email), "Email ID mismatch");
		Assert.assertTrue(resp.jsonPath().get("full_name").toString()
				.equalsIgnoreCase("test User"), "Test User mismatch");
		Assert.assertNotNull(resp.jsonPath().get("createdTime").toString());
		Assert.assertNotNull(resp.jsonPath().get("userid").toString());
		Assert.assertTrue(resp.jsonPath().get("created").toString()
				.equalsIgnoreCase("true"), "Not Created ");

		logger.info("***get user Details***");
		String userId = String.valueOf(resp.jsonPath().get("userId"));
		resp = UserResourceCommon.doGetUserByID(userId);
		Assert.assertEquals(resp.getStatusCode(), 200);
		Assert.assertNotNull(resp.jsonPath().get("createdTime").toString());
		Assert.assertTrue(resp.jsonPath().get("active").toString()
				.equalsIgnoreCase("true"), "User is not Active");
		Assert.assertTrue(resp.jsonPath().get("userId").toString()
				.equalsIgnoreCase(userId), "User id mismatch");

		logger.info("***Login with Correct Details***");

		reqBody = UserResourceRequestPayload.getRequestPayloadForLogin(
				userName, password);
		resp = UserResourceCommon.doPostNewUser(reqBody);
		Assert.assertEquals(resp.getStatusCode(), 200);
		Assert.assertTrue(resp.jsonPath().get("login").toString()
				.equalsIgnoreCase("true"), "Login Failed");

	}

	/*
	 * Test to create a new User with invalid userName
	 */

	@Test
	public void postCreateNewUserTestInvalidUserNameNegTest() {

		logger.info("##############################################################################");
		logger.info("UserAPI->Test to create New user with invalid Username");
		logger.info("##############################################################################");
		String getcurrentTime = Utility
				.getCurrentDateTime("yyyy_MM_ddHH_mm_ss");
		String userName = "@$#%^&";
		String email = "Automation" + getcurrentTime + "@kiwi.qa";

		logger.info("***Get the Request Payload***");
		String reqBody = UserResourceRequestPayload.getRequestPayload(userName,
				password, "test User", email);
		logger.info("***Create new user***");
		Response resp = UserResourceCommon.doPostCreateNewUser(reqBody);
		Assert.assertEquals(resp.getStatusCode(), 0);
		Assert.assertTrue(resp.jsonPath().get("created").toString()
				.equalsIgnoreCase("false"), "Created ");
	}

	/*
	 * Test to create a new User with invalid password
	 */

	@Test
	public void postCreateNewUserTestInvalidPasswordNegTest() {

		logger.info("##############################################################################");
		logger.info("UserAPI->Test to create New user with invalid Password");
		logger.info("##############################################################################");
		String getcurrentTime = Utility
				.getCurrentDateTime("yyyy_MM_ddHH_mm_ss");
		String userName = "Automation" + getcurrentTime;
		String email = "Automation" + getcurrentTime + "@kiwi.qa";

		logger.info("***Get the Request Payload***");
		String reqBody = UserResourceRequestPayload.getRequestPayload(userName,
				"wrongPassword", "test User", email);
		logger.info("***Create new user***");
		Response resp = UserResourceCommon.doPostCreateNewUser(reqBody);
		Assert.assertEquals(resp.getStatusCode(), 2);
		Assert.assertTrue(resp.jsonPath().get("created").toString()
				.equalsIgnoreCase("false"), "Created ");
	}

	/*
	 * Test to create a new User with invalid Full Name
	 */
	@Test
	public void postCreateNewUserTestInvalidFullNameNegTest() {

		logger.info("##############################################################################");
		logger.info("UserAPI->Test to create New user with invalid Full Name");
		logger.info("##############################################################################");
		String getcurrentTime = Utility
				.getCurrentDateTime("yyyy_MM_ddHH_mm_ss");
		String userName = "Automation" + getcurrentTime;
		String email = "Automation" + getcurrentTime + "@kiwi.qa";

		logger.info("***Get the Request Payload***");
		String reqBody = UserResourceRequestPayload.getRequestPayload(userName,
				password, "&^%%(*&(", email);
		logger.info("***Create new user***");
		Response resp = UserResourceCommon.doPostCreateNewUser(reqBody);
		Assert.assertEquals(resp.getStatusCode(), 3);
		Assert.assertTrue(resp.jsonPath().get("created").toString()
				.equalsIgnoreCase("false"), "Created ");
	}

	/*
	 * Test to create a new User with invalid Email
	 */
	@Test
	public void postCreateNewUserTestInvalidEmailNegTest() {

		logger.info("##############################################################################");
		logger.info("UserAPI->Test to create New user with invalid Email");
		logger.info("##############################################################################");
		String getcurrentTime = Utility
				.getCurrentDateTime("yyyy_MM_ddHH_mm_ss");
		String userName = "Automation" + getcurrentTime;

		logger.info("***Get the Request Payload***");
		String reqBody = UserResourceRequestPayload.getRequestPayload(userName,
				password, "test User", "^%&$*^&%&@*&^*&");
		logger.info("***Create new user***");
		Response resp = UserResourceCommon.doPostCreateNewUser(reqBody);
		Assert.assertEquals(resp.getStatusCode(), 4);
		Assert.assertTrue(resp.jsonPath().get("created").toString()
				.equalsIgnoreCase("false"), "Created ");
	}

	/*
	 * Test to Check login with invalid user Name
	 */
	@Test
	public void postLoginNewUserTestInvaliduserNameNegTest() {

		logger.info("##############################################################################");
		logger.info("UserAPI->Test to login  user with invalid userName");
		logger.info("##############################################################################");

		String reqBody = UserResourceRequestPayload.getRequestPayloadForLogin(
				"%&^*%^&%*&", password);
		Response resp = UserResourceCommon.doPostNewUser(reqBody);
		Assert.assertEquals(resp.getStatusCode(), 1);
		Assert.assertTrue(resp.jsonPath().get("login").toString()
				.equalsIgnoreCase("false"), "Login Success");

	}

	/*
	 * Test to Check login with invalid Password
	 */
	@Test
	public void postLoginNewUserTestInvalidPasswordNegTest() {

		logger.info("##############################################################################");
		logger.info("UserAPI->Test to login  user with invalid Password");
		logger.info("##############################################################################");
		String getcurrentTime = Utility
				.getCurrentDateTime("yyyy_MM_ddHH_mm_ss");
		String userName = "Automation" + getcurrentTime;

		String reqBody = UserResourceRequestPayload.getRequestPayloadForLogin(
				userName, "^%^&%&*%&");
		Response resp = UserResourceCommon.doPostNewUser(reqBody);
		Assert.assertEquals(resp.getStatusCode(), 2);
		Assert.assertTrue(resp.jsonPath().get("login").toString()
				.equalsIgnoreCase("false"), "Login Success");

	}

	/*
	 * Test to get User Details with invalid user ID Details
	 */
	@Test
	public void postGetUserDetailsWithInvalidIDNegTest() {

		logger.info("##############################################################################");
		logger.info("UserAPI->Test to Get User Details with Invalid ID");
		logger.info("##############################################################################");

		Response resp = UserResourceCommon.doGetUserByID("^&%&^%^&");
		Assert.assertEquals(resp.getStatusCode(), 1);

	}
}
