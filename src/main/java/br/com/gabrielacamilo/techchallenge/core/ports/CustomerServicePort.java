package br.com.gabrielacamilo.techchallenge.core.ports;

import br.com.gabrielacamilo.techchallenge.core.domain.CustomerDomain;

import java.util.List;

public interface CustomerServicePort {
    CustomerDomain saveCustomer(CustomerDomain customer);
    CustomerDomain getCustomerByCpf(String cpf);
    List<CustomerDomain> getAllCustomers();
}
