package br.com.fiap.productcatalog.infraestructure.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BatchProductControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void shouldReturn200andImportData() {
        File file = new File("src/test/resources/testSmall.csv");
        int milliseconds = 1000;

        RestAssured.given()
                .multiPart("file", file)
                .formParam("milliseconds", milliseconds)
                .when()
                .post("/batch/import")
                .then()
                .statusCode(HttpStatus.OK.value());

        CompletableFuture.runAsync(this::shouldReturn200AndImportedCategories,
                        CompletableFuture.delayedExecutor(milliseconds, TimeUnit.MILLISECONDS))
                .join();

    }

    void shouldReturn200AndImportedCategories() {
        RestAssured.given()
                .contentType("application/json")
                .when()
                .get("/categories")
                .then()
                .statusCode(200)
                .body("size()", not(0))
                .body("[0].name", equalTo("Eletrodomésticos"))
                .body("[1].name", equalTo("Redes"))
                .body("[2].name", equalTo("Periféricos"));
    }
}
