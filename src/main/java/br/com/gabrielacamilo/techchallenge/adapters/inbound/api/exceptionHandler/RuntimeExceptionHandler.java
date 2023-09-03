package br.com.gabrielacamilo.techchallenge.adapters.inbound.api.exceptionHandler;

import br.com.gabrielacamilo.techchallenge.adapters.inbound.api.exceptionHandler.errorDtos.UnexpectedErrorResponse;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

//@RestControllerAdvice
public class RuntimeExceptionHandler {


//    @Autowired
    RuntimeExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private final MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public UnexpectedErrorResponse handle(RuntimeException exception) {
        return buildErrorResponse(exception);
    }

    private UnexpectedErrorResponse buildErrorResponse(RuntimeException exception) {
        String className = exception.getClass().getName();
        String message = exception.getMessage();
        StackTraceElement[] stackTrace = exception.getStackTrace();
        Throwable exceptionCause = exception.getCause();

        return new UnexpectedErrorResponse(className, message, stackTrace, exceptionCause);
    }
}
