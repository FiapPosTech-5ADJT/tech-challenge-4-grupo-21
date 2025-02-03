package com.fiap.order.management.controller.exception;

import lombok.Getter;

@Getter
public class EnviarMensagemException extends SystemBaseException {
	private static final long serialVersionUID = 7657583158513527362L;

	private final String code = "pedido-service.erroNoEnvioMensagem";
	private final String message = "Erro ao enviar mensagem";
	private final Integer httpStatus = 500;
}
