package com.fiap.order.management.gateway;

import com.fiap.order.management.gateway.api.LogisticGatewayImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.cloud.stream.function.StreamBridge;

import static org.mockito.Mockito.verify;

class LogisticGatewayImplTest {

    private StreamBridge streamBridge;
    private LogisticGateway logisticGateway;

    @BeforeEach
    void setUp() {
        streamBridge = Mockito.mock(StreamBridge.class);
        logisticGateway = new LogisticGatewayImpl(streamBridge);
    }

    @Test
    void shouldSendOrderToDelivery() {
        String json = "{\"orderId\": 1}";

        logisticGateway.sendOrderToDelivery(json);

        verify(streamBridge).send("logistica-out-0", json);
    }
}
