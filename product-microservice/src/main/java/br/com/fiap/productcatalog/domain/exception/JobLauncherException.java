package br.com.fiap.productcatalog.domain.exception;

public class JobLauncherException extends BaseExceptionClass {

    private static final String CODE = "errorJobLauncher";
    private static final Integer HTTP_STATUS = 500;
    private final String errorMessage;

    public JobLauncherException(final String message, final String errorMessage) {
        this.errorMessage = message + errorMessage;
    }

    public String getCode() {
        return CODE;
    }

    public Integer getHttpStatus() {
        return HTTP_STATUS;
    }

    public String getMessage() {
        return errorMessage;
    }
}
