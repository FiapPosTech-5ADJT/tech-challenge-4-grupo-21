package br.com.fiap.productcatalog.infraestructure.controller;

import br.com.fiap.productcatalog.domain.entity.Category;
import br.com.fiap.productcatalog.infraestructure.persistence.converter.db.CategoryEntityConverter;
import br.com.fiap.productcatalog.infraestructure.persistence.jpa.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import io.restassured.RestAssured;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@ActiveProfiles("dev")
class CategoryControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryEntityConverter categoryEntityConverter;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        createCategory();
    }

    @Test
    void shouldReturn200andAllCategories() {
        RestAssured.given()
                .contentType("application/json")
                .when()
                .get("/categories")
                .then()
                .statusCode(200)
                .body("name", equalTo("Test Category"));


    }

    void createCategory() {
        categoryRepository.save(categoryEntityConverter.toEntity(new Category("Test Category")));
    }

}
