package br.com.gabrielacamilo.techchallenge.core.ports.customer;

import br.com.gabrielacamilo.techchallenge.core.domain.customer.CustomerDomain;
import br.com.gabrielacamilo.techchallenge.core.ports.ValidationPort;

public interface CustomerValidationPort extends ValidationPort<CustomerDomain, CustomerPersistencePort> {

    void validateUniqueCpf(String cpf, CustomerPersistencePort customerPersistencePort) throws IllegalArgumentException;

    void validateUniqueEmail(String email, CustomerPersistencePort customerPersistencePort);
}

