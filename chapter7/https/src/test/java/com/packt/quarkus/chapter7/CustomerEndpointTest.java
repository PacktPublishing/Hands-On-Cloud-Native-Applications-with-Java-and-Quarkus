package com.packt.quarkus.chapter7;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import javax.json.Json;
import javax.json.JsonObject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;


@QuarkusTest
public class CustomerEndpointTest {

    @Test
    public void testCustomerService() {

        given()

                .when().get("/customers")
                .then()
                .statusCode(200)
                .body("$.size()", is(2));





    }
}

