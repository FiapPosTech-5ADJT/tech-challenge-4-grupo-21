package br.com.fiap.productcatalog.domain.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

class CategoryTest {

    final List<Product> productListTest = List.of(new Product(1L,
            "Product 1",
            "Description 1",
            BigDecimal.valueOf(10),
            new Category( "Category 1"), BigDecimal.valueOf(10)),
            new Product(2L,
                    "Product 2",
                    "Description 2",
                    BigDecimal.valueOf(20),
                    new Category( "Category 2"), BigDecimal.valueOf(20)));

    @Test
    void createCategoryWithValidData() {
        Category category = new Category(1L, "Electronics", productListTest);
        assertEquals(1L, category.getId());
        assertEquals("Electronics", category.getName());
        assertFalse(category.getProducts().isEmpty());
    }

    @Test
    void createCategoryWithNullIdThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Category(null, "Electronics", Collections.emptyList());
        });
        assertEquals("Id cannot be null", exception.getMessage());
    }

    @Test
    void createCategoryWithNegativeIdThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Category(-1L, "Electronics", Collections.emptyList());
        });
        assertEquals("Id cannot be negative", exception.getMessage());
    }

    @Test
    void createCategoryWithNullNameThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Category(1L, null, Collections.emptyList());
        });
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }

    @Test
    void createCategoryWithEmptyNameThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Category(1L, "", Collections.emptyList());
        });
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }

    @Test
    void createCategoryWithNullProductsThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Category(1L, "Electronics", null);
        });
        assertEquals("Products cannot be null or empty", exception.getMessage());
    }

    @Test
    void createCategoryWithEmptyProductsThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Category(1L, "Electronics", Collections.emptyList());
        });
        assertEquals("Products cannot be null or empty", exception.getMessage());
    }
}