package br.com.fiap.productcatalog.domain.entity;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product createProduct(Long id, String name, String description,
                                  double price, String category, double stock) {
        return new Product(id, name, description, BigDecimal.valueOf(price), createCategory(category), BigDecimal.valueOf(stock));
    }

    private Category createCategory(String name) {
        return new Category(name);
    }

    @Test
    void createProductWithValidData() {
        Product product = createProduct(1L, "Product 1", "Description 1", 10.0,
                "Category 1", 10.0);
        assertEquals(1L, product.getId());
        assertEquals("Product 1", product.getName());
        assertEquals("Description 1", product.getDescription());
        assertEquals(BigDecimal.valueOf(10.0), product.getPrice());
        assertEquals("Category 1", product.getCategory().getName());
        assertEquals(BigDecimal.valueOf(10.0), product.getStock());
    }

    @Test
    void createProductWithNullIdThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                createProduct(null, "Product 1", "Description 1", 10.0,
                        "Category 1", 10.0)
        );
        assertEquals("Id cannot be null", exception.getMessage());
    }

    @Test
    void createProductWithNegativeIdThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                createProduct(-1L, "Product 1", "Description 1", 10.0,
                        "Category 1", 10.0)
        );
        assertEquals("Id cannot be negative", exception.getMessage());
    }

    @Test
    void createProductWithNullNameThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                createProduct(1L, null, "Description 1", 10.0,
                        "Category 1", 10.0)
        );
        assertEquals("Name cannot be null", exception.getMessage());
    }

    @Test
    void createProductWithEmptyNameThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                createProduct(1L, "", "Description 1", 10.0,
                        "Category 1", 10.0)
        );
        assertEquals("Name cannot be null", exception.getMessage());
    }

    @Test
    void createProductWithNullDescriptionThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                createProduct(1L, "Product 1", null, 10.0,
                        "Category 1", 10.0)
        );
        assertEquals("Description cannot be null", exception.getMessage());
    }

    @Test
    void createProductWithEmptyDescriptionThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                createProduct(1L, "Product 1", "", 10.0,
                        "Category 1", 10.0)
        );
        assertEquals("Description cannot be null", exception.getMessage());
    }

    @Test
    void createProductWithNullPriceThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                createProduct(1L, "Product 1", "Description 1", 0.0,
                        "Category 1", 10.0)
        );
        assertEquals("Price cannot be null", exception.getMessage());
    }

    @Test
    void createProductWithNullCategoryThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                createProduct(1L, "Product 1", "Description 1", 10.0,
                        null, 10.0)
        );
        assertEquals("Category cannot be null", exception.getMessage());
    }

    @Test
    void createProductWithNullStockThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                createProduct(1L, "Product 1", "Description 1", 10.0,
                        "Category 1", 0.0)
        );
        assertEquals("Stock cannot be null", exception.getMessage());
    }

    @Test
    void createProductWithNegativeStockThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                createProduct(1L, "Product 1", "Description 1", 10.0,
                        "Category 1", -10.0)
        );
        assertEquals("Stock cannot be negative", exception.getMessage());
    }

    @Test
    void addStockWithNullQuantityThrowsException() {
        Product product = createProduct(1L, "Product 1", "Description 1", 10.0,
                "Category 1", 10.0);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                product.addStock(null)
        );
        assertEquals("Quantity cannot be null", exception.getMessage());
    }

    @Test
    void addStockWithNegativeQuantityThrowsException() {
        Product product = createProduct(1L, "Product 1", "Description 1", 10.0,
                "Category 1", 10.0);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                product.addStock(BigDecimal.valueOf(-10))
        );
        assertEquals("Quantity cannot be negative", exception.getMessage());
    }

    @Test
    void removeStockWithNullQuantityThrowsException() {
        Product product = createProduct(1L, "Product 1", "Description 1", 10.0,
                "Category 1", 10.0);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                product.removeStock(null)
        );
        assertEquals("Quantity cannot be null", exception.getMessage());
    }

    @Test
    void removeStockWithNegativeQuantityThrowsException() {
        Product product = createProduct(1L, "Product 1", "Description 1", 10.0,
                "Category 1", 10.0);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                product.removeStock(BigDecimal.valueOf(-10))
        );
        assertEquals("Quantity cannot be negative", exception.getMessage());
    }

    @Test
    void removeStockWithQuantityGreaterThanStockThrowsException() {
        Product product = createProduct(1L, "Product 1", "Description 1", 10.0,
                "Category 1", 10.0);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                product.removeStock(BigDecimal.valueOf(20))
        );
        assertEquals("Quantity cannot be greater than stock", exception.getMessage());
    }

    @Test
    void removeStockWithQuantityEqualsStock() {
        Product product = createProduct(1L, "Product 1", "Description 1", 10.0,
                "Category 1", 10.0);
        product.removeStock(BigDecimal.valueOf(10));
        assertEquals(BigDecimal.ZERO, product.getStock());
    }

    @Test
    void removeStockWithQuantityLessThanStock() {
        Product product = createProduct(1L, "Product 1", "Description 1", 10.0,
                "Category 1", 10.0);
        product.removeStock(BigDecimal.valueOf(5));
        assertEquals(BigDecimal.valueOf(5), product.getStock());
    }

    @Test
    void removeStockWithQuantityEqualsZero() {
        Product product = createProduct(1L, "Product 1", "Description 1", 10.0,
                "Category 1", 10.0);
        product.removeStock(BigDecimal.ZERO);
        assertEquals(BigDecimal.valueOf(10), product.getStock());
    }

    @Test
    void addStockWithQuantityEqualsZero() {
        Product product = createProduct(1L, "Product 1", "Description 1", 10.0,
                "Category 1", 10.0);
        product.addStock(BigDecimal.ZERO);
        assertEquals(BigDecimal.valueOf(10), product.getStock());
    }

    @Test
    void addStockWithQuantityGreaterThanZero() {
        Product product = createProduct(1L, "Product 1", "Description 1", 10.0,
                "Category 1", 10.0);
        product.addStock(BigDecimal.valueOf(5));
        assertEquals(BigDecimal.valueOf(15), product.getStock());
    }
}
