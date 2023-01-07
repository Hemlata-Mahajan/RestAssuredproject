package com.employeeapi.Test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.BaseClass;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC_02_GET_Sinfgle_EmployeeRecord extends BaseClass {
	
	
			@BeforeClass
		public void getAllEmployees() throws InterruptedException {
			logger.info("************ started TC001_GET__Single Employees Record*************");
			RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
	        httprequest = RestAssured.given();
	        response = httprequest.request(Method.GET,"/employee/"+empId);
	        Thread.sleep(3000);
	        
		}
		@Test
		public void checkResponsebody() {
			logger.info("************ checking response body ***************");
			String responsebody=response.getBody().asString();
			logger.info("ResponseBody :" + responsebody);
			Assert.assertEquals(responsebody.contains(empId),true);
		
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
		@Test
		public void checkContentType() {
			logger.info("************ checking content type ***************");
			String content_type =response.header("Content-Type");
			logger.info("statusline  :" + content_type);
			Assert.assertEquals(content_type, "txt/html; charset=UTF-8");
		
		}
		@Test
		public void checkServertype() {
			logger.info("************ checking server type***************");
			String servertype =response.header("Server");
			logger.info("servertype :" + servertype);
			Assert.assertEquals(servertype, "nginx/1.14.1");
		
		}
		@Test
		public void checkContentlength() {
			logger.info("************ checking Content lenght***************");
			String Content_Length =response.header("Content-Length");
			logger.info("Content-Length :" + Content_Length);
			Assert.assertTrue(Integer.parseInt(Content_Length)<1500);
		
		}
		@AfterClass
		public void teardown() {
			logger.info("*****Finished TC002_Single_Employee_Record***********");
			
		}
		
		
		}
