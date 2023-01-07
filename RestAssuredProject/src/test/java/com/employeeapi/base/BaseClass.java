package com.employeeapi.base;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {
	public static RequestSpecification httprequest;
	public static Response response;
	public String empId= "1"; // hard coded this for fetch data single employee

	public Logger logger;
	@BeforeClass
	public void setUp() {
		logger= Logger.getLogger("EmployeeRestApi");
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.DEBUG);
	}
}
