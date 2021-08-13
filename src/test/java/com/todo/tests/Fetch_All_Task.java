package com.todo.tests;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;
import com.todo.utils.HttpOperation;
import com.todo.utils.PayLoads;
import com.todo.utils.ReusableMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Fetch_All_Task extends BaseTest {

	public static String user_sheet = "User_Payload";

	public static String task_sheetName = "Task_Payload";

	public static String auth_sheetName = "Auth_Data";
	public static String auth_valid_testName = "Valid Scenario- valid Username & Password";

	public static final Logger log = Logger.getLogger(Fetch_All_Task.class);

//==========================     VALID TEST CASES - Fetch All Task of a User     =============================

	// Fetch all Task
	@Test
	public static void fetch_Task() {
		String user_testName = "Valid Scenario- When all the data are valid";
		log.info("Valid Scenario: Fetch All User Task");
		// report generation start
		extentTest = extent.startTest("Valid Scenario: Fetch All User Task");

		Response response = HttpOperation
				.createAuthToken(PayLoads.createAuthToken_Payload(extentTest, auth_sheetName, auth_valid_testName));
		String authToken = ReusableMethods.Auth(extentTest, response);

		// response for login the user
		response = HttpOperation.loginUser(authToken, PayLoads.login(user_sheet, user_testName));
		log.info("Response received for login");
		extentTest.log(LogStatus.INFO, "Response received for login:- " + response);

		// get the User Token
		JsonPath jp = ReusableMethods.rawToJson(response);
		userToken = jp.get("jwt");
		log.info("Received User Token:-  " + userToken);
		extentTest.log(LogStatus.INFO, "User Token:-  " + userToken);

		// Response to edit the task
		response = HttpOperation.fetch_Task(userToken);
		log.info("Response received to get all the user task");
		extentTest.log(LogStatus.INFO, "Response received to get all the user task:- " + response.asString());

		// Assertion
	//	SoftAssert sa = new SoftAssert(); 
		Assert.assertEquals(response.getStatusCode(), 200);
		log.info("Assertion Passed!!");
		extentTest.log(LogStatus.INFO, "HTTP Status Code:- " + response.getStatusCode());

	}



}
