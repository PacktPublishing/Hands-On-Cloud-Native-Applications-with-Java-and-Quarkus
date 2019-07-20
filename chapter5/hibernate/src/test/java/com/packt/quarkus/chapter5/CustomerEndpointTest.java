package com.packt.quarkus.chapter5;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import javax.json.Json;
import javax.json.JsonObject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
<<<<<<< HEAD
import static org.hamcrest.CoreMatchers.is;
=======
>>>>>>> fc099194d947fb486088b902a424566c9b9fa3a7

@QuarkusTest
public class CustomerEndpointTest {

    @Test
    public void testCustomerService() {
<<<<<<< HEAD
/*
=======

>>>>>>> fc099194d947fb486088b902a424566c9b9fa3a7
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
<<<<<<< HEAD
*/
        // Test GET
=======

         // Test GET
>>>>>>> fc099194d947fb486088b902a424566c9b9fa3a7
        given()
                .when().get("/customers")
                .then()
                .statusCode(200)
<<<<<<< HEAD
                .body("$.size()", is(2));


        JsonObject objOrder = Json.createObjectBuilder()
=======
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
>>>>>>> fc099194d947fb486088b902a424566c9b9fa3a7
                .add("item", "bike")
                .add("price", new Long(100))
                .build();

<<<<<<< HEAD

        // Test POST Order for Customer #1
        given()
                .contentType("application/json")
                .body(objOrder.toString())
=======
        JsonObject objOrder2 = Json.createObjectBuilder()
                .add("item", "laptop")
                .add("price", new Long(500))
                .build();

        // Test POST
        given()
                .contentType("application/json")
                .body(objOrder1.toString())
>>>>>>> fc099194d947fb486088b902a424566c9b9fa3a7
                .when()
                .post("/orders/1")
                .then()
                .statusCode(201);

<<<<<<< HEAD
        // Create new JSON for Order #1
        objOrder = Json.createObjectBuilder()
                .add("id", new Long(1))
                .add("item", "mountain bike")
                .add("price", new Long(100))
                .build();

        // Test UPDATE Order #1
        given()
                .contentType("application/json")
                .body(objOrder.toString())
                .when()
                .put("/orders")
                .then()
                .statusCode(204);

        // Test GET for Order #1
        given()
                .when().get("/orders?customerId=1")
                .then()
                .statusCode(200)
                .body(containsString("mountain bike"));

        // Test DELETE Order #1
        given()
                .when().delete("/orders/1")
                .then()
                .statusCode(204);

=======

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
>>>>>>> fc099194d947fb486088b902a424566c9b9fa3a7


    }
}
