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
        List<Product> mockProducts = TestDataFactory.createProductList();
        when(productGateway.findAll()).thenReturn(mockProducts);

        List<Product> result = productService.getProducts();

        assertEquals(2, result.size());
        assertEquals("Product 1", result.get(0).getName());
        verify(productGateway, times(1)).findAll();
    }

    @Test
    void getProductById_ShouldReturnProduct() {
        when(productGateway.findById(1L)).thenReturn(product);

        Product result = productService.getProductById(1L);

        assertNotNull(result);
        assertEquals("Product 1", result.getName());
        verify(productGateway, times(1)).findById(1L);
    }

    @Test
    void addStock_ShouldIncreaseStock() {
        when(productGateway.findById(1L)).thenReturn(product);
        BigDecimal quantityToAdd = BigDecimal.valueOf(3);

        productService.addStock(1L, quantityToAdd);

        assertEquals(0, product.getStock().compareTo(BigDecimal.valueOf(8)));
        verify(productGateway, times(1)).addStock(product);
    }

    @Test
    void removeStock_ShouldDecreaseStock() {
        when(productGateway.findById(1L)).thenReturn(product);
        BigDecimal quantityToRemove = BigDecimal.valueOf(3);

        productService.removeStock(1L, quantityToRemove);

        assertEquals(0, BigDecimal.valueOf(2).compareTo(product.getStock()));
        verify(productGateway, times(1)).removeStock(product);
    }

    @Test
    void removeStock_ShouldThrowException_WhenStockIsNotEnough() {
        when(productGateway.findById(1L)).thenReturn(product);
        BigDecimal quantityToRemove = BigDecimal.valueOf(10);

        assertThrows(IllegalArgumentException.class, () -> productService.removeStock(1L, quantityToRemove));
        verify(productGateway, never()).removeStock(product);
    }

    @Test
    void getProductStock_ShouldReturnStockValue() {
        when(productGateway.getProductStock(1L)).thenReturn(BigDecimal.valueOf(5));

        BigDecimal stock = productService.getProductStock(1L);

        assertEquals(BigDecimal.valueOf(5), stock);
        verify(productGateway, times(1)).getProductStock(1L);
    }
}