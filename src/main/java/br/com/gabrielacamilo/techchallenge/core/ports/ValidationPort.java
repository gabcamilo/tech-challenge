package br.com.gabrielacamilo.techchallenge.core.ports;

import br.com.gabrielacamilo.techchallenge.core.domain.BaseDomain;
import jakarta.validation.*;

import java.util.Optional;
import java.util.Set;

public interface ValidationPort<D extends BaseDomain, P extends PersistencePort> {
    default void validateDomainData(D domainItem) throws ConstraintViolationException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<D>> violations = validator.validate(domainItem);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    default D mustExist(Optional<D> item, String fieldName) throws IllegalArgumentException {
        return item.orElseThrow(() -> new IllegalArgumentException(fieldName));
    }

    default void mustNotExist(Optional<D> item, String fieldName) throws IllegalArgumentException {
        item.ifPresent((domainItem) -> {
            throw new IllegalArgumentException(fieldName);
        });
    }

    void validateCreationalBusinessRules(D domainItem, P persistencePort) throws IllegalArgumentException;
    void validateUpdateBusinessRules(D updatedData, D domainData, P persistencePort) throws IllegalArgumentException;
    void validateDeleteBusinessRules(D domainItem, P persistencePort) throws IllegalArgumentException;
}

