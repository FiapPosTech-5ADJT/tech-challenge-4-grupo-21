package br.com.fiap.productcatalog.domain.exception;

public abstract class BaseExceptionClass extends RuntimeException {

    public abstract String getCode();

    public abstract Integer getHttpStatus();

    @Override
    public abstract String getMessage();

}
