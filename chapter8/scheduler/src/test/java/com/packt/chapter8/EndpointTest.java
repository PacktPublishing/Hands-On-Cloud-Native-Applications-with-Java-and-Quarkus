package com.packt.chapter8;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class EndpointTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/token")
          .then()
             .statusCode(200);

    }

}