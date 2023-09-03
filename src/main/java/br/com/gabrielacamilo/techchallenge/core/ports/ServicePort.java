package br.com.gabrielacamilo.techchallenge.core.ports;

import br.com.gabrielacamilo.techchallenge.core.domain.BaseDomain;

import java.util.List;

public interface ServicePort <T extends BaseDomain, V extends ValidationPort, G extends PersistencePort> {
    T create(T domain) throws Throwable;
    T update(T domain, String id) throws Throwable;
    T get(String id) throws Throwable;
    void delete(String id) throws Throwable;
    List<T> list() throws Throwable;
    T validateDomainDataAndSave(T domain, V validationPort, G persistencePort) throws Throwable;
}
