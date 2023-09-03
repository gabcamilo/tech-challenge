package br.com.gabrielacamilo.techchallenge.adapters.inbound.api.exceptionHandler.errorDtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FormValidationErrorsResponse implements Serializable {

    private List<FieldError> fieldErrors = new ArrayList<>();

    public void addFieldError(String field, String message) {
        FieldError fieldError = new FieldError(field, message);
        fieldErrors.add(fieldError);
    }

    public List<FieldError> getErrors() {
        return fieldErrors;
    }

    static class FieldError implements Serializable {
        private String field;
        private String error;

        public FieldError(String field, String error) {
            this.field = field;
            this.error = error;
        }

        public String getField() {
            return field;
        }

        public String getError() {
            return error;
        }
    }
}

