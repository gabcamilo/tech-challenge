package br.com.gabrielacamilo.techchallenge.core.domain;

import jakarta.validation.*;

import java.util.Set;

public abstract class BaseDomain {
    private String id;

    public BaseDomain(String id) {
    }

    @Deprecated
    public BaseDomain() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static <T> void validate(T domain){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(domain);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
