package com.employeeapi.Test;

import org.apache.commons.codec.binary.Base16;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.BaseClass;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class TC_05_DELETE_EmployeeRecord extends BaseClass {

	@BeforeClass
	public void deleteEmployeeRecords() throws InterruptedException {

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httprequest = RestAssured.given();

		response = httprequest.request(Method.GET, "/employees");

		// first get the json object from the response interface
		JsonPath jsonPathEvaluator = response.jsonPath();
		// capture id
		String empID = jsonPathEvaluator.get("[0].id");

		response = httprequest.request(Method.DELETE, "/delete/" + empID);
		// pass id to delete record
		Thread.sleep(3000);

	}

	@Test
	void checkResponseBody() {

		String responsebody = response.getBody().asString();
		Assert.assertEquals(responsebody.contains("successfully! deleted Records"), true);

	}

	@Test
	void checkStatuscode()
	{
		int statuscode = response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
		
	}
	@Test
	void checkstatusline() {
		String statusline= response.getStatusLine();
		Assert.assertEquals(statusline, "HTTP/1.1 200 OK");

	}
@Test 
void checkserverType() {
	String serverType = response.header("Server");
	Assert.assertEquals(serverType, "nginx/1.14.1");
}


@Test 
void checkContentEncoding() {
String contentEncoding = response.header("Content-Encoding");
Assert.assertEquals(contentEncoding, "gzip");
}

@AfterClass
void teardown()
{
	logger.info("*****TC_05_DELETE_Employee data finished********");
}

}
