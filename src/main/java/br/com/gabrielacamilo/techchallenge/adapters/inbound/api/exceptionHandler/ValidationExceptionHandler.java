package br.com.gabrielacamilo.techchallenge.adapters.inbound.api.exceptionHandler;

import br.com.gabrielacamilo.techchallenge.adapters.inbound.api.exceptionHandler.errorDtos.FormValidationErrorsResponse;
import br.com.gabrielacamilo.techchallenge.adapters.inbound.api.exceptionHandler.errorDtos.UnexpectedErrorResponse;
import jakarta.validation.ConstraintViolationException;
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
public class ValidationExceptionHandler {

    private MessageSource messageSource;

    public ValidationExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public FormValidationErrorsResponse handle(ConstraintViolationException exception) {

        List<FieldError> fieldErrors = new ArrayList<>();

        exception.getConstraintViolations().forEach(violation -> {
            fieldErrors.add(new FieldError(violation.getRootBeanClass().getName(), violation.getPropertyPath().toString(), violation.getMessage()));
        });

        return buildValidationErrors(fieldErrors);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public FormValidationErrorsResponse handle(IllegalArgumentException exception) {

        List<FieldError> fieldErrors = new ArrayList<>();

        fieldErrors.add(new FieldError("", exception.getMessage(), exception.getMessage() + " already registered"  ));

        return buildValidationErrors(fieldErrors);

    }

//    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(RuntimeException.class)
//    public UnexpectedErrorResponse handle(RuntimeException exception) {
//
//        List<FieldError> fieldErrors =new ArrayList<>();
//
//        fieldErrors.add(new FieldError("", exception.getMessage(), exception.getMessage() + " already registered"  ));
//
//        return buildValidationErrors(fieldErrors);
//
//    }

    private FormValidationErrorsResponse buildValidationErrors(List<FieldError> fieldErrors) {
        FormValidationErrorsResponse validationErrors = new FormValidationErrorsResponse();

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
