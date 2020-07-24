package br.com.core;

import java.util.UUID;

import org.hamcrest.Matchers;
import org.junit.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;

public class BaseTests implements Contantes{

	@BeforeClass
	public static void setup() {
		
		final String uuid = UUID.randomUUID().toString();
		RestAssured.baseURI = APP_URL_BASE;
		RestAssured.port = APP_PORT;
		RestAssured.basePath = APP_BASE_PATH;
		
		RequestSpecBuilder req = new RequestSpecBuilder();
		req.setContentType(APP_CONTENT_TYPE);
		RestAssured.requestSpecification = req.build();
		
		ResponseSpecBuilder res = new ResponseSpecBuilder();
		res.expectResponseTime(Matchers.lessThan(MAX_TIMEOUT));
		RestAssured.responseSpecification = res.build();
		
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		
	}
	
}
