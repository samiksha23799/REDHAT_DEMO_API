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

public class UserController extends BaseTest {
	public static final Logger log = Logger.getLogger(UserController.class);
	public static String sheetName = "User_Payload";
	public static String auth_sheetName = "Auth_Data";
	public static String auth_valid_testName = "Valid Scenario- valid Username & Password";

// ------------------VALID SCENARIO-----------------------------------

	// Create user by using valid request body
	@Test
	public static void createUser() {
		String testName = "Valid Scenario- When all the data are valid";
		log.info("Valid Scenario- Create User");
		// report generation start
		extentTest = extent.startTest("Valid Scenario- Create User");

		Response response = HttpOperation
				.createAuthToken(PayLoads.createAuthToken_Payload(extentTest, auth_sheetName, auth_valid_testName));
		String authToken = ReusableMethods.Auth(extentTest, response);

		// get the Token
		JsonPath jsonresp = ReusableMethods.rawToJson(response);
		authToken = jsonresp.get("jwt");
		log.info("Token created");
		extentTest.log(LogStatus.INFO, "Token created:-  " + authToken);

		// response for creating the user
		response = HttpOperation.createUser(authToken, PayLoads.create_user_Payload(sheetName, testName));
		log.info("Response received to create User");
		extentTest.log(LogStatus.INFO, "Response received to create User:- " + response.asString());

		// Assertion
		Assert.assertEquals(response.getStatusCode(), 201);
		log.info("Assertion Passed!!");
		extentTest.log(LogStatus.INFO, "HTTP Status Code:- " + response.getStatusCode());
	}

	// login user by using valid username and password
	@Test
	public static void loginUser() {
		String testName = "Valid Scenario- When all the data are valid";
		log.info("Valid Scenario- Login User");
		// report generation start
		extentTest = extent.startTest("Valid Scenario- Login User");

		Response response = HttpOperation
				.createAuthToken(PayLoads.createAuthToken_Payload(extentTest, auth_sheetName, auth_valid_testName));
		String authToken = ReusableMethods.Auth(extentTest, response);

		// response for login the user
		response = HttpOperation.loginUser(authToken, PayLoads.login(sheetName, testName));
		log.info("Response received for login");
		extentTest.log(LogStatus.INFO, "Response received for login:- " + response.asString());

		/// get the Token
		JsonPath jp = ReusableMethods.rawToJson(response);
		userToken = jp.get("jwtToken");
		log.info("Received User Token" + userToken);
		extentTest.log(LogStatus.INFO, "Received User Token:- " + userToken);

		// Assertion
		Assert.assertEquals(response.getStatusCode(), 200);
		log.info("Assertion Passed!!");
		extentTest.log(LogStatus.INFO, "HTTP Status Code:- " + response.getStatusCode());
	}

//--------------------INVALID SCENARIO-------------------

	// Create User by entering invalid email
	@Test
	public static void invalid_createUser_email() {
		String testName = "Invalid Scenario- With invalid email";

		log.info("Invalid Scenario: Create User - with Invalid Email ");
		// report generation start
		extentTest = extent.startTest("Invalid Scenario: Create User - with Invalid Email  ");

		Response response = HttpOperation
				.createAuthToken(PayLoads.createAuthToken_Payload(extentTest, auth_sheetName, auth_valid_testName));
		String authToken = ReusableMethods.Auth(extentTest, response);

		// response for creating the user
		response = HttpOperation.createUser(authToken, PayLoads.create_user_Payload(sheetName, testName));
		log.info("Response received to create User");
		extentTest.log(LogStatus.INFO, "Response received to create User:- " + response.asString());

		// Assertion
		Assert.assertEquals(response.getStatusCode(), 406);
		log.info("Assertion Passed!!");
		extentTest.log(LogStatus.INFO, "HTTP Status Code:- " + response.getStatusCode());
	}

	// Create User by entering invalid firstname
	@Test
	public static void invalid_createUser_firstname() {
		String testName = "Invalid Scenario- With Invalid Firstname";

		log.info("Invalid Scenario: Create User - with Invalid FirstName ");
		// report generation start
		extentTest = extent.startTest("Invalid Scenario: Create User - with Invalid FirstName  ");

		Response response = HttpOperation
				.createAuthToken(PayLoads.createAuthToken_Payload(extentTest, auth_sheetName, auth_valid_testName));
		String authToken = ReusableMethods.Auth(extentTest, response);

		// response for creating the user
		response = HttpOperation.createUser(authToken, PayLoads.create_user_Payload(sheetName, testName));
		log.info("Response received to create User");
		extentTest.log(LogStatus.INFO, "Response received to create User:- " + response.asString());

		// Assertion
		Assert.assertEquals(response.getStatusCode(), 406);
		log.info("Assertion Passed!!");
		extentTest.log(LogStatus.INFO, "HTTP Status Code:- " + response.getStatusCode());
	}

	// Create User by entering invalid lastname
	@Test
	public static void invalid_createUser_lastname() {
		String testName = "Invalid Scenario- With Invalid Lastname";

		log.info("Invalid Scenario: Create User - with Invalid LastName ");
		// report generation start
		extentTest = extent.startTest("Invalid Scenario: Create User - with Invalid LastName  ");

		Response response = HttpOperation
				.createAuthToken(PayLoads.createAuthToken_Payload(extentTest, auth_sheetName, auth_valid_testName));
		String authToken = ReusableMethods.Auth(extentTest, response);

		// response for creating the user
		response = HttpOperation.createUser(authToken, PayLoads.create_user_Payload(sheetName, testName));
		log.info("Response received to create User");
		extentTest.log(LogStatus.INFO, "Response received to create User:- " + response.asString());

		// Assertion
		Assert.assertEquals(response.getStatusCode(), 406);
		log.info("Assertion Passed!!");
		extentTest.log(LogStatus.INFO, "HTTP Status Code:- " + response.getStatusCode());

	}

	// Create User by entering invalid Password
	@Test
	public static void invalid_createUser_password() {
		String testName = "Invalid Scenario- With Invalid Password";

		log.info("Invalid Scenario: Create User - with Invalid Password ");
		// report generation start
		extentTest = extent.startTest("Invalid Scenario: Create User - with Invalid Password  ");

		Response response = HttpOperation
				.createAuthToken(PayLoads.createAuthToken_Payload(extentTest, auth_sheetName, auth_valid_testName));
		String authToken = ReusableMethods.Auth(extentTest, response);

		// response for creating the user
		response = HttpOperation.createUser(authToken, PayLoads.create_user_Payload(sheetName, testName));
		log.info("Response received to create User");
		extentTest.log(LogStatus.INFO, "Response received to create User:- " + response.asString());

		// Assertion
		Assert.assertEquals(response.getStatusCode(), 406);
		log.info("Assertion Passed!!");
		extentTest.log(LogStatus.INFO, "HTTP Status Code:- " + response.getStatusCode());

	}

	// Login User by entering Invalid Password
	@Test
	public static void invalid_loginUser_Password() {
		String testName = "Invalid Scenario- With Invalid Password";

		log.info("Invalid Scenario-Login User With invalid password");
		// report generation start
		extentTest = extent.startTest("Invalid Scenario-Login User With invalid password");

		Response response = HttpOperation
				.createAuthToken(PayLoads.createAuthToken_Payload(extentTest, auth_sheetName, auth_valid_testName));
		String authToken = ReusableMethods.Auth(extentTest, response);

		// response for login the user
		response = HttpOperation.loginUser(authToken, PayLoads.login(sheetName, testName));
		log.info("Response received for login");
		extentTest.log(LogStatus.INFO, "Response received for login:- " + response.asString());


		// Assertion
		Assert.assertEquals(response.getStatusCode(), 406);
		log.info("Assertion Passed!!");
		extentTest.log(LogStatus.INFO, "HTTP Status Code:- " + response.getStatusCode());
	}

	// Login User by entering Invalid Email
	@Test
	public static void invalid_loginUser_email() {
		String testName = "Invalid Scenario- With invalid email";

		log.info("Invalid Scenario-Login User With invalid email");
		// report generation start
		extentTest = extent.startTest("Invalid Scenario-Login User With invalid email");

		Response response = HttpOperation
				.createAuthToken(PayLoads.createAuthToken_Payload(extentTest, auth_sheetName, auth_valid_testName));
		String authToken = ReusableMethods.Auth(extentTest, response);
		// response for login the user
		response = HttpOperation.loginUser(authToken, PayLoads.login(sheetName, testName));
		log.info("Response received for login");
		extentTest.log(LogStatus.INFO, "Response received for login:- " + response.asString());


		// Assertion
		Assert.assertEquals(response.getStatusCode(), 404);
		log.info("Assertion Passed!!");
		extentTest.log(LogStatus.INFO, "HTTP Status Code:- " + response.getStatusCode());
	}

}
