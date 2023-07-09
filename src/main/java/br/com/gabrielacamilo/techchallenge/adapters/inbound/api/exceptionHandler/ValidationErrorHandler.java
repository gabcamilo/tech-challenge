package br.com.gabrielacamilo.techchallenge.adapters.inbound.api.exceptionHandler;

import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationErrorHandler {

    @Autowired
    ValidationErrorHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ValidationErrorsOutputDto handle(ConstraintViolationException exception) {

        List<FieldError> fieldErrors =new ArrayList<>();

        exception.getConstraintViolations().forEach(violation -> {
            fieldErrors.add(new FieldError(violation.getRootBeanClass().getName(), violation.getPropertyPath().toString(), violation.getMessage()));
        });

        return buildValidationErrors(fieldErrors);

    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ValidationErrorsOutputDto handle(IllegalArgumentException exception) {

        List<FieldError> fieldErrors =new ArrayList<>();

        fieldErrors.add(new FieldError("", exception.getMessage(), exception.getMessage() + " already registered"  ));

        return buildValidationErrors(fieldErrors);

    }

    private ValidationErrorsOutputDto buildValidationErrors(List<FieldError> fieldErrors) {
        ValidationErrorsOutputDto validationErrors = new ValidationErrorsOutputDto();

        fieldErrors.forEach(error -> {
            String errorMessage = getErrorMessage(error);
            validationErrors.addFieldError(error.getField(), errorMessage);
        });
        return validationErrors;
    }


    private String getErrorMessage(ObjectError error) {
        return messageSource.getMessage(error, LocaleContextHolder.getLocale());
    }
}
