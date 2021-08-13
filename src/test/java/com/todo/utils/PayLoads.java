package com.todo.utils;

import java.util.HashMap;
import java.util.Map;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.todo.tests.BaseTest;

public class PayLoads extends BaseTest {
	

//---------------------Get access token payload-----------------
	public static Map<String, Object> createAuthToken_Payload(ExtentTest extentTest, String sheetName, String testName) {
		

		// Fetching Data from Excel File
		HashMap<String, String> testData = new HashMap<String, String>();
		testData = CommonUtils.reader.getRowTestData(sheetName, testName);
		String executionRequired = testData.get("Execution Required").toLowerCase();
		String username = testData.get("Username");
		String pwd = testData.get("Password");
		CommonUtils.toCheckExecutionRequired(executionRequired);
		log.info("Username:-  " + username);
		log.info("Password:- " + pwd);
		extentTest.log(LogStatus.INFO, "Username:- " +username +"\t Password:- " +pwd );

		Map<String, Object> jsonBody = new HashMap<String, Object>();
		jsonBody.put("username", username);
		jsonBody.put("password", pwd);
		return jsonBody;
	}

// ----------------User Controller Payload------------------

	// Create User
	public static Map<String, Object> create_user_Payload(String sheetName, String testName) {

		// Fetching Data from Excel File
		HashMap<String, String> testData = new HashMap<String, String>();
		testData = CommonUtils.reader.getRowTestData(sheetName, testName);
		String executionRequired = testData.get("Execution Required").toLowerCase();
		String email = testData.get("Email");
		String fn = testData.get("Firstname");
		String ln = testData.get("Lastname");
		String pwd = testData.get("Password");
		CommonUtils.toCheckExecutionRequired(executionRequired);

		Map<String, Object> jsonBody = new HashMap<String, Object>();
		jsonBody.put("email", email);
		jsonBody.put("firstName", fn);
		jsonBody.put("lastName", ln);
		jsonBody.put("password", pwd);
		log.info(jsonBody);

		return jsonBody;
	}

	// Login user
	public static Map<String, Object> login(String sheetName, String testName) {

		// Fetching Data from Excel File
		HashMap<String, String> testData = new HashMap<String, String>();
		testData = CommonUtils.reader.getRowTestData(sheetName, testName);
		String executionRequired = testData.get("Execution Required").toLowerCase();
		String email = testData.get("Email");
		String pwd = testData.get("Password");
		CommonUtils.toCheckExecutionRequired(executionRequired);

		Map<String, Object> jsonBody = new HashMap<String, Object>();
		jsonBody.put("email", email);
		jsonBody.put("password", pwd);
		log.info(jsonBody);
		return jsonBody;
	}

// ----------------Task Controller Payload------------------
	
	// Create Task
	public static Map<String, Object> create_task_Payload(String sheetName, String testName) {
		// Fetching Data from Excel File
		HashMap<String, String> testData = new HashMap<String, String>();
		testData = CommonUtils.reader.getRowTestData(sheetName, testName);
		String executionRequired = testData.get("Execution Required").toLowerCase();
		String taskName = testData.get("TaskName");
		String priority = testData.get("Priority").split("\\.")[0];
		String date = testData.get("Duedate");

		CommonUtils.toCheckExecutionRequired(executionRequired);
		log.info(testData);
		Map<String, Object> jsonBody = new HashMap<String, Object>();
		jsonBody.put("taskName", taskName);
		jsonBody.put("priority",priority );
		jsonBody.put("dueDate", date);
		return jsonBody;
	}

	// Edit Task
	public static Map<String, Object> edittask_Payload( int taskid, String sheetName, String testName) {
		// Fetching Data from Excel File
		HashMap<String, String> testData = new HashMap<String, String>();
		testData = CommonUtils.reader.getRowTestData(sheetName, testName);
		String executionRequired = testData.get("Execution Required").toLowerCase();
		String taskName = testData.get("TaskName");
		String priority = testData.get("Priority").split("\\.")[0];
		String date = testData.get("Duedate");
		CommonUtils.toCheckExecutionRequired(executionRequired);
		log.info(testData);		

		// Converting the data into JSON format
		Map<String, Object> jsonBody = new HashMap<String, Object>();
		jsonBody.put("id", taskid);
		jsonBody.put("taskName", taskName);
		jsonBody.put("priority", priority);
		jsonBody.put("dueDate", date);
		return jsonBody;
	}


	
}
