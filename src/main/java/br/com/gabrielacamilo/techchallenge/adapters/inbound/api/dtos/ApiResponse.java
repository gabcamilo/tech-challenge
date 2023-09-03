package br.com.gabrielacamilo.techchallenge.adapters.inbound.api.dtos;

import java.io.Serializable;

public class ApiResponse implements Serializable {
    private String message;
    private Integer httpStatusCode;
    private Serializable data;

    public ApiResponse(String message, Integer httpStatusCode, Serializable data) {
        this.message = message;
        this.httpStatusCode = httpStatusCode;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public Serializable getData() {
        return data;
    }
}
