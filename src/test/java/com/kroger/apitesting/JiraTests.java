package com.kroger.apitesting;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class JiraTests extends PetStoreTests2{
	
	
	@Test

	public void getJiraCardPositive() {

		given()
		.when()
			.get("/2323024")
			.then()
				.log().all()
				.statusCode(200)
				.and()
				.body("id", equalTo(2323024));
				
	}
	

}
