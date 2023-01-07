package com.employeeapi.Test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.BaseClass;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC_01_GET_All_Employees extends BaseClass {
	@BeforeClass
	public void getAllEmployees() throws InterruptedException {
		logger.info("************ started TC001_GET_All_Employees ***************");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httprequest = RestAssured.given();
        response = httprequest.request(Method.GET,"/employees");
        Thread.sleep(3000);
        
	}
	@Test
	public void checkResponsebody() {
		logger.info("************ checking response body ***************");
		String responsebody=response.getBody().asString();
		logger.info("ResponseBody :" + responsebody);
		Assert.assertTrue(responsebody!=null);
	
	}
	@Test
	public void checkStatusCode() {
		logger.info("************ checking status code ***************");
		int statuscode =response.statusCode();
		logger.info("statuscode  :" + statuscode);
		Assert.assertEquals(statuscode, 200);
	
	}
	@Test
	public void checkResponsetime() {
		logger.info("************ checking response time ***************");
		long responsetime =response.getTime();
		logger.info("responsetime  :" + responsetime);
		
		if(responsetime>=2000) {
			logger.info("response time is greater than 2000");
		
			Assert.assertTrue(responsetime<2000);
		}
		}
	@Test
		public void checkStatusline() {
			logger.info("************ checking status line ***************");
			String statusline =response.statusLine();
			logger.info("statusline  :" + statusline);
			Assert.assertEquals(statusline, "HTTP/1.1 200 OK");
		
		}
	
	}

