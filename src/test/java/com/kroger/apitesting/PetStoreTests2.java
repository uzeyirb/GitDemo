package com.kroger.apitesting;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class PetStoreTests2 {
	
	@BeforeClass
	
	public void setUp() {
		
		RestAssured.baseURI="https://petstore.swagger.io";
		RestAssured.basePath="/v2/pet";
	}
	
	@Test

	public void getPetPositive() {

		given().when().get("/2323024").then().statusCode(200);

	}

	@Test

	public void getPetPositive2() {

		given()
		.when()
			.get("/2323024")
			.then()
				.statusCode(200);

	}
	
	@Test

	public void getPetPositive2WithLog() {

		given()
		.when()
			.get("/2323024")
			.then()
				.log().all()
				.statusCode(200)
				.and()
				.body("id", equalTo(2323024));
				
	}
	
	@Test

	public void getPetNegative2WithLog() {

		given()
		.when()
			.get("/2323024")
			.then()
				.log().all()
				.statusCode(not(equalTo(404)))
				.and()
				.body("id", equalTo(2323024));
				
	}
	
	@Test

	public void postPetPositiveWithLog() {

		given()
		.contentType(ContentType.JSON)
		.body("{\r\n" + 
				"    \"id\": 2323025,\r\n" + 
				"    \"category\": {\r\n" + 
				"        \"id\": 0,\r\n" + 
				"        \"name\": \"string\"\r\n" + 
				"    },\r\n" + 
				"    \"name\": \"Katzen\",\r\n" + 
				"    \"photoUrls\": [\r\n" + 
				"        \"string\"\r\n" + 
				"    ],\r\n" + 
				"    \"tags\": [\r\n" + 
				"        {\r\n" + 
				"            \"id\": 0,\r\n" + 
				"            \"name\": \"string\"\r\n" + 
				"        }\r\n" + 
				"    ],\r\n" + 
				"    \"status\": \"available\"\r\n" + 
				"}")
		.when()
			.post()
		.then()
			.log().all()
			.statusCode(200)
			.and()
			.body("id", equalTo(2323025));
				
	}
	
	@Test

	public void putPetPositiveWithLog() {

		given()
		.contentType(ContentType.JSON)
		.body("{\r\n" + 
				"    \"id\": 2323025,\r\n" + 
				"    \"category\": {\r\n" + 
				"        \"id\": 0,\r\n" + 
				"        \"name\": \"string\"\r\n" + 
				"    },\r\n" + 
				"    \"name\": \"Scwartz Katzen\",\r\n" + 
				"    \"photoUrls\": [\r\n" + 
				"        \"string\"\r\n" + 
				"    ],\r\n" + 
				"    \"tags\": [\r\n" + 
				"        {\r\n" + 
				"            \"id\": 0,\r\n" + 
				"            \"name\": \"string\"\r\n" + 
				"        }\r\n" + 
				"    ],\r\n" + 
				"    \"status\": \"available\"\r\n" + 
				"}")
		.when()
			.put()
		.then()
			.log().all()
			.statusCode(200)
			.and()
			.body("id", equalTo(2323025));
				
	}
	
	
	@Test

	public void getDeletePutPetPositive2WithLog() {

		given()
		.when()
			.delete("/2323025")
			.then()
				.log().all();
				
	}
	
	@Test

	public void getDeletePutPetPositive2WithLog2() {

		given()
		.when()
			.get("/2323025")
			.then()
				.log().all()
				.statusCode(404);
				
	}
	

}
