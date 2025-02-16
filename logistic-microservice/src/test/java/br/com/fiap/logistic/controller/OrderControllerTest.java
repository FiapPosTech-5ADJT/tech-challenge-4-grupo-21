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
class OrderControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void shouldReturn200andAllOrdersByZipCode() {
        RestAssured.given()
                .contentType("application/json")
                .queryParam("zipCode", "12345-678")
                .when()
                .get("/orders")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(2)) // Verifica que retornou pelo menos 2 pedidos
                .body("[0].zipCode", equalTo("12345-678"));
    }

    @Test
    void shouldReturn404WhenNoOrdersFoundByZipCode() {
        RestAssured.given()
                .contentType("application/json")
                .queryParam("zipCode", "99999-999")
                .when()
                .get("/orders")
                .then()
                .statusCode(200)
                .body("size()", equalTo(0)); // Verifica que nenhum pedido foi encontrado
    }

    @Test
    void shouldReturn200andUpdateOrderStatus() {
        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("status", "IN_TRANSIT");
        RestAssured.given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .put("/orders/123/status?status=IN_TRANSIT")
                .then()
                .statusCode(200);
    }

    @Test
    void shouldReturn400WhenInvalidStatus() {
        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("status", "INVALID_STATUS");
        RestAssured.given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .put("/orders/124/status?status=INVALID_STATUS")
                .then()
                .statusCode(400);
    }

    @Test
    void shouldReturn404WhenOrderNotFoundForStatusUpdate() {
        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("status", "COMPLETED");
        RestAssured.given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .put("/orders/999/status?status=COMPLETED")
                .then()
                .statusCode(404)
                 .body("message", equalTo("Order not found"));
    }
}
