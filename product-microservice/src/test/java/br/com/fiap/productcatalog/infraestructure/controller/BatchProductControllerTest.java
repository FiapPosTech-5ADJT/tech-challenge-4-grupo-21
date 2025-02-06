package br.com.fiap.productcatalog.infraestructure.controller;

import io.restassured.RestAssured;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("import")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class BatchProductControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void shouldImportDataAndPersistInDatabaseSmallFile() {
        File file = new File("src/test/resources/testSmall.csv");

        // Enviar requisição de importação
        RestAssured.given()
                .multiPart("file", file)
                .formParam("milliseconds", 0) // Processamento imediato
                .when()
                .post("/batch/import")
                .then()
                .statusCode(HttpStatus.OK.value());

        // Aguardar a importação ser concluída e os dados aparecerem no banco
        Awaitility.await()
                .atMost(5, TimeUnit.SECONDS) // Espera até 5s (ajustável)
                .pollInterval(500, TimeUnit.MILLISECONDS) // Verifica a cada 500ms
                .untilAsserted(this::verifyCategoriesImported);
    }

    @Test
    void shouldImportDataAndPersistInDatabaseLargeFile() {
        File file = new File("src/test/resources/testLarge.csv");

        // Enviar requisição de importação
        RestAssured.given()
                .multiPart("file", file)
                .formParam("milliseconds", 0) // Processamento imediato
                .when()
                .post("/batch/import")
                .then()
                .statusCode(HttpStatus.OK.value());

        // Aguardar a importação ser concluída e os dados aparecerem no banco
        Awaitility.await()
                .atMost(5, TimeUnit.SECONDS) // Espera até 5s (ajustável)
                .pollInterval(500, TimeUnit.MILLISECONDS) // Verifica a cada 500ms
                .untilAsserted(this::verifyProductsImported);
    }

    void verifyCategoriesImported() {
        RestAssured.given()
                .contentType("application/json")
                .when()
                .get("/categories")
                .then()
                .statusCode(200)
                .body("size()", equalTo(2))
                .body(containsString("Eletrônicos"))
                .body(containsString("Casa"));
    }

    void verifyProductsImported() {
        RestAssured.given()
                .contentType("application/json")
                .when()
                .get("/product")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(2500))
                .body(containsString("Perfume 2087"));
    }
}
