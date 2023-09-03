package br.com.gabrielacamilo.techchallenge.core.ports;

import br.com.gabrielacamilo.techchallenge.core.domain.BaseDomain;

import java.util.Optional;
import java.util.List;

public interface PersistencePort<T extends BaseDomain> {
    T save(T item);

    List<T> list();

    Optional<T> get(String id);

    T update(T item);

    void delete(T item);
}
