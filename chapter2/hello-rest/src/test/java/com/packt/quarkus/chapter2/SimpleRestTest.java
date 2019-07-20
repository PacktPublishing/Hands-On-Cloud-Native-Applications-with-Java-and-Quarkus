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
             .body(is("hello\n"));
    }
    @Test
    public void testHelloEndpointHeader() {
        given()
                .when().get("/helloworld")
                .then()
                .statusCode(200)
                .body(is("hello\n"))
                .and()
                .header("Content-Length","6");
    }
    @Test
    public void testHelloEndpointQueryParam() {
        given()
                .param("name","Frank")
                .when().get("/helloworld")
                .then()
                .statusCode(200)
                .body(is("hello\n"));

    }
    @Test
    public void testHelloEndpointPathParam() {

        given()
                .pathParam("name", "Frank")
                .when().get("/helloworld/{name}")
                .then()
                .statusCode(200)
                .body(is("hello Frank"));
    }
    @Test
    public void testTimedHelloEndpointPathParam() {

        given()
                .pathParam("name", "Frank")
                .when().get("/helloworld/{name}")
                .then()
                .time(lessThan(1000L))
                .body(is("hello Frank"));
    }
}