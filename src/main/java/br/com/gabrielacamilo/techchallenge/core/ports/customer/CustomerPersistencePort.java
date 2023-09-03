package br.com.gabrielacamilo.techchallenge.core.ports.customer;

import br.com.gabrielacamilo.techchallenge.core.domain.BaseDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.customer.CustomerDomain;
import br.com.gabrielacamilo.techchallenge.core.ports.PersistencePort;

import java.util.List;
import java.util.Optional;

public interface CustomerPersistencePort extends PersistencePort<CustomerDomain> {
    Optional<CustomerDomain> getCustomerByCpf(String cpf);
    Optional<CustomerDomain> getCustomerByEmail(String email);
}
