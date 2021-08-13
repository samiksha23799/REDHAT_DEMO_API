package com.todo.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.SkipException;

import com.relevantcodes.extentreports.LogStatus;
import com.todo.tests.BaseTest;

public class CommonUtils extends BaseTest {

	// Excel File Test Data Reader
	public static ExcelHandler reader = null;

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

		// Exception Handling for Excel File
		try {

			reader = new ExcelHandler(prop.getProperty("EXCEL_PATH"));
		} catch (Exception e) {

			log.error(e.getMessage());
		}
	}

	// function toCheckExecutionRequired
	public static void toCheckExecutionRequired(String executionRequired) {

		// if execution required field is no
		if (executionRequired.equals("no")) {

			extentTest.log(LogStatus.WARNING, "Execution Required : " + executionRequired.toUpperCase());
			log.info("Execution required is no , skipping the test");
			throw new SkipException("Skipping this exception");
		}

		// if execution required field is empty
		if (executionRequired.equals("")) {

			extentTest.log(LogStatus.WARNING, "Execution Required : " + executionRequired.toUpperCase());
			log.info("Execution required is empty , skipping the test");
			throw new SkipException("Skipping this exception");
		}
	}

}
