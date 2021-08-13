package com.todo.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;

public class BaseTest {

	// for Extent Report
	public static ExtentReports extent;
	public static ExtentTest extentTest;

	public static String authToken = null;
	public static String userToken = null;
	public static int taskid = 0;

	public static final Logger log = Logger.getLogger(BaseTest.class);

	// -------------------------Config properties file
	// creation----------------------------
	public static File file = new File(".\\src\\test\\resources\\config.properties");
	public static FileInputStream fis = null;
	public static Properties prop = new Properties();

	static {

		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// -----------------------------Define
	// URI----------------------------------------
	@BeforeMethod
	public void baseURI() {
		RestAssured.baseURI = prop.getProperty("BASE_URI"); // define the URI
		log.info("\n============ New Test Case==============\n");
	}
	
	//------------------------------ CREATING THE EXTENT REPORT-------------------------
	@BeforeSuite
	public void setExtent() {
		extent = new ExtentReports(".\\Reports\\ExtentReport.html");
		log.info("Report Started");
		
	}

	// ---------------------------GET REPORT RESULT----------------------------------
	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {

			extentTest.log(LogStatus.FAIL, "Test Case failed" + result.getName());
			extentTest.log(LogStatus.FAIL, "Test Case failed" + result.getThrowable());

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test case passed successfully");
		}
		extent.endTest(extentTest);

	}

	// --------------------------------STOP THE REPORT GENERATION------------------------------
	@AfterSuite
	public void endReport() {
		extent.flush();
		extent.close();

	}

}
