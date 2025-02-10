package com.fiap.order.management.controller.exception;

import lombok.Getter;

@Getter
public class EnviarMensagemException extends SystemBaseException {
  private static final long serialVersionUID = 7657583158513527362L;

  private static final String CODE = "pedido-service.erroNoEnvioMensagem";
  private static final String MESSAGE = "Erro ao enviar mensagem";
  private static final Integer HTTP_STATUS = 500;

  @Override
  public String getCode() {
    return "";
  }

  @Override
  public Integer getHttpStatus() {
    return 0;
  }

  @Override
  public String getMessage() {
    return "";
  }
}
