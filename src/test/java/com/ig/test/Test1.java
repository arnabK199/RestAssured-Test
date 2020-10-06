package com.ig.test;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import utils.CommonMethods;


public class Test1 extends BaseTest{
	
	
	public String baseUrl;
	
	// Response Spec for asserting the reasponse headers
	static ResponseSpecBuilder resBuilder;
	static ResponseSpecification resSpec;
	
	public Test1() throws IOException {
		
		try{
			resBuilder = new ResponseSpecBuilder();
			resBuilder.expectHeader("Content-Type", "application/json; charset=UTF-8");
			resBuilder.expectHeader("Server", "Google Frontend");
			
			resSpec = resBuilder.build();
			
			baseUrl = prop.getProperty("base_url");
			
			RestAssured.baseURI = baseUrl;
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void Mytest1() {
		
		given()
		.when()
		.get("/apitest")
		.then()
		.log()
		.ifError()
		.assertThat()
		.statusCode(200);
	}
	
	@Test
	public void Mytest2() {
		
		given()
		.when()
		.get("/apitest")
		.then()
		.spec(resSpec)
		.log()
		.ifError();
	}
	
	@Test
	public void Mytest3() {
		
		Response res = given()
		.when()
		.get("/apitest")
		.then()
		.log()
		.ifError()
		.extract()
		.response();
		
		JsonPath jspath = CommonMethods.JsonParser(res);
		
		Assert.assertEquals("200", jspath.get("status").toString());
		Assert.assertEquals("25", jspath.get("employeeData[0].age").toString());
		Assert.assertEquals("QA Automation Developer", jspath.get("employeeData[0].role"));
		Assert.assertEquals("25-02-1994", jspath.get("employeeData[0].dob"));
		Assert.assertEquals("data retrieved successful", jspath.get("message"));

	}
	
	@Test
	public void Mytest4() {
		
		Response res = given()
		.when()
		.get("/apitest")
		.then()
		.log()
		.ifError()
		.extract()
		.response();
		
		JsonPath jspath = CommonMethods.JsonParser(res);
		
		Assert.assertEquals("ABC Infotech", jspath.get("employeeData[0].company"));
		
	}

}
