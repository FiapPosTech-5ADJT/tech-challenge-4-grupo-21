package com.fiap.order.management.gateway.api;

import com.fiap.order.management.gateway.StockGateway;
import org.springframework.cloud.stream.function.StreamBridge;

public class StockGatewayImpl implements StockGateway {

  private static final String ATUALIZAR_ESTOQUE = "atualizar-estoque-queue";

  private final StreamBridge streamBridge;

  public StockGatewayImpl(StreamBridge streamBridge) {
    this.streamBridge = streamBridge;
  }

  @Override
    public void updateStock(String json) {
      streamBridge.send(ATUALIZAR_ESTOQUE, json);
  }
}
