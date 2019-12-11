package com.packt.quarkus.chapter9;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CustomerEndpointTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/customers")
          .then()
             .statusCode(200);

    }

}