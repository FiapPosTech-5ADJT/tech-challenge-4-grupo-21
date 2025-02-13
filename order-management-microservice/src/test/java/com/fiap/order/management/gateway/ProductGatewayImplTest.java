package com.fiap.order.management.gateway;

import com.fiap.order.management.clients.ProductClient;
import com.fiap.order.management.gateway.api.ProductGatewayImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class ProductGatewayImplTest {

    private ProductClient productClient;
    private ProductGatewayImpl productGateway;

    @BeforeEach
    void setUp() {
        productClient = Mockito.mock(ProductClient.class);
        productGateway = new ProductGatewayImpl(productClient);
    }

    @Test
    void shouldFindProductById() {
        Long productId = 1L;
        BigDecimal expectedQuantity = BigDecimal.TEN;
        ResponseEntity<BigDecimal> responseEntity = ResponseEntity.ok(expectedQuantity);
        when(productClient.findById(productId)).thenReturn(responseEntity);

        ResponseEntity<BigDecimal> actualResponse = productGateway.findById(productId);

        assertThat(actualResponse).isEqualTo(responseEntity);
        assertThat(actualResponse.getBody()).isEqualTo(expectedQuantity);
    }
}
