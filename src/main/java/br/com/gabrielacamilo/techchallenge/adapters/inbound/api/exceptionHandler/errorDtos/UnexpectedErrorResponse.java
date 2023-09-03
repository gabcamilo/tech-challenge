package br.com.gabrielacamilo.techchallenge.adapters.inbound.api.exceptionHandler.errorDtos;

import java.io.Serializable;

public class UnexpectedErrorResponse implements Serializable {

    private String className;
    private String message;
    private StackTraceElement[] stackTrace;
    private Throwable exceptionCause;

    public UnexpectedErrorResponse(String className, String message, StackTraceElement[] stackTrace, Throwable exceptionCause) {
        this.className = className;
        this.message = message;
        this.stackTrace = stackTrace;
        this.exceptionCause = exceptionCause;
    }

    public String getClassName() {
        return className;
    }

    public String getMessage() {
        return message;
    }

    public StackTraceElement[] getStackTrace() {
        return stackTrace;
    }

    public Throwable getExceptionCause() {
        return exceptionCause;
    }
}
