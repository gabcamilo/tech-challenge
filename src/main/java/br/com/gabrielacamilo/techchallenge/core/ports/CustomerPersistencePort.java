package br.com.gabrielacamilo.techchallenge.core.ports;

import br.com.gabrielacamilo.techchallenge.core.domain.CustomerDomain;

import java.util.List;
import java.util.Optional;

public interface CustomerPersistencePort {
    CustomerDomain saveCustomer(CustomerDomain customer);
    Optional<CustomerDomain> getCustomerByCpf(String cpf);
    List<CustomerDomain> listAllCustomers();
    Optional<CustomerDomain> getCustomer(String id);
}
