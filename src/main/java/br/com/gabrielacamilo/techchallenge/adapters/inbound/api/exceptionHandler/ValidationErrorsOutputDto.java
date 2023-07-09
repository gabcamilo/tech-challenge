package br.com.gabrielacamilo.techchallenge.adapters.inbound.api.exceptionHandler;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorsOutputDto {
    private List<String> globalErrorMessages = new ArrayList<>();
    private List<FormErrorDto> fieldErrors = new ArrayList<>();

    public void addError(String message) {
        globalErrorMessages.add(message);
    }

    public void addFieldError(String field, String message) {
        FormErrorDto fieldError = new FormErrorDto(field, message);
        fieldErrors.add(fieldError);
    }

    public List<String> getGlobalErrorMessages() {
        return globalErrorMessages;
    }

    public List<FormErrorDto> getErrors() {
        return fieldErrors;
    }

    public int getNumberOfErrors() {
        return this.globalErrorMessages.size() + this.fieldErrors.size();
    }
}
