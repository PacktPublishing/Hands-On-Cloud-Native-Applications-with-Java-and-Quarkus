package org.acme.kogito;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.kie.kogito.Application;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class PersonProcessTest {

    @Inject
    Application application;

    @Test
    public void testProduct() {
        given()
               .body("{\"product\": {\"name\":\"cheese\", \"date\":\"01/10/2019\"}}")
               .contentType(ContentType.JSON)
          .when()
               .post("/products")
          .then()
             .statusCode(200);
//             .body("person.adult", is(true));
    }


}
