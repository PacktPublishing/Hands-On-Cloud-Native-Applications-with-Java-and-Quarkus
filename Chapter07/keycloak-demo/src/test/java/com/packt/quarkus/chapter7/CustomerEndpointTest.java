package com.packt.quarkus.chapter7;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
@QuarkusTest
public class CustomerEndpointTest {

    @ConfigProperty(name = "keycloak.url")
    String keycloakURL;

    @Test
    public void testHelloEndpoint() {

        String secret ="mysecret";
        RestAssured.baseURI = keycloakURL;
        Response response = given().urlEncodingEnabled(true)
                .auth().preemptive().basic("quarkus-client", secret)
                .param("grant_type", "password")
                .param("client_id", "quarkus-client")
                .param("username", "test")
                .param("password", "test")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .post("/auth/realms/quarkus-realm/protocol/openid-connect/token")
                .then().statusCode(200).extract()
                .response();

        JsonReader jsonReader = Json.createReader(new StringReader(response.getBody().asString()));
        JsonObject object = jsonReader.readObject();
        String userToken = object.getString("access_token");
        response = given().urlEncodingEnabled(true)
                .auth().preemptive().basic("quarkus-client", secret)
                .param("grant_type", "password")
                .param("client_id", "quarkus-client")
                .param("username", "admin")
                .param("password", "test")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .post("/auth/realms/quarkus-realm/protocol/openid-connect/token")
                .then().statusCode(200).extract()
                .response();
        jsonReader = Json.createReader(new StringReader(response.getBody().asString()));


        object = jsonReader.readObject();
        String adminToken = object.getString("access_token");

        RestAssured.baseURI = "http://localhost:8081";
        given().auth().preemptive()
                .oauth2(userToken)
                .when().get("/customers")
                .then()
                .statusCode(200)
                .body("$.size()", is(2));


        JsonObject objOrder = Json.createObjectBuilder()
                .add("item", "bike")
                .add("price", new Long(100))
                .build();


        // Test POST Order for Customer #1

        given().auth()
                .oauth2(adminToken)
                .contentType("application/json")
                .body(objOrder.toString())
                .when()
                .post("/orders/1")
                .then()
                .statusCode(201);

        // Create new JSON for Order #1
        objOrder = Json.createObjectBuilder()
                .add("id", new Long(1))
                .add("item", "mountain bike")
                .add("price", new Long(100))
                .build();

        // Test UPDATE Order #1
        given().auth()
                .oauth2(adminToken)
                .contentType("application/json")
                .body(objOrder.toString())
                .when()
                .put("/orders")
                .then()
                .statusCode(204);

        // Test GET for Order #1
        given().auth()
                .oauth2(userToken)
                .when().get("/orders?customerId=1")
                .then()
                .statusCode(200)
                .body(containsString("mountain bike"));

        // Test DELETE Order #1
        given().auth()
                .oauth2(adminToken)
                .when().delete("/orders/1")
                .then()
                .statusCode(204);




     }

}