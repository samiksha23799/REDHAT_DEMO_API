package com.todo.tests;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.todo.utils.HttpOperation;
import com.todo.utils.PayLoads;
import com.todo.utils.ReusableMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Authenticate_Token extends BaseTest {
	public static final Logger log = Logger.getLogger(Authenticate_Token.class);
	public static String auth_sheetName = "Auth_Data";

	@Test
	public static void getAccessToken() {
		String auth_testName = "Valid Scenario- valid Username & Password";
		log.info("Valid Scenario:Get Access Token");
		// report generation start
		extentTest = extent.startTest("Valid Scenario:Get Access Token");

		// getting response
		Response response = HttpOperation
				.createAuthToken(PayLoads.createAuthToken_Payload(extentTest, auth_sheetName, auth_testName));

		log.info("Response received");
		extentTest.log(LogStatus.INFO, "Response received:- " + response.asString());

		// get the Token
		JsonPath jsonresp = ReusableMethods.rawToJson(response);
		authToken = jsonresp.get("jwt");
		log.info("Token created");
		extentTest.log(LogStatus.INFO, "Token created:-  " + authToken);

		// Assertion
		Assert.assertTrue(response.body().asString().contains(authToken));
		Assert.assertEquals(response.getStatusCode(), 200);
		log.info("Assertion Passed!!");
		extentTest.log(LogStatus.INFO, "HTTP Status Code:- " + response.getStatusCode());
	}

	@Test
	public static void invalid_getAccessToken() {
		String auth_testName = "Invalid Scenario- Valid Username &  invalid Password";
		log.info("Invalid Scenario : Get Access Token - When user enters the wrong password.");
		// report generation start
		extentTest = extent.startTest("Invalid Scenario : Get Access Token - When user enters the wrong password.");

		// getting response
				Response response = HttpOperation
						.createAuthToken(PayLoads.createAuthToken_Payload(extentTest, auth_sheetName, auth_testName));

		log.info("Response received");
		extentTest.log(LogStatus.INFO, "Response received:- " + response.asString());

		// Assertion
		Assert.assertEquals(response.getStatusCode(), 403);
		log.info("Assertion Passed!!");
		extentTest.log(LogStatus.INFO, "HTTP Status Code:- " + response.getStatusCode());

	}

	@Test
	public static void invalid_getAccessToken1() {
		String auth_testName = "Invalid Scenario- invalid Username & invalid Password";
		log.info("Invalid Scenario : Get Access Token - When user enters the invalid password and username.");
		// report generation start
		extentTest = extent
				.startTest("Invalid Scenario : Get Access Token - When user enters the invalid password and username.");

		// getting response
		Response response = HttpOperation
				.createAuthToken(PayLoads.createAuthToken_Payload(extentTest, auth_sheetName, auth_testName));

		log.info("Response received");
		extentTest.log(LogStatus.INFO, "Response received:- " + response.asString());

		// Assertion

		Assert.assertEquals(response.getStatusCode(), 403);
		log.info("Assertion Passed!!");
		extentTest.log(LogStatus.INFO, "HTTP Status Code:- " + response.getStatusCode());

	}

	@Test
	public static void invalid_getAccessToken2() {
		String auth_testName = "Invalid Scenario- invalid Username & Valid Password";
		log.info("Invalid Scenario : Get Access Token - When user enters the invalid username.");
		// report generation start
		extentTest = extent.startTest("Invalid Scenario : Get Access Token - When user enters the invalid username.");

		// getting response
		Response response = HttpOperation
				.createAuthToken(PayLoads.createAuthToken_Payload(extentTest, auth_sheetName, auth_testName));

		log.info("Response received");
		extentTest.log(LogStatus.INFO, "Response received:- " + response.asString());

		// Assertion
		Assert.assertEquals(response.getStatusCode(), 403);
		log.info("Assertion Passed!!");
		extentTest.log(LogStatus.INFO, "HTTP Status Code:- " + response.getStatusCode());

	}

}
