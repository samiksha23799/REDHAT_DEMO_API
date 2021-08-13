package com.todo.utils;


import org.apache.log4j.Logger;
import org.junit.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.todo.tests.BaseTest;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReusableMethods extends BaseTest{
	public static final Logger log = Logger.getLogger(ReusableMethods.class);

	// ________________________Return response in JSON format____________________

	public static JsonPath rawToJson(Response res) {
		String respon = (res).asString();
		JsonPath x = new JsonPath(respon);
		return x;
	}
	
	// 
	public static String Auth(ExtentTest extentTest, Response response) {
		
		// get the Token
		JsonPath jsonresp = ReusableMethods.rawToJson(response);
		authToken = jsonresp.get("jwt");
		extentTest.log(LogStatus.INFO, "Token Used:-  " + authToken);

		// Assertion
		Assert.assertTrue(response.body().asString().contains(authToken));
		Assert.assertEquals(response.getStatusCode(), 200);
		return authToken;
	}


}
