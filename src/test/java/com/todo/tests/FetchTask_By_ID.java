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

public class FetchTask_By_ID extends BaseTest {
	public static String user_sheet = "User_Payload";

	public static String task_sheetName = "Task_Payload";

	public static String auth_sheetName = "Auth_Data";
	public static String auth_valid_testName = "Valid Scenario- valid Username & Password";

	public static final Logger log = Logger.getLogger(FetchTask_By_ID.class);

//==========================     VALID TEST CASES - Fetch  Task of a User     =============================

	// Fetch all Task
	@Test
	public static void fetch_Task() {
		String task_testName = "Valid Scenario- When all the data are valid";
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

		// Creating the Task response
		response = HttpOperation.create_Task(userToken, PayLoads.create_task_Payload(task_sheetName, task_testName));

		// get the User Token
		JsonPath jp1 = ReusableMethods.rawToJson(response);
		taskid = jp1.get("id");
		log.info("Received Task ID:-  " + taskid);
		extentTest.log(LogStatus.INFO, "TASK ID :-  " + taskid);

		// Response to edit the task
		response = HttpOperation.fetch_Task_by_ID(taskid, userToken);
		log.info("Response received to get all the user task");
		extentTest.log(LogStatus.INFO, "Response received to get all the user task:- " + response.asString());

		// Assertion
		Assert.assertEquals(response.getStatusCode(), 200);

		// Response to delete the task
		response = HttpOperation.delete_Task(taskid, userToken);
		log.info("Response received for delete the task" + response.asString());

		// Assertion
		Assert.assertEquals(response.getStatusCode(), 200);
		log.info("Assertion Passed!!");
		extentTest.log(LogStatus.INFO, "HTTP Status Code:- " + response.getStatusCode());

	}

}
