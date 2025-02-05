package br.com.fiap.productcatalog.infraestructure.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import io.restassured.RestAssured;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
class CategoryControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void shouldReturn200andAllCategories() {
        RestAssured.given()
                .contentType("application/json")
                .when()
                .get("/categories")
                .then()
                .statusCode(200)
                .body("size()", equalTo(3))
                .body("[0].name", equalTo("Eletrônicos"))
                .body("[1].name", equalTo("Livros"))
                .body("[2].name", equalTo("Roupas"));
    }

}
