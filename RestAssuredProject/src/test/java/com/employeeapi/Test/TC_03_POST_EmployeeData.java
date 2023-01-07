package com.employeeapi.Test;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.BaseClass;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured; 	
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class TC_03_POST_EmployeeData extends BaseClass {

	RequestSpecification httprequest;
	Response response;

	String empName = RestUtils.empName();
	String empSal = RestUtils.empSal();
	String empAge = RestUtils.empAge();

	@BeforeClass
	void createEmployee() throws InterruptedException {
		logger.info("*********TC_03_POST_EmployeeData started **********");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httprequest = RestAssured.given();

		// JSONObject is class that represent the Simple JSON,we can add kay- value pair
		// using the put method
		// {"name": "johnxyz", "salary":"23455","age":"23"}
		JSONObject requestparam = new JSONObject();
		
		requestparam.put("name", empName);
		requestparam.put("salary", empSal);
		requestparam.put("age", empAge);

		// Add a header stating a Request body in JSON
		httprequest.header("Content-Type", "application/json");

		// Add json to the body of request
		httprequest.body(requestparam.toJSONString());

		response = httprequest.request(Method.POST, "/create");
		Thread.sleep(5000);

	}

	@Test
	void checkResposebody() {
		String responsebody = response.getBody().asString();
		Assert.assertEquals(responsebody.contains(empName), true);
		Assert.assertEquals(responsebody.contains(empSal), true);
		Assert.assertEquals(responsebody.contains(empAge), true);

	}

	@Test
	void checkStatuscode() {

		int statuscode = response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
	}

	@Test
	void checkStatusline() {

		String statusline = response.getStatusLine();
		Assert.assertEquals(statusline, "HTTP/1.1 200 OK");
	}

	@Test
	void checkContentType() {

		String contenttype = response.header("Content-Type");
		Assert.assertEquals(contenttype, "application/json");
	}

	@AfterClass
	void teardown() {
		logger.info("******finished TC_03_ POST_employee*******");
	}

}
