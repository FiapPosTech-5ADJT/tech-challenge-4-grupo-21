package br.com.fiap.productcatalog.infraestructure.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
class ProductControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void shouldReturn200andAllProducts() {
        RestAssured.given()
                .contentType("application/json")
                .when()
                .get("/product")
                .then()
                .statusCode(200)
                .body("size()", equalTo(4))
                .body("[0].name", equalTo("Smartphone"))
                .body("[1].name", equalTo("Notebook"))
                .body("[2].name", equalTo("Livro de Ficção"));
    }

    @Test
    void shouldReturn200andProductStock() {
        RestAssured.given()
                .contentType("application/json")
                .when()
                .get("/product/1/stock")
                .then()
                .statusCode(200)
                .body(equalTo("50.00"));
    }

    @Test
    void shouldReturn204andAddProductStock() {
        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("productId", 1);
        jsonBody.put("quantity", 10);
        RestAssured.given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .post("/product/addStock")
                .then()
                .statusCode(204);

        RestAssured.given()
                .contentType("application/json")
                .when()
                .get("/product/1/stock")
                .then()
                .statusCode(200)
                .body(equalTo("60.00"));
    }

    @Test
    void shouldReturn204andRemoveProductStock() {
        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("productId", 2);
        jsonBody.put("quantity", 10);
        RestAssured.given()
                .contentType("application/json")
                .body(jsonBody)
                .when()
                .post("/product/removeStock")
                .then()
                .statusCode(204);

        RestAssured.given()
                .contentType("application/json")
                .when()
                .get("/product/2/stock")
                .then()
                .statusCode(200)
                .body(equalTo("20.00"));
    }

}
