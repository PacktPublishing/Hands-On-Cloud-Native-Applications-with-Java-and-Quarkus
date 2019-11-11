package com.packt.quarkus.chapter3;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

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