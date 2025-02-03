package com.fiap.order.management.gateway.api;

import com.fiap.order.management.gateway.LogisticGateway;
import org.springframework.cloud.stream.function.StreamBridge;

public class LogisticGatewayImpl implements LogisticGateway {
  private static final String ENVIAR_PEDIDO_PARA_ENTREGA = "enviar-pedido-para-entrega-queue";

  private final StreamBridge streamBridge;

  public LogisticGatewayImpl(StreamBridge streamBridge) {
    this.streamBridge = streamBridge;
  }

  @Override
  public void sendOrderToDelivery(String json) {
    streamBridge.send(ENVIAR_PEDIDO_PARA_ENTREGA, json);
  }
}
