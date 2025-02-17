package br.com.fiap.logistic.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
class DeliveryPersonControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void shouldReturn200AndCreateDeliveryPerson() {
        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("name", "John Doe");
        jsonBody.put("vehiclePlate", "abc-1234");

        RestAssured.given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .post("/delivery-person")
                .then()
                .statusCode(200)
                .body("name", equalTo("John Doe"))
                .body("vehiclePlate", equalTo("abc-1234"));
    }

    @Test
    void shouldReturn200WhenAssigningDeliveryPerson() {
        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("deliveryPersonId", 528);
        jsonBody.put("orderId", 123);
        jsonBody.put("zipCode", "12345-678");

        RestAssured.given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .put("/delivery-person/assign")
                .then()
                .statusCode(200);
    }

    @Test
    void shouldReturn404WhenCompletingDeliveryButTheStatusIsPending() {
        RestAssured.given()
                .contentType("application/json")
                .when()
                .put("/delivery-person/complete/123")
                .then()
                .statusCode(404)
                .body("message", equalTo("Order can't be completed without being in transit"));
    }

    @Test
    void shouldReturn204WhenCompletingDelivery() {
        RestAssured.given()
                .contentType("application/json")
                .when()
                .put("/delivery-person/complete/124")
                .then()
                .statusCode(204);
    }

    @Test
    void shouldReturn404WhenCompletingNonExistentDelivery() {
        RestAssured.given()
                .contentType("application/json")
                .when()
                .put("/delivery-person/complete/999")
                .then()
                .statusCode(404)
                .body("message", equalTo("Pedido não encontrado"));
    }
}
