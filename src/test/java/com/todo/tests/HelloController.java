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

public class HelloController extends BaseTest {
	public static final Logger log = Logger.getLogger(HelloController.class);
	public static String auth_sheetName = "Auth_Data";
	public static String auth_valid_testName = "Valid Scenario- valid Username & Password";

	@Test(priority = 1)
	public static void helloTest() {

		log.info("Welcome Test");
		// report generation start
		extentTest = extent.startTest("Welcome Test");

		Response response = HttpOperation
				.createAuthToken(PayLoads.createAuthToken_Payload(extentTest, auth_sheetName, auth_valid_testName));
		String authToken = ReusableMethods.Auth(extentTest, response);

		// get the Token
		JsonPath jsonresp = ReusableMethods.rawToJson(response);
		authToken = jsonresp.get("jwt");
		log.info("Token created");
		extentTest.log(LogStatus.INFO, "Token created:-  " + authToken);

		// Assertion
		Assert.assertEquals(response.getStatusCode(), 200);
		log.info("Assertion Passed!!");
		extentTest.log(LogStatus.INFO, "HTTP Status Code:- " + response.getStatusCode());

		response = HttpOperation.hello(authToken);

		// Assertion
		Assert.assertEquals(response.getStatusCode(), 200);
		log.info("Assertion Passed!!");
		extentTest.log(LogStatus.INFO, "HTTP Status Code:- " + response.getStatusCode());

	}

}
