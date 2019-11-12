package com.packt.chapter8;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
@QuarkusTest
public class AdvancedConfigTest {

    @Test
    public void testHelloEndpoint() {

        // You need to copy the file customconfig.properties to the /tmp folder
        given()
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body(is("custom greeting"));

        given()
                .when().get("/year")
                .then()
                .body(is("2020"));

        given()
                .pathParam("id", 1)
                .when().get("/pets/{id}")
                .then()
                .body(is("cat"));

        given()
                .when().get("/students")
                .then()
                .body(is("Lucy"));

        given()
                .when().get("/email")
                .then()
                .body(is("johnsmith@gmail.com"));

        given()
                .when().get("/isUser")
                .then()
                .body(is("true"));

    }

}
