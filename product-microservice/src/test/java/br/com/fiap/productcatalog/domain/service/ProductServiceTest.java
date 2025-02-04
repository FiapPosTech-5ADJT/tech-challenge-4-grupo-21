package br.com.fiap.productcatalog.domain.service;

import br.com.fiap.productcatalog.domain.entity.Product;
import br.com.fiap.productcatalog.domain.gateway.ProductGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Habilita o Mockito para injetar os mocks
class ProductServiceTest {

    @Mock
    private ProductGateway productGateway;

    @InjectMocks
    private ProductService productService;

    private Product product;

    @BeforeEach
    void setup() {
        product = TestDataFactory.createProduct(1L, "Product 1", "Description 1", 10.0, TestDataFactory.createCategory(1L, "Category 1"), 5.0);
    }

    @Test
    void getProducts_ShouldReturnListOfProducts() {
        // Arrange
        List<Product> mockProducts = TestDataFactory.createProductList();
        when(productGateway.findAll()).thenReturn(mockProducts);

        // Act
        List<Product> result = productService.getProducts();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Product 1", result.get(0).getName());
        verify(productGateway, times(1)).findAll();
    }

    @Test
    void getProductById_ShouldReturnProduct() {
        // Arrange
        when(productGateway.findById(1L)).thenReturn(product);

        // Act
        Product result = productService.getProductById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Product 1", result.getName());
        verify(productGateway, times(1)).findById(1L);
    }

    @Test
    void addStock_ShouldIncreaseStock() {
        // Arrange
        when(productGateway.findById(1L)).thenReturn(product);
        BigDecimal quantityToAdd = BigDecimal.valueOf(3);

        // Act
        productService.addStock(1L, quantityToAdd);

        // Assert
        assertEquals(0, product.getStock().compareTo(BigDecimal.valueOf(8)));
        verify(productGateway, times(1)).addStock(product);
    }

    @Test
    void removeStock_ShouldDecreaseStock() {
        // Arrange
        when(productGateway.findById(1L)).thenReturn(product);
        BigDecimal quantityToRemove = BigDecimal.valueOf(3);

        // Act
        productService.removeStock(1L, quantityToRemove);

        // Assert
        assertEquals(0, BigDecimal.valueOf(2).compareTo(product.getStock()));
        verify(productGateway, times(1)).removeStock(product);
    }

    @Test
    void removeStock_ShouldThrowException_WhenStockIsNotEnough() {
        // Arrange
        when(productGateway.findById(1L)).thenReturn(product);
        BigDecimal quantityToRemove = BigDecimal.valueOf(10);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> productService.removeStock(1L, quantityToRemove));
        verify(productGateway, never()).removeStock(product);
    }

    @Test
    void getProductStock_ShouldReturnStockValue() {
        // Arrange
        when(productGateway.getProductStock(1L)).thenReturn(BigDecimal.valueOf(5));

        // Act
        BigDecimal stock = productService.getProductStock(1L);

        // Assert
        assertEquals(BigDecimal.valueOf(5), stock);
        verify(productGateway, times(1)).getProductStock(1L);
    }
}