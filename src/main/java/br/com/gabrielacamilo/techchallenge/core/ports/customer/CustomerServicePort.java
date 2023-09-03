package br.com.gabrielacamilo.techchallenge.core.ports.customer;

import br.com.gabrielacamilo.techchallenge.core.domain.customer.CustomerDomain;
import br.com.gabrielacamilo.techchallenge.core.ports.ServicePort;

import java.util.List;

public interface CustomerServicePort extends ServicePort<CustomerDomain, CustomerValidationPort, CustomerPersistencePort> {
    CustomerDomain getCustomerByCpf(String cpf) throws Throwable;
}
