package br.com.fiap.logistic.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
class TrackingControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void shouldReturn200WhenUpdatingTrackingLocation() {
        RestAssured.given()
                .contentType("application/json")
                .queryParam("latitude", -23.563099)
                .queryParam("longitude", -46.656571)
                .when()
                .put("/trackings/12773/location")
                .then()
                .statusCode(200);
    }

    @Test
    void shouldReturn404WhenTrackingNotFoundForUpdate() {
        RestAssured.given()
                .contentType("application/json")
                .queryParam("latitude", -23.563099)
                .queryParam("longitude", -46.656571)
                .when()
                .put("/trackings/999/location")
                .then()
                .statusCode(404)
                .body("message", equalTo("Tracking não encontrado"));
    }

    @Test
    void shouldReturn200AndTrackingListByLatitudeAndLongitude() {
        RestAssured.given()
                .contentType("application/json")
                .queryParam("latitude", 84.599498)
                .queryParam("longitude", -146.568067)
                .when()
                .get("/trackings")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(1));
    }

    @Test
    void shouldReturnEmptyListWhenNoTrackingFoundByLatitudeAndLongitude() {
        RestAssured.given()
                .contentType("application/json")
                .queryParam("latitude", 0.0)
                .queryParam("longitude", 0.0)
                .when()
                .get("/trackings")
                .then()
                .statusCode(200)
                .body("size()", equalTo(0));
    }
}
