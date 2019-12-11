package com.packt.quarkus.chapter2;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.lessThan;

@QuarkusTest
public class SimpleRestTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/helloworld")
          .then()
             .statusCode(200)
             .body(is("hello"));

        given()
                .pathParam("name", "Frank")
                .when().get("/helloworld/{name}")
                .then()
                .statusCode(200)
                .body(is("hello Frank"));

        given()
                .pathParam("name", "Frank")
                .when().get("/helloworld/{name}")
                .then()
                .time(lessThan(1000L))
                .body(is("hello Frank"));
    }

}