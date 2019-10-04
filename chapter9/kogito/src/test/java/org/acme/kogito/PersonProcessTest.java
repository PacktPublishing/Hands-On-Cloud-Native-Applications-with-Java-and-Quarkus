package org.acme.kogito;

import static io.restassured.RestAssured.given;


import org.junit.jupiter.api.Test;


import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class PersonProcessTest {


    @Test
    public void testProduct() {
        given()
               .body("{\"product\": {\"name\":\"cheese\", \"date\":\"01/10/2019\"}}")
               .contentType(ContentType.JSON)
          .when()
               .post("/products")
          .then()
             .statusCode(200);

    }

}
