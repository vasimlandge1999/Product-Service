package com.product;

import io.restassured.RestAssured;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

	@LocalServerPort
	private int port;

	@BeforeEach
	void setUp(){
		RestAssured.baseURI="http://localhost";
		RestAssured.port=port;
	}

	static {
		mongoDBContainer.start();
	}

	@Test
	void shoudCreateProduct() {
		String requestBody= """
				{
				    "name":"Samsung",
				    "description":"A35",
				    "price":23000
				}
				""";
		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/product")
				.then()
				.statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("name",Matchers.equalTo("Samsung"))
				.body("description",Matchers.equalTo("A35"));
	}

}
