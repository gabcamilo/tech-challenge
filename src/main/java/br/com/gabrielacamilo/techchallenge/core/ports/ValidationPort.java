package br.com.gabrielacamilo.techchallenge.core.ports;

import br.com.gabrielacamilo.techchallenge.core.domain.BaseDomain;
import jakarta.validation.*;

import java.util.Optional;
import java.util.Set;

public interface ValidationPort<T extends BaseDomain, G extends PersistencePort> {
    default void validateDomainData(T domainItem) throws ConstraintViolationException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(domainItem);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    default T mustExist(Optional<T> item) throws IllegalArgumentException {
        return item.orElseThrow(() -> new IllegalArgumentException("The item does not exist", new Throwable(item.toString())));
    }

    default void mustNotExist(Optional<T> item) throws IllegalArgumentException {
        item.ifPresent((domainItem) -> {
            throw new IllegalArgumentException("The item already exists", new Throwable(item.toString()));
        });
    }

    void validateCreationalBusinessRules(T domainItem, G persistencePort) throws IllegalArgumentException;
    void validateUpdateBusinessRules(T updatedData, T domainData, G persistencePort) throws IllegalArgumentException;
    void validateDeleteBusinessRules(T domainItem, G persistencePort) throws IllegalArgumentException;
}

