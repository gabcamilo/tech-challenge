package br.com.gabrielacamilo.techchallenge.core.ports;

import br.com.gabrielacamilo.techchallenge.core.domain.CustomerDomain;

import java.util.List;
import java.util.Optional;

public interface CustomerServicePort {
    CustomerDomain saveCustomer(CustomerDomain customer);
    Optional<CustomerDomain> getCustomerByCpf(String cpf);
    List<CustomerDomain> getAllCustomers();
}
