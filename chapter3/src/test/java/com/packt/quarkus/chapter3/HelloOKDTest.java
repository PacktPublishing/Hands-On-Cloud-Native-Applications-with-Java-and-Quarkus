package com.packt.quarkus.chapter3;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class HelloOKDTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/getContainerId")
          .then()
             .statusCode(200);
    }

}