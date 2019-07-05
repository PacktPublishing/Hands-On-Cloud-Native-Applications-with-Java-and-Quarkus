package com.packt.quarkus.chapter5;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import javax.json.Json;
import javax.json.JsonObject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class CustomerEndpointTest {

    @Test
    public void testCustomerService() {

        // Customer #2 ( customer #1 created by import.sql)
        JsonObject obj = Json.createObjectBuilder()
                .add("name", "John")
                .add("surname", "Smith").build();


        // Test POST
        given()
                .contentType("application/json")
                .body(obj.toString())
                .when()
                .post("/customers")
                .then()
                .statusCode(201);

         // Test GET
        given()
                .when().get("/customers")
                .then()
                .statusCode(200)
                .body(containsString("John"),
                      containsString("Smith"));

        // Test GET2
        given()
                .when().get("/customers")
                .then()
                .statusCode(200)
                .body(containsString("John"),
                        containsString("Doe"));


        JsonObject objOrder1 = Json.createObjectBuilder()
                .add("item", "bike")
                .add("price", new Long(100))
                .build();

        JsonObject objOrder2 = Json.createObjectBuilder()
                .add("item", "laptop")
                .add("price", new Long(500))
                .build();

        // Test POST
        given()
                .contentType("application/json")
                .body(objOrder1.toString())
                .when()
                .post("/orders/1")
                .then()
                .statusCode(201);


        // Test GET
        given()
                .when().get("/orders?customerId=1")
                .then()
                .statusCode(200);



        // Test POST
        given()
                .contentType("application/json")
                .body(objOrder2.toString())
                .when()
                .post("/orders/2")
                .then()
                .statusCode(201);

        // Test GET2
        given()
                .when().get("/orders?customerId=2")
                .then()
                .statusCode(200);


    }
}
