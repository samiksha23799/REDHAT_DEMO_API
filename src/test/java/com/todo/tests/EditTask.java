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

public class EditTask extends BaseTest {

	public static String user_sheet = "User_Payload";

	public static String sheetName = "Task_Payload";

	public static String auth_sheetName = "Auth_Data";
	public static String auth_valid_testName = "Valid Scenario- valid Username & Password";

	public static final Logger log = Logger.getLogger(EditTask.class);

//==========================     VALID TEST CASES - Edit Task      =============================

	// Edit task
	@Test
	public static void edit_Task() {
		String testName = "Valid Scenario- When all the data are valid";
		log.info("Valid Scenario: Edit Task");
		// report generation start
		extentTest = extent.startTest("Valid Scenario: Edit Task");

		Response response = HttpOperation
				.createAuthToken(PayLoads.createAuthToken_Payload(extentTest, auth_sheetName, auth_valid_testName));
		String authToken = ReusableMethods.Auth(extentTest, response);

		// response for login the user
		response = HttpOperation.loginUser(authToken, PayLoads.create_user_Payload(user_sheet, testName));
		log.info("Response received for login");
		extentTest.log(LogStatus.INFO, "Response received for login:- " + response);

		// get the User Token
		JsonPath jp = ReusableMethods.rawToJson(response);
		userToken = jp.get("jwt");
		log.info("Received User Token:-  " + userToken);
		extentTest.log(LogStatus.INFO, "User Token:-  " + userToken);

		// Creating the Task response
		response = HttpOperation.create_Task(userToken, PayLoads.create_task_Payload(sheetName, testName));

		// get the User Token
		JsonPath jp1 = ReusableMethods.rawToJson(response);
		taskid = jp1.get("id");
		log.info("Received Task ID:-  " + taskid);
		extentTest.log(LogStatus.INFO, "TASK ID :-  " + taskid);

		// Response to edit the task
		response = HttpOperation.edit_Task(userToken, PayLoads.edittask_Payload( taskid, sheetName, testName));
		log.info("Response received for edit the task");
		extentTest.log(LogStatus.INFO, "Response received to edit the task:- " + response.asString());

		// Assertion
		Assert.assertEquals(response.getStatusCode(), 200);
		log.info("Assertion Passed!!");
		extentTest.log(LogStatus.INFO, "HTTP Status Code:- " + response.getStatusCode());

		// Response to delete the task
		response = HttpOperation.delete_Task(taskid, userToken);
		log.info("Response received for delete the task" + response.asString());

		// Assertion
		Assert.assertEquals(response.getStatusCode(), 200);

	}

}
