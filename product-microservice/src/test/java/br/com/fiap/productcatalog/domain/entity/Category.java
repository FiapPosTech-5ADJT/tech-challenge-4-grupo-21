package br.com.fiap.productcatalog.domain.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void createCategoryWithValidData() {
        Category category = new Category(1L, "Electronics");
        assertEquals(1L, category.getId());
        assertEquals("Electronics", category.getName());
    }

    @Test
    void createCategoryWithNullIdThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Category(null, "Electronics");
        });
        assertEquals("Id cannot be null", exception.getMessage());
    }

    @Test
    void createCategoryWithNegativeIdThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Category(-1L, "Electronics");
        });
        assertEquals("Id cannot be negative", exception.getMessage());
    }

    @Test
    void createCategoryWithNullNameThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Category(1L, null);
        });
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }

    @Test
    void createCategoryWithEmptyNameThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Category(1L, "");
        });
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }

}