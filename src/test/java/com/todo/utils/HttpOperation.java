package com.todo.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.relevantcodes.extentreports.LogStatus;
import com.todo.tests.BaseTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HttpOperation extends BaseTest {
	public static final Logger log = Logger.getLogger(HttpOperation.class);

// ***************************AUTHENTICATION CONTROLLER*********************
	
	public static Response createAuthToken(Map<String, Object> jsonBody) {
		// getting response
		RequestSpecification httpRequest = RestAssured.given();
		log.info("Request Sent to to get access Token");
		extentTest.log(LogStatus.INFO, "Request Sent to to get access Token:- " + httpRequest);
		Response response = httpRequest.body(jsonBody).header("Content-Type", "application/json").when()
				.post(BaseTest.prop.getProperty("Auth_EndPoint")).then().log().all().extract().response();

		return response;
	}

// *******************************HELLO CONTROLLER************************
	
	public static Response hello(String authToken) {
		// getting response
		RequestSpecification httpRequest = RestAssured.given();
		log.info("Request Sent to Get hello message");
		extentTest.log(LogStatus.INFO, "Request Sent to Get hello message :- " + httpRequest);
		Response response = httpRequest.header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + authToken).when()
				.get(BaseTest.prop.getProperty("Hello_Endpoint")).then().log().all().extract().response();

		return response;
	}
	
//	****************************** USER CONTROLLER ************************
	

	public static Response createUser(String authToken, Map<String, Object> jsonBody) {
		// getting response
		RequestSpecification httpRequest = RestAssured.given();
		log.info("Request Sent to Create User");
		extentTest.log(LogStatus.INFO, "Request Sent to Create User :- " + httpRequest);
		Response response = httpRequest.body(jsonBody).header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + authToken).when()
				.post(BaseTest.prop.getProperty("CreateUser_EndPoint")).then().log().all().extract().response();

		return response;
	}


	public static Response loginUser(String authToken, Map<String, Object> jsonBody) {
		// getting response
		RequestSpecification httpRequest = RestAssured.given();
		log.info("Request Sent to Login User");
		extentTest.log(LogStatus.INFO, "Request Sent to Login User :- " + httpRequest);
		Response response = httpRequest.body(jsonBody).header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + authToken).when()
				.post(BaseTest.prop.getProperty("LoginUser_EndPoint")).then().log().all().extract().response();

		return response;
	}

//	****************************** TASK CONTROLLER ************************

	public static Response create_Task(String userToken, Map<String, Object> jsonBody) {
		// getting response
		RequestSpecification httpRequest = RestAssured.given();
		log.info("Request Sent to create Task" + httpRequest.toString());
		extentTest.log(LogStatus.INFO, "Request Sent to create Task:- " + httpRequest);
		Response response = httpRequest.body(jsonBody)
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + userToken)
				.when()
				.post(BaseTest.prop.getProperty("Task_EndPoint"))
				.then().log().all().extract()
				.response();
		return response;
	}

	public static Response delete_Task(int taskID, String userToken) {		
		// getting response
		RequestSpecification httpRequest = RestAssured.given();
		log.info("Request Sent to delete Task");
		extentTest.log(LogStatus.INFO, "Request Sent to delete Task:- " + httpRequest);
		
		Response response =httpRequest
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + userToken).when()
				.delete(BaseTest.prop.getProperty("Task_EndPoint") + taskid).then().log().all().extract()
				.response();

		return response;
	}

	public static Response edit_Task(String userToken, Map<String, Object> jsonBody) {
		// getting response
		RequestSpecification httpRequest = RestAssured.given();
		log.info("Request Sent to edit Task");
		extentTest.log(LogStatus.INFO, "Request Sent to edit Task:- " + httpRequest);
		Response response = httpRequest.body(jsonBody)
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + userToken)
				.when()
				.put(BaseTest.prop.getProperty("Task_EndPoint"))
				.then().log().all().extract()
				.response();

		return response;
	}

	public static Response fetch_Task(String userToken) {
	
		// getting response
		RequestSpecification httpRequest = RestAssured.given();
		log.info("Request Sent to fetch all user Task");
		extentTest.log(LogStatus.INFO, "Request Sent to fetch all user Task:- " + httpRequest);

		Response response = httpRequest
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + userToken)
				.when()
				.get(BaseTest.prop.getProperty("Task_EndPoint"))
				.then().log().all().extract().response();

		return response;
	}

	public static Response fetch_Task_by_ID(int taskID, String userToken) {
	
		// getting response
		RequestSpecification httpRequest = RestAssured.given();
		log.info("Request Sent to fetch user Task by ID");
		extentTest.log(LogStatus.INFO, "Request Sent to fetch user Task by ID:- " + httpRequest);

		Response response = httpRequest
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + userToken)
				.when()
				.get(BaseTest.prop.getProperty("Task_EndPoint") + taskID)
				.then().log().all().extract()
				.response();

		return response;
	}

}
