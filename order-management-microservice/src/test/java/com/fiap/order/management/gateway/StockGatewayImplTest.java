package com.fiap.order.management.gateway;
import com.fiap.order.management.gateway.api.StockGatewayImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.cloud.stream.function.StreamBridge;

import static org.mockito.Mockito.verify;

class StockGatewayImplTest {

    private StreamBridge streamBridge;
    private StockGateway stockGateway;

    @BeforeEach
    void setUp() {
        streamBridge = Mockito.mock(StreamBridge.class);
        stockGateway = new StockGatewayImpl(streamBridge);
    }

    @Test
    void shouldUpdateStock() {
        String json = "{\"productId\": 1, \"quantity\": 10}";

        stockGateway.updateStock(json);

        verify(streamBridge).send("atualizarEstoque-out-0", json);
    }
}
